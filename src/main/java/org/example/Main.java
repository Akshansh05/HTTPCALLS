//package org.example;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//
//        // set connection
//        URL url = new URL("https://openlibrary.org/api/books?bibkeys=ISBN%3A0201558025%2CLCCN%3A93005405&format=json");
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        //con.setDoOutput(true);//1st line if we need to have query param or body
//        //set headers
//
//        //set content Type
//        con.setRequestProperty("Content-Type", "application/json");
//
//        //for getting Header Field
//        // String contentType = con.getHeaderField("Content-Type");
//
//        //set method
//        con.setRequestMethod("GET");
//
//
//        //set TImeouts
//        con.setConnectTimeout(15000);
//        con.setReadTimeout(15000);
//
//
//        //set the cookier in request
//        //  con.setRequestProperty("Cookie", "");
//
//        // set query params
//        Map<String, String> parameters = new HashMap<>();
//       // parameters.put("INPUT", "Hello World");
//
//        StringBuilder resultParams = new StringBuilder();
//
//        for (Map.Entry<String, String> entry : parameters.entrySet()) {
//            resultParams.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
//            resultParams.append("=");
//            resultParams.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
//            resultParams.append("&");
//        }
//
//        String resultString = resultParams.length() > 0 ? resultParams.substring(0, resultParams.length() - 1) : resultParams.toString();
//        //to add query param
////        DataOutputStream dataOutputStream = new DataOutputStream(con.getOutputStream());
////        dataOutputStream.writeBytes(resultString);
////        dataOutputStream.flush();
////        dataOutputStream.close();
//
//
//        //Reading the resonse
//
//        int status = con.getResponseCode();
//        System.out.println("Status " + status);
//
//        InputStreamReader streamReader = null;
//
//        if (status > 299) {
//            streamReader = new InputStreamReader(con.getErrorStream());
//        } else {
//            streamReader = new InputStreamReader(con.getInputStream());
//        }
//
//        BufferedReader bufferedReader = new BufferedReader(streamReader);
//        String inputLine;
//        StringBuilder content = new StringBuilder();
//
//        while ((inputLine = bufferedReader.readLine()) != null) {
//            content.append(inputLine);
//        }
//        bufferedReader.close();
//
//        System.out.println(content);
//
//        System.out.println();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        InfoObject info = objectMapper.readValue(content.toString(), new TypeReference<InfoObject>() {
//        });
//
////        InfoQueryParam infoQueryParam = objectMapper.readValue(content.toString(),InfoQueryParam.class);
////        System.out.println("numFound " + infoQueryParam.getNumFound());
////        System.out.println("numFoundExact " + infoQueryParam.getNumFoundExact());
//
//
//        //GEt cookie from resonse and set it
////        String cookiesHeader = con.getHeaderField("Set-Cookie");
////        List<HttpCookie> cookies = HttpCookie.parse(cookiesHeader);
//
//    }
//}