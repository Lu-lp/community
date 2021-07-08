package sysu.lulp.community.mapper;

import org.springframework.stereotype.Component;
import sysu.lulp.community.model.Question;

@Component
public interface QuestionExtMapper {

    int incView(Question record);

    int incCommentCount(Question question);
}
