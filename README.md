# PARKIR-MALL: Sistem Manajemen Parkir

Aplikasi berbasis Java Console (CLI) yang dirancang untuk mengelola kendaraan masuk dan keluar secara otomatis dengan integrasi database MySQL. Proyek ini disusun untuk memenuhi seluruh kriteria penilaian UAS Pemrograman Berorientasi Objek.



## Pemenuhan Persyaratan UAS

| Poin | Kriteria Penilaian | Implementasi dalam Kode |
| :--- | :--- | :--- |
| **a** | Class, Object, Constructor | Class `ManajemenParkir`, objek `Kendaraan`, dan constructor di tiap subclass. |
| **b** | Interface & Implementasi | Interface `OperasiParkir` yang diimplementasikan oleh class `ManajemenParkir`. |
| **c** | Inheritance | Superclass `Kendaraan` dengan subclass `Mobil` dan `Motor`. |
| **d** | Loop, Cabang, Math | Perulangan `while`, percabangan `switch-case`, dan kalkulasi biaya (Durasi * Tarif). |
| **e** | String & Date | Method `toUpperCase()`, `printf()`, serta penggunaan `java.util.Date` dan `Timestamp`. |
| **f** | Exception Handling | Penggunaan `try-catch` pada input user dan koneksi database (SQLException). |
| **g** | Collection Framework | Penggunaan `ArrayList<String>` untuk mencatat histori plat nomor kendaraan. |
| **h** | JDBC & CRUD | Operasi **Create** (Masuk), **Read** (Daftar), **Update** (Keluar), **Delete** (Hapus). |



## Fitur Utama

- **Check-In**: Mencatat plat nomor dan jenis kendaraan (Mobil/Motor).
- **Check-Out**: Menghitung durasi jam (pembulatan ke atas) dan total biaya otomatis.
- **Reporting**: Menampilkan tabel riwayat transaksi dari database.
- **Maintenance**: Menghapus data transaksi lama berdasarkan ID.

## 🗄️ Skema Database

**Database**: `database_kendaraanparkir`
**Tabel**: `transaksi_parkir`

| Kolom | Deskripsi |
| :--- | :--- |
| `id_transaksi` | Primary Key (Auto Increment) |
| `no_plat` | String (Nomor Kendaraan) |
| `jenis_kendaraan` | Mobil / Motor |
| `waktu_masuk` | Timestamp Kedatangan |
| `waktu_keluar` | Timestamp Keluar |
| `total_biaya` | Nilai Rupiah |



## ⌨️ Setup & Jalankan

1. **Database**: Import file SQL atau buat database dengan nama `database_kendaraanparkir`.
2. **Koneksi**: Atur `USER` dan `PASSWORD` database pada file `KoneksiDB.java`.
3. **Library**: Pastikan `mysql-connector-java.jar` sudah masuk dalam library proyek.
4. **Main**: Jalankan file `Main.java`.

## 📂 Struktur File

```text
src/
├── KoneksiDB.java        # Pengaturan JDBC
├── Kendaraan.java        # Superclass (Abstract)
├── Mobil.java            # Subclass
├── Motor.java            # Subclass
├── OperasiParkir.java    # Interface
├── ManajemenParkir.java  # Implementasi CRUD & Logika
└── Main.java             # Menu Utama

Nama: Rangga Arsya Bima

NIM: 2411522030

Matakuliah: Pemrograman Berorientasi Objek