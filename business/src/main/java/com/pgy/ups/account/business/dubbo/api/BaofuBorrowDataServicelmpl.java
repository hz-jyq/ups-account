package com.pgy.ups.account.business.dubbo.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.pgy.ups.account.business.dao.mapper.BaofuBorrowDataDao;
import com.pgy.ups.account.business.dao.mapper.BaofuReturnDataDao;
import com.pgy.ups.account.facade.dubbo.api.BaofuBorrowDataService;
import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.model.proofread.BaoFuModelBorrow;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BaofuBorrowDataServicelmpl  implements BaofuBorrowDataService {

    @Resource
    private BaofuBorrowDataDao baofuBorrowDataDao;


    @Override
    public List<BaoFuModelBorrow> getExcelList(ExcelForm form) {
        return  baofuBorrowDataDao.getExcelList(form);
    }
}
