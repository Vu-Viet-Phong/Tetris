package tetris;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Vector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class LeaderboardForm extends javax.swing.JFrame {
    private DefaultTableModel tm;
    private String leaderboardFile = "leaderboard";
    private TableRowSorter<TableModel> sorter;

    private JButton jButton1;
    private JScrollPane jScrollPane1;
    private JTable leaderboard;
    
    public LeaderboardForm() {
        initComponents();
        initTableData();
        initTableSorter();
    }

    private void initComponents() {
        jButton1 = new JButton();
        jScrollPane1 = new JScrollPane();
        leaderboard = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton1.setText("Main Menu");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1ActionPerformed(e);
            }
        });

        leaderboard.setModel(new DefaultTableModel(new Object [][] {}, new String [] {"Player", "Score"}) {
            Class[] types = new Class [] {Object.class, Integer.class};
            boolean[] canEdit = new boolean [] {false, false};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(leaderboard);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButton1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void initTableData() {
        Vector ci = new Vector();
        ci.add("Player");
        ci.add("Score");
        
        tm = (DefaultTableModel)leaderboard.getModel();
        
        try {
            FileInputStream fs = new FileInputStream(leaderboardFile);
            ObjectInputStream os = new ObjectInputStream(fs);
            
            tm.setDataVector((Vector<Vector>)os.readObject(), ci);
            
            os.close();
            fs.close();
        } catch (Exception e) {}
    }
    
    private void initTableSorter() {
        sorter = new TableRowSorter<>(tm);
        leaderboard.setRowSorter(sorter);
        
        ArrayList<SortKey> keys = new ArrayList<>();
        keys.add(new SortKey(1, SortOrder.DESCENDING));
        
        sorter.setSortKeys(keys);
    }
    
    private void saveLeaderboard() {
        try {
            FileOutputStream fs = new FileOutputStream(leaderboardFile);
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(tm.getDataVector());
            
            os.close();
            fs.close();
        } catch(Exception e) {}
    }

    private void jButton1ActionPerformed(ActionEvent e) {
        this.setVisible(false);
        Tetris.showStartup();
    }

    public void addPlayer(String playerName, int score) {
       this.setVisible(true);
       tm.addRow(new Object[]{playerName, score});
       sorter.sort();
       saveLeaderboard();
    }
}
