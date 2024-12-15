package cn.hmck.controller;

import cn.hmck.entity.Resource;
import cn.hmck.util.ErrorMsg;
import cn.hmck.util.FileNameUtil;
import cn.hmck.util.JSONFileUtil;
import cn.hmck.util.Result;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件资源实体类
 *
 * @author 陈亮
 * @since 2024-12-14
 */

@RestController
@RequestMapping("/file")
public class FileController {

    // 文件上传接口
    @CrossOrigin(origins = "http://localhost:8081")  // 允许指定前端地址跨域访问
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file == null || file.isEmpty()) {
            return Result.fail(ErrorMsg.FILE_EMPTY_ERROR);
        }
        try {
            // 获取Web应用根目录的路径
            String projectRootPath = request.getServletContext().getRealPath("/");
            System.out.println("原始项目根目录路径: " + projectRootPath);

            // 获取需要的目标路径，去除掉不需要的部分并拼接
            String targetDir = projectRootPath.replace("out\\artifacts\\backend_war_exploded", "src\\main\\webapp\\files");
            System.out.println("目标文件保存路径: " + targetDir);

            // 创建目标文件夹对象
            File targetDirectory = new File(targetDir);
            if (!targetDirectory.exists()) {
                targetDirectory.mkdirs();
            }

            // 创建 ObjectMapper 用于 JSON 操作
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Resource> list = new ArrayList<>();
            String jsonFilePath = targetDir + "/files.json";

            // 如果存在 files.json 文件，读取文件内容
            String json = JSONFileUtil.readFile(jsonFilePath);
            if (json.length() != 0) {
                // 将 JSON 内容转为资源列表
                list = mapper.readValue(json, new TypeReference<List<Resource>>() {
                });
            }

            // 获取上传文件的原始名称
            String originalFilename = file.getOriginalFilename();

            // 使用时间戳命名文件
            String newFilename = FileNameUtil.generateTimeBasedFileName();

            // 如果有扩展名，添加扩展名到新文件名
            if (originalFilename != null && originalFilename.contains(".")) {
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                newFilename += extension;
            }

            // 检查文件名是否已存在于 files.json 中
            for (Resource resource : list) {
                if (newFilename.equals(resource.getName())) {
                    // 如果文件名已存在，给文件重命名
                    newFilename = newFilename.split("\\.")[0] + "(1)." + newFilename.split("\\.")[1];
                    break;
                }
            }

            // 文件保存的全路径
            String filePath = targetDir + "/" + newFilename;

            // 保存上传的文件
            file.transferTo(new File(filePath));

            // 添加新文件记录到资源列表
            list.add(new Resource(newFilename));

            // 更新 JSON 内容
            json = mapper.writeValueAsString(list);
            // 将文件记录保存到 files.json 文件中
            JSONFileUtil.writeFile(json, jsonFilePath);

            // 返回文件路径
            String fileUrl = "/files/" + newFilename;
            System.out.println("文件上传成功，文件路径为：" + fileUrl);
            return Result.success(fileUrl);
        } catch (Exception e) {
            return Result.fail(ErrorMsg.FILE_UPLOAD_ERROR);
        }
    }
}