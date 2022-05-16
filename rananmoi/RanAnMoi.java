
package rananmoi;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

public class RanAnMoi extends JFrame{

    private GameScreen gscreen;
    public static ArrayList<User> users = null; 
    
    public RanAnMoi() {
        this.setSize(727, 450);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Snake game");
        gscreen = new GameScreen();
        this.add(gscreen);
        this.addKeyListener(new mHandler());
        users = new ArrayList<User>();
        this.readData();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                updateData();
            }
        });
        this.setVisible(true);
    }
    
    private class mHandler implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    gscreen.cr.setVector(ConRan.GO_UP);
                    break;
                case KeyEvent.VK_DOWN:
                    gscreen.cr.setVector(ConRan.GO_DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    gscreen.cr.setVector(ConRan.GO_LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    gscreen.cr.setVector(ConRan.GO_RIGHT);
                    break;
                case KeyEvent.VK_SPACE:
                    GameScreen.isPlaying = !GameScreen.isPlaying;
                    if (GameScreen.isGameOver) {
                        GameScreen.isGameOver = false;
                        gscreen.cr.resetGame();
                    }
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }
    
    public void updateData() {
        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter("data/data.bin");
            bw = new BufferedWriter(fw);
            for (int i = 0; i < users.size(); i++) {
                if (i >= 10) break;
                bw.write(users.get(i).toString() + "\n");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        finally {
            try {
                bw.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public void readData() {
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader("data/data.bin");
            br = new BufferedReader(fr);
            String str = null;
            while ((str = br.readLine()) != null && !str.isEmpty()) {
                String[] arr = str.split("  ");
                if (arr.length == 3)
                    users.add(new User(arr[0], arr[1], arr[2]));
            }
            for (int i = 0; i < users.size(); i++) {
                System.out.println(users.get(i).toString());
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        finally {
            try {
                br.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
}
