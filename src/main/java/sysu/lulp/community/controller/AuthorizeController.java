package sysu.lulp.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sysu.lulp.community.dto.AccessTokenDTO;
import sysu.lulp.community.dto.GiteeAccessTokenDTO;
import sysu.lulp.community.dto.GiteeUser;
import sysu.lulp.community.provider.GiteeProvider;
import sysu.lulp.community.provider.GithubProvider;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private GiteeProvider giteeProvider;

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

    @GetMapping("/GiteeCallback")
    public String giteeCallback(@RequestParam(name="code") String code){
        System.out.println(code);
        GiteeAccessTokenDTO giteeAccessTokenDTO = new GiteeAccessTokenDTO();
        giteeAccessTokenDTO.setClient_id("736e705e861edfb8fde9f984c997984b18d03a03758acf32783329b8398893be");
        giteeAccessTokenDTO.setClient_secret("403dca249ae9e989da55037a56449e6f889cb2278ac3d798f87149eeaf309a32");
        giteeAccessTokenDTO.setCode(code);
        giteeAccessTokenDTO.setRedirect_uri("http://localhost:8887/GiteeCallback");
        giteeAccessTokenDTO.setGrant_type("authorization_code");
        String accessToken = giteeProvider.getAccessToken(giteeAccessTokenDTO);
        GiteeUser giteeUser = giteeProvider.getUser(accessToken);
        System.out.println(giteeUser.toString());
        return "index";
    }
}
