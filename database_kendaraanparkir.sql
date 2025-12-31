-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 31 Des 2025 pada 09.35
-- Versi server: 8.0.30
-- Versi PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Basis data: `database_kendaraanparkir`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_parkir`
--

CREATE TABLE `transaksi_parkir` (
  `id_transaksi` int NOT NULL,
  `no_plat` varchar(15) NOT NULL,
  `jenis_kendaraan` varchar(10) NOT NULL,
  `waktu_masuk` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `waktu_keluar` datetime DEFAULT NULL,
  `total_biaya` decimal(10,2) DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `transaksi_parkir`
--

INSERT INTO `transaksi_parkir` (`id_transaksi`, `no_plat`, `jenis_kendaraan`, `waktu_masuk`, `waktu_keluar`, `total_biaya`) VALUES
(3, 'CD 5678 DC', 'Mobil', '2025-12-24 17:20:11', '2025-12-24 17:23:27', 5000.00),
(4, 'AB 123 BD', 'Mobil', '2025-12-28 20:38:54', '2025-12-31 10:58:47', 315000.00),
(5, 'EF 567 GF', 'Mobil', '2025-12-31 11:01:04', '2025-12-31 11:20:54', 5000.00),
(6, 'EC 987 DF', 'Motor', '2025-12-31 11:23:28', '2025-12-31 12:00:18', 2000.00),
(7, 'ABC 567 BC', 'Mobil', '2025-12-31 12:28:28', '2025-12-31 12:28:48', 5000.00),
(8, 'ABC 123 BD', 'Mobil', '2025-12-31 13:14:56', '2025-12-31 13:16:00', 5000.00);

--
-- Indeks untuk tabel yang dibuang
--

--
-- Indeks untuk tabel `transaksi_parkir`
--
ALTER TABLE `transaksi_parkir`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `transaksi_parkir`
--
ALTER TABLE `transaksi_parkir`
  MODIFY `id_transaksi` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
