/**
 * Запуск програми та створення початкових файлів
 */

import Model.Group;
import Model.Item;
import Model.StuctOfGroup;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    static Item[] items = {
            new Item("Рис", "Опис рису", 50.0, "Виробник1", "Крупи"),
            new Item("Гречка", "Опис гречки", 60.0, "Виробник2", "Крупи"),
            new Item("Кукурудзяна крупа", "Опис кукурудзяної крупи", 40.0, "Виробник3", "Крупи"),

            new Item("Чай", "Опис чаю", 30.0, "Виробник4", "Напої"),
            new Item("Кава", "Опис кави", 40.0, "Виробник5", "Напої"),
            new Item("Сік", "Опис соку", 20.0, "Виробник6", "Напої"),

            new Item("Шоколад", "Опис шоколаду", 25.0, "Виробник7", "Солодощі"),
            new Item("Печиво", "Опис печива", 15.0, "Виробник8", "Солодощі"),
            new Item("Цукерки", "Опис цукерок", 10.0, "Виробник9", "Солодощі"),
    };

    static Group[] group = {
            new Group("Крупи", ""),
            new Group("Напої", ""),
            new Group("Солодощі", ""),
            new Group("Макарони", "")
    };


    public static void main(String[] args) throws Exception {
        for(int i = 0; i < group.length; i++) {
            createGroups(group[i]);
            StuctOfGroup.arrayGroup.add(group[i]);

            FileWriter writer = null;
            try {
                writer = new FileWriter("Items/" + group[i].getName()+ ".txt");
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            writer.close();
        }

        for(int i = 0; i < items.length; i++) {
            writeToFile(items[i]);
            StuctOfGroup.arrayGoods.add(items[i]);
        }




        MenuWindow menu = new MenuWindow();
        menu.setVisible(true);
    }

    private static void writeToFile(Item item) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter("Items/" + item.getGroup() + ".txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(item + "\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writer.close();
    }

    private static void createGroups(Group group) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter("Groups/Group.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(group + "\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writer.close();

    }
}