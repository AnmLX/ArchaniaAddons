package me.anml.archaniafactions.reportgui;

import me.anml.archaniafactions.Main;
import me.anml.archaniafactions.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Created by kishanpatel on 12/5/15.
 */
public class ReportListener implements Listener {

    ReportManager reportManager = Main.getReportManager();
    MessageManager messageManager = Main.getMessageManager();

    @EventHandler
    public void onPlayerClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();

        if(!(event.getInventory().getName().contains("'s Report Reason:")))
            return;

        String targetName = event.getInventory().getName().split("'")[0];
        targetName = targetName.substring(2, targetName.length());
        UUID target = Bukkit.getOfflinePlayer(targetName).getUniqueId();
        event.setCancelled(true);

        if(event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) {
            return;
        }

        if(event.getCurrentItem().isSimilar(new ItemStack(Material.REDSTONE, 1))) {
            player.closeInventory();
        }

       Accusation accusation = null;

        for (Accusation a : Accusation.values()) {
            if(event.getCurrentItem().isSimilar(a.getItem()))
                accusation = a;
        }

        if(accusation == null) {
            player.sendMessage("&4Error! &cAccusation information not found! Please contact an administrator.");
            return;
        }

        Report report = new Report(player.getUniqueId(), target, accusation);
        reportManager.saveReport(report);

        player.closeInventory();

        player.sendMessage(messageManager.colorize("&aYour report has been sent to the staff team. Thank you for reporting!"));

        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.hasPermission("archaniaaddons.viewreports")) {
                p.sendMessage(messageManager.colorize(messageManager.reportPrefix + "&9" + player.getName() + " &7has reported &c" + Bukkit.getOfflinePlayer(target).getName() + " &7for " + "&a" + accusation.getName() + "&7."));
            }
        }
    }

    public Accusation getSimilarAccusation(ItemStack item) {

        for (Accusation accusation : Accusation.values()) {
            if(item.isSimilar(accusation.getItem()))
                return accusation;
        }

        return null;
    }

}
