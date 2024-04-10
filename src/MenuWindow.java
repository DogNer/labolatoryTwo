import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuWindow extends JFrame{
    private JPanel panel1;
    private JButton btnSettings;
    private JButton btnGroup;
    private JButton btnItem;
    private JButton btnAddItem;
    private JButton btnCout;

    public MenuWindow() {
        this.setSize(900, 600);

        this.add(panel1);

        btnAddItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddItemWindow addItemWindow = new AddItemWindow();
                addItemWindow.setVisible(true);
            }
        });

        btnItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemWindow ItemWindow = new ItemWindow();
                ItemWindow.setVisible(true);
            }
        });
    }
}
