import Adapter.ChooserAdapter;
import Model.Group;
import Model.Item;
import Model.StuctOfGroup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GroupEditWindow extends JFrame{
    private JTextField txtInput;
    private JTextField editDescription;
    private JButton btnSave;
    private JButton btnReturn;

    private int pos = 0;

    public GroupEditWindow() {
        this.setSize(500, 300);

        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < StuctOfGroup.arrayGroup.size(); i++) {
            String item = StuctOfGroup.arrayGroup.get(i).getName();
            items.add(item);
        }

        for(int i = 0; i < StuctOfGroup.arrayGoods.size(); i++) {
            if (StuctOfGroup.arrayGoods.get(i).getName().equals(txtInput.getText())) {
                editDescription.setText(StuctOfGroup.arrayGoods.get(i).getDescription());

                pos = i;
                break;
            }
        }
        ChooserAdapter chooserGroupWindow = new ChooserAdapter(txtInput, items);

        btnSave.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editDescription.getText().isEmpty() || txtInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Будь ласка, заповніть всі поля");
                    return;
                }

                StuctOfGroup.arrayGroup.get(pos).setDescription(txtInput.getText());

                rewriteFile();

                JOptionPane.showMessageDialog(null, "Зміни збережено");
            }
        });

        btnReturn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
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
}
