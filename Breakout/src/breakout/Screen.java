package breakout;

import breakout.BreakoutGame;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;
import java.awt.Color;

public class Screen {
    private breakout.BreakoutGame game;

    /**
     *
     * @param text The message that is displayed if the user wins or loses the game.
     * @param backgroundColor the color of the screen.
     */
    public Screen(String text, String playText, Color backgroundColor){
        CanvasWindow screen = new CanvasWindow("Breakout", 400, 300);
        screen.setBackground(backgroundColor);

        GraphicsText title = new GraphicsText();
        title.setFont("Arial",FontStyle.PLAIN,30);
        title.setText(text);
        title.setCenter(200,75);
        screen.add(title);

        Button playButton = new Button(playText);
        screen.add(playButton);
        playButton.setCenter(200,150);
        playButton.onClick(()->{
            screen.closeWindow();
            game = new BreakoutGame();
            game.run();
        });

        Button quitButton = new Button("Quit");
        screen.add(quitButton);
        quitButton.setCenter(200,200);
        quitButton.onClick(()->screen.closeWindow());
    }
}
