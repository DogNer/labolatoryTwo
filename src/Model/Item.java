package Model;

public class Item {
    private String name;
    private String description;
    private double price;
    private String maker;
    private String group;
    private int itemOnStore;

    public Item(String name, String description, double price, String maker, String group) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.maker = maker;
        this.group = group;
        this.itemOnStore = 0;
    }

    public int getItemOnStore() {
        return itemOnStore;
    }

    public void setItemOnStore(int itemOnStore) {
        this.itemOnStore = itemOnStore;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", maker='" + maker + '\'' +
                ", group='" + group + '\'' +
                ", 'Кількість на складі: " + itemOnStore + "\'}";
    }

    public String outputFormat() {
        return "Назва: " + name + "\n" +
                "Опис: " + description + "\n" +
                "Ціна: " + price + "\n" +
                "Виробник: " + maker + "\n" +
                "Група: " + group + "\n" +
                "Кількість на складі: " + itemOnStore + "\n";
    }
}
