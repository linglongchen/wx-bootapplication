package com.modules.system.weixin.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class XMLUtil {
    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     * @param strxml
     * @return
     * @throws JDOMException
     * @throws IOException
     * @throws DocumentException
     */
    public static Map doXMLParse(String strxml) throws IOException, DocumentException {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
        if(null == strxml || "".equals(strxml)) {
            return null;
        }
        Map m = new TreeMap();
        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXReader builder = new SAXReader();
        Document doc =  builder.read(in);
        Element root = doc.getRootElement();
        Iterator it = root.elementIterator();
        while(it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = e.getText();
//        List children = e.getChildren();
//        if(children.isEmpty()) {
//            v = e.getTextNormalize();
//        } else {
//            v = XMLUtil.getChildrenText(children);
//        }
            m.put(k, v);
        }
        //关闭流
        in.close();
        return m;
    }

    public static void main(String[] args) throws IOException, DocumentException {
        String xml="<xml><id>555</id>"+
                "<name>hello</name></xml>";
        Map<String,Object>m=XMLUtil.doXMLParse(xml);
        System.out.println(m);
    }
}
