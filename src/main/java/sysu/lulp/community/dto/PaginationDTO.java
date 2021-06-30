package sysu.lulp.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page, Integer size) {
        this.totalPage = totalPage;
        this.page = page;
        // 是否展示上一页按钮
        showPrevious = page != 1;
        // 是否展示下一页按钮
        showNext = !page.equals(totalPage);

        for (int i = -3; i <= 3; i++) {
            int curPage = page + i;
            if (curPage >= 1 && curPage <= totalPage) {
                pages.add(curPage);
            }
        }
        // 是否展示第一页按钮
        showFirstPage = !pages.contains(1);

        // 是否展示最后一页按钮
        showEndPage = !pages.contains(totalPage);
    }
}
