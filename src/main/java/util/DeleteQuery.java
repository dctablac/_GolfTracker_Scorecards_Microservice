package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteQuery {
    
    public static boolean deleteScorecard(int id) {
        String DELETE = "DELETE\n";
        String FROM = "FROM scorecards\n";
        String WHERE = "WHERE id = ?;";

        String query = DELETE + FROM + WHERE;
        Connection con = dbUtil.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
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