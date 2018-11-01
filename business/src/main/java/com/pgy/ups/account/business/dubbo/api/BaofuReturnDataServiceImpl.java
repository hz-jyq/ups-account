package com.pgy.ups.account.business.dubbo.api;


import com.alibaba.dubbo.config.annotation.Service;
import com.pgy.ups.account.business.dao.mapper.BaofuReturnDataDao;
import com.pgy.ups.account.facade.dubbo.api.BaofuReturnDataService;
import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.model.proofread.BaoFuModelReturn;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BaofuReturnDataServiceImpl implements BaofuReturnDataService {

    @Resource
    private BaofuReturnDataDao baofuReturnDataDao;


    @Override
    public List<BaoFuModelReturn> getExcelList(ExcelForm form) {
        return baofuReturnDataDao.getExcelList(form);
    }
}
