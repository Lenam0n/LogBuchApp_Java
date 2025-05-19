package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Vector;


public class TableTree {

    private TableTree() { /* Utility-Klasse */ }

    /**
     * @param columnNames Spaltenüberschriften
     * @param data         2D-Datenarray
     * @return JScrollPane mit nicht editierbarer JTable
     */
    public static JScrollPane createTable(String[] columnNames, Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        return new JScrollPane(table);
    }

    /**
     * Baut einen Baum aus einem DefaultMutableTreeNode.
     * @param root Wurzelknoten
     * @return JScrollPane mit JTree
     */
    public static JScrollPane createTree(DefaultMutableTreeNode root) {
        JTree tree = new JTree(root);
        return new JScrollPane(tree);
    }
}
