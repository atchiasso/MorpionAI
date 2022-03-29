import java.io.IOException;
import java.util.Scanner;

public class Joueur {

    private String symbol;

    private boolean isAI;

    public Joueur(boolean isAI, String symbol) {
        this.isAI = isAI;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public boolean isAI() {
        return this.isAI;
    }

    public boolean humanPlay(PlateauDeJeu plateau, Scanner in) {
        try {
            System.out.println("Choissisez vos coordonnées (ex: 1,2) : \n");
            String symbolJoueur = in.next();
            String[] tokens = symbolJoueur.split(",");
            while(tokens.length != 2) {
                System.out.println("fait un effort... (EXEMPLEEEEEE : 1,2) : \n");
                symbolJoueur = in.next();
                tokens = symbolJoueur.split(",");
            }
            while(!plateau.addSymbol(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]), this.symbol)) {
                System.out.println("Choissisez vos coordonnées (ex: 1,2) : \n");
                symbolJoueur = in.next();
                tokens = symbolJoueur.split(",");
            }

            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            if(plateau.win(x,y)) {
                System.out.println("Tu as gagné la partie");
                System.exit(0);
            }
            if(plateau.full()) {
                System.out.println("MATCH NUUUUUUUL");
                System.exit(0);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean aiPlay(PlateauDeJeu plateau, RemoteCommand commandAI) throws IOException {
        try {
            if(commandAI.sentPlateauToAI(plateau)) {
                System.out.println("L'IA joue...\n");
                //symbolJoueur = in.next();
                String symbolJoueur = commandAI.receiptDataFromAI();
                String[] tokens = symbolJoueur.split(",");
                while(tokens.length != 2) {
                    System.out.println("fait un effort... (EXEMPLEEEEEE : 1,2) : \n");
                    //symbolJoueur = in.next();
                    symbolJoueur = commandAI.receiptDataFromAI();
                    tokens = symbolJoueur.split(",");
                }
                while(!plateau.addSymbol(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]), this.symbol)) {
                    System.out.println("L'IA joue...\n");
                    //symbolJoueur = in.next();
                    symbolJoueur = commandAI.receiptDataFromAI();
                    tokens = symbolJoueur.split(",");
                }
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                if(plateau.win(x,y)) {
                    System.out.println("L'IA a gagné la partie");
                    System.exit(0);
                }
                if(plateau.full()) {
                    System.out.println("MATCH NUUUUUUUL");
                    System.exit(0);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

}
