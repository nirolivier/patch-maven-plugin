/*
 *
 */
package com.zeus.patch.maven.plugin.test.utils;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.zeus.patch.maven.plugin.csv.CsvFileManager;

/**
 *
 */
public class CsvFileManagerTest {

	/**
	 * Test method for {@link com.zeus.patch.maven.plugin.csv.CsvFileManager#readCsv(java.io.File, char)}.
	 */
	@Test
	public void testReadCsvFileChar() {
	}

	/**
	 * Test method for {@link com.zeus.patch.maven.plugin.csv.CsvFileManager#readCsv(java.lang.String, char)}.
	 */
	@Test
	public void testReadCsvStringChar() {
		List<String[]> fields = CsvFileManager.readCsv("C:\\Users\\n.razafindrabekoto\\Documents\\mydoc\\project\\R20\\patch\\patch2.2\\Test.csv", ',');
		
		for (String[] field : fields) {
			for (String string : field) {
				Assert.assertNotNull(string);
				System.out.println(string);
			}
		}
	}

}
