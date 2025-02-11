import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:postgresql://localhost:5432/Transport";
        String username = "postgres";
        String password = "Fdds1223";

        // Attempt to connect to the database
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            if (connection != null) {
                System.out.println("Connection to the database is successful!");
                insertBus(connection, "Mercedes-Benz Sprinter", "AB123CD", 20);
                insertPassenger(connection, "John Doe", 30, 10);
                readData(connection);
                updateBusCapacity(connection, 10, 30);
                deletePassenger(connection, 11);
                deleteBus(connection, 10);

            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    // Create a bus
    public static void insertBus(Connection connection, String model, String registrationNumber, int capacity) {
        String sql = "INSERT INTO Bus (model, registration_number, capacity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model);
            statement.setString(2, registrationNumber);
            statement.setInt(3, capacity);
            statement.executeUpdate();
            System.out.println("Bus inserted successfully!");
        } catch (SQLException e) {
            System.out.println("Error inserting bus.");
            e.printStackTrace();
        }
    }

    // Create a passenger
    public static void insertPassenger(Connection connection, String name, int age, int busId) {
        String sql = "INSERT INTO Passenger (name, age, bus_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setInt(3, busId);
            statement.executeUpdate();
            System.out.println("Passenger inserted successfully!");
        } catch (SQLException e) {
            System.out.println("Error inserting passenger.");
            e.printStackTrace();
        }
    }

    // Read buses and passengers
    public static void readData(Connection connection) {
        String sql = "SELECT b.model, b.registration_number, b.capacity, p.name, p.age " +
                "FROM Bus b LEFT JOIN Passenger p ON b.id = p.bus_id";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String model = resultSet.getString("model");
                String registrationNumber = resultSet.getString("registration_number");
                int capacity = resultSet.getInt("capacity");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                System.out.println("Bus Model: " + model + ", Registration: " + registrationNumber + ", Capacity: " + capacity);
                if (name != null) {
                    System.out.println("  Passenger: " + name + ", Age: " + age);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error reading data.");
            e.printStackTrace();
        }
    }

    // Update bus capacity
    public static void updateBusCapacity(Connection connection, int busId, int newCapacity) {
        String sql = "UPDATE Bus SET capacity = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newCapacity);
            statement.setInt(2, busId);
            statement.executeUpdate();
            System.out.println("Bus capacity updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating bus capacity.");
            e.printStackTrace();
        }
    }

    // Delete a passenger
    public static void deletePassenger(Connection connection, int passengerId) {
        String sql = "DELETE FROM Passenger WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, passengerId);
            statement.executeUpdate();
            System.out.println("Passenger deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting passenger.");
            e.printStackTrace();
        }
    }
    public static void deleteBus(Connection connection, int busId) {
        String sql = "DELETE FROM Bus WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, busId);
            statement.executeUpdate();
            System.out.println("Bus deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting bus.");
            e.printStackTrace();
        }
    }
}

