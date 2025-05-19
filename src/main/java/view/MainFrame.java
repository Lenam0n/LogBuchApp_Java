package view;

import global.Config;
import global.Styles;
import ui.Button;
import ui.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Nur für das Anlegen und Layouten der Buttons zuständig.
 * Navigation/Listener kommen später im Controller.
 */
@Deprecated
public abstract class MainFrame extends Frame {

    private final List<Button> menuButtons = new ArrayList<>();

    public MainFrame(String title,
            int width,
            int height,
            Map<String, Class<? extends Frame>> menuMap) {
        super("Themen-Übersicht", 300, 200);

        int row = 0;
        for (String label : Config.TOPIC_CONTROLLERS.keySet()) {
        	Button btn = Button.create(label, style ->
                style
                  .font(Styles.INPUT_FONT.deriveFont(14f))
                  .cursor(Styles.HAND_CURSOR)
            );
            add(0, row++, 1, btn);
            menuButtons.add(btn);
        }
    }

    /** Damit der Controller die Buttons mit Listeners belegen kann */
    public List<Button> getMenuButtons() {
        return menuButtons;
    }
}
