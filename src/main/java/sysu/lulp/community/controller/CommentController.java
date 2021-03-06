package sysu.lulp.community.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sysu.lulp.community.dto.CommentCreateDTO;
import sysu.lulp.community.dto.CommentDTO;
import sysu.lulp.community.dto.ResultDTO;
import sysu.lulp.community.enumType.CommentTypeEnum;
import sysu.lulp.community.exception.CustomizeErrorCode;
import sysu.lulp.community.model.Comment;
import sysu.lulp.community.model.User;
import sysu.lulp.community.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_NOT_FOUND);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0);
        comment.setCommentCount(0);
        commentService.insert(comment, user);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Integer id){
        List<CommentDTO> commentDTOS =
                commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
