import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Membuat objek dari class ManajemenParkir untuk mengakses fitur-fiturnya
        ManajemenParkir app = new ManajemenParkir(); 
        
        // Membuat Scanner khusus untuk menerima input menu dari pengguna
        Scanner mainScanner = new Scanner(System.in); 
        int menu = 0;

        // Mengecek status koneksi database sebelum aplikasi dimulai
        KoneksiDB.checkConnection();

        // Loop 'while' agar aplikasi terus berjalan (kembali ke menu) sampai user memilih angka 5
        while (menu != 5) {
            System.out.println("\n===== MANAJEMEN PARKIR =====");
            System.out.println("1. Kendaraan Masuk");
            System.out.println("2. Kendaraan Keluar");
            System.out.println("3. Lihat Daftar Kendaraan");
            System.out.println("4. Hapus Data");
            System.out.println("5. Keluar");
            System.out.print("Pilih Menu: ");
            
            try {
                // Membaca input sebagai String lalu diubah ke Integer
                String input = mainScanner.nextLine();
                menu = Integer.parseInt(input);

                // Menggunakan Switch-Case untuk memanggil method sesuai pilihan user
                switch (menu) {
                    case 1: 
                        app.kendaraanMasuk(); // Memanggil fitur masuk
                        break;
                    case 2: 
                        app.kendaraanKeluar(); // Memanggil fitur keluar/bayar
                        break;
                    case 3: 
                        app.tampilkanDaftarKendaraan(); // Menampilkan histori
                        break;
                    case 4: 
                        app.hapusDataParkir(); // Menghapus data transaksi
                        break;
                    case 5: 
                        System.out.println("Terima kasih, sistem berhenti."); 
                        
                        // Membersihkan memori dengan menutup scanner sebelum program berakhir
                        app.scanner.close();
                        mainScanner.close();
                        break;
                    default: 
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (NumberFormatException e) {
                // Menangani error jika user tidak sengaja memasukkan huruf/simbol
                System.out.println("Input harus berupa angka!");
            }
        }
    }
}