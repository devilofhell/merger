package me.synology.jstieler.merger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MergerApplication {

    public static void main (String[] args){
        if (args.length == 0)
            System.out.println("No arguments detected. Try adding numbers in the format: \"x,y\" or \"(x,y)\" or \"[x,y]\" or \"x,y a,b ...\"");

        List<Interval> input = IntervalMerger.parseIntervals(args);

        IntervalMerger.printIntervalList(IntervalMerger.mergeIntervals(input));
    }
}
