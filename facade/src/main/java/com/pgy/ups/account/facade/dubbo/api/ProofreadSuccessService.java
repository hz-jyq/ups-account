package com.pgy.ups.account.facade.dubbo.api;

import com.pgy.ups.account.facade.from.ExcelForm;
import com.pgy.ups.account.facade.model.proofread.BaoFuModelReturn;
import com.pgy.ups.account.facade.model.proofread.ProofreadSuccess;

import javax.rmi.PortableRemoteObject;
import java.util.List;

public interface ProofreadSuccessService {

    List<ProofreadSuccess> getExcelList(ExcelForm form);
}
