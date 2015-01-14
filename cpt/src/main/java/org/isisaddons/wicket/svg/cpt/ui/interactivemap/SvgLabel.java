package org.isisaddons.wicket.svg.cpt.ui.interactivemap;

import org.apache.wicket.markup.html.basic.Label;
import org.isisaddons.wicket.svg.cpt.applib.InteractiveMap;

/**
 * A component that renders a SVG document
 */
public class SvgLabel extends Label {

    /**
     * Constructor
     *
     * @param id The component id
     * @param interactiveMap The SVG map to render
     */
    public SvgLabel(final String id, final InteractiveMap interactiveMap) {
        super(id, interactiveMap.parse());

        setEscapeModelStrings(false);
    }
}
