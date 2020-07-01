package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveQuery {
    
    public static ResultSet allScorecards(String email) {
        String SELECT = "SELECT *\n";
        String FROM = "FROM scorecards\n";
        String WHERE = "WHERE email = ?";

        String query = SELECT + FROM + WHERE;

        Connection con = dbUtil.getConnection();
        ResultSet rs = null;

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet scorecardDetails(int id) {
        String SELECT = "SELECT *\n";
        String FROM = "FROM scorecards\n";
        String JOIN = "INNER JOIN yards\n" + 
                      "ON scorecards.id = yards.id\n" +
                      "INNER JOIN pars\n" +
                      "ON scorecards.id = pars.id\n";
        String WHERE = "WHERE scorecards.id = ?\n";
        String ORDERBY = "ORDER BY scorecards.id;";

        String query = SELECT + FROM + JOIN + WHERE + ORDERBY;

        Connection con = dbUtil.getConnection();
        ResultSet rs = null;

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet playerDetails(int id) {
        String SELECT = "SELECT *\n";
        String FROM = "FROM player\n";
        String WHERE = "WHERE id = ?;";

        String query = SELECT + FROM + WHERE;

        Connection con = dbUtil.getConnection();
        ResultSet rs = null;

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}