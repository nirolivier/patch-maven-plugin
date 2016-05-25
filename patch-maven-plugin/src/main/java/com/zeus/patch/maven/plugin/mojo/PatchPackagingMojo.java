/*
 * 
 */
package com.zeus.patch.maven.plugin.mojo;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.zeus.patch.maven.plugin.utils.ZipUtils;

/**
 * @author lambo
 *
 */
@Mojo(name = "package", defaultPhase = LifecyclePhase.PACKAGE,threadSafe = true)
public class PatchPackagingMojo extends AbstractPatchMojo {

	@Parameter(name = "archiveType", property = "archiveType", required=true, defaultValue="zip")
	private String archiveType;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		if(getOutPutPatch() != null){
			File patchDirOutPut = new File(getOutPutPatch().getOutPutPatchDir());
			
			if(patchDirOutPut.exists() && patchDirOutPut.isDirectory() && patchDirOutPut.canRead()){
				File[] patchesFolders = patchDirOutPut.listFiles();
				for (File dir : patchesFolders) {
					ZipUtils.zipDirectory(dir, null, null);
				}
			}
		}
	}

}
