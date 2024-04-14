import Adapter.ChooserAdapter;
import Model.Group;
import Model.StuctOfGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class GroupDeleteWindow extends JFrame{
    private JTextField txtInput;
    private JButton btnReturn;
    private JButton btnDel;
    private JPanel panel1;

    public GroupDeleteWindow() throws HeadlessException {
        this.setSize(500, 300);
        this.add(panel1);

        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < StuctOfGroup.arrayGroup.size(); i++) {
            String item = StuctOfGroup.arrayGroup.get(i).getName();
            items.add(item);
        }

        ChooserAdapter chooserGroupWindow = new ChooserAdapter(txtInput, items);

        btnReturn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        btnDel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtInput.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Введіть назву групи");
                    return;
                }

                for(int i = 0; i < StuctOfGroup.arrayGroup.size(); ++i){
                    if (StuctOfGroup.arrayGroup.get(i).getName().equals(txtInput.getText())) {
                        Group group = StuctOfGroup.arrayGroup.get(i);
                        StuctOfGroup.arrayGroup.remove(i);
                        rewriteFile();
                        JOptionPane.showMessageDialog(null, "Групу видалено");
                        deleteGroup(group.getName());
                        setVisible(false);
                        return;
                    }
                }
            }
        });
    }

    private void rewriteFile() {
        try {
            FileWriter writer = new FileWriter("Groups/Group.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (Group item : StuctOfGroup.arrayGroup) {
                bufferedWriter.write(item + "\n");
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
