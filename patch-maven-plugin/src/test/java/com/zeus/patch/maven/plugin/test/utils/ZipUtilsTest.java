/*
 * 
 */
package com.zeus.patch.maven.plugin.test.utils;

import java.io.File;

import org.junit.Test;

import com.zeus.patch.maven.plugin.utils.ZipUtils;

/**
 * @author lambo
 *
 */
public class ZipUtilsTest {

	
	/**
	 * Test method for {@link com.zeus.patch.maven.plugin.utils.ZipUtils#zipDirectory(java.io.File, java.io.File, java.lang.String)}.
	 */
	@Test
	public void testZipDirectory() {
		ZipUtils.zipDirectory(new File("/opt/projects/prj-tmp/patch/release"), null, null);
	}

}
