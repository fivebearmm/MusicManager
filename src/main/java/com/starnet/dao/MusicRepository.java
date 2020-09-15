package com.starnet.dao;
import com.starnet.po.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MusicRepository extends JpaRepository<Music,Long>, JpaSpecificationExecutor<Music> {

}
