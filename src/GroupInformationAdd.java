import Model.Group;
import Model.StuctOfGroup;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GroupInformationAdd extends JFrame{
    private JTextField nameText;
    private JTextField descText;
    private JButton btnSave;
    private JPanel panel;

    public GroupInformationAdd() throws HeadlessException {
        this.setSize(500, 300);

        this.add(panel);

        btnSave.addActionListener(e -> {
            String groupName = nameText.getText();
            String description = descText.getText();

            if (groupName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Назва групи не може бути порожньою!");
                return;
            }

            if (description.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Опис групи не може бути порожнім!");
                return;
            }

            if (!groupName.isEmpty()) {
                boolean groupExists = false;
                for (Group group : StuctOfGroup.arrayGroup) {
                    if (group.getName().equals(groupName)) {
                        groupExists = true;
                        break;
                    }
                }
                if (!groupExists) {
                    StuctOfGroup.arrayGroup.add(new Group(groupName, description));
                    JOptionPane.showMessageDialog(null, "Групу товарів '" + groupName + "' створено успішно!");

                    for(int i = 0; i < StuctOfGroup.arrayGroup.size(); i++) {
                        try {
                            rewriteFileGroup(StuctOfGroup.arrayGroup.get(i));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    try {
                        createGroup(new Group(groupName, description));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Група з назвою '" + groupName + "' вже існує!");
                    setVisible(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Назва групи не може бути порожньою!");
                setVisible(false);
            }
        });
    }

    private void createGroup(Group group) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter("Items/" + group.getName() + ".txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writer.close();
    }

    private void rewriteFileGroup(Group group) throws IOException {
        FileWriter writer = new FileWriter("Groups/Group.txt", false);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for(Group group1 : StuctOfGroup.arrayGroup) {
                bufferedWriter.write(group1 + "\n");
            }
            bufferedWriter.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
