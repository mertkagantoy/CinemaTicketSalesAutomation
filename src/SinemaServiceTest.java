import java.sql.SQLException;
import java.util.List;

public class SinemaServiceTest {

    public static void main(String[] args) {
        try {
            testGetAllFilms();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testGetAllFilms() throws SQLException {
        // Veritabanı bağlantı adapter'ini oluştur
        DatabaseAdapter dbAdapter = new DatabaseAdapter();

        // Sinema servisini oluştur
        SinemaService sinemaService = new SinemaService(dbAdapter);

        // Tüm filmleri al
        List<Film> filmler = sinemaService.getAllFilms();

        // Beklenen sonuçları manuel olarak belirle
        String[] expectedFilmNames = {
                "Hababam Sınıfı", "Eşkıya", "Kış Uykusu", "Babam ve Oğlum", "Ayla",
                "Vizontele", "Bir Zamanlar Anadoluda", "Mucize", "Dedemin İnsanları", "Recep İvedik",
                "Selvi Boylum Al Yazmalım", "G.O.R.A.", "Aile Arasında", "Kelebeğin Rüyası", "Yol",
                "Mustang", "Vavien", "Issız Adam", "Sonbahar", "Beynelmilel"
        };

        // Testi yap
        boolean testPassed = true;
        if (filmler.size() != expectedFilmNames.length) {
            System.out.println("Test failed: Farklı sayıda film listelendi.");
            testPassed = false;
        } else {
            for (int i = 0; i < filmler.size(); i++) {
                if (!filmler.get(i).getAd().equals(expectedFilmNames[i])) {
                    System.out.println("Test failed: Beklenen film adı: " + expectedFilmNames[i] + ", Bulunan film adı: " + filmler.get(i).getAd());
                    testPassed = false;
                }
            }
        }

        if (testPassed) {
            System.out.println("Test passed: Tüm filmler doğru bir şekilde listelendi.");
        }

        // Veritabanı bağlantısını kapat
        dbAdapter.closeConnection();
    }
}
