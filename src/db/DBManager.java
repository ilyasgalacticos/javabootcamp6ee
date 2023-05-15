package db;

import java.util.ArrayList;

public class DBManager {
    private static final ArrayList<Item> items = new ArrayList<>();
    private static int id = 5;

    static {
        items.add(new Item(1, "Iphone", 400000));
        items.add(new Item(2, "XIAOMI", 200000));
        items.add(new Item(3, "SAMSUNG", 300000));
        items.add(new Item(4, "NOKIA", 40000));
    }

    public static ArrayList<Item> getItems(){
        return items;
    }

    public static void addItem(Item item){
        item.setId(id);
        items.add(item);
        id++;
    }

    public static Item getItem(int id){
        for(Item it : items){
            if(it.getId()==id) return it;
        }
        return null;
    }

}
