package com.wt;

public class Conf {
	public final static String RedisIp = "192.168.1.66";
//	public final static String RedisIp = "127.0.0.1";

	public final static int RedisPort = 6379;
	
	/* 系统基本参数 配置************************************************************* */
	/**
	 * 美元汇率
	 */
//	public static double ExchangeRateUSA = 7.2;
//	/**
//	 * 欧元
//	 */
//	public static double ExchangeRateEUR = 7.5;
//	/**
//	 * 日元
//	 */
//	public static double ExchangeRateYen = 0.06;
	/**
	 * 保税仓税率
	 */
	// public static double TaxRate = 0.119;
	/**
	 * 美国直邮首重：1000克，40块
	 */
	// public static int FreightAmericaFirst = 40;
	/**
	 * 美国直邮续重：500克，20块
	 */
	// public static int FreightAmericaAdded = 20;
	/**
	 * 美国直邮首重：1000克
	 */
	// public static int FreightAmericaFirstWeight = 1000;
	/**
	 * 美国直邮续重：500克
	 */
	// public static int FreightAmericaAddedWeight = 500;
	public static int PageSize = 60;

	// 短信发送频率:120秒
	// public final static int SmsTime = 120000;

	// TODO
	// 测试用
	public final static int SmsTime = 60000;
	/* ********************************************************************************** */

	/* 阿里大于 参数配置************************************************************* */
	public final static String AlidayuUrl = "http://gw.api.taobao.com/router/rest";
	public final static String AlidayuAppKey = "23351629";
	public final static String AlidayuAppSecret = "50dea586815ab6d5305492b72b46581d";
	public final static String AlidayuRegisterTemplateCode = "SMS_8145208";
	public final static String AlidayuPasswordTemplateCode = "SMS_8145206";
	public final static String AlidayuSignName = "海洽网络";
	public final static String AlidayuProduct = "海洽全球供应链";
	/* ********************************************************************************** */

	/* 快递鸟配置************************************************************* */
	// 电商ID
	public final static String KdnEBusinessID = "1270919";
	// 电商加密私钥，快递鸟提供，注意保管，不要泄漏
	public final static String KdnAppKey = "a094a99d-2afb-47da-ab76-87a9f695d9a6";
	// 测试请求url
	public final static String KdnReqTestURL = "http://testapi.kdniao.cc:8081/api/dist";
	// 正式请求url
	public final static String KdnReqURL = "http://api.kdniao.cc/api/dist";

	public static String getKdnLogisticsCode(String logisticsName) {
		String code = "";
		if (logisticsName.contains("中通")) {
			code = "ZTO";
		} else if (logisticsName.contains("圆通")) {
			code = "YTO";
		} else if (logisticsName.contains("申通")) {
			code = "STO";
		} else if (logisticsName.contains("EMS")) {
			code = "EMS";
		} else if (logisticsName.contains("韵达")) {
			code = "YD";
		}
		return code;
	}

	/* ***************************************************************************************** */

	/* 后台邮件权限 配置************************************************************ */
	public final static String action_mail_purchase_cost = "action_mail_purchase_cost";
	public final static String action_mail_purchase_approval = "action_mail_purchase_approval";
	public final static String action_mail_purchase_arrive = "action_mail_purchase_arrive";
	public final static String action_mail_instock_approval = "action_mail_instock_approval";
	public final static String action_mail_instock_finish = "action_mail_instock_finish";
	public final static String action_mail_outstock_approval = "action_mail_outstock_approval";
	public final static String action_mail_outstock_finish = "action_mail_outstock_finish";
	public final static String action_mail_allot_approvalout = "action_mail_allot_approvalout";
	public final static String action_mail_allot_innum = "action_mail_allot_innum";
	public final static String action_mail_allot_approvalin = "action_mail_allot_approvalin";
	public final static String action_mail_allot_finish = "action_mail_allot_finish";
	/* ***************************************************************************************** */

	/* 字典的Key 配置************************************************************ */
	public final static String DicUrlGmyxYouzan = "UrlGmyxYouzan";
	public final static String DicUrlXxbWeidian = "UrlXxbWeidian";
	/**
	 * 云集获取订单时间和页码
	 */
	public final static String DicErp3YunjiGetOrder = "Erp3YunjiGetOrder";
	/**
	 * sku销量统计，统计订单的最后统计时间
	 */
	public final static String DicTimeStatSkuSalse = "TimeStatSkuSalse";
	/**
	 * 第三方ERP，用于模糊查询所有商铺编号
	 */
	public final static String DicErpShop = "ErpShop";
	/**
	 * 北美E店长ERP推送订单店铺号-洋小淘,2
	 */
	public final static String DicErpShopUsaYxt = "ErpShopUsaYxt";
	/**
	 * 中柏E店长ERP推送订单所有店铺号
	 */
	public final static String DicErpShopZb = "ErpShopZb";
	/**
	 * 中柏E店长ERP推送订单店铺号-海洽,46
	 */
	public final static String DicErpShopZbYmt = "ErpShopZbYmt";
	/**
	 * 中柏E店长ERP推送订单店铺号-糕妈优选,47
	 */
	public final static String DicErpShopZbGmyx = "ErpShopZbGmyx";
	public final static String DicEShopZbSendOrderShopId = "EShopZbSendOrderShopId";

	/**
	 * 物产推送订单批次号
	 */
	public final static String DicErpShopWuchanSeriesNo = "DicErpShopWuchanSeriesNo";
	/* ***************************************************************************************** */

	/* 第三方ERP 配置************************************************************ */
	public final static String ErpBodedAbAddOrderAsmx = "http://ytzbfx.erp8.meetok.com/Areas/KJB2C/WebService/KJB2CWebService.asmx";
	public final static String ErpBodedZbEShopLogisticsAsmx = "http://ytzbfx.erp8.meetok.com/Areas/BSM/WebService/LogisticsWebService.asmx";

	public final static String ErpBodedZbShopId = "46";
	public final static String ErpBodedZbKey = "hq20160811";
	public final static String ErpBodedZbSecret = "hq20160811001";

	public final static String ErpBodedShopId = "44";
	public final static String ErpBodedKey = "hq20160226";
	public final static String ErpBodedSecret = "hq20160226001";

	public final static String ErpBodedAddOrderAsmx = "http://nbgtfx.erp5.meetok.com/Areas/KJB2C/WebService/KJB2CWebService.asmx";
	public final static String ErpBodedAddOrderAction = "http://tempuri.org/AddOrder_KJB2C";
	public final static String ErpBodedAddOrderMethod = "AddOrder_KJB2C";
	public final static String ErpBodedAddOrderResponse = "/AddOrder_KJB2CResponse";

	public final static String ErpBodedStockSyncAsmx = "http://nbgtfx.erp5.meetok.com/Areas/BSM/WebService/StockSyncWebService.asmx";
	public final static String ErpBodedStockSyncAction = "http://tempuri.org/GetStock";
	public final static String ErpBodedStockSyncMethod = "GetStock";
	public final static String ErpBodedStockSyncResponse = "/GetStockResponse/GetStockResult/Message";

	public final static String ErpBodedStockSyncAllAsmx = "http://nbgtfx.erp5.meetok.com/Areas/BSM/WebService/StockSyncWebService.asmx";
	public final static String ErpBodedStockSyncAllAction = "http://tempuri.org/GetAllStock";
	public final static String ErpBodedStockSyncAllMethod = "GetAllStock";
	public final static String ErpBodedStockSyncAllResponse = "/GetAllStockResponse/GetAllStockResult/Message";

	public final static String ErpBodedLogisticsAsmx = "http://nbgtfx.erp5.meetok.com/Areas/BSM/WebService/LogisticsWebService.asmx";
	public final static String ErpBodedLogisticsAction = "http://tempuri.org/GetKJB2CLogisticsInfo";
	public final static String ErpBodedLogisticsMethod = "GetKJB2CLogisticsInfo";
	public final static String ErpBodedLogisticsResponse = "/GetKJB2CLogisticsInfoResponse/GetKJB2CLogisticsInfoResult/Message";

	public final static String ErpShopId = "2";
	public final static String ErpKey = "hzhq20151203";
	public final static String ErpSecret = "hzhq201512030001";

	public final static String ErpAddOrderAsmx = "http://hzhq.erp3.meetok.com/Areas/BSM/WebService/OrderWebService.asmx";
	public final static String ErpAddOrderAction = "http://tempuri.org/AddOrder";
	public final static String ErpAddOrderMethod = "AddOrder";
	public final static String ErpAddOrderResponse = "/AddOrderResponse";

	public final static String ErpStockSyncAsmx = "http://hzhq.erp3.meetok.com/Areas/BSM/WebService/StockSyncWebService.asmx";
	public final static String ErpStockSyncAction = "http://tempuri.org/GetStock";
	public final static String ErpStockSyncMethod = "GetStock";
	public final static String ErpStockSyncResponse = "/GetStockResponse/GetStockResult/Message";

	public final static String ErpLogisticsAsmx = "http://hzhq.erp3.meetok.com/Areas/BSM/WebService/LogisticsWebService.asmx";
	public final static String ErpLogisticsAction = "http://tempuri.org/GetLogisticsInfo";
	public final static String ErpLogisticsMethod = "GetLogisticsInfo";
	public final static String ErpLogisticsResponse = "/GetLogisticsInfoResponse/GetLogisticsInfoResult/Message";
	/* ***************************************************************************************** */

}
