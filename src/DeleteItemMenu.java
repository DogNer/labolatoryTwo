import Adapter.ChooserAdapter;
import Model.Item;
import Model.StuctOfGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DeleteItemMenu extends JFrame{
    private JPanel panel1;
    private JButton btnReturn;
    private JButton btnDel;
    private JTextField txtInput;

    public DeleteItemMenu() throws HeadlessException {
        this.setSize(500, 300);
        this.add(panel1);

        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < StuctOfGroup.arrayGoods.size(); i++) {
            String item = StuctOfGroup.arrayGoods.get(i).getName();
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
                    JOptionPane.showMessageDialog(null, "Введіть назву товару");
                    return;
                }

                for(int i = 0; i < StuctOfGroup.arrayGoods.size(); ++i){
                    if (StuctOfGroup.arrayGoods.get(i).getName().equals(txtInput.getText())) {
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
