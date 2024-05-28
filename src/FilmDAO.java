import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// FilmDAO sınıfı, film veritabanı işlemlerini yönetir.
public class FilmDAO {
    private DatabaseAdapter dbAdapter;

    // Yapıcı metot, FilmDAO nesnesi oluşturur ve DatabaseAdapter'ı kullanır.
    public FilmDAO(DatabaseAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    public List<Film> getAllFilms() throws SQLException {
        Connection connection = dbAdapter.getConnection();
        String query = "SELECT * FROM filmler";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<Film> filmler = new ArrayList<>();
        while (resultSet.next()) {
            Film film = new Film(
                    resultSet.getInt("film_id"),
                    resultSet.getString("ad"),
                    resultSet.getString("yonetmen"),
                    resultSet.getString("tur"),
                    resultSet.getInt("sure")
            );
            filmler.add(film);
        }

        dbAdapter.closeConnection();
        return filmler;
    }
}
