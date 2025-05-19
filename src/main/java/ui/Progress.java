package ui;

import javax.swing.*;

/**
 * Fabrik für Fortschrittsbalken und Regler.
 */
public class Progress {

    private Progress() { /* Utility-Klasse */ }

    /**
     * @param min minimaler Wert
     * @param max maximaler Wert
     * @param stringPainted true für Anzeige der Prozentzahl/Text
     * @return JProgressBar
     */
    public static JProgressBar createProgressBar(int min, int max, boolean stringPainted) {
        JProgressBar bar = new JProgressBar(min, max);
        bar.setStringPainted(stringPainted);
        return bar;
    }

    /**
     * @param min Minimalwert
     * @param max Maximalwert
     * @param value Startwert
     * @param paintTicks true für Tick-Markierungen
     * @param paintLabels true für Beschriftung der Ticks
     * @return JSlider
     */
    public static JSlider createSlider(int min, int max, int value,
                                       boolean paintTicks, boolean paintLabels) {
        JSlider slider = new JSlider(min, max, value);
        slider.setPaintTicks(paintTicks);
        slider.setPaintLabels(paintLabels);
        return slider;
    }
    /**
     * Erzeugt einen JSpinner für jeden Number-Typ.
     * Intern wird bei ganzzahligen Typen (Byte, Short, Integer) der int-Konstruktor,
     * bei allen anderen der double-Konstruktor von SpinnerNumberModel verwendet.
     *
     * @param initial Startwert
     * @param min     Minimalwert
     * @param max     Maximalwert
     * @param step    Schrittweite
     * @param <T>     jede Klasse, die von Number erbt
     * @return JSpinner
     */
    public static <T extends Number> JSpinner createSpinner(
            T initial, T min, T max, T step) {

        SpinnerNumberModel model;
        // Ganzzahlige Typen nutzen int-Konstruktor
        if (initial instanceof Byte || initial instanceof Short || initial instanceof Integer) {
            int init = initial.intValue();
            int mn   = min.intValue();
            int mx   = max.intValue();
            int st   = step.intValue();
            model = new SpinnerNumberModel(init, mn, mx, st);
        } else {
            // Alle anderen (Float, Double, BigDecimal u. Ä.) als double
            double init = initial.doubleValue();
            double mn   = min.doubleValue();
            double mx   = max.doubleValue();
            double st   = step.doubleValue();
            model = new SpinnerNumberModel(init, mn, mx, st);
        }
        return new JSpinner(model);
    }

}
