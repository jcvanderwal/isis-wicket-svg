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

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
        this.elements = new ArrayList<InteractiveMapElement>();
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
        try {
            String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
            String uri = "http://www.example.org/diagram.svg";
            Document doc = f.createDocument(uri, new StringReader(getSvg()));

            for (InteractiveMapElement element : elements) {
                Element domElement = doc.getElementById(element.getId());
                if (domElement != null) {
                    for (InteractiveMapAttribute attribute : element.getAttributes()) {
                        domElement.setAttribute(attribute.getName(), attribute.getValue());
                    }
                }
            }
            return getStringFromDocument(doc);
        } catch (IOException ex) {
            // ...
        }
        return null;
    }
    
    private String getStringFromDocument(Document doc)
    {
        try
        {
           DOMSource domSource = new DOMSource(doc);
           StringWriter writer = new StringWriter();
           StreamResult result = new StreamResult(writer);
           TransformerFactory tf = TransformerFactory.newInstance();
           Transformer transformer = tf.newTransformer();
           transformer.transform(domSource, result);
           return writer.toString();
        }
        catch(TransformerException ex)
        {
           ex.printStackTrace();
           return null;
        }
    } 

}
