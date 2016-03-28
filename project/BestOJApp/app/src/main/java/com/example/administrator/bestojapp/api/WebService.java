package com.example.administrator.bestojapp.api;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.RequiresCookie;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.annotations.rest.SetsCookie;
import org.androidannotations.api.rest.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * Created by Administrator on 2016/3/26 0026.
 */
@Rest(rootUrl = "http://192.168.209.1:8080/BestOJAppServer", converters = { GsonHttpMessageConverter.class } )
public interface WebService {

    @Get("/CheckNetwork")
    public String checkNetwork();

    @Post("/RegisterServlet?username={username}&passwd={passwd}")
    public String register(String username, String passwd);

    @Post("/LoginServlet?username={username}&passwd={passwd}")
    @SetsCookie("JSESSIONID")
    public String login(String username, String passwd);

    @Get("/TestLoginServlet")
    @RequiresCookie("JSESSIONID")
    public String testLogin();

    @Get("/LogoutServlet")
    @RequiresCookie("JSESSIONID")
    @SetsCookie("JSESSIONID")
    public String logout();
}
