package org.isisaddons.wicket.svg.fixture.dom;

public class DatesDueColorService implements ColorService {

    private static final Color DUE = new Color("red", "Due");
    private static final Color NOT_DUE = new Color("lightgreen", "Not due");

    public Color getColor(SvgWicketToDoItem item) {
        if (item.isDue()) {
            return DUE;
        }
        return NOT_DUE;
    }

}
