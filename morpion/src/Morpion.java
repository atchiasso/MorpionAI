import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Morpion {

    private static Joueur joueur1 = null;
    private static Joueur joueur2 = null;
    private static Scanner in = new Scanner(System.in);
    private static PlateauDeJeu plateau = new PlateauDeJeu();
    private static boolean partyFinish = false;
    private static RemoteCommand commandAI = new RemoteCommand();

    public static void main(String[] args) throws IOException {

        // Tirage au sort
        String[] symbols = {"X" , "O"};
        int randomIndex = (int) Math.round( Math.random());
        joueur1 = new Joueur(false, symbols[randomIndex]);
        joueur2 = new Joueur(true, symbols[(randomIndex+1) % 2]);
        //joueur1.setSymbol("X");
        //joueur2.setSymbol("O");
        commandAI.initializeServer();

        while(!partyFinish) {
            if(joueur1.getSymbol().equalsIgnoreCase("X")) {
                if(joueur1.isAI()) {
                    joueur1.aiPlay(plateau,commandAI);
                }
                else {
                    joueur1.humanPlay(plateau, in);
                }
                //Verifier si le joueur1 a gagné ou si le plateau est plein
                System.out.println(plateau.toString());

                if(joueur2.isAI()) {
                    joueur2.aiPlay(plateau,commandAI);
                }
                else {
                    joueur2.humanPlay(plateau, in);
                }
                System.out.println(plateau.toString());
            }
            else {
                if(joueur2.isAI()) {
                    joueur2.aiPlay(plateau,commandAI);
                }
                else {
                    joueur2.humanPlay(plateau, in);
                }
                //Verifier si le joueur1 a gagné ou si le plateau est plein
                System.out.println(plateau.toString());

                if(joueur1.isAI()) {
                    joueur1.aiPlay(plateau,commandAI);
                }
                else {
                    joueur1.humanPlay(plateau, in);
                }
                System.out.println(plateau.toString());
            }

        }


    }
}
