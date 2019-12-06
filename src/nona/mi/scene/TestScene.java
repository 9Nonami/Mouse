package nona.mi.scene;

import java.awt.Graphics;

import nona.mi.button.Button;
import nona.mi.main.MyGame;

public class TestScene extends Scene {

    private Button[] buttons;

    public TestScene(MyGame myGame) {
        super(myGame);
    }

    public void setButtons(Button[] buttons) {
        this.buttons = buttons;
    }

    @Override
    public void update() {
        super.update();

        for (Button button : buttons) {
            button.update();
            if (button.isClicked()) {
                myGame.changeScene(button.getNextScene());
                button.reset();
                break;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        for (Button button : buttons) {
            button.render(g);
        }
    }

}
