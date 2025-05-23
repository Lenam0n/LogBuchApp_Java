package util;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Utility zum flexiblen Anwenden von Styles auf jede JComponent
 * über eine Fluent-API, die sofort wirkt.
 */
public class StyleUtil {

    private StyleUtil() { /* nicht instanziierbar */ }

    public static Chain apply(JComponent comp) {
        return new Chain(comp);
    }

    public static class Chain {
        private final JComponent comp;

        private Chain(JComponent comp) {
            this.comp = comp;
        }

        public Chain font(Font f) {
            if (f != null) comp.setFont(f);
            return this;
        }

        public Chain foreground(Color c) {
            if (c != null) comp.setForeground(c);
            return this;
        }

        public Chain background(Color c) {
            if (c != null) {
                comp.setOpaque(true);
                comp.setBackground(c);
            }
            return this;
        }

        public Chain border(Border b) {
            if (b != null) comp.setBorder(b);
            return this;
        }

        public Chain cursor(Cursor c) {
            if (c != null) comp.setCursor(c);
            return this;
        }

        /** Liefert die Komponente zurück, falls Du sie direkt weiterverwenden willst */
        public <T extends JComponent> T get() {
            @SuppressWarnings("unchecked")
            T t = (T) comp;
            return t;
        }
        
        public Chain let(Consumer<Chain> fn) {
            fn.accept(this);
            return this;
        }
    }
}
