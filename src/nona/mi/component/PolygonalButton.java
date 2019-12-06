package nona.mi.component;

import nona.mi.main.Game;
import nona.mi.main.MyGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

public class PolygonalButton extends Button {

    private Polygon polygon;
    private BufferedImage standardImage;
    private BufferedImage focusedImage;
    private int imageX;
    private int imageY;
    private boolean focused;
    private final int MOUSE_MASK = 6;


    public PolygonalButton(Game game, int[] x, int[] y) {
        super(game);
        polygon = new Polygon(x, y, x.length);
        focused = false;
    }

    public void setImages(BufferedImage standardImage, BufferedImage focusedImage, int imageX, int imageY) {
        this.standardImage = standardImage;
        this.focusedImage = focusedImage;
        this.imageX = imageX;
        this.imageY = imageY;
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
            g2d.drawPolygon(polygon);
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
        return polygon.intersects((mx - (MOUSE_MASK / 2)), (my - (MOUSE_MASK / 2)), MOUSE_MASK, MOUSE_MASK);
    }

    @Override
    public void act() {
        MyGame myGame = (MyGame) game;
        myGame.changeScene(1);
        //todo : fazer isso na scene
    }

}
