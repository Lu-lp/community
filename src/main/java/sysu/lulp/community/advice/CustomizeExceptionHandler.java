package sysu.lulp.community.advice;

import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import sysu.lulp.community.dto.ResultDTO;
import sysu.lulp.community.exception.CustomizeErrorCode;
import sysu.lulp.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e,
                        Model model,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        String contentType = request.getContentType();
        System.out.println(e.getMessage());
        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO;
            // 返回 JSON
            if (e instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            } else {
//                log.error("handle error", e);
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {
            }
            return null;
        } else {
            // 错误页面跳转
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
//                log.error("handle error", e);
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
//        if(e instanceof CustomizeException){
//            model.addAttribute("message", e.getMessage());
//        }else{
//            System.out.println(e.getMessage());
//            model.addAttribute("message", "服务器错误，请稍后尝试！！");
//        }
//        return new ModelAndView("error");
}


