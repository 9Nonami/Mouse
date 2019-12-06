package nona.mi.component;

import nona.mi.main.Game;

import java.awt.Color;
import java.awt.Graphics;

public class Component {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Game game;
    private boolean showRectangle;

    public Component(int x, int y, int width, int height, Game game) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.game = game;
    }

    public void update() {

    }

    public void render(Graphics g) {
        if (showRectangle) {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
        }
    }

    public boolean isOnComponent() {
        int mx = game.getMouseX();
        int my = game.getMouseY();
        return (mx >= x && mx <= x + width) && (my >= y && my <= y + height);
    }

    public boolean isMouseClicked() {
        return game.isMouseClicked();
    }

    public void act() {

    }

    public void setShowRectangle(boolean showRectangle) {
        this.showRectangle = showRectangle;
    }
}
