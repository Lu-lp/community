package sysu.lulp.community.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import sysu.lulp.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e,
                        Model model,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        if(e instanceof CustomizeException){
            model.addAttribute("message", e.getMessage());
        }else{
            model.addAttribute("message", "服务器错误，请稍后尝试！！");
        }
        return new ModelAndView("error");
    }
}

