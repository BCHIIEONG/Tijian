package com.jida.tijian.service;

import com.jida.tijian.domain.Users;
import com.jida.tijian.dto.ResponseObject;

public interface UsersService {
    ResponseObject<Users> login(Users user);
    ResponseObject<Users> register(Users user);
    ResponseObject<Boolean> checkUserExist(String userId);
}
