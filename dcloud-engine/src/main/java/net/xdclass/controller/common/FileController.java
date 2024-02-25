package net.xdclass.controller.common;
import jakarta.annotation.Resource;
import net.xdclass.service.common.FileService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 文件上传接口
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public JsonData upload(@RequestParam("file") MultipartFile file){

        String path = fileService.upload(file);

        return JsonData.buildSuccess(path);
    }

}
