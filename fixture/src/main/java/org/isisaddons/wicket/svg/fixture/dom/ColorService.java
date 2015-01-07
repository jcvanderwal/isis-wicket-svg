package org.isisaddons.wicket.svg.fixture.dom;

public class ColorService {
        
    public Color getColor(SvgWicketToDoItem item) {
        if (item.isComplete()) {
            return new Color("green", "Completed");
        }
        return new Color("red", "Not completed");
    }

}
