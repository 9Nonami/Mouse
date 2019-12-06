package nona.mi.scene;

import nona.mi.main.MyGame;

import java.awt.Graphics;

public abstract class Scene {

    protected MyGame myGame;

    public Scene(MyGame myGame) {
        this.myGame = myGame;
    }

    public void update() {

    }

    public void render(Graphics g) {

    }

    public void reset() {

    }

}
