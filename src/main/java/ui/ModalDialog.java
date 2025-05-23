package ui;

import util.StyleUtil;
import global.Styles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Wiederverwendbarer, modal Dialog, der eine Label-Nachricht
 * und Buttons zeigt, plus Fluent-Builder und zentrales Styling.
 */
public class ModalDialog extends JDialog {

    private final Label messageLabel;
    private final Panel buttonPanel;

    private ModalDialog(Window owner) {
        super(owner, ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Content-Panel mit Padding und weißem Hintergrund
        Panel content = Panel.create(
            new BorderLayout(Styles.PADDING.left, Styles.PADDING.top),
            chain -> chain
                .background(Color.WHITE)
                .border(BorderFactory.createEmptyBorder(
                    Styles.PADDING.top, Styles.PADDING.left,
                    Styles.PADDING.bottom, Styles.PADDING.right
                ))
        );
        setContentPane(content);

        // Message-Label in der Mitte
        messageLabel = Label.create("");
        StyleUtil.apply(messageLabel)
            .font(Styles.LABEL_FONT)
            .foreground(Styles.TEXT_COL)
            .border(null);
        content.add(messageLabel, BorderLayout.CENTER);

        // Button-Panel unten
        buttonPanel = Panel.create(
            new FlowLayout(FlowLayout.CENTER, Styles.PADDING.left, Styles.PADDING.top),
            chain -> chain
                .background(Color.WHITE)
                .border(null)
        );
        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    /** Einstiegspunkt für den Fluent-Builder */
    public static Builder builder(Window owner) {
        return new Builder(owner);
    }

    public static class Builder {
        private final ModalDialog dialog;
        private final Map<String, ActionListener> actions = new LinkedHashMap<>();

        private Builder(Window owner) {
            dialog = new ModalDialog(owner);
        }

        /** Fenstertitel setzen */
        public Builder title(String title) {
            dialog.setTitle(title);
            return this;
        }

        /** Nachrichtentext setzen */
        public Builder message(String msg) {
            dialog.messageLabel.setText("<html>" + msg.replace("\n","<br>") + "</html>");
            return this;
        }

        /** Fügt einen Button mit Label und Listener hinzu */
        public Builder addButton(String label, ActionListener listener) {
            actions.put(label, listener);
            return this;
        }

        /** Dialog-Größe (optional) */
        public Builder size(int width, int height) {
            dialog.setPreferredSize(new Dimension(width, height));
            return this;
        }

        /** Dialog zentriert auf dem Owner-Fenster */
        public Builder centerOnOwner() {
            dialog.setLocationRelativeTo(dialog.getOwner());
            return this;
        }

        /** Baut die Buttons, zeigt den Dialog modal an */
        public void show() {
            // Buttons erzeugen und stylen
            actions.forEach((label, lst) -> {
                JButton btn = Button.create(label);
                btn.addActionListener(e -> {
                    lst.actionPerformed(e);
                    dialog.dispose();
                });
                dialog.buttonPanel.add(btn);
            });

            dialog.pack();
            dialog.setResizable(false);
            dialog.setVisible(true);
        }
    }
}
