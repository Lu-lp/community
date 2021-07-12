package sysu.lulp.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sysu.lulp.community.dto.PaginationDTO;
import sysu.lulp.community.mapper.UserMapper;
import sysu.lulp.community.model.User;
import sysu.lulp.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "2") Integer size,
                        @RequestParam(name = "search", required = false) String search,
                        Model model) {
        PaginationDTO paginationDTO = questionService.list(search, page, size);
        model.addAttribute("pagination", paginationDTO);
        model.addAttribute("search", search);
//        System.out.println("asdddddddd");
        return "index";
    }
}
