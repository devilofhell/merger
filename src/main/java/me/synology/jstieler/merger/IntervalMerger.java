package me.synology.jstieler.merger;

import java.util.LinkedList;
import java.util.List;

public class IntervalMerger {

    /**
     * @param input multiple intervals as String. A single interval are 2 numbers separated by a comma.
     *              The interval can be enclosed by brackets "()[]"
     * @return An ordered list of separated intervals
     */

    public static List<Interval> mergeIntervals (String[] input){
        List<Interval> list = parseIntervals(input);

        if (list.size() == 0 || list.size() == 1)
            return list;

        List<Interval> sortedList = new IntervalSorter(list).getSortedList();
        return new OverlappingMerger(sortedList).getSeparatedIntervals();
    }

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

    private static List<Interval> parseIntervals (String[] args){
        if (args.length == 0)
            System.out.println("No arguments detected. Try adding numbers in the format: \"x,y\" or \"(x,y)\" or \"[x,y]\" or \"x,y a,b ...\"");

        List<Interval> result = new LinkedList<>();

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
