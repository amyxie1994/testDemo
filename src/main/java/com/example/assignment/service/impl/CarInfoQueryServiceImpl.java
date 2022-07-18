package com.example.assignment.service.impl;

import com.example.assignment.common.errorcode.CarRentalErrorCode;
import com.example.assignment.controller.BusinessException;
import com.example.assignment.dao.CarInfoMapper;
import com.example.assignment.domain.convert.CarInfoConvert;
import com.example.assignment.domain.domain.CarInfoDO;
import com.example.assignment.domain.po.CarInfoPO;
import com.example.assignment.service.CarInfoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service("carInfoQueryService")
public class CarInfoQueryServiceImpl implements CarInfoQueryService {

    @Autowired
    private CarInfoMapper carInfoMapper;

    @Override
    public List<CarInfoDO> queryCarInfoListByModel(String model) {
       if(StringUtils.isEmpty(model)){
           throw new BusinessException(CarRentalErrorCode.PARAMS_EMPTY.getCode(),"model name empty");
       }
       CarInfoPO queryPO = new CarInfoPO();
       queryPO.setCarModel(model);
       return queryCarInfoListByParams(queryPO);

    }

    private List<CarInfoDO> queryCarInfoListByParams(CarInfoPO carInfoPO){
        return CarInfoConvert.carInfoPOList2DOList(carInfoMapper.queryCarInfoListByParams(carInfoPO));
    }


}
