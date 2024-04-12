import Model.StuctOfGroup;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuWindow extends JFrame{
    private JPanel panel1;
    private JButton btnSearch;
    private JButton btnGroup;
    private JButton btnItem;
    private JButton btnAddItem;
    private JButton btnCout;
    private JButton buyBtn;

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
                if (StuctOfGroup.arrayGoods.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Список товарів порожній");
                    return;
                }

                ItemWindow ItemWindow = new ItemWindow();
                ItemWindow.setVisible(true);
            }
        });

        buyBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StuctOfGroup.arrayGoods.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Список товарів порожній");
                    return;
                }

                BuyWindow buyWindow = new BuyWindow();
                buyWindow.setVisible(true);
            }
        });

        btnCout.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StuctOfGroup.arrayGoods.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Список товарів порожній");
                    return;
                }

                OutputWindow countWindow = new OutputWindow();
                countWindow.setVisible(true);
            }
        });

        btnSearch.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StuctOfGroup.arrayGoods.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Список товарів порожній");
                    return;
                }

                SearchWindow searchWindow = new SearchWindow();
                searchWindow.setVisible(true);
            }
        });
    }
}
