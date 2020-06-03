package com.jhome.jhomeweb;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;


@SpringBootTest
class JhomeWebApplicationTests {

    @Autowired
    public DataSource ds;
    @Test
    void contextLoads() throws SQLException {
      System.out.println(ds.getConnection().toString());
    }

}
