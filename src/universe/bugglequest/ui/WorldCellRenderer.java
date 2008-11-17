package universe.bugglequest.ui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import universe.bugglequest.BuggleWorld;



public class WorldCellRenderer extends JLabel implements ListCellRenderer {

 	private static final long serialVersionUID = -8332490521368971733L;

	public WorldCellRenderer() {
		//setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		if (value instanceof BuggleWorld) {
			BuggleWorld w = (BuggleWorld) value;
			setText(w.getName());
		} else {
			setText(value!=null?value.toString():"");
		}
       
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
                     
        setFont(list.getFont());
        setOpaque(true);
        return this;
	}

}