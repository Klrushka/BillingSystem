package counters;

import models.Item;

public class PriceCounter {

    private static double count(Item item, int amount) {
        return item.getPrice() * amount;
    }






}
