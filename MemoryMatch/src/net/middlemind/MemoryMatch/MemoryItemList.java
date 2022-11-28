package net.middlemind.MemoryMatch;

import java.util.Random;

/**
 * A static class the holds the names of all the memory match image files and provides
 * access to get a random one from the list.
 * 
 * @author Victor G. Brusca
 */
public class MemoryItemList {
    
    /**
     * 
     */
    private static final MemoryItem[] MEMORY_ITEMS = new MemoryItem[102];
    
    /**
     * 
     */
    private static final Random RAND = new Random((int)System.currentTimeMillis());
    
    /**
     * 
     */
    private static boolean READY = false;
    
    /**
     * 
     */
    private static final String IMAGE_FILE_EXTENSION = ".png";
    
    /**
     * 
     */
    private static final String IMAGE_FILE_CARD_BACK = "card_back_48x48.png";
    
    /**
     * 
     */
    private static final String IMAGE_FILE_CARD_FRONT = "card_front_48x48.png";    
    
    /**
     * 
     */
    public static void Init() {
        READY = false;
        MEMORY_ITEMS[0] = new MemoryItem(("01_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Dish #1");
        MEMORY_ITEMS[1] = new MemoryItem(("02_dish_2" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Dish #2");
        MEMORY_ITEMS[2] = new MemoryItem(("03_dish_pile" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Dish Pile");
        MEMORY_ITEMS[3] = new MemoryItem(("04_bowl" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Dish Bowl");
        MEMORY_ITEMS[4] = new MemoryItem(("05_apple_pie" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Apple Pie");
        MEMORY_ITEMS[5] = new MemoryItem(("06_apple_pie_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Apple Pie with Dish");
        MEMORY_ITEMS[6] = new MemoryItem(("07_bread" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Bread");
        MEMORY_ITEMS[7] = new MemoryItem(("08_bread_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Bread with Dish");
        MEMORY_ITEMS[8] = new MemoryItem(("09_baguette" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Baguette");
        MEMORY_ITEMS[9] = new MemoryItem(("10_baguette_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Baguette with Dish");
        
        MEMORY_ITEMS[10] = new MemoryItem(("11_bun" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Bun");
        MEMORY_ITEMS[11] = new MemoryItem(("12_bun_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Bun with Dish");
        MEMORY_ITEMS[12] = new MemoryItem(("13_bacon" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Bacon");
        MEMORY_ITEMS[13] = new MemoryItem(("14_bacon_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Bacon with Dish");
        MEMORY_ITEMS[14] = new MemoryItem(("15_burger" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Burger");
        MEMORY_ITEMS[15] = new MemoryItem(("16_burger_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Burger with Dish");
        MEMORY_ITEMS[16] = new MemoryItem(("17_burger_napkin" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Burger with Napkin");
        MEMORY_ITEMS[17] = new MemoryItem(("18_burrito" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Burrito");
        MEMORY_ITEMS[18] = new MemoryItem(("19_burrito_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Burrito with Dish");
        MEMORY_ITEMS[19] = new MemoryItem(("20_bagel" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Bagel");
        
        MEMORY_ITEMS[20] = new MemoryItem(("21_bagel_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Bagel with Dish");
        MEMORY_ITEMS[21] = new MemoryItem(("22_cheesecake" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Cheesecake");
        MEMORY_ITEMS[22] = new MemoryItem(("23_cheesecake_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Cheesecake with Dish");
        MEMORY_ITEMS[23] = new MemoryItem(("24_cheesepuff" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Cheesepuff");
        MEMORY_ITEMS[24] = new MemoryItem(("25_cheesepuff_bowl" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Cheesepuff Bowl");
        MEMORY_ITEMS[25] = new MemoryItem(("26_chocolate" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Chocolate");
        MEMORY_ITEMS[26] = new MemoryItem(("27_chocolate_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Chocolate with Dish");
        MEMORY_ITEMS[27] = new MemoryItem(("28_cookies" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Cookies");
        MEMORY_ITEMS[28] = new MemoryItem(("29_cookies_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Cookies with Dish");
        MEMORY_ITEMS[29] = new MemoryItem(("30_chocolatecake" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Chocolate Cake");
        
        MEMORY_ITEMS[30] = new MemoryItem(("31_chocolatecake_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Chocolate Cake with Dish");
        MEMORY_ITEMS[31] = new MemoryItem(("32_curry" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Curry");
        MEMORY_ITEMS[32] = new MemoryItem(("33_curry_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Curry with Dish");
        MEMORY_ITEMS[33] = new MemoryItem(("34_donut" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Donut");
        MEMORY_ITEMS[34] = new MemoryItem(("35_donut_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Donut with Dish");
        MEMORY_ITEMS[35] = new MemoryItem(("36_dumplings" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Dumplings");
        MEMORY_ITEMS[36] = new MemoryItem(("37_dumplings_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Dumplings with Dish");
        MEMORY_ITEMS[37] = new MemoryItem(("38_friedegg" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Fried Egg");
        MEMORY_ITEMS[38] = new MemoryItem(("39_friedegg_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Fried Egg with Dish");
        MEMORY_ITEMS[39] = new MemoryItem(("40_eggsalad" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Eggsalad");

        MEMORY_ITEMS[40] = new MemoryItem(("41_eggsalad_bowl" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Eggsalad with Dish");
        MEMORY_ITEMS[41] = new MemoryItem(("42_eggtart" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Egg Tart");
        MEMORY_ITEMS[42] = new MemoryItem(("43_eggtart_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Egg Tart with Dish");
        MEMORY_ITEMS[43] = new MemoryItem(("44_frenchfries" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "French Fries");
        MEMORY_ITEMS[44] = new MemoryItem(("45_frenchfries_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "French Fries with Dish");
        MEMORY_ITEMS[45] = new MemoryItem(("46_fruitcake" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Fruit Cake");
        MEMORY_ITEMS[46] = new MemoryItem(("47_fruitcake_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Fruit Cake with Dish");
        MEMORY_ITEMS[47] = new MemoryItem(("48_garlicbread" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Garlic Bread");
        MEMORY_ITEMS[48] = new MemoryItem(("49_garlicbread_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Garlic Bread with Dish");
        MEMORY_ITEMS[49] = new MemoryItem(("50_giantgummybear" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Giant Gummy Bear");

        MEMORY_ITEMS[50] = new MemoryItem(("51_giantgummybear_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Giant Gummy Bear with Dish");
        MEMORY_ITEMS[51] = new MemoryItem(("52_gingerbreadman" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Ginger Bread Man");
        MEMORY_ITEMS[52] = new MemoryItem(("53_gingerbreadman_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Ginger Bread Man with Dish");
        MEMORY_ITEMS[53] = new MemoryItem(("54_hotdog" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Hotdog");
        MEMORY_ITEMS[54] = new MemoryItem(("55_hotdog_sauce" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Hotdog with Sauce");
        MEMORY_ITEMS[55] = new MemoryItem(("56_hotdog_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "HotDog with Dish");
        MEMORY_ITEMS[56] = new MemoryItem(("57_icecream" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Icecream");
        MEMORY_ITEMS[57] = new MemoryItem(("58_icecream_bowl" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Icecream with Dish");
        MEMORY_ITEMS[58] = new MemoryItem(("59_jelly" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Jelly");
        MEMORY_ITEMS[59] = new MemoryItem(("60_jelly_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Jelly with Dish");

        MEMORY_ITEMS[60] = new MemoryItem(("61_jam" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Jam");
        MEMORY_ITEMS[61] = new MemoryItem(("62_jam_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Jam with Dish");
        MEMORY_ITEMS[62] = new MemoryItem(("63_lemonpie" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Lemon Pie");
        MEMORY_ITEMS[63] = new MemoryItem(("64_lemonpie_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Lemon Pie with Dish");
        MEMORY_ITEMS[64] = new MemoryItem(("65_loafbread" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Loaf Bread");
        MEMORY_ITEMS[65] = new MemoryItem(("66_loafbread_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Loaf Bread with Dish");
        MEMORY_ITEMS[66] = new MemoryItem(("67_macncheese" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Mac N' Cheese");
        MEMORY_ITEMS[67] = new MemoryItem(("68_macncheese_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Mac N' Cheese with Dish");
        MEMORY_ITEMS[68] = new MemoryItem(("69_meatball" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Meatball");
        MEMORY_ITEMS[69] = new MemoryItem(("70_meatball_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Meatball with Dish");

        MEMORY_ITEMS[70] = new MemoryItem(("71_nacho" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Nacho");
        MEMORY_ITEMS[71] = new MemoryItem(("72_nacho_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Nacho with Dish");
        MEMORY_ITEMS[72] = new MemoryItem(("73_omlet" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Omlet");
        MEMORY_ITEMS[73] = new MemoryItem(("74_omlet_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Omlet with Dish");
        MEMORY_ITEMS[74] = new MemoryItem(("75_pudding" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Pudding");
        MEMORY_ITEMS[75] = new MemoryItem(("76_pudding_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Pudding with Dish");
        MEMORY_ITEMS[76] = new MemoryItem(("77_potatochips" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Potato Chips");
        MEMORY_ITEMS[77] = new MemoryItem(("78_potatochips_bowl" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Potato Chips with Bowl");
        MEMORY_ITEMS[78] = new MemoryItem(("79_pancakes" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Pancakes");
        MEMORY_ITEMS[79] = new MemoryItem(("80_pancakes_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Pancakes with Dish");

        MEMORY_ITEMS[80] = new MemoryItem(("81_pizza" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Pizza");
        MEMORY_ITEMS[81] = new MemoryItem(("82_pizza_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Pizza with Dish");
        MEMORY_ITEMS[82] = new MemoryItem(("83_popcorn" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Popcorn");
        MEMORY_ITEMS[83] = new MemoryItem(("84_popcorn_bowl" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Popcorn with Bowl");
        MEMORY_ITEMS[84] = new MemoryItem(("85_roastedchicken" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Roasted Chicken");
        MEMORY_ITEMS[85] = new MemoryItem(("86_roastedchicken_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Roasted Chicken with Dish");
        MEMORY_ITEMS[86] = new MemoryItem(("87_ramen" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Ramen");
        MEMORY_ITEMS[87] = new MemoryItem(("88_salmon" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Salmon");
        MEMORY_ITEMS[88] = new MemoryItem(("89_salmon_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Salmon with Dish");
        MEMORY_ITEMS[89] = new MemoryItem(("90_strawberrycake" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Strawberry Cake");

        MEMORY_ITEMS[90] = new MemoryItem(("91_strawberrycake_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Strawberry Cake with Dish");
        MEMORY_ITEMS[91] = new MemoryItem(("92_sandwich" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Sandwich");
        MEMORY_ITEMS[92] = new MemoryItem(("93_sandwich_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Sandwich with Dish");
        MEMORY_ITEMS[93] = new MemoryItem(("94_spaghetti" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Spaghetti");
        MEMORY_ITEMS[94] = new MemoryItem(("95_steak" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Steak");
        MEMORY_ITEMS[95] = new MemoryItem(("96_steak_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Steak with Dish");
        MEMORY_ITEMS[96] = new MemoryItem(("97_sushi" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Sushi");
        MEMORY_ITEMS[97] = new MemoryItem(("98_sushi_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Sushi with Dish");
        MEMORY_ITEMS[98] = new MemoryItem(("99_taco" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Taco");
        MEMORY_ITEMS[99] = new MemoryItem(("100_taco_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Taco with Dish");

        MEMORY_ITEMS[100] = new MemoryItem(("101_waffle" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Waffle");
        MEMORY_ITEMS[101] = new MemoryItem(("102_waffle_dish" + IMAGE_FILE_EXTENSION), IMAGE_FILE_CARD_BACK, "Waffle with Dish");
        READY = true;
    }
    
    /**
     * 
     * @return 
     */
    public static Random GetRandomNumberGenerator() {
        return RAND;
    }
    
    /**
     * 
     * @return 
     */
    public static MemoryItem[] GetMemoryItems() {
        return MEMORY_ITEMS;
    }
    
    /**
     * 
     * @return 
     */
    public static boolean GetReady() {
        return READY;
    }
    
    /**
     * 
     * @return 
     */
    public static MemoryItem GetRandomMemoryItem() {
        if(!READY) {
            System.out.println("Warning: ImageList has not been prepped. Call the static Init method first.");
            return null;
        }
        int r = RAND.nextInt(MEMORY_ITEMS.length);
        return MEMORY_ITEMS[r];
    }
}
