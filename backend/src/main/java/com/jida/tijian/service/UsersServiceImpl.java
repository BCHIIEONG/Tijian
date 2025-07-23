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

    @Override
    public ResponseObject<Users> register(Users user) {
        ResponseObject<Users> responseObject = null;
        
        try {
            // 检查用户是否已存在
            Users existingUser = usersMapper.selectByPrimaryKey(user.getUserId());
            if (existingUser != null) {
                responseObject = new ResponseObject<>(201, "该手机号已注册，请直接登录");
                return responseObject;
            }
            
            // 插入新用户
            int result = usersMapper.insertSelective(user);
            if (result > 0) {
                responseObject = new ResponseObject<>(ResponseObject.SUCCESS_STATUS, "注册成功", user);
            } else {
                responseObject = new ResponseObject<>(202, "注册失败，请稍后重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseObject = new ResponseObject<>(203, "注册失败，系统错误");
        }
        
        return responseObject;
    }

    @Override
    public ResponseObject<Boolean> checkUserExist(String userId) {
        ResponseObject<Boolean> responseObject = null;
        
        try {
            Users existingUser = usersMapper.selectByPrimaryKey(userId);
            boolean exists = existingUser != null;
            responseObject = new ResponseObject<>(ResponseObject.SUCCESS_STATUS, "查询成功", exists);
        } catch (Exception e) {
            e.printStackTrace();
            responseObject = new ResponseObject<>(204, "查询失败", false);
        }
        
        return responseObject;
    }
}
