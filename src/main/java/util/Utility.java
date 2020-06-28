package util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import models.ParModel;
import models.PlayersModel;
import models.ResponseModel;
import models.ScorecardRequestModel;
import models.YardModel;

public class Utility {
    
    public static ResponseEntity<ResponseModel> createScorecard(ScorecardRequestModel requestModel) {
        String email = requestModel.getEmail();
        String courseName = requestModel.getCourseName();
        String playDate = requestModel.getPlayDate();
        YardModel yards = requestModel.getYards();
        ParModel pars = requestModel.getPars();
        PlayersModel players = requestModel.getPlayers();

        for (Integer i : yards.getYardList()) {
            System.out.println(String.format("Yard %d", i));
        }
        for (Integer p : pars.getParList()) {
            System.out.println(String.format("Par %d", p));
        }
        for (Integer d : players.getPlayer1().getStrokes()) {
            System.out.println(String.format("Player 1 score: %d", d));
        }

        
        ResponseModel responseModel = null;
        try {
            responseModel = new ResponseModel(130, "Scorecard created successfully.", "1234");
        } catch (Exception e) {
            if (e instanceof JsonMappingException) {
                responseModel = new ResponseModel(-3, "JSON Mapping Exception.", null);
            }
            else if (e instanceof JsonParseException) {
                responseModel = new ResponseModel(-2, "JSON Parse Exception", null);
            }
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }
}