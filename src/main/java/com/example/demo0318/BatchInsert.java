package com.example.demo0318;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.Name;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


@Repository
public class BatchInsert {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public void batchInsert(){

        jdbcTemplate.batchUpdate("INSERT INTO FOO (BAR) VALUES (?)",

                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1,"b-"+i);
                    }

                    @Override
                    public int getBatchSize() {
                        return 5;
                    }
                });

        ArrayList<FOO> list = new ArrayList<>();

        list.add(FOO.builder().id(100L).bar("b-100").build());
        list.add(FOO.builder().id(101L).bar("b-101").build());

        namedParameterJdbcTemplate.batchUpdate("INSERT INTO FOO (ID, BAR) VALUES (:id, :bar)", SqlParameterSourceUtils.createBatch(list));



    }

}
