package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OKHTTPUse {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws IOException {

        //do GET
        String getString = doGET("https://openlibrary.org/api/books?bibkeys=ISBN%3A0201558025%2CLCCN%3A93005405&format=json");


        //parser
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        HashMap<String, InfoObject> map = objectMapper.readValue(getString, new TypeReference<HashMap<String, InfoObject>>() {
        });

        //InfoObject io   = objectMapper.readValue("abcd",InfoObject.class);

        for (Map.Entry<String, InfoObject> entry : map.entrySet()) {
            System.out.println("key " + entry.getKey());
            System.out.println("url " + entry.getValue().getInfo_url());
        }


        //do POST
        String postString = doPOST("http://www.roundsapp.com/post", constructJson("Akshansh", "Ashok"));
        System.out.println(postString);

    }

    static String doGET(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    static String doPOST(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                //.addHeader("Accept", "application/json; q=0.5")
                .build();
        try (Response response = client.newCall(request).execute()) {
            //print  response Headers
            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }
            //return body
            return response.body().string();
        }

    }

    static String constructJson(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }

}
