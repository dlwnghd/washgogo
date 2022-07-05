package com.project.washgogo.DAOTests;

import com.project.washgogo.domain.dao.ServiceAreaDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ServiceAreaDAOTests {

    @Autowired
    private ServiceAreaDAO serviceAreaDAO;

    // 테스트 완료
//    @Test
//    public void selectProvinceTest(){
//        log.info(serviceAreaDAO.selectProvince("경기"));
//    }

//    // 테스트 완료
//    @Test
//    public void selectCityTest(){
//        log.info(serviceAreaDAO.selectCity("서울"));
//    }

    // 테스트 완료
//    @Test
//    public void selectNewTownTest(){
//        log.info(serviceAreaDAO.selectNewTown("동탄"));
//    }

    // 테스트 완료
//    @Test
//    public void selectGuTest(){
//        log.info(serviceAreaDAO.selectGu("수지"));
//    }

    // 테스트 완료
//    @Test
//    public void selectDongTest(){
//        log.info(serviceAreaDAO.selectDong("정자"));
//    }

    // 테스트 완료
//    @Test
//    public void selectDongByCityTest(){
//        serviceAreaDAO.selectDongByCity("성남").stream().forEach(log::info);
//    }

    // 테스트 완료
//    @Test
//    public void selectDongByNewTownTest(){
//        serviceAreaDAO.selectDongByNewTown("미사강변").stream().forEach(log::info);
//    }

    // 테스트 완료
//    @Test
//    public void selectDongByGuTest(){
//        serviceAreaDAO.selectDongByGu("서").stream().forEach(log::info);
//    }
}
