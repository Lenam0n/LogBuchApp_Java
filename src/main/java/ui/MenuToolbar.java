package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;
import java.util.Map;


public class MenuToolbar {

    private MenuToolbar() { /* Utility-Klasse */ }

    /**
     * Erzeugt eine JMenuBar.
     * @param menus Zuordung von Menü-Titel → Liste von JMenuItems
     */
    public static JMenuBar createMenuBar(Map<String, List<JMenuItem>> menus) {
        JMenuBar bar = new JMenuBar();
        bar.setBorder(new EmptyBorder(2,2,2,2));
        menus.forEach((title, items) -> {
            JMenu menu = new JMenu(title);
            items.forEach(menu::add);
            bar.add(menu);
        });
        return bar;
    }

    /**
     * Erzeugt eine JToolBar.
     * @param buttons Liste vorbereiteter JButton/JToggleButton/etc.
     * @param floatable true, wenn die Toolbar verschiebbar sein soll
     */
    public static JToolBar createToolBar(List<AbstractButton> buttons, boolean floatable) {
        JToolBar tb = new JToolBar();
        tb.setFloatable(floatable);
        buttons.forEach(tb::add);
        return tb;
    }
}
