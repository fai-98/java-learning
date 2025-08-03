import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class UserDAO {

    public void createUserTable() throws SQLException {
        // Step 1: Define the SQL statement for creating the 'users' table if it doesn't already exist
        String sql = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50))";

        // Step 2: Open a connection and prepare the statement using try-with-resources to ensure automatic closing
        try (Connection conn = JavaDatabaseConnection.getConnection();  // Step 2a: Get database connection
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Step 2b: Prepare the SQL statement

            // Step 3: Execute the SQL statement
            stmt.executeUpdate();
        }
    }

    public void insertUser(String name, String email) throws SQLException {
        // Step 1: Define the SQL INSERT statement with a placeholder for the name
        String sql = "INSERT INTO users(name,email) VALUES (?,?)";

        // Step 2: Open a connection and prepare the statement using try-with-resources
        try (Connection conn = JavaDatabaseConnection.getConnection();  // Step 2a: Get database connection
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Step 2b: Prepare the SQL statement

            // Step 3: Set the value for the placeholder (bind parameter)
            stmt.setString(1, name);  // Replace the ? with the actual name value
            stmt.setString(2,email);
            // Step 4: Execute the INSERT operation
            stmt.executeUpdate();
        }
    }

    public void updateUser(int id, String newName, String newEmail) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE users SET ");
        List<Object> parameters = new ArrayList<>();

        if (newName != null) {
            sql.append("name = ?");
            parameters.add(newName);
        }
        if (newEmail != null) {
            if (!parameters.isEmpty()) {
                sql.append(", ");
            }
            sql.append("email = ?");
            parameters.add(newEmail);
        }

        if (parameters.isEmpty()) {
            return; // Nothing to update
        }

        sql.append(" WHERE id = ?");
        parameters.add(id);

        try (Connection conn = JavaDatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                stmt.setObject(i + 1, parameters.get(i));
            }
            stmt.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException{
        String sql = "DELETE FROM users WHERE id = ?";

        try(Connection conn = JavaDatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            stmt.executeUpdate();
        }
    }

    public void deleteUsersByIds(List<Integer> ids) throws SQLException {
        if (ids == null || ids.isEmpty()) return;

        // Construct the SQL: DELETE FROM users WHERE id IN (?, ?, ?)
        String placeholders = ids.stream()
                .map(id -> "?")
                .collect(Collectors.joining(", "));

        String sql = "DELETE FROM users WHERE id IN (" + placeholders + ")";

        try (Connection conn = JavaDatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters for each placeholder
            for (int i = 0; i < ids.size(); i++) {
                stmt.setInt(i + 1, ids.get(i));
            }

            int deletedRows = stmt.executeUpdate();
            System.out.println(deletedRows + " rows deleted.");
        }
    }


    public void listUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        try (Connection conn = JavaDatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("name")+ " - " + rs.getString("email"));
            }
        }
    }

}

//DAO (Data Access Object)