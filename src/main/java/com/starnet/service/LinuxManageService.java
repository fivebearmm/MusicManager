package com.starnet.service;

import com.starnet.po.Linux;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LinuxManageService {

    Linux getLinux(Long id);//查询一个

    Page<Linux> listLinux(Pageable pageable, Linux linux);//分页查询

    Linux saveLinux(Linux linux);//新增

    Linux updateLinux(Long id,Linux linux);//修改

    void deleteLinux(Long id);
}
