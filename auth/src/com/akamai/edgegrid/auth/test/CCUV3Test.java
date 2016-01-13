package com.akamai.edgegrid.auth.test;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.akamai.edgegrid.auth.AkamaiConstants;
import com.akamai.edgegrid.auth.AkamaiPostData;
import com.akamai.edgegrid.auth.ClientCredential;
import com.akamai.edgegrid.auth.DefaultCredential;
import com.akamai.edgegrid.auth.EdgeGridV1Signer;
import com.akamai.edgegrid.auth.RequestSigner;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.gson.Gson;

public class CCUV3Test {

	
	@SuppressWarnings({ "unused", "unchecked" })
    @Test
    public void testRead() throws Exception {

        
        URL url = new URL(AkamaiConstants.OPEN_AUTH_SERVICE_URL + AkamaiConstants.ARL_URL_PROD_PURGING);
        
        HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();
        HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
        AkamaiPostData postData = new AkamaiPostData();
        postData.setHostname(AkamaiConstants.SITE_HOST_NAME);
        List<String> purgeObjList = new ArrayList<String>(); 
        purgeObjList.add("/akamaitest.html");
        purgeObjList.add("/akamai123.html");
        postData.setObjects(purgeObjList);
        
        Gson gson = new Gson();
        String postDataJSON = gson.toJson(postData);
        //String postData = "{\"objects\":[CPCODE]}";
        String postDataEncoded = "query=" + URLEncoder.encode(postDataJSON, "utf-8");
        byte [] contentBytes = postDataJSON.getBytes();
        HttpContent content = new ByteArrayContent("application/json", contentBytes);
        HttpRequest request = requestFactory.buildPostRequest(new GenericUrl(url), content);
        HttpHeaders headers = request.getHeaders();
        headers.set("Host", AkamaiConstants.HOST_HEADER);

        ClientCredential credential = new DefaultCredential(AkamaiConstants.CLIENT_TOKEN, AkamaiConstants.ACCESS_TOKEN, AkamaiConstants.CLIENT_SECRET);
        RequestSigner signer = new EdgeGridV1Signer(Collections.EMPTY_LIST, 1024 * 2);
        HttpRequest signedRequest = signer.sign(request, credential);
        HttpResponse response = signedRequest.execute();
        String result = response.parseAsString();
        System.out.println("result::"+result);
        int breakpont = 1;
    }
}
