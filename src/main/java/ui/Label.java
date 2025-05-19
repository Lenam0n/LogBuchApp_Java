package ui;

import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import global.Styles;
import util.StyleUtil;

/**
 * Ein gestyltes Label, mit Default-Styles
 * und optional per Lambda noch überschreibbare Styles zulässt.
 */
public class Label extends JLabel {
    protected Label(String text) {
        super(text);
        initStyle();
    }

    /**
     * Default-Styling.
     */
    private void initStyle() {
        StyleUtil.apply(this)
            .font(Styles.LABEL_FONT);
    }

    /**
     * Erzeugt ein Label mit Default-Style.
     *
     * @param text        Text des Labels
     */
    public static Label create(String text) {
        return new Label(text);
    }

    /**
     * Erzeugt eine Label mit Default-Style und wendet anschließend
     * customStyle auf den Style-Builder an.
     *
     * @param text        Label des Labels
     * @param customStyle ein Consumer, der auf den StyleUtil.Chain zugreift
     */
    public static Label create(String text, Consumer<StyleUtil.Chain> customStyle) {
        Label l = new Label(text);
        StyleUtil.Chain chain = StyleUtil.apply(l);
        customStyle.accept(chain);
        return l;
    }
}
