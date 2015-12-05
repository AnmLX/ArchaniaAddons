package me.anml.archaniafactions.reportgui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by kishanpatel on 12/5/15.
 */
public enum Accusation  {

    XRAY("Xray", Material.GRASS),
    KILL_AURA("Kill Aura", Material.DIAMOND_SWORD),
    ANTI_KNOCKBACK("Anti-Knockback", Material.CHAINMAIL_BOOTS),
    NO_FALL("No Fall", Material.FEATHER),
    SPEED("Speed", Material.ENDER_PEARL),
    FLY("Fly", Material.NETHER_STAR),
    SPIDER("Spider", Material.DIAMOND_SWORD),
    AUTO_SOUP("Auto Soup", Material.MUSHROOM_SOUP),
    AUTO_ARMOR("Auto Aarmor1", Material.DIAMOND_CHESTPLATE),
    OTHER("Other", Material.PAPER);

    String name;

    public ItemStack getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    ItemStack item;

    Accusation(String name, Material material) {
        this.name = name;
        this.item = new ItemStack(material, 1);
    }

}
