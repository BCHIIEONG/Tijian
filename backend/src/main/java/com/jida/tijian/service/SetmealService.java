package com.jida.tijian.service;

import com.jida.tijian.domain.Setmeal;
import com.jida.tijian.dto.ResponseObject;

public interface SetmealService {
    ResponseObject listSetmeal(Integer sex);

    Setmeal loadSetmeal(Setmeal setmeal);
}
