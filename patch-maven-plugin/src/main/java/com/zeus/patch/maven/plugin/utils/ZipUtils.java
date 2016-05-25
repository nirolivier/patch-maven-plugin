/*
 * 
 */
package com.zeus.patch.maven.plugin.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author lambo
 *
 */
public abstract class ZipUtils {

	public static void zipDirectory(File dir, File zipOut,String zipExt){
		if(dir != null && dir.isDirectory() && dir.canRead()){
			try {
				
				if(zipExt == null || zipExt==""){
					zipExt = ".zip";
				}
				String zipOutPath = null;
				if( zipOut == null){
					zipOutPath = dir.getPath();
				}else{
					zipOutPath=zipOut.getPath();
				}
				
				byte[] bf = new byte[1024];
				
				FileOutputStream fos = new FileOutputStream(zipOutPath + zipExt);
				ZipOutputStream zos = new ZipOutputStream(fos);
				
				List<File> filesOnly = FileUtil.getAllFileInDirectory(dir);
				
				for (File eachFile : filesOnly) {
					String fileToZip = eachFile.getPath().substring(dir.getPath().length(), eachFile.getPath().length());
					ZipEntry ze = new ZipEntry(fileToZip);
					zos.putNextEntry(ze);
					FileInputStream in = new FileInputStream(eachFile);
					int len = 0;
					while ((len = in.read(bf)) > 0 ) {
						zos.write(bf, 0, len);
					}
					
					in.close();
				}
				
				zos.closeEntry();
				zos.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
