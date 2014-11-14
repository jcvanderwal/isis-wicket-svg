package org.isisaddons.wicket.svg.cpt.ui.interactivemap.panzoom;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.util.Json;

import org.apache.wicket.Component;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 *
 */
public class PanZoomConfig extends AbstractConfig {

    // in ms
    private static final IKey<Long> Duration = newKey("duration", 200L);

    private static final IKey<Json.RawValue> $ZoomIn = newKey("$zoomIn", new Json.RawValue("$()"));

    private static final IKey<Json.RawValue> $ZoomOut = newKey("$zoomOut", new Json.RawValue("$()"));

    public PanZoomConfig withDuration(org.apache.wicket.util.time.Duration duration) {
        put(Duration, duration.getMilliseconds());
        return this;
    }

    public PanZoomConfig withZoomIn(Component zoomIn) {
        put($ZoomIn, new Json.RawValue($(zoomIn).build()));
        return this;
    }

    public PanZoomConfig withZoomOut(Component zoomOut) {
        put($ZoomOut, new Json.RawValue($(zoomOut).build()));
        return this;
    }
}
