package com.example.administrator.bestojapp.web;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.RequiresCookie;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.annotations.rest.SetsCookie;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * Created by Administrator on 2016/3/26 0026.
 */
@EBean
class AppRequestFactory extends SimpleClientHttpRequestFactory {

    @AfterInject
    void afterinject() {
        setReadTimeout(2*1000); //set 8s read timeout
        setConnectTimeout(2*1000); //set 8s connect timeout
    }
}

@Rest(rootUrl = "http://192.168.209.1:8080/myproject", requestFactory = AppRequestFactory.class, converters = { GsonHttpMessageConverter.class } )
public interface WebService {

    @Get("/appUser_user_CheckNetwork_PUBLIC.html")
    String checkNetwork();

    @Post("/appUser_user_Register_PUBLIC.html?username={username}&password={password}")
    String register(String username, String password);
    
    @Post("/appUser_user_login_PUBLIC.html?username={username}&password={password}")
    @SetsCookie("USERID")
    String login(String username, String password);

    @Get("/appUser_user_testLogin_PUBLIC.html")
    @RequiresCookie("USERID")
    String testLogin();

    @Get("/LogoutServlet")
    @RequiresCookie("USERID")
    @SetsCookie("USERID")
    String logout();

    @Get("/appUser_user_GetOffspringByParentId_PUBLIC.html?token={token}&parentId={parentId}")
    String getOffspringByParentId(String token, Long parentId);

    @Get("/appUser_user_GetProblemByProblemId_PUBLIC.html?token={token}&problemId={problemId}")
    String getProblemByProblemId(String token, Long problemId);

    @Get("/appUser_user_GetListOfUserByProblemId_PUBLIC.html?token={token}&problemId={problemId}")
    String getListOfUserByProblemId(String token, Long problemId);

    @Get("/appUser_user_GetBySolutionId_PUBLIC.html?token={token}&solutionId={solutionId}")
    String getBySolutionId(String token, Long solutionId);

    @Get("/appUser_user_GetDiscussByProblemId_PUBLIC.html?problemId={problemId}")
    String getDiscussByProblemId(Long problemId);

    @Get("/appUser_user_PostDiscuss_PUBLIC.html?title={title}&content={content}&problemId={problemId}&replyId={replyId}")
    @RequiresCookie("USERID")
    String postDiscuss(String title, String content, Long problemId, Long replyId);

    @Get("/appUser_user_PostDiscuss_PUBLIC.html?title={title}&content={content}&problemId={problemId}")
    @RequiresCookie("USERID")
    String postDiscuss(String title, String content, Long problemId);

    @Get("/appUser_user_removeDiscuss_PUBLIC.html?discussId={discussId}")
    String removeDiscuss(Long discussId);

    @Get("/appUser_user_exam_examPaper_getAllOfUser_PUBLIC.html?token={token}")
    String getExamListFromServer(String token);

    @Get("/appUser_user_exam_examPaper_getDetailOfExamPaper_PUBLIC.html?token={token}&examPaperId={examPaperId}")
    String getExamPaper(String token, Long examPaperId);

    @Get("/appUser_user_exam_examSolution_getListOfExamPaper_PUBLIC.html?token={token}&examPaperId={examPaperId}")
    String getExamSolution(String token, Long examPaperId);
}