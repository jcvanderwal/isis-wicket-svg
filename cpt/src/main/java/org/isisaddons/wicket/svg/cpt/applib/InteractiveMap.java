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
package org.isisaddons.wicket.svg.cpt.applib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import com.google.common.base.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Value;

@Value(semanticsProviderClass = InteractiveMapSemanticsProvider.class)
public class InteractiveMap implements Serializable {

    private static final long serialVersionUID = 1L;

    private String svg;

    private String title;

    private List<InteractiveMapElement> elements;

    public InteractiveMap(String svg) {
        this.svg = svg;
        this.elements = new ArrayList<>();
    }

    public InteractiveMap() {
        // TODO Auto-generated constructor stub
    }

    public String title() {
        return title != null ? title : "Interactive Map";
    }

    @Programmatic
    public String getSvg() {
        return svg;
    }

    public List<InteractiveMapElement> getElements() {
        return elements;
    }

    public void addElement(InteractiveMapElement element) {
        elements.add(element);
    }

    public String parse() {
        Document doc = Jsoup.parse(getSvg());
        OutputSettings settings = new OutputSettings();
        settings.prettyPrint(false);
        doc.outputSettings(settings);
        for (InteractiveMapElement element : getElements()) {
            Element domElement = doc.getElementById(element.getId());

            if (domElement != null) {
                for (InteractiveMapAttribute attribute : element.getAttributes()) {
                    String attributeName = attribute.getName();
                    if ("fill".equals(attributeName)) {
                        final String cssAttributes = Strings.nullToEmpty(domElement.attr("style"));
                        domElement.attr("style", replaceCssAttribute(cssAttributes, attributeName, attribute.getValue()));
                    } else if ("xlink:href".equals(attributeName)) {
                        final String cssAttributes = Strings.nullToEmpty(domElement.attr("style"));
                        domElement.attr("style", replaceCssAttribute(cssAttributes, "cursor", "pointer"));
                        domElement.attr("onclick", "document.location.href='"+attribute.getValue()+"';");
                    } else {
                        domElement.attr(attributeName, attribute.getValue());
                    }
                }
                int i = 0;
                for (String value : element.getValues()) {
                    final Elements tspans = domElement.getElementsByTag("tspan");
                    if (tspans.size() > i) {
                        tspans.get(i).text(value);
                    }
                    i++;
                }
            }
        }
        return doc.outerHtml();
    }

    private String replaceCssAttribute(String cssAttributes, String key, String value) {
        Map<String, String> oldAttrsMap = Splitter.on(';').withKeyValueSeparator(':').split(cssAttributes);
        TreeMap<String, String> map = new TreeMap<>(oldAttrsMap);
        map.put(key, value);
        return Joiner.on(';').withKeyValueSeparator(":").join(map);
    }
}
