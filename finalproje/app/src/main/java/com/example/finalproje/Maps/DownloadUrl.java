package com.example.finalproje.Maps;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadUrl {

    public String retireveUrl(String myUrl) throws IOException {
        /*String urlData="";
        HttpURLConnection httpURLConnection=null;
        InputStream inputStream=null;

        try {
            URL  getUrl=new URL(url);
            httpURLConnection=(HttpURLConnection) getUrl.openConnection();
            httpURLConnection.connect();

            inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb = new StringBuffer();

            String line="";
            while ((line= bufferedReader.readLine()) != null){
                sb.append(line);
            }
            urlData=sb.toString();
            bufferedReader.close();
        }catch (Exception e){
            Log.e("Exception",e.toString());
        }finally {
            inputStream.close();
            httpURLConnection.disconnect();
        }
        return urlData;*/

        String data = "";
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(myUrl);
            urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb = new StringBuffer();

            String line = "";
            while((line = br.readLine()) != null)
            {
                sb.append(line);
            }

            data = sb.toString();
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            inputStream.close();
            urlConnection.disconnect();
        }
        Log.d("DownloadURL","Returning data= "+data);

        return data;
    }
}
