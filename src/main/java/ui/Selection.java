package ui;

import javax.swing.*;
import java.util.List;

public class Selection {

    private Selection() { /* Utility-Klasse */ }

    /** @return einfache JCheckBox */
    public static JCheckBox createCheckBox(String text, boolean selected) {
        return new JCheckBox(text, selected);
    }

    /**
     * Baut eine Gruppe von RadioButtons.
     * @param options Texte für die Buttons
     * @param selectedIndex Index des vorausgewählten (-1 = keiner)
     */
    public static ButtonGroup createRadioGroup(List<String> options, int selectedIndex, JPanel container) {
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < options.size(); i++) {
            JRadioButton rb = new JRadioButton(options.get(i));
            if (i == selectedIndex) rb.setSelected(true);
            group.add(rb);
            container.add(rb);
        }
        return group;
    }

    /** @return JComboBox mit den Einträgen */
    public static <T> JComboBox<T> createComboBox(T[] items) {
        return new JComboBox<>(items);
    }

    /** @return JList im JScrollPane */
    public static <T> JScrollPane createListPane(T[] items) {
        JList<T> list = new JList<>(items);
        return new JScrollPane(list);
    }
}
