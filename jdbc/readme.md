# JDBC Module
Support JDBC.

### Quick Start
1. Add database driver at dependencies and config Database by **application.properties**
```properties
datasource.url=jdbc:mysql://127.0.0.1:3306/train?characterEncoding=utf8&useUnicode=true&verifyServerCertificate=false&useSSL=false&requireSSL=false
datasource.username=root
datasource.password=123456
datasource.driver=com.mysql.jdbc.Driver
```
2. Autowired JDBCTemplate, and then we could use it.
```java
@Bean("UserDao")
public class UserDao {

    @Autowired("JDBCTemplate")
    private JDBCTemplate jdbcTemplate;

    public List<User> findUsers(){
        return jdbcTemplate.query(User.class, "Select * From user");
    }

    public ResultSet findUsersResultSet(){
        return jdbcTemplate.query("Select * From user");
    }

    public int updateUserTable(){
        return jdbcTemplate.update("UPDATE user SET username = 'Zhongshan'");
    }
}
```