package org.isisaddons.wicket.svg.cpt.ui.interactivemap.panzoom;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 *
 */
public class PanZoomBehavior extends Behavior {

    private PanZoomConfig config = new PanZoomConfig();

    public PanZoomConfig getConfig() {
        return config;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        response.render(JavaScriptHeaderItem.forReference(new JQueryPluginResourceReference(PanZoomBehavior.class, "js/jquery.panzoom.js")));
        response.render(OnDomReadyHeaderItem.forScript($(component).chain("panzoom", config).get()));
    }
}
