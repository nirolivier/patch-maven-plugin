/*
 * 
 */
package com.zeus.patch.maven.plugin.csv;

/**
 * @author lambo
 *
 */
public enum CsvColumnIndex {

	ZERO(0),
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4);
	
	private final int index;
	
	/**
	 * 
	 */
	private CsvColumnIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	
	public static CsvColumnIndex fromValue(int index){
		for (CsvColumnIndex columnIndex : values()) {
			if(columnIndex.index == index){
				return columnIndex;
			}
		}
		throw new IllegalArgumentException("The argument value is not supported. Chose value less than 4 including the value 0.");
	}

}
