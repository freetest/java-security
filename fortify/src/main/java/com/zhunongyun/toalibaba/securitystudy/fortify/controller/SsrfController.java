package com.zhunongyun.toalibaba.securitystudy.fortify.controller;

import com.zhunongyun.toalibaba.securitystudy.fortify.enums.ResponseCodeEnum;
import com.zhunongyun.toalibaba.securitystudy.fortify.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * SSRF 漏洞
 *
 * @author oscar
 * @date 2022/1/12 15:49
 */
@Slf4j
@RequestMapping("ssrf")
@RestController
public class SsrfController {

    private static final String ERROR_MSG = "访问 url:{}, 发生未知异常";

    private static final String ERROR_RESPONSE_MSG = "访问URL发生异常";

    @GetMapping("httpUrlConnection")
    public ResponseVO httpUrlConnection(@RequestParam("url") String url) {
        try {
            // 创建一个连接
            URLConnection urlConnection = new URL(url).openConnection();
            // 强转为 HttpURLConnection
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 获取url中的资源
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));

            StringBuilder stringBuilder = new StringBuilder();
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                stringBuilder.append(data);
            }
            return ResponseVO.success(stringBuilder.toString());
        } catch (IOException e) {
            log.error(ERROR_MSG, url, e);
            return ResponseVO.fail(ResponseCodeEnum.FAIL, ERROR_RESPONSE_MSG);
        }
    }

    @GetMapping("urlConnection")
    public ResponseVO urlConnection(@RequestParam("url") String url, HttpServletResponse response) {
        URLConnection urlConnection = null;
        try {
            urlConnection = new URL(url).openConnection();
        } catch (IOException e) {
            log.error(ERROR_MSG, url, e);
            return ResponseVO.fail(ResponseCodeEnum.FAIL, ERROR_RESPONSE_MSG);
        }

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8))) {

            StringBuilder stringBuilder = new StringBuilder();
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                stringBuilder.append(data);
            }
            return ResponseVO.success(stringBuilder.toString());
        } catch (IOException e) {
            log.error(ERROR_MSG, url, e);
            return ResponseVO.fail(ResponseCodeEnum.FAIL, ERROR_RESPONSE_MSG);
        }
    }

    @GetMapping("imageIO")
    public void imageIO(@RequestParam("url") String url, HttpServletResponse response) {
        try (ServletOutputStream outputStream = response.getOutputStream();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             InputStream inputStream = new URL(url).openStream();
             ImageInputStream imageInputStream =  ImageIO.createImageInputStream(inputStream)) {

            BufferedImage bufferedImage = ImageIO.read(imageInputStream);
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

            try (InputStream input = new ByteArrayInputStream(byteArrayOutputStream.toByteArray())) {
                int len;
                byte[] bytes = new byte[1024];
                while ((len = input.read(bytes)) > 0) {
                    outputStream.write(bytes, 0, len);
                }
            }
        } catch (Exception e) {
            log.error(ERROR_MSG, url, e);
        }
    }

    @GetMapping("httpClients")
    public ResponseVO httpClient(@RequestParam("url") String url) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return ResponseVO.success(EntityUtils.toString(entity));
        } catch (IOException e) {
            log.error(ERROR_MSG, url, e);
            return ResponseVO.fail(ResponseCodeEnum.FAIL, ERROR_RESPONSE_MSG);
        }
    }

    @GetMapping("okHttp")
    public ResponseVO okHttp(@RequestParam("url") String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return ResponseVO.success(response.body().string());
        } catch (IOException e) {
            log.error(ERROR_MSG, url, e);
            return ResponseVO.fail(ResponseCodeEnum.FAIL, ERROR_RESPONSE_MSG);
        }

    }
}