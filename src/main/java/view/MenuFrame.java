// src/main/java/view/MenuFrame.java
package view;

import interfaces.PageController;
import global.Config;
import global.Styles;
import ui.Button;
import ui.Frame;
import util.StyleUtil;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.JButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Eine reine View mit Buttons, die per Getter dem Controller
 * zur Verfügung stehen – die Map selbst wird intern benutzt.
 */
@Component
@Scope("prototype")
public class MenuFrame extends Frame {

    private final Map<String, Class<? extends PageController>> topicMap = Config.TOPIC_CONTROLLERS;
    private final List<Button> menuButtons = new ArrayList<>();

    public MenuFrame() {
        super("Menü", 300, 200);

        int row = 0;
        for (String label : topicMap.keySet()) {
            Button btn = Button.create(label, chain ->
              chain.font(Styles.INPUT_FONT.deriveFont(14f))
                   .cursor(Styles.HAND_CURSOR)
            );
            add(0, row++, 1, btn);
            menuButtons.add(btn);
        }
    }

    /** Gibt Map Label→Controller-Klasse zurück, damit der Controller sie benutzen kann */
    public Map<String, Class<? extends PageController>> getTopicMap() {
        return topicMap;
    }

    /** Damit der Controller die Button-Instanzen mit ActionListenern belegen kann */
    public List<Button> getMenuButtons() {
        return menuButtons;
    }
}
