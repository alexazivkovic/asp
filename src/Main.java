import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    Stablo stablo = new Stablo();
        stablo.ucitavanje("stekovi.txt");
        stablo.ispis();
    }
}
