public class Motor extends Kendaraan {
  //Constructor
  public Motor(String noPlat){
    super(noPlat,  "Motor"); // Memanggil constructor superclass
  }

  // override methode getTarif
  @Override
    public double getTarif() { return 2000.0; } // Tarif khusus motor (Poin 2d: Math)
}
