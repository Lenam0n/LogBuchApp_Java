package ui;

import java.awt.Font;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import util.StyleUtil;
import global.Styles;

/**
 * Ein Title-Label, abgeleitet von Label, das einen
 * größeren, fetten Font nutzt und optional per Lambda
 * weiter angepasst werden kann.
 */
public class Title extends Label {

    private static final String DEFAULT_TEXT = "Titel";

    /**
     * Privater Konstruktor, der nach dem Basis-Label-Styling
     * noch das Title-spezifische Styling aufträgt.
     */
    private Title(String text) {
        super(text);
        initTitleStyle();
    }

    /**
     * Default Styling
     */
    private void initTitleStyle() {
        setHorizontalAlignment(SwingConstants.CENTER);
        StyleUtil.apply(this)
            .font(Styles.LABEL_FONT.deriveFont(Font.BOLD, Styles.LABEL_FONT.getSize() + 4f))
            .border(BorderFactory.createEmptyBorder(
                Styles.PADDING.top,
                Styles.PADDING.left,
                Styles.PADDING.bottom * 2,
                Styles.PADDING.right
            ));
    }

    /**
     * Erzeugt ein TitleLabel mit Default-Text.
     */
    public static Title create() {
        return new Title(DEFAULT_TEXT);
    }

    /**
     * Erzeugt ein TitleLabel mit benutzerdefiniertem Text.
     *
     * @param text Der anzuzeigende Titel
     */
    public static Title create(String text) {
        return new Title(text);
    }

    /**
     * Erzeugt ein TitleLabel mit benutzerdefiniertem Text
     * und wendet anschließend customStyle auf den Style-Builder an.
     *
     * @param text        Der anzuzeigende Titel
     * @param customStyle Ein Consumer, der auf den StyleUtil.Chain zugreift
     */
    public static Title create(String text, Consumer<StyleUtil.Chain> customStyle) {
    	Title t = new Title(text);
        customStyle.accept(StyleUtil.apply(t));
        return t;
    }
}
