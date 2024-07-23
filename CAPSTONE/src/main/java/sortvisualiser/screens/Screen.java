package src.main.java.sortvisualiser.screens;

import static src.main.java.sortvisualiser.MainApp.WIN_HEIGHT;
import static src.main.java.sortvisualiser.MainApp.WIN_WIDTH;

import java.awt.Dimension;
import javax.swing.JPanel;
import src.main.java.sortvisualiser.MainApp;


public abstract class Screen extends JPanel {
    protected MainApp app;

    public Screen(MainApp app) {
        this.app = app;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIN_WIDTH, WIN_HEIGHT);
    }

    /**
     * Chamar quando a screen for aberta. Subclasses tem que dar overided nesse metodo
     */
    public abstract void onOpen();
}
