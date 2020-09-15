package com.starnet.service;

import com.starnet.po.Phone;
import com.starnet.po.User;
import com.starnet.vo.UserQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PhoneManageService  {

    Phone getPhone(Long id);

    Phone savePhone(Phone phone);

    Page<Phone> listPhone(Pageable pageable,Phone phone);

    List<Phone> listPhone();

    Phone updatePhone(Long id, Phone phone);

    void deletePhone(Long id);
}
