/*
 * 
 */
package com.zeus.patch.maven.plugin.utils;

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
		if (packageName != null && packageName != "") {
			return packageName.replaceAll("[.\\\\/]", "/");
		}
		return packageName;
	}
	
}
