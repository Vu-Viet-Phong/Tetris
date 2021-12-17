package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class StartupForm extends JFrame {
    private JButton btnLeaderBoard;
    private JButton btnQuit;
    private JButton btnStart;

    public StartupForm() {
        initComponents();
    }

    private void initComponents() {
        btnStart = new JButton();
        btnLeaderBoard = new JButton();
        btnQuit = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnStart.setText("Start Game");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnStartActionPerformed(e);
            }
        });

        btnLeaderBoard.setText("LeaderBoard");
        btnLeaderBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnLeaderBoardActionPerformed(e);
            }
        });

        btnQuit.setText("Quit");
        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnQuitActionPerformed(e);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(119, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(btnQuit, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLeaderBoard, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(161, Short.MAX_VALUE)
                .addComponent(btnStart)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLeaderBoard)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuit)
                .addGap(58, 58, 58))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnStartActionPerformed(ActionEvent e) {
        this.setVisible(false);
        Tetris.start();
    }

    private void btnLeaderBoardActionPerformed(ActionEvent e) {
        this.setVisible(false);
        Tetris.showLeaderboard();
    }

    private void btnQuitActionPerformed(ActionEvent evet) {
        System.exit(0);
    }
}
