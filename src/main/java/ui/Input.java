package ui;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.function.Consumer;

import javax.swing.*;

import global.Styles;
import util.StyleUtil;

/**
 * Ein gestyltes Inputfield, mit Default-Styles
 * und optional per Lambda noch überschreibbare Styles zulässt.
 */
public class Input extends JTextField {
    private String placeholder;

    private Input(String placeholder, int columns) {
        super(columns);
        setPlaceholder(placeholder);
        initStyle();
        initPlaceholderListener();
    }


    /**
     * Default-Styling.
     */
    private void initStyle() {
        StyleUtil.apply(this)
            .font(Styles.INPUT_FONT)              
            .foreground(Styles.TEXT_COL)          
            .border(Styles.COMPONENT_BORDER)      
            .cursor(Styles.DEFAULT_CURSOR);      

        setCaretColor(Styles.TEXT_COL);
    }
    
    /**
     * Erzeugt ein Inputfield mit Default-Style.
     *
     * @param text        Placeholder des Inputfields
     */
    public static Input create(String text, int columns) {
        return new Input(text, columns);
    }
    
    /**
     * Erzeugt ein Inputfield mit Default-Style und wendet anschließend
     * customStyle auf den Style-Builder an.
     *
     * @param text        Placeholder des Inputfields
     * @param customStyle ein Consumer, der auf den StyleUtil.Chain zugreift
     */
    public static Input create(String text, int columns, Consumer<StyleUtil.Chain> customStyle) {
        Input b = new Input(text, columns);
        StyleUtil.Chain chain = StyleUtil.apply(b);
        customStyle.accept(chain);
        return b;
    }

    private void initPlaceholderListener() {
        addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { repaint(); }
            @Override public void focusLost(FocusEvent e)   { repaint(); }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getText().isEmpty() && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            g2.setColor(Styles.PLACEHOLDER_COL);
            FontMetrics fm = g2.getFontMetrics();
            int y = (getHeight() - fm.getHeight())/2 + fm.getAscent();
            g2.drawString(placeholder, getInsets().left, y);
            g2.dispose();
        }
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }
}
