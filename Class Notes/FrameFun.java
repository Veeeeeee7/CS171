import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class FrameFun extends JFrame {
    Canvas canvas;
    JPanel mainPanel;
    JPanel buttonPanel;
    JButton button1, button2, button3;
    Point2D.Double ballPos;
    Color ballColor;
    Timer timer;

    class Canvas extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.fillOval((int) ballPos.x, (int) ballPos.y, 10, 10);
        }
    }

    class Button2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button 2 was clicked!");
            canvas.setBackground(Color.yellow);
        }
    }

    public FrameFun() {
        super("FrameFun!");
        setSize(400, 400);
        canvas = new Canvas();
        buttonPanel = new JPanel();

        ballPos = new Point2D.Double(100, 200);

        button1 = new JButton("Btn 1");
        Button1Listener button1Listener = new Button1Listener();
        button1.addActionListener(button1Listener);

        button2 = new JButton("Btn 2");
        Button2Listener button2Listener = new Button2Listener();
        button2.addActionListener(button2Listener);

        button3 = new JButton("Btn 3");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ballColor = Color.red;
                System.out.println("Button 3 was clicked!");
                canvas.repaint();
            }
        });

        this.canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("mouse moved to (" + e.getX() + "," + e.getY() + ")");
                ballPos.x = e.getX();
                ballPos.y = e.getY();
                canvas.repaint();
            }
        });

        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(canvas, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(mainPanel);

        this.canvas.repaint();
    }

    public static void main(String[] args) {
        FrameFun ff = new FrameFun();
        ff.setVisible(true);
    }
}