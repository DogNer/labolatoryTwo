import Model.Group;
import Model.StuctOfGroup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ItemWindow extends JFrame{
    private JButton btnCreate;
    private JButton btnDelete;
    private JButton btnEdit;
    private JPanel panel;

    public ItemWindow() {
        this.setSize(500, 300);

        this.add(panel);

        btnCreate.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent click) {
                ItemInformationAdd itemInformationAdd = new ItemInformationAdd();
                itemInformationAdd.setVisible(true);
            }
        });


        btnDelete.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StuctOfGroup.arrayGoods.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Товарів немає");
                    return;
                }

                DeleteItemMenu deleteItemMenu = new DeleteItemMenu();
                deleteItemMenu.setVisible(true);
            }
        });

        btnEdit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StuctOfGroup.arrayGoods.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Товарів немає");
                    return;
                }

                EditItemWindow editItemWindow = new EditItemWindow();
                editItemWindow.setVisible(true);
            }
        });
    }
}
