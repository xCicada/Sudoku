/**
 * Created by Adonis on 26/04/2016.
 */

package Sudoku;

/*TODO LIST
button CHECK for solution
Press button --> checksolution(get object+grid + getUserGrid) in GUI or main
*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;
import javax.swing.*;

public class GUI extends JPanel {
    private static final int SML_SIDE = 3;
    private static final int SIDE = SML_SIDE * SML_SIDE;
    private static final int GAP = 3;
    private static final Color BG = Color.DARK_GRAY;
    private static final Dimension BTN_PREF_SIZE = new Dimension(65, 65);
    private static final JButton[][] buttons = new JButton[SIDE][SIDE];


    public GUI(Console sudoku) {

        sudoku.createBoard(35);
        JPanel GUI = new JPanel();
        GUI.setBackground(BG);
        GUI.setLayout(new GridLayout(SML_SIDE, SML_SIDE, GAP, GAP));
        GUI.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        JPanel[][] smallPanels = new JPanel[SML_SIDE][SML_SIDE];

        for (int i = 0; i < smallPanels.length; i++) {
            for (int j = 0; j < smallPanels[i].length; j++) {
                smallPanels[i][j] = new JPanel(new GridLayout(SML_SIDE, SML_SIDE));
                GUI.add(smallPanels[i][j]);
            }
        }

        add(GUI);

        ///////////// BUTTONS CREATION ////////////////

        for (int i = 0; i < buttons.length; i++) {
            int panelI = i / SML_SIDE;
            for (int j = 0; j < buttons[i].length; j++) {
                int panelJ = j / SML_SIDE;
                buttons[i][j] = new JButton();
                buttons[i][j].setPreferredSize(BTN_PREF_SIZE);
                RatingMouseListener ml = new RatingMouseListener(i,j,buttons);
                buttons[i][j].addMouseListener(ml);
                int number = sudoku.boardH[i][j];
                if (number != 0) {
                    buttons[i][j].setForeground(Color.RED);
                    String value = String.valueOf(number);
                    buttons[i][j].setText(value);
                    buttons[i][j].removeMouseListener(ml);
                } smallPanels[panelI][panelJ].add(buttons[i][j]);
            }
        }

        JPanel sol = new JPanel();
        sol.setLayout(new BoxLayout(sol, BoxLayout.PAGE_AXIS));
        JButton checkS = new JButton("CHECK SOLUTION");
        checkS.addActionListener(arg0 -> {
            int[][] board = getUserGrid();
            boolean solution = Arrays.deepEquals(board,sudoku.board);
            if(solution){
                JLabel win = new JLabel("You found the solution !");
                sol.add(win);
                System.out.println("gagné");
            }
        });
        checkS.setPreferredSize(new Dimension(140,60));
        sol.add(checkS);
        add(sol);
    }

    private int[][] getUserGrid(){
        int[][] board = new int[9][9];
        for (int i = 0; i < buttons.length; i++){
            for (int j = 0; j < buttons[i].length; j++) {
                String valueS = buttons[i][j].getText();
                int value = Integer.parseInt(valueS);
                board[i][j] = value;
            }
        }
        return board;
    }

    public void createGui(GUI GridPanel) {
        JPanel mainPanel = new JPanel();
        //mainPanel.add(UPanel);
        mainPanel.add(GridPanel);

        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}