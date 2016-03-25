package com.example.administrator.bestojapp.api;

import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * Created by Administrator on 2016/3/26 0026.
 */
@Rest(rootUrl = "http://10.134.104.3:8080/BestOJAppServer", converters = { GsonHttpMessageConverter.class } )
public interface WebService {
    @Post("/RegisterServlet?username={username}&passwd={passwd}")
    public String register(String username, String passwd);
}
