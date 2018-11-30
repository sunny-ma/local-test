package user;

import com.testjpa.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserJpaRepositoryTest {

    @Autowired
    IUserService userService;

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    UserJpaBaseDao userJpaBaseDao;

    @Test
    public void findOne(){
        userService.findOne(0L);
    }

    @Test
    public void deleteAll(){
        userJpaRepository.deleteAll();
    }

    @Test
    public void save(){
        User user = new User();
        user.setId(111111111111111L);
        user.setName("测试1");
        user.setAddress("张三的地址");
        userJpaBaseDao.save(user);
    }
}

