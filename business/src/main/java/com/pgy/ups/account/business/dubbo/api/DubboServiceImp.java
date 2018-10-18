package com.pgy.ups.account.business.dubbo.api;


import com.alibaba.dubbo.config.annotation.Service;
import com.pgy.ups.account.facade.dubbo.api.DubboService;

@Service(version = "1.0.0")
public class DubboServiceImp implements DubboService {

	@Override
	public String doService() {
		return "do dubbo..";
	}

}
