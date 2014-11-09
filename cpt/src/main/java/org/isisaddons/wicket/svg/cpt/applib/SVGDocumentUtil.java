package org.isisaddons.wicket.svg.cpt.applib;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

public class SVGDocumentUtil {
    
    public static SVGDocument documentFromBytes(byte[] bytes) {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        String uri = "http://www.example.org/diagram.svg";
        final String ns = SVGDOMImplementation.SVG_NAMESPACE_URI;
        final String root = "svg";
        try {
            final SVGDocument d = (SVGDocument) f.createDocument(ns, root, uri, is);
            return d;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static  SVGDocument documentFromString(String string) {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        StringReader reader = new StringReader(string);
        String uri = "http://www.example.org/diagram.svg";
        final String ns = SVGDOMImplementation.SVG_NAMESPACE_URI;
        final String root = "svg";
        try {
            final SVGDocument d = (SVGDocument) f.createDocument(uri, reader);
            return d;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


}
