package game.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;

    public MainFrame(JPanel cards){
        setTitle("*** WORD OF TANKS ***");
        setLocation(500, 100);
        setMinimumSize(new Dimension(576, 576+22+22));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());
        getContentPane().add(cards, BorderLayout.CENTER);
        pack();
        setVisible(true);
        toFront();
    }

    private JMenuBar createMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);

        menu = new JMenu("Game");
        addMenuItem("Start", KeyEvent.VK_1);
        addMenuItem("Pause", KeyEvent.VK_2);
        addMenuItem("Quit", KeyEvent.VK_Q);

        menu = new JMenu("?");
        addMenuItem("About", KeyEvent.VK_A);

        return menuBar;
    }

    private void addMenuItem(String command, int keyEvent){
        menuItem = new JMenuItem(command);
        menuItem.setMnemonic(keyEvent);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(keyEvent, ActionEvent.ALT_MASK));
        menuItem.setActionCommand(command.toLowerCase());
        menu.add(menuItem);
        menuBar.add(menu);
    }

}
