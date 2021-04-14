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
        }
        return sb.toString();
    }

    public static List<Interval> mergeIntervals(String[] input){
        List<Interval> list = parseIntervals(input);

        if (list.size() == 0 || list.size() == 1)
            return list;

        for (int b = 0; b < list.size(); b++){
            Interval baseInterval = list.get(b);

            for (int c = b+1; c < list.size(); c++){
                Interval currentInterval = list.get(c);

                if (currentInterval.isWithin(baseInterval)){
                    list.remove(c);
                    c--;
                    continue;
                }
                if (currentInterval.isPartOf(baseInterval)){
                    currentInterval.resizeBoardersWith(baseInterval);
                    list.remove(b);
                    b--;
                    break;
                }
            }
        }
        return list;
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
