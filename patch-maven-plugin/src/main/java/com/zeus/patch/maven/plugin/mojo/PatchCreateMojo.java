/**
 * 
 */
package com.zeus.patch.maven.plugin.mojo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.FileUtils;

import com.zeus.patch.maven.plugin.csv.CsvColumnIndex;
import com.zeus.patch.maven.plugin.csv.CsvFileManager;
import com.zeus.patch.maven.plugin.model.CsvRecord;
import com.zeus.patch.maven.plugin.utils.ClassUtils;
import com.zeus.patch.maven.plugin.utils.FileSeparator;
import com.zeus.patch.maven.plugin.utils.FileUtil;

/**
 * @author lambo
 *
 */
@Mojo(name = "create", defaultPhase = LifecyclePhase.COMPILE, threadSafe = true)
public class PatchCreateMojo extends AbstractPatchMojo {

	@Parameter(name = "csvFile", required = true)
	private File csvFile;

	@Parameter(name = "csvDelimiter", defaultValue = ",", required = true)
	private char csvDelimiter;

	@Parameter(name = "sendMail", property = "sendMail", defaultValue = "true")
	private boolean sendMail;

	/**
	 * {@inheritDoc}
	 */
	public void execute() throws MojoExecutionException, MojoFailureException {
		// read csv file
		List<String[]> source = CsvFileManager.readCsv(csvFile, csvDelimiter);

		for (String[] items : source) {
			CsvRecord csvRecord = createCsvRecord(items);
			String outPutDir = getProject().getBuild().getOutputDirectory();
			String classPackagePath = ClassUtils.convertToPath(csvRecord.getClassPackage());
			outPutDir += FileSeparator.SLASH + classPackagePath;

			// cat the patch dir pattern
			StringBuilder patchDirNamePattern = new StringBuilder();
			if (getOutPutPatch() != null && getOutPutPatch().getPatterns() != null) {
				for (String pattern : getOutPutPatch().getPatterns()) {
					patchDirNamePattern.append(pattern).append(FileSeparator.HYPHEN);
				}
			}
			patchDirNamePattern.append(csvRecord.getId()).append(FileSeparator.HYPHEN)
					.append(getProject().getVersion());

			// search all class and its subclass
			List<File> classes = FileUtil.findLike(new File(outPutDir), csvRecord.getClassName());
			for (File clazz : classes) {
				StringBuilder outPutPatchDirTemp = new StringBuilder(getOutPutPatch().getOutPutPatchDir());
				outPutPatchDirTemp.append(FileSeparator.SLASH).append(patchDirNamePattern.toString())
						.append(FileSeparator.SLASH).append(getProject().getArtifactId()).append(FileSeparator.SLASH)
						.append(classPackagePath);
				try {
					FileUtils.copyFileToDirectory(clazz, new File(outPutPatchDirTemp.toString()));
				} catch (IOException e) {
					throw new MojoFailureException(e.getMessage());
				}
			}
		}
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
