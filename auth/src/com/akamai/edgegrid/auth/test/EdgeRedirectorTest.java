package com.akamai.edgegrid.auth.test;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;

import org.junit.Test;

import com.akamai.edgegrid.auth.AkamaiConstants;
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

public class EdgeRedirectorTest {

    @SuppressWarnings({ "unused", "unchecked" })
    @Test
    public void testRead() throws Exception {

        // Use the values from control.akamai.com
        // (CONFIGURE -> Manage APIs -> ...)
    	URL url = new URL(AkamaiConstants.OPEN_AUTH_SERVICE_URL + "/config-edgeredirector-data/api/v1/policymanager");
        
        HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();
        HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
        String postData = "{\"policyManagerRequest\":{\"command\":\"read\",\"read\":{\"id\":\" + id + \"}}}";
        String postDataEncoded = "query=" + URLEncoder.encode(postData, "utf-8");
        byte [] contentBytes = postDataEncoded.getBytes();
        HttpContent content = new ByteArrayContent("application/x-www-form-urlencoded", contentBytes);
        HttpRequest request = requestFactory.buildPostRequest(new GenericUrl(url), content);
        HttpHeaders headers = request.getHeaders();
        headers.set("Host", AkamaiConstants.HOST_HEADER);

        ClientCredential credential = new DefaultCredential(AkamaiConstants.CLIENT_TOKEN, AkamaiConstants.ACCESS_TOKEN, AkamaiConstants.CLIENT_SECRET);
        RequestSigner signer = new EdgeGridV1Signer(Collections.EMPTY_LIST, 1024 * 2);
        HttpRequest signedRequest = signer.sign(request, credential);
        HttpResponse response = signedRequest.execute();
        String result = response.parseAsString();
        int breakpont = 1;
    }
}