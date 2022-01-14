package com.zhunongyun.toalibaba.securitystudy.fortify.controller;

import org.apache.poi.xssf.extractor.XSSFExportToXml;
import org.apache.poi.xssf.usermodel.XSSFMap;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * poi 组件 XXE 漏洞
 * @author oscar
 */
public class PoiLeak {


    public static void main(String[] args) {
        PoiLeak poiLeak = new PoiLeak();

        // CVE_2019_12415
        poiLeak.CVE_2019_12415();

        //
    }

    /**
     * CVE-2019-12415
     * 修复方案
     *
     * 1. 升级 poi 版本>= 4.1.1
     * 2. 不要使用 XSSFExportToXml 类,或者不要让 exportToXML 方法的第二个参数为 true
     */
    private void CVE_2019_12415() {
        try {
            XSSFWorkbook wb = new XSSFWorkbook(PoiLeak.class.getResourceAsStream("/static/my_excel_1.xlsx"));
            for (XSSFMap map : wb.getCustomXMLMappings()) {
                XSSFExportToXml exporter = new XSSFExportToXml(map);
                // 使用 XSSFExportToXml 将 xlsx 转成 xml
                // 第一个参数是输出流无所谓，第二个参数要为 true
                exporter.exportToXML(System.out, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}