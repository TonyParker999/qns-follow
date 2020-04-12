package cn.platform.app.service;


import cn.platform.app.dao.TestDao;
import cn.platform.app.model.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl {
    @Resource
    private TestDao testDao;

//    @Cacheable(value ="springBoot-redis-userListCach")
    public List<Map<String,Object>> getUsers(Map<String, Object> map) {
        List<Map<String, Object>> users = testDao.getUsers(map);
        return users;
    }

    @Transactional
    public void update(User user) {
        testDao.updateUser(user);
        System.out.println(1/0);
    }

    @Transactional
    @CachePut(key="#user.id",value = "User")
    public int insertUser(User user) {
        testDao.insertUser(user);
        return user.getId();
    }

    @Cacheable(key="#id",value ="returnUser")
    public User getUserById(int id){
        User user = testDao.getUserById(id);
        return user;
    }
}
