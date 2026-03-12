package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void normalItemDegrades() {
        Item[] items = { new Item("foo", 10, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(19, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    void normalItemQualityNeverNegative() {
        Item[] items = { new Item("foo", 10, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, items[0].quality);
    }

    @Test
    void normalItemDegradesTwiceAfterExpiration() {
        Item[] items = { new Item("foo", 0, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(8, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    void agedBrieIncreasesQuality() {
        Item[] items = { new Item("Aged Brie", 5, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(11, items[0].quality);
    }

    @Test
    void agedBrieMaxQuality50() {
        Item[] items = { new Item("Aged Brie", 5, 50) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, items[0].quality);
    }

    @Test
    void agedBrieAfterExpiration() {
        Item[] items = { new Item("Aged Brie", 0, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(12, items[0].quality);
    }

    @Test
    void sulfurasNeverChanges() {
        Item[] items = { new Item("Sulfuras, Hand of Ragnaros", 5, 80) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(80, items[0].quality);
        assertEquals(5, items[0].sellIn);
    }

    @Test
    void backstagePassIncrease() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(21, items[0].quality);
    }

    @Test
    void backstagePassIncreaseByTwoWhen10Days() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(22, items[0].quality);
    }

    @Test
    void backstagePassIncreaseByThreeWhen5Days() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(23, items[0].quality);
    }

    @Test
    void backstagePassDropsToZeroAfterConcert() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, items[0].quality);
    }

    @Test
    void backstagePassMaxQuality50() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, items[0].quality);
    }
    @Test
    void testToString(){
        Item[] items = { new Item("foo", 10, 20) };
        assertEquals("foo" + ", " + "10" + ", " + "20", items[0].toString());
    }





}
