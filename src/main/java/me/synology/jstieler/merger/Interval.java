package me.synology.jstieler.merger;

public class Interval {

    int start;
    int end;

    public Interval(int start, int end) {
        if (start <= end) {
            this.start = start;
            this.end = end;
        } else {
            this.start = end;
            this.end = start;
        }
    }

    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start +
                "," + end +
                ']';
    }

    /**
     * returns true, if
     *
     *       this instance
     *          <     >
     * ...[.....[.....]....]...
     *    <                >
     *   within baseInterval
     */

    public boolean isWithin(Interval baseInterval) {
        return  start >= baseInterval.getStart() && end <= baseInterval.getEnd();
    }


    /**
     * returns true, if beginning or ending of this instance is part of baseInterval
     *
     *   this instance
     *    <        >
     * ...[.....[..]....]...
     *          <       >
     *         baseInterval
     * OR
     *         this instance
     *           <        >
     *  ...[.....[..]....]...
     *     <        >
     *    baseInterval
     *
     * returns false, if overlapping borders or separate
     *          this instance
     *              <      >
     *   ...[.......x......]...
     *      <       >
     *     baseInterval
     */
    public boolean isPartOf(Interval baseInterval) {
        return start < baseInterval.getStart() && end <= baseInterval.getEnd() && end > baseInterval.getStart() ||
                start >= baseInterval.getStart() && start < baseInterval.getEnd() && end > baseInterval.getEnd();
    }

    public void resizeBoardersWith(Interval currentInterval) {
        if (start >= currentInterval.getStart())
            setStart(currentInterval.getStart());
        if (end <= currentInterval.getEnd())
            setEnd(currentInterval.getEnd());
    }
}
