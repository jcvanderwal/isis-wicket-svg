package org.isisaddons.wicket.svg.cpt.applib;

import java.io.Serializable;

public class InteractiveMapAttribute implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String name;
    
    private String value;
    
    public InteractiveMapAttribute(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    
    public String getValue() {
        return value;
    }
    
}
