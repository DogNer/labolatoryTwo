import Model.Item;
import Model.StuctOfGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DeleteItemMenu extends JFrame{
    private JPanel panel1;
    private JComboBox selectDelete;
    private JButton btnReturn;
    private JButton btnDel;

    public DeleteItemMenu() throws HeadlessException {
        this.setSize(500, 300);
        this.add(panel1);

        for(int i = 0; i < StuctOfGroup.arrayGoods.size(); ++i){
            selectDelete.addItem(StuctOfGroup.arrayGoods.get(i).getName());
        }

        btnReturn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        btnDel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < StuctOfGroup.arrayGoods.size(); ++i){
                    if (StuctOfGroup.arrayGoods.get(i).getName().equals(selectDelete.getSelectedItem().toString())) {
                        Item it = StuctOfGroup.arrayGoods.get(i);
                        StuctOfGroup.arrayGoods.remove(i);
                        rewriteFile(it);
                        JOptionPane.showMessageDialog(null, "Товар видалено");
                        setVisible(false);
                        return;
                    }
                }
            }
        });
    }

    private void rewriteFile(Item good) {
        try {
            FileWriter writer = new FileWriter("Items/" + good.getGroup() + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (Item item : StuctOfGroup.arrayGoods) {
                bufferedWriter.write(item + "\n");
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
