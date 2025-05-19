package util;

import java.awt.GridBagConstraints;

import javax.swing.JComponent;
import javax.swing.JPanel;

import global.Styles;

public class MountUtil {

	public MountUtil() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Fügt eine Komponente in ein GridBagLayout-Panel ein und aktualisiert das Layout.
	 */
	public static <T extends JComponent> T mount(
	    JPanel panel,
	    T comp,
	    int gridx,
	    int gridy,
	    int gridwidth
	) {
	    GridBagConstraints c = new GridBagConstraints();
	    c.insets     = Styles.PADDING;
	    c.fill       = GridBagConstraints.HORIZONTAL;
	    c.gridx      = gridx;
	    c.gridy      = gridy;
	    c.gridwidth  = gridwidth;
	    c.weightx    = 0;

	    panel.add(comp, c);
	    panel.revalidate();
	    panel.repaint();
	    return comp;
	}


}
