package sysu.lulp.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sysu.lulp.community.dto.FileDTO;
import sysu.lulp.community.provider.ALiCloudProvider;
import sysu.lulp.community.service.FileService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private ALiCloudProvider aLiCloudProvider;
    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        try {
            assert file != null;
            String url = aLiCloudProvider.upload(file.getInputStream(),
                    file.getContentType(),
                    file.getOriginalFilename());
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setMessage("upload success！！！");
            fileDTO.setUrl(url);
            return fileDTO;
        } catch (IOException e) {
            e.printStackTrace();
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(0);
            fileDTO.setMessage("upload failure！！！");
            fileDTO.setUrl("/images/logo.jpg");
            return fileDTO;
        }
    }
}
