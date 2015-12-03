package me.anml.archaniafactions.advertisements.commands;

import me.anml.archaniafactions.Main;
import me.anml.archaniafactions.utils.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        MessageManager messageManager = Main.getMessageManager();


        if (!(sender instanceof Player)) {
            sender.sendMessage(messageManager.colorize(messageManager.mustBePlayer));
        } else {
            Player player = (Player) sender;
            if (!player.hasPermission("archaniaaddons.advertise")) {
                player.sendMessage(messageManager.colorize(messageManager.noPermission));
            } else {
                if (args.length >= 2) {
                    if (args[0].equalsIgnoreCase("youtube")) {
                        if (Main.main.getConfig().contains("Advertisement-Info." + player.getUniqueId() + ".Youtube.Confirmed")) {
                            if (Main.main.getConfig().getBoolean("Advertisement-Info." + player.getUniqueId() + ".Youtube.Confirmed")) {
                                player.sendMessage(messageManager.colorize("&4Error! &cYour &6Youtube &clink was already confirmed by an administrator./nIf you wish to change it, please contact an administrator."));
                                return false;
                            }
                        }

                        Main.main.getConfig().set("Advertisement-Info." + player.getUniqueId() + ".Youtube.Link", args[1]);
                        Main.main.getConfig().set("Advertisement-Info." + player.getUniqueId() + ".Youtube.Confirmed", false);
                        Main.main.saveConfig();

                        player.sendMessage(messageManager.colorize("&aYou have successfully set your &6Youtube &alink to &b" + args[1] + "&a."));

                    } else if (args[0].equalsIgnoreCase("twitch")) {
                        if (Main.main.getConfig().contains("Advertisement-Info." + player.getUniqueId() + ".Twitch.Confirmed")) {
                            if (Main.main.getConfig().getBoolean("Advertisement-Info." + player.getUniqueId() + ".Twitch.Confirmed")) {
                                player.sendMessage(messageManager.colorize("&4Error! &cYour &5Twitch &clink was already confirmed by an administrator/nIf you wish to change it, please contact an administrator."));
                                return false;
                            }
                        }

                        Main.main.getConfig().set("Advertisement-Info." + player.getUniqueId() + ".Twitch.Link", args[1]);
                        Main.main.getConfig().set("Advertisement-Info." + player.getUniqueId() + ".Twitch.Confirmed", false);
                        Main.main.saveConfig();

                        player.sendMessage(messageManager.colorize("&aYou have successfully set your &5Twitch &alink to &b" + args[1] + "&a."));

                    } else {
                        player.sendMessage(messageManager.colorize("&4Error! &cUsage: /ad <youtube | twitch> <link>"));
                    }
                } else {
                        player.sendMessage(messageManager.colorize("&4Error! &cUsage: /ad <youtube | twitch> <link>"));

                }
            }
        }
        return false;
    }
}
