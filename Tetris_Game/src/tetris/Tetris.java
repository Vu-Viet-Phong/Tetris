package tetris;

import java.awt.EventQueue;
import javax.swing.JOptionPane;

public class Tetris {
    private static GameForm gf;
    private static StartupForm sf;
    private static LeaderboardForm lf;
    private static AudioPlayer audio = new AudioPlayer();
    
    public static void start() {
        gf.setVisible(true);
        gf.startGame();
    }
    
    public static void showStartup() { 
        sf.setVisible(true);   
    }
    
    public static void showLeaderboard() { 
        lf.setVisible(true);   
    } 

    public static void playClear() { 
        audio.playClearLine(); 
    }

    public static void playGameOver() { 
        audio.playGameOver();  
    }
    
    public static void gameOver(int score) {
        playGameOver();
        String playerName = JOptionPane.showInputDialog("Game Over!\nPlease enter your name!");
        gf.setVisible(false);
        lf.addPlayer(playerName, score);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            gf = new GameForm();
            sf = new StartupForm();
            lf = new LeaderboardForm();
        
            sf.setVisible(true);
        }); 
    }
}
