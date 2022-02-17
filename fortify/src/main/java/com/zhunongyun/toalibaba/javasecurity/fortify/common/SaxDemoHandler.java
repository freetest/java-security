package com.zhunongyun.toalibaba.javasecurity.fortify.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX 解析 xml 的 handler
 * @author oscar
 */
@Data
@Slf4j
public class SaxDemoHandler extends DefaultHandler {

    private static final Logger logger = LoggerFactory.getLogger(SaxDemoHandler.class);

    private JSONArray jsonArray;
    private JSONObject jsonObject;
    /**
     * 记录当前解析到的节点名称
     */
    private String currentTag;

    /**
     * 遍历xml文件开始标签
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        jsonArray = new JSONArray();
        log.info("sax 解析开始");
    }

    /**
     * 遍历xml文件结束标签
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        log.info("sax 解析结束");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        jsonObject = new JSONObject();
        log.info("SAX 解析结点开始");
        currentTag = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        jsonArray.add(jsonObject);
        log.info("SAX 解析结点结束");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String value = new String(ch,start,length);
        if (jsonObject.containsKey(currentTag)) {
            value += jsonObject.get(currentTag);
        }

        jsonObject.put(currentTag, value);
    }
}