package nona.mi.scene;

import nona.mi.component.PolygonalButton;
import nona.mi.component.RectButton;
import nona.mi.loader.ImageLoader;
import nona.mi.main.MyGame;

import java.awt.Graphics;

public class TestScene extends Scene {


    private PolygonalButton polyButton;

    public TestScene(MyGame myGame) {
        super(myGame);

        int[] x = {66, 205, 205, 66};
        int[] y = {89, 26, 215, 278};
        polyButton = new PolygonalButton(myGame, x, y);
        polyButton.setImages(ImageLoader.loadImage("/res/stan0.png"), ImageLoader.loadImage("/res/focus0.png"), 60, 20);

    }

    @Override
    public void update() {
        super.update();
        polyButton.update();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        polyButton.render(g);
    }

}
