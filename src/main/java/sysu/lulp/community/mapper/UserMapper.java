package sysu.lulp.community.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import sysu.lulp.community.model.User;

@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified, avatar_url) " +
            "values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    public void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer creator);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId")String accountId);

    @Update("update user set name = #{name}, token = #{token}," +
            "gmt_modified = #{gmtModified}," +
            "avatar_url= #{avatarUrl}")
    void update(User user);
}
