package checkers;

import java.io.File;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class adminScreen extends javax.swing.JPanel {

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList users_list;
    private Vector users_list_storage;

    /** Creates new form adminScreen */
    public adminScreen() {
        initComponents();
    }

    public void populate_vector() {
        File folder = new File("users/");
        File[] listOfFiles = folder.listFiles();
        users_list_storage.clear();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                users_list_storage.addElement((String) listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length() - 4));
            }
        }
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();

        users_list_storage = new Vector();
        users_list = new javax.swing.JList(users_list_storage);

        jLabel1.setFont(new java.awt.Font("Castellar", 1, 36));
        jLabel1.setText("Administration");

        jButton1.setText("Delete Player");
        jButton2.setText("Clear Stats");

        jLabel2.setFont(new java.awt.Font("Ravie", 1, 14));
        jLabel2.setText("Tools");


        jButton1.addActionListener(new ButtonListener());//adds button listener to jButton1
        jButton2.addActionListener(new ButtonListener());//adds button listener to jButton2

        jScrollPane1.setViewportView(users_list);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(layout.createSequentialGroup().add(19, 19, 19).add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE).add(18, 18, 18)).add(layout.createSequentialGroup().add(64, 64, 64).add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jLabel2).add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING).add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 119, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 119, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 39, Short.MAX_VALUE).add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 142, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(76, 76, 76)));
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(layout.createSequentialGroup().add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(53, 53, 53).add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(layout.createSequentialGroup().add(jLabel2).add(18, 18, 18).add(jButton1).add(33, 33, 33).add(jButton2)).add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).add(79, 79, 79)));

        populate_vector();
        users_list.updateUI();
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == jButton1)//checks to see if remove button was pushed
            {

                if (users_list_storage.size() == 0)//checks to see if courses selected is empty
                {
                    System.out.println("NO Files IN USERS FOLDER!!");
                } else {
                    Object selected = users_list.getSelectedValue();//creates an object that is used to move selected values from the JList
                    if (selected == null) {
                        System.out.println("Nothing was selected!!!");
                    } else {
                        String to_be_deleted = (String) selected;
                        User_Saver user_to_delete = new User_Saver(to_be_deleted);
                        user_to_delete.delete_file();
                        populate_vector();
                        users_list.updateUI();//updates the JList selectedCourses
                    }
                }
            }

            if (event.getSource() == jButton2)//checks to see if remove button was pushed
            {

                if (users_list_storage.size() == 0)//checks to see if courses selected is empty
                {
                    System.out.println("NO Files IN USERS FOLDER!!");
                } else {
                    Object selected = users_list.getSelectedValue();//creates an object that is used to move selected values from the JList
                    if (selected == null) {
                        System.out.println("Nothing was selected!!!");
                    } else {
                        String user_to_clear = (String) selected;
                        User_Saver user = new User_Saver(user_to_clear);
                        user.read_file();
                        user.clear_stats();
                        user.write_to_file();
                        populate_vector();
                        users_list.updateUI();//updates the JList selectedCourses
                    }
                }
            }

        }
    }
}
