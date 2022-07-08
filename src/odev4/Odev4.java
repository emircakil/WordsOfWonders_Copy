package odev4;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;
import javax.swing.*;

public class Odev4 extends JPanel implements MouseMotionListener, MouseListener {

    BufferedImage backgroundImage;
    BufferedImage squareImage;
    BufferedImage circleGroupImage;
    File backgroundFile;
    File squareFile;
    File circleGroupFile;
    Font font;
    int ySporcu[];
    int xPuro[];
    int xRus[];
    int ySur[];
    int xSoru[];
    int yPus[];
    int dragMouseX, dragMouseY;
    int startMouseX, startMouseY;
    int mouseX, mouseY;
    int point;
    int lineX[];
    int lineY[];
    int counter;
    int showLineX[];
    int showLineY[];
    int finishCounter;
    String word;
    String sporcu;
    String puro;
    String pus;
    String rus;
    String sur;
    String soru;
    String pointString;
    static int width, height;
    boolean letterCondition[];
    boolean sporcuCondition;
    boolean puroCondition;
    boolean pusCondition;
    boolean rusCondition;
    boolean surCondition;
    boolean soruCondition;
    boolean closeLine;
    boolean gameOver;

    Odev4() {
        addMouseMotionListener(this);
        addMouseListener(this);

        backgroundFile = new File("background.png");
        squareFile = new File("square.png");
        circleGroupFile = new File("circleGroup.png");
        word = "";
        width = 450;
        height = 900;
        counter = 0;
        point = 0;
        finishCounter = 0;
        letterCondition = new boolean[6];
        ySporcu = new int[6];
        xPuro = new int[4];
        xRus = new int[3];
        ySur = new int[3];
        xSoru = new int[4];
        yPus = new int[3];
        lineX = new int[6];
        lineY = new int[6];
        showLineX = new int[6];
        showLineY = new int[6];
        sporcu = "SPORCU";
        puro = "PURO";
        pus = "PUS";
        rus = "RUS";
        sur = "SUR";
        soru = "SORU";
        pointString = "Puan: 0";

        lineX[0] = 220;
        lineX[1] = 105;
        lineX[2] = 105;
        lineX[3] = 345;
        lineX[4] = 345;
        lineX[5] = 220;

        lineY[0] = 500;
        lineY[1] = 570;
        lineY[2] = 720;
        lineY[3] = 570;
        lineY[4] = 720;
        lineY[5] = 790;
        try {

            backgroundImage = ImageIO.read(backgroundFile);
            squareImage = ImageIO.read(squareFile);
            circleGroupImage = ImageIO.read(circleGroupFile);

        } catch (Exception e) {

            System.out.println("Trying for take image from file -" + e.getMessage());
        }

        for (int i = 0, j = 10; i < ySporcu.length; i++, j += 55) {
            ySporcu[i] = j;
        }
        for (int i = 0, j = 45; i < xPuro.length; i++, j += 55) {

            xPuro[i] = j;

        }
        for (int i = 0, j = 210; i < xRus.length; i++, j += 55) {

            xRus[i] = j;

        }
        for (int i = 0, j = 175; i < ySur.length; i++, j += 55) {
            ySur[i] = j;
        }
        for (int i = 0, j = 45; i < xSoru.length; i++, j += 55) {
            xSoru[i] = j;
        }
        for (int i = 0, j = 65; i < yPus.length; i++, j += 55) {
            yPus[i] = j;
        }
    }

    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(backgroundImage, 0, 0, this);
        g2.drawImage(circleGroupImage, -25, 400, this);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Serif", Font.BOLD, 30));

        for (int i = 0; i < ySporcu.length; i++) {
            g2.drawImage(squareImage, 210, ySporcu[i], this);
        }
        for (int i = 0; i < xPuro.length; i++) {
            g2.drawImage(squareImage, xPuro[i], 120, this);
        }
        for (int i = 0; i < xRus.length; i++) {
            g2.drawImage(squareImage, xRus[i], 175, this);
        }
        for (int i = 0; i < ySur.length; i++) {
            g2.drawImage(squareImage, 320, ySur[i], this);
        }
        for (int i = 0; i < xSoru.length; i++) {
            g2.drawImage(squareImage, xSoru[i], 285, this);
        }
        for (int i = 0; i < yPus.length; i++) {
            g2.drawImage(squareImage, 100, yPus[i], this);
        }

        if (sporcuCondition) {

            for (int i = 0; i < sporcu.length(); i++) {

                g2.drawString(sporcu.substring(i, i + 1), 235, ySporcu[i] + 50);
            }
        }
        if (puroCondition) {

            for (int i = 0; i < puro.length(); i++) {
                g2.drawString(puro.substring(i, i + 1), xPuro[i] + 25, 170);
            }
        }
        if (pusCondition) {

            for (int i = 0; i < yPus.length; i++) {
                g2.drawString(pus.substring(i, i + 1), 125, yPus[i] + 50);
            }

        }
        if (rusCondition) {

            for (int i = 0; i < xRus.length; i++) {

                g2.drawString(rus.substring(i, i + 1), xRus[i] + 25, 225);
            }
        }
        if (surCondition) {
            for (int i = 0; i < ySur.length; i++) {
                g2.drawString(sur.substring(i, i + 1), 345, ySur[i] + 50);
            }

        }
        if (soruCondition) {

            for (int i = 0; i < xSoru.length; i++) {
                g2.drawString(soru.substring(i, i + 1), xSoru[i] + 25, 335);
            }
        }
        if (letterCondition[0]) {

            g2.setColor(Color.WHITE);
            g2.fillOval(198, 480, 50, 50);
        } else {
            g2.setColor(Color.CYAN);
            g2.fillOval(198, 480, 50, 50);
        }

        if (letterCondition[1]) {
            g2.setColor(Color.WHITE);
            g2.fillOval(80, 550, 50, 50);
        } else {
            g2.setColor(Color.CYAN);
            g2.fillOval(80, 550, 50, 50);
        }

        if (letterCondition[2]) {
            g2.setColor(Color.WHITE);
            g2.fillOval(80, 700, 50, 50);
        } else {
            g2.setColor(Color.CYAN);
            g2.fillOval(80, 700, 50, 50);
        }

        if (letterCondition[3]) {
            g2.setColor(Color.WHITE);
            g2.fillOval(320, 550, 50, 50);

        } else {
            g2.setColor(Color.CYAN);
            g2.fillOval(320, 550, 50, 50);

        }

        if (letterCondition[4]) {
            g2.setColor(Color.WHITE);
            g2.fillOval(320, 700, 50, 50);

        } else {
            g2.setColor(Color.CYAN);
            g2.fillOval(320, 700, 50, 50);

        }
        if (letterCondition[5]) {
            g2.setColor(Color.WHITE);
            g2.fillOval(198, 770, 50, 50);

        } else {
            g2.setColor(Color.CYAN);
            g2.fillOval(198, 770, 50, 50);
        }

        g2.setColor(Color.white);
        g2.setStroke((new BasicStroke(5)));

        if (closeLine) {

            g.drawLine(startMouseX, startMouseY, dragMouseX, dragMouseY);

        }

        g2.drawOval(198, 480, 50, 50);
        g2.drawOval(80, 550, 50, 50);
        g2.drawOval(80, 700, 50, 50);
        g2.drawOval(320, 550, 50, 50);
        g2.drawOval(320, 700, 50, 50);
        g2.drawOval(198, 770, 50, 50);

        g2.setColor(Color.PINK);
        g2.drawString("R", 210, 515);
        g2.drawString("C", 92, 585);
        g2.drawString("S", 92, 735);
        g2.drawString("P", 332, 585);
        g2.drawString("U", 332, 735);
        g2.drawString("O", 210, 805);

        g2.setFont(new Font("Serif", Font.BOLD, 40));
        g2.setColor(Color.WHITE);
        if (finishCounter == 6) {

            g2.setColor(Color.PINK);
            g2.setFont(new Font("Serif", Font.BOLD, 20));

            g2.drawString("Tebrikler! Oyun Bitti. Puaniniz: " + String.valueOf(point), width / 2 - 190, height / 2 - 40);
            gameOver = true;
        } else {

            g2.drawString(word, width / 2 - 85, height / 2 - 40);

        }

        for (int i = 0; i < 5; i++) {

            if (showLineX[i + 1] != 0 && showLineY[i + 1] != 0) {
                g2.drawLine(showLineX[i], showLineY[i], showLineX[i + 1], showLineY[i + 1]);
            }
        }

        g2.setFont(new Font("Serif", Font.BOLD, 20));
        pointString = "Puan: " + String.valueOf(point);
        g2.drawString(pointString, 340, 50);

    }

    public static void main(String[] args) {

        Odev4 panel = new Odev4();
        JFrame jf = new JFrame();

        jf.add(panel);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //jf.setLocationRelativeTo(null); ortadan baslamiyordu
        jf.setSize(width, height);
        jf.setVisible(true);
    }

    @Override
    public void mouseDragged(MouseEvent me) {

        if (me.getX() >= 198 && me.getX() <= 248 && me.getY() >= 480 && me.getY() <= 530 && letterCondition[0] == false) {

            word += "R";

            letterCondition[0] = true;
            closeLine = true;

            showLineX[counter] = lineX[0];
            showLineY[counter] = lineY[0];
            counter++;
            startMouseX = lineX[0];
            startMouseY = lineY[0];

        }

        if (me.getX() >= 80 && me.getX() <= 130 && me.getY() >= 550 && me.getY() <= 600 && letterCondition[1] == false) {

            word += "C";

            letterCondition[1] = true;
            closeLine = true;

            showLineX[counter] = lineX[1];
            showLineY[counter] = lineY[1];
            counter++;
            startMouseX = lineX[1];
            startMouseY = lineY[1];
        }

        if (me.getX() >= 80 && me.getX() <= 130 && me.getY() >= 700 && me.getY() <= 750 && letterCondition[2] == false) {

            word += "S";

            letterCondition[2] = true;
            closeLine = true;

            showLineX[counter] = lineX[2];
            showLineY[counter] = lineY[2];
            counter++;
            startMouseX = lineX[2];
            startMouseY = lineY[2];

        }

        if (me.getX() >= 320 && me.getX() <= 370 && me.getY() >= 550 && me.getY() <= 600 && letterCondition[3] == false) {

            word += "P";

            letterCondition[3] = true;
            closeLine = true;

            showLineX[counter] = lineX[3];
            showLineY[counter] = lineY[3];
            counter++;
            startMouseX = lineX[3];
            startMouseY = lineY[3];
        }

        if (me.getX() >= 320 && me.getX() <= 370 && me.getY() >= 700 && me.getY() <= 750 && letterCondition[4] == false) {

            word += "U";

            letterCondition[4] = true;
            closeLine = true;

            showLineX[counter] = lineX[4];
            showLineY[counter] = lineY[4];
            counter++;
            startMouseX = lineX[4];
            startMouseY = lineY[4];
        }

        if (me.getX() >= 198 && me.getX() <= 248 && me.getY() >= 770 && me.getY() <= 820 && letterCondition[5] == false) {

            word += "O";

            letterCondition[5] = true;
            closeLine = true;

            showLineX[counter] = lineX[5];
            showLineY[counter] = lineY[5];
            counter++;
            startMouseX = lineX[5];
            startMouseY = lineY[5];
        }
        dragMouseX = me.getX();
        dragMouseY = me.getY();

        if (gameOver == false) {
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {

    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {

        startMouseX = me.getX();
        startMouseY = me.getY();
    }

    @Override
    public void mouseReleased(MouseEvent me) {

        if (gameOver == false) {
            if (word.equals(sporcu)) {

                if (sporcuCondition == false) {

                    finishCounter++;
                }
                sporcuCondition = true;
                point += 10;

            } else if (word.equals(puro)) {

                if (puroCondition == false) {

                    finishCounter++;
                }

                puroCondition = true;
                point += 10;
            } else if (word.equals(pus)) {

                if (pusCondition == false) {

                    finishCounter++;
                }

                pusCondition = true;
                point += 10;
            } else if (word.equals(rus)) {

                if (rusCondition == false) {

                    finishCounter++;
                }
                rusCondition = true;
                point += 10;
            } else if (word.equals(sur)) {

                if (surCondition == false) {

                    finishCounter++;
                }

                surCondition = true;
                point += 10;
            } else if (word.equals(soru)) {

                if (soruCondition == false) {

                    finishCounter++;
                }
                soruCondition = true;
                point += 10;
            } else {
                point -= 5;
            }

            for (int i = 0; i < letterCondition.length; i++) {

                letterCondition[i] = false;
            }
            for (int i = 0; i < 6; i++) {
                showLineX[i] = 0;
                showLineY[i] = 0;
            }

            closeLine = false;
            counter = 0;
            word = "";

            if (gameOver == false) {
                repaint();
            }

        }
    }


@Override
public void mouseEntered(MouseEvent me) {

    }

    @Override
public void mouseExited(MouseEvent e) {
    }

}
