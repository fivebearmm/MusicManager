package com.starnet.dao;

import com.starnet.po.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PhoneRepository extends JpaRepository<Phone,Long> , JpaSpecificationExecutor<Phone> {

}
