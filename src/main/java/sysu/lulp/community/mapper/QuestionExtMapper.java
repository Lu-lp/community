package sysu.lulp.community.mapper;

import org.springframework.stereotype.Component;
import sysu.lulp.community.dto.QuestionQueryDTO;
import sysu.lulp.community.model.Question;

import java.util.List;

@Component
public interface QuestionExtMapper {

    int incView(Question record);

    int incCommentCount(Question question);

    List<Question> selectRelated(Question qUestion);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}
