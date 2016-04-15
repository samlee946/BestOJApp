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
    @SetsCookie("USERID")
    String login(String username, String password);

    @Get("/TestLoginServlet")
    @RequiresCookie("USERID")
    String testLogin();

    @Get("/LogoutServlet")
    @RequiresCookie("USERID")
    @SetsCookie("USERID")
    String logout();

    @Get("/GetOffspringByParentId?token={token}&parentId={parentId}")
    String getOffspringByParentId(String token, Long parentId);

    @Get("/GetProblemByProblemId?token={token}&problemId={problemId}")
    String getProblemByProblemId(String token, Long problemId);

    @Get("/GetListOfUserByProblemId?token={token}&problemId={problemId}")
    String getListOfUserByProblemId(String token, Long problemId);

    @Get("/GetBySolutionId?token={token}&solutionId={solutionId}")
    String getBySolutionId(String token, Long solutionId);

    @Get("/GetDiscussByProblemId?problemId={problemId}")
    String getDiscussByProblemId(Long problemId);

    @Get("/PostDiscuss?title={title}&content={content}&problemId={problemId}")
    @RequiresCookie("USERID")
    String postDiscuss(String title, String content, Long problemId);
}
