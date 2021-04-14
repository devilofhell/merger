package me.synology.jstieler.merger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MergerApplication {

    public static void main (String[] args){
        List<Interval> result = IntervalMerger.mergeIntervals(args);

        IntervalMerger.printIntervalList(result);
    }
}
