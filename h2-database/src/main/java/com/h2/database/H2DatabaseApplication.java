package com.h2.database;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
public class H2DatabaseApplication {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void initDB(){
        String sqlStatement[] = {
                "DROP TABLE Employee IF EXISTS",
                "CREATE TABLE Employee (\n" +
                " id INT,\n"+
                " firstName varchar(500), \n"+
                " lastName varchar(500), \n"+
                " email varchar(500), \n"+
                " phoneNumber varchar \n" +
                ")",
                "INSERT INTO Employee(id, firstName, lastName, email, phoneNumber) values(1, 'John','abc', 'abc@abc.com','123-345-234')",
                "INSERT INTO Employee(id, firstName, lastName, email, phoneNumber) values(2, 'John','abc', 'abc@abc.com','123-345-234')",
                "INSERT INTO Employee(id, firstName, lastName, email, phoneNumber) values(3, 'John','abc', 'abc@abc.com','123-345-234')"
        };

        Arrays.asList(sqlStatement).stream().forEach(sql -> {
            System.out.println(sql);
            jdbcTemplate.execute(sql);
        });

        System.out.println(String.format("****** Fetching from table: %s *****", "feature data"));

        jdbcTemplate.query("select id, firstName, lastName, email, phoneNumber from Employee",
                new RowMapper<Object>() {
                    public Object mapRow(ResultSet rs, int i) throws SQLException {
                        System.out.println(String.format("id:%d, firstName:%s, lastName:%s,email:%s, phoneNumber:%s",
                                rs.getInt("id"),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("email"),
                                rs.getString("phoneNumber")));
                        return null;
                    }
                });
    }

    @Bean(destroyMethod = "stop")
    public Server inMemoryDataBaseServer() throws SQLException {
        return Server.createTcpServer("-tcp","-tcpAllowOthers", "-tcpPort", "9092").start();
    }

    public static void main(String args[]){
        SpringApplication.run(com.h2.database.H2DatabaseApplication.class, args);
    }
}
