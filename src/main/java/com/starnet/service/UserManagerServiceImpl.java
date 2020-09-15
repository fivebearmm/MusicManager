package com.starnet.service;

import com.starnet.NotFoundException;
import com.starnet.dao.UserManagerRepository;
import com.starnet.po.User;
import com.starnet.vo.UserQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    private UserManagerRepository userManagerRepository;

    @Transactional
    @Override
    public User getUser(Long id) {

        return userManagerRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Page<User> listUser(Pageable pageable, UserQuery user) {

        return userManagerRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if(user.getUsername() != null){
                     predicates.add(criteriaBuilder.like(root.<String>get("username"),"%"+user.getUsername()+"%"));
                }
                if(user.getMobliephone() != null){
                    predicates.add(criteriaBuilder.like(root.<String>get("mobilephone"),"%"+user.getMobliephone()+"%"));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Transactional
    @Override
    public User saveUser(User user) {

        return userManagerRepository.save(user);
    }

    @Transactional
    @Override
    public User updateUser(Long id, User user) {
        User u = userManagerRepository.findById(id).get();
        if(u == null){
            throw new NotFoundException("该用户不存在");
        }
        BeanUtils.copyProperties(user,u);
        return userManagerRepository.save(u);
    }

    @Override
    public void deleteUser(Long id) {
            userManagerRepository.deleteById(id);
    }
}
