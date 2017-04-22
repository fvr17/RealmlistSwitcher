/**
 * @author fvr
 */
package rls;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;


public class RealmlistSwitcherGUI extends javax.swing.JFrame {

    String currentRealmlist;
    String filename = "realmlist.wtf";
    String configFilename = "realmlistSwitcherConfig.conf";
    DefaultListModel realmlists = new DefaultListModel();
    String defaultAddText = "logon.realm.com (no \"set realmlist\" here)";
    
    public RealmlistSwitcherGUI() {
        initComponents();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.jDialog1.setTitle("Add realmlist");
        this.jDialog1.setLocation( dim.width/2-jDialog1.getSize().width/2, dim.height/2-jDialog1.getSize().height/2 );
        
        this.jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        this.jList1.setModel( realmlists );
    }
    
    void readRealmlist() throws IOException {
        try (BufferedReader br = new BufferedReader( new FileReader( this.filename ) )) {
            this.currentRealmlist = br.readLine().split(" ")[2];
            jLabel2.setText( this.currentRealmlist );
            if( !this.realmlists.contains( this.currentRealmlist ) ) {
                this.realmlists.addElement( this.currentRealmlist );
                this.writeConfig();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RealmlistSwitcherGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void readConfig() throws IOException {
        File f = new File( this.configFilename );
        if( f.exists() ) {
            try (BufferedReader br = new BufferedReader( new FileReader( this.configFilename ) )) {
            String line;
            this.realmlists.clear();
            while ((line = br.readLine()) != null) {
                this.realmlists.addElement( line );
            }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RealmlistSwitcherGUI.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    void writeConfig() throws IOException {
        try (BufferedWriter bw = new BufferedWriter( new FileWriter( this.configFilename ) )) {
            for(int i = 0; i < this.realmlists.size(); ++i) {
                bw.write( this.realmlists.get(i).toString() );
                bw.newLine();
            }
            bw.close();
            this.readConfig();
        }
    }
    
    void setRealmlist(String newRealmlist) throws IOException {
        try (BufferedWriter bw = new BufferedWriter( new FileWriter( this.filename ) )) {
            bw.write( "set realmlist " + newRealmlist );
            bw.close();
            this.readRealmlist();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jDialog1.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jDialog1.setMinimumSize(new java.awt.Dimension(310, 126));
        jDialog1.setModal(true);
        jDialog1.setResizable(false);

        jButton4.setText("Add");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton5.setText("Cancel");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WoW Realmlist Switcher");
        setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        setResizable(false);

        jButton1.setText("Set");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel1.setText("Current realmlist:");
        jLabel1.setFocusable(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setFocusable(false);

        jScrollPane1.setViewportView(jList1);

        jButton2.setText("Add..");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jLabel3.setText("List of realmlists:");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.setFocusable(false);

        jButton3.setText("Remove");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 6)); // NOI18N
        jLabel4.setText("fvr");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //SET
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if( !jList1.isSelectionEmpty() ) {
            try {
                this.setRealmlist( jList1.getSelectedValue() );
            } catch (IOException ex) {
                Logger.getLogger(RealmlistSwitcherGUI.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
    }//GEN-LAST:event_jButton1MouseClicked

    //REMOVE
    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        if( !jList1.isSelectionEmpty() ) {
            String line = this.jList1.getSelectedValue();
            if( !line.equals(this.currentRealmlist) ) {
                this.realmlists.removeElement( line );
                try {
                    this.writeConfig();
                } catch (IOException ex) {
                    Logger.getLogger(RealmlistSwitcherGUI.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
        }
    }//GEN-LAST:event_jButton3MouseClicked

    //ADD
    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        this.jTextField1.setText( this.defaultAddText );
        this.jTextField1.selectAll();
        this.jTextField1.requestFocus();
        this.jDialog1.pack();
        this.jDialog1.setVisible(true);
    }//GEN-LAST:event_jButton2MouseClicked
    
    //ADD (dialog)
    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        String text = this.jTextField1.getText();
        Boolean correct = (text.length() > 0) && (!text.equals( this.defaultAddText )) && ( !this.realmlists.contains( text ) );
        if(correct) {
            this.realmlists.addElement( text );
            try {
                this.writeConfig();
            } catch (IOException ex) {
                Logger.getLogger(RealmlistSwitcherGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.jDialog1.setVisible(false);
            this.jTextField1.setText("");
        }
    }//GEN-LAST:event_jButton4MouseClicked

    //CANCEL (dialog)
    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        this.jDialog1.setVisible(false);
        this.jTextField1.setText("");
    }//GEN-LAST:event_jButton5MouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RealmlistSwitcherGUI rsgui = new RealmlistSwitcherGUI();
                try {
                    rsgui.readConfig();
                    rsgui.readRealmlist();
                } catch (IOException ex) {
                    Logger.getLogger(RealmlistSwitcherGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                rsgui.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
