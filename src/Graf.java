import java.util.ArrayList;
import java.util.Stack;

public class Graf {
    private ArrayList<String> cvorovi = new ArrayList<>();
    private boolean[][] matricaSusednosti;

    public Graf(){
        Cvor root = Stablo.getRoot();
        Stack<Cvor> cvoroviS = new Stack<>();
        cvoroviS.push(root);
        while (!cvoroviS.isEmpty()) {
            Cvor trenutni = cvoroviS.pop();
            if (trenutni != null) {
                if(!cvorovi.contains(trenutni.naziv)){
                    cvorovi.add(trenutni.naziv);
                }
                for(int i=trenutni.sledeci.size()-1; i>=0; i--) {
                    cvoroviS.add(trenutni.sledeci.get(i));
                }
            }
        }
        matricaSusednosti = new boolean[cvorovi.size()][cvorovi.size()];
        cvoroviS.push(root);
        while (!cvoroviS.isEmpty()) {
            Cvor trenutni = cvoroviS.pop();
            if (trenutni != null) {
                for(int i=trenutni.sledeci.size()-1; i>=0; i--) {
                    cvoroviS.add(trenutni.sledeci.get(i));
                    matricaSusednosti[cvorovi.indexOf(trenutni.naziv)][cvorovi.indexOf(trenutni.sledeci.get(i).naziv)] = true;
                }
            }
        }
    }

    public void ispis(){
        boolean[] posecen = new boolean[cvorovi.size()];
        dfs(Stablo.getRoot().naziv, posecen);
    }

    private void dfs(String cvor, boolean[] posecen){
        posecen[cvorovi.indexOf(cvor)] = true;
        System.out.print(cvor + " ");
       for(int i=0; i<cvorovi.size(); i++){
           if(matricaSusednosti[cvorovi.indexOf(cvor)][i] && !posecen[i]){
               dfs(cvorovi.get(i), posecen);
           }
       }
    }

    public int rekurzivniPozivi(){
        int br = 0;
        for (int i = 0; i < cvorovi.size(); i++) {
            boolean[] posecen = new boolean[cvorovi.size()];
            br += dfs2(i, i, posecen);
        }
        int dijagonala = 0;
        for(int i=0; i<cvorovi.size(); i++){
            if(matricaSusednosti[i][i])
                dijagonala++;
        }
        return br / 2 + dijagonala;
    }

    private int dfs2(int root, int trnutni, boolean[] posecen) {
        posecen[trnutni] = true;
        int br = 0;
        for (int i = 0; i < cvorovi.size(); i++) {
            if (matricaSusednosti[trnutni][i]) {
                if (!posecen[i]) {
                    br += dfs2(root, i, posecen);
                } else if (i == root) {
                    br++;
                }
            }
        }
        posecen[trnutni] = false;
        return br;
    }
}
