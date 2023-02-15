import breakout.BreakoutGame;
import org.junit.jupiter.api.Test;


import java.beans.Transient;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BallTest {

    @Test
    void gameWinTest() {
        BreakoutGame game = new BreakoutGame();
        game.run();
        for (int i = 0; i < 101; i++)
            game.destroyBrick();
        assertTrue(game.winGame());
    }

}


