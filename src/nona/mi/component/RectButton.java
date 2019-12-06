package nona.mi.component;

import nona.mi.main.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class RectButton extends Button {

    private BufferedImage standardImage;
    private BufferedImage focusedImage;
    private int imageX;
    private int imageY;
    private int imageWidth;
    private int imageHeight;
    private boolean focused;
    private int nextScene;


    public RectButton(Game game) {
        super(game);
        focused = false;
    }

    public void setImages(BufferedImage standardImage, BufferedImage focusedImage, int imageX, int imageY, int imageWidth, int imageHeight) {
        this.standardImage = standardImage;
        this.focusedImage = focusedImage;
        this.imageX = imageX;
        this.imageY = imageY;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public void setNextScene(int nextScene) {
        this.nextScene = nextScene;
    }

    @Override
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

    @Override
    public void render(Graphics g) {
        if (showMask) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLUE);
            g2d.fillRect(imageX, imageY, imageWidth, imageHeight);
        }
        g.drawImage(standardImage, imageX, imageY, null);
        if (focused) {
            g.drawImage(focusedImage, imageX, imageY, null);
        }
    }

    @Override
    public boolean isMouseOnButton() {
        int mx = game.getMouseX();
        int my = game.getMouseY();
        return (mx >= imageX && mx <= imageX + imageWidth) && (my >= imageY && my <= imageY + imageHeight);
    }

    @Override
    public void act() {

    }

}
