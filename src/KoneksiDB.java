import java.lang.reflect.Method;
import java.sql.*; // Mengimpor seluruh library JDBC untuk menangani koneksi database

public class KoneksiDB {
//Koneksi Database db_perpustakaan
private static final String URL = "jdbc:mysql://localhost:3306/database_kendaraanparkir";
private static final String USER = "root";
private static final String PASSWORD = "";


// Blok Static Initialization
static {
  try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
    System.out.println("MySQL JDBC Driver not found in classpath: " +
    e.getMessage());
  }
}

// Method getConnection, Bertugas membuat dan mengembalikan objek Connection aktif
public static Connection getConnection() throws SQLException {
  return DriverManager.getConnection(URL, USER, PASSWORD);
}

//Method checkConnection, Method utilitas sederhana untuk menguji apakah koneksi ke database berhasil atau gagal.
public static void checkConnection() {
  try (Connection conn = getConnection()) {
     System.out.println("Koneksi MySQL berhasil.");
  } catch (SQLException e) {
     System.out.println("Koneksi ke database gagal: " + e.getMessage());
  }
}

}
