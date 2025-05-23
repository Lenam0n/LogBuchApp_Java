// src/main/java/ui/Panel.java
package ui;

import javax.swing.*;
import javax.swing.border.Border;

import global.Styles;

import java.awt.*;
import java.util.function.Consumer;
import util.StyleUtil;

/**
 * Gestyltes JPanel mit Default-Padding und Cursor,
 * plus optionalem Fluent-Override.
 */
public class Panel extends JPanel {

    /** Default-Border basierend auf Styles.PADDING */
    private static final Border DEFAULT_BORDER =
        BorderFactory.createEmptyBorder(
            Styles.PADDING.top,
            Styles.PADDING.left,
            Styles.PADDING.bottom,
            Styles.PADDING.right
        );

    // privater Konstruktor, dass nur factory-Methoden genutzt werden
    private Panel(LayoutManager layout) {
        super(layout);
        initStyle();
    }

    // wendet Default-Styling an
    private void initStyle() {
        StyleUtil.apply(this)
            .border(DEFAULT_BORDER)
            .cursor(Styles.DEFAULT_CURSOR);
    }

    /**
     * Factory-Methode: neues Panel mit dem angegebenen Layout.
     */
    public static Panel create(LayoutManager layout) {
        return new Panel(layout);
    }

    /**
     * Factory-Methode mit anschließendem Fluent-Override.
     *
     * @param layout      das Layout (z.B. new BorderLayout(...))
     * @param customStyle Lambda, um per StyleUtil chain weitere Styles zu setzen
     */
    public static Panel create(
        LayoutManager layout,
        Consumer<StyleUtil.Chain> customStyle
    ) {
        Panel p = new Panel(layout);
        customStyle.accept(StyleUtil.apply(p));
        return p;
    }
}
