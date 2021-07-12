package sysu.lulp.community.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sysu.lulp.community.dto.PaginationDTO;
import sysu.lulp.community.dto.QuestionDTO;
import sysu.lulp.community.dto.QuestionQueryDTO;
import sysu.lulp.community.exception.CustomizeErrorCode;
import sysu.lulp.community.exception.CustomizeException;
import sysu.lulp.community.mapper.QuestionExtMapper;
import sysu.lulp.community.mapper.QuestionMapper;
import sysu.lulp.community.mapper.UserMapper;
import sysu.lulp.community.model.Question;
import sysu.lulp.community.model.QuestionExample;
import sysu.lulp.community.model.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    // 用于Index页面返回问题列表
    public PaginationDTO list(String search, Integer page, Integer size) {
        if(StringUtils.isNotBlank(search)){
            search = StringUtils.replace(search, " ", "|");
        }

        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(search);
        Integer totalCount = (int) questionExtMapper.countBySearch(questionQueryDTO);
        Integer totalPage = 0;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page <= 0) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        Integer offset = size * (page - 1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");
        questionQueryDTO.setPage(offset);
        questionQueryDTO.setSize(size);
        List<Question> questions = questionExtMapper.selectBySearch(questionQueryDTO);
        List<QuestionDTO> questionDTOS = new LinkedList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        if (questions != null) {
            for (Question question : questions) {
                User user = userMapper.selectByPrimaryKey(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOS.add(questionDTO);
            }
        }
        paginationDTO.setData(questionDTOS);
        paginationDTO.setPagination(totalPage, page, size);
        return paginationDTO;
    }

    // 用于个人问题页面返回问题列表
    public PaginationDTO list(Integer id, Integer page, Integer size) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        Integer totalCount = (int) questionMapper.countByExample(questionExample);
//        Integer totalCount = questionMapper.countByUserId(id);
        Integer totalPage = 0;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page <= 0) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(questionExample,
                new RowBounds(offset, size));
//        List<Question> questions = questionMapper.listByUserId(id, offset, size);
        List<QuestionDTO> questionDTOS = new LinkedList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        if (questions != null) {
            for (Question question : questions) {
                User user = userMapper.selectByPrimaryKey(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOS.add(questionDTO);
            }
        }
        paginationDTO.setData(questionDTOS);
        paginationDTO.setPagination(totalPage, page, size);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        }else{
            question.setGmtModified(System.currentTimeMillis());
//            QuestionExample questionExample = new QuestionExample();
//            questionExample.createCriteria().andIdEqualTo(question.getId());
//            questionMapper.updateByExample(question, questionExample);
            int i = questionMapper.updateByPrimaryKeySelective(question);
            if(i != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Integer id) {
        Question updateQuestion = new Question();
        updateQuestion.setId(id);
        updateQuestion.setViewCount(1);
        questionExtMapper.incView(updateQuestion);
//        questionMapper.updateByExampleSelective(updateQuestion, questionExample);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO questionDTO) {
        if(StringUtils.isBlank(questionDTO.getTag())){
            return new ArrayList<>();
        }
        String s = StringUtils.replace(questionDTO.getTag(), "，", "|");
        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setTag(s);
        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> collect = questions.stream().map(question1 -> {
            QuestionDTO questionDTO1 = new QuestionDTO();
            BeanUtils.copyProperties(question1, questionDTO1);
            return questionDTO1;
        }).collect(Collectors.toList());
        return collect;
    }
}
