package com.solvd.library.persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class PersistenceConfig {

    private static final SqlSessionFactory sqlSessionFactory;

    static {
        try(InputStream is = Resources.getResourceAsStream("mybatis-configuration.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
