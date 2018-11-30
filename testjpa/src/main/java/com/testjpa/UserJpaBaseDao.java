package com.testjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

public interface UserJpaBaseDao extends JpaRepository<User, Long> {

}
