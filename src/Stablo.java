import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Stablo {
    private static class Cvor{
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

    private Cvor root = null;
    File file;

    public void ucitavanje(String path) throws FileNotFoundException {
        file = new File(path);
        Scanner in = new Scanner(file);
        ArrayList<String> stekovi = new ArrayList<>();
        while (in.hasNextLine()){
            stekovi.add(in.nextLine());
        }
        root = new Cvor(stekovi.get(0).substring(0, stekovi.get(0).indexOf(" ")));
        for(int i=0; i<stekovi.size(); i++){
            Cvor prethodni = root;
            stekovi.set(i, stekovi.get(i).substring(stekovi.get(i).indexOf(" ")+1));
            while (stekovi.get(i).length()>0){
                if(stekovi.get(i).contains(" ")){
                    Cvor trenutni = new Cvor(stekovi.get(i).substring(0, stekovi.get(i).indexOf(" ")));
                    if(!prethodni.postoji(trenutni)){
                        prethodni.sledeci.add(trenutni);
                        prethodni = trenutni;
                    }
                    else{
                        prethodni = nadji(prethodni, trenutni);
                    }
                    stekovi.set(i, stekovi.get(i).substring(stekovi.get(i).indexOf(" ")+1));
                }
                else {
                    Cvor trenutni = new Cvor(stekovi.get(i));
                    if(!prethodni.postoji(trenutni)){
                        prethodni.sledeci.add(trenutni);
                    }
                    stekovi.set(i, "");
                    prethodni = trenutni;
                }
            }
        }
    }

    private Cvor nadji(Cvor prethodni, Cvor trenutni){
        for (Cvor cvor : prethodni.sledeci) {
            if (cvor.naziv.compareTo(trenutni.naziv) == 0) {
                return cvor;
            }
        }
        return new Cvor(trenutni.naziv);
    }

    public void ispis(){
        Stack<Cvor> cvorovi = new Stack<>();
        cvorovi.push(root);
        while (!cvorovi.isEmpty()) {
            Cvor trenutni = cvorovi.pop();
            if (trenutni != null) {
                System.out.println(trenutni.naziv);
                for(int i=trenutni.sledeci.size()-1; i>=0; i--) {
                    cvorovi.add(trenutni.sledeci.get(i));
                }
            }
        }
    }

    public void dodaj(String stek){
        Cvor prethodni = root;
        stek = stek.substring(stek.indexOf(" ")+1);
        while (stek.length()>0){
            if(stek.contains(" ")){
                Cvor trenutni = new Cvor(stek.substring(0, stek.indexOf(" ")));
                if(!prethodni.postoji(trenutni)){
                    prethodni.sledeci.add(trenutni);
                    prethodni = trenutni;
                }
                else{
                    prethodni = nadji(prethodni, trenutni);
                }
                stek = stek.substring(stek.indexOf(" ")+1);
            }
            else {
                Cvor trenutni = new Cvor(stek);
                if(!prethodni.postoji(trenutni)){
                    prethodni.sledeci.add(trenutni);
                }
                stek = "";
                prethodni = trenutni;
            }
        }
    }

    public void obrisiStablo() {
        if (root != null) {
            root.obrisi();
            root = null;
        }
    }
}

