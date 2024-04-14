/**
 * Клас для створення групи
 */
package Model;

import java.util.ArrayList;

public class Group {
    String name;
    String description;
    ArrayList<Item> nameItems;

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Group() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getNameItems() {
        return nameItems;
    }

    public void setNameItems(ArrayList<Item> nameItems) {
        this.nameItems = nameItems;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void addItemToArray(Item it){
        nameItems.add(it);
    }

    @Override
    public String toString() {
        return name + "{" +
                "description='" + description + '\'' +
                '}';
    }
}
