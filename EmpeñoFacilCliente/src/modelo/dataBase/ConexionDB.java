/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dataBase;

/**
 *
 * @author Juuan
 */

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ConexionDB {
    
    public static String RESOURCE = "modelo/dataBase/mybatis-config.xml";    
    public static String ENVIRONMENT = "development";
    
    public static SqlSession getSession() throws IOException{
        SqlSession session = null;
        Reader reader = Resources.getResourceAsReader(RESOURCE);
        SqlSessionFactory sqlMapper =
                new SqlSessionFactoryBuilder().build(reader,ENVIRONMENT);
        session = sqlMapper.openSession();
        return session;
    }
}

