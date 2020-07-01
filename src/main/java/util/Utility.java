package util;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import models.AllScorecardsResponseModel;
import models.ParModel;
import models.PlayerModel;
import models.PlayersModel;
import models.ResponseModel;
import models.ScorecardModel;
import models.SessionResponseModel;
import models.ScorecardRequestModel;
import models.ScorecardResponseModel;
import models.YardModel;

public class Utility {
    
    public static ResponseEntity<SessionResponseModel> createScorecard(ScorecardRequestModel requestModel) {
        
        String email = requestModel.getEmail();
        String courseName = requestModel.getCourseName();
        String playDate = requestModel.getPlayDate();
        YardModel yards = requestModel.getYards();
        ParModel pars = requestModel.getPars();
        PlayersModel players = requestModel.getPlayers();

        SessionResponseModel responseModel = null;

        try {
            if (CreateQuery.duplicateScorecard(email, courseName, playDate)) {
                responseModel = new SessionResponseModel(131, "Scorecard for course on that day already exists. Please enter a different course name.", null);
            } else {
                CreateQuery.createScorecard(email, courseName, playDate);
                int id = CreateQuery.getScorecardId(email, courseName, playDate);
                CreateQuery.insertYardData(id, yards);
                CreateQuery.insertParData(id, pars);
                CreateQuery.insertPlayerData(id, players.getPlayer1());
                CreateQuery.insertPlayerData(id, players.getPlayer2());
                CreateQuery.insertPlayerData(id, players.getPlayer3());
                CreateQuery.insertPlayerData(id, players.getPlayer4());
                responseModel = new SessionResponseModel(130, "Scorecard created successfully.", "1234");
            }
        } catch (Exception e) {
            if (e instanceof JsonMappingException) {
                responseModel = new SessionResponseModel(-3, "JSON Mapping Exception.", null);
            }
            else if (e instanceof JsonParseException) {
                responseModel = new SessionResponseModel(-2, "JSON Parse Exception", null);
            }
            return new ResponseEntity<SessionResponseModel>(responseModel, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<SessionResponseModel>(responseModel, HttpStatus.OK);
    }

    public static ResponseEntity<AllScorecardsResponseModel> allScorecards(String email) {
        AllScorecardsResponseModel responseModel = null;
        try {
            ResultSet rs = RetrieveQuery.allScorecards(email);
            ArrayList<ScorecardModel> scorecards = new ArrayList<ScorecardModel>();
            while (rs.next()) {
                ScorecardModel scorecard = new ScorecardModel(
                    rs.getInt("id"), rs.getString("course_name"), 
                    rs.getString("play_date"), rs.getString("email"));
                scorecards.add(scorecard);
            }
            responseModel = new AllScorecardsResponseModel(140, "All scorecards retrieved successfully.", scorecards);
        } catch (Exception e) {
            if (e instanceof JsonMappingException) {
                responseModel = new AllScorecardsResponseModel(-3, "JSON Mapping Exception.", null);
            }
            else if (e instanceof JsonParseException) {
                responseModel = new AllScorecardsResponseModel(-2, "JSON Parse Exception", null);
            }
            return new ResponseEntity<AllScorecardsResponseModel>(responseModel, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<AllScorecardsResponseModel>(responseModel, HttpStatus.OK);
    }

    public static ResponseEntity<ScorecardResponseModel> scorecardDetails(int id, String email) {
        ScorecardResponseModel responseModel = null;
        YardModel yards = null;
        ParModel pars = null;
        PlayersModel playersModel = null;

        try {
            ResultSet rs = RetrieveQuery.scorecardDetails(id);
            while (rs.next()) {
                ArrayList<Integer> yard_list = new ArrayList<Integer>();
                ArrayList<Integer> par_list = new ArrayList<Integer>();

                for (int i = 1; i <= 18; i++) {
                    yard_list.add(rs.getInt(String.format("yard_%d", i)));
                }
                for (int j = 1; j <= 18; j++) {
                    par_list.add(rs.getInt(String.format("pars_%d", j)));
                }

                yards = new YardModel(rs.getInt("yard_front"), 
                                      rs.getInt("yard_back"), 
                                      rs.getInt("yard_total"), 
                                      rs.getInt("yard_handicap"), 
                                      yard_list.toArray(new Integer[0]));
                pars = new ParModel(rs.getInt("pars_front"),
                                    rs.getInt("pars_back"),
                                    rs.getInt("pars_total"),
                                    rs.getInt("pars_handicap"),
                                    par_list.toArray(new Integer[0]));
            }

            ArrayList<PlayerModel> players = new ArrayList<PlayerModel>();
            ResultSet rs2 = RetrieveQuery.playerDetails(id);

            // Loop through each player details
            while (rs2.next()) {
                String name = rs2.getString("name");
                Integer score_front = rs2.getInt("score_front");
                Integer score_back = rs2.getInt("score_back");
                Integer score_total = rs2.getInt("score_total");
                Integer score_handicap = rs2.getInt("score_handicap");

                // Add score for each hole
                Integer[] strokes = new Integer[18];
                for (int i = 1; i <= 18; i++) {
                    strokes[i-1] = rs2.getInt(String.format("score_%d", i));
                }
                
                PlayerModel player = new PlayerModel(name, score_front, 
                                                     score_back, score_total, 
                                                     score_handicap, strokes);
                players.add(player);
            }

            playersModel = new PlayersModel(players.get(0), players.get(1), players.get(2), players.get(3));

            responseModel = new ScorecardResponseModel(150, "Scorecard details retrieved successfully.", 
                                                        yards, pars, playersModel);
        } catch (Exception e) {
            if (e instanceof JsonMappingException) {
                responseModel = new ScorecardResponseModel(-3, "JSON Mapping Exception.", null, null, null);
            }
            else if (e instanceof JsonParseException) {
                responseModel = new ScorecardResponseModel(-2, "JSON Parse Exception", null, null, null);
            }
            e.printStackTrace();
            return new ResponseEntity<ScorecardResponseModel>(responseModel, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ScorecardResponseModel>(responseModel, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseModel>deleteScorecard(int id) {
        ResponseModel responseModel = null;

        try {
            if (DeleteQuery.deleteScorecard(id) != true) {
                responseModel = new ResponseModel(161, "Scorecard to delete does not exist.");
            }
            else {
                responseModel = new ResponseModel(160, "Scorecard has been deleted.");
            }
        } catch(Exception e) {
            if (e instanceof JsonMappingException) {
                responseModel = new ResponseModel(-3, "JSON Mapping Exception.");
            }
            else if (e instanceof JsonParseException) {
                responseModel = new ResponseModel(-2, "JSON Parse Exception");
            }
            e.printStackTrace();
        }
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseModel>updateScorecard(int oldId, ScorecardRequestModel requestModel) {
        // Delete scorecard by id
        // Insert new scorecard w/ edits, has new id.
        String email = requestModel.getEmail();
        String courseName = requestModel.getCourseName();
        String playDate = requestModel.getPlayDate();
        YardModel yards = requestModel.getYards();
        ParModel pars = requestModel.getPars();
        PlayersModel players = requestModel.getPlayers();

        ResponseModel responseModel = null;
        
        try {
            if (DeleteQuery.deleteScorecard(oldId) != true) {
                responseModel = new ResponseModel(171, "Could not delete scorecard.");
                return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.BAD_REQUEST);
            }
            else {
                CreateQuery.createScorecard(email, courseName, playDate);
                int newId = CreateQuery.getScorecardId(email, courseName, playDate);
                CreateQuery.insertYardData(newId, yards);
                CreateQuery.insertParData(newId, pars);
                CreateQuery.insertPlayerData(newId, players.getPlayer1());
                CreateQuery.insertPlayerData(newId, players.getPlayer2());
                CreateQuery.insertPlayerData(newId, players.getPlayer3());
                CreateQuery.insertPlayerData(newId, players.getPlayer4());
                responseModel = new ResponseModel(170, "Scorecard updated successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof JsonMappingException) {
                responseModel = new ResponseModel(-3, "JSON Mapping Exception.");
            }
            else if (e instanceof JsonParseException) {
                responseModel = new ResponseModel(-2, "JSON Parse Exception");
            }
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }
}