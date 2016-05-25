/*
 * 
 */
package com.zeus.patch.maven.plugin.mojo;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.codehaus.plexus.util.FileUtils;

/**
 * @author lambo
 *
 */
@Mojo(name = "clean", defaultPhase = LifecyclePhase.CLEAN,threadSafe = true)
public class PatchCleanMojo extends AbstractPatchMojo {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		File outPatchDir = new File(getOutPutPatch().getOutPutPatchDir());
		if(outPatchDir.exists() && outPatchDir.canRead()){
			try {
				FileUtils.forceDelete(outPatchDir);
			} catch (IOException e) {
				throw new MojoFailureException(e.getMessage());
			}
		}
	}

}
