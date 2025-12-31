public class Mobil extends Kendaraan {
  //Constructor
  public Mobil(String noPlat) {
        super(noPlat, "Mobil"); // Memanggil constructor superclass
    }
  
  // Override Method Gettarif
  @Override
    public double getTarif() { return 5000.0; } // Tarif khusus mobil (Poin 2d: Math)
  
}
