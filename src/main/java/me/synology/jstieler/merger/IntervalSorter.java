package me.synology.jstieler.merger;

import java.util.LinkedList;
import java.util.List;

public class IntervalSorter {

    List<Interval> sortedList;

    public IntervalSorter (List<Interval> input){
        sortedList = sortByStart(input);
    }

    public List<Interval> getSortedList (){
        return sortedList;
    }

    /**
     * classic MergeSort Algorithm.
     */
    private List<Interval> sortByStart(List<Interval> list){
        if (list.size() <= 1) {
            return list;
        } else {
            List<Interval> leftList = list.subList(0, list.size() / 2);
            List<Interval> rightList = list.subList((list.size() / 2), list.size());

            leftList = sortByStart(leftList);
            rightList = sortByStart(rightList);
            return merge(leftList, rightList);
        }
    }

    private List<Interval> merge (List<Interval> leftList, List<Interval> rightList){
        List<Interval> result = new LinkedList<>();

        int left = 0;
        int right = 0;

        while (left < leftList.size() || right < rightList.size()){
            if (listIsFinished(leftList, left)){
                result.add(rightList.get(right));
                right++;
                continue;
            }
            if (listIsFinished(rightList, right)){
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

    private boolean listIsFinished(List<Interval> list, int iterator) {
        return iterator == list.size();
    }
}
