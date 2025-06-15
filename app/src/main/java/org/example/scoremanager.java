package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class scoremanager {
    private static final String URL = "jdbc:mysql://localhost:3306/scoremanager";
    private static final String USER = "root";
    private static final String PASSWORD = "Khan_1795";

    public static void saveScore(String playerName, int score) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scoremanager", "root", "Khan_1795");

            String sql = "INSERT INTO scores (player_name, score) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, playerName);
            stmt.setInt(2, score);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
