import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Morpion {

    private static Joueur joueur1 = new Joueur();
    private static Joueur joueur2 = new Joueur();
    private static Scanner in = new Scanner(System.in);
    private static PlateauDeJeu plateau = new PlateauDeJeu();
    private static boolean partyFinish = false;
    private static RemoteCommand commandAI = new RemoteCommand();

    public static void main(String[] args) throws IOException {

        // Tirage au sort
        String[] symbols = {"X" , "O"};
        int randomIndex = (int) Math.round( Math.random());
        //joueur1.setSymbol(symbols[randomIndex]);
        //joueur2.setSymbol(symbols[(randomIndex+1) % 2]);
        joueur1.setSymbol("X");
        joueur2.setSymbol("O");
        System.out.println(symbols[randomIndex]);
        System.out.println(symbols[(randomIndex+1) % 2]);

        commandAI.initializeServer();

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
                commandAI.sentPlateauToAI(plateau);

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

                if(commandAI.sentPlateauToAI(plateau)) {
                    System.out.println("Joueur 2, choissisez vos coordonnées (ex: 1,2) : \n");
                    //symbolJoueur = in.next();
                    symbolJoueur = commandAI.receiptDataFromAI();
                    tokens = symbolJoueur.split(",");
                    while(tokens.length != 2) {
                        System.out.println("fait un effort... (EXEMPLEEEEEE : 1,2) : \n");
                        //symbolJoueur = in.next();
                        symbolJoueur = commandAI.receiptDataFromAI();
                        tokens = symbolJoueur.split(",");
                    }
                    while(!plateau.addSymbol(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]), "O")) {
                        System.out.println("Joueur 2 , choissisez vos coordonnées (ex: 1,2) : \n");
                        //symbolJoueur = in.next();
                        symbolJoueur = commandAI.receiptDataFromAI();
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

            }
            else {
                System.out.println("Joueur 2, choissisez vos coordonnées\n");
            }

        }


    }
}
