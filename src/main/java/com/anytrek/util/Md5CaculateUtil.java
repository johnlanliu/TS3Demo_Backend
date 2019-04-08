package com.anytrek.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5CaculateUtil {

	public static String getHash(File file) throws IOException, NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		InputStream ins = null;
		try {
			ins = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int len;
			while ((len = ins.read(buffer)) != -1) {
				md5.update(buffer, 0, len);
			}
		} finally {
			if (ins != null) {
				ins.close();
			}
		}
		return StringTools.toHexString(md5.digest());
	}

	public static void main(String[] args) throws Exception, Exception {
		long start = System.currentTimeMillis();
		System.out.println("开始计算文件MD5值,请稍后...");
		String fileName = "C:\\Users\\z-hongc\\Desktop\\64BitMailAgent.exe";
		String hash = getHash(new File(fileName));
		System.out.println("MD5:" + hash);
		long end = System.currentTimeMillis();
		System.out.println("一共耗时:" + (end - start) + "毫秒");
	}
}