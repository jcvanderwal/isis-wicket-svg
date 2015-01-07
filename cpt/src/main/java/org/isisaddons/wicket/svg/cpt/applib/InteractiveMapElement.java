package org.isisaddons.wicket.svg.cpt.applib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class InteractiveMapElement implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;

    private final List<String> values;

    private final List<InteractiveMapAttribute> attributes;

    public InteractiveMapElement(String id, List<String> value) {
        this.id = id;
        this.values = value;
        attributes = new ArrayList<>();
    }

    public InteractiveMapElement(String id, String value) {
        this.id = id;
        this.values = Lists.newArrayList(value);
        attributes = new ArrayList<>();
    }

    public InteractiveMapElement(String id) {
        this.id = id;
        this.values = Lists.newArrayList();
        attributes = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<String> getValues() {
        return values;
    }

    public List<InteractiveMapAttribute> getAttributes() {
        return attributes;
    }

    public void addAttribute(InteractiveMapAttribute attribute) {
        attributes.add(attribute);
    }

}
