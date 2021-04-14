package me.synology.jstieler.merger;

import java.util.List;

public class OverlappingMerger {
    Interval currentInterval;
    Interval nextInterval;

    List<Interval> sortedList;
    List<Interval> result;

    public OverlappingMerger(List<Interval> sortedList){
        this.sortedList = sortedList;
        result = merge();
    }

    public List<Interval> getSeparatedIntervals(){
        return result;
    }

    private List<Interval> merge () {
        for (int i = 0; i + 1 < sortedList.size(); i++) {
            currentInterval = sortedList.get(i);
            nextInterval = sortedList.get(i + 1);

            if (nextInterval.isWithin(currentInterval)) {
                sortedList.remove(nextInterval);
                i--;
            }
            if (nextInterval.isPartOf(currentInterval)) {
                currentInterval.resizeBoardersWith(nextInterval);
                sortedList.remove(nextInterval);
                i--;
            }
        }
        return sortedList;
    }
}
