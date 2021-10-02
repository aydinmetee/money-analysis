package com.metea.moneyanalysis.service;

import com.metea.moneyanalysis.domain.UserDetail;
import com.metea.moneyanalysis.dto.UserLoginDTO;
import com.metea.moneyanalysis.dto.UserWriteDTO;

public interface UserService {

    UserDetail save(UserWriteDTO userWriteDTO);

    UserDetail getById(Long id);

    UserDetail getByUsername(String username);

    Boolean delete(Long id);

    Boolean login(UserLoginDTO userLoginDTO);

    UserDetail getSessionInfo();
}
