package counters;

import models.Item;

public class PriceCounter {

    public static float count(Item item, int amount) {
        return item.getPrice() * amount;
    }






}
