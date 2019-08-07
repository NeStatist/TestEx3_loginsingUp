package com.example.testex3_loginsingup;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class RequestHandler {

    public static String sendPost(String r_url, JSONObject jsonObject) throws Exception  {
        return new RequestPost(r_url, jsonObject).execute().get();
    }

    private static class RequestPost extends AsyncTask<Void, Void, String>  {

        private URL url;
        private JSONObject jsonObject;

        public RequestPost(String r_url, JSONObject jsonObject) {
            try {
                url = new URL(r_url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            this.jsonObject = jsonObject;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                return post();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        public String post() throws Exception{
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(20000);
            conn.setConnectTimeout(20000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(encodeParams(jsonObject));
            writer.flush();
            writer.close();
            os.close();

            int respondCode = conn.getResponseCode();
            if(respondCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                while((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            }
            return null;
        }
    }

    private static String encodeParams(JSONObject params) throws Exception {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while(itr.hasNext()) {
            String key = itr.next();
            Object value = params.get(key);
            if(first) {
                first = false;
            } else {
                result.append("&");
            }

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }
}