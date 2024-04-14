import Model.Group;
import Model.StuctOfGroup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.*;

public class GroupWindow extends JFrame{
    private JButton btnCreate;
    private JButton btnDelete;
    private JButton btnEdit;
    private JPanel panel;

    public GroupWindow() {
        this.setSize(500, 300);

        btnCreate.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupInformationAdd groupInformationAdd = new GroupInformationAdd();
                groupInformationAdd.setVisible(true);
            }
        });

        btnDelete.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupDeleteWindow groupDeleteWindow = new GroupDeleteWindow();
                groupDeleteWindow.setVisible(true);
            }
        });

        btnEdit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupEditWindow groupEditWindow = new GroupEditWindow();
                groupEditWindow.setVisible(true);
            }
        });

        this.add(panel);
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
}
