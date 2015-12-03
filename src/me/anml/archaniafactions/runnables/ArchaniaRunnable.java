package me.anml.archaniafactions.runnables;

import me.anml.archaniafactions.Main;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

/**
 * Created by Kishan on 12/3/15.
 */
public class ArchaniaRunnable extends BukkitRunnable {

    @Override
    public void run() {
        for (UUID uuid : Main.getWaitingTwitch().keySet()) {
            if (Main.getWaitingTwitch().containsKey(uuid)) {
                if (Main.getWaitingTwitch().get(uuid) >= System.currentTimeMillis()) {
                    Main.getWaitingTwitch().remove(uuid);
                }
            }
        }

        for (UUID uuid : Main.getWaitingYoutube().keySet()) {
            if (Main.getWaitingYoutube().containsKey(uuid)) {
                if (Main.getWaitingYoutube().get(uuid) >= System.currentTimeMillis()) {
                    Main.getWaitingYoutube().remove(uuid);
                }
            }
        }
    }
}
