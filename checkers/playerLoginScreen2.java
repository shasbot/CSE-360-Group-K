/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * playerLoginScreen2.java
 *
 * Created on Nov 23, 2009, 1:16:11 AM
 */

package checkers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmalmskog
 */
public class playerLoginScreen2 extends javax.swing.JPanel {

    /** Creates new form playerLoginScreen2 */
    public playerLoginScreen2() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jLabel3.setFont(new java.awt.Font("Ravie", 1, 14));
        jLabel3.setText("Password:");

        jLabel6.setFont(new java.awt.Font("Ravie", 1, 14));
        jLabel6.setText("Player 2");

        jLabel1.setFont(new java.awt.Font("Castellar", 1, 36));
        jLabel1.setText("Log IN (10x10)");

        jButton6.setText("Play Game");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Ravie", 1, 14));
        jLabel4.setText("Password:");

        jLabel7.setFont(new java.awt.Font("Ravie", 1, 14));
        jLabel7.setText("Player 1");

        jLabel5.setFont(new java.awt.Font("Ravie", 1, 14));
        jLabel5.setText("Username:");

        jLabel2.setFont(new java.awt.Font("Ravie", 1, 14));
        jLabel2.setText("Username:");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(64, 64, 64)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel5)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(jLabel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jTextField2)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 38, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel2)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(jLabel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jTextField4)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .add(235, 235, 235)
                .add(jButton6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel1))
                    .add(layout.createSequentialGroup()
                        .add(123, 123, 123)
                        .add(jLabel7)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 163, Short.MAX_VALUE)
                        .add(jLabel6)))
                .add(140, 140, 140))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .add(30, 30, 30)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jLabel7))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel5)
                            .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(8, 8, 8)
                        .add(jLabel4))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel2)
                            .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(8, 8, 8)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel3)
                            .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(11, 11, 11)
                        .add(jLabel9)
                        .add(8, 8, 8)
                        .add(jButton6))
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel8)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (jTextField1.getText() != null)
            playerOne = jTextField1.getText();
        if (jTextField3.getText() != null)
            playerTwo = jTextField3.getText();
        if(jTextField2.getText() != null)
            passOne = jTextField2.getText();
        if(jTextField4.getText() != null)
            passTwo = jTextField4.getText();
        playerOne = "users/" + playerOne + ".txt";
        playerTwo = "users/" + playerTwo + ".txt";
        File fileOne = new File(playerOne);
        File fileTwo = new File(playerTwo);
        if(fileOne.exists() && fileTwo.exists())//checks if files are valid
        {
            try {
                Scanner one = new Scanner(new File(playerOne));
                Scanner two = new Scanner(new File(playerTwo));
                actualPassOne = one.nextLine();actualPassOne = one.nextLine();
                actualPassTwo = two.nextLine();actualPassTwo = two.nextLine();
                one.close();
                two.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(playerLoginScreen.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(passTwo.compareTo(actualPassTwo) == 0 && passOne.compareTo(actualPassOne) == 0)
            {
                GUI test = new GUI(10, jTextField1.getText(), jTextField3.getText());
            }
            if(passTwo.compareTo(actualPassTwo) != 0)
                jLabel9.setText("Invalid Password.");
            if(passTwo.compareTo(actualPassTwo) == 0)
                jLabel9.setText("");
            if(passOne.compareTo(actualPassOne) != 0)
                jLabel8.setText("Invalid Password.");
            if(passOne.compareTo(actualPassOne) == 0)
                jLabel8.setText("");
        }
        if(!fileOne.exists())//if user one's file was not found
            jLabel8.setText("Invalid User.");
        if(fileOne.exists() && passOne.compareTo(actualPassOne) == 0 )
            jLabel8.setText("");
        if(!fileTwo.exists())//if user two's file was not found
            jLabel9.setText("Invalid User.");
        if(fileTwo.exists() && passTwo.compareTo(actualPassTwo) == 0)
            jLabel9.setText("");



    //}
        
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
    String playerOne = null;
    String playerTwo = null;
    String passOne = null;
    String passTwo = null;
    String actualPassOne = "";
    String actualPassTwo = "";
}
