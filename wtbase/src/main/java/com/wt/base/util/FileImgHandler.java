package com.wt.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

public class FileImgHandler implements FileHandler {
	private Logger logger = Logger.getLogger(this.getClass());
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String saveFile(byte[] content, String filename, String path)
			throws IOException {
		logger.debug("Save Content " + filename + " To:" + location);
		File fileDir = new File(path);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		String saveFileName = System.currentTimeMillis()
				+ ShortPath.shortText(filename) + ".jpg";
		FileOutputStream fo = new FileOutputStream(
				new File(path + saveFileName));
		try {
			fo.write(content);
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			fo.flush();
			fo.close();
		}
		return saveFileName;
	}

	@Override
	public String saveFileCom(byte[] content, String filename, String path)
			throws IOException {
		logger.debug("Save Content " + filename + " To:" + location);
		File fileDir = new File(path);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		
		FileOutputStream fo = new FileOutputStream(
				new File(path + filename));
		try {
			fo.write(content);
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			fo.flush();
			fo.close();
		}
		return filename;
	}

	@Override
	public void removeFile(String filename, String path) throws IOException {
		logger.info("Delete Content " + filename + " To:" + location);
		File file = new File(path + "/" + filename);
		if (file.exists()) {
			file.delete();
		}
	}
}
