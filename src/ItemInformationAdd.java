import Model.Item;
import Model.StuctOfGroup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Struct;

public class ItemInformationAdd extends JFrame{
    private JTextField nameText;
    private JPanel panel;
    private JTextField descText;
    private JButton btnSave;
    private JTextField makerText;
    private JTextField priceText;
    private JComboBox chooserGroup;

    public ItemInformationAdd() {
        this.setSize(600, 400);

        this.add(panel);

        chooserGroup.addItem("Крупи");

        btnSave.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (nameText.getText().isEmpty() || descText.getText().isEmpty() || makerText.getText().isEmpty()
                        || priceText.getText().isEmpty() || chooserGroup.getSelectedItem().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                    return;
                }

                double price;
                try {
                    price = Double.parseDouble(priceText.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ціна повина бути числом");
                    return;
                }

                if (price < 0) {
                    JOptionPane.showMessageDialog(null, "Ціна повина бути більше 0");
                    return;
                }

                Item item = new Item(
                        nameText.getText(),
                        descText.getText(),
                        price,
                        makerText.getText(),
                        chooserGroup.getSelectedItem().toString()
                );

                for(int i = 0; i < StuctOfGroup.arrayGoods.size(); ++i){
                    if (StuctOfGroup.arrayGoods.get(i).getName().equals(nameText.getText())) {
                        JOptionPane.showMessageDialog(null, "Вже є такий товар в цій групі");
                        return;
                    }
                }

                StuctOfGroup.arrayGoods.add(item);


                JOptionPane.showMessageDialog(null, "Успішне дордавання");
                writeToFile(item);

                closeWindow();
            }
        });
    }

    private void closeWindow() {
        this.setVisible(false);
    }

    private void writeToFile(Item item) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("Items/" + item.getGroup() + ".txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(item + "\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
