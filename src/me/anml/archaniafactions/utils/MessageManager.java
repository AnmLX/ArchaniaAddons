package me.anml.archaniafactions.utils;

import net.md_5.bungee.api.ChatColor;

public class MessageManager {

    public String noPermission = "&cYou do not have permission to execute this command.";
    public String mustBePlayer = "&cYou must be a player in order to perform this command.";
    public String invalidPlayerName = "&cYou have entered an invalid player name.";

    public String colorize(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
