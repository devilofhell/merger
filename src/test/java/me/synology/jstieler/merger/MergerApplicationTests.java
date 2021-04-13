package me.synology.jstieler.merger;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class MergerApplicationTests {

    @Test
    void contextLoads() {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(25, 30));
        input.add(new Interval(2, 19));
        input.add(new Interval(14, 23));
        input.add(new Interval(4, 8));

        List<Interval> output = IntervalMerger.mergeIntervals(input);

        assertThat(output.size()).isEqualTo(2);
        assertThat(output.get(0).isPartOf(output.get(1))).isFalse();
    }

}
