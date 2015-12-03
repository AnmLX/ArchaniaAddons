package me.anml.archaniafactions;

import me.anml.archaniafactions.advertisements.commands.AdAdminCommand;
import me.anml.archaniafactions.advertisements.commands.AdCommand;
import me.anml.archaniafactions.advertisements.commands.TwitchCommand;
import me.anml.archaniafactions.advertisements.commands.YoutubeCommand;
import me.anml.archaniafactions.runnables.ArchaniaRunnable;
import me.anml.archaniafactions.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * *******************************************************************
 * » Copyright Dylzqn (c) 2015. All rights Reserved.
 * » Any code contained within this document, and any associated APIs with similar branding
 * » are the sole property of Dylzqn because he never loved Fabelz. Distribution, reproduction, taking snippets, or
 * » claiming any contents as your own will break the terms of the licence, and void any
 * » agreements with you. the third party.
 * » Thanks :D
 * ********************************************************************
 */
public class Main extends JavaPlugin {

    public static Main main;
    private static MessageManager messageManager;

    private static ArrayList<Player> confirmedYoutube = new ArrayList<>();
    private static Map<UUID, Long> waitingYoutube = new HashMap<>();
    private static ArrayList<Player> confirmedTwitch = new ArrayList<>();
    private static Map<UUID, Long> waitingTwitch = new HashMap<>();
    private ArchaniaRunnable archaniaRunnable;

    public static Map<UUID, Long> getWaitingYoutube() {
        return waitingYoutube;
    }

    public static Map<UUID, Long> getWaitingTwitch() {
        return waitingTwitch;
    }

    public static ArrayList<Player> getConfirmedYoutube() {
        return confirmedYoutube;
    }

    public static ArrayList<Player> getConfirmedTwitch() {
        return confirmedTwitch;
    }


    public static MessageManager getMessageManager() {
        return messageManager;
    }

    @Override
    public void onEnable() {

        main = this;
        messageManager = new MessageManager();
        getCommand("ad").setExecutor(new AdCommand());
        getCommand("adadmin").setExecutor(new AdAdminCommand());
        getCommand("youtube").setExecutor(new YoutubeCommand());
        getCommand("twitch").setExecutor(new TwitchCommand());

        saveDefaultConfig();

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, archaniaRunnable = new ArchaniaRunnable(), 20L, 0L);


    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
        main = null;
    }
}
