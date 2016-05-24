/*
 * 
 */
package com.zeus.patch.maven.plugin.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lambo
 *
 */
public abstract class ClassUtils {

	/**
	 * 
	 */
	private ClassUtils() {
	}

	public static String convertToPath(String packageName) {
		if(packageName != null && packageName != ""){
			return packageName.replaceAll("[.\\\\/]", "/");
		}
		return packageName;
	}
	
	public static List<File> find(File dir, String fileName){
		List<File> result = new ArrayList<File>();
		if(dir == null || fileName == null || fileName == ""){
			return result;
		}
		recursiveSearch(dir, fileName,result);
		return result;
	}

	/**
	 * @param dir
	 * @param fileName
	 * @param result 
	 */
	private static void recursiveSearch(File dir, String fileName, List<File> result) {
		if(dir.exists() && dir.canRead()){
			File[] files = dir.listFiles();
			for (File file : files) {
				if(dir.isDirectory()){
					recursiveSearch(file, fileName, result);
				} else if(file.getName().equals(fileName) || file.getName().startsWith(fileName+"$")){
					result.add(file);
				}
			}
		}
	}
}
