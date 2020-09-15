package com.starnet.service;

import com.starnet.NotFoundException;
import com.starnet.dao.LinuxRepository;
import com.starnet.po.Linux;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class LinuxManageServiceImpl implements LinuxManageService {


    @Autowired
    private LinuxRepository linuxRepository;

    @Override
    public Linux getLinux(Long id) {
        return linuxRepository.findById(id).get();
    }

    @Override
    public Page<Linux> listLinux(Pageable pageable, Linux linux) {
        return linuxRepository.findAll(new Specification<Linux>() {
            @Override
            public Predicate toPredicate(Root<Linux> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(linux.getLinuxname() != null){
                    predicates.add(criteriaBuilder.like(root.<String>get("linuxname"),"%"+linux.getLinuxname()+"%"));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Linux saveLinux(Linux linux) {
        return linuxRepository.save(linux);
    }

    @Override
    public Linux updateLinux(Long id, Linux linux) {

        Linux l = linuxRepository.findById(id).get();
        if(l == null){
            throw new NotFoundException("该广告端设备不存在");
        }
        BeanUtils.copyProperties(linux,l);
        return linuxRepository.save(l);
    }

    @Override
    public void deleteLinux(Long id) {

        linuxRepository.deleteById(id);

    }
}
