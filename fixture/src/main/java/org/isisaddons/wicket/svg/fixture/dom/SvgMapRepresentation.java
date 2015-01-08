package org.isisaddons.wicket.svg.fixture.dom;

public enum SvgMapRepresentation {
    COMPLETED {
        @Override
        public ColorService getColorService() {
            return new StatusColorService();
        }
    },
    DATES_DUE {
        @Override
        public ColorService getColorService() {
            return new DatesDueColorService();
        }
    };

    public abstract ColorService getColorService();
}
