package ca.ubc.cpsc210.paddleball.model;

import java.awt.event.KeyEvent;
import java.util.Random;

/*
 * Represents a paddle ball game.
 */
public class PBG {
    public static final int DIMENSION1 = 800;
    public static final int DIMENSION2 = 600;
    private static final Random RND = new Random();

    private Ball ball;
    private Puddle puddle;
    private boolean stop;

    // Constructs a Paddle Ball Game
    // EFFECTS:  creates ball at random location at top of screen
    public PBG() {
        setUp();
    }

    public Puddle getPaddle() {
        return puddle;
    }

    public Ball getBall() {
        return ball;
    }

    // Is game over?
    // EFFECTS: returns true if game is over, false otherwise
    public boolean isOver() {
        return stop;
    }

    // Updates the game on clock tick
    // MODIFIES: this
    // EFFECTS:  updates ball, paddle and game over status
    public void update() {
        ball.move();
        puddle.move();

        checkHitSomething();
        checkStyle();
    }

    // Responds to key press codes
    // MODIFIES: this
    // EFFECTS:  turns paddle and resets game (if game over) in response to
    //           given key pressed code
    public void keyPressed(int kcode) {
        if (kcode == KeyEvent.VK_R && stop) {
            setUp();
        } else if (kcode == KeyEvent.VK_X) {
            System.exit(0);
        } else {
            paddleControl(kcode);
        }
    }

    // Sets / resets the game
    // MODIFIES: this
    // EFFECTS: initializes game with paddle in centre of screen and ball
    // at random horizontal coordinate at top of screen
    private void setUp() {
        ball = new Ball(RND.nextInt(PBG.DIMENSION1), Ball.SIZE / 2);
        puddle = new Puddle(DIMENSION1 / 2);
        stop = false;
    }

    // Controls the paddle
    // MODIFIES: this
    // EFFECTS: changes direction of paddle in response to key code
    private void paddleControl(int kcode) {
        if (kcode == KeyEvent.VK_KP_LEFT || kcode == KeyEvent.VK_LEFT) {
            puddle.left();
        } else if (kcode == KeyEvent.VK_KP_RIGHT || kcode == KeyEvent.VK_RIGHT) {
            puddle.right();
        }
    }

    // Checks for collision between ball and paddle
    // MODIFIES: this
    // EFFECTS:  bounces ball if it collides with paddle
    private void checkHitSomething() {
        if (ball.doSomething(puddle)) {
            ball.bounceOffPaddle();
        }
    }

    // Is game over? (Has ball hit ground?)
    // MODIFIES: this
    // EFFECTS:  if ball has hit ground, game is marked as over
    private void checkStyle() {
        if (ball.getY() > DIMENSION2) {
            stop = true;
        }
    }
}
