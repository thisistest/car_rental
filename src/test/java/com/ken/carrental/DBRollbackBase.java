package com.ken.carrental;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Sql(scripts = {"classpath:/db/schema.sql", "classpath:db/data.sql"})
@SpringBootTest
public class DBRollbackBase {

}
