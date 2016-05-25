/*
 * 
 */
package com.zeus.patch.maven.plugin.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.FileUtils;

/**
 * @author lambo
 *
 */
public abstract class FileUtil {

	/**
	 * 
	 */
	private FileUtil() {
	}
	
	public static List<File> findLike(File dir, String fileName) {
		List<File> result = new ArrayList<File>();
		if (dir == null || fileName == null || fileName == "") {
			return result;
		}
		recursiveSearch(dir, FileUtils.removeExtension(fileName), result, false);
		return result;
	}

	public static List<File> findExact(File dir, String fileName) {
		List<File> result = new ArrayList<File>();
		if (dir == null || fileName == null || fileName == "") {
			return result;
		}
		recursiveSearch(dir, fileName, result, true);
		return result;
	}
	
	/**
	 * @param dir
	 * @param fileName
	 * @param result
	 */
	private static void recursiveSearch(File dir, String fileName, List<File> result, boolean exact) {
		if (dir.exists() && dir.canRead()) {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					recursiveSearch(file, fileName, result, exact);
				} else if ((!exact && file.getName().startsWith(fileName)) || (file.getName().equals(fileName))) {
					result.add(file);
				}
			}
		}
	}
	
	public static List<File> getAllFileInDirectory(File dir){
		List<File> filesOnly = new ArrayList<File>();
		pushIfOnlyFile(dir, filesOnly);
		return filesOnly;
	}
	
	private static void pushIfOnlyFile(File file, List<File> filesOnly){
		if(file != null && file.exists() && file.canRead() && filesOnly != null){
			File[] files = file.listFiles();
			for (File eachFile : files) {
				if (eachFile.isDirectory()) {
					pushIfOnlyFile(eachFile,filesOnly);
				} else {
					filesOnly.add(eachFile);
				}
			}
		}
	}

}
