package com.jida.tijian.service;

import com.jida.tijian.domain.Checkitem;
import com.jida.tijian.domain.Setmeal;
import com.jida.tijian.domain.Setmealdetailed;
import com.jida.tijian.dto.ResponseObject;
import com.jida.tijian.mapper.CheckitemMapper;
import com.jida.tijian.mapper.SetmealMapper;
import com.jida.tijian.mapper.SetmealdetailedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    SetmealMapper setmealMapper;

    @Autowired
    SetmealdetailedMapper setmealdetailedMapper;

    @Autowired
    CheckitemMapper checkitemMapper;

    @Override
    public ResponseObject listSetmeal(Integer sex){

        List<Setmeal> setmealList = setmealMapper.selectBySex(sex);

        for (int i = 0;i<setmealList.size();i++){

            Setmeal setmeal = setmealList.get(i);


            List<Setmealdetailed> setmealdetailedList = setmealdetailedMapper.selectBySetmealId(setmealList.get(i).getSmId());

            List<Checkitem> checkitemList = new ArrayList<>();

            for (Setmealdetailed sd:setmealdetailedList){
                Integer ciId = sd.getCiId();
                Checkitem checkitem = checkitemMapper.selectByPrimaryKey(ciId);
                checkitemList.add(checkitem);
            }

            setmeal.setCheckitemsList(checkitemList);
        }

        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,setmealList);
    }

    @Override
    public Setmeal loadSetmeal(Setmeal setmeal) {
        return setmealMapper.selectByPrimaryKey(setmeal.getSmId());
    }
}
