/*
 *  Copyright 2014 Jeroen van der Wal
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.isisaddons.wicket.svg.cpt.ui.interactivemap;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.viewer.wicket.model.models.ValueModel;
import org.apache.isis.viewer.wicket.ui.panels.PanelAbstract;
import org.apache.isis.viewer.wicket.ui.panels.PanelUtil;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.resource.JQueryPluginResourceReference;
import org.isisaddons.wicket.svg.cpt.applib.Color;
import org.isisaddons.wicket.svg.cpt.applib.InteractiveMap;

import com.google.common.collect.Lists;

public class StandaloneValueAsInteractiveMap extends PanelAbstract<ValueModel> {

    private static final long serialVersionUID = 1L;

    public StandaloneValueAsInteractiveMap(final String id, final ValueModel valueModel) {
        super(id, valueModel);

        buildGui();
    }

    private void buildGui() {

        final ValueModel model = getModel();
        final ObjectAdapter mapAdapter = model.getObject();
        final Object mapObj = mapAdapter.getObject();
        InteractiveMap map = (InteractiveMap) mapObj;

        addOrReplace(new SvgLabel("interactiveMap", map));

        addLegend(map);

        addLinkList(map);
    }

    private void addLinkList(InteractiveMap map) {
        final Map<String, String> elementTitle2Id = map.getElementTitle2Id();
        List<String> titles = Lists.newArrayList(elementTitle2Id.keySet());
        Collections.sort(titles);
        final ListView<String> sortedTitlesView = new ListView<String>("links", titles) {
            @Override
            protected void populateItem(ListItem<String> item) {
                final String title = item.getModelObject();
                item.add(new Label("link", title) {
                    @Override
                    protected void onComponentTag(ComponentTag tag) {
                        super.onComponentTag(tag);
                        tag.put("data-ref-id", elementTitle2Id.get(title));
                    }
                });
            }
        };
        addOrReplace(sortedTitlesView);
    }

    private void addLegend(InteractiveMap map) {
        List<Color> legend = map.getLegend();
        final ListView<Color> legendView = new ListView<Color>("legend", legend) {
            @Override
            protected void populateItem(ListItem<Color> item) {
                final Color legendItem = item.getModelObject();
                item.add(new Label("item", legendItem.getLabel()) {
                    @Override
                    protected void onComponentTag(ComponentTag tag) {
                        super.onComponentTag(tag);
                        tag.append("style", "background-color:" + legendItem.getColor(), ";");
                        tag.put("data-ref-class", legendItem.getLabel());
                    }
                });
            }
        };
        addOrReplace(legendView);
    }

    @Override
    protected void onModelChanged() {
        buildGui();
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        Class<StandaloneValueAsInteractiveMap> cls = StandaloneValueAsInteractiveMap.class;
        PanelUtil.renderHead(response, cls);
        response.render(JavaScriptHeaderItem.forReference(new JQueryPluginResourceReference(cls, cls.getSimpleName() + ".js")));
    }
}
