/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package checkers;
import javax.swing.*;
/**
 *
 * @author jmalmskog
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame f1 = new JFrame("3-D Checkers");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homePageScreen p1 = new homePageScreen();
        f1.getContentPane().add(p1);
        f1.setSize(675,400);
        f1.setVisible(true);
    }

}
