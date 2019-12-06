package nona.mi.scene;

import nona.mi.main.MyGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;


public class TestPolygonScene extends Scene {

    private Polygon triangle;
    private Rectangle rectangle;

    private int mx;
    private int my;
    private int width;
    private int height;

    public TestPolygonScene(MyGame myGame) {
        super(myGame);

        int[] x = {50, 80, 25};
        int[] y = {50, 120, 120};
        triangle = new Polygon(x, y, x.length);
        rectangle = new Rectangle(80, 50, 50, 50);

    }

    @Override
    public void update() {
        super.update();
        mx = myGame.getMouseX();
        my = myGame.getMouseY();
        if (triangle.intersects(mx - 15, my - 15, 30, 30)) {
            System.out.println(true);
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.drawPolygon(triangle);

        g2d.setColor(Color.WHITE);
        g2d.drawRect((int)rectangle.getX(), (int)rectangle.getY(), (int)rectangle.getWidth(), (int)rectangle.getHeight());

        g2d.drawRect(mx - 15, my - 15, 30, 30);
    }
}
