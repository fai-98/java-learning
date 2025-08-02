import java.sql.*;

public class UserDAO {
    public void listUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        try (Connection conn = JavaDatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
            }
        }
    }
}

