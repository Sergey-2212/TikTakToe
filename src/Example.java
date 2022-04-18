import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example {
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;
    private static final int WINDOW_Y_POSITION = 300;
    private static final int WINDOW_X_POSITION = 400;

    public static void main(String[] args) {
        new ExampleWindow();
    }

    public static class ExampleWindow extends JFrame {
        public int counter = 0;
        public ExampleWindow() {
            setTitle("Game");
            setBounds(WINDOW_X_POSITION, WINDOW_Y_POSITION, WINDOW_WIDTH, WINDOW_HEIGHT);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            JButton button1 = new JButton("button#1");
            JButton button2 = new JButton("Возможно это кнопка");
            //add(button1, BorderLayout.NORTH);
            //add(button2, BorderLayout.SOUTH);
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            panel.add(button1);
            panel.add(button2);
            add(panel, BorderLayout.SOUTH);

            JMenuBar bar = new JMenuBar();
            JMenu file = new JMenu("File");
            JMenu help = new JMenu("Help");
            JMenu settings = new JMenu("Settings");
            JMenuItem exit = new JMenuItem("Exit");


            JMenuItem open = new JMenuItem("Open");
            JMenuItem save = new JMenuItem("Save");
            bar.add(file);
            bar.add(help);
            bar.add(settings);

            file.add(open);
            file.add(save);
            file.add(exit);
            add(bar, BorderLayout.NORTH);
            JLabel label = new JLabel();
            label.setFont(new Font("Arial", Font.BOLD, 16));
            add(label);
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(1);
                }
            });

            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    counter++;
                    label.setText("Counter = " + counter);
                }
            });

            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    counter--;
                    label.setText("Counter = " + counter);
                }
            });
            setVisible(true);
        }
    }
}
