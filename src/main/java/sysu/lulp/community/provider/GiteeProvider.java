package sysu.lulp.community.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.stereotype.Component;
import sysu.lulp.community.dto.GiteeAccessTokenDTO;
import sysu.lulp.community.dto.GiteeUser;

import java.io.IOException;

@Component
public class GiteeProvider {

    public String getAccessToken(GiteeAccessTokenDTO giteeAccessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(giteeAccessTokenDTO));
//        System.out.println(JSON.toJSONString(giteeAccessTokenDTO));
        Request request = new Request.Builder()
                .url("https://gitee.com/oauth/token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            JSONObject jsonObject = JSON.parseObject(string);
//            System.out.println(jsonObject.get("access_token"));
//            System.out.println(string);
            return (String)jsonObject.get("access_token");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GiteeUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://gitee.com/api/v5/user?access_token=" + accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
//            System.out.println(string);
            GiteeUser giteeUser = JSON.parseObject(string, GiteeUser.class);
            return giteeUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
