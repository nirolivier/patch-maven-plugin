/*
 * 
 */
package com.zeus.patch.maven.plugin.test.utils;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.zeus.patch.maven.plugin.utils.FileUtil;

/**
 * @author lambo
 *
 */
public class FileUtilTest {

	/**
	 * Test method for {@link com.zeus.patch.maven.plugin.utils.ClassUtils#convertToPath(java.lang.String)}.
	 */
	@Test
	public void testConvertToPath() {
	}
	
	/**
	 * Test method for {@link com.zeus.patch.maven.plugin.utils.ClassUtils#find(java.io.File, java.lang.String)}.
	 */
	@Test
	public void testFind() {
		List<File> files = FileUtil.findLike(new File("/opt/projects/prj-atlas/atlas/atlas-cleva/be.mercator.atlas.cleva.batchs/target/classes"), "BatchAddressRiskTransferImpl.class");
		Assert.assertNotNull(files);
	}

}
