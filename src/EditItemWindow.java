import Model.Item;
import Model.StuctOfGroup;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EditItemWindow extends JFrame{
    private JPanel panel1;
    private JTextField editDescription;
    private JButton btnSave;
    private JButton btnReturn;
    private JComboBox chooserGoods;
    private JTextField editMaker;
    private JTextField editPrice;

    public EditItemWindow() throws HeadlessException {
        this.setSize(500, 300);
        this.add(panel1);

        for (int i = 0; i < StuctOfGroup.arrayGoods.size(); i++) {
            chooserGoods.addItem(StuctOfGroup.arrayGoods.get(i).getName());
        }

        chooserGoods.addActionListener(e -> {
            editDescription.setText(StuctOfGroup.arrayGoods.get(chooserGoods.getSelectedIndex()).getDescription());
            editMaker.setText(StuctOfGroup.arrayGoods.get(chooserGoods.getSelectedIndex()).getMaker());
            editPrice.setText(String.valueOf(StuctOfGroup.arrayGoods.get(chooserGoods.getSelectedIndex()).getPrice()));
        });



        btnSave.addActionListener(e -> {
            if (editDescription.getText().isEmpty() || editMaker.getText().isEmpty() || editPrice.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Будь ласка, заповніть всі поля");
                return;
            }

            double price;
            try {
                price = Double.parseDouble(editPrice.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ціна повина бути числом");
                return;
            }

            if (price < 0) {
                JOptionPane.showMessageDialog(null, "Ціна повина бути більше 0");
                return;
            }

            StuctOfGroup.arrayGoods.get(chooserGoods.getSelectedIndex()).setDescription(editDescription.getText());
            StuctOfGroup.arrayGoods.get(chooserGoods.getSelectedIndex()).setMaker(editMaker.getText());
            StuctOfGroup.arrayGoods.get(chooserGoods.getSelectedIndex()).setPrice(price);

            rewriteFile(StuctOfGroup.arrayGoods.get(chooserGoods.getSelectedIndex()));

            JOptionPane.showMessageDialog(null, "Зміни збережено");
            this.setVisible(false);
        });

        btnReturn.addActionListener(e -> {
            this.setVisible(false);
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
