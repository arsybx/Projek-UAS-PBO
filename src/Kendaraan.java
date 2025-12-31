import java.util.Date;

// Superclass Kendaraan
abstract class Kendaraan {
    protected String noPlat; // Atribut plat nomor
    protected String jenis;  // Atribut jenis kendaraan
    protected Date waktuMasuk; // Atribut Date

    // Constructor untuk inisialisasi
    public Kendaraan(String noPlat, String jenis) {
        this.noPlat = noPlat;
        this.jenis = jenis;
        this.waktuMasuk = new Date();
    }

    // Method abstract agar diimplementasikan berbeda oleh sub-class
    public abstract double getTarif();
    
    // Getter untuk akses data
    public String getNoPlat() { return noPlat; }
    public String getJenis() { return jenis; }
}