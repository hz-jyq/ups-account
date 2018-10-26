package com.pgy.ups.account.facade.model.proofread;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.pgy.ups.account.commom.utils.DateUtils;
import com.pgy.ups.account.facade.model.Model;



/**
 * 对账汇总结果数据
 * 
 * @author 墨凉
 *
 */
public class ProofreadSum extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6385302551739865578L;

	protected Long id;

	// 对账系统编码 01：美期 02：米融 03：秒呗
	protected String fromSystem;

	// '对账类型 01：借款对账 02：还款对账',
	protected String proofreadType;

	// 对账日期
	protected String proofreadDate;

	// 创建时间
	protected String createTime;

	//'对账渠道 01:宝付',
	protected String channel;

	// 商户订单号
	protected String businessNum;

	// 业务交易总金额
	protected BigDecimal businessTotalMoney;

	// 业务交易总笔数
	protected Integer businessTotal;

	// 渠道交易金额（宝付或其他第三方）
	protected BigDecimal channelTotalMoney;

	// 渠道交易总笔数（宝付或其他第三方）
	protected Integer channelTotal;

	// 对账成功交易金额
	protected BigDecimal successTotalMoney;

	// 对账成功总笔数
	protected Integer successTotal;

	// 渠道对账失败交易金额
	protected BigDecimal channelFailTotalMoney;

	// 渠道对账失败总笔数
	protected Integer channelFailTotal;

	// 业务对账失败交易金额
	protected BigDecimal businessFailTotalMoney;

	// 业务对账失败总笔数
	protected Integer businessFailTotal;

	// '对账状态 01 已对账 02 有异常 03待对账'
	protected String proofreadStatus;

	// 最後一次修改時間
	protected String updateTime;

	// 操作人員
	protected String updateUser;

	// 对账成功（已对账）
	public static final String PROOFREAD_SUCCESS = "01";

	// 对账异常（有异常）
	public static final String PROOFREAD_ERROR = "02";

	// 对账异常（未对账）
	public static final String PROOFREAD_NON = "03";

	public ProofreadSum() {
	}

	public ProofreadSum(ProofreadResult proofreadResult) {
		this.setFromSystem(proofreadResult.getFromSystem());
		this.setProofreadType(proofreadResult.getProofreadType());
		this.setProofreadDate(proofreadResult.getProofreadDate());	
		this.setChannel(proofreadResult.getChannel());
		this.setProofreadStatus(PROOFREAD_NON);
		this.setBusinessNum(proofreadResult.getBusinessNum());
		// 设置创建时间
		this.setCreateTime(DateUtils.getCurrentDateTime());
	}

	/**
	 * 对账前初始化
	 * 
	 * @param businessList
	 * @param baofuList
	 */

	public ProofreadSum initBeforeProofread(List<BusinessProofreadModel> businessList,
			List<? extends BaoFuModel> baofuList) {
		List<BusinessProofreadModel> bl = new ArrayList<>(businessList);
		List<? extends BaoFuModel> cl = new ArrayList<>(baofuList);
		// 业务总笔数
		this.setBusinessTotal(bl.size());
		// 渠道（宝付）总笔数
		this.setChannelTotal(cl.size());
		// 业务总金额
		this.setBusinessTotalMoney(bl.stream().map(e -> {
			return e.getExchangeAmount();
		}).reduce(BigDecimal.ZERO, BigDecimal::add));
		// 宝付总金额
		this.setChannelTotalMoney(cl.stream().map(e -> {
			return e.getExchangeAmount();
		}).reduce(BigDecimal.ZERO, BigDecimal::add));
		// 设置对账成功金额初始值为0
		this.successTotalMoney = BigDecimal.ZERO;
		this.successTotal = 0;
		// 设置渠道对账失败金额初始值为0
		this.channelFailTotalMoney = BigDecimal.ZERO;
		this.channelFailTotal = 0;
		// 设置业务对账失败金额初始值为0
		this.businessFailTotalMoney = BigDecimal.ZERO;
		this.businessFailTotal = 0;
		return this;
	}

	/**
	 * 对账后生成结果
	 * 
	 * @param businessList
	 * @param baofuList
	 */
	public ProofreadSum buildAfterProofread(List<BusinessProofreadModel> businessList,
			List<? extends BaoFuModel> baofuList) {
		
		// 业务失败总笔数(包括差错账的和缺省账的总数)
		this.setBusinessFailTotal(this.businessFailTotal + businessList.size());
		// 渠道失败总笔数(包括差错账的和缺省账的总数)
		this.setChannelFailTotal(this.channelFailTotal + baofuList.size());
		// 业务失败总额(包括差错账的和缺省账的总额)
		this.setChannelFailTotalMoney(this.channelFailTotalMoney.add(baofuList.stream().map((e) -> {
			return e.getExchangeAmount();
		}).reduce(BigDecimal.ZERO, BigDecimal::add)));
		// 渠道失败总额(包括差错账的和缺省账的总额)
		this.setBusinessFailTotalMoney(this.businessFailTotalMoney.add(businessList.stream().map((e) -> {
			return e.getExchangeAmount();
		}).reduce(BigDecimal.ZERO, BigDecimal::add)));

		// 如果有一条失败，则对账失
		if (businessFailTotal > 0 || channelFailTotal > 0) {
			this.setProofreadStatus(PROOFREAD_ERROR);
		} else {
			this.setProofreadStatus(PROOFREAD_SUCCESS);
		}
		return this;

	}

	// 成功对账数加1
	public BigDecimal increaseSuccess(BigDecimal money) {
		this.successTotal++;
		return this.successTotalMoney = this.successTotalMoney.add(money);
	}

	// 渠道对账失败
	public BigDecimal increaseChannelFail(BigDecimal money) {
		this.channelFailTotal++;
		return this.channelFailTotalMoney = this.channelFailTotalMoney.add(money);
	}

	// 业务对账失败
	public BigDecimal increaseBusinessFail(BigDecimal money) {
		this.businessFailTotal++;
		return this.businessFailTotalMoney = this.businessFailTotalMoney.add(money);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromSystem() {
		return fromSystem;
	}

	public void setFromSystem(String fromSystem) {
		this.fromSystem = fromSystem;
	}

	public String getProofreadType() {
		return proofreadType;
	}

	public void setProofreadType(String proofreadType) {
		this.proofreadType = proofreadType;
	}

	public String getProofreadDate() {
		return proofreadDate;
	}

	public void setProofreadDate(String proofreadDate) {
		this.proofreadDate = proofreadDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}




	public String getBusinessNum() {
		return businessNum;
	}

	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}

	public BigDecimal getBusinessTotalMoney() {
		return businessTotalMoney;
	}

	public void setBusinessTotalMoney(BigDecimal businessTotalMoney) {
		this.businessTotalMoney = businessTotalMoney;
	}

	public Integer getBusinessTotal() {
		return businessTotal;
	}

	public void setBusinessTotal(Integer businessTotal) {
		this.businessTotal = businessTotal;
	}

	public BigDecimal getChannelTotalMoney() {
		return channelTotalMoney;
	}

	public void setChannelTotalMoney(BigDecimal channelTotalMoney) {
		this.channelTotalMoney = channelTotalMoney;
	}

	public Integer getChannelTotal() {
		return channelTotal;
	}

	public void setChannelTotal(Integer channelTotal) {
		this.channelTotal = channelTotal;
	}

	public BigDecimal getSuccessTotalMoney() {
		return successTotalMoney;
	}

	public void setSuccessTotalMoney(BigDecimal successTotalMoney) {
		this.successTotalMoney = successTotalMoney;
	}

	public Integer getSuccessTotal() {
		return successTotal;
	}

	public void setSuccessTotal(Integer successTotal) {
		this.successTotal = successTotal;
	}

	public BigDecimal getChannelFailTotalMoney() {
		return channelFailTotalMoney;
	}

	public void setChannelFailTotalMoney(BigDecimal channelFailTotalMoney) {
		this.channelFailTotalMoney = channelFailTotalMoney;
	}

	public Integer getChannelFailTotal() {
		return channelFailTotal;
	}

	public void setChannelFailTotal(Integer channelFailTotal) {
		this.channelFailTotal = channelFailTotal;
	}

	public BigDecimal getBusinessFailTotalMoney() {
		return businessFailTotalMoney;
	}

	public void setBusinessFailTotalMoney(BigDecimal businessFailTotalMoney) {
		this.businessFailTotalMoney = businessFailTotalMoney;
	}

	public Integer getBusinessFailTotal() {
		return businessFailTotal;
	}



	public void setBusinessFailTotal(Integer businessFailTotal) {
		this.businessFailTotal = businessFailTotal;
	}

	public String getProofreadStatus() {
		return proofreadStatus;
	}

	public void setProofreadStatus(String proofreadStatus) {
		this.proofreadStatus = proofreadStatus;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}



}
