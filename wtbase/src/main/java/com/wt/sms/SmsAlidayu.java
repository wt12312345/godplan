package com.wt.sms;

import com.wt.CallBackBo;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SmsAlidayu {
	/**
	 * 
	 * @param mobile
	 *            手机号
	 * @param code
	 *            验证码
	 * @param extend
	 *            回调的参数
	 * @param templateCode
	 *            模版
	 * @return
	 */
	public static CallBackBo send(SmsAlidayuBo args) {
		CallBackBo callBack = new CallBackBo();
		try {
			TaobaoClient client = new DefaultTaobaoClient(args.getUrl(),
					args.getAppKey(), args.getAppSecret());
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setExtend(args.getExtend());
			req.setSmsType("normal");
			req.setSmsFreeSignName(args.getSignName());
			req.setSmsParamString("{\"code\":\"" + args.getCode()
					+ "\",\"product\":\"" + args.getProduct() + "\"}");
			req.setRecNum(args.getMobile());
			req.setSmsTemplateCode(args.getTemplateCode());
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			System.out.println(rsp.getBody());
			if (rsp.getResult() != null) {
				callBack.setResult(rsp.getResult().getSuccess());
			} else {
				callBack.setMsg(rsp.getSubMsg());
			}
			callBack.setContent(rsp.getBody());
		} catch (ApiException e) {
			callBack.setErr(e.getMessage());
		}
		return callBack;
	}
}

// {"alibaba_aliqin_fc_sms_num_send_response":{"result":{"err_code":"0","model":"101478252794^1102004460032","success":true},"request_id":"11iggx8y6vbqd"}}
// {"error_response":{"code":15,"msg":"Remote service error","sub_code":"isv.SMS_TEMPLATE_ILLEGAL","sub_msg":"短信模板不合法","request_id":"z29cpapr4oaq"}}
