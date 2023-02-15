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
    void displayLoseLife(){
        BreakoutGame game = new BreakoutGame();
        game.run();
        game.setLives(3);
        game.loselives();
        assertEquals(2,game.getLives());
        assertEquals("Lives: 2",livesDisplay().getText());
    }

    @Test
    void loseGameLive() {
        BreakoutGame game = new BreakoutGame();
        game.run();
        game.setLives(0);
        assertTrue(game.loseGame());
        assertNull(livesDisplay);
    }

    @Test
    void livesDisplayLocation() {
        BreakoutGame game = new BreakoutGame();
        game.run();
        assertEquals(250,livesDisplay().getX());
        assertEquals(500,livesDisplay().getY());
    }
}