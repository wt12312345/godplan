package com.godplan.m.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wt.base.constant.BegCode;
import com.wt.base.util.FileImgHandler;
import com.wt.base.util.TypeUtil;
import com.wt.base.webconf.PathConf;
import com.wt.web.domain.JsonResponse;

/** 上传文件controller */
@RequestMapping("/upload")
@Controller
public class UploadController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Resource
	private FileImgHandler fileImgHandler;

	/**
	 * 图片上传 目录结构：项目名/商户名/模块/年月日/ 返回结构：一致
	 * */
	@ResponseBody
	@RequestMapping(value = "/uploadimg/{module}", method = RequestMethod.POST)
	public JsonResponse uploadImg(MultipartHttpServletRequest request,
			HttpSession session, HttpServletResponse response,
			@PathVariable String module) throws Exception {
		JsonResponse jr = new JsonResponse();

		String param = request.getParameter("param");
		MultipartHttpServletRequest r = (MultipartHttpServletRequest) request;
		MultipartFile mFile = null;
		if (TypeUtil.isEmpty(param)) {
			mFile = r.getFile("imgFile");
		} else {
			mFile = r.getFile(param);
		}
		if (mFile == null) {
			jr.setMsg("上传文件无法获取");
		} else if (mFile.getSize() > 2097152) {
			// 如果文件大于2M就不给上传-zhouzhihuan
			jr.setMsg("文件大小不能超过2M");
			return jr;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			String month = sdf.format(new Date());
			String fileDirPath = PathConf.getTrendsImgPath() + module + "/"
					+ month;
			File file = new File(fileDirPath);

			if (!file.exists()) {
				file.mkdirs();
			}
			String fileName = fileImgHandler.saveFile(mFile.getBytes(),
					mFile.getOriginalFilename(), fileDirPath);
			jr.setCode(BegCode.SUCCESS);
			jr.setObj(module + "/" + month + "/" + fileName);
		}
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public JsonResponse req(HttpSession session, HttpServletResponse response) {
		int progressBarBoxContent = Integer.parseInt(session.getAttribute(
				"fileuploadprogress").toString());
		JsonResponse js = new JsonResponse(0);
		js.setMsg(progressBarBoxContent + "");
		return js;

	}
}
