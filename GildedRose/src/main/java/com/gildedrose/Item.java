package com.gildedrose;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }
    public Boolean isAgedBrie(){
        return this.name.equals("Aged Brie");
    }
    public Boolean isSulfuras(){
        return this.name.equals("Sulfuras, Hand of Ragnaros");
    }
    public Boolean isBackstagePasses(){
        return this.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
