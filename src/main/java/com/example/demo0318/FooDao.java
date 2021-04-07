package com.example.demo0318;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Repository
@Slf4j
public class FooDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;


    public void insertData() {
        Arrays.asList("bbbb", "cccccc").forEach(bar -> {
            jdbcTemplate.update("INSERT INTO FOO (BAR) VALUES (?)", bar);
        });


        HashMap<String, String> row = new HashMap<>();

        log.info("11244" + simpleJdbcInsert.toString());

        row.put("BAR", "ddddd");

        Number id = simpleJdbcInsert.execute(row);

        log.info("ID of d : {} ", id.longValue());

    }


    public void listData() {
        log.info("Count:{}", jdbcTemplate.queryForObject("select count(*) from FOO", Long.class));

        List<String> list = jdbcTemplate.queryForList("select BAR from FOO", String.class);

        list.forEach(s -> log.info("BAR :{}", s));

        List<FOO> fooList = jdbcTemplate.query("SELECT * FROM FOO", new RowMapper<FOO>() {
            @Override
            public FOO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return FOO.builder()
                        .id(rs.getLong(1))
                        .bar(rs.getString(2))
                        .build();
            }
        } );

        fooList.forEach(f -> log.info("Foo: {}", f));

    }

}
