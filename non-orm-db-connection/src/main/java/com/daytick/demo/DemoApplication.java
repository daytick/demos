package com.daytick.demo;

import com.daytick.demo.model.Post;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author luyi
 * @date 2021/2/1 20:37
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/demo?serverTimezone=GMT%2b8&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        String username = "root";
        String password = "root";

        // 一：加载 MySQL 驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 二：获取数据库连接
        Connection connection = DriverManager.getConnection(url, username, password);

        // 三：创建 sql 语句并执行
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement
            .executeQuery("SELECT * FROM `post` WHERE title like '%Java%'");

        // 四：处理数据库操作结果集
        List<Post> posts = new ArrayList<>();
        while (resultSet.next()) {
            Post post = new Post();
            post.setId(resultSet.getLong("id"));
            post.setTitle(resultSet.getString("title"));
            post.setContent(resultSet.getString("content"));
            post.setUserId(resultSet.getLong("user_id"));
            posts.add(post);
        }

        // 五：关闭数据库连接
        statement.close();

        posts.forEach(System.out::println);
    }
}
