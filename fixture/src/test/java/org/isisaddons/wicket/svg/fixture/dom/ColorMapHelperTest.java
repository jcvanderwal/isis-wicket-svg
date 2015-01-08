package org.isisaddons.wicket.svg.fixture.dom;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ColorMapHelperTest {

    @Test
    public void sortByValue() {
        Map<Color, Integer> map0 = new HashMap<>();
        final Color green = new Color("green", "Green");
        final Color red = new Color("red", "Red");

        final Map<Color, Integer> map1 = ColorMapHelper.addToMap(map0, green);
        final Map<Color, Integer> map2 = ColorMapHelper.addToMap(map1, red);
        final Map<Color, Integer> map3 = ColorMapHelper.addToMap(map2, red);

        assertThat(map3.get(green), is(1));
        assertThat(map3.get(red), is(2));
        assertThat(map3.size(), is(2));

        assertThat(ColorMapHelper.sortByValue(map3).get(0), is(red));
    }
}
