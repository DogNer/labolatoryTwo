import Model.Item;
import Model.StuctOfGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BuyWindow extends JFrame{
    private JComboBox nameItem;
    private JTextField priceText;
    private JButton btnBack;
    private JButton btnSave;
    private JPanel panel;

    public BuyWindow() throws HeadlessException {
        this.setSize(500, 300);

        this.add(panel);

        for (int i = 0; i < StuctOfGroup.arrayGoods.size(); i++) {
            nameItem.addItem(StuctOfGroup.arrayGoods.get(i).getName());
        }

        btnBack.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });

        btnSave.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cntItem = 0;
                try {
                    cntItem = Integer.parseInt(priceText.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ціна повина бути числом");
                    return;
                }

                if (cntItem < 0) {
                    JOptionPane.showMessageDialog(null, "Кількість товару повина бути більше 0");
                    return;
                }

                String name = nameItem.getSelectedItem().toString();
                for (int i = 0; i < StuctOfGroup.arrayGoods.size(); i++) {
                    if (StuctOfGroup.arrayGoods.get(i).getName().equals(name)) {
                        int privCnt = StuctOfGroup.arrayGoods.get(i).getItemOnStore();

                        if (privCnt < cntItem) {
                            JOptionPane.showMessageDialog(null, "На складі недостатньо товару");
                            return;
                        }

                        StuctOfGroup.arrayGoods.get(i).setItemOnStore(privCnt - cntItem);
                        rewriteFile(StuctOfGroup.arrayGoods.get(i));
                        JOptionPane.showMessageDialog(null, "Зі складу видалено " + cntItem + " одиниць товару " + name);
                        closeWindow();
                        return;
                    }
                }
            }
        });
    }

    private void rewriteFile(Item item) {
        try {
            FileWriter writer = new FileWriter("Items/" + item.getGroup() + ".txt", false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (int i = 0; i < StuctOfGroup.arrayGoods.size(); i++) {
                bufferedWriter.write(StuctOfGroup.arrayGoods.get(i) + "\n");
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeWindow() {
        this.setVisible(false);
    }
}
