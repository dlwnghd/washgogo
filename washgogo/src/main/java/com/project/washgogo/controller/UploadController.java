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

// 1. 동일한 이름으로 파일이 업로드 되면, 기존 파일 삭제
// 2. 이미지 파일의 경우 원본 파일의 용량이 클 수 있기 때문에 썸네일 이미지가 필요하다.
// 3. 첨부파일 공격에 대비하기 위한 업로드 파일의 확장자 제한

@Controller
@Slf4j
@RequestMapping("/upload/*")
public class UploadController {
    @GetMapping("/uploadAjax")
    public void uploadAjax(){
        log.info("upload ajax");
    }

    @ResponseBody //REST
    @PostMapping("/uploadAjax")
    public ProfileVO uploadAjax(MultipartFile file) throws IOException{
        String rootDirectory = "C:/upload";

        File uploadDirectory = new File(rootDirectory, getDateDirectory());
        if(!uploadDirectory.exists()) {uploadDirectory.mkdirs();}

        log.info("------------------------------------");
        log.info("upload file name : " + file.getOriginalFilename());
        log.info("upload file size : " + file.getSize());

        ProfileVO profileVO = new ProfileVO();

        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString() + "_" + file.getOriginalFilename();

        profileVO.setOriginalFileName(file.getOriginalFilename());
        profileVO.setFileName(fileName);
        profileVO.setUploadDirectory(getDateDirectory());

        File saveFile = new File(uploadDirectory, fileName);
        file.transferTo(saveFile);

        if(checkImageType(saveFile)){
            FileOutputStream thumbnail = new FileOutputStream(new File(uploadDirectory, "t_" + fileName));
//                MultipartFile객체를 통해 바로 파일을 가져올 경우,
//                임시로 저장될 영역을 임계 영역이라 한다.
//                apllication.properties에서 임계 영역에 대한 용량을 설정해 주어야
//                그 영역에 먼저 업로드 후 inputStream()을 가져올 수 있다.
            Thumbnailator.createThumbnail(file.getInputStream(), thumbnail, 100, 100);
            thumbnail.close();
            profileVO.setImage(true);
        }
        //profile.add(profileVO);

        return profile;
    }

    @GetMapping("display")
    @ResponseBody
    public byte[] getFile(String path) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload/" + path));
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<Resource> download(String path) throws UnsupportedEncodingException {
        Resource resource = new FileSystemResource("C:/upload/" + path);
        String name = resource.getFilename();
        name = name.substring(name.indexOf("_") + 1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + new String(name.getBytes("UTF-8"), "ISO-8859-1"));
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public void deleteFile(String path){
//        썸네일 삭제
        File file = new File("C:/upload", path);
        if(file.exists()) {file.delete();}

//        원본파일 삭제
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
