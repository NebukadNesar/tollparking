package com.example.tollparking.api.statistic;

import com.example.tollparking.api.requestresponse.KeyValuePair;
import com.example.tollparking.api.slot.Slot;

import java.util.ArrayList;
import java.util.List;

/**
 * Class is used to send statistical information about the park Which slots are full, what is the percentege of that
 * slot type etc..
 */
public class StatisticsResponse {

    private String             statisticName = "Current slots statuses for each type.";
    private List<Slot>         sedanSlot     = new ArrayList<>();
    private List<Slot>         ec20WattSlot  = new ArrayList<>();
    private List<Slot>         ec50WattSlot  = new ArrayList<>();
    private List<KeyValuePair> stats         = new ArrayList<>();

    public StatisticsResponse() {
    }

    public List<Slot> getSedanSlot() {
        return sedanSlot;
    }

    public String getStatisticName() {
        return statisticName;
    }

    public void setSedanSlot(List<Slot> sedanSlot) {
        this.sedanSlot = sedanSlot;
    }

    public List<Slot> getEc20WattSlot() {
        return ec20WattSlot;
    }

    public void setEc20WattSlot(List<Slot> ec20WattSlot) {
        this.ec20WattSlot = ec20WattSlot;
    }

    public List<Slot> getEc50WattSlot() {
        return ec50WattSlot;
    }

    public void setEc50WattSlot(List<Slot> ec50WattSlot) {
        this.ec50WattSlot = ec50WattSlot;
    }

    public List<KeyValuePair> getStats() {
        return stats;
    }

    public void setStats(List<KeyValuePair> stats) {
        this.stats = stats;
    }

    public void setStatisticName(String statisticName) {
        this.statisticName = statisticName;
    }

    public void addParam(KeyValuePair keyValuePair) {
        stats.add(keyValuePair);
    }

    @Override
    public String toString() {
        return "StatisticsResponse{" + "statisticName='" + statisticName + '\'' + ", sedanSlot=" + sedanSlot + ", ec20WattSlot=" + ec20WattSlot + ", ec50WattSlot=" + ec50WattSlot + ", stats=" + stats + '}';
    }
}
