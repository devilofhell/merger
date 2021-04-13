package me.synology.jstieler.merger;

import java.util.ArrayList;
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
            sb.append(" ");
        }
        return sb.toString();
    }

    public static List<Interval> mergeIntervals(List<Interval> list){
        if (list.size() == 0 || list.size() == 1)
            return list;

        for (int i = 0; i < list.size(); i++){
            Interval baseInterval = list.get(i);

            for (int j = i+1; j < list.size(); j++){
                Interval currentInterval = list.get(j);

                if (currentInterval.isWithin(baseInterval)){
                    list.remove(j);
                    j--;
                    continue;
                }
                if (currentInterval.isPartOf(baseInterval)){
                    currentInterval.resizeBoardersWith(baseInterval);
                    list.remove(i);
                    i--;
                    break;
                }
            }
        }
        return list;
    }

    public static List<Interval> parseIntervals (String[] args){
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
