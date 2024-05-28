import java.sql.SQLException;
import java.util.List;


//SinemaService sınıfı, iş mantığını ve veritabanı işlemlerini yönetir. Filmlerin ve
// seansların listelenmesi ile bilet satın alma işlemlerini gerçekleştirir.
public class SinemaService {
    private FilmDAO filmDAO;
    private SeansDAO seansDAO;

    // Yapıcı metot, SinemaService nesnesi oluşturur ve DAO nesnelerini başlatır.
    public SinemaService(DatabaseAdapter dbAdapter) {
        this.filmDAO = new FilmDAO(dbAdapter);
        this.seansDAO = new SeansDAO(dbAdapter);
    }
    // Tüm filmleri listeleyen yöntem.
    public List<Film> getAllFilms() throws SQLException {
        return filmDAO.getAllFilms();
    }
    // Belirli bir film ID'sine ait seansları listeleyen yöntem.
    public List<Seans> getSeanslarByFilmId(int filmId) throws SQLException {
        return seansDAO.getSeanslarByFilmId(filmId);
    }
    // Belirli bir seans ID'sine bilet satın alma işlemini gerçekleştiren yöntem.
    public boolean buyTickets(int seansId, int numTickets) throws SQLException {
        Seans seans = seansDAO.getSeansById(seansId);
        if (seans.getBiletStogu() >= numTickets) {
            seans.setBiletStogu(seans.getBiletStogu() - numTickets);
            seansDAO.updateBiletStogu(seansId, seans.getBiletStogu());
            return true;
        }
        return false;
    }
}
