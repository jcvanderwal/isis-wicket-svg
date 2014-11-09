/*
 *  Copyright 2013~2014 Dan Haywood
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
package org.isisaddons.wicket.svg.fixture.app;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotContributed;

import org.isisaddons.wicket.svg.cpt.applib.InteractiveMap;
import org.isisaddons.wicket.svg.cpt.applib.InteractiveMapAttribute;
import org.isisaddons.wicket.svg.cpt.applib.InteractiveMapElement;
import org.isisaddons.wicket.svg.fixture.dom.SvgWicketDocument;
import org.isisaddons.wicket.svg.fixture.dom.SvgWicketDocuments;
import org.isisaddons.wicket.svg.fixture.dom.SvgWicketToDoItem;
import org.isisaddons.wicket.svg.fixture.dom.SvgWicketToDoItems;

@Named("Interactive Maps")
@DomainService(menuOrder = "15")
public class SvgWicketService {

    @ActionSemantics(Of.SAFE)
    @NotContributed
    public InteractiveMap showMap(SvgWicketDocument document) {

        String svgString;
        try {
            svgString = new String(document.getFile().getBytes(), "UTF-8");
            InteractiveMap interactiveMap = new InteractiveMap(svgString);
            Integer i = 1;
            for (SvgWicketToDoItem toDoItem : toDoItems.allToDos()) {
                InteractiveMapElement element = new InteractiveMapElement(i.toString(), toDoItem.getDescription());
                element.addAttribute(new InteractiveMapAttribute("fill", toDoItem.isComplete() ? "green" : "red"));
                interactiveMap.addElement(element);
                i++;
            }

            return interactiveMap;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public List<SvgWicketDocument> choices0ShowMap() {
        return documents.allDocuments();
    }

    // //////////////////////////////////////

    @ActionSemantics(Of.SAFE)
    public InteractiveMap showFirstMap() {
        final List<SvgWicketDocument> collection = documents.allDocuments();
        if (collection.size() == 0) {
            return null;
        }
        return showMap(collection.get(0));
    }

    // //////////////////////////////////////

    @Inject
    private SvgWicketDocuments documents;

    @Inject
    private SvgWicketToDoItems toDoItems;

}
