package me.anml.archaniafactions.reportgui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by kishanpatel on 12/5/15.
 */
public enum Accusation {

    KILL_AURA("Kill Aura", Material.DIAMOND_SWORD, 10),
    AIMBOT("Aimbot", Material.COMPASS, 11),
    ANTI_KNOCKBACK("Anti-Knockback", Material.CHAINMAIL_BOOTS, 12),
    SPEED("Speed", Material.ENDER_PEARL, 13),
    FLY("Fly", Material.NETHER_STAR, 14),
    NO_FALL("No Fall", Material.FEATHER, 15),
    XRAY("Xray", Material.GRASS, 16),

    SPIDER("Spider", Material.PISTON_STICKY_BASE, 19),
    AUTO_ARMOR("Auto Armor", Material.DIAMOND_CHESTPLATE, 20),
    AUTO_EAT("Auto Eat", Material.MUSHROOM_SOUP, 21),
    INAPPROPRIATE_NAME("Inappropriate Name", Material.NAME_TAG, 22),
    OTHER("Other", Material.PAPER, 23);


    String name;
    int slot;
    ItemStack item;

    Accusation(String name, Material material, int slot) {
        this.name = name;
        this.item = new ItemStack(material, 1);
        this.slot = slot;
    }

    public ItemStack getItem() {
        return item;
    }

    public String getName() { return name; }

    public int getSlot() { return slot; }

}
