package org.isisaddons.wicket.svg.cpt.applib;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class InteractiveMapTest {

    @Test
    @Ignore
    public void testReplaceFill() {
        // given
        String svgBefore = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"400\" height=\"450\"> <rect id=\"1\" x=\"10\" y=\"20\" width=\"100\" height=\"50\" fill=\"red\"/></svg>";
        InteractiveMap map = new InteractiveMap(svgBefore);
        InteractiveMapElement element = new InteractiveMapElement("1", "Test");
        element.addAttribute(new InteractiveMapAttribute("fill", "green"));
        map.addElement(element);
        // when

        // then
        final String parse = map.parse();
        assertTrue(parse.contains("fill=\"green\""));

    }

    @Test
    public void testReplaceCssFill() {
        // given
        String svgBefore = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"400\" height=\"450\"> <rect id=\"1\" x=\"10\" y=\"20\" width=\"100\" height=\"50\" style=\"fill:red;stroke:something\"/></svg>";
        InteractiveMap map = new InteractiveMap(svgBefore);
        InteractiveMapElement element = new InteractiveMapElement("1", "Test");
        element.addAttribute(new InteractiveMapAttribute("fill", "green"));
        map.addElement(element);
        // when

        // then
        final String parse = map.parse();
        assertTrue(parse.contains("fill:green;stroke:something"));

    }

}
