package ui;

import java.awt.Color;
import java.awt.Font;
import util.StyleUtil;
import global.Styles;

/**
 * Ein Label für Fehlermeldungen, erbt von Label.
 * Zeigt standardmäßig einen Fehler-Text in Rot an.
 */
public class ErrorLabel extends Label {

    private static final String DEFAULT_TEXT = "Ein Fehler ist aufgetreten";

    /**
     * Privater Konstruktor, der nach dem Basis-Label-Styling
     * noch das Error-Style-Override anwendet.
     */
    private ErrorLabel(String text) {
        super(text);
        initErrorStyle();
    }

    /**
     * Setzt die Fehlermeldungs-spezifischen Styles:
     * rote Schriftfarbe und fettere Font.
     */
    private void initErrorStyle() {
        StyleUtil.apply(this)
            .foreground(Color.RED)                                             
            .font(Styles.LABEL_FONT.deriveFont(Font.BOLD, Styles.LABEL_FONT.getSize()))
            .cursor(Styles.DEFAULT_CURSOR);                                     
    }

    /**
     * Factory-Methode mit Default-Fehlermeldung.
     */
    public static ErrorLabel create() {
        return new ErrorLabel(DEFAULT_TEXT);
    }

    /**
     * Factory-Methode mit benutzerdefinierter Fehlermeldung.
     *
     * @param text Der anzuzeigende Fehlertext.
     */
    public static ErrorLabel create(String text) {
        return new ErrorLabel(text);
    }
}
