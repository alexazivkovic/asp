import java.util.ArrayList;

public class Cvor {
    String naziv;
    ArrayList<Cvor> sledeci = new ArrayList<>();

    public Cvor(String naziv){
        this.naziv = naziv;
    }

    public boolean postoji(Cvor novi){
        boolean a = false;
        for (Cvor cvor : this.sledeci) {
            if (cvor.naziv.compareTo(novi.naziv) == 0) {
                a = true;
                break;
            }
        }
        return a;
    }

    public void obrisi() {
        for (Cvor child : sledeci) {
            child.obrisi();
        }
        sledeci.clear();
    }
}
