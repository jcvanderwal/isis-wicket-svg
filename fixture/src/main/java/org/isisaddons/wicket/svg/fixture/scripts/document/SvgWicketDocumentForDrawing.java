package org.isisaddons.wicket.svg.fixture.scripts.document;


public class SvgWicketDocumentForDrawing extends SvgWicketDocumentAbstract {

    public static final String NAME = "drawing.svg";

    @Override
    protected void execute(ExecutionContext executionContext) {
        createDocument(NAME);
    }

}
