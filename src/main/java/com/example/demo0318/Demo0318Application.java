package com.example.demo0318;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;


import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
public class Demo0318Application implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private FooDao fooDao;


    public static void main(String[] args) {
        SpringApplication.run(Demo0318Application.class, args);
    }

    @Autowired
    @Bean
    public SimpleJdbcInsert simpleJdbcInsert(JdbcTemplate jdbcTemplate){
        return new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("FOO").usingGeneratedKeyColumns("ID");
    }

    @Autowired
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public void run(String... args) throws  Exception{
        log.info(dataSource.getConnection().toString());

       // fooDao.insertData();

        //fooDao.listData();

        //batchInsert.batchInsert();
    }

}
