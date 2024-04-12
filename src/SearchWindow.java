import Model.StuctOfGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchWindow extends JFrame{
    private JTextField regexField;
    private JButton btnFind;
    private JPanel panel;
    private JButton btnBack;
    private JList listItem;

    public SearchWindow() throws HeadlessException {
        setTitle("Search");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listItem.setVisible(false);

        btnFind.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (regexField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Введіть фільтр для пошуку!");
                    return;
                }

                System.out.println("Search");
                String regex = regexField.getText();
                DefaultListModel listModel = new DefaultListModel();
                for(int i = 0; i < StuctOfGroup.arrayGoods.size(); i++) {
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(StuctOfGroup.arrayGoods.get(i).getName());

                    listItem.removeAll();

                    while (matcher.find()) {
                        System.out.println(matcher.group());
                        if (matcher.group() != null)
                            listModel.addElement(StuctOfGroup.arrayGoods.get(i).getName());
                    }
                }

                if (listModel.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Нічого не знайдено");
                    listItem.setVisible(false);
                    return;
                }
                else {
                    listItem.setVisible(false);
                    listItem.setModel(listModel);
                    listItem.setVisible(true);
                }
            }
        });

        btnBack.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        this.add(panel);
    }
}
