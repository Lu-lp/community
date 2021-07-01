package sysu.lulp.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sysu.lulp.community.mapper.UserMapper;
import sysu.lulp.community.model.User;
import sysu.lulp.community.model.UserExample;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size() == 0){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            User dbUser = users.get(0);
            user.setGmtModified(System.currentTimeMillis());
            user.setId(dbUser.getId());
//            UserExample userExample1 = new UserExample();
            userMapper.updateByPrimaryKey(user);
        }
    }
}
