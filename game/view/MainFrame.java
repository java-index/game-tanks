package game.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItemStart;
    private JMenuItem menuItemStop;
    private JMenuItem menuItemPause;
    private JMenuItem menuItemQuit;
    private JMenuItem menuItemAbout;


    public MainFrame() {
        setTitle("*** WORD OF TANKS ***");
        setLocation(500, 100);
        setMinimumSize(new Dimension(576, 576 + 22));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());
        pack();
        setVisible(true);
        toFront();
    }

    private JMenuBar createMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);

        menu = new JMenu("Game");
        menuItemStart = createMenuItem("Start", KeyEvent.VK_1);
        menuItemStop = createMenuItem("Stop", KeyEvent.VK_2);
        menuItemPause = createMenuItem("Pause", KeyEvent.VK_3);
        menuItemQuit = createMenuItem("Quit", KeyEvent.VK_Q);

        menu = new JMenu("?");
        menuItemAbout = createMenuItem("About", KeyEvent.VK_A);

        return menuBar;
    }

    private JMenuItem createMenuItem(String command, int keyEvent) {
        JMenuItem menuItem = new JMenuItem(command);
        menuItem.setMnemonic(keyEvent);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(keyEvent, ActionEvent.ALT_MASK));
        menuItem.setActionCommand(command.toLowerCase());
        menu.add(menuItem);
        menuBar.add(menu);
        return menuItem;
    }

    public void addFrameMenuListener(ActionListener actionListener) {
        menuItemStart.addActionListener(actionListener);
        menuItemStop.addActionListener(actionListener);
        menuItemPause.addActionListener(actionListener);
        menuItemQuit.addActionListener(actionListener);
        menuItemAbout.addActionListener(actionListener);
    }

    public void setContentPane(JPanel panel){
        getContentPane().add(panel);
        pack();
    }
}
