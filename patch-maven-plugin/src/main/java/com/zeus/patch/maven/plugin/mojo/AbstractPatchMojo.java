/*
 * 
 */
package com.zeus.patch.maven.plugin.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.zeus.patch.maven.plugin.model.OutPutPatch;

/**
 * @author lambo
 *
 */
public abstract class AbstractPatchMojo extends AbstractMojo{

	@Component(role = MavenProject.class)
	private MavenProject project;
	
	@Parameter(required=true)
	private OutPutPatch outPutPatch;
	
	/**
	 * @return the project
	 */
	public MavenProject getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(MavenProject project) {
		this.project = project;
	}

	/**
	 * @return the outPutPatch
	 */
	public OutPutPatch getOutPutPatch() {
		return outPutPatch;
	}

	/**
	 * @param outPutPatch the outPutPatch to set
	 */
	public void setOutPutPatch(OutPutPatch outPutPatch) {
		this.outPutPatch = outPutPatch;
	}
	
}
