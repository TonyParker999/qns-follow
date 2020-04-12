package cn.platform.app.dao;

import cn.platform.app.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TestDao {

    @Insert("insert into sys_user (id,name,phone) values ( #{id},#{name},#{phone} )")
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")//获取自增id
    int insertUser(User user);

    @Select("select * from sys_user u where u.id=#{id}")
    User getUserById(int id);

    List<Map<String,Object>> getUsers(Map<String, Object> map);

    void updateSpecialReview(Map<String, Object> map);

    void updateUser(User user);
}
