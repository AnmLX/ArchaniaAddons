package me.anml.archaniafactions.runnables;

import me.anml.archaniafactions.Main;
import me.anml.archaniafactions.reportgui.Report;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

/**
 * Created by Kishan on 12/3/15.
 */
public class ArchaniaRunnable extends BukkitRunnable {

    @Override
    public void run() {
        for (UUID uuid : Main.getWaitingTwitch().keySet()) {
            if (Main.getWaitingTwitch().get(uuid) >= System.currentTimeMillis()) {
                Main.getWaitingTwitch().remove(uuid);
            }
        }

        for (UUID uuid : Main.getWaitingYoutube().keySet()) {
            if (Main.getWaitingYoutube().get(uuid) >= System.currentTimeMillis()) {
                Main.getWaitingYoutube().remove(uuid);
            }
        }

        for (Report report : Main.getQueuedReports().keySet()) {
            if (Main.getQueuedReports().get(report) >= System.currentTimeMillis()) {
                Main.getQueuedReports().remove(report);
            }
        }
    }
}
