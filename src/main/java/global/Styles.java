package global;

import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.*;

/**
 * Zentrale Styling-Konstanten für die gesamte Anwendung.
 */
public class Styles {

	// --- Defaults -----------------------------------------------------------
	
	private static final String DEFAULT_FONT = "SansSerif";
	private static final int DEFAULT_FONT_SIZE = 13;
	
    // --- Fonts -----------------------------------------------------------

	
    /** Font für Labels und statische Texte */
    public static final Font LABEL_FONT = new Font(DEFAULT_FONT, Font.PLAIN, 14);

    /** Font für Eingabefelder (TextField, ComboBox) */
    public static final Font INPUT_FONT = new Font(DEFAULT_FONT, Font.PLAIN, DEFAULT_FONT_SIZE);

    /** Font für Buttons (bold, etwas größer) */
    public static final Font BUTTON_FONT = INPUT_FONT.deriveFont(Font.BOLD, 14f);


    // --- Colors ----------------------------------------------------------

    /** Standard-Textfarbe */
    public static final Color TEXT_COL = Color.DARK_GRAY;

    /** Farbe für Placeholder-Texte in Eingabefeldern */
    public static final Color PLACEHOLDER_COL = Color.GRAY;

    /** Hintergrundfarbe für Buttons */
    public static final Color BUTTON_BG = new Color(33, 150, 243);

    /** Schriftfarbe für Buttons */
    public static final Color BUTTON_FG = Color.WHITE;


    // --- Insets & Borders ------------------------------------------------

    /** Standard-Insets für Komponenten-Abstände */
    public static final Insets PADDING = new Insets(5, 5, 5, 5);

    /**
     * Standard-Border für Komponenten (z.B. Buttons, Labels, TextFields).
     * Fügt zusätzliches Padding um den Inhalt herum ein.
     */
    public static final Border COMPONENT_BORDER =
        BorderFactory.createEmptyBorder(
            PADDING.top,
            PADDING.left * 2,
            PADDING.bottom,
            PADDING.right * 2
        );


    // --- Cursors ----------------------------------------------------------

    /** Hand-Cursor für interaktive Komponenten */
    public static final Cursor HAND_CURSOR =
        Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

    /** Standard-Cursor */
    public static final Cursor DEFAULT_CURSOR =
        Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
}
