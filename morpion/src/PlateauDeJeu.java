import java.util.Arrays;

public class PlateauDeJeu {

    private String[][] plateau;
    private final String[] xCorrector = {"X","X","X"};
    private final String[] oCorrector = {"O","O","O"};
    private final String[][] xdCorrector = new String[][]{{"X", " ", " "}, {" ", "X", " "}, {" ", " ", "X"}};
    private final String[][] odCorrector = new String[][]{{"O", " ", " "}, {" ", "O", " "}, {" ", " ", "O"}};
    private final String[][] xd1Corrector = new String[][]{{" ", " ", "X"}, {" ", "X", ""}, {"X", " ", " "}};
    private final String[][] od1Corrector = new String[][]{{" ", " ", "O"}, {" ", "O", ""}, {"O", " ", " "}};

    public PlateauDeJeu() {
        plateau = new String[][]{{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};

    }

    @Override
    public String toString() {
        return Arrays.toString(plateau[0]) +"\n" +Arrays.toString(plateau[1])+"\n" +Arrays.toString(plateau[2]) + "\n";
    }

    public String[][] getPlateau() {
        return plateau;
    }

    public boolean addSymbol(int x, int y, String symbol) {
        if(x > 2 || y > 2 || x < 0 || y < 0) {
            System.out.println("Tu es hors du tableau frère");
            return false;
        }

        if(!plateau[x][y].equalsIgnoreCase(" ")) {
            System.out.println("Cette case est déjà prise frère");
            return false;
        }

        plateau[x][y] = symbol;
        return true;
    }

    public boolean full() {
        for(int i = 0; i <= 2; i++) {
            for(int j = 0; j <= 2; j++) {
                if(plateau[i][j].equalsIgnoreCase(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean win(int x, int y) {
        if(Arrays.equals(plateau[x], xCorrector) || Arrays.equals(plateau[x], oCorrector)) {
            return true;
        }
        if(plateau[0][y].equals(plateau[1][y]) && plateau[1][y].equals(plateau[2][y]))
        {
            return true;
        }
        if(plateau[2][0].equals("X") && plateau[1][1].equals("X") && plateau[0][2].equals("X"))
        {
            return true;
        }
        if(plateau[2][0].equals("O") && plateau[1][1].equals("O") && plateau[0][2].equals("O"))
        {
            return true;
        }

        if(plateau[0][0].equals("X") && plateau[1][1].equals("X") && plateau[2][2].equals("X"))
        {
            return true;
        }
        if(plateau[0][0].equals("O") && plateau[1][1].equals("O") && plateau[2][2].equals("O"))
        {
            return true;
        }
        return false;
    }

}
