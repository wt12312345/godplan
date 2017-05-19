package com.wt.base.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImgUtil {

	/**
	 * 构造函数
	 */
	public ImgUtil(String filePath) throws IOException {

	}

	public static void resizeFix(String filePath, String toFilePath, int w,
			int h) throws IOException {
		File file = new File(filePath);// 读入文件
		Image img = ImageIO.read(file); // 构造Image对象
		resize(img, toFilePath, w, h);
	}

	public static void resizeFix(String filePath, String toFilePath, int w)
			throws IOException {
		File file = new File(filePath);// 读入文件
		Image img = ImageIO.read(file); // 构造Image对象
		int width = img.getWidth(null); // 得到源图宽
		int height = img.getHeight(null);
		int h = (int) (height * w / width);

		resize(img, toFilePath, w, h);
	}

	private static void resize(Image img, String toFilePath, int w, int h)
			throws ImageFormatException, IOException {
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D) image.getGraphics();

		// Map mapH = new HashMap();
		// mapH.put(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON); // 抗锯齿 （抗锯齿总开关）
		// mapH.put(RenderingHints.KEY_TEXT_ANTIALIASING,
		// RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//
		// g.setRenderingHints(mapH);
		// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON);

		// g2.drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图

		g2.drawImage(img.getScaledInstance(w, h, Image.SCALE_SMOOTH), 0, 0,
				null);

		File destFile = new File(toFilePath);
		FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image);
		out.close();
	}
}
