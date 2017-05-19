package com.wt.base.util;

import java.io.IOException;

public interface FileHandler {
	public String saveFile(byte[] content, String filename, String path)
			throws IOException;

	String saveFileCom(byte[] content, String filename, String path)
			throws IOException;

	public void removeFile(String filename, String path) throws IOException;
}
