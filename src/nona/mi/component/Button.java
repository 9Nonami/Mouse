package nona.mi.component;

import nona.mi.main.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Button {

    protected Game game;
    protected boolean showMask;
    protected boolean clicked;
    protected boolean focused;
    protected int nextScene;
    protected BufferedImage standardImage;
    protected BufferedImage focusedImage;
    protected int imageX;
    protected int imageY;


    public Button(Game game, int nextScene) {
        this.game = game;
        this.nextScene = nextScene;
    }

    public void setImages(BufferedImage standardImage, BufferedImage focusedImage, int imageX, int imageY) {
        this.standardImage = standardImage;
        this.focusedImage = focusedImage;
        this.imageX = imageX;
        this.imageY = imageY;
    }

    public void update() {
        if (isMouseOnButton()) {
            focused = true;
            if (game.isMouseClicked()) {
                act();
            }
        } else {
            focused = false;
        }
    }

    public abstract void render(Graphics g);

    protected abstract boolean isMouseOnButton();

    protected void act() {
        clicked = true;
    }

    public void setShowMask(boolean showMask) {
        this.showMask = showMask;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void reset() {
        clicked = false;
    }

    public int getNextScene() {
        return nextScene;
    }

}
