import static org.junit.jupiter.api.Assertions.*;

import breakout.Ball;
import breakout.Paddle;
import breakout.BreakoutGame;
import org.junit.jupiter.api.Test;
import edu.macalester.graphics.CanvasWindow;
import org.mockito.Mockito;

import java.beans.Transient;
import java.awt.Paint;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class BreakoutGameTest {
    BreakoutGame mockGame = mock(BreakoutGame.class);
    Paddle mockPaddle = mock(Paddle.class);
    CanvasWindow mockCanvas = mock(CanvasWindow.class);

    @Test
    void mockitoBall(){
//        when(mockGame.getCanvas()).thenReturn(mockCanvas);
//        Ball ball = new breakout.Ball(300, 10, 200, 490, mockGame.getCanvas());
//        when(ball.hitPaddle(mockGame.getCanvas(),mockPaddle)).thenReturn(true);
////        Mockito.verify(ball).hitPaddle(mockGame.getCanvas(),mockPaddle);
//        assertEquals(ball.hitPaddle(mockGame.getCanvas(),mockPaddle),true);

        Ball ball = new breakout.Ball(300, 10, 200, 490, mockCanvas);
        Paddle paddle = new breakout.Paddle(200.0, 500.01);
        when(mockCanvas.getElementAt(200.0, 500.01 )).thenReturn(paddle);
        assertEquals(ball.hitPaddle(mockCanvas,paddle),true);

    }

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

    @Test
    void ballPaddleColorChangeTest() {
        BreakoutGame game = new BreakoutGame();
        game.run();
        Paint changedBallColor = game.getBall().getColor();
        System.out.println("Ball color: " + game.getBall().getColor());

        Paint actualBallColor = game.ballHitPaddle();
        System.out.println("Actual Changed Ball Color: " + actualBallColor);

        assertNotEquals(changedBallColor, actualBallColor);
    }
}