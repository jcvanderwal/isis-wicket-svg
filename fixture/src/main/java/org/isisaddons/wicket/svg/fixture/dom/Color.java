package org.isisaddons.wicket.svg.fixture.dom;

public class Color {
    
    final private String color;
    
    final private String label;
    
    public Color(String color, String label) {
        this.color = color;
        this.label = label;
    }

    public String getColor() {
        return color;
    }
    
    public String getLabel() {
        return label;
    }
    
}
