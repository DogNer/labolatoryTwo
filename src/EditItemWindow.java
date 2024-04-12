import Adapter.ChooserAdapter;
import Model.Item;
import Model.StuctOfGroup;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EditItemWindow extends JFrame{
    private JPanel panel1;
    private JTextField editDescription;
    private JButton btnSave;
    private JButton btnReturn;
    private JTextField editMaker;
    private JTextField editPrice;
    private JTextField txtInput;
    public int pos = 0;

    public EditItemWindow() throws HeadlessException {
        this.setSize(500, 300);
        this.add(panel1);

        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < StuctOfGroup.arrayGoods.size(); i++) {
            String item = StuctOfGroup.arrayGoods.get(i).getName();
            items.add(item);
        }

        ChooserAdapter chooserGroupWindow = new ChooserAdapter(txtInput, items);

        for(int i = 0; i < StuctOfGroup.arrayGoods.size(); i++) {
            if (StuctOfGroup.arrayGoods.get(i).getName().equals(txtInput.getText())) {
                editDescription.setText(StuctOfGroup.arrayGoods.get(i).getDescription());
                editMaker.setText(StuctOfGroup.arrayGoods.get(i).getMaker());
                editPrice.setText(String.valueOf(StuctOfGroup.arrayGoods.get(i).getPrice()));

                pos = i;
                break;
            }
        }

        btnSave.addActionListener(e -> {
            if (editDescription.getText().isEmpty() || editMaker.getText().isEmpty()
                    || editPrice.getText().isEmpty() || txtInput.getText().isEmpty()) {
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

            StuctOfGroup.arrayGoods.get(pos).setDescription(editDescription.getText());
            StuctOfGroup.arrayGoods.get(pos).setMaker(editMaker.getText());
            StuctOfGroup.arrayGoods.get(pos).setPrice(price);

            rewriteFile(StuctOfGroup.arrayGoods.get(pos));

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
