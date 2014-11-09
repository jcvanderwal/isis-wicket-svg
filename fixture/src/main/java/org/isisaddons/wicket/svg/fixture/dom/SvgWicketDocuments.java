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
package org.isisaddons.wicket.svg.fixture.dom;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.value.Blob;

@DomainService(menuOrder = "10")
public class SvgWicketDocuments {

    public SvgWicketDocuments() {
    }

    public String getId() {
        return "documents";
    }

    public String iconName() {
        return "Document";
    }

    public List<SvgWicketDocument> allDocuments() {
        return container.allInstances(SvgWicketDocument.class);
    }

    public SvgWicketDocument newDocument(final @Named("Name") String name, final @Named("File") Blob file) {
        SvgWicketDocument document = container.newTransientInstance(SvgWicketDocument.class);
        document.setName(name);
        document.setFile(file);
        container.persist(document);
        return document;
    }

    // //////////////////////////////////////

    @javax.inject.Inject
    private DomainObjectContainer container;

}
