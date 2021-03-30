package org.weiyada.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/30 3:10 下午
 */
public class CookieUtil {
    /**
     *
     *@description:
     * @param req
     * @param res
     * @param cookieName
     * @param msg
     *@return: void
     *@author: weiguangwei
     *@time: 2021/3/30 3:21 下午
     */
    public static void  addCookie(HttpServletRequest req , HttpServletResponse res,String cookieName, String msg){
        Cookie cookie = new Cookie(cookieName,msg);
        cookie.setMaxAge(30*24*60*60);
        cookie.setPath(req.getContextPath());
        res.addCookie(cookie);
    }
    /**
     *
     *@description:
     * @param req
     * @param res
     * @param cookieName
     *@return: void
     *@author: weiguangwei
     *@time: 2021/3/30 3:19 下午
     */
    public static void delCookie(HttpServletRequest req,HttpServletResponse res,String cookieName){
        Cookie cookie = new Cookie(cookieName,"");
        cookie.setPath(req.getContextPath());
        res.addCookie(cookie);
    }
}
