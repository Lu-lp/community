package sysu.lulp.community.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import sysu.lulp.community.model.Question;

import java.util.List;

@Component
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title, description, gmt_create, gmt_modified, creator, tag) " +
            "values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param("offset") Integer offset,
                        @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{id} limit #{offset}, #{size}")
    List<Question> listByUserId(@Param("id")Integer id,
                                @Param("offset") Integer offset,
                                @Param("size") Integer size);

    @Select("select count(1) from question where creator = #{id}")
    Integer countByUserId(@Param("id") Integer id);

    @Select("select * from question where id = #{id}")
    Question getById(@Param("id") Integer id);

    @Update("update question set title = #{title}," +
            "description = #{description}," +
            "gmt_modified = #{gmtModified}," +
            "tag = #{tag}" +
            "where id = #{id}")
    void update(Question question);
}
