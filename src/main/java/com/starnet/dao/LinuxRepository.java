package com.starnet.dao;

import com.starnet.po.Linux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LinuxRepository extends JpaRepository<Linux,Long>, JpaSpecificationExecutor<Linux> {
}
