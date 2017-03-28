package com.iwilley.b1ec2.api.request;

import java.util.Date;
import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.SalesOrderQueryResponse;

public class SalesOrderQueryRequest implements B1EC2Request<SalesOrderQueryResponse>{

	//�ֿ�ID��Ĭ�ϲ�ѯ���вֿ����ݣ�����Ĭ��ֵ��ÿ��ֻ�ܲ�ѯһ���ֿ⡣ 
    public Integer whsId;

   //����ID��Ĭ�ϲ�ѯ���е��̵���ݣ�
    public Integer shopId;
    
    /// ʱ�����ͣ�Ĭ��10
    /// 10:������ʱ��;20:������ʱ��
    public Integer timeType;
    
    // ����״̬��Ĭ�ϲ�ѯ���н���״̬����ݣ�����Ĭ��ֵ��ÿ��ֻ�ܲ�ѯһ��״̬�� 
    // 10:�����;20:�����;30:����ͨ��;40:��ʼ���;43:�����;45:�ѳ���;60:�ѽ���;70:�����;80:��ȡ��;99:��ɾ��;
    public Integer status;
    
    //������
    public String orderNo;
    public String shopOrderNo;

	//�����޸Ŀ�ʼʱ��
    public Date startTime;

    //�����޸Ľ���ʱ��
    public Date endTime;

    // ҳ�롣ȡֵ��Χ:�����������;Ĭ��ֵ:1��
    // ע��������õ���ķ�ҳ��ʽ�������һҳ���ȡ�����ܱ���©�����⡣
    public Integer pageNum;

    //ÿҳ����ȡֵ��Χ��1~100��Ĭ��ֵ��50
    public Integer pageSize;
    
    /// //�Զ�������1
    public String userDefinedField1;

    /// //�Զ�������2
    public String userDefinedField2;
    
    // ��Ʊ��
    public String invoiceNo;
    
	@Override
	public String getApiMethodName() {
        return "B1EC2.SalesOrder.Query";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("WhsId", whsId);
		parameters.put("TimeType", timeType);
		parameters.put("OrderNo", orderNo);
		parameters.put("ShopOrderNo", shopOrderNo);
		parameters.put("ShopId", shopId);
		parameters.put("Status", status);
		parameters.put("StartTime", startTime);
		parameters.put("EndTime", endTime);
		parameters.put("PageNum", pageNum);
		parameters.put("PageSize", pageSize);
		parameters.put("UserDefinedField1", userDefinedField1);
		parameters.put("UserDefinedField2", userDefinedField2);
		parameters.put("InvoiceNo", invoiceNo);
		return parameters;
	}

	@Override
	public Class<SalesOrderQueryResponse> getResponseClass() {
		return SalesOrderQueryResponse.class;
	}

	public Integer getWhsId() {
		return whsId;
	}

	public void setWhsId(Integer whsId) {
		this.whsId = whsId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getUserDefinedField1() {
		return userDefinedField1;
	}

	public void setUserDefinedField1(String userDefinedField1) {
		this.userDefinedField1 = userDefinedField1;
	}
	
	public String getUserDefinedField2() {
		return userDefinedField2;
	}

	public void setUserDefinedField2(String userDefinedField2) {
		this.userDefinedField2 = userDefinedField2;
	}

	public Integer getTimeType() {
		return timeType;
	}

	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}
	
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getShopOrderNo() {
		return shopOrderNo;
	}

	public void setShopOrderNo(String shopOrderNo) {
		this.shopOrderNo = shopOrderNo;
	}
	
}
