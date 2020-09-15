package com.starnet.service;

import com.starnet.NotFoundException;
import com.starnet.dao.PhoneRepository;
import com.starnet.po.Phone;
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
public class PhoneManagerServiceImpl implements PhoneManageService {

    @Autowired
    private PhoneRepository phoneRepository;
    @Override
    public Phone getPhone(Long id) {
        return phoneRepository.findById(id).get();
    }

    @Override
    public Phone savePhone(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public Page<Phone> listPhone(Pageable pageable, Phone phone) {
        return phoneRepository.findAll(new Specification<Phone>() {
            @Override
            public Predicate toPredicate(Root<Phone> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(phone.getPhonename() != null){
                    predicates.add(criteriaBuilder.like(root.<String>get("phonename"),"%"+phone.getPhonename()+"%"));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public List<Phone> listPhone() {
        return phoneRepository.findAll();
    }

    @Override
    public Phone updatePhone(Long id, Phone phone) {
        Phone p = phoneRepository.findById(id).get();
        if(p == null){
            throw new NotFoundException("该手机接受端不存在");
        }
        BeanUtils.copyProperties(phone,p);
        return phoneRepository.save(p);
    }

    @Override
    public void deletePhone(Long id) {
        phoneRepository.deleteById(id);
    }
}
