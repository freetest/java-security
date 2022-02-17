package com.zhunongyun.toalibaba.javasecurity.fortify.controller;

import com.zhunongyun.toalibaba.javasecurity.fortify.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 文件上传和下载
 *
 * @author oscar
 */
@Slf4j
@RestController
@RequestMapping("file")
public class FileController {
    /**
     * 下载任意文件,固定目录+文件名形式
     * 测试用例
     * GET  localhost:8080/file/download?fileName=C%3A%5CWindows%5CSystem32%5Cdrivers%5Cetc%5Chosts
     *
     * @param response
     * @param fileName
     */
    @GetMapping(value = "download")
    public void downloadFile(HttpServletResponse response, @RequestParam("fileName") String fileName) {
        /**
         * 在 /Users/oscar/Downloads/test/download/file/ 目录下存在多个 txt 文件
         * 正常的业务场景是，前端传入fileName文件名（例如 test.txt），下载/Users/oscar/Downloads/test/download/file/下对应的文件
         * 黑客在攻击时会可能会传入一个文件路径， 例如 ../../../../etc/passwd等路径，这样就能轻松下载服务器的 passwd 文件
         */
        this.handlerDownloadFile(response, fileName);
    }

    /**
     * 上传任意文件
     *
     * @param file 上传文件
     * @return
     */
    @PostMapping("/upload")
    public ResponseVO upload(@RequestParam("file") MultipartFile file) {
        /**
         * 此处文件上传存在四处安全问题
         * 1. 未校验文件格式（包含校验文件后缀 和 校验文件内容中的文件格式）
         * 2. 将上传的文件上传至容器能解析位置
         * 3. 文件未重命名，重命名后增加黑客攻击难度
         * 4. 将文件上传地址信息返回前端
         */
        try {
            FileCopyUtils.copy(file.getBytes(), new File("static/" + file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseVO.success("文件保存路径:static/xx.xx");
    }

    /**
     * 下载文件公共方法
     *
     * @param response
     * @param filePath
     */
    private void handlerDownloadFile(HttpServletResponse response, String filePath) {

        try (InputStream f = new FileInputStream(new File(filePath));
             BufferedInputStream bis = new BufferedInputStream(f);
             BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {

            response.setContentType("application/text;charset=utf-8");
            // 下载文件的名称
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(("测试下载文件").getBytes("gbk"), StandardCharsets.ISO_8859_1));

            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bos.flush();
        } catch (IOException e) {
            log.error("文件下载,读取文件出现异常:{}", e.getMessage());
        }
    }
}