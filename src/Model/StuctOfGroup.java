package Model;

import java.util.ArrayList;

public class StuctOfGroup {
    public static ArrayList<Group> arrayGroup = new ArrayList<>();
    public static ArrayList<Item> arrayGoods = new ArrayList<>();

    public static ArrayList<Group> getArrayGroup() {
        return arrayGroup;
    }

    @Override
    public String toString() {
        for(Group arr : arrayGroup){
            System.out.println(arr.getName());
        }
        return "";
    }
}
