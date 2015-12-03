package me.anml.archaniafactions.advertisements.commands;

import me.anml.archaniafactions.Main;
import me.anml.archaniafactions.utils.MessageManager;
import me.anml.archaniafactions.utils.UUIDManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AdAdminCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        MessageManager messageManager = Main.getMessageManager();


        if (!(sender instanceof Player)) {
            sender.sendMessage(messageManager.colorize(messageManager.mustBePlayer));
        } else {
            Player player = (Player) sender;
            if (!player.hasPermission("archaniaaddons.adadmin")) {
                player.sendMessage(messageManager.colorize(messageManager.noPermission));
            } else {

                if (args.length >= 2) {
                    if (args[0].equalsIgnoreCase("check")) {
                        try {
                            UUID uuid = UUIDManager.getUUIDOf(args[1]);

                            if (uuid == null) {
                                player.sendMessage(messageManager.colorize(messageManager.invalidPlayerName));
                                return false;
                            }

                            Player target = Bukkit.getPlayer(uuid);

                            String youtubeLink = "Not found";
                            boolean youtubeConfirmed = false;
                            String twitchLink = "Not found";
                            boolean twitchConfirmed = false;

                            if (Main.main.getConfig().contains("Advertisement-Info." + target.getUniqueId() + ".Youtube.Link")) {
                                youtubeLink = Main.main.getConfig().getString("Advertisement-Info." + target.getUniqueId() + ".Youtube.Link");
                            }

                            if (Main.main.getConfig().getBoolean("Advertisement-Info." + target.getUniqueId() + ".Youtube.Confirmed")) {
                                youtubeConfirmed = true;
                            }

                            if (Main.main.getConfig().contains("Advertisement-Info." + target.getUniqueId() + ".Twitch.Link")) {
                                twitchLink = Main.main.getConfig().getString("Advertisement-Info." + target.getUniqueId() + ".Twitch.Link");
                            }

                            if (Main.main.getConfig().getBoolean("Advertisement-Info." + target.getUniqueId() + ".Twitch.Confirmed")) {
                                twitchConfirmed = true;
                            }

                            player.sendMessage(messageManager.colorize("&aInformation regarding " + target.getName() + " is as follows:"));
                            player.sendMessage(messageManager.colorize("&f - &6Youtube Link&f: &b" + youtubeLink));
                            player.sendMessage(messageManager.colorize("&f - &6Youtube Confirmed&f: &b" + youtubeConfirmed));
                            player.sendMessage(messageManager.colorize("&f - &5Twitch Link&f: &b" + twitchLink));
                            player.sendMessage(messageManager.colorize("&f - &5Twitch Confirmed&f: &b" + twitchConfirmed));


                        } catch (Exception e) {
                            return false;
                        }

                    } else if (args.length >= 3 && args[0].equalsIgnoreCase("confirm")) {
                        try {
                            UUID uuid = UUIDManager.getUUIDOf(args[1]);

                            if (uuid == null) {
                                player.sendMessage(messageManager.colorize(messageManager.invalidPlayerName));
                                return false;
                            }

                            Player target = Bukkit.getPlayer(uuid);
                            if (args[2].equalsIgnoreCase("youtube")) {
                                if (Main.main.getConfig().contains("Advertisement-Info." + target.getUniqueId() + ".Youtube.Confirmed")) {
                                    if (Main.main.getConfig().getBoolean("Advertisement-Info." + target.getUniqueId() + ".Youtube.Confirmed")) {
                                        player.sendMessage(messageManager.colorize("&a" + target.getName() + "'s link for &6Youtube &ais already confirmed."));
                                    } else {
                                        Main.main.getConfig().set("Advertisement-Info." + target.getUniqueId() + ".Youtube.Confirmed", true);
                                        player.sendMessage(messageManager.colorize("&aYou have confirmed " + target.getName() + "'s &6Youtube &alink. This will now allow him to broadcast freely."));
                                        target.sendMessage(messageManager.colorize("&cAn administrator has confirmed your link for &6Youtube&a. You may now broadcast freely using '/youtube broadcast'."));

                                    }
                                } else {
                                    player.sendMessage(messageManager.colorize("&cThe player you have stated has not set their links yet."));
                                }
                            } else if (args[2].equalsIgnoreCase("twitch")) {
                                if (Main.main.getConfig().contains("Advertisement-Info." + target.getUniqueId() + ".Twitch.Confirmed")) {
                                    if (Main.main.getConfig().getBoolean("Advertisement-Info." + target.getUniqueId() + ".Twitch.Confirmed")) {
                                        player.sendMessage(messageManager.colorize("&a" + target.getName() + "'s link for &5Twitch &ais already confirmed."));
                                    } else {
                                        Main.main.getConfig().set("Advertisement-Info." + target.getUniqueId() + ".Twitch.Confirmed", true);
                                        player.sendMessage(messageManager.colorize("&aYou have confirmed " + target.getName() + "'s &5Twitch &alink. This will now allow him to broadcast freely."));
                                        target.sendMessage(messageManager.colorize("&cAn administrator has confirmed your link for &5Twitch&a. You may now broadcast freely using '/twitch broadcast'."));

                                    }
                                } else {
                                    player.sendMessage(messageManager.colorize("&cThe player you have stated has not set their links yet."));
                                }
                            } else {
                                player.sendMessage(messageManager.colorize("&4Invalid arguments! &cUsage: /adadmin confirm <player> <youtube | twitch>"));
                            }

                        } catch (Exception e) {
                            return false;
                        }
                    } else {
                        player.sendMessage(messageManager.colorize("&4Invalid arguments! &cUsage: /adadmin <check | confirm>"));
                    }
                } else {
                    player.sendMessage(messageManager.colorize("&4Usage: &c- /adadmin check <player>\n         /adadmin confirm <player> <youtube | twitch>"));
                }
            }
        }
        return false;
    }
}
