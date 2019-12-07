package nona.mi.animatedcomponent;

import nona.mi.main.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class AnimatedComponent {

    private BufferedImage[] images;
    private int x;
    private int y;
    private int width;
    private int height;
    private float speed;
    private float id;
    private boolean animating;
    private Game game;
    private String audioPath;
    private String audioName;


    public AnimatedComponent(Game game) {
        this.game = game;
        id = 0;
    }

    public void setImages(BufferedImage[] images, int x, int y, float speed) {
        this.images = images;
        this.x = x;
        this.y = y;
        this.speed = speed;
        width = images[0].getWidth();
        height = images[0].getHeight();
    }

    public void defineAudio(String audioPath, String audioName) {
        this.audioPath = audioPath;
        this.audioName = audioName;
    }

    public void update() {
        if (isMouseOnComponent() && isComponentClicked() && !animating && !game.getJukeBox().isPlaying(audioName)) {
            animating = true;
            if (!game.getJukeBox().isPlaying(audioName)) {
                game.getJukeBox().play(audioName);
            }
        }
        if (animating) {
            id += speed;
            if (id >= images.length) {
                id = 0;
                animating = false;
            }
        }
    }

    private boolean isMouseOnComponent() {
        int mx = game.getMouseX();
        int my = game.getMouseY();
        return (mx >= x && mx <= x + width) && (my >= y && my <= y + height);
    }

    private boolean isComponentClicked() {
        return game.isMouseClicked();
    }

    public void render(Graphics g) {
        g.drawImage(images[(int)id], x, y, null);
    }

    public String getAudioName() {
        return audioName;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void reset() {
        animating = false;
        id = 0;
        if (game.getJukeBox().isPlaying(audioName)) {
            game.getJukeBox().stop(audioName);
        }
    }

}
