import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    public ArrayList<Character> lettersFound;
    public int numGuesses;
    public boolean gameOver;
    public boolean gameWon;
    @Serial
    private static final long serialVersionUID = 1234567L;
}
