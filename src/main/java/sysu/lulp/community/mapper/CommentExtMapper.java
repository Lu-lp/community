package sysu.lulp.community.mapper;

import org.springframework.stereotype.Component;
import sysu.lulp.community.model.Comment;
import sysu.lulp.community.model.Question;

@Component
public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}
