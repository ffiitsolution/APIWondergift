/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.ffi.wondergift;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import static java.lang.System.out;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author IT
 */
@RestController
public class IndexController {

    @Value("${pos.code}")
    private String pos;

    @Value("${url.verified}")
    private String urlVerified;

    @Value("${url.redeeemcode}")
    private String urlRedemCode;

    @RequestMapping(value = "/version")
    public @ResponseBody
    Map<String, Object> tes() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("VERSION", "ITD FFI 2023 1.0.1");
        return map;
//        String json = "{\n"
//                + "  \"status\": \"200\",\n"
//                + "  \"message\": \"Voucher sah\",\n"
//                + "  \"data\": [\n"
//                + "    {\n"
//                + "      \"itemcode\": \"1077\",\n"
//                + "      \"itemname\": \"Charge TA\",\n"
//                + "      \"itemvariant\": \"Promo\",\n"
//                + "      \"itemprice\": 0,\n"
//                + "      \"vouchervalue\": 0,\n"
//                + "      \"discountvalue\": 0,\n"
//                + "      \"sign\": \"2f9263e02d5e06cf31599516599e1cba\"\n"
//                + "    },\n"
//                + "    {\n"
//                + "      \"itemcode\": \"12345\",\n"
//                + "      \"itemname\": \"F.Bucket Tin 1Pc Chicken\",\n"
//                + "      \"itemvariant\": \"Promo\",\n"
//                + "      \"itemprice\": 0,\n"
//                + "      \"vouchervalue\": 0,\n"
//                + "      \"discountvalue\": 0,\n"
//                + "      \"sign\": \"2f9263e02d5e06cf31599516599e1cba\"\n"
//                + "    }\n"
//                + "  ]\n"
//                + "}";
//
//        JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);
//        String result = convertedObject.toString();

        //       return result
    }

    @RequestMapping(value = "/post-wondergift-action", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Contoh Response list yang di Tampilkan", response = Object.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "The resource not found")
    }
    )
    public @ResponseBody
    Map<String, Object> postWondergift(@RequestBody String param) throws AuthenticationException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Gson gson = new Gson();
            String uri = urlVerified;
            Map<String, String> balance = gson.fromJson(param, new TypeToken<Map<String, Object>>() {
            }.getType());
            HttpClient client = HttpClientBuilder.create().build();

            HttpPost post = new HttpPost(uri);
            post.setHeader("Authorization", "Basic SDdnbHJVcGp2Qlk5TToyeTEwUlhyRzEwN3ZNMHNtdjU1UEVRR1J0ZWRWeEtxTGFYMzd0M2ZISHR3SDRHRldaNDVCN09EYQ==");
            post.setHeader("Content-type", "application/json");
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("rt", balance.get("rt"));
            jsonMap.put("vc", balance.get("vc"));
            String JsonString = gson.toJson(jsonMap);

            System.out.println(JsonString);
            StringEntity entity = new StringEntity(JsonString);
            post.setEntity(entity);
            HttpResponse response = client.execute(post);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            (response.getEntity().getContent())
                    )
            );

            StringBuilder content = new StringBuilder();
            String line;
            while (null != (line = br.readLine())) {
                content.append(line);
            }
            String result = content.toString();

//            map = gson.fromJson(result, new TypeToken<Map<String, Object>>() {
//            }.getType());
////            if (map.get("message").equals("Voucher sah")) {
////                map.put("status", "gohan");
////                map.put("data1", "{\n"
////                        + "\"itemcode\": \"1234\"\n"
////                        + "\"itemname\": \"Chicken Wing\"\n"
////                        + "\"itemvariant\": \"Original\"\n"
////                        + "\"itemprice\": \"0\"\n"
////                        + "\"vouchervalue\": \"\"\n"
////                        + "\"discountvalue\": \"\"\n"
////                        + "\"sign\": \"250a70af4df14695ae1fa6abfe3d9de7 \"\n"
////                        + " }");
////            } else {
////                map.put("status", "son goku Ss");
////                map.put("dona", "COBA RESPON");
////                map.get("data");
////                
////                List test = (List) map.get("data");
////                
////                map.put("data1", "{\n"
////                        + "\"itemcode\": \"1234\"\n"
////                        + "\"itemname\": \"Chicken Wing\"\n"
////                        + "\"itemvariant\": \"Original\"\n"
////                        + "\"itemprice\": \"0\"\n"
////                        + "\"vouchervalue\": \"\"\n"
////                        + "\"discountvalue\": \"\"\n"
////                        + "\"sign\": \"250a70af4df14695ae1fa6abfe3d9de7 \"\n"
////                        + " }");
////            }
//
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        System.out.println(map);
//
//        return map;
            System.out.println(result);

            map = gson.fromJson(result, new TypeToken<Map<String, Object>>() {
            }.getType());

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(map);
        return map;

    }

    @RequestMapping(value = "/post-wondergift-redeem", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Contoh Response list yang di Tampilkan", response = Object.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "The resource not found")
    }
    )
    public @ResponseBody
    Map<String, Object> redeemWondergift(@RequestBody String param) throws AuthenticationException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Gson gson = new Gson();
            String uri = urlRedemCode;
            Map<String, String> balance = gson.fromJson(param, new TypeToken<Map<String, Object>>() {
            }.getType());
            HttpClient client = HttpClientBuilder.create().build();

            HttpPost post = new HttpPost(uri);
            post.setHeader("Authorization", "Basic SDdnbHJVcGp2Qlk5TToyeTEwUlhyRzEwN3ZNMHNtdjU1UEVRR1J0ZWRWeEtxTGFYMzd0M2ZISHR3SDRHRldaNDVCN09EYQ==");
            post.setHeader("Content-type", "application/json");
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("rt", balance.get("rt"));
            jsonMap.put("vc", balance.get("vc"));
            jsonMap.put("tid", balance.get("tid"));
            jsonMap.put("sid", balance.get("sid"));
            jsonMap.put("sname", balance.get("sname"));
            jsonMap.put("stype", balance.get("stype"));
            jsonMap.put("sarea", balance.get("sarea"));
            jsonMap.put("sregion", balance.get("sregion"));
            jsonMap.put("scity", balance.get("scity"));
            jsonMap.put("sprovince", balance.get("sprovince"));
            String JsonString = gson.toJson(jsonMap);
            //map.put("status", "true");
            System.out.println(JsonString);
            StringEntity entity = new StringEntity(JsonString);
            post.setEntity(entity);
            HttpResponse response = client.execute(post);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            (response.getEntity().getContent())
                    )
            );

            StringBuilder content = new StringBuilder();
            String line;
            while (null != (line = br.readLine())) {
                content.append(line);
            }
            String result = content.toString();
            System.out.println(result);

            map = gson.fromJson(result, new TypeToken<Map<String, Object>>() {
            }.getType());

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(map);
        return map;
    }
}
