import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class Testing {
    @Test
    @DisplayName("STUDENT TEST CASE - Win Condition")
    public void firstCaseTest() {
        AbstractStrategyGame g = new ConnectFour();
        //assertEquals(ConnectFour.PLAYER_1, g.getNextPlayer(), "Player 1 not next player after construction");
        //assertEquals(ConnectFour.GAME_NOT_OVER, g.getWinner(), "Winner incorrectly declared after construction");
        //assertFalse(g.isGameOver(), "Game over immediately after construction");
        g.makeMove("1");
        g.makeMove("1");
        g.makeMove("2");
        g.makeMove("2");
        g.makeMove("3");
        g.makeMove("3");
        g.makeMove("4");
        assertEquals(1, g.getWinner());

    }

    @Test
    @DisplayName("STUDENT TEST CASE - Illegal Move")
    public void secondCaseTest() {
        AbstractStrategyGame g = new ConnectFour();
        g.makeMove("1");
        g.makeMove("1");
        g.makeMove("1");
        g.makeMove("1");
        g.makeMove("1");
        g.makeMove("1");
        assertThrows(IllegalArgumentException.class, () -> g.makeMove("1"));
        assertThrows(IllegalArgumentException.class, () -> g.makeMove("-1"));

        /*assertThrows(IllegalArgumentException.class, () -> {
            // 0 is an illegal move so our code should throw an IllegalArgumentException
            g.makeMove("1");
        }, "IllegalArgumentException not thrown for illegal move");*/
    }
}
