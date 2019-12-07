package nona.mi.main;

import nona.mi.animatedcomponent.AnimatedComponent;
import nona.mi.button.AnimatedRectButton;
import nona.mi.button.Button;
import nona.mi.button.PolygonalButton;
import nona.mi.button.RectButton;
import nona.mi.loader.ImageLoader;
import nona.mi.scene.Scene;
import nona.mi.scene.ScenePackage;
import nona.mi.scene.TestScene;
import nona.mi.spritesheet.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MyGame extends Game {

    private Scene sceneBasis;
    private ScenePackage packBasis;
    private int scene;


    public MyGame(int width, int height, String title, int gameLoopStyle) {

        super(width, height, title, gameLoopStyle);

        packBasis = new ScenePackage();

        //path, key
        String audioClickPath = "/res/click.wav";
        String audioClickName = "click";
        jukeBox.load(audioClickPath, audioClickName);

        //------------------------------------------------------------------------

        TestScene scene0 = new TestScene(this);
        Button[] buttonsScene0 = new Button[2];

        RectButton rectButton = new RectButton(this, 1);
        BufferedImage uno = ImageLoader.loadImage("/res/uno.png");
        BufferedImage dos = ImageLoader.loadImage("/res/dos.png");
        rectButton.setImages(uno, dos, 10, 10);
        rectButton.setAudioName(audioClickName);
        buttonsScene0[0] = rectButton;

        int[] x = {66, 205, 205, 66};
        int[] y = {89, 26, 215, 278};
        PolygonalButton polyButton = new PolygonalButton(this, 1, x, y);
        polyButton.setImages(ImageLoader.loadImage("/res/stan0.png"), ImageLoader.loadImage("/res/focus0.png"), 60, 20);
        polyButton.setAudioName(audioClickName);
        buttonsScene0[1] = polyButton;

        scene0.setButtons(buttonsScene0);

        //http://opengameart.org/content/lpc-sara
        //http://opengameart.org/content/sara-wizard
        AnimatedComponent[] components = new AnimatedComponent[1];
        AnimatedComponent ac = new AnimatedComponent(this);
        BufferedImage[] componentImages2 = SpriteSheet.getSprites("/res/spritesheet.png", 18 * 64, 64, 64, 13);
        ac.setImages(componentImages2, 400, 400, 1f);
        ac.setAudioName(audioClickName);
        components[0] = ac;

        scene0.setAnimatedComponents(components);

        packBasis.put(0, scene0);

        //------------------------------------------------------------------------

        TestScene scene1 = new TestScene(this);
        Button[] buttonsScene1 = new Button[2];

        RectButton rectButton2 = new RectButton(this, 0);
        rectButton2.setImages(uno, dos, 300, 100);
        rectButton2.setAudioName(audioClickName);
        buttonsScene1[0] = rectButton2;

        AnimatedRectButton arb = new AnimatedRectButton(this, 0);
        arb.setImages(componentImages2, 10, 10, 0.2f);
        arb.setAudioName(audioClickName);
        buttonsScene1[1] = arb;

        scene1.setButtons(buttonsScene1);
        packBasis.put(1, scene1);

        //------------------------------------------------------------------------

        scene = 0;
        sceneBasis = packBasis.get(scene);
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
        g.setColor(Color.PINK);
        g.drawString("Scene: " + scene, 10, getHeight() - 20);
    }

    private void resetInputs() {
        setMouseClicked(false);
    }

    public void changeScene(int scene) {
        this.scene = scene;
        sceneBasis = packBasis.get(scene);
    }
}
