package com.project.washgogo.controller;

import com.project.washgogo.domain.vo.ProfileVO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/upload/*")
public class UploadController {

    @ResponseBody
    @PostMapping("/uploadAjax")
    public ProfileVO uploadAjax(MultipartFile file) throws IOException{
        String rootDirectory = "C:/upload";

        File uploadDirectory = new File(rootDirectory, getDateDirectory());
        if(!uploadDirectory.exists()) {uploadDirectory.mkdirs();}

        log.info("------------------------------------");
        log.info("upload file name : " + file.getOriginalFilename());
        log.info("upload file size : " + file.getSize());

        ProfileVO profileVO = new ProfileVO();

        /* 파일 이름 중복 제거*/
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString() + "_" + file.getOriginalFilename();

        profileVO.setOriginalFileName(file.getOriginalFilename());
        profileVO.setFileName(fileName);
        profileVO.setUploadDirectory(getDateDirectory());

        File saveFile = new File(uploadDirectory, fileName);
        file.transferTo(saveFile);

        /* 썸네일 생성 */
        if(checkImageType(saveFile)){
            FileOutputStream thumbnail = new FileOutputStream(new File(uploadDirectory, "t_" + fileName));
            Thumbnailator.createThumbnail(file.getInputStream(), thumbnail, 250, 250);
            thumbnail.close();
        }

        return profileVO;
    }

    /* 썸네일 화면에 전송 */
    @GetMapping("/display")
    @ResponseBody
    public byte[] getFile(String path) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload/" + path));
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public void deleteFile(String path){
        log.info("===================="+ path + "=====================" );

    // 썸네일 삭제
        File file = new File("C:/upload", path);
        if(file.exists()) {file.delete();}

    // 원본파일 삭제
        file = new File(file.getPath().replace("t_", ""));
        if(file.exists()) {file.delete();}
    }

    private String getDateDirectory(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String directory = sdf.format(date);
        return directory;
    }

    private boolean checkImageType(File file) throws IOException{
        return Files.probeContentType(file.toPath()).startsWith("image");
    }
}
