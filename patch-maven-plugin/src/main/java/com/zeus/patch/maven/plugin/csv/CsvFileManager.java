/*
 * 
 */
package com.zeus.patch.maven.plugin.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author lambo
 *
 */
public final class CsvFileManager {

	/**
	 * 
	 */
	private CsvFileManager() {
	}
	
	public static List<String[]> readCsv(File csvFile, char delimiter) {
		// read csv file
		FileReader reader = null;
		List<String[]> csvLineArrayList = new ArrayList<String[]>();
		CSVReader csvReader = null;
		try {
			reader = new FileReader(csvFile);
			csvReader = new CSVReader(reader, delimiter);
			csvLineArrayList = csvReader.readAll();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			closeCsvReader(csvReader);
			closeFileReader(reader);
		}
		return csvLineArrayList;
	}

	public static List<String[]> readCsv(String csvFilePath,char delimiter) {
		return readCsv(new File(csvFilePath),delimiter);
	}

	/**
	 * @param reader
	 */
	private static void closeFileReader(FileReader reader) {
		if(reader != null){
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @param csvReader
	 */
	private static void closeCsvReader(CSVReader csvReader) {
		if(csvReader != null){
			try {
				csvReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
