package com.zhunongyun.toalibaba.securitystudy.fortify.controller;

import com.alibaba.fastjson.JSONArray;
import com.zhunongyun.toalibaba.securitystudy.fortify.common.SaxDemoHandler;
import com.zhunongyun.toalibaba.securitystudy.fortify.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * XXE漏洞
 * @author oscar
 */
@Slf4j
public class XmlExternalEntityController {

    public static void main(String[] args) {
        XmlExternalEntityController xml = new XmlExternalEntityController();

        // XXE 漏洞
        xml.parseXml();
    }

    private void parseXml() {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            SaxDemoHandler saxDemoHandler = new SaxDemoHandler();

            saxParser.parse(XmlExternalEntityController.class.getResourceAsStream("/static/xxe.xml"),
                    saxDemoHandler);
            JSONArray jsonArray = saxDemoHandler.getJsonArray();
            log.info("输出结果:{}", jsonArray);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }

    }

    /** 
     * @description: 修复后的 xxe 
     * @param: file 上传的文件
     * @return: ResponseVO
     * @author oscar
     * @date: 2021/12/16 10:01
     */
    @PostMapping("parseFix")
    public ResponseVO parseXmlFix(@RequestParam("file") MultipartFile file) {
        JSONArray jsonArray = new JSONArray();
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

            // to be compliant, completely disable DOCTYPE declaration:
//            saxParserFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            // or completely disable external entities declarations:
//            saxParserFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
//            saxParserFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

            SAXParser saxParser = saxParserFactory.newSAXParser();
            SaxDemoHandler saxDemoHandler = new SaxDemoHandler();

            saxParser.parse(file.getInputStream(), saxDemoHandler);
            jsonArray = saxDemoHandler.getJsonArray();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }

        return ResponseVO.success(jsonArray);
    }
}