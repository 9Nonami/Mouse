package nona.mi.component;

import nona.mi.main.Game;

import java.awt.Graphics;

public abstract class Button {

    protected Game game;
    protected boolean showMask;

    public Button(Game game) {
        this.game = game;
        showMask = false;
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract boolean isMouseOnButton();

    public abstract void act();

    public void setShowMask(boolean showMask) {
        this.showMask = showMask;
    }

}
