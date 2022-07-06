package com.project.washgogo.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProfileVO {
    private String fileName;
    private String originalFileName;
    private String uploadDirectory;

    private UserVO userVO;

}
