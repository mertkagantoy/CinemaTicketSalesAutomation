import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DatabaseAdapter sınıfı, veritabanı bağlantılarını yönetir ve Singleton olarak tasarlanmıştır.
public class DatabaseAdapter {
    private static final String URL = "jdbc:postgresql://localhost:5432/CinemaManagement";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345";

    // Singleton örneği tutmak için kullanılır.
    private static DatabaseAdapter instance;
    // Tek bir bağlantı örneği tutmak için kullanılır.
    private Connection connection;

    // Private constructor: Singleton tasarım kalıbı için gerekli.
    DatabaseAdapter() {
        // Bağlantıyı başlat
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Singleton örneğini dönen yöntem.
    public static DatabaseAdapter getInstance() {
        if (instance == null) {
            instance = new DatabaseAdapter();
        }
        return instance;
    }

    // Veritabanı bağlantısını döndüren yöntem.
    public Connection getConnection() throws SQLException {
        // Bağlantı yoksa veya kapalıysa yeni bir bağlantı oluştur.
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    // Veritabanı bağlantısını kapatan yöntem.
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
