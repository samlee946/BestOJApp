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
    String checkNetwork();

    @Post("/RegisterServlet?username={username}&password={password}")
    String register(String username, String password);

    @Post("/LoginServlet?username={username}&password={password}")
    @SetsCookie("JSESSIONID")
    String login(String username, String password);

    @Get("/TestLoginServlet")
    @RequiresCookie("JSESSIONID")
    String testLogin();

    @Get("/LogoutServlet")
    @RequiresCookie("JSESSIONID")
    @SetsCookie("JSESSIONID")
    String logout();

    @Get("/GetOffspringByParentId?token={token}&parentId={parentId}")
    String getOffspringByParentId(String token, Long parentId);
}
