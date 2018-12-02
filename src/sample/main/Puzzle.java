package sample.main;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hello
 */
//         N odd: inverse even
//         N even: 
//         Blank even-th row (bottom): inverse old
//         Blank odd-th row (bottom): inverse even
public class Puzzle extends javax.swing.JFrame implements ActionListener {

    private boolean startGame = false;
    private int moveCount;
    private int elapsed;
    private boolean newGame = false;
    private Vector<Integer> v = new Vector<>();

    private CardLayout card = new CardLayout();

    /**
     * Creates new form Puzzle
     */
    public Puzzle() {
        initComponents();
        setTitle("Puzzle Game");

        pnlMain.setLayout(card);
        pnlMain.add(pnl1, "3x3");
        pnlMain.add(pnl2, "4x4");

        card.show(pnlMain, "3x3");

        txtElapsed.setText("");
        txtMoveCount.setText("");

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
        btn8.addActionListener(this);
        btn9.addActionListener(this);
        btn10.addActionListener(this);
        btn11.addActionListener(this);
        btn12.addActionListener(this);
        btn13.addActionListener(this);
        btn14.addActionListener(this);
        btn15.addActionListener(this);
        btn16.addActionListener(this);
        btn17.addActionListener(this);
        btn18.addActionListener(this);
        btn19.addActionListener(this);
        btn20.addActionListener(this);
        btn21.addActionListener(this);
        btn22.addActionListener(this);
        btn23.addActionListener(this);
        btn24.addActionListener(this);
        btn25.addActionListener(this);
        btnNewGame.addActionListener(this);
        cboSize.addActionListener(this);
        setLocationRelativeTo(null);

        pnl1.setVisible(true);
        pnl2.setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void random() {
        int n = 0;
        boolean resultCheckWin;

        do {
            for (int i = 0; i < 8;) {
                n = new Random().nextInt(9);

                if (!v.contains(n)) {
                    if (n != 0) {
                        i++;
                        v.add(n);
                    }
                }
            }
            resultCheckWin = checkWin();
            if (!resultCheckWin) {
                v.removeAllElements();
            }
        } while (!resultCheckWin);

        btn1.setText(v.get(0).toString());
        btn2.setText(v.get(1).toString());
        btn3.setText(v.get(2).toString());
        btn4.setText(v.get(3).toString());
        btn5.setText(v.get(4).toString());
        btn6.setText(v.get(5).toString());
        btn7.setText(v.get(6).toString());
        btn8.setText(v.get(7).toString());
        btn9.setText("");
    }

    private void random2() {
        int n = 0;
        boolean checkResult;

        do {
            for (int i = 0; i < 15;) {
                n = new Random().nextInt(16);
                if (!v.contains(n)) {
                    if (n != 0) {
                        v.add(n);
                        i++;
                    }
                }
            }
            checkResult = checkWinEven();
            if (!checkResult) {
                v.removeAllElements();
            }
        } while (!checkResult);

        btn10.setText(Integer.toString(v.get(0)));
        btn11.setText(Integer.toString(v.get(1)));
        btn12.setText(Integer.toString(v.get(2)));
        btn13.setText(Integer.toString(v.get(3)));
        btn14.setText(Integer.toString(v.get(4)));
        btn15.setText(Integer.toString(v.get(5)));
        btn16.setText(Integer.toString(v.get(6)));
        btn17.setText(Integer.toString(v.get(7)));
        btn18.setText(Integer.toString(v.get(8)));
        btn19.setText(Integer.toString(v.get(9)));
        btn20.setText(Integer.toString(v.get(10)));
        btn21.setText(Integer.toString(v.get(11)));
        btn22.setText(Integer.toString(v.get(12)));
        btn23.setText(Integer.toString(v.get(13)));
        btn24.setText(Integer.toString(v.get(14)));
        btn25.setText("");
    }

    private boolean checkWin() {
        int count = 0;
        for (int i = 0; i < v.size() - 1; i++) {
            for (int j = i + 1; j < v.size(); j++) {
                if (v.get(i) > v.get(j)) {
                    count++;
                }
            }
        }

        if (count % 2 == 0) {
            return true;
        }
        return false;
    }

    private boolean checkWinEven() {
        int count = 0;
        for (int i = 0; i < v.size() - 1; i++) {
            for (int j = i + 1; j < v.size(); j++) {
                if (v.get(i) > v.get(j)) {
                    count++;
                }
            }
        }

        if (count % 2 == 0) {
            return true;
        }
        return false;
    }

    private void countElapse() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (startGame) {
                    try {
                        txtElapsed.setText(elapsed + " sec");
                        elapsed++;
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (cboSize.getSelectedItem().equals("3x3")) {
            // new game
            if (e.getSource().equals(btnNewGame)) {
                startGame = false;
                newGame = true;
                v.removeAllElements();
                txtElapsed.setText("");
                txtMoveCount.setText("");
                elapsed = 0;
                moveCount = 0;
                random();
            }

            if (!startGame) {
                countElapse();
            }
            // Avoid user press any button before start game to solve count times and clock
            if (newGame) {
                // btn1
                if (e.getSource().equals(btn1)) {
                    startGame = true;
                    if (btn2.getText().equals("")) {
                        String tmp = btn1.getText();
                        btn1.setText(btn2.getText());
                        btn2.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn4.getText().equals("")) {
                        String tmp = btn1.getText();
                        btn1.setText(btn4.getText());
                        btn4.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn2
                if (e.getSource().equals(btn2)) {
                    startGame = true;
                    if (btn1.getText().equals("")) {
                        String tmp = btn2.getText();
                        btn2.setText(btn1.getText());
                        btn1.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn3.getText().equals("")) {
                        String tmp = btn2.getText();
                        btn2.setText(btn3.getText());
                        btn3.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn5.getText().equals("")) {
                        String tmp = btn2.getText();
                        btn2.setText(btn5.getText());
                        btn5.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn3
                if (e.getSource().equals(btn3)) {
                    startGame = true;
                    if (btn2.getText().equals("")) {
                        String tmp = btn3.getText();
                        btn3.setText(btn2.getText());
                        btn2.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn6.getText().equals("")) {
                        String tmp = btn3.getText();
                        btn3.setText(btn6.getText());
                        btn6.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn4
                if (e.getSource().equals(btn4)) {
                    startGame = true;
                    if (btn1.getText().equals("")) {
                        String tmp = btn4.getText();
                        btn4.setText(btn1.getText());
                        btn1.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn5.getText().equals("")) {
                        String tmp = btn4.getText();
                        btn4.setText(btn5.getText());
                        btn5.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn7.getText().equals("")) {
                        String tmp = btn4.getText();
                        btn4.setText(btn7.getText());
                        btn7.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn5
                if (e.getSource().equals(btn5)) {
                    startGame = true;
                    if (btn2.getText().equals("")) {
                        String tmp = btn5.getText();
                        btn5.setText(btn2.getText());
                        btn2.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn4.getText().equals("")) {
                        String tmp = btn5.getText();
                        btn5.setText(btn4.getText());
                        btn4.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn6.getText().equals("")) {
                        String tmp = btn5.getText();
                        btn5.setText(btn6.getText());
                        btn6.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn8.getText().equals("")) {
                        String tmp = btn5.getText();
                        btn5.setText(btn8.getText());
                        btn8.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn6
                if (e.getSource().equals(btn6)) {
                    startGame = true;
                    if (btn3.getText().equals("")) {
                        String tmp = btn6.getText();
                        btn6.setText(btn3.getText());
                        btn3.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                    if (btn5.getText().equals("")) {
                        String tmp = btn6.getText();
                        btn6.setText(btn5.getText());
                        btn5.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                    if (btn9.getText().equals("")) {
                        String tmp = btn6.getText();
                        btn6.setText(btn9.getText());
                        btn9.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn7
                if (e.getSource().equals(btn7)) {
                    startGame = true;
                    if (btn4.getText().equals("")) {
                        String tmp = btn7.getText();
                        btn7.setText(btn4.getText());
                        btn4.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                    if (btn8.getText().equals("")) {
                        String tmp = btn7.getText();
                        btn7.setText(btn8.getText());
                        btn8.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn8
                if (e.getSource().equals(btn8)) {
                    startGame = true;
                    if (btn5.getText().equals("")) {
                        String tmp = btn8.getText();
                        btn8.setText(btn5.getText());
                        btn5.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                    if (btn7.getText().equals("")) {
                        String tmp = btn8.getText();
                        btn8.setText(btn7.getText());
                        btn7.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                    if (btn9.getText().equals("")) {
                        String tmp = btn8.getText();
                        btn8.setText(btn9.getText());
                        btn9.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn9
                if (e.getSource().equals(btn9)) {
                    startGame = true;
                    if (btn6.getText().equals("")) {
                        String tmp = btn9.getText();
                        btn9.setText(btn6.getText());
                        btn6.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                    if (btn8.getText().equals("")) {
                        String tmp = btn9.getText();
                        btn9.setText(btn8.getText());
                        btn8.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                if (btn1.getText().equals("1") && btn2.getText().equals("2") && btn3.getText().equals("3")
                        && btn4.getText().equals("4") && btn5.getText().equals("5") && btn6.getText().equals("6")
                        && btn7.getText().equals("7") && btn8.getText().equals("8") && btn9.getText().equals("")) {
                    startGame = false;
                    newGame = false;
                    JOptionPane.showMessageDialog(this, "You Win.");
                }
            }
        } else {
            if (e.getSource().equals(btnNewGame)) {
                startGame = false;
                newGame = true;
                v.removeAllElements();
                elapsed = 0;
                moveCount = 0;
                random2();
            }

            if (!startGame) {
                countElapse();
            }

            if (newGame) {
                // btn10
                if (e.getSource().equals(btn10)) {
                    startGame = true;
                    if (btn11.getText().equals("")) {
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                        String tmp = btn10.getText();
                        btn10.setText(btn11.getText());
                        btn11.setText(tmp);
                    }

                    if (btn14.getText().equals("")) {
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                        String tmp = btn10.getText();
                        btn10.setText(btn14.getText());
                        btn14.setText(tmp);
                    }
                }
                // btn11
                if (e.getSource().equals(btn11)) {
                    startGame = true;
                    if (btn10.getText().equals("")) {
                        String tmp = btn11.getText();
                        btn11.setText(btn10.getText());
                        btn10.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn12.getText().equals("")) {
                        String tmp = btn11.getText();
                        btn11.setText(btn12.getText());
                        btn12.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn15.getText().equals("")) {
                        String tmp = btn11.getText();
                        btn11.setText(btn15.getText());
                        btn15.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn12
                if (e.getSource().equals(btn12)) {
                    startGame = true;
                    if (btn11.getText().equals("")) {
                        String tmp = btn12.getText();
                        btn12.setText(btn11.getText());
                        btn11.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn13.getText().equals("")) {
                        String tmp = btn12.getText();
                        btn12.setText(btn13.getText());
                        btn13.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn16.getText().equals("")) {
                        String tmp = btn12.getText();
                        btn12.setText(btn16.getText());
                        btn16.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn13
                if (e.getSource().equals(btn13)) {
                    startGame = true;
                    if (btn12.getText().equals("")) {
                        String tmp = btn13.getText();
                        btn13.setText(btn12.getText());
                        btn12.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn17.getText().equals("")) {
                        String tmp = btn13.getText();
                        btn13.setText(btn17.getText());
                        btn17.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn14
                if (e.getSource().equals(btn14)) {
                    startGame = true;
                    if (btn10.getText().equals("")) {
                        String tmp = btn14.getText();
                        btn14.setText(btn10.getText());
                        btn10.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn15.getText().equals("")) {
                        String tmp = btn14.getText();
                        btn14.setText(btn15.getText());
                        btn15.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn18.getText().equals("")) {
                        String tmp = btn14.getText();
                        btn14.setText(btn18.getText());
                        btn18.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn15
                if (e.getSource().equals(btn15)) {
                    startGame = true;
                    if (btn11.getText().equals("")) {
                        String tmp = btn15.getText();
                        btn15.setText(btn11.getText());
                        btn11.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn14.getText().equals("")) {
                        String tmp = btn15.getText();
                        btn15.setText(btn14.getText());
                        btn14.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn16.getText().equals("")) {
                        String tmp = btn15.getText();
                        btn15.setText(btn16.getText());
                        btn16.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn19.getText().equals("")) {
                        String tmp = btn15.getText();
                        btn15.setText(btn19.getText());
                        btn19.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn16
                if (e.getSource().equals(btn16)) {
                    startGame = true;
                    if (btn12.getText().equals("")) {
                        String tmp = btn16.getText();
                        btn16.setText(btn12.getText());
                        btn12.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn15.getText().equals("")) {
                        String tmp = btn16.getText();
                        btn16.setText(btn15.getText());
                        btn15.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn17.getText().equals("")) {
                        String tmp = btn16.getText();
                        btn16.setText(btn17.getText());
                        btn17.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn20.getText().equals("")) {
                        String tmp = btn16.getText();
                        btn16.setText(btn20.getText());
                        btn20.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn17
                if (e.getSource().equals(btn17)) {
                    startGame = true;
                    if (btn13.getText().equals("")) {
                        String tmp = btn17.getText();
                        btn17.setText(btn13.getText());
                        btn13.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn16.getText().equals("")) {
                        String tmp = btn17.getText();
                        btn17.setText(btn16.getText());
                        btn16.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn21.getText().equals("")) {
                        String tmp = btn17.getText();
                        btn17.setText(btn21.getText());
                        btn21.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn18
                if (e.getSource().equals(btn18)) {
                    startGame = true;
                    if (btn14.getText().equals("")) {
                        String tmp = btn18.getText();
                        btn18.setText(btn14.getText());
                        btn14.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn19.getText().equals("")) {
                        String tmp = btn18.getText();
                        btn18.setText(btn19.getText());
                        btn19.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn22.getText().equals("")) {
                        String tmp = btn18.getText();
                        btn18.setText(btn22.getText());
                        btn22.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn19
                if (e.getSource().equals(btn19)) {
                    startGame = true;
                    if (btn15.getText().equals("")) {
                        String tmp = btn19.getText();
                        btn19.setText(btn15.getText());
                        btn15.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn18.getText().equals("")) {
                        String tmp = btn19.getText();
                        btn19.setText(btn18.getText());
                        btn18.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn20.getText().equals("")) {
                        String tmp = btn19.getText();
                        btn19.setText(btn20.getText());
                        btn20.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn23.getText().equals("")) {
                        String tmp = btn19.getText();
                        btn19.setText(btn23.getText());
                        btn23.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn20
                if (e.getSource().equals(btn20)) {
                    startGame = true;
                    if (btn16.getText().equals("")) {
                        String tmp = btn20.getText();
                        btn20.setText(btn16.getText());
                        btn16.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn19.getText().equals("")) {
                        String tmp = btn20.getText();
                        btn20.setText(btn19.getText());
                        btn19.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn21.getText().equals("")) {
                        String tmp = btn20.getText();
                        btn20.setText(btn21.getText());
                        btn21.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn24.getText().equals("")) {
                        String tmp = btn20.getText();
                        btn20.setText(btn24.getText());
                        btn24.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn21
                if (e.getSource().equals(btn21)) {
                    startGame = true;
                    if (btn17.getText().equals("")) {
                        String tmp = btn21.getText();
                        btn21.setText(btn17.getText());
                        btn17.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn20.getText().equals("")) {
                        String tmp = btn21.getText();
                        btn21.setText(btn20.getText());
                        btn20.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn25.getText().equals("")) {
                        String tmp = btn21.getText();
                        btn21.setText(btn25.getText());
                        btn25.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn22
                if (e.getSource().equals(btn22)) {
                    startGame = true;
                    if (btn18.getText().equals("")) {
                        String tmp = btn22.getText();
                        btn22.setText(btn18.getText());
                        btn18.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn23.getText().equals("")) {
                        String tmp = btn22.getText();
                        btn22.setText(btn23.getText());
                        btn23.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn23
                if (e.getSource().equals(btn23)) {
                    startGame = true;
                    if (btn19.getText().equals("")) {
                        String tmp = btn23.getText();
                        btn23.setText(btn19.getText());
                        btn19.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn22.getText().equals("")) {
                        String tmp = btn23.getText();
                        btn23.setText(btn22.getText());
                        btn22.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn24.getText().equals("")) {
                        String tmp = btn23.getText();
                        btn23.setText(btn24.getText());
                        btn24.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn24
                if (e.getSource().equals(btn24)) {
                    startGame = true;
                    if (btn20.getText().equals("")) {
                        String tmp = btn24.getText();
                        btn24.setText(btn20.getText());
                        btn20.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn23.getText().equals("")) {
                        String tmp = btn24.getText();
                        btn24.setText(btn23.getText());
                        btn23.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn25.getText().equals("")) {
                        String tmp = btn24.getText();
                        btn24.setText(btn25.getText());
                        btn25.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }
                // btn25
                if (e.getSource().equals(btn25)) {
                    startGame = true;
                    if (btn21.getText().equals("")) {
                        String tmp = btn25.getText();
                        btn25.setText(btn21.getText());
                        btn21.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }

                    if (btn24.getText().equals("")) {
                        String tmp = btn25.getText();
                        btn25.setText(btn24.getText());
                        btn24.setText(tmp);
                        moveCount++;
                        txtMoveCount.setText(Integer.toString(moveCount));
                    }
                }

                if (btn10.getText().equals("1") && btn11.getText().equals("2") && btn12.getText().equals("3")
                        && btn13.getText().equals("4") && btn14.getText().equals("5") && btn15.getText().equals("6")
                        && btn16.getText().equals("7") && btn17.getText().equals("8") && btn18.getText().equals("9")
                        && btn19.getText().equals("10") && btn20.getText().equals("11") && btn21.getText().equals("12")
                        && btn22.getText().equals("13") && btn23.getText().equals("14") && btn24.getText().equals("15")
                        && btn25.getText().equals("")) {
                    startGame = false;
                    newGame = false;
                    JOptionPane.showMessageDialog(this, "You win.");
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        pnlMain = new javax.swing.JPanel();
        pnl1 = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        pnl2 = new javax.swing.JPanel();
        btn10 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn13 = new javax.swing.JButton();
        btn14 = new javax.swing.JButton();
        btn15 = new javax.swing.JButton();
        btn16 = new javax.swing.JButton();
        btn17 = new javax.swing.JButton();
        btn18 = new javax.swing.JButton();
        btn19 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        btn22 = new javax.swing.JButton();
        btn23 = new javax.swing.JButton();
        btn24 = new javax.swing.JButton();
        btn25 = new javax.swing.JButton();
        pnlOption = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMoveCount = new javax.swing.JTextField();
        txtElapsed = new javax.swing.JTextField();
        cboSize = new javax.swing.JComboBox<>();
        btnNewGame = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(300, 400));
        setMinimumSize(new java.awt.Dimension(300, 400));
        setPreferredSize(new java.awt.Dimension(300, 400));

        pnlMain.setMaximumSize(new java.awt.Dimension(200, 200));
        pnlMain.setMinimumSize(new java.awt.Dimension(200, 200));
        pnlMain.setPreferredSize(new java.awt.Dimension(200, 200));

        pnl1.setMaximumSize(new java.awt.Dimension(100, 100));
        pnl1.setMinimumSize(new java.awt.Dimension(100, 100));
        pnl1.setPreferredSize(new java.awt.Dimension(200, 200));
        pnl1.setLayout(new java.awt.GridLayout(3, 3));

        btn1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn1.setMaximumSize(new java.awt.Dimension(40, 40));
        btn1.setMinimumSize(new java.awt.Dimension(40, 40));
        btn1.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl1.add(btn1);

        btn2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        pnl1.add(btn2);

        btn3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        pnl1.add(btn3);

        btn4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        pnl1.add(btn4);

        btn5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        pnl1.add(btn5);

        btn6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        pnl1.add(btn6);

        btn7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        pnl1.add(btn7);

        btn8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        pnl1.add(btn8);

        btn9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        pnl1.add(btn9);

        pnl2.setMaximumSize(new java.awt.Dimension(100, 100));
        pnl2.setMinimumSize(new java.awt.Dimension(100, 100));
        pnl2.setPreferredSize(new java.awt.Dimension(200, 200));
        pnl2.setLayout(new java.awt.GridLayout(4, 4));

        btn10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn10.setMaximumSize(new java.awt.Dimension(40, 40));
        btn10.setMinimumSize(new java.awt.Dimension(40, 40));
        btn10.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn10);

        btn11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn11.setMaximumSize(new java.awt.Dimension(40, 40));
        btn11.setMinimumSize(new java.awt.Dimension(40, 40));
        btn11.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn11);

        btn12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn12.setMaximumSize(new java.awt.Dimension(40, 40));
        btn12.setMinimumSize(new java.awt.Dimension(40, 40));
        btn12.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn12);

        btn13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn13.setMaximumSize(new java.awt.Dimension(40, 40));
        btn13.setMinimumSize(new java.awt.Dimension(40, 40));
        btn13.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn13);

        btn14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn14.setMaximumSize(new java.awt.Dimension(40, 40));
        btn14.setMinimumSize(new java.awt.Dimension(40, 40));
        btn14.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn14);

        btn15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn15.setMaximumSize(new java.awt.Dimension(40, 40));
        btn15.setMinimumSize(new java.awt.Dimension(40, 40));
        btn15.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn15);

        btn16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn16.setMaximumSize(new java.awt.Dimension(40, 40));
        btn16.setMinimumSize(new java.awt.Dimension(40, 40));
        btn16.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn16);

        btn17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn17.setMaximumSize(new java.awt.Dimension(40, 40));
        btn17.setMinimumSize(new java.awt.Dimension(40, 40));
        btn17.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn17);

        btn18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn18.setMaximumSize(new java.awt.Dimension(40, 40));
        btn18.setMinimumSize(new java.awt.Dimension(40, 40));
        btn18.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn18);

        btn19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn19.setMaximumSize(new java.awt.Dimension(40, 40));
        btn19.setMinimumSize(new java.awt.Dimension(40, 40));
        btn19.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn19);

        btn20.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn20.setMaximumSize(new java.awt.Dimension(40, 40));
        btn20.setMinimumSize(new java.awt.Dimension(40, 40));
        btn20.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn20);

        btn21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn21.setMaximumSize(new java.awt.Dimension(40, 40));
        btn21.setMinimumSize(new java.awt.Dimension(40, 40));
        btn21.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn21);

        btn22.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn22.setMaximumSize(new java.awt.Dimension(40, 40));
        btn22.setMinimumSize(new java.awt.Dimension(40, 40));
        btn22.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn22);

        btn23.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn23.setMaximumSize(new java.awt.Dimension(40, 40));
        btn23.setMinimumSize(new java.awt.Dimension(40, 40));
        btn23.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn23);

        btn24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn24.setMaximumSize(new java.awt.Dimension(40, 40));
        btn24.setMinimumSize(new java.awt.Dimension(40, 40));
        btn24.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn24);

        btn25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn25.setMaximumSize(new java.awt.Dimension(40, 40));
        btn25.setMinimumSize(new java.awt.Dimension(40, 40));
        btn25.setPreferredSize(new java.awt.Dimension(40, 40));
        pnl2.add(btn25);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnl2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlOption.setMaximumSize(new java.awt.Dimension(200, 200));
        pnlOption.setMinimumSize(new java.awt.Dimension(200, 200));
        pnlOption.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel1.setText("Move count:");

        jLabel2.setText("Elapsed:");

        jLabel3.setText("Size:");

        txtMoveCount.setEditable(false);

        txtElapsed.setEditable(false);

        cboSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3x3", "4x4" }));
        cboSize.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSizeItemStateChanged(evt);
            }
        });
        cboSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboSizeMouseClicked(evt);
            }
        });

        btnNewGame.setText("New Game");

        javax.swing.GroupLayout pnlOptionLayout = new javax.swing.GroupLayout(pnlOption);
        pnlOption.setLayout(pnlOptionLayout);
        pnlOptionLayout.setHorizontalGroup(
            pnlOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOptionLayout.createSequentialGroup()
                .addGroup(pnlOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnNewGame, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlOptionLayout.createSequentialGroup()
                        .addGroup(pnlOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMoveCount)
                            .addComponent(txtElapsed, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(cboSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlOptionLayout.setVerticalGroup(
            pnlOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOptionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMoveCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtElapsed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNewGame)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlOption, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboSizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSizeItemStateChanged
        CardLayout cards = (CardLayout) pnlMain.getLayout();
        int pos = cboSize.getSelectedIndex();
        String details = cboSize.getItemAt(pos);
        cards.show(pnlMain, details);
        if (details.equals("3x3")) {
            startGame = false; // stop thread clock
            newGame = false; // don't press before play game
            txtElapsed.setText("");
            txtMoveCount.setText("");
            btn10.setText("");
            btn11.setText("");
            btn12.setText("");
            btn13.setText("");
            btn14.setText("");
            btn15.setText("");
            btn16.setText("");
            btn17.setText("");
            btn18.setText("");
            btn19.setText("");
            btn20.setText("");
            btn21.setText("");
            btn22.setText("");
            btn23.setText("");
            btn24.setText("");
            btn25.setText("");
        } else {
            startGame = false; //stop thread clock
            newGame = false; // don't press before play game
            txtElapsed.setText("");
            txtMoveCount.setText("");
            btn1.setText("");
            btn2.setText("");
            btn3.setText("");
            btn4.setText("");
            btn5.setText("");
            btn6.setText("");
            btn7.setText("");
            btn8.setText("");
            btn9.setText("");
        }
    }//GEN-LAST:event_cboSizeItemStateChanged

    private void cboSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboSizeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSizeMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Puzzle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Puzzle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Puzzle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Puzzle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Puzzle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn13;
    private javax.swing.JButton btn14;
    private javax.swing.JButton btn15;
    private javax.swing.JButton btn16;
    private javax.swing.JButton btn17;
    private javax.swing.JButton btn18;
    private javax.swing.JButton btn19;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn20;
    private javax.swing.JButton btn21;
    private javax.swing.JButton btn22;
    private javax.swing.JButton btn23;
    private javax.swing.JButton btn24;
    private javax.swing.JButton btn25;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnNewGame;
    private javax.swing.JComboBox<String> cboSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlOption;
    private javax.swing.JTextField txtElapsed;
    private javax.swing.JTextField txtMoveCount;
    // End of variables declaration//GEN-END:variables

}
