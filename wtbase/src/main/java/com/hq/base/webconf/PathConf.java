package com.hq.base.webconf;

/** 用于生成需要存储的路径 */
public class PathConf {

	/** 远程路径 */
	private static final String REMOTE = "remote";
	/** 动态图片路径 */
	private static final String REMOTE_TRENDSIMG = "remote.trendsimg";
	/**
	 * 上传的其他文件
	 */
	private static final String REMOTE_TRENDSFILE = "remote.trendsfile";
	
	/** 静态页面存储路径 */
	private static final String REMOTE_STATIC_HTML = "remote.htmlfolder";
	
	private static final String REMOTE_ASSETS_PATH = "remote.assets";

	/** 根目录 http://www.yangxiaotao.com.cn/ */
	private static final String HTTP = "http";
	/** www.yangxiaotao.com.cn */
	private static final String HTTP_HOST = "http.host";
	/** 动态图片资源访问路径 http://www.yangxiaotao.com.cn/trends/img/ */
	private static final String HTTP_TRENDSIMG = "http.trendsimg";
	private static final String HTTP_TRENDSFILE = "http.trendsfile";

	/** 页面资源访问路径 */
	private static final String HTTP_HTML = "http.html";

	/** JS、css、img等资源访问路径 http://www.yangxiaotao.com.cn/assets */
	private static final String HTTP_ASSETS = "http.assets";
	

	private static String remoteValue = null;
	private static String remoteTrendsImgValue = null;
	private static String remoteTrendsFileValue = null;
	private static String remoteHtmlValue = null;
	private static String http = null;
	private static String httpHost = null;
	private static String trendsImg = null;
	private static String html = null;
	private static String assets = null;
	private static String assetsPath = null;

	/**
	 * 项目物理地址
	 * 
	 * @return
	 */
	public static String getRemotePath() {
		if (remoteValue == null) {
			remoteValue = WebConfLoader.getConfig().getConfig(REMOTE);
		}
		return remoteValue;
	}

	/**
	 * 动态资源物理地址
	 * 
	 * @return
	 */
	public static String getTrendsImgPath() {
		if (remoteTrendsImgValue == null) {
			remoteTrendsImgValue = WebConfLoader.getConfig().getConfig(
					REMOTE_TRENDSIMG);
		}
		return remoteTrendsImgValue;
	}

	public static String getTrendsFilePath() {
		if (remoteTrendsFileValue == null) {
			remoteTrendsFileValue = WebConfLoader.getConfig().getConfig(
					REMOTE_TRENDSFILE);
		}
		return remoteTrendsFileValue;
	}
	/**
	 * HTML文件物理地址
	 * 
	 * @return
	 */
	public static String getHtmlPath() {
		if (remoteHtmlValue == null) {
			remoteHtmlValue = WebConfLoader.getConfig().getConfig(
					REMOTE_STATIC_HTML);
		}
		return remoteHtmlValue;
	}

	/**
	 * http://www.????.com/
	 * 
	 * @return
	 */
	public static String getRoot() {
		if (http == null) {
			http = WebConfLoader.getConfig().getConfig(HTTP);
		}
		return http;
	}

	/**
	 * www.????.com
	 * 
	 * @return
	 */
	public static String getRootHost() {
		if (httpHost == null) {
			httpHost = WebConfLoader.getConfig().getConfig(HTTP_HOST);
		}
		return httpHost;
	}

	/**
	 * http://www.????.com/trends/img/
	 * 
	 * @return
	 */
	public static String getTrendsimg() {
		if (trendsImg == null) {
			trendsImg = WebConfLoader.getConfig().getConfig(HTTP_TRENDSIMG);
		}
		return trendsImg;
	}

	/**
	 * http://www.????.com/assets/
	 * 
	 * @return
	 */
	public static String getAssets() {
		if (assets == null) {
			assets = WebConfLoader.getConfig().getConfig(HTTP_ASSETS);
		}
		return assets;
	}
	
	public static String getAssetsPath() {
		if (assetsPath == null) {
			assetsPath = WebConfLoader.getConfig().getConfig(REMOTE_ASSETS_PATH);
		}
		return assetsPath;
	}

	/**
	 * http://www.????.com/html/
	 * 
	 * @return
	 */
	public static String getHtml() {
		if (html == null) {
			html = WebConfLoader.getConfig().getConfig(HTTP_HTML);
		}
		return html;
	}

}
