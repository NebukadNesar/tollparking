package com.example.tollparking.api.statistic;

import com.example.tollparking.api.requestresponse.KeyValuePair;
import com.example.tollparking.api.slot.Slot;

import java.util.List;

import static com.example.tollparking.api.slot.StaticSlotFactory.*;

/**
 * StatisticsController is used only to construct informational response of the current occupation status of slots
 */
public class StatisticController {

    /**
     * Method is used to get information about the current statuses of each slot.
     * Which slot is empty
     * Which slot is full
     * What is the time that car parked
     * @return
     */
    public StatisticsResponse getStats() {

        StatisticsResponse response = new StatisticsResponse();
        response.setEc20WattSlot(ec20WattSlot);
        response.setEc50WattSlot(ec50WattSlot);
        response.setSedanSlot(sedanSlot);

        int occupiedSedanSlotCount   = getOccupiedCount(sedanSlot);
        int unOccupiedSedanSlotCount = getUnOccupiedCount(sedanSlot, occupiedSedanSlotCount);

        int occupiedEC20SlotCount   = getOccupiedCount(ec20WattSlot);
        int unOccupiedEC20SlotCount = getUnOccupiedCount(ec20WattSlot, occupiedEC20SlotCount);

        int occupiedEC50SlotCount   = getOccupiedCount(ec50WattSlot);
        int unOccupiedEC50SlotCount = getUnOccupiedCount(ec50WattSlot, occupiedEC50SlotCount);

        int sedanPercentage = (int) (occupiedSedanSlotCount / (1.0 * sedanSlot.size()) * 100);
        int ec20wPercentage = (int) (occupiedEC20SlotCount / (1.0 * ec20WattSlot.size()) * 100);
        int ec50wPercentage = (int) (occupiedEC50SlotCount / (1.0 * ec50WattSlot.size()) * 100);

        response.addParam(new KeyValuePair("occupied sedan slot count", String.valueOf(occupiedSedanSlotCount)));
        response.addParam(new KeyValuePair("unoccupied sedan slot count", String.valueOf(unOccupiedSedanSlotCount)));
        response.addParam(new KeyValuePair("sedan usage percentege % ", String.valueOf(sedanPercentage)));

        response.addParam(new KeyValuePair("occupied ec20kw slot count", String.valueOf(occupiedEC20SlotCount)));
        response.addParam(new KeyValuePair("unoccupied ec20kw slot count", String.valueOf(unOccupiedEC20SlotCount)));
        response.addParam(new KeyValuePair("ec20kw usage percentege % ", String.valueOf(ec20wPercentage)));

        response.addParam(new KeyValuePair("occupied ec50kw slot count", String.valueOf(occupiedEC50SlotCount)));
        response.addParam(new KeyValuePair("unoccupied ec50kw slot count", String.valueOf(unOccupiedEC50SlotCount)));
        response.addParam(new KeyValuePair("ec50kw usage percentege % ",   String.valueOf(ec50wPercentage)));

        return response;
    }

    /**
     * Method returns the occupied count of that slot type
     *
     * @param slots
     * @return
     */
    private int getOccupiedCount(List<Slot> slots) {
        int count = 0;
        for (int i = 0; i < slots.size(); i++) {
            Slot.SlotStatus t = slots.get(i).getStatus();
            if (Slot.SlotStatus.FULL.equals(t)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Method returns unoccupied count of that slot type
     *
     * @param slots
     * @param occupiedCount
     * @return
     */
    private int getUnOccupiedCount(List<Slot> slots, int occupiedCount) {
        return slots.size() - occupiedCount;
    }
}
