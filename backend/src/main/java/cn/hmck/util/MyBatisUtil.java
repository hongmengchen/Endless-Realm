package cn.hmck.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory = null;
    private static SqlSession sqlSession;

    // 初始化SqlSessionFactory对象
    static {
        try {
            // 使用MyBatis提供的Resources类加载MyBatis的配置文件
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            // 构建SqlSessionFactory工厂
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取SqlSession实例
    public static SqlSession getSqlSession(boolean autoCommit){
        // 创建SqlSession实例
        sqlSession = sqlSessionFactory.openSession(autoCommit);
        return sqlSession;
    }
}
