package me.anml.archaniafactions.reportgui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by Kishan on 12/3/15.
 */
public class Report {

    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Calendar calendar = Calendar.getInstance();
    UUID reporter;
    UUID target;
    Accusation accusation;
    String date;
    String time;

    Report(UUID reporter, UUID target, Accusation accusation) {
        this.reporter = reporter;
        this.target = target;
        this.accusation = accusation;
        date = dateFormat.format(calendar.getTime()).split(" ")[0];
        time = dateFormat.format(calendar.getTime()).split(" ")[1];

    }

    public UUID getReporter() {
        return reporter;
    }

    public UUID getTarget() {
        return target;
    }

    public void setTarget(UUID target) {
        this.target = target;
    }

    public Accusation getAccusation() {
        return accusation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String serialize() {
        return reporter + "," + accusation.getName() + "," + date + "," + time;
    }


}
