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

import org.apache.wicket.markup.html.basic.Label;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.viewer.wicket.model.models.ValueModel;
import org.apache.isis.viewer.wicket.ui.panels.PanelAbstract;

import org.isisaddons.wicket.svg.cpt.applib.InteractiveMap;

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

        
        addOrReplace(new Label("interactiveMap", map.parse()).setEscapeModelStrings(false));
        
    }

    @Override
    protected void onModelChanged() {
        buildGui();
    }

}
