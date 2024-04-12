import Adapter.ChooserAdapter;
import Model.StuctOfGroup;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class ChooserGroupWindow extends JFrame{
    private JButton btnChoose;
    private JPanel panel;
    private JButton btnBack;
    private JTextField txtInput;

    public ChooserGroupWindow() throws HeadlessException {
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < StuctOfGroup.arrayGroup.size(); i++) {
            String item = StuctOfGroup.arrayGroup.get(i).getName();
            items.add(item);
        }

        ChooserAdapter chooserAdapter = new ChooserAdapter(txtInput, items);

        this.add(panel);

        btnBack.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public void makeTree(){
        btnChoose.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Введіть назву групи");
                    return;
                }

                DefaultTreeModel treeModel;
                treeModel = makeTree(txtInput.getText().toString());
                if (treeModel == null) {
                    JOptionPane.showMessageDialog(null, "Не знайдено товарів в цій групі");
                    return;
                }
                InforamtionWindow inforamtionWindow = new InforamtionWindow(treeModel);
                inforamtionWindow.setVisible(true);
            }
        });
    }

    public void outPrice(){
        btnChoose.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Введіть назву групи");
                    return;
                }

                double price = 0;
                for (int i = 0; i < StuctOfGroup.arrayGoods.size(); i++) {
                    if (StuctOfGroup.arrayGoods.get(i).getGroup().equals(txtInput.getText().toString())) {
                        price += StuctOfGroup.arrayGoods.get(i).getPrice() * StuctOfGroup.arrayGoods.get(i).getItemOnStore();
                    }
                }

                JOptionPane.showMessageDialog(null, "Загальна вартість товарів: " + price);
            }
        });
    }

    private DefaultTreeModel makeTree(String name) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(name);
        DefaultTreeModel treeModel = new DefaultTreeModel(root);

        boolean check = true;
        for (int j = 0; j < StuctOfGroup.arrayGoods.size(); j++) {
            if (StuctOfGroup.arrayGoods.get(j).getGroup().equals(name)) {
                DefaultMutableTreeNode item = new DefaultMutableTreeNode(StuctOfGroup.arrayGoods.get(j).getName());
                root.add(item);
                check = false;
            }
        }

       if (check)
           return null;
       return treeModel;
    }
}