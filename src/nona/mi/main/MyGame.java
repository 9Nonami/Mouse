package nona.mi.main;

import nona.mi.scene.*;

import java.awt.Graphics;

public class MyGame extends Game {

    private Scene sceneBasis;
    private ScenePackage packBasis;

    public MyGame(int width, int height, String title, int gameLoopStyle) {
        super(width, height, title, gameLoopStyle);

        packBasis = new ScenePackage();
        packBasis.put(0, new TestScene(this));
        packBasis.put(1, new TestScene2(this, "hello darkness, my old friend"));
        sceneBasis = packBasis.get(0);
    }

    @Override
    public void updateClass() {
        sceneBasis.update();
        resetInputs();
    }

    @Override
    public void renderClass(Graphics g) {
        testFill();
        sceneBasis.render(g);
    }

    private void resetInputs() {
        setMouseClicked(false);
    }

    public void changeScene(int scene) {
        sceneBasis = packBasis.get(scene);
    }
}
