package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import global.Styles;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Eine generische, wiederverwendbare JComboBox-Komponente mit konsistentem Styling.
 *
 * @param <T> der Typ der Einträge im Dropdown
 */
public class Dropdown<T> extends JComboBox<T> {

    /**
     * Erzeugt ein Dropdown mit den gegebenen Items.
     *
     * @param items Liste der Einträge
     */
    public Dropdown(List<T> items) {
        super(new DefaultComboBoxModel<>());
        initStyle();
        setItems(items);
    }

    /**
     * Erzeugt ein Dropdown mit den gegebenen Items und wählt den Default-Eintrag aus.
     *
     * @param items Liste der Einträge
     * @param defaultIndex Index, der initial ausgewählt sein soll
     */
    public Dropdown(List<T> items, int defaultIndex) {
        this(items);
        if (defaultIndex >= 0 && defaultIndex < getItemCount()) {
            setSelectedIndex(defaultIndex);
        }
    }

    /**
     * Erzeugt ein Dropdown mit den gegebenen Items, wählt den Default-Eintrag aus
     * und hängt einen ActionListener an.
     *
     * @param items Liste der Einträge
     * @param defaultIndex Index, der initial ausgewählt sein soll
     * @param listener ActionListener, der bei Auswahländerung benachrichtigt wird
     */
    public Dropdown(List<T> items, int defaultIndex, ActionListener listener) {
        this(items, defaultIndex);
        addActionListener(listener);
    }

    private void initStyle() {
        setFont(Styles.INPUT_FONT);
        setForeground(Styles.TEXT_COL);
        setBorder(new EmptyBorder(
            Styles.PADDING.top,
            Styles.PADDING.left,
            Styles.PADDING.bottom,
            Styles.PADDING.right
        ));
        setMaximumRowCount(10);
    }

    /**
     * Ersetzt alle Einträge im Dropdown durch die übergebene Liste.
     *
     * @param items neue Einträge
     */
    public void setItems(List<T> items) {
        DefaultComboBoxModel<T> model = (DefaultComboBoxModel<T>) getModel();
        model.removeAllElements();
        if (items != null) {
            for (T item : items) {
                model.addElement(item);
            }
        }
    }

    /**
     * Liest den aktuell ausgewählten Eintrag aus.
     *
     * @return der selektierte Eintrag oder null, wenn nichts ausgewählt
     */
    public T getSelected() {
        @SuppressWarnings("unchecked")
        T sel = (T) getSelectedItem();
        return sel;
    }

    /**
     * Fügt einen ActionListener hinzu, der auf Auswahlwechsel reagiert.
     *
     * @param listener der Listener
     */
    public void onSelection(ActionListener listener) {
        addActionListener(listener);
    }
}
