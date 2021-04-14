package me.synology.jstieler.merger;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class MergerApplicationTests {

    @Test
    void testGivenArguments() {
        String[] input = {"25,30", "2,19", "14,23", "4,8"};

        List<Interval> output = IntervalMerger.mergeIntervals(input);

        assertThat(output.size()).isEqualTo(2);
        assertThat(output.get(0).isPartOf(output.get(1))).isFalse();
        assertThat(IntervalMerger.getIntervalAsString(output)).isEqualTo("[2,23][25,30]");
    }

    @Test
    void testEmptyArguments() {
        String[] input = {};

        List<Interval> output = IntervalMerger.mergeIntervals(input);

        assertThat(output.size()).isEqualTo(0);
        assertThat(IntervalMerger.getIntervalAsString(output)).isEqualTo("");
    }

    @Test
    void testSingleArgument() {
        String[] input = {"3,6"};

        List<Interval> output = IntervalMerger.mergeIntervals(input);

        assertThat(output.size()).isEqualTo(1);
        assertThat(IntervalMerger.getIntervalAsString(output)).isEqualTo("[3,6]");
    }

    @Test
    void testRemoveDuplicate() {
        String[] input = {"3,6", "3,6"};

        List<Interval> output = IntervalMerger.mergeIntervals(input);

        assertThat(output.size()).isEqualTo(1);
        assertThat(IntervalMerger.getIntervalAsString(output)).isEqualTo("[3,6]");
    }

    @Test
    void testZeroInterval() {
        String[] input = {"1,1"};

        List<Interval> output = IntervalMerger.mergeIntervals(input);

        assertThat(output.size()).isEqualTo(1);
        assertThat(IntervalMerger.getIntervalAsString(output)).isEqualTo("[1,1]");
    }

    @Test
    void testIgnoreBadArguments() {

        String[] input = {"1,5", "2,5412547893214578924"};

        List<Interval> output = IntervalMerger.mergeIntervals(input);

        assertThat(output.size()).isEqualTo(1);
        assertThat(IntervalMerger.getIntervalAsString(output)).isEqualTo("[1,5]");
    }

    @Test
    void testNegativeArguments() {

        String[] input = {"-5,5", "-12,-3"};

        List<Interval> output = IntervalMerger.mergeIntervals(input);

        assertThat(output.size()).isEqualTo(1);
        assertThat(IntervalMerger.getIntervalAsString(output)).isEqualTo("[-12,5]");
    }

    @Test
    void testMultipleResults() {

        String[] input = {"[25,30]", "(2,19)", "14,23", "4,8", "31,32", "31,47", "46,66", "22,26"};

        List<Interval> output = IntervalMerger.mergeIntervals(input);

        assertThat(output.size()).isEqualTo(2);
        assertThat(IntervalMerger.getIntervalAsString(output)).isEqualTo("[2,30][31,66]");
    }

}
