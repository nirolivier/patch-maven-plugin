/*
 * 
 */
package com.zeus.patch.maven.plugin.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lambo
 *
 */
public class ClassMemo implements Serializable{

	private static final long serialVersionUID = 6395542029466346819L;
	private final Map<String, List<File>> memo = new ConcurrentHashMap<String, List<File>>();

	/**
	 * 
	 */
	public ClassMemo() {
	}

	/**
	 * @return the memo
	 */
	public Map<String, List<File>> getMemo() {
		return memo;
	}
	
	public void save(String key,File newFile){
		if(key != null && key !=""){
			if(memo.containsKey(key)){
				List<File> files = memo.get(key);
				if(files == null){
					files = new ArrayList<File>();
				} 
				
				if(newFile != null){
					files.add(newFile);
				}
				
			}else{
				List<File> files = new ArrayList<File>();
				if(newFile != null){
					files.add(newFile);
					memo.put(key, files);
				}
			}
		}
	}
	
	public void saveAll(String key,List<File> newFiles){
		for (File newFile : newFiles) {
			save(key, newFile);
		}
	}

	public List<File> get(String key){
		if(key != null && key !="" && memo.containsKey(key)){
			return memo.get(key);
		}
		return new ArrayList<File>();
	}
	
	public List<File> remove(String key){
		if(key != null && key !="" && memo.containsKey(key)){
			return memo.remove(key);
		}
		return new ArrayList<File>();
	}
	
	public List<File> removeInList(String key,File file){
		List<File> removed = new ArrayList<File>();
		if(key != null && key !="" && memo.containsKey(key)){
			List<File> files = get(key);
			for (int i = 0; i < files.size(); i++) {
				if(files.get(i).equals(file)){
					removed.add(files.remove(i));
				}
			}
		}
		return removed;
	}
	
	public boolean removeInSubList(String key,List<File> subFile){
		if(key != null && key !="" && memo.containsKey(key)){
			List<File> files = get(key);
			return files.removeAll(subFile);
		}
		return false;
	}
}
