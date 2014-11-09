package org.isisaddons.wicket.svg.fixture.scripts.todo;


public class SvgWicketDocumentForTaj extends SvgWicketDocumentAbstract {

    public static final String NAME = "taj_floorplan.svg";

    @Override
    protected void execute(ExecutionContext executionContext) {
        createDocument(NAME);
    }

}
