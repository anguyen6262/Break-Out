import static org.junit.jupiter.api.Assertions.*;
import breakout.BreakoutGame;
import org.junit.jupiter.api.Test;
import java.beans.Transient;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BreakoutGameTest {

    @Test
    void loseGame() {
        BreakoutGame game = new BreakoutGame();
        game.run();
        game.setLives(2);
        assertFalse(game.loseGame());
        game.setLives(-1);
        assertTrue(game.loseGame());
    }

    @Test
    void livesDisplayLocation() {
        BreakoutGame game = new BreakoutGame();
        game.run();

        assertEquals(275,game.getLivesDisplay().getX());
        assertEquals(600,game.getLivesDisplay().getY());
    }

    @Test
    void displayLoseLife(){
        BreakoutGame game = new BreakoutGame();
        game.run();
        assertEquals("Lives: 3",game.getLivesDisplay().getText());
        game.loselife();
        assertEquals("Lives: 2",game.getLivesDisplay().getText());
    }

    @Test
    void loseGameLives() {
        BreakoutGame game = new BreakoutGame();
        game.run();
        game.setLives(0);
        assertTrue(game.loseGame());
        assertNull(game.getCanvas().getElementAt(game.getLivesDisplay().getX(),game.getLivesDisplay().getY() ));
    }
}