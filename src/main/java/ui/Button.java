package ui;

import javax.swing.*;
import global.Styles;
import util.StyleUtil;
import java.util.function.Consumer;

/**
 * Ein gestylter Button, mit Default-Styles
 * und optional per Lambda noch überschreibbare Styles zulässt.
 */
public class Button extends JButton {
    private Button(String text) {
        super(text);
        initStyle();
    }

    /**
     * Default-Styling.
     */
    private void initStyle() {
        StyleUtil.apply(this)
            .font(Styles.BUTTON_FONT)
            .foreground(Styles.BUTTON_FG)
            .background(Styles.BUTTON_BG)
            .border(Styles.COMPONENT_BORDER)
            .cursor(Styles.HAND_CURSOR);
        setFocusPainted(false);
    }

    /**
     * Erzeugt einen Button mit Default-Style.
     *
     * @param text        Label des Buttons
     */
    public static Button create(String text) {
        return new Button(text);
    }

    /**
     * Erzeugt einen Button mit Default-Style und wendet anschließend
     * customStyle auf den Style-Builder an.
     *
     * @param text        Label des Buttons
     * @param customStyle ein Consumer, der auf den StyleUtil.Chain zugreift
     */
    public static Button create(String text, Consumer<StyleUtil.Chain> customStyle) {
        Button b = new Button(text);
        StyleUtil.Chain chain = StyleUtil.apply(b);
        customStyle.accept(chain);
        return b;
    }
}
