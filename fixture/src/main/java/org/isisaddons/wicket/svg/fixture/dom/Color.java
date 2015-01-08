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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Color color1 = (Color) o;

        if (!color.equals(color1.color)) {
            return false;
        }
        if (!label.equals(color1.label)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = color.hashCode();
        result = 31 * result + label.hashCode();
        return result;
    }
}
