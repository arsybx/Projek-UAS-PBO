import java.sql.*;
import java.util.*;
import java.util.Date; // Impor khusus untuk menangani tipe data tanggal Java

//Class ManajemenParkir mengimplementasikan interface OperasiParkir.
public class ManajemenParkir implements OperasiParkir {
    
    // Objek Scanner untuk menerima input dari terminal
    public Scanner scanner = new Scanner(System.in);
    
    // List untuk menyimpan histori plat nomor (Collection Framework)
    ArrayList<String> historiPlat = new ArrayList<>();

    // Method untuk menangani kendaraan yang masuk
    @Override
    public void kendaraanMasuk() {
        try (Connection conn = KoneksiDB.getConnection()) {
            System.out.print("Masukkan No Plat: ");
            String plat = scanner.nextLine().toUpperCase();

            System.out.println("Pilih Jenis: 1. Mobil | 2. Motor");
            int pilihan = 0;
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input salah, default ke Motor.");
                pilihan = 2;
            }

            // Polimorfisme: Inisialisasi objek berdasarkan pilihan user
            Kendaraan k; 
            if (pilihan == 1) {
                k = new Mobil(plat);
            } else {
                k = new Motor(plat);
            }

            // Query SQL untuk menyimpan data kendaraan ke database
            String sql = "INSERT INTO transaksi_parkir (no_plat, jenis_kendaraan, waktu_masuk) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, k.getNoPlat());
            pstmt.setString(2, k.getJenis());
            // Konversi Date Java ke Timestamp SQL
            pstmt.setTimestamp(3, new Timestamp(k.waktuMasuk.getTime())); 
            pstmt.executeUpdate();

            System.out.println("Kendaraan " + plat + " berhasil masuk.");
            historiPlat.add(plat);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method untuk menangani proses keluar dan pembayaran
    @Override
    public void kendaraanKeluar() {
        try (Connection conn = KoneksiDB.getConnection()) {
            System.out.print("Masukkan No Plat Keluar: ");
            String plat = scanner.nextLine().toUpperCase();

            // Mencari data kendaraan yang belum memiliki waktu keluar (masih di dalam)
            String sql = "SELECT * FROM transaksi_parkir WHERE no_plat = ? AND waktu_keluar IS NULL";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, plat);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int idTransaksi = rs.getInt("id_transaksi"); 
                String jenis = rs.getString("jenis_kendaraan");
                Timestamp masuk = rs.getTimestamp("waktu_masuk");
                Timestamp keluar = new Timestamp(System.currentTimeMillis());

                // Menggunakan polimorfisme untuk mengambil tarif yang sesuai (Mobil/Motor)
                Kendaraan k = jenis.equalsIgnoreCase("Mobil") ? new Mobil(plat) : new Motor(plat);
                
                // Menghitung selisih waktu dalam milidetik dan dikonversi ke jam
                long selisih = keluar.getTime() - masuk.getTime();
                long jam = (long) Math.ceil(selisih / (1000.0 * 60 * 60)); 
                if (jam == 0) jam = 1; // Minimal bayar 1 jam

                double total = jam * k.getTarif();

                // Update data transaksi di database dengan waktu keluar dan total biaya
                String upSql = "UPDATE transaksi_parkir SET waktu_keluar = ?, total_biaya = ? WHERE id_transaksi = ?";
                PreparedStatement upPstmt = conn.prepareStatement(upSql);
                upPstmt.setTimestamp(1, keluar);
                upPstmt.setDouble(2, total);
                upPstmt.setInt(3, idTransaksi); 
                upPstmt.executeUpdate();

                System.out.println("\n--- STRUK PARKIR ---");
                System.out.println("Plat  : " + k.getNoPlat());
                System.out.println("Durasi: " + jam + " Jam");
                System.out.println("Total : Rp" + total);
            } else {
                System.out.println("Data kendaraan tidak ditemukan atau sudah keluar.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method untuk menampilkan semua riwayat transaksi dari database
    @Override
    public void tampilkanDaftarKendaraan() {
        try (Connection conn = KoneksiDB.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transaksi_parkir ORDER BY waktu_masuk DESC");

            System.out.println("\n--- DATA TRANSAKSI PARKIR ---");
            // Menggunakan format printf agar tampilan kolom rapi (tabel)
            System.out.printf("%-5s | %-10s | %-10s | %-20s | %-20s | %-10s\n", 
                "ID", "Plat", "Jenis", "Waktu Masuk", "Waktu Keluar", "Biaya");
            System.out.println("------------------------------------------------------------------------------------------");
            
            while (rs.next()) {
                System.out.printf("%-5d | %-10s | %-10s | %-20s | %-20s | Rp%-10.0f\n",
                    rs.getInt("id_transaksi"),
                    rs.getString("no_plat"),
                    rs.getString("jenis_kendaraan"),
                    rs.getTimestamp("waktu_masuk"),
                    (rs.getTimestamp("waktu_keluar") != null ? rs.getTimestamp("waktu_keluar") : "Sedang Parkir"),
                    rs.getDouble("total_biaya")
                );
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
    }

    // Method untuk menghapus data transaksi berdasarkan ID tertentu
    @Override
    public void hapusDataParkir() {
        try (Connection conn = KoneksiDB.getConnection()) {
            System.out.print("Masukkan ID Transaksi yang akan dihapus: ");
            try {
                int id = Integer.parseInt(scanner.nextLine());
                String sql = "DELETE FROM transaksi_parkir WHERE id_transaksi = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                
                int barisDihapus = pstmt.executeUpdate();
                if (barisDihapus > 0) {
                    System.out.println("Data dengan ID " + id + " berhasil dihapus.");
                } else {
                    System.out.println("ID tidak ditemukan.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input ID harus berupa angka.");
            }
        } catch (Exception e) {
            System.out.println("Gagal menghapus: " + e.getMessage());
        }
    }
}