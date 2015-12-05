package me.anml.archaniafactions;

import me.anml.archaniafactions.advertisements.commands.AdAdminCommand;
import me.anml.archaniafactions.advertisements.commands.AdCommand;
import me.anml.archaniafactions.advertisements.commands.TwitchCommand;
import me.anml.archaniafactions.advertisements.commands.YoutubeCommand;
import me.anml.archaniafactions.reportgui.Report;
import me.anml.archaniafactions.reportgui.ReportManager;
import me.anml.archaniafactions.runnables.ArchaniaRunnable;
import me.anml.archaniafactions.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main extends JavaPlugin {

    public static Main main;
    private static MessageManager messageManager;
    private static ReportManager reportManager;

    private static Map<Report, Integer> queuedReports = new HashMap<>();
    private static ArrayList<Player> confirmedYoutube = new ArrayList<>();
    private static Map<UUID, Long> waitingYoutube = new HashMap<>();
    private static ArrayList<Player> confirmedTwitch = new ArrayList<>();
    private static Map<UUID, Long> waitingTwitch = new HashMap<>();
    private ArchaniaRunnable archaniaRunnable;

    public static Map<Report, Integer> getQueuedReports() {
        return queuedReports;
    }

    public static ReportManager getReportManager() {
        return reportManager;
    }

    public static Map<UUID, Long> getWaitingYoutube() {
        return waitingYoutube;
    }

    public static Map<UUID, Long> getWaitingTwitch() {
        return waitingTwitch;
    }

    public static ArrayList<Player> getConfirmedYoutube() {
        return confirmedYoutube;
    }

    public static ArrayList<Player> getConfirmedTwitch() { return confirmedTwitch; }


    public static MessageManager getMessageManager() {
        return messageManager;
    }

    @Override
    public void onEnable() {

        main = this;
        messageManager = new MessageManager();
        reportManager = new ReportManager();
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
