package Day14;

import java.util.HashMap;

public class NanoFactory {

    private HashMap<String, HashMap<String, Integer>> recipes;
    private HashMap<String, Item> items;
    private HashMap<String, Integer> recipeOutput;

    public NanoFactory() {
        recipes = new HashMap<>();
        items = new HashMap<>();
        recipeOutput = new HashMap<>();
    }

    public void make(String item, int amount) {
        HashMap<String, Integer> requirements = recipes.get(item);
        Item res = items.get(item);
        if (item.equals("ORE")) {
            if (res.getAmountAvailable() < amount) {
                throw new RuntimeException("Ores are out. Fuel created = " + items.get("FUEL").getCreated());
            }
        }
        while (res.getAmountAvailable() < amount) {
            requirements.forEach((s, v) -> {
                Item part = items.get(s);
                make(part.getName(), v);
                part.useItem(v);
            });
            res.created(recipeOutput.get(item));
        }
    }

    public void addOre(long amount) {
        Item ore = new Item("ORE");
        ore.created(amount);
        items.put("ORE", ore);
    }

    public Item getItem(String item) {
        return items.get(item);
    }

    public void addRecipe(String input){
        String[] pts = input.split("=>");

        String[] resultPts = pts[1].strip().split(" ");
        int resultAmnt = Integer.parseInt(resultPts[0].strip());
        String resultItem = resultPts[1].strip();
        recipeOutput.put(resultItem, resultAmnt);
        items.put(resultItem, new Item(resultItem));

        HashMap<String, Integer> itemsRequired = new HashMap<>();
        String[] parts = pts[0].split(", ");
        for (String part : parts) {
            String[] item = part.split(" ");
            String itemName = item[1].strip();
            int itemAmnt = Integer.parseInt(item[0].strip());
            itemsRequired.put(itemName, itemAmnt);
        }
        recipes.put(resultItem, itemsRequired);
    }


}
