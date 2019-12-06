package nona.mi.scene;

import nona.mi.main.MyGame;

import java.awt.Color;
import java.awt.Graphics;

public class TestScene2 extends Scene {

    private String text;

    public TestScene2(MyGame myGame, String text) {
        super(myGame);
        this.text = text;
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.WHITE);
        g.drawString(text, 5, 15);
    }

}
