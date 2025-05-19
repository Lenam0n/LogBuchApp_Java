package ui;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * Fabrik für Trennlinien.
 */
public class Separator {

    private Separator() { /* Utility-Klasse */ }

    /**
     * @return horizontale Trennlinie
     */
    public static JSeparator createHorizontal() {
        return new JSeparator(SwingConstants.HORIZONTAL);
    }

    /**
     * @return vertikale Trennlinie
     */
    public static JSeparator createVertical() {
        return new JSeparator(SwingConstants.VERTICAL);
    }
}

