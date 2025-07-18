package com.jida.tijian.service;

import com.jida.tijian.domain.Users;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.exception.TijianAPPException;
import com.jida.tijian.exception.TijianAPPExceptionEnum;
import com.jida.tijian.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersMapper usersMapper;

//    @Override
//    public ResponseObject<Users> login1(Users user){
//        ResponseObject<Users> responseObject = null;
//
//        Users result = usersMapper.selectByPrimaryKey(user.getUserId());
//
//        if(result == null){
//            responseObject = new ResponseObject<>(201,"手机号尚未注册");
//            return responseObject;
//        }
//
//        if(!result.getPassword().equals(user.getPassword())){
//            responseObject = new ResponseObject<>(202,"密码错误");
//            return responseObject;
//        }
//
//        responseObject = new ResponseObject<>(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,result);
//
//        return responseObject;
//    }

    @Override
    public ResponseObject<Users> login(Users user){
        //int a = 10/0;

        ResponseObject<Users> responseObject = null;

        Users result = usersMapper.selectByPrimaryKey(user.getUserId());

        if(result == null){
            //responseObject = new ResponseObject<>(201,"手机号尚未注册");
            throw new TijianAPPException(TijianAPPExceptionEnum.MOBILE_NOT_REGISTER.getCode(), TijianAPPExceptionEnum.MOBILE_NOT_REGISTER.getMessage());
            //return responseObject;
        }

        if(!result.getPassword().equals(user.getPassword())){
            responseObject = new ResponseObject<>(202,"密码错误");
            throw new TijianAPPException(TijianAPPExceptionEnum.USERID_OR_PASSWORD_IS_WRONG.getCode(), TijianAPPExceptionEnum.USERID_OR_PASSWORD_IS_WRONG.getMessage());
            //return responseObject;
        }

        responseObject = new ResponseObject<>(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,result);

        return responseObject;
    }
}
