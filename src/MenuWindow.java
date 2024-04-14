
import Model.Group;
import Model.Item;
import Model.StuctOfGroup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

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

        btnGroup.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupWindow groupWindow = new GroupWindow();
                groupWindow.setVisible(true);
            }
        });

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

    private boolean deleteGroup(String groupName) {
        StuctOfGroup.arrayGoods.removeIf(item -> item.getGroup().equals(groupName));

        StuctOfGroup.arrayGroup.removeIf(group -> group.getName().equals(groupName));

        try {
            Path path = Paths.get("Items/" + groupName + ".txt");

            Files.delete(path);

            System.out.println("File deleted successfully");
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such file or directory%n", "src/Items/Крупи.txt");
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", "src/Items/Крупи.txt");
        } catch (IOException x) {
            System.err.println(x);
        }

        return false;
    }

    public JButton getBtnGroup() {
        return btnGroup;
    }

}
