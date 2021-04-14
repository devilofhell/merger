package me.synology.jstieler.merger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IntervalMerger {

    public static void printIntervalList(List<Interval> list){
        for (Interval i : list) {
            System.out.print(i.toString());
        }
    }

    public static String getIntervalAsString (List<Interval> list){
        StringBuilder sb = new StringBuilder();
        for (Interval i : list){
            sb.append(i.toString());
        }
        return sb.toString();
    }

    public static List<Interval> mergeIntervals (String[] input){
        List<Interval> list = parseIntervals(input);

        if (list.size() == 0 || list.size() == 1)
            return list;

        List<Interval> sortedList = mergesortIntervalsByStart(list);
        return mergeOverlappingIntervals(sortedList);
    }

    /**
     * classic MergeSort Algorithm. Sorts the intervals by its start-value.
     */
    private static List<Interval> mergesortIntervalsByStart (List<Interval> list){
        if (list.size() <= 1) {
            return list;
        } else {
            List<Interval> leftList = list.subList(0, list.size() / 2);
            List<Interval> rightList = list.subList((list.size() / 2), list.size());

            leftList = mergesortIntervalsByStart(leftList);
            rightList = mergesortIntervalsByStart(rightList);
            return merge(leftList, rightList);
        }
    }

    private static List<Interval> merge (List<Interval> leftList, List<Interval> rightList){
        List<Interval> result = new LinkedList<>();

        int left = 0;
        int right = 0;

        while (left < leftList.size() || right < rightList.size()){
            if (left == leftList.size()){
                result.add(rightList.get(right));
                right++;
                continue;
            }
            if (right == rightList.size()){
                result.add(leftList.get(left));
                left++;
                continue;
            }

            if (leftList.get(left).getStart() <= rightList.get(right).getStart()){
                result.add(leftList.get(left));
                left++;
            } else if (leftList.get(left).getStart() > rightList.get(right).getStart()){
                result.add(rightList.get(right));
                right++;
            }
        }
        return result;
    }

    private static List<Interval> mergeOverlappingIntervals(List<Interval> sortedList) {
        Interval currentInterval;
        Interval nextInterval;

        for (int i = 0; i+1 < sortedList.size(); i++){
            currentInterval = sortedList.get(i);
            nextInterval = sortedList.get(i+1);

            if (nextInterval.isWithin(currentInterval)){
                sortedList.remove(nextInterval);
                i--;
            }
            if (nextInterval.isPartOf(currentInterval)){
                currentInterval.resizeBoardersWith(nextInterval);
                sortedList.remove(nextInterval);
                i--;
            }
        }
        return sortedList;
    }

    private static List<Interval> parseIntervals (String[] args){
        if (args.length == 0)
            System.out.println("No arguments detected. Try adding numbers in the format: \"x,y\" or \"(x,y)\" or \"[x,y]\" or \"x,y a,b ...\"");

        List<Interval> result = new ArrayList<>();

        for (String s : args){
            try {
                result.add(parseSingleInterval(s));
            } catch (NumberFormatException e){
                System.out.println("Argument '" + s + "' can not be parsed. Argument will be skipped.");
            }
        }

        return result;
    }

    private static Interval parseSingleInterval(String s) throws NumberFormatException {
        s = s.replace("[", "");
        s = s.replace("]", "");
        s = s.replace("(", "");
        s = s.replace(")", "");

        String[] splittedArg = s.split(",");
        int start = Integer.parseInt(splittedArg[0]);
        int end = Integer.parseInt(splittedArg[1]);
        return new Interval(start,end);
    }
}
