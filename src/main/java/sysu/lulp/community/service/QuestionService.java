package sysu.lulp.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sysu.lulp.community.dto.QuestionDTO;
import sysu.lulp.community.mapper.QuestionMapper;
import sysu.lulp.community.mapper.UserMapper;
import sysu.lulp.community.model.Question;
import sysu.lulp.community.model.User;

import java.util.LinkedList;
import java.util.List;


@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOS = new LinkedList<>();
        if(questions != null){
            for (Question question : questions) {
                User user = userMapper.findById(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOS.add(questionDTO);
            }
        }
        return questionDTOS;
    }
}
