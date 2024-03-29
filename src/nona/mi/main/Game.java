package nona.mi.main;

import nona.mi.jukebox.JukeBox;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;

public abstract class Game implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private JFrame jframe;
    private BufferStrategy bs;
    private Canvas canvas;

    private Graphics g;
    private boolean running;
    private Thread t;

    private int width; //854
    private int height; //480

    private double limit;
    private int fps;
    private float speedAdjust;

    public static final int HARD_GAME_LOOP = 0;
    public static final int SMOOTH_GAME_LOOP = 1;
    private int gameLoopStyle;
    private boolean showLoopLog;

    private int mouseX;
    private int mouseY;
    private boolean mouseClicked;

    protected JukeBox jukeBox;


    public Game(int width, int height, String title, int gameLoopStyle) {

        this.width = width;
        this.height = height;
        this.gameLoopStyle = gameLoopStyle;

        if (gameLoopStyle == HARD_GAME_LOOP){
            fps = 30;
        } else {
            fps = 60;
        }

        limit = (double) 1_000_000_000 / fps;

        //se for 30fps, roda com os valores definidos
        //se for 60fps, nao deixa ser o dobro da velocidade
        if (gameLoopStyle == HARD_GAME_LOOP){
            speedAdjust = 1;
        } else {
            speedAdjust = 0.5f;
        }

        jframe = new JFrame(title);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas();
        Dimension d = new Dimension(width, height);
        canvas.setPreferredSize(d);
        canvas.setMinimumSize(d);
        canvas.setMaximumSize(d);

        jframe.add(canvas, BorderLayout.CENTER);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);

        jframe.addKeyListener(this);

        canvas.createBufferStrategy(2);

        jframe.setIgnoreRepaint(true);
        canvas.setFocusable(false);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);

        jukeBox = new JukeBox();

        t = new Thread(this);
    }

    public void start() {
        running = true;
        jframe.setVisible(true);
        t.start();
    }

    private void update() {
        updateClass();
    }

    public abstract void updateClass();

    private void render() {
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        renderClass(g);
        g.dispose();
        bs.show();
    }

    public abstract void renderClass(Graphics g);

    @Override
    public void run(){
        if (gameLoopStyle == HARD_GAME_LOOP){
            hardGameLoop();
        } else {
            smoothGameLoop();
        }
    }

    public void hardGameLoop() {
        double ini = 0;
        double end = 0;
        double delta = 0;
        double wait = 0;
        while (running) {
            ini = System.nanoTime();
            update();
            render();
            end = System.nanoTime();
            delta = end - ini;
            wait = limit - delta;
            if (wait > 0) {
                try {
                    wait = wait / 1_000_000;
                    if (showLoopLog) {
                        System.out.println("wait: " + (int) wait + " millis");
                    }
                    Thread.sleep((long) wait);
                } catch (Exception ex) {
                }
            } else {
                if (showLoopLog) {
                    System.out.println("overload: " + (int) wait / 1_000_000);
                }
            }
        }
    }

	public void smoothGameLoop(){
		double ini = 0;
		double end = 0;
		double delta = 0;
		
		double timer = 0;
		int fps = 0;
		int ups = 0;

		timer = System.currentTimeMillis();
		ini = System.nanoTime();
		while(running){
			end = System.nanoTime();
			delta += end - ini;
			ini = end;
			fps++;
			while (delta >= limit) {
				ups++;
				update();
				delta -= limit;
			}
			render();
			try{
				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				if (showLoopLog) {
                    System.out.println("ups: " + ups);
                    System.out.println("fps: " + fps);
                    System.out.println("");
                }
				ups = 0;
				fps = 0;
			}
		}
	}

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void testFill() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClicked = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setShowLoopLog(boolean showLoopLog) {
        this.showLoopLog = showLoopLog;
    }

    public int getFps() {
        return fps;
    }

    public float getSpeedAdjust() {
        return speedAdjust;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isMouseClicked() {
        return mouseClicked;
    }

    public void setMouseClicked(boolean mouseClicked) {
        this.mouseClicked = mouseClicked;
    }

    public JukeBox getJukeBox() {
        return jukeBox;
    }

}
