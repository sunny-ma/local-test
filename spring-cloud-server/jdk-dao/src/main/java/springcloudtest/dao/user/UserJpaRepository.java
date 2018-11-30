package springcloudtest.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import springcloudtest.dao.base.BaseDao;
import springcloudtest.dao.entity.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {

}
