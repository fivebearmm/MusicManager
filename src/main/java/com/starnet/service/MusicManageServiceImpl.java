package com.starnet.service;

import com.starnet.NotFoundException;
import com.starnet.dao.MusicRepository;
import com.starnet.po.Linux;
import com.starnet.po.Music;
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
public class MusicManageServiceImpl implements MusicManageService {

    @Autowired
    MusicRepository musicRepository;

    @Override
    public Music getMusic(Long id) {
        return musicRepository.findById(id).get();
    }

    @Override
    public Page<Music> listMusic(Pageable pageable, Music music) {

        return musicRepository.findAll(new Specification<Music>() {
            @Override
            public Predicate toPredicate(Root<Music> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(music.getMusicname()!= null){
                    predicates.add(criteriaBuilder.like(root.<String>get("musicname"),"%"+music.getMusicname()+"%"));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);

    }

    @Override
    public Music saveMusic(Music music) {
        return musicRepository.save(music);
    }

    @Override
    public Music updateMusic(Long id, Music music) {
        Music m = musicRepository.findById(id).get();
        if(m == null){
            throw new NotFoundException("该音乐不存在");
        }
        BeanUtils.copyProperties(music,m);
        return musicRepository.save(m);
    }

    @Override
    public void deleteMusic(Long id) {
        musicRepository.deleteById(id);
    }
}
