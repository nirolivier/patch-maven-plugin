/*
 * 
 */
package com.zeus.patch.maven.plugin.model;

import java.io.Serializable;
import java.util.List;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author lambo
 *
 */
public class OutPutPatch implements Serializable {

	private static final long serialVersionUID = 6140963283806492871L;

	@Parameter(required=true)
	private String outPutPatchDir;
	
	private  List<String> patterns;
	
	/**
	 * 
	 */
	public OutPutPatch() {
	}
	/**
	 * @return the outPutPatchDir
	 */
	public String getOutPutPatchDir() {
		return outPutPatchDir;
	}
	/**
	 * @param outPutPatchDir the outPutPatchDir to set
	 */
	public void setOutPutPatchDir(String outPutPatchDir) {
		this.outPutPatchDir = outPutPatchDir;
	}
	/**
	 * @return the patterns
	 */
	public List<String> getPatterns() {
		return patterns;
	}
	/**
	 * @param patterns the patterns to set
	 */
	public void setPatterns(List<String> patterns) {
		this.patterns = patterns;
	}
	
	

}
