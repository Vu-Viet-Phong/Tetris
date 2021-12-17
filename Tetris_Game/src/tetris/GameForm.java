package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout;
import javax.swing.WindowConstants;

public class GameForm extends JFrame {
    private GameArea ga;
    private GameThread gt;

    private JButton btnMainMenu;
    private JPanel gameAreaPlaceholder;
    private JLabel levelDisplay;
    private JLabel scoreDisplay;
    
    public GameForm() {
        initComponents();
        
        ga = new GameArea(gameAreaPlaceholder, 10);
        this.add(ga);
        
        initControls();
    }

    private void initComponents() {
        gameAreaPlaceholder = new JPanel();
        scoreDisplay = new JLabel();
        levelDisplay = new JLabel();
        btnMainMenu = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gameAreaPlaceholder.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        GroupLayout gameAreaPlaceholderLayout = new GroupLayout(gameAreaPlaceholder);
        gameAreaPlaceholder.setLayout(gameAreaPlaceholderLayout);
        gameAreaPlaceholderLayout.setHorizontalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        gameAreaPlaceholderLayout.setVerticalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );

        scoreDisplay.setFont(new Font("Tahoma", 0, 18)); 
        scoreDisplay.setText("Score: 0");

        levelDisplay.setFont(new Font("Tahoma", 0, 18));
        levelDisplay.setText("Level: 1");

        btnMainMenu.setText("Main Menu");
        btnMainMenu.setFocusable(false);
        btnMainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnMainMenuActionPerformed(e);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMainMenu, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(gameAreaPlaceholder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(levelDisplay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scoreDisplay, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btnMainMenu)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scoreDisplay)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(levelDisplay))
                    .addComponent(gameAreaPlaceholder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }
    
    private void initControls() {
        InputMap im = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();
        
        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");

        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockRight();
            }
        });
        
        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockLeft();
            }
        });
        
        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.rotateBlock();
            }
        });
        
        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.dropBlock();
            }
        });
    }
    
    public void startGame() {
        ga.initBackgroundArray();
        gt = new GameThread(ga, this);
        gt.start();
    }
    
    public void updateScore(int score) {
        scoreDisplay.setText("Score: " + score);
    }
    
    public void updateLevel(int level) {
        levelDisplay.setText("Level: " + level);
    }

    private void btnMainMenuActionPerformed(ActionEvent e) {
        gt.interrupt();
        this.setVisible(false);
        Tetris.showStartup();
    }
}
