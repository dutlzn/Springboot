package com.dutlzn.authentication.session;

import com.dutlzn.base.result.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomInvalidSessionStrategy implements InvalidSessionStrategy {
    private SessionRegistry sessionRegistry;
    public CustomInvalidSessionStrategy(SessionRegistry sessionRegistry){
        this.sessionRegistry = sessionRegistry;
    }
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {

        sessionRegistry.removeSessionInformation(
                request.getRequestedSessionId()
        );
        // 将浏览器的sessionid清除，不关闭浏览器cookie不会被删除，一直请求都提示：Session失效
        cancelCookie(request,response);

        MyResult result = new MyResult().build(
                HttpStatus.UNAUTHORIZED.value(), "登录已经超时，请重新登录"
        );


        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(result.toJsonString());
    }


// 参考记住我功能的 AbstractRememberMeServices 代码
protected void cancelCookie(HttpServletRequest request, HttpServletResponse response) {
Cookie cookie = new Cookie("JSESSIONID", null);
cookie.setMaxAge(0);
cookie.setPath(getCookiePath(request));
response.addCookie(cookie);
}
private String getCookiePath(HttpServletRequest request) {
String contextPath = request.getContextPath();
return contextPath.length() > 0 ? contextPath : "/";
}
}
