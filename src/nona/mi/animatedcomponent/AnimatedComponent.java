package nona.mi.animatedcomponent;

import nona.mi.main.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class AnimatedComponent {

    private BufferedImage[] images;
    private int x;
    private int y;
    private int width;
    private int height;
    private float speed;
    private float id;
    private boolean animating;
    private Game game;


    public AnimatedComponent(Game game) {
        this.game = game;
        id = 0;
    }

    public void setImages(BufferedImage[] images, int x, int y, float speed) {
        this.images = images;
        this.x = x;
        this.y = y;
        this.speed = speed;
        width = images[0].getWidth();
        height = images[0].getHeight();
    }

    public void update() {
        if (isMouseOnComponent() && isComponentClicked() && !animating) {
            animating = true;
        }
        if (animating) {
            id += speed;
            if (id >= images.length) {
                id = 0;
                animating = false;
            }
        }
    }

    private boolean isMouseOnComponent() {
        int mx = game.getMouseX();
        int my = game.getMouseY();
        return (mx >= x && mx <= x + width) && (my >= y && my <= y + height);
    }

    private boolean isComponentClicked() {
        return game.isMouseClicked();
    }

    public void render(Graphics g) {
        g.drawImage(images[(int)id], x, y, null);
    }

    public void reset() {
        animating = false;
        id = 0;
    }

}
