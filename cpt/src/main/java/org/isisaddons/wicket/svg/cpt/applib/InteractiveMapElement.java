package org.isisaddons.wicket.svg.cpt.applib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InteractiveMapElement implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;

    private final String label;

    private final List<InteractiveMapAttribute> attributes;

    public InteractiveMapElement(String id, String label) {
        this.id = id;
        this.label = label;
        attributes = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public List<InteractiveMapAttribute> getAttributes() {
        return attributes;
    }

    public void addAttribute(InteractiveMapAttribute attribute) {
        attributes.add(attribute);
    }

}
