package breakout;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.CanvasWindow;
import java.awt.Color;
import java.lang.Math;

/**
 * represents a ball that can bounce off of bricks, paddle, sides of the canvas window, and top 
 * of the canvas window.
 */
public class Ball {
    private double centerX;
    private double centerY;
    private Ellipse ball;
    private double velocityX;
    private double velocityY;
    private static final double RADIUS = 10.0;
    private int bricksLeft = 100;
    
    /**
     * Constructs a ball centered on the centerX/Y position with the specified radius.
     */
    public Ball(double initialAngle,
                double initialSpeed,
                double centerX, 
                double centerY, 
                CanvasWindow canvas 
                ) {

        ball = new Ellipse(0, 0, RADIUS * 2, RADIUS * 2);
        this.centerX = centerX;
        this.centerY = centerY;
        ball.setFillColor(Color.BLACK);
        ball.setCenter(centerX,centerY);
        canvas.add(ball);
        double initialAngleRadians = Math.toRadians(initialAngle);
        velocityX = initialSpeed * Math.cos(initialAngleRadians);
        velocityY = initialSpeed * -Math.sin(initialAngleRadians);
        
    }

    /**
     * Changes the ball's velocity in the x direction if the ball hits either side of the canvas window.
     * @return true if the ball hits either side of the canvas window.
     */
    public boolean sideCollision() {
    double leftX = centerX - RADIUS;
    double rightX = centerX + RADIUS;
        if (leftX<=0 || rightX>=600) {
            velocityX=-velocityX;
            return true;
        }
    return false;
    }

    /**
     * Changes the ball's velocity in the y direction if the ball hits the top of the canvas window.
     * @return true if the ball hits the top of the canvas window.
     */
    public boolean topCollision() {
    double topY = centerY - RADIUS;
        if (topY<=0) {
            velocityY=-velocityY;
            return true;
        }     
    return false;
    }

    /**
     * Update the ball's position if it is in bounds
     * @return true if the ball is in within the width and height of the canvas window.
     */
    public boolean move(double dt) { 
        double newX = centerX + velocityX *dt;
        double newY = centerY + velocityY *dt;
        
        if (newX > 0 && newX < 600 && newY > 0 && newY < 800){
            centerX = newX ;
            centerY = newY;
            ball.setCenter(centerX,centerY);
            return true;   
        }
        else {
        return false;
        }
    }

    /**
     * Changes the ball's velocity in the y direction if the ball hits paddle.
     * @return true if the ball hits paddle.
     */
    public void hitPaddle(CanvasWindow canvas, Paddle paddle){
        double bottomY = centerY + RADIUS;
        if (paddle.equals(canvas.getElementAt(ball.getCenter().getX(),bottomY + 0.01))) {
            velocityY=-velocityY;
        }
    }

    /**
     * Changes the ball's velocity in the y direction if the top point or bottom point of the ball hits
     * a brick. It also changes ball's velocity in the x direction if the left point or right point of 
     * the ball hits a brick. When a brick is hit, the brick is removed from the canvas and the variable
     * bricksLeft decreases by 1.
     */
    public void hitBrick(CanvasWindow canvas, BrickManager brick){
        double bottomY = centerY + RADIUS;
        double topY = centerY - RADIUS;
        double leftX = centerX - RADIUS;
        double rightX = centerX + RADIUS;
    
        GraphicsObject southElement = canvas.getElementAt(ball.getCenter().getX(), bottomY + 0.01);
        GraphicsObject northElement = canvas.getElementAt(ball.getCenter().getX(), topY - 0.01);
        GraphicsObject westElement = canvas.getElementAt(leftX - 0.01, ball.getCenter().getY());
        GraphicsObject eastElement = canvas.getElementAt(rightX + 0.01, ball.getCenter().getY());
        
        if (southElement instanceof Brick){
            canvas.remove((Brick)southElement);
            BreakoutGame.destroyBrick();
            velocityY=-velocityY;
        }
        
        if (northElement instanceof Brick){
            canvas.remove((Brick)northElement);
            BreakoutGame.destroyBrick();
            velocityY=-velocityY;
        }

        if (westElement instanceof Brick){
            canvas.remove((Brick)westElement);
            BreakoutGame.destroyBrick();
            velocityX=-velocityX;
        }

        if (eastElement instanceof Brick){
            canvas.remove((Brick)eastElement);
            BreakoutGame.destroyBrick();
            velocityX=-velocityX;
        }

    }

    /**
     * Checks to see if the ball touches the bottom of the canvas.
     * @return true if the ball touches the bottom of the canvas.
     */
    public boolean Reset() {
        double bottomY = centerY + RADIUS;
        if (bottomY > 700){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Removes the ball from the given canvas.
     */
    public void removeFromCanvas(CanvasWindow canvas){
        canvas.remove(ball);
    }

    /**
     * Returns the total number of bricks left in canvas.
     */


}
