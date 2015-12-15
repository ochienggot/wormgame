package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.gui.Updatable;
import wormgame.domain.Worm;
import wormgame.domain.Apple;
import wormgame.domain.Piece;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Worm worm;
    private Apple apple;
    private Random rand;


    public WormGame(int width, int height) {
        super(1000, null);

        this.width = width;
        this.height = height;
        this.continues = true;

        addActionListener(this);
        setInitialDelay(2000);

        worm = new Worm(width/2, height/2, Direction.DOWN);
        rand = new Random();
        int x = rand.nextInt(this.width);
        int y = rand.nextInt(this.height);
        apple = new Apple(x, y);
        if (worm.runsInto(apple)) {
            x = rand.nextInt(this.width);
            y = rand.nextInt(this.height);
            apple = new Apple(x, y);
        }
    }

    public Worm getWorm() {
	return worm;
    }

    public void setWorm(Worm worm) {
	this.worm = worm;
    }

    public Apple getApple() {
	return apple;
    }

    public void setApple(Apple apple) {
	this.apple = apple;
    }


    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
	    worm.move();

	    while (worm.runsInto(apple)) {
            apple = null;
            worm.grow();
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            apple = new Apple(x, y);
	    }

	    if (worm.runsIntoItself()) {
            continues = false;
        }

        for (Piece p : worm.getPieces()) {
            if (p.getX() >= getWidth() || p.getY() >= getHeight()) {
                continues = false;
            }

            if (p.getX() == 0 || p.getY() == 0) {
                continues = false;
            }
	    }

	    updatable.update();

	    setDelay(1000 / worm.getLength());
    }
}
