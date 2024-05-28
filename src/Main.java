

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
// Kullanıcı etkileşimini yöneten sınıf
public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseAdapter dbAdapter = new DatabaseAdapter();
        SinemaService sinemaService = new SinemaService(dbAdapter);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            // Tüm filmleri listele.
            System.out.println("Tüm Filmler:");
            List<Film> filmler = sinemaService.getAllFilms();
            for (Film film : filmler) {
                System.out.println(film.getFilmId() + ": " + film.getAd());
            }
            // Kullanıcıdan film ID'si al.
            System.out.print("Film ID'si seçin: ");
            int filmId = scanner.nextInt();

            // Seansları listele.
            System.out.println("Seanslar:");
            List<Seans> seanslar = sinemaService.getSeanslarByFilmId(filmId);
            if (seanslar != null && !seanslar.isEmpty()) {
                for (Seans seans : seanslar) {
                    System.out.println(seans.getSeansId() + ": " + seans.getSeansZamani());
                }
                // Kullanıcıdan seans ID'si ve bilet sayısı al.
                System.out.print("Seans ID'si seçin: ");
                int seansId = scanner.nextInt();

                System.out.print("Bilet sayısı girin: ");
                int numTickets = scanner.nextInt();

                // Bilet satın alma işlemini gerçekleştir.
                if (sinemaService.buyTickets(seansId, numTickets)) {
                    System.out.println("Bilet satın alındı!");
                } else {
                    System.out.println("Yeterli bilet yok!");
                }
            } else {
                System.out.println("Seans bulunamadı.");
            }
        }

    }
}
