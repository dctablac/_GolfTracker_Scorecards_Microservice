package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.ParModel;
import models.PlayerModel;
import models.YardModel;

public class CreateQuery {

    // Verify if a scorecard entry exists according to a specific combo
    // of email, course name, and play date.
    public static boolean duplicateScorecard(String email, String course_name, String play_date) {
        String SELECT = "SELECT COUNT(id) AS count\n";
        String FROM = "FROM scorecards\n";
        String WHERE = "WHERE email = ? && course_name = ? && play_date = ?;";
        
        String query = SELECT + FROM + WHERE;

        Connection con = dbUtil.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, course_name);
            ps.setString(3, play_date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("count");
                if (count != 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Create a unique scorecard entry in the scorecard table.
    public static boolean createScorecard(String email, String course_name, String play_date) {
        String INSERT = "INSERT INTO scorecards(course_name, play_date, email)\n";
        String VALUES = "VALUES(?, ?, ?);";

        String query = INSERT + VALUES;

        Connection con = dbUtil.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, course_name);
            ps.setString(2, play_date);
            ps.setString(3, email);
            int affected = ps.executeUpdate();
            if (affected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve the id of the newly created scorecard.
    public static int getScorecardId(String email, String course_name, String play_date) {
        String SELECT = "SELECT id\n";
        String FROM = "FROM scorecards\n";
        String WHERE = "WHERE email = ? && course_name = ? && play_date = ?;";
        
        String query = SELECT + FROM + WHERE;

        Connection con = dbUtil.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, course_name);
            ps.setString(3, play_date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_result = rs.getInt("id");
                return id_result;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // error here
    }

    // With the retrieved id of the new scorecard, insert scorecard data into their respective tables.

    // Insert scorecard's yardage data.
    public static boolean insertYardData(int course_id, YardModel yards) {

        Integer yard_front = yards.getYardFront();
        Integer yard_back = yards.getYardBack();
        Integer yard_total = yards.getYardTotal();
        Integer yard_handicap = yards.getYardHandicap();
        Integer[] yard_list = yards.getYardList();

        String INSERT_YARDS = "INSERT INTO yards(id, yard_1, yard_2, yard_3, yard_4, yard_5, yard_6, yard_7, yard_8, yard_9, " +
                              "yard_front, yard_10, yard_11, yard_12, yard_13, yard_14, yard_15, yard_16, yard_17, yard_18, " + 
                              "yard_back, yard_total, yard_handicap)\n";
        String VALUES = "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        String query = INSERT_YARDS + VALUES;

        Connection con = dbUtil.getConnection();

        try {  
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, course_id);
            ps.setInt(2, yard_list[0]);
            ps.setInt(3, yard_list[1]);
            ps.setInt(4, yard_list[2]);
            ps.setInt(5, yard_list[3]);
            ps.setInt(6, yard_list[4]);
            ps.setInt(7, yard_list[5]);
            ps.setInt(8, yard_list[6]);
            ps.setInt(9, yard_list[7]);
            ps.setInt(10, yard_list[8]);
            ps.setInt(11, yard_front);
            ps.setInt(12, yard_list[9]);
            ps.setInt(13, yard_list[10]);
            ps.setInt(14, yard_list[11]);
            ps.setInt(15, yard_list[12]);
            ps.setInt(16, yard_list[13]);
            ps.setInt(17, yard_list[14]);
            ps.setInt(18, yard_list[15]);
            ps.setInt(19, yard_list[16]);
            ps.setInt(20, yard_list[17]);
            ps.setInt(21, yard_back);
            ps.setInt(22, yard_total);
            ps.setInt(23, yard_handicap);
            int affected = ps.executeUpdate();
            if (affected < 1) {
                return false;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Insert scorecard's par data.
    public static boolean insertParData(int course_id, ParModel pars) {

        Integer par_front = pars.getParFront();
        Integer par_back = pars.getParBack();
        Integer par_total = pars.getParTotal();
        Integer par_handicap = pars.getParHandicap();
        Integer[] par_list = pars.getParList();

        String INSERT_PARS = "INSERT INTO pars(id, pars_1, pars_2, pars_3, pars_4, pars_5, pars_6, pars_7, pars_8, pars_9, " +
                              "pars_front, pars_10, pars_11, pars_12, pars_13, pars_14, pars_15, pars_16, pars_17, pars_18, " + 
                              "pars_back, pars_total, pars_handicap)\n";
        String VALUES = "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        String query = INSERT_PARS + VALUES;

        Connection con = dbUtil.getConnection();

        try {  
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, course_id);
            ps.setInt(2, par_list[0]);
            ps.setInt(3, par_list[1]);
            ps.setInt(4, par_list[2]);
            ps.setInt(5, par_list[3]);
            ps.setInt(6, par_list[4]);
            ps.setInt(7, par_list[5]);
            ps.setInt(8, par_list[6]);
            ps.setInt(9, par_list[7]);
            ps.setInt(10, par_list[8]);
            ps.setInt(11, par_front);
            ps.setInt(12, par_list[9]);
            ps.setInt(13, par_list[10]);
            ps.setInt(14, par_list[11]);
            ps.setInt(15, par_list[12]);
            ps.setInt(16, par_list[13]);
            ps.setInt(17, par_list[14]);
            ps.setInt(18, par_list[15]);
            ps.setInt(19, par_list[16]);
            ps.setInt(20, par_list[17]);
            ps.setInt(21, par_back);
            ps.setInt(22, par_total);
            ps.setInt(23, par_handicap);
            int affected = ps.executeUpdate();
            if (affected < 1) {
                return false;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Insert player data into player table.
    public static boolean insertPlayerData(int course_id, PlayerModel player) {
        if (player == null) {
            System.out.println("No player data detected.");
            return false;
        }
        String player_name = player.getName();
        Integer score_front = player.getScoreFront();
        Integer score_back = player.getScoreBack();
        Integer score_total = player.getScoreTotal();
        Integer score_handicap = player.getScoreHandicap();
        Integer[] strokes = player.getStrokes();

        String INSERT = "INSERT INTO player(id, name, score_1, score_2, score_3, score_4, score_5, score_6, score_7, " +
                        "score_8, score_9, score_front, score_10, score_11, score_12, score_13, score_14, score_15, " + 
                        "score_16, score_17, score_18, score_back, score_total, score_handicap)\n";
        String VALUES = "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        String query = INSERT + VALUES;

        Connection con = dbUtil.getConnection();

        try {  
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, course_id);
            ps.setString(2, player_name);
            ps.setInt(3, strokes[0]);
            ps.setInt(4, strokes[1]);
            ps.setInt(5, strokes[2]);
            ps.setInt(6, strokes[3]);
            ps.setInt(7, strokes[4]);
            ps.setInt(8, strokes[5]);
            ps.setInt(9, strokes[6]);
            ps.setInt(10, strokes[7]);
            ps.setInt(11, strokes[8]);
            ps.setInt(12, score_front);
            ps.setInt(13, strokes[9]);
            ps.setInt(14, strokes[10]);
            ps.setInt(15, strokes[11]);
            ps.setInt(16, strokes[12]);
            ps.setInt(17, strokes[13]);
            ps.setInt(18, strokes[14]);
            ps.setInt(19, strokes[15]);
            ps.setInt(20, strokes[16]);
            ps.setInt(21, strokes[17]);
            ps.setInt(22, score_back);
            ps.setInt(23, score_total);
            ps.setInt(24, score_handicap);
            int affected = ps.executeUpdate();
            if (affected < 1) {
                return false;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}