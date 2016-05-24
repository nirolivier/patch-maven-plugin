/*
 * 
 */
package com.zeus.patch.maven.plugin.model;

import java.io.Serializable;

import com.zeus.patch.maven.plugin.csv.CsvColumnIndex;

/**
 * @author lambo
 *
 */
public class CsvRecord implements Serializable {

	private static final long serialVersionUID = -3970765474996357411L;

	private String id;
	private String artifactId;
	private String version;
	private String classPackage;
	private String className;

	/**
	 * 
	 */
	public CsvRecord() {
		super();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id, CsvColumnIndex zero) {
		if (zero != null && zero == CsvColumnIndex.ZERO) {
			this.id = id;
		}
	}

	/**
	 * @return the artifactId
	 */
	public String getArtifactId() {
		return artifactId;
	}

	/**
	 * @param artifactId
	 *            the artifactId to set
	 */
	public void setArtifactId(String artifactId, CsvColumnIndex one) {
		if (one != null && one == CsvColumnIndex.ONE) {
			this.artifactId = artifactId;
		}
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version, CsvColumnIndex two) {
		if (two != null && two == CsvColumnIndex.TWO) {
			this.version = version;
		}
	}

	/**
	 * @return the classPackage
	 */
	public String getClassPackage() {
		return classPackage;
	}

	/**
	 * @param classPackage
	 *            the classPackage to set
	 */
	public void setClassPackage(String classPackage, CsvColumnIndex three) {
		if (three != null && three == CsvColumnIndex.THREE) {
			this.classPackage = classPackage;
		}
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className, CsvColumnIndex four) {
		if (four != null && four == CsvColumnIndex.FOUR) {
			this.className = className;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artifactId == null) ? 0 : artifactId.hashCode());
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((classPackage == null) ? 0 : classPackage.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CsvRecord other = (CsvRecord) obj;
		if (artifactId == null) {
			if (other.artifactId != null)
				return false;
		} else if (!artifactId.equals(other.artifactId))
			return false;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (classPackage == null) {
			if (other.classPackage != null)
				return false;
		} else if (!classPackage.equals(other.classPackage))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CsvRecord [id=");
		builder.append(id);
		builder.append(", artifactId=");
		builder.append(artifactId);
		builder.append(", version=");
		builder.append(version);
		builder.append(", classPackage=");
		builder.append(classPackage);
		builder.append(", className=");
		builder.append(className);
		builder.append("]");
		return builder.toString();
	}

}
