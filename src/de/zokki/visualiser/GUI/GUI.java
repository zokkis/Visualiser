package de.zokki.visualiser.GUI;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private Panel panel;

    public GUI(String name, int width, int height) {
	super(name);
	setMinimumSize(new Dimension(width, height));
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setFocusable(true);

	panel = new Panel(width, height);
	setContentPane(panel);
	pack();
	setVisible(true);

	addWindowStateListener(new WindowStateListener() {
	    @Override
	    public void windowStateChanged(WindowEvent e) {
		if (e.getNewState() == JFrame.MAXIMIZED_BOTH) {
		    setFullScreen();
		}
	    }
	});

	addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (getExtendedState() == JFrame.MAXIMIZED_BOTH && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
		    setSize(width, height);
		    dispose();
		    setUndecorated(false);
		    setVisible(true);
		} else if (getExtendedState() != JFrame.MAXIMIZED_BOTH && e.getKeyCode() == KeyEvent.VK_F11) {
		    setFullScreen();
		}
	    }
	});

	panel.randomizeColumns();
    }

    private void setFullScreen() {
	setExtendedState(JFrame.MAXIMIZED_BOTH);
	dispose();
	setUndecorated(true);
	setVisible(true);
    }
}
