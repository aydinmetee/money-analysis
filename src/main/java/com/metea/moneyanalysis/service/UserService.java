package com.metea.moneyanalysis.service;

import com.metea.moneyanalysis.dto.UserLoginDTO;
import com.metea.moneyanalysis.dto.UserReadDTO;
import com.metea.moneyanalysis.dto.UserWriteDTO;

public interface UserService {

    UserReadDTO save(UserWriteDTO userWriteDTO);

    UserReadDTO getById(Long id);

    UserReadDTO getByUsername(String username);

    Boolean delete(Long id);

    Boolean login(UserLoginDTO userLoginDTO);
}
