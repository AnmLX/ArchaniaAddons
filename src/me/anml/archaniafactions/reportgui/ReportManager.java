package me.anml.archaniafactions.reportgui;

import me.anml.archaniafactions.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kishanpatel on 12/5/15.
 */
public class ReportManager {

    FileConfiguration config = Main.main.getConfig();

    public void saveReport(Report report) {

        String path = "ReportsInfo." + report.getTarget() + ".reports";

        if (config.contains(path)) {
            List<String> serializedReports = config.getStringList(path);
            serializedReports.add(report.serialize());
            config.set(path, serializedReports);
        } else {
            List<String> reports = new ArrayList<>();
            reports.add(report.serialize());
            config.set(path, reports);
        }

        Main.main.saveConfig();
        Main.getQueuedReports().put(report, 300000);
    }

    public List<Report> getReports(UUID uuid) {

        String path = "ReportsInfo." + uuid + ".reports";

        if (config.contains(path)) {
            List<String> serializedReports = config.getStringList(path);
            List<Report> reports = new ArrayList<>();

            for (String serializedReport : serializedReports) {

                String[] reportInfo = serializedReport.split(",");

                Accusation accusation = null;

                for (Accusation a : Accusation.values()) {

                    if (a.getName().equalsIgnoreCase(reportInfo[2])) {
                        accusation = a;
                    }
                }

                if (accusation != null) {
                    Report report = new Report(UUID.fromString(reportInfo[0]), UUID.fromString(reportInfo[1]), accusation);
                    report.setTarget(uuid);
                    report.setDate(reportInfo[3]);
                    report.setTime(reportInfo[4]);
                    reports.add(report);
                }
            }

            return reports;
        }

        return null;

    }

    public boolean eraseReport(UUID uuid, int id) {

        String path = "ReportsInfo." + uuid + ".reports";

        if(config.contains(path)) {
            List<String> reports = config.getStringList(path);

            if(reports.get(id) != null) {
                reports.remove(id);
                config.set(path, reports);
                return true;
            }

            Main.main.saveConfig();
        }

        return false;
    }

    public boolean resetUUID(UUID uuid) {

        String path = "ReportsInfo." + uuid + ".reports";

        if(config.contains(path)) {
            config.set(path, new ArrayList<String>());
            Main.main.saveConfig();
            return true;
        }

        return false;
    }
}
