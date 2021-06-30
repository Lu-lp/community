package sysu.lulp.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sysu.lulp.community.dto.QuestionDTO;
import sysu.lulp.community.model.User;
import sysu.lulp.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;


    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model,
                           HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        if(user == null){
//            return "redirect:/";
//        }
//        System.out.println("id: " + id );
        QuestionDTO questionDTO = questionService.getById(id);
//        questionDTO.setUser(user);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
