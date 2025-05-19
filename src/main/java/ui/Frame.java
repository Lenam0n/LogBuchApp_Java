package ui;

import javax.swing.*;

import global.Styles;

import java.awt.*;

/**
 * Ein wiederverwendbarer Frame, der ein GridBagLayout nutzt
 * und in den man per Fluent-API Komponenten hinzufügen kann.
 */
public abstract class Frame extends JFrame {

    protected final JPanel panel;
    protected final GridBagConstraints gbc;

    /**
     * Grundkonfiguration des Frames (Titel, Größe, Zentrierung).
     *
     * @param title  Fenstertitel
     * @param width  Breite in Pixel
     * @param height Höhe in Pixel
     */
    protected Frame(String title, int width, int height) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = Styles.PADDING;
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        setContentPane(panel);
    }

    /**
     * Fügt eine Komponente an der angegebenen GridBag-Position hinzu
     * und gibt den Frame zurück.
     *
     * @param gridx     Spalten-Index
     * @param gridy     Zeilen-Index
     * @param gridwidth Wie viele Spalten soll die Komponente überspannen?
     * @param comp      die Swing-Komponente
     */
    public Frame add(int gridx, int gridy, int gridwidth, JComponent comp) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.weightx = (gridwidth > 1 ? 1.0 : 0.0);
        panel.add(comp, gbc);
        return this;
    }
    
    /** Holt das interne Content‐Panel, um dynamisch Komponenten zu verwalten. */
    public JPanel getContentPanel() {
        return panel;
    }

    /**
     * Nachträglich das Layout neu anordnen und neu zeichnen.
     * Sollst du aufrufen, wenn du zur Laufzeit Komponenten hinzufügst.
     */
    public void refresh() {
        panel.revalidate();
        panel.repaint();
    }
}
