import Model.StuctOfGroup;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class OutputWindow extends JFrame {
    private JPanel panel1;
    private JButton btnOutStoreItem;
    private JButton btnBack;
    private JButton btnOutItemGroup;
    private JButton btnPriceStore;
    private JButton btnPriceGrope;

    public OutputWindow() throws HeadlessException {
        this.setSize(500, 300);
        this.add(panel1);

        btnOutStoreItem.addActionListener(e -> {
            InforamtionWindow inforamtionWindow = new InforamtionWindow(makeTree());
            inforamtionWindow.setVisible(true);
        });

        btnOutItemGroup.addActionListener(e -> {
            ChooserGroupWindow chooserGroupWindow = new ChooserGroupWindow();
            chooserGroupWindow.setVisible(true);
            chooserGroupWindow.makeTree();
        });

        btnPriceStore.addActionListener(e -> {
            double price = 0;
            for (int i = 0; i < StuctOfGroup.arrayGoods.size(); i++) {
                price += StuctOfGroup.arrayGoods.get(i).getPrice() * StuctOfGroup.arrayGoods.get(i).getItemOnStore();
            }

            JOptionPane.showMessageDialog(null, "Загальна вартість товарів: " + price);
        });

        btnPriceGrope.addActionListener(e -> {
            ChooserGroupWindow chooserGroupWindow = new ChooserGroupWindow();
            chooserGroupWindow.setVisible(true);
            chooserGroupWindow.outPrice();
        });

        btnBack.addActionListener(e -> {
            this.setVisible(false);
        });
    }

    private DefaultTreeModel makeTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Групи товарів");
        DefaultTreeModel treeModel = new DefaultTreeModel(root);

        for (int i = 0; i < StuctOfGroup.arrayGroup.size(); i++) {
            DefaultMutableTreeNode group = new DefaultMutableTreeNode(StuctOfGroup.arrayGroup.get(i).getName());
            root.add(group);

            for (int j = 0; j < StuctOfGroup.arrayGoods.size(); j++) {
                if (StuctOfGroup.arrayGoods.get(j).getGroup().equals(StuctOfGroup.arrayGroup.get(i).getName())) {
                    DefaultMutableTreeNode item = new DefaultMutableTreeNode(StuctOfGroup.arrayGoods.get(j).getName());
                    group.add(item);
                }
            }
        }

        return treeModel;
    }
}
