package Sudoku;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Adonis on 30/04/2016.
 */
public class RatingMouseListener extends MouseAdapter {
    private final int index1;
    private final int index2;
    private final JButton[][] button;

    public RatingMouseListener(int i, int j, JButton[][] buttons) {
        this.index1 = i;
        this.index2 = j;
        this.button = buttons;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        try {
            double number = Double.parseDouble(JOptionPane.showInputDialog(null, "Write a number :"));
            if (number > 0) {
                if (number <=9){
                    int Inumber = (int) number;
                    String value = String.valueOf(Inumber);
                    button[index1][index2].setText(value);
                } else {
                    JOptionPane.showMessageDialog(null, "Illegal Value.");
                }
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Please enter a numeric value.");
        }
    }
}
