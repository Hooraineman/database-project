package org.example;

import java.sql.*;
import java.util.Scanner;

public class virusescapegame {

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/virusescapegame";
    private static final String USER = "root";
    private static final String PASS = "Khan_1795";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement()
        ) {
            System.out.println("✅ Database connected: true\n");

            while (true) {
                System.out.print("Enter player name (or type 'exit' to finish): ");
                String name = scanner.nextLine().trim();
                if (name.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.print("Enter score: ");
                int score;
                try {
                    score = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("❌ Invalid score. Please enter a number.\n");
                    continue;
                }

                // Insert the score
                String insertQuery = "INSERT INTO scores (player_name, score) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                    pstmt.setString(1, name);
                    pstmt.setInt(2, score);
                    pstmt.executeUpdate();
                    System.out.println("✅ Score inserted successfully!\n");
                } catch (SQLException e) {
                    System.out.println("❌ Failed to insert score: " + e.getMessage());
                }
            }

            // Display all scores
            System.out.println("\n--- All Scores ---");
            ResultSet rs = stmt.executeQuery("SELECT player_name, score FROM scores");
            while (rs.next()) {
                System.out.printf("%s - %d%n", rs.getString("player_name"), rs.getInt("score"));
            }

            // Display highest score
            rs = stmt.executeQuery("SELECT player_name, score FROM scores ORDER BY score DESC LIMIT 1");
            if (rs.next()) {
                System.out.printf("\n🏆 Highest Score: %s - %d%n", rs.getString("player_name"), rs.getInt("score"));
            }

            // Display lowest score
            rs = stmt.executeQuery("SELECT player_name, score FROM scores ORDER BY score ASC LIMIT 1");
            if (rs.next()) {
                System.out.printf("📉 Lowest Score: %s - %d%n", rs.getString("player_name"), rs.getInt("score"));
            }

        } catch (SQLException e) {
            System.out.println("❌ Database error: " + e.getMessage());
            System.out.println("✅ Database connected: false");
        }

        scanner.close();
    }
}
