import javax.swing.*;

import org.example.scoremanager;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GameBoard extends JPanel implements ActionListener, KeyListener {
    private Player player;
    private ArrayList<Virus> viruses;
    private ArrayList<Patch> patches;
    private Timer timer;
    private int score = 0;
    private boolean gameOver = false;
    private Random random = new Random();
    private String playerName;

    public GameBoard() {
        this.playerName = JOptionPane.showInputDialog("Enter your name:");

        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        initGame();
        timer = new Timer(200, this);
        timer.start();
    }

    private void initGame() {
        player = new Player(200, 200);
        viruses = new ArrayList<>();
        patches = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            viruses.add(new Virus(randCoord(), randCoord()));
        }
        for (int i = 0; i < 5; i++) {
            patches.add(new Patch(randCoord(), randCoord()));
        }
    }

    private int randCoord() {
        return random.nextInt(20) * 20;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            player.move();
            for (Virus v : viruses) {
                v.moveRandom();
                if (player.getBounds().intersects(v.getBounds())) {
                    gameOver = true;
                    scoremanager.saveScore(playerName, score); // Save score when game ends
                }
            }
            patches.removeIf(patch -> {
                if (player.getBounds().intersects(patch.getBounds())) {
                    score++;
                    return true;
                }
                return false;
            });
            if (patches.isEmpty()) {
                for (int i = 0; i < 5; i++) {
                    patches.add(new Patch(randCoord(), randCoord()));
                }
            }
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!gameOver) {
            player.draw(g);
            for (Virus v : viruses) v.draw(g);
            for (Patch p : patches) p.draw(g);
            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 10, 20);
        } else {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over!", 120, 180);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Score: " + score, 160, 210);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT -> player.setDirection(-20, 0);
            case KeyEvent.VK_RIGHT -> player.setDirection(20, 0);
            case KeyEvent.VK_UP -> player.setDirection(0, -20);
            case KeyEvent.VK_DOWN -> player.setDirection(0, 20);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
