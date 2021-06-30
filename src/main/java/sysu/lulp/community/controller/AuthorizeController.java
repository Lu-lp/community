package sysu.lulp.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sysu.lulp.community.dto.AccessTokenDTO;
import sysu.lulp.community.dto.GiteeAccessTokenDTO;
import sysu.lulp.community.dto.GiteeUser;
import sysu.lulp.community.mapper.UserMapper;
import sysu.lulp.community.model.User;
import sysu.lulp.community.provider.GiteeProvider;
import sysu.lulp.community.provider.GithubProvider;
import sysu.lulp.community.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private GiteeProvider giteeProvider;

    @Value("${gitee.client.id}")
    private String clientId;

    @Value("${gitee.client.secret}")
    private String clientSecret;

    @Value("${gitee.rediret.uri}")
    private String redirectUri;

    @Value("${gitee.grant.type}")
    private String grantType;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/GiteeCallback")
    public String giteeCallback(@RequestParam(name="code") String code,
                                HttpServletRequest request,
                                HttpServletResponse response){
//        System.out.println(code);
        GiteeAccessTokenDTO giteeAccessTokenDTO = new GiteeAccessTokenDTO();
        giteeAccessTokenDTO.setClient_id(clientId);
        giteeAccessTokenDTO.setClient_secret(clientSecret);
        giteeAccessTokenDTO.setCode(code);
        giteeAccessTokenDTO.setRedirect_uri(redirectUri);
        giteeAccessTokenDTO.setGrant_type(grantType);
        String accessToken = giteeProvider.getAccessToken(giteeAccessTokenDTO);
        GiteeUser giteeUser = giteeProvider.getUser(accessToken);
        if(giteeUser != null){
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(giteeUser.getName());
            user.setAccountId(String.valueOf(giteeUser.getId()));
            user.setAvatarUrl(giteeUser.getAvatarUrl());
//            userMapper.insert(user);
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token", user.getToken()));
//            request.getSession().setAttribute("user", giteeUser);
            return "redirect:/";
        }else{
            return "redirect:/";
        }

    }

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        System.out.println(code +" " + state);
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("e2e2be83bda144cac8b6");
        accessTokenDTO.setClient_secret("8d612cf900ccace1e7ca6d6cfa7848f960b2cfd5");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        githubProvider.getAccessToken(accessTokenDTO);
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
