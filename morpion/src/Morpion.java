import java.util.Scanner;

public class Morpion {

    static Joueur joueur1 = new Joueur();
    static Joueur joueur2 = new Joueur();
    static Scanner in = new Scanner(System.in);
    static PlateauDeJeu plateau = new PlateauDeJeu();
    static boolean partyFinish = false;

    public static void main(String[] args) {

        // Tirage au sort
        String[] symbols = {"X" , "O"};
        int randomIndex = (int) Math.round( Math.random());
        //joueur1.setSymbol(symbols[randomIndex]);
        //joueur2.setSymbol(symbols[(randomIndex+1) % 2]);
        joueur1.setSymbol("X");
        joueur2.setSymbol("O");
        System.out.println(symbols[randomIndex]);
        System.out.println(symbols[(randomIndex+1) % 2]);

        while(!partyFinish) {
            if(joueur1.getSymbol().equalsIgnoreCase("X")) {
                System.out.println("Joueur 1, choissisez vos coordonnées (ex: 1,2) : \n");
                String symbolJoueur = in.next();
                String[] tokens = symbolJoueur.split(",");
                while(tokens.length != 2) {
                    System.out.println("fait un effort... (EXEMPLEEEEEE : 1,2) : \n");
                    symbolJoueur = in.next();
                    tokens = symbolJoueur.split(",");
                }
                while(!plateau.addSymbol(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]), "X")) {
                    System.out.println("Joueur 1, choissisez vos coordonnées (ex: 1,2) : \n");
                    symbolJoueur = in.next();
                    tokens = symbolJoueur.split(",");
                }
                //Verifier si le joueur1 a gagné ou si le plateau est plein
                System.out.println(plateau.toString());
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                if(plateau.win(x,y)) {
                    System.out.println("Le Joueur 1 a gagné la partie");
                    break;
                }
                if(plateau.full()) {
                    System.out.println("MATCH NUUUUUUUL");
                    break;
                }

                System.out.println("Joueur 2, choissisez vos coordonnées (ex: 1,2) : \n");
                symbolJoueur = in.next();
                tokens = symbolJoueur.split(",");
                while(tokens.length != 2) {
                    System.out.println("fait un effort... (EXEMPLEEEEEE : 1,2) : \n");
                    symbolJoueur = in.next();
                    tokens = symbolJoueur.split(",");
                }
                while(!plateau.addSymbol(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]), "O")) {
                    System.out.println("Joueur 2 , choissisez vos coordonnées (ex: 1,2) : \n");
                    symbolJoueur = in.next();
                    tokens = symbolJoueur.split(",");
                }
                //Verifier si le joueur2 a gagné ou si le plateau est plein
                System.out.println(plateau);
                x = Integer.parseInt(tokens[0]);
                y = Integer.parseInt(tokens[1]);
                if(plateau.win(x,y)) {
                    System.out.println("Le Joueur 2 a gagné la partie");
                    break;
                }
                if(plateau.full()) {
                    System.out.println("MATCH NUUUUUUUL");
                    break;
                }
            }
            else {
                System.out.println("Joueur 2, choissisez vos coordonnées\n");
            }

        }


    }
}
