import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    Stablo stablo = new Stablo();
        stablo.ucitavanje("stekovi.txt");
        stablo.ispis();

        System.out.println();

        Graf graf = new Graf();
        graf.ispis();

        System.out.println();
        System.out.println();

        System.out.println("Broj rekurzivnih poziva je: " + graf.rekurzivniPozivi());
    }
}
