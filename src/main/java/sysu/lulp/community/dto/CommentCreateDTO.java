package sysu.lulp.community.dto;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private int parentId;
    private String content;
    private Integer type;
}
