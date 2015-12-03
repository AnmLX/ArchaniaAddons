package me.anml.archaniafactions.advertisements.commands;

import me.anml.archaniafactions.Main;
import me.anml.archaniafactions.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class YoutubeCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        MessageManager messageManager = Main.getMessageManager();


        if (!(sender instanceof Player)) {
            sender.sendMessage(messageManager.colorize(messageManager.mustBePlayer));
        } else {
            Player player = (Player) sender;
            if (!player.hasPermission("archaniaaddons.advertise")) {
                player.sendMessage(messageManager.colorize(messageManager.noPermission));
            } else {
                if (args.length >= 1) {
                    if (args[0].equalsIgnoreCase("broadcast")) {
                        if (Main.main.getConfig().contains("Advertisement-Info." + player.getUniqueId() + ".Youtube.Confirmed")) {
                            if (Main.main.getConfig().getBoolean("Advertisement-Info." + player.getUniqueId() + ".Youtube.Confirmed")) {
                                if (Main.main.getConfig().contains("Advertisement-Info." + player.getUniqueId() + ".Youtube.Link")) {
                                    String link = Main.main.getConfig().getString("Advertisement-Info." + player.getUniqueId() + ".Youtube.Link");

                                    if (!Main.getWaitingYoutube().containsKey(player.getUniqueId())) {
                                        if (Main.getConfirmedYoutube().contains(player)) {
                                            Main.getConfirmedYoutube().remove(player);
                                            for (Player p : Bukkit.getOnlinePlayers()) {
                                                p.sendMessage(messageManager.colorize("&c[&4Alert&c] &6" + player.getName() + " &ahas posted a video of Archania at &b" + link + " &a!"));
                                            }
                                            Main.getWaitingYoutube().put(player.getUniqueId(), System.currentTimeMillis() + 7200000);
                                            return true;
                                        } else {

                                            Main.getConfirmedYoutube().add(player);
                                            player.sendMessage(messageManager.colorize("&aYou must retype the command if you wish to broadcast the message below to the network."));
                                            player.sendMessage(messageManager.colorize("&fMessage: &6" + player.getName() + " &ahas posted a video of Archania at &b" + link + " &a!"));
                                            return false;

                                        }
                                    } else {
                                        player.sendMessage(messageManager.colorize("&cYou have already broadcasted your &6Youtube &cchannel in the past 2 hours, please wait the remaining time."));
                                    }
                                } else {
                                    Main.main.getConfig().set("Advertisement-Info." + player.getUniqueId() + ".Youtube.Confirmed", false);
                                    player.sendMessage(messageManager.colorize("&4Error! &cYou have not stated a &6Youtube &clink yet. You may do so using '/advertise youtube <link>'."));
                                }
                            } else {
                                player.sendMessage(messageManager.colorize("&cAn administrator has not yet confirmed your link for your &6Youtube &cchannel. Please ask an administrator for assistance."));
                            }
                        } else {
                            player.sendMessage(messageManager.colorize("&cAn administrator has not yet confirmed your link for your &6Youtube &cchannel. Please ask an administrator for assistance."));
                        }
                    } else {
                        player.sendMessage(messageManager.colorize("&4Error! &cUsage: /youtube broadcast"));
                    }
                } else {
                    player.sendMessage(messageManager.colorize("&4Error! &cUsage: /youtube broadcast"));
                }
            }

        }
        return false;
    }
}
