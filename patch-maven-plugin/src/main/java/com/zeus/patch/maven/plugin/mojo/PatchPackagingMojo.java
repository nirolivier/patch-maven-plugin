/**
 * 
 */
package com.zeus.patch.maven.plugin.mojo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.zeus.patch.maven.plugin.csv.CsvColumnIndex;
import com.zeus.patch.maven.plugin.csv.CsvFileManager;
import com.zeus.patch.maven.plugin.model.ClassMemo;
import com.zeus.patch.maven.plugin.model.CsvRecord;
import com.zeus.patch.maven.plugin.utils.ClassUtils;

/**
 * @author lambo
 *
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.PACKAGE, inheritByDefault = true)
public class PatchPackagingMojo extends AbstractMojo {

	@Parameter(name = "csvFile", required = true)
	protected File csvFile;

	@Parameter(name = "csvDelimiter", defaultValue = ",", required = true)
	protected char csvDelimiter;

	@Parameter(name = "zipNamePattern", property = "zipNamePattern")
	protected String zipNamePattern;

	@Parameter(name = "outputPatchDir", property = "outputPatchDir")
	protected String outputPatchDir;

	@Parameter(name = "sendMail", property = "sendMail", defaultValue = "true")
	protected boolean sendMail;

	@Component(role = MavenProject.class)
	protected MavenProject project;

	protected final List<CsvRecord> csvRecords = new ArrayList<CsvRecord>();
	protected final ClassMemo classMemo = new ClassMemo();
	/**
	 * 
	 */
	public PatchPackagingMojo() {
		super();
		// read csv file
		List<String[]> source = CsvFileManager.readCsv(csvFile, csvDelimiter);
		for (String[] items : source) {
			csvRecords.add(createCsvRecord(items));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute() throws MojoExecutionException, MojoFailureException {
		// Make sure the treatment is for the current.
		for (CsvRecord csvRecord : csvRecords) {
			if(csvRecord.getArtifactId().equals(project.getArtifactId())){
				String outPutDir = project.getBuild().getOutputDirectory();
				outPutDir += "/" + ClassUtils.convertToPath(csvRecord.getClassPackage());
				
				// search all class and its subclass
				classMemo.saveAll(csvRecord.getId(), ClassUtils.find(new File(outPutDir), csvRecord.getClassName()));
			}
		}
		
		for (Entry<String, List<File>> entries : classMemo.getMemo().entrySet()) {
			System.out.println("Key :" + entries.getKey());
			System.out.println("Values :");
			for (File file : entries.getValue()) {
				System.out.println(file.getPath());
			}
		}
		// update classPackage className version

		// at the end of execution: copy file + zip

		// generate release note

		// if active, send email
	}

	/**
	 * @param items
	 */
	private CsvRecord createCsvRecord(String[] items) {
		CsvRecord csvRecord = null;
		if (items != null) {
			csvRecord = new CsvRecord();
			for (int i = 0; i < items.length; i++) {
				csvRecord.setId(items[i], CsvColumnIndex.fromValue(i));
				csvRecord.setArtifactId(items[i], CsvColumnIndex.fromValue(i));
				csvRecord.setVersion(items[i], CsvColumnIndex.fromValue(i));
				csvRecord.setClassPackage(items[i], CsvColumnIndex.fromValue(i));
				csvRecord.setClassName(items[i], CsvColumnIndex.fromValue(i));
			}
		}
		return csvRecord;
	}

}
