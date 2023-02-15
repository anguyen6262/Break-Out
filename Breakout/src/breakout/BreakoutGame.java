package breakout;


import breakout.Screen;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;

import java.awt.*;

/**
 * The game of Breakout.
 */
public class BreakoutGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    private breakout.BrickManager grid;
    private breakout.BrickManager brickManager;
    private CanvasWindow canvas;
    private breakout.Paddle paddle;
    private breakout.Ball ball;
    private breakout.Screen loseScreen;
    private breakout.Screen winScreen;
    private int lives = 3;
    private static int bricksLeft = 2;

    private int loseTracker = 0;
    private  int winTracker = 0;
    private GraphicsText livesDisplay;

    /**
     * Lets the user move the paddle with a mouse. Also makes the ball move, checks
     * for different
     * elements the call collided with, and checks win/lose conditions.
     */
    public BreakoutGame() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        brickManager = new breakout.BrickManager(canvas);
        livesDisplay = new GraphicsText("Lives: 3",0,0);
        canvas.add(livesDisplay);
        canvas.onMouseMove(event -> paddle.setX(event.getPosition().getX()));
        canvas.animate(event -> {
            ball.hitPaddle(canvas, paddle);
            ball.hitBrick(canvas, brickManager);
            ball.move(1);

            if (ball.topCollision() || ball.sideCollision()) {
                ball.move(1);
            }
        });
    }

    /**
     * Constructs a 10 by 10 grid of bricks.
     */
    public void createGrid() {
        grid = new breakout.BrickManager(canvas);
        grid.generateBrickGrid();
    }

    /**
     * Constructs a paddle at (200,500).
     */
    public void createPaddle() {
        paddle = new breakout.Paddle(200, 500);
        canvas.add(paddle);
    }

    /**
     * Constructs a ball at (250, 300).
     */
    public void createBall() {
        ball = new breakout.Ball(300, 10, 250, 300, canvas);
    }

    /**
     * Adds 100 bricks, a paddle, and ball to the canvas.
     */
    public void run() {
        createPaddle();
        createGrid();
        createBall();
        canvas.animate(() -> {
            if(lives > 0 && loseTracker == 0) {
                loselives();
            }
                loseGame();
            if(bricksLeft <= 0 && winTracker == 0){
                winGame();
                ball.removeFromCanvas(canvas);
            }
        });
    }

    /**
     * Checks to see if the user won the game. The user wins if they hit all the
     * bricks on the canvas.
     * 
     * @return true if the bricksLeft variable becomes 0. The bricksLeft variable
     *         needs to be manually
     *         changed if the total number of bricks on the canvas is not 100.
     */
    public boolean winGame() {
        canvas.remove(paddle);
        winScreen = new Screen("You Win!","Play Again", Color.GREEN);
        bricksLeft = 2;
        winTracker++;

        return true;
    }

    /**
     * Checks to see if the user lost the game. The user loses the game if the ball
     * touches the bottom
     * of the canvas 3 times.
     * 
     * @return true if the lives variable becomes 0. The bricksLeft variable needs to
     *         be manually
     *         changed if the user wants more or less than 3 lives.
     */

    public void loselives() {
        if (ball.Reset() && winTracker == 0) {
            ball.removeFromCanvas(canvas);
            if(lives > 0) {
                createBall();
            }
            canvas.remove(livesDisplay);
            livesDisplay = new GraphicsText("Lives: 2",0,0);
            livesDisplay.setText("Lives: " + lives);
            canvas.add(livesDisplay);
            lives -= 1;
        }
    }

    public boolean loseGame() {
        if (lives < 1) {
            canvas.removeAll();
            loseScreen = new Screen("You Lose","Play Again",Color.RED);
            lives = 3;
            loseTracker++;
            return true;
        }
        return false;
    }

    public static void destroyBrick() {
        bricksLeft -= 1;
    }

    public void setLives(int num){
        lives = num;
    }

    public int getLives(){
        return lives;
    }

    public static void main(String[] args) {
        BreakoutGame game = new BreakoutGame();
        game.run();
    }
}
