package org.isisaddons.wicket.svg.fixture.dom;

public class StatusColorService implements ColorService {

    private static final Color COMPLETED = new Color("green", "Completed");
    private static final Color NOT_COMPLETED = new Color("red", "Not completed");

    public Color getColor(SvgWicketToDoItem item) {
        if (item.isComplete()) {
            return COMPLETED;
        }
        return NOT_COMPLETED;
    }

}
