/**
 * Клас AddItemWindow відповідає за вікно додавання товару(кількість) на склад
 */

import Adapter.ChooserAdapter;
import Model.Item;
import Model.StuctOfGroup;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AddItemWindow extends JFrame{
    private JPanel panel;
    private JTextField priceText;
    private JButton btnBack;
    private JButton btnSave;
    private JTextField txtInput;

    public AddItemWindow() {
        this.setSize(500, 300);

        this.add(panel);

        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < StuctOfGroup.arrayGoods.size(); i++) {
            String item = StuctOfGroup.arrayGoods.get(i).getName();
            items.add(item);
        }

        ChooserAdapter chooserGroupWindow = new ChooserAdapter(txtInput, items);

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

                String name = txtInput.getText();
                if(name.equals("")) {
                    JOptionPane.showMessageDialog(null, "Введіть назву товару");
                    return;
                }

                for (int i = 0; i < StuctOfGroup.arrayGoods.size(); i++) {
                    if (StuctOfGroup.arrayGoods.get(i).getName().equals(name)) {
                        int privCnt = StuctOfGroup.arrayGoods.get(i).getItemOnStore();

                        StuctOfGroup.arrayGoods.get(i).setItemOnStore(privCnt + cntItem);
                        rewriteFile(StuctOfGroup.arrayGoods.get(i));
                        JOptionPane.showMessageDialog(null, "На склад додано " + cntItem + " одиниць товару " + name);
                        closeWindow();
                        return;
                    }
                }
            }
        });
    }

    private void closeWindow() {
        this.setVisible(false);
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
