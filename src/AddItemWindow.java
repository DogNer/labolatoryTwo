import javax.swing.*;

public class AddItemWindow extends JFrame{
    private JPanel panel;
    private JTextField fieldNameItem;
    private JTextField fieldCntItem;
    private JButton btnAddItem;

    public AddItemWindow() {
        this.setSize(500, 300);
        fieldNameItem.setToolTipText("Enter item name");
        fieldCntItem.setToolTipText("Enter item count");

        this.add(panel);
    }
}
