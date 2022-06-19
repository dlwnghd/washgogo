package com.project.washgogo.myBatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
@Slf4j
public class MyBatisConfigTests {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

//    @Test
//    public void dataSourceTest(){
////        try statement 문법
////        try(외부 드라이버 요청 문법 작성){}
////        try문에 있는 소괄호에 연결객체를 요청하면, 사용종료 후 자동으로 close()된다.
//        try
//                (
//                        Connection conn = dataSource.getConnection();
//                )
//        {
//            log.info("---------------------------------");
//            log.info("datasource connection : " + conn);
//            log.info("---------------------------------");
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    @Test
    public void sqlSessionTest(){
        log.info("-------------------------------");
        log.info("sql session factory : " + sqlSessionFactory);
        log.info("-------------------------------");

        try
                (
                        SqlSession sqlSession = sqlSessionFactory.openSession(true);
                        Connection conn = sqlSession.getConnection();
                )
        {
            log.info("sql session : " + sqlSession);
            log.info("-------------------------------");
            log.info("sql session connection " + conn);
            log.info("-------------------------------");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}




















