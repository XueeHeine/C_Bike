package com.qdu.error;

import com.qdu.utils.ResultMsg;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Alpha-LGC on 2019/2/13.
 */
@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler
    public ResultMsg whenException(Exception ex, HttpServletResponse resp){
        //resp.setContentType("text/html;charset=utf-8");
        /*try {

            resp.sendRedirect("/C_Bike/4033");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
       /* try(PrintWriter out = resp.getWriter()){
            //out.println("<html><body><em>错误信息："+ex.getMessage()+"</em></body></html>");

        }catch(Exception e){
            e.printStackTrace();
        }*/
       return new ResultMsg(403,"对不起你没有权限！");
    }
}
