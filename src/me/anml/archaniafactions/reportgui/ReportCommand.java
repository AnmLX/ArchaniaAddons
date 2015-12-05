package me.anml.archaniafactions.reportgui;

import me.anml.archaniafactions.Main;
import me.anml.archaniafactions.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Created by kishanpatel on 12/5/15.
 */
public class ReportCommand implements CommandExecutor {

    ReportManager reportManager = Main.getReportManager();
    MessageManager messageManager = Main.getMessageManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(messageManager.colorize(messageManager.mustBePlayer));
        } else {

            Player player = (Player) sender;

            if(!(player.hasPermission("archaniaaddons.report"))) {
                player.sendMessage(messageManager.colorize(messageManager.noPermission));
            }

            if(args.length >= 1) {

                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    player.sendMessage(messageManager.colorize(messageManager.invalidPlayerName));
                } else {

                    Inventory inventory = Bukkit.createInventory(null, )
                }

            }
        }
        return false;
    }
}
