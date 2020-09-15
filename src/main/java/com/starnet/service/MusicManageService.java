package com.starnet.service;

import com.starnet.po.Linux;
import com.starnet.po.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MusicManageService {

    Music getMusic(Long id);//查询一个

    Page<Music> listMusic(Pageable pageable,Music music);//分页查询

    Music saveMusic(Music music);//新增

    Music updateMusic(Long id,Music music);//修改

    void deleteMusic(Long id);
}
