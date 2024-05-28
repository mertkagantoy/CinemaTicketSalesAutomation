import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// SeansDAO sınıfı, seans veritabanı işlemlerini yönetir.
public class SeansDAO {
    private DatabaseAdapter dbAdapter;

    // Yapıcı metot, SeansDAO nesnesi oluşturur ve DatabaseAdapter'ı kullanır
    public SeansDAO(DatabaseAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }
    // Belirli bir film ID'sine göre ait seansları veritabanından çeken yöntem.
    public List<Seans> getSeanslarByFilmId(int filmId) throws SQLException {
        Connection connection = dbAdapter.getConnection();
        String query = "SELECT * FROM seanslar WHERE film_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, filmId);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Seans> seanslar = new ArrayList<>();
        while (resultSet.next()) {
            Seans seans = new Seans(
                    resultSet.getInt("seans_id"),
                    resultSet.getInt("film_id"),
                    resultSet.getTime("seans_zamani").toLocalTime(),
                    resultSet.getInt("bilet_stogu")
            );
            seanslar.add(seans);
        }

        dbAdapter.closeConnection();
        return seanslar;
    }
    // Belirli bir seans ID'sine ait seansı veritabanından çeken yöntem.
    public Seans getSeansById(int seansId) throws SQLException {
        Connection connection = dbAdapter.getConnection();
        String query = "SELECT * FROM seanslar WHERE seans_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, seansId);
        ResultSet resultSet = preparedStatement.executeQuery();

        Seans seans = null;
        if (resultSet.next()) {
            seans = new Seans(
                    resultSet.getInt("seans_id"),
                    resultSet.getInt("film_id"),
                    resultSet.getTime("seans_zamani").toLocalTime(),
                    resultSet.getInt("bilet_stogu")
            );
        }

        dbAdapter.closeConnection();
        return seans;
    }
    // Seansın bilet stokunu güncelleyen yöntem.
    public void updateBiletStogu(int seansId, int newStok) throws SQLException {
        Connection connection = dbAdapter.getConnection();
        String query = "UPDATE seanslar SET bilet_stogu = ? WHERE seans_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, newStok);
        preparedStatement.setInt(2, seansId);
        preparedStatement.executeUpdate();
        dbAdapter.closeConnection();
    }
}
