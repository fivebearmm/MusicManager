package com.starnet.dao;

import com.starnet.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserManagerRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
}
