package nona.mi.scene;

import java.awt.Graphics;

import nona.mi.animatedcomponent.AnimatedComponent;
import nona.mi.button.Button;
import nona.mi.main.MyGame;

public class TestScene extends Scene {

    private Button[] buttons;
    private AnimatedComponent[] animatedComponents;

    public TestScene(MyGame myGame) {
        super(myGame);
    }

    public void setButtons(Button[] buttons) {
        this.buttons = buttons;
    }

    public void setAnimatedComponents(AnimatedComponent[] animatedComponents) {
        this.animatedComponents = animatedComponents;
    }

    @Override
    public void update() {
        super.update();
        if (animatedComponents != null) {
            for (AnimatedComponent ac : animatedComponents) {
                ac.update();
            }
        }
        for (Button button : buttons) {
            button.update();
            if (button.isClicked()) {
                myGame.changeScene(button.getNextScene());
                button.reset();
                if (animatedComponents != null) {
                    for (AnimatedComponent ac : animatedComponents) {
                        ac.reset();
                    }
                }
                break;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        if (animatedComponents != null) {
            for (AnimatedComponent ac : animatedComponents) {
                ac.render(g);
            }
        }
        for (Button button : buttons) {
            button.render(g);
        }
    }

}
