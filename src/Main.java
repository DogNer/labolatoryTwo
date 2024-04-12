import Model.Group;
import Model.Item;
import Model.StuctOfGroup;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static Item[] items = {
            new Item("Рис", "Опис рису", 50.0, "Виробник1", "Крупи"),
            new Item("Гречка", "Опис гречки", 60.0, "Виробник2", "Крупи"),
            new Item("Кукурудзяна крупа", "Опис кукурудзяної крупи", 40.0, "Виробник3", "Крупи")
    };

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < items.length; i++) {
            writeToFile(items[i]);
            StuctOfGroup.arrayGoods.add(items[i]);
        }

        StuctOfGroup.arrayGroup.add(new Group("Крупи", ""));
        StuctOfGroup.arrayGroup.add(new Group("Кава", ""));
        StuctOfGroup.arrayGroup.add(new Group("Кар", ""));
        StuctOfGroup.arrayGroup.add(new Group("Макарони", ""));
        StuctOfGroup.arrayGroup.add(new Group("Напої", ""));
        StuctOfGroup.arrayGroup.add(new Group("Солодощі", ""));


        MenuWindow menu = new MenuWindow();
        menu.setVisible(true);
    }

    private static void writeToFile(Item item) {
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