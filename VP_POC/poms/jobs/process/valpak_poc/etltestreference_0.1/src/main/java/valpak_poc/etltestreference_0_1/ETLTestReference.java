
package valpak_poc.etltestreference_0_1;

import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.DataQuality;
import routines.Relational;
import routines.Mathematical;
import routines.DataQualityDependencies;
import routines.SQLike;
import routines.Numeric;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.MDM;
import routines.StringHandling;
import routines.DQTechnical;
import routines.TalendDate;
import routines.DataMasking;
import routines.DqStringHandling;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: ETLTestReference Purpose: <br>
 * Description: <br>
 * 
 * @author alex.hicks@qlik.com
 * @version 8.0.1.20241121_1314-patch
 * @status
 */
public class ETLTestReference implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "ETLTestReference.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(ETLTestReference.class);

	protected static void logIgnoredError(String message, Throwable cause) {
		log.error(message, cause);

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	public static String taskExecutionId = null;

	public static String jobExecutionId = java.util.UUID.randomUUID().toString();;

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	protected java.util.Map<String, String> defaultProperties = new java.util.HashMap<String, String>();
	protected java.util.Map<String, String> additionalProperties = new java.util.HashMap<String, String>();

	public java.util.Map<String, String> getDefaultProperties() {
		return this.defaultProperties;
	}

	public java.util.Map<String, String> getAdditionalProperties() {
		return this.additionalProperties;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "ETLTestReference";
	private final String projectName = "VALPAK_POC";
	public Integer errorCode = null;
	private String currentComponent = "";
	public static boolean isStandaloneMS = Boolean.valueOf("false");

	private void s(final String component) {
		try {
			org.talend.metrics.DataReadTracker.setCurrentComponent(jobName, component);
		} catch (Exception | NoClassDefFoundError e) {
			// ignore
		}
	}

	private void mdc(final String subJobName, final String subJobPidPrefix) {
		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", subJobName);
		org.slf4j.MDC.put("_subJobPid", subJobPidPrefix + subJobPidCounter.getAndIncrement());
	}

	private void sh(final String componentId) {
		ok_Hash.put(componentId, false);
		start_Hash.put(componentId, System.currentTimeMillis());
	}

	{
		s("none");
	}

	private String cLabel = null;

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private final JobStructureCatcherUtils talendJobLog = new JobStructureCatcherUtils(jobName,
			"_xk2dkK2hEe-5pIHvCIRXWQ", "0.1");
	private org.talend.job.audit.JobAuditLogger auditLogger_talendJobLog = null;

	private RunStat runStat = new RunStat(talendJobLog, System.getProperty("audit.interval"));

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;

		private String currentComponent = null;
		private String cLabel = null;

		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		private TalendException(Exception e, String errorComponent, String errorComponentLabel,
				final java.util.Map<String, Object> globalMap) {
			this(e, errorComponent, globalMap);
			this.cLabel = errorComponentLabel;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					ETLTestReference.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(ETLTestReference.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
						if (enableLogStash) {
							talendJobLog.addJobExceptionMessage(currentComponent, cLabel, null, e);
							talendJobLogProcess(globalMap);
						}
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendJobLog_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendJobLog_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_ETLTestReference = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_ETLTestReference = new byte[0];

		public String productCode;

		public String getProductCode() {
			return this.productCode;
		}

		public Boolean productCodeIsNullable() {
			return true;
		}

		public Boolean productCodeIsKey() {
			return false;
		}

		public Integer productCodeLength() {
			return null;
		}

		public Integer productCodePrecision() {
			return null;
		}

		public String productCodeDefault() {

			return null;

		}

		public String productCodeComment() {

			return "";

		}

		public String productCodePattern() {

			return "";

		}

		public String productCodeOriginalDbColumnName() {

			return "productCode";

		}

		public String productName;

		public String getProductName() {
			return this.productName;
		}

		public Boolean productNameIsNullable() {
			return true;
		}

		public Boolean productNameIsKey() {
			return false;
		}

		public Integer productNameLength() {
			return null;
		}

		public Integer productNamePrecision() {
			return null;
		}

		public String productNameDefault() {

			return null;

		}

		public String productNameComment() {

			return "";

		}

		public String productNamePattern() {

			return "";

		}

		public String productNameOriginalDbColumnName() {

			return "productName";

		}

		public String departmentCode;

		public String getDepartmentCode() {
			return this.departmentCode;
		}

		public Boolean departmentCodeIsNullable() {
			return true;
		}

		public Boolean departmentCodeIsKey() {
			return false;
		}

		public Integer departmentCodeLength() {
			return null;
		}

		public Integer departmentCodePrecision() {
			return null;
		}

		public String departmentCodeDefault() {

			return null;

		}

		public String departmentCodeComment() {

			return "";

		}

		public String departmentCodePattern() {

			return "";

		}

		public String departmentCodeOriginalDbColumnName() {

			return "departmentCode";

		}

		public String department;

		public String getDepartment() {
			return this.department;
		}

		public Boolean departmentIsNullable() {
			return true;
		}

		public Boolean departmentIsKey() {
			return false;
		}

		public Integer departmentLength() {
			return null;
		}

		public Integer departmentPrecision() {
			return null;
		}

		public String departmentDefault() {

			return null;

		}

		public String departmentComment() {

			return "";

		}

		public String departmentPattern() {

			return "";

		}

		public String departmentOriginalDbColumnName() {

			return "department";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_ETLTestReference.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_ETLTestReference.length == 0) {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_ETLTestReference, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_ETLTestReference, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_ETLTestReference.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_ETLTestReference.length == 0) {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_ETLTestReference, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_ETLTestReference, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_ETLTestReference) {

				try {

					int length = 0;

					this.productCode = readString(dis);

					this.productName = readString(dis);

					this.departmentCode = readString(dis);

					this.department = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_ETLTestReference) {

				try {

					int length = 0;

					this.productCode = readString(dis);

					this.productName = readString(dis);

					this.departmentCode = readString(dis);

					this.department = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.productCode, dos);

				// String

				writeString(this.productName, dos);

				// String

				writeString(this.departmentCode, dos);

				// String

				writeString(this.department, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.productCode, dos);

				// String

				writeString(this.productName, dos);

				// String

				writeString(this.departmentCode, dos);

				// String

				writeString(this.department, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("productCode=" + productCode);
			sb.append(",productName=" + productName);
			sb.append(",departmentCode=" + departmentCode);
			sb.append(",department=" + department);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (productCode == null) {
				sb.append("<null>");
			} else {
				sb.append(productCode);
			}

			sb.append("|");

			if (productName == null) {
				sb.append("<null>");
			} else {
				sb.append(productName);
			}

			sb.append("|");

			if (departmentCode == null) {
				sb.append("<null>");
			} else {
				sb.append(departmentCode);
			}

			sb.append("|");

			if (department == null) {
				sb.append("<null>");
			} else {
				sb.append(department);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class departmentNamesStruct implements routines.system.IPersistableRow<departmentNamesStruct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_ETLTestReference = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_ETLTestReference = new byte[0];

		public String productCode;

		public String getProductCode() {
			return this.productCode;
		}

		public Boolean productCodeIsNullable() {
			return true;
		}

		public Boolean productCodeIsKey() {
			return false;
		}

		public Integer productCodeLength() {
			return null;
		}

		public Integer productCodePrecision() {
			return null;
		}

		public String productCodeDefault() {

			return null;

		}

		public String productCodeComment() {

			return "";

		}

		public String productCodePattern() {

			return "";

		}

		public String productCodeOriginalDbColumnName() {

			return "productCode";

		}

		public String productName;

		public String getProductName() {
			return this.productName;
		}

		public Boolean productNameIsNullable() {
			return true;
		}

		public Boolean productNameIsKey() {
			return false;
		}

		public Integer productNameLength() {
			return null;
		}

		public Integer productNamePrecision() {
			return null;
		}

		public String productNameDefault() {

			return null;

		}

		public String productNameComment() {

			return "";

		}

		public String productNamePattern() {

			return "";

		}

		public String productNameOriginalDbColumnName() {

			return "productName";

		}

		public String departmentCode;

		public String getDepartmentCode() {
			return this.departmentCode;
		}

		public Boolean departmentCodeIsNullable() {
			return true;
		}

		public Boolean departmentCodeIsKey() {
			return false;
		}

		public Integer departmentCodeLength() {
			return null;
		}

		public Integer departmentCodePrecision() {
			return null;
		}

		public String departmentCodeDefault() {

			return null;

		}

		public String departmentCodeComment() {

			return "";

		}

		public String departmentCodePattern() {

			return "";

		}

		public String departmentCodeOriginalDbColumnName() {

			return "departmentCode";

		}

		public String department;

		public String getDepartment() {
			return this.department;
		}

		public Boolean departmentIsNullable() {
			return true;
		}

		public Boolean departmentIsKey() {
			return false;
		}

		public Integer departmentLength() {
			return null;
		}

		public Integer departmentPrecision() {
			return null;
		}

		public String departmentDefault() {

			return null;

		}

		public String departmentComment() {

			return "";

		}

		public String departmentPattern() {

			return "";

		}

		public String departmentOriginalDbColumnName() {

			return "department";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_ETLTestReference.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_ETLTestReference.length == 0) {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_ETLTestReference, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_ETLTestReference, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_ETLTestReference.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_ETLTestReference.length == 0) {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_ETLTestReference, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_ETLTestReference, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_ETLTestReference) {

				try {

					int length = 0;

					this.productCode = readString(dis);

					this.productName = readString(dis);

					this.departmentCode = readString(dis);

					this.department = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_ETLTestReference) {

				try {

					int length = 0;

					this.productCode = readString(dis);

					this.productName = readString(dis);

					this.departmentCode = readString(dis);

					this.department = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.productCode, dos);

				// String

				writeString(this.productName, dos);

				// String

				writeString(this.departmentCode, dos);

				// String

				writeString(this.department, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.productCode, dos);

				// String

				writeString(this.productName, dos);

				// String

				writeString(this.departmentCode, dos);

				// String

				writeString(this.department, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("productCode=" + productCode);
			sb.append(",productName=" + productName);
			sb.append(",departmentCode=" + departmentCode);
			sb.append(",department=" + department);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (productCode == null) {
				sb.append("<null>");
			} else {
				sb.append(productCode);
			}

			sb.append("|");

			if (productName == null) {
				sb.append("<null>");
			} else {
				sb.append(productName);
			}

			sb.append("|");

			if (departmentCode == null) {
				sb.append("<null>");
			} else {
				sb.append(departmentCode);
			}

			sb.append("|");

			if (department == null) {
				sb.append("<null>");
			} else {
				sb.append(department);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(departmentNamesStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_ETLTestReference = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_ETLTestReference = new byte[0];

		public String productCode;

		public String getProductCode() {
			return this.productCode;
		}

		public Boolean productCodeIsNullable() {
			return true;
		}

		public Boolean productCodeIsKey() {
			return false;
		}

		public Integer productCodeLength() {
			return null;
		}

		public Integer productCodePrecision() {
			return null;
		}

		public String productCodeDefault() {

			return null;

		}

		public String productCodeComment() {

			return "";

		}

		public String productCodePattern() {

			return "";

		}

		public String productCodeOriginalDbColumnName() {

			return "productCode";

		}

		public String productName;

		public String getProductName() {
			return this.productName;
		}

		public Boolean productNameIsNullable() {
			return true;
		}

		public Boolean productNameIsKey() {
			return false;
		}

		public Integer productNameLength() {
			return null;
		}

		public Integer productNamePrecision() {
			return null;
		}

		public String productNameDefault() {

			return null;

		}

		public String productNameComment() {

			return "";

		}

		public String productNamePattern() {

			return "";

		}

		public String productNameOriginalDbColumnName() {

			return "productName";

		}

		public String departmentCode;

		public String getDepartmentCode() {
			return this.departmentCode;
		}

		public Boolean departmentCodeIsNullable() {
			return true;
		}

		public Boolean departmentCodeIsKey() {
			return false;
		}

		public Integer departmentCodeLength() {
			return null;
		}

		public Integer departmentCodePrecision() {
			return null;
		}

		public String departmentCodeDefault() {

			return null;

		}

		public String departmentCodeComment() {

			return "";

		}

		public String departmentCodePattern() {

			return "";

		}

		public String departmentCodeOriginalDbColumnName() {

			return "departmentCode";

		}

		public String department;

		public String getDepartment() {
			return this.department;
		}

		public Boolean departmentIsNullable() {
			return true;
		}

		public Boolean departmentIsKey() {
			return false;
		}

		public Integer departmentLength() {
			return null;
		}

		public Integer departmentPrecision() {
			return null;
		}

		public String departmentDefault() {

			return null;

		}

		public String departmentComment() {

			return "";

		}

		public String departmentPattern() {

			return "";

		}

		public String departmentOriginalDbColumnName() {

			return "department";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_ETLTestReference.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_ETLTestReference.length == 0) {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_ETLTestReference, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_ETLTestReference, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_ETLTestReference.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_ETLTestReference.length == 0) {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_ETLTestReference, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_ETLTestReference, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_ETLTestReference) {

				try {

					int length = 0;

					this.productCode = readString(dis);

					this.productName = readString(dis);

					this.departmentCode = readString(dis);

					this.department = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_ETLTestReference) {

				try {

					int length = 0;

					this.productCode = readString(dis);

					this.productName = readString(dis);

					this.departmentCode = readString(dis);

					this.department = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.productCode, dos);

				// String

				writeString(this.productName, dos);

				// String

				writeString(this.departmentCode, dos);

				// String

				writeString(this.department, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.productCode, dos);

				// String

				writeString(this.productName, dos);

				// String

				writeString(this.departmentCode, dos);

				// String

				writeString(this.department, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("productCode=" + productCode);
			sb.append(",productName=" + productName);
			sb.append(",departmentCode=" + departmentCode);
			sb.append(",department=" + department);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (productCode == null) {
				sb.append("<null>");
			} else {
				sb.append(productCode);
			}

			sb.append("|");

			if (productName == null) {
				sb.append("<null>");
			} else {
				sb.append(productName);
			}

			sb.append("|");

			if (departmentCode == null) {
				sb.append("<null>");
			} else {
				sb.append(departmentCode);
			}

			sb.append("|");

			if (department == null) {
				sb.append("<null>");
			} else {
				sb.append(department);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tFileInputExcel_1Struct
			implements routines.system.IPersistableRow<after_tFileInputExcel_1Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_ETLTestReference = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_ETLTestReference = new byte[0];

		public String productCode;

		public String getProductCode() {
			return this.productCode;
		}

		public Boolean productCodeIsNullable() {
			return true;
		}

		public Boolean productCodeIsKey() {
			return false;
		}

		public Integer productCodeLength() {
			return null;
		}

		public Integer productCodePrecision() {
			return null;
		}

		public String productCodeDefault() {

			return null;

		}

		public String productCodeComment() {

			return "";

		}

		public String productCodePattern() {

			return "";

		}

		public String productCodeOriginalDbColumnName() {

			return "productCode";

		}

		public String productName;

		public String getProductName() {
			return this.productName;
		}

		public Boolean productNameIsNullable() {
			return true;
		}

		public Boolean productNameIsKey() {
			return false;
		}

		public Integer productNameLength() {
			return null;
		}

		public Integer productNamePrecision() {
			return null;
		}

		public String productNameDefault() {

			return null;

		}

		public String productNameComment() {

			return "";

		}

		public String productNamePattern() {

			return "";

		}

		public String productNameOriginalDbColumnName() {

			return "productName";

		}

		public String departmentCode;

		public String getDepartmentCode() {
			return this.departmentCode;
		}

		public Boolean departmentCodeIsNullable() {
			return true;
		}

		public Boolean departmentCodeIsKey() {
			return false;
		}

		public Integer departmentCodeLength() {
			return null;
		}

		public Integer departmentCodePrecision() {
			return null;
		}

		public String departmentCodeDefault() {

			return null;

		}

		public String departmentCodeComment() {

			return "";

		}

		public String departmentCodePattern() {

			return "";

		}

		public String departmentCodeOriginalDbColumnName() {

			return "departmentCode";

		}

		public String department;

		public String getDepartment() {
			return this.department;
		}

		public Boolean departmentIsNullable() {
			return true;
		}

		public Boolean departmentIsKey() {
			return false;
		}

		public Integer departmentLength() {
			return null;
		}

		public Integer departmentPrecision() {
			return null;
		}

		public String departmentDefault() {

			return null;

		}

		public String departmentComment() {

			return "";

		}

		public String departmentPattern() {

			return "";

		}

		public String departmentOriginalDbColumnName() {

			return "department";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_ETLTestReference.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_ETLTestReference.length == 0) {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_ETLTestReference, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_ETLTestReference, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_ETLTestReference.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_ETLTestReference.length == 0) {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_ETLTestReference, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_ETLTestReference, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_ETLTestReference) {

				try {

					int length = 0;

					this.productCode = readString(dis);

					this.productName = readString(dis);

					this.departmentCode = readString(dis);

					this.department = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_ETLTestReference) {

				try {

					int length = 0;

					this.productCode = readString(dis);

					this.productName = readString(dis);

					this.departmentCode = readString(dis);

					this.department = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.productCode, dos);

				// String

				writeString(this.productName, dos);

				// String

				writeString(this.departmentCode, dos);

				// String

				writeString(this.department, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.productCode, dos);

				// String

				writeString(this.productName, dos);

				// String

				writeString(this.departmentCode, dos);

				// String

				writeString(this.department, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("productCode=" + productCode);
			sb.append(",productName=" + productName);
			sb.append(",departmentCode=" + departmentCode);
			sb.append(",department=" + department);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (productCode == null) {
				sb.append("<null>");
			} else {
				sb.append(productCode);
			}

			sb.append("|");

			if (productName == null) {
				sb.append("<null>");
			} else {
				sb.append(productName);
			}

			sb.append("|");

			if (departmentCode == null) {
				sb.append("<null>");
			} else {
				sb.append(departmentCode);
			}

			sb.append("|");

			if (department == null) {
				sb.append("<null>");
			} else {
				sb.append(department);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputExcel_1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputExcel_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tFileInputExcel_1", "Wa0DYA_");

		String iterateId = "";

		String currentComponent = "";
		s("none");
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tFileInputExcel_2Process(globalMap);

				row1Struct row1 = new row1Struct();
				departmentNamesStruct departmentNames = new departmentNamesStruct();
				departmentNamesStruct row3 = departmentNames;

				/**
				 * [tFileOutputExcel_1 begin ] start
				 */

				sh("tFileOutputExcel_1");

				s(currentComponent = "tFileOutputExcel_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row3");

				int tos_count_tFileOutputExcel_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileOutputExcel_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileOutputExcel_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileOutputExcel_1 = new StringBuilder();
							log4jParamters_tFileOutputExcel_1.append("Parameters:");
							log4jParamters_tFileOutputExcel_1.append("VERSION_2007" + " = " + "true");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("USESTREAM" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("FILENAME" + " = "
									+ "\"C:/Users/valpakuser/Desktop/Outputs/ETL-TestReference_output.xlsx\"");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("SHEETNAME" + " = " + "\"Sheet1\"");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("INCLUDEHEADER" + " = " + "true");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("APPEND_FILE" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("FIRST_CELL_Y_ABSOLUTE" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("FONT" + " = " + "");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("IS_ALL_AUTO_SZIE" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("AUTO_SZIE_SETTING" + " = " + "[{IS_AUTO_SIZE="
									+ ("false") + ", SCHEMA_COLUMN=" + ("productCode") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("productName") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("departmentCode") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("department") + "}]");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("PROTECT_FILE" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("CREATE" + " = " + "true");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("FLUSHONROW" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("TRUNCATE_EXCEEDING_CHARACTERS" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("DELETE_EMPTYFILE" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("USE_SHARED_STRINGS_TABLE" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileOutputExcel_1 - " + (log4jParamters_tFileOutputExcel_1));
						}
					}
					new BytesLimit65535_tFileOutputExcel_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileOutputExcel_1", "tFileOutputExcel_1", "tFileOutputExcel");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				int columnIndex_tFileOutputExcel_1 = 0;
				boolean headerIsInserted_tFileOutputExcel_1 = false;

				String fileName_tFileOutputExcel_1 = "C:/Users/valpakuser/Desktop/Outputs/ETL-TestReference_output.xlsx";
				int nb_line_tFileOutputExcel_1 = 0;
				org.talend.ExcelTool xlsxTool_tFileOutputExcel_1 = new org.talend.ExcelTool();
				xlsxTool_tFileOutputExcel_1.setUseSharedStringTable(false);

				xlsxTool_tFileOutputExcel_1.setTruncateExceedingCharacters(false);
				xlsxTool_tFileOutputExcel_1.setSheet("Sheet1");
				xlsxTool_tFileOutputExcel_1.setAppend(false, false, false);
				xlsxTool_tFileOutputExcel_1.setRecalculateFormula(false);
				xlsxTool_tFileOutputExcel_1.setXY(false, 0, 0, false);

				java.util.concurrent.ConcurrentHashMap<java.lang.Object, java.lang.Object> chm_tFileOutputExcel_1 = (java.util.concurrent.ConcurrentHashMap<java.lang.Object, java.lang.Object>) globalMap
						.get("concurrentHashMap");
				java.lang.Object lockObj_tFileOutputExcel_1 = chm_tFileOutputExcel_1
						.computeIfAbsent("EXCEL_OUTPUT_LOCK_OBJ_tFileOutputExcel_1", k -> new Object());
				synchronized (lockObj_tFileOutputExcel_1) {

					xlsxTool_tFileOutputExcel_1.prepareXlsxFile(fileName_tFileOutputExcel_1);

				}

				xlsxTool_tFileOutputExcel_1.setFont("");

				if (xlsxTool_tFileOutputExcel_1.getStartRow() == 0) {

					xlsxTool_tFileOutputExcel_1.addRow();

					xlsxTool_tFileOutputExcel_1.addCellValue("productCode");

					xlsxTool_tFileOutputExcel_1.addCellValue("productName");

					xlsxTool_tFileOutputExcel_1.addCellValue("departmentCode");

					xlsxTool_tFileOutputExcel_1.addCellValue("department");

					nb_line_tFileOutputExcel_1++;
					headerIsInserted_tFileOutputExcel_1 = true;

				}

				/**
				 * [tFileOutputExcel_1 begin ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				sh("tLogRow_1");

				s(currentComponent = "tLogRow_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "departmentNames");

				int tos_count_tLogRow_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tLogRow_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tLogRow_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tLogRow_1 = new StringBuilder();
							log4jParamters_tLogRow_1.append("Parameters:");
							log4jParamters_tLogRow_1.append("BASIC_MODE" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("TABLE_PRINT" + " = " + "true");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("VERTICAL" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
							log4jParamters_tLogRow_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tLogRow_1 - " + (log4jParamters_tLogRow_1));
						}
					}
					new BytesLimit65535_tLogRow_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tLogRow_1", "tLogRow_1", "tLogRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				///////////////////////

				class Util_tLogRow_1 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[4];

					public void addRow(String[] row) {

						for (int i = 0; i < 4; i++) {
							if (row[i] != null) {
								colLengths[i] = Math.max(colLengths[i], row[i].length());
							}
						}
						list.add(row);
					}

					public void setTableName(String name) {

						this.name = name;
					}

					public StringBuilder format() {

						StringBuilder sb = new StringBuilder();

						sb.append(print(des_top));

						int totals = 0;
						for (int i = 0; i < colLengths.length; i++) {
							totals = totals + colLengths[i];
						}

						// name
						sb.append("|");
						int k = 0;
						for (k = 0; k < (totals + 3 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 3 - name.length() - k; i++) {
							sb.append(' ');
						}
						sb.append("|\n");

						// head and rows
						sb.append(print(des_head));
						for (int i = 0; i < list.size(); i++) {

							String[] row = list.get(i);

							java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

							StringBuilder sbformat = new StringBuilder();
							sbformat.append("|%1$-");
							sbformat.append(colLengths[0]);
							sbformat.append("s");

							sbformat.append("|%2$-");
							sbformat.append(colLengths[1]);
							sbformat.append("s");

							sbformat.append("|%3$-");
							sbformat.append(colLengths[2]);
							sbformat.append("s");

							sbformat.append("|%4$-");
							sbformat.append(colLengths[3]);
							sbformat.append("s");

							sbformat.append("|\n");

							formatter.format(sbformat.toString(), (Object[]) row);

							sb.append(formatter.toString());
							if (i == 0)
								sb.append(print(des_head)); // print the head
						}

						// end
						sb.append(print(des_bottom));
						return sb;
					}

					private StringBuilder print(String[] fillChars) {
						StringBuilder sb = new StringBuilder();
						// first column
						sb.append(fillChars[0]);
						for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[2] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[3] - fillChars[1].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[1]);
						sb.append("\n");
						return sb;
					}

					public boolean isTableEmpty() {
						if (list.size() > 1)
							return false;
						return true;
					}
				}
				Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
				util_tLogRow_1.setTableName("tLogRow_1");
				util_tLogRow_1.addRow(new String[] { "productCode", "productName", "departmentCode", "department", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				sh("tMap_1");

				s(currentComponent = "tMap_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row1");

				int tos_count_tMap_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tMap_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tMap_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tMap_1 = new StringBuilder();
							log4jParamters_tMap_1.append("Parameters:");
							log4jParamters_tMap_1.append("LINK_STYLE" + " = " + "AUTO");
							log4jParamters_tMap_1.append(" | ");
							log4jParamters_tMap_1.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
							log4jParamters_tMap_1.append(" | ");
							log4jParamters_tMap_1.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
							log4jParamters_tMap_1.append(" | ");
							log4jParamters_tMap_1.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
							log4jParamters_tMap_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tMap_1 - " + (log4jParamters_tMap_1));
						}
					}
					new BytesLimit65535_tMap_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tMap_1", "tMap_1", "tMap");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

// ###############################
// # Lookup's keys initialization
				int count_row1_tMap_1 = 0;

				int count_row2_tMap_1 = 0;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) globalMap
						.get("tHash_Lookup_row2"));

				row2Struct row2HashKey = new row2Struct();
				row2Struct row2Default = new row2Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_departmentNames_tMap_1 = 0;

				departmentNamesStruct departmentNames_tmp = new departmentNamesStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tFileInputExcel_1 begin ] start
				 */

				sh("tFileInputExcel_1");

				s(currentComponent = "tFileInputExcel_1");

				int tos_count_tFileInputExcel_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputExcel_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputExcel_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputExcel_1 = new StringBuilder();
							log4jParamters_tFileInputExcel_1.append("Parameters:");
							log4jParamters_tFileInputExcel_1.append("VERSION_2007" + " = " + "true");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("FILENAME" + " = "
									+ "\"C:/Users/valpakuser/Desktop/ValPak PoC Data/ETL-TestReference.xlsx\"");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("PASSWORD" + " = "
									+ String.valueOf(
											"enc:routine.encryption.key.v1:7J0fOPY44C+ZwrdGTT9IHKjMyKyDPk7dzOS33w==")
											.substring(0, 4)
									+ "...");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("ALL_SHEETS" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("SHEETLIST" + " = " + "[{USE_REGEX=" + ("false")
									+ ", SHEETNAME=" + ("\"Source\"") + "}]");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("HEADER" + " = " + "1");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("LIMIT" + " = " + "");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("FIRST_COLUMN" + " = " + "1");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("LAST_COLUMN" + " = " + "");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("TRIMALL" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append(
									"TRIMSELECT" + " = " + "[{TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("productCode")
											+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("productName")
											+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("departmentCode")
											+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("department") + "}]");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("GENERATION_MODE" + " = " + "EVENT_MODE");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("INCLUDE_PHONETICRUNS" + " = " + "true");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("CONFIGURE_INFLATION_RATIO" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputExcel_1 - " + (log4jParamters_tFileInputExcel_1));
						}
					}
					new BytesLimit65535_tFileInputExcel_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileInputExcel_1", "tFileInputExcel_1", "tFileInputExcel");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				final String decryptedPassword_tFileInputExcel_1 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:2XJmolUR2zzCBPDNLXh3/rfDnPY+jI27nLEy5A==");
				String password_tFileInputExcel_1 = decryptedPassword_tFileInputExcel_1;
				if (password_tFileInputExcel_1.isEmpty()) {
					password_tFileInputExcel_1 = null;
				}
				Object source_tFileInputExcel_1 = "C:/Users/valpakuser/Desktop/ValPak PoC Data/ETL-TestReference.xlsx";
				com.talend.excel.xssf.event.ExcelReader excelReader_tFileInputExcel_1 = null;

				if (source_tFileInputExcel_1 instanceof java.io.InputStream
						|| source_tFileInputExcel_1 instanceof String) {
					excelReader_tFileInputExcel_1 = new com.talend.excel.xssf.event.ExcelReader();
					excelReader_tFileInputExcel_1.setIncludePhoneticRuns(true);
				} else {
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}

				try {
					excelReader_tFileInputExcel_1.addSheetName("Source", false);
					int start_column_tFileInputExcel_1 = 1 - 1;
					int end_column_tFileInputExcel_1 = -1;
					if (start_column_tFileInputExcel_1 >= 0) {// follow start column

						end_column_tFileInputExcel_1 = start_column_tFileInputExcel_1 + 4 - 1;

					} else if (end_column_tFileInputExcel_1 >= 0) {// follow end column
						start_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 - 4 + 1;
					}

					if (end_column_tFileInputExcel_1 < 0 || start_column_tFileInputExcel_1 < 0) {
						throw new RuntimeException("Error start column and end column.");
					}
					int actual_end_column_tFileInputExcel_1 = end_column_tFileInputExcel_1;

					int header_tFileInputExcel_1 = 1;
					int limit_tFileInputExcel_1 = -1;

					int nb_line_tFileInputExcel_1 = 0;

					// for the number format
					java.text.DecimalFormat df_tFileInputExcel_1 = new java.text.DecimalFormat(
							"#.####################################");
					char decimalChar_tFileInputExcel_1 = df_tFileInputExcel_1.getDecimalFormatSymbols()
							.getDecimalSeparator();

					if (source_tFileInputExcel_1 instanceof String) {
						excelReader_tFileInputExcel_1.parse((String) source_tFileInputExcel_1, "ISO-8859-15",
								password_tFileInputExcel_1);
					} else if (source_tFileInputExcel_1 instanceof java.io.InputStream) {
						excelReader_tFileInputExcel_1.parse((java.io.InputStream) source_tFileInputExcel_1,
								"ISO-8859-15", password_tFileInputExcel_1);
					}

					while ((header_tFileInputExcel_1--) > 0 && excelReader_tFileInputExcel_1.hasNext()) {// skip the
																											// header
						excelReader_tFileInputExcel_1.next();
					}

					log.debug("tFileInputExcel_1 - Retrieving records from the datasource.");

					while (excelReader_tFileInputExcel_1.hasNext()) {
						int emptyColumnCount_tFileInputExcel_1 = 0;

						if (limit_tFileInputExcel_1 != -1 && nb_line_tFileInputExcel_1 >= limit_tFileInputExcel_1) {
							excelReader_tFileInputExcel_1.stopRead();
							break;
						}

						java.util.List<String> row_tFileInputExcel_1 = excelReader_tFileInputExcel_1.next();
						row1 = null;
						int tempRowLength_tFileInputExcel_1 = 4;

						int columnIndex_tFileInputExcel_1 = 0;

						String[] temp_row_tFileInputExcel_1 = new String[tempRowLength_tFileInputExcel_1];

						for (int i_tFileInputExcel_1 = 0; i_tFileInputExcel_1 < tempRowLength_tFileInputExcel_1; i_tFileInputExcel_1++) {
							int current_tFileInputExcel_1 = i_tFileInputExcel_1 + start_column_tFileInputExcel_1;
							if (current_tFileInputExcel_1 <= actual_end_column_tFileInputExcel_1) {
								if (current_tFileInputExcel_1 < row_tFileInputExcel_1.size()) {
									String column_tFileInputExcel_1 = row_tFileInputExcel_1
											.get(current_tFileInputExcel_1);
									if (column_tFileInputExcel_1 != null) {
										temp_row_tFileInputExcel_1[i_tFileInputExcel_1] = column_tFileInputExcel_1;
									} else {
										temp_row_tFileInputExcel_1[i_tFileInputExcel_1] = "";
									}
								} else {
									temp_row_tFileInputExcel_1[i_tFileInputExcel_1] = "";
								}
							} else {
								temp_row_tFileInputExcel_1[i_tFileInputExcel_1] = "";
							}
						}

						boolean whetherReject_tFileInputExcel_1 = false;
						row1 = new row1Struct();
						int curColNum_tFileInputExcel_1 = -1;
						String curColName_tFileInputExcel_1 = "";

						try {
							columnIndex_tFileInputExcel_1 = 0;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "productCode";

								row1.productCode = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.productCode = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 1;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "productName";

								row1.productName = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.productName = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 2;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "departmentCode";

								row1.departmentCode = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.departmentCode = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 3;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "department";

								row1.department = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.department = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							nb_line_tFileInputExcel_1++;

							log.debug("tFileInputExcel_1 - Retrieving the record " + (nb_line_tFileInputExcel_1) + ".");

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputExcel_1 = true;
							System.err.println(e.getMessage());
							row1 = null;
						}

						/**
						 * [tFileInputExcel_1 begin ] stop
						 */

						/**
						 * [tFileInputExcel_1 main ] start
						 */

						s(currentComponent = "tFileInputExcel_1");

						tos_count_tFileInputExcel_1++;

						/**
						 * [tFileInputExcel_1 main ] stop
						 */

						/**
						 * [tFileInputExcel_1 process_data_begin ] start
						 */

						s(currentComponent = "tFileInputExcel_1");

						/**
						 * [tFileInputExcel_1 process_data_begin ] stop
						 */

// Start of branch "row1"
						if (row1 != null) {

							/**
							 * [tMap_1 main ] start
							 */

							s(currentComponent = "tMap_1");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row1", "tFileInputExcel_1", "tFileInputExcel_1", "tFileInputExcel", "tMap_1",
									"tMap_1", "tMap"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row1 - " + (row1 == null ? "" : row1.toLogString()));
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

							row2Struct row2 = null;

							// ###############################
							// # Input tables (lookups)

							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row2"
							///////////////////////////////////////////////

							boolean forceLooprow2 = false;

							row2Struct row2ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_1 = false;

								row2HashKey.departmentCode = row1.departmentCode;

								row2HashKey.hashCodeDirty = true;

								tHash_Lookup_row2.lookup(row2HashKey);

							} // G_TM_M_020

							if (tHash_Lookup_row2 != null && tHash_Lookup_row2.getCount(row2HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row2'
								// and it contains more one result from keys : row2.departmentCode = '" +
								// row2HashKey.departmentCode + "'");
							} // G 071

							row2Struct fromLookup_row2 = null;
							row2 = row2Default;

							if (tHash_Lookup_row2 != null && tHash_Lookup_row2.hasNext()) { // G 099

								fromLookup_row2 = tHash_Lookup_row2.next();

							} // G 099

							if (fromLookup_row2 != null) {
								row2 = fromLookup_row2;
							}

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
								// ###############################
								// # Output tables

								departmentNames = null;

// # Output table : 'departmentNames'
								count_departmentNames_tMap_1++;

								departmentNames_tmp.productCode = row1.productCode;
								departmentNames_tmp.productName = row1.productName;
								departmentNames_tmp.departmentCode = row1.departmentCode;
								departmentNames_tmp.department = row2.departmentName;
								departmentNames = departmentNames_tmp;
								log.debug("tMap_1 - Outputting the record " + count_departmentNames_tMap_1
										+ " of the output table 'departmentNames'.");

// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_1 = false;

							tos_count_tMap_1++;

							/**
							 * [tMap_1 main ] stop
							 */

							/**
							 * [tMap_1 process_data_begin ] start
							 */

							s(currentComponent = "tMap_1");

							/**
							 * [tMap_1 process_data_begin ] stop
							 */

// Start of branch "departmentNames"
							if (departmentNames != null) {

								/**
								 * [tLogRow_1 main ] start
								 */

								s(currentComponent = "tLogRow_1");

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "departmentNames", "tMap_1", "tMap_1", "tMap", "tLogRow_1", "tLogRow_1",
										"tLogRow"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("departmentNames - "
											+ (departmentNames == null ? "" : departmentNames.toLogString()));
								}

///////////////////////		

								String[] row_tLogRow_1 = new String[4];

								if (departmentNames.productCode != null) { //
									row_tLogRow_1[0] = String.valueOf(departmentNames.productCode);

								} //

								if (departmentNames.productName != null) { //
									row_tLogRow_1[1] = String.valueOf(departmentNames.productName);

								} //

								if (departmentNames.departmentCode != null) { //
									row_tLogRow_1[2] = String.valueOf(departmentNames.departmentCode);

								} //

								if (departmentNames.department != null) { //
									row_tLogRow_1[3] = String.valueOf(departmentNames.department);

								} //

								util_tLogRow_1.addRow(row_tLogRow_1);
								nb_line_tLogRow_1++;
								log.info("tLogRow_1 - Content of row " + nb_line_tLogRow_1 + ": "
										+ TalendString.unionString("|", row_tLogRow_1));
//////

//////                    

///////////////////////    			

								row3 = departmentNames;

								tos_count_tLogRow_1++;

								/**
								 * [tLogRow_1 main ] stop
								 */

								/**
								 * [tLogRow_1 process_data_begin ] start
								 */

								s(currentComponent = "tLogRow_1");

								/**
								 * [tLogRow_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputExcel_1 main ] start
								 */

								s(currentComponent = "tFileOutputExcel_1");

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "row3", "tLogRow_1", "tLogRow_1", "tLogRow", "tFileOutputExcel_1",
										"tFileOutputExcel_1", "tFileOutputExcel"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("row3 - " + (row3 == null ? "" : row3.toLogString()));
								}

								xlsxTool_tFileOutputExcel_1.addRow();

								if (row3.productCode != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row3.productCode));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row3.productName != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row3.productName));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row3.departmentCode != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row3.departmentCode));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row3.department != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row3.department));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								nb_line_tFileOutputExcel_1++;

								log.debug("tFileOutputExcel_1 - Writing the record " + nb_line_tFileOutputExcel_1
										+ " to the file.");

								tos_count_tFileOutputExcel_1++;

								/**
								 * [tFileOutputExcel_1 main ] stop
								 */

								/**
								 * [tFileOutputExcel_1 process_data_begin ] start
								 */

								s(currentComponent = "tFileOutputExcel_1");

								/**
								 * [tFileOutputExcel_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputExcel_1 process_data_end ] start
								 */

								s(currentComponent = "tFileOutputExcel_1");

								/**
								 * [tFileOutputExcel_1 process_data_end ] stop
								 */

								/**
								 * [tLogRow_1 process_data_end ] start
								 */

								s(currentComponent = "tLogRow_1");

								/**
								 * [tLogRow_1 process_data_end ] stop
								 */

							} // End of branch "departmentNames"

							/**
							 * [tMap_1 process_data_end ] start
							 */

							s(currentComponent = "tMap_1");

							/**
							 * [tMap_1 process_data_end ] stop
							 */

						} // End of branch "row1"

						/**
						 * [tFileInputExcel_1 process_data_end ] start
						 */

						s(currentComponent = "tFileInputExcel_1");

						/**
						 * [tFileInputExcel_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputExcel_1 end ] start
						 */

						s(currentComponent = "tFileInputExcel_1");

					}

					try {
						if (excelReader_tFileInputExcel_1 != null) {
							excelReader_tFileInputExcel_1.handleException();
						}
					} catch (java.lang.Exception e_tFileInputExcel_1) {
						globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e_tFileInputExcel_1.getMessage());
						if (!(e_tFileInputExcel_1
								.getCause() instanceof com.talend.excel.xssf.event.EnoughDataException)) {

							log.error("tFileInputExcel_1 - " + e_tFileInputExcel_1.getMessage());

							System.err.println(e_tFileInputExcel_1.getMessage());

						}
					}

					log.debug("tFileInputExcel_1 - Retrieved records count: " + nb_line_tFileInputExcel_1 + " .");

					globalMap.put("tFileInputExcel_1_NB_LINE", nb_line_tFileInputExcel_1);
				} finally {

				}

				if (log.isDebugEnabled())
					log.debug("tFileInputExcel_1 - " + ("Done."));

				ok_Hash.put("tFileInputExcel_1", true);
				end_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				s(currentComponent = "tMap_1");

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row2 != null) {
					tHash_Lookup_row2.endGet();
				}
				globalMap.remove("tHash_Lookup_row2");

// ###############################      
				log.debug("tMap_1 - Written records count in the table 'departmentNames': "
						+ count_departmentNames_tMap_1 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row1", 2, 0,
						"tFileInputExcel_1", "tFileInputExcel_1", "tFileInputExcel", "tMap_1", "tMap_1", "tMap",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tMap_1 - " + ("Done."));

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				s(currentComponent = "tLogRow_1");

//////

				java.io.PrintStream consoleOut_tLogRow_1 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
				}

				consoleOut_tLogRow_1.println(util_tLogRow_1.format().toString());
				consoleOut_tLogRow_1.flush();
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);
				if (log.isInfoEnabled())
					log.info("tLogRow_1 - " + ("Printed row count: ") + (nb_line_tLogRow_1) + ("."));

///////////////////////    			

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "departmentNames", 2, 0,
						"tMap_1", "tMap_1", "tMap", "tLogRow_1", "tLogRow_1", "tLogRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_1 - " + ("Done."));

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

				/**
				 * [tFileOutputExcel_1 end ] start
				 */

				s(currentComponent = "tFileOutputExcel_1");

				xlsxTool_tFileOutputExcel_1.writeExcel(fileName_tFileOutputExcel_1, true);

				if (headerIsInserted_tFileOutputExcel_1 && nb_line_tFileOutputExcel_1 > 0) {
					nb_line_tFileOutputExcel_1 = nb_line_tFileOutputExcel_1 - 1;
				}
				globalMap.put("tFileOutputExcel_1_NB_LINE", nb_line_tFileOutputExcel_1);

				log.debug("tFileOutputExcel_1 - Written records count: " + nb_line_tFileOutputExcel_1 + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row3", 2, 0,
						"tLogRow_1", "tLogRow_1", "tLogRow", "tFileOutputExcel_1", "tFileOutputExcel_1",
						"tFileOutputExcel", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFileOutputExcel_1 - " + ("Done."));

				ok_Hash.put("tFileOutputExcel_1", true);
				end_Hash.put("tFileOutputExcel_1", System.currentTimeMillis());

				/**
				 * [tFileOutputExcel_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row2");

			try {

				/**
				 * [tFileInputExcel_1 finally ] start
				 */

				s(currentComponent = "tFileInputExcel_1");

				/**
				 * [tFileInputExcel_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				s(currentComponent = "tMap_1");

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				s(currentComponent = "tLogRow_1");

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tFileOutputExcel_1 finally ] start
				 */

				s(currentComponent = "tFileOutputExcel_1");

				/**
				 * [tFileOutputExcel_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 1);
	}

	public static class row2Struct implements routines.system.IPersistableComparableLookupRow<row2Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_ETLTestReference = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_ETLTestReference = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String departmentCode;

		public String getDepartmentCode() {
			return this.departmentCode;
		}

		public Boolean departmentCodeIsNullable() {
			return true;
		}

		public Boolean departmentCodeIsKey() {
			return false;
		}

		public Integer departmentCodeLength() {
			return null;
		}

		public Integer departmentCodePrecision() {
			return null;
		}

		public String departmentCodeDefault() {

			return null;

		}

		public String departmentCodeComment() {

			return "";

		}

		public String departmentCodePattern() {

			return "";

		}

		public String departmentCodeOriginalDbColumnName() {

			return "departmentCode";

		}

		public String departmentName;

		public String getDepartmentName() {
			return this.departmentName;
		}

		public Boolean departmentNameIsNullable() {
			return true;
		}

		public Boolean departmentNameIsKey() {
			return false;
		}

		public Integer departmentNameLength() {
			return null;
		}

		public Integer departmentNamePrecision() {
			return null;
		}

		public String departmentNameDefault() {

			return null;

		}

		public String departmentNameComment() {

			return "";

		}

		public String departmentNamePattern() {

			return "";

		}

		public String departmentNameOriginalDbColumnName() {

			return "departmentName";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.departmentCode == null) ? 0 : this.departmentCode.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row2Struct other = (row2Struct) obj;

			if (this.departmentCode == null) {
				if (other.departmentCode != null)
					return false;

			} else if (!this.departmentCode.equals(other.departmentCode))

				return false;

			return true;
		}

		public void copyDataTo(row2Struct other) {

			other.departmentCode = this.departmentCode;
			other.departmentName = this.departmentName;

		}

		public void copyKeysDataTo(row2Struct other) {

			other.departmentCode = this.departmentCode;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_ETLTestReference.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_ETLTestReference.length == 0) {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_ETLTestReference, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_ETLTestReference, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_ETLTestReference.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_ETLTestReference.length == 0) {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_ETLTestReference = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_ETLTestReference, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_ETLTestReference, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_ETLTestReference) {

				try {

					int length = 0;

					this.departmentCode = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_ETLTestReference) {

				try {

					int length = 0;

					this.departmentCode = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.departmentCode, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.departmentCode, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.departmentName = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.departmentName = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.departmentName, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.departmentName, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("departmentCode=" + departmentCode);
			sb.append(",departmentName=" + departmentName);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (departmentCode == null) {
				sb.append("<null>");
			} else {
				sb.append(departmentCode);
			}

			sb.append("|");

			if (departmentName == null) {
				sb.append("<null>");
			} else {
				sb.append(departmentName);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.departmentCode, other.departmentCode);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputExcel_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tFileInputExcel_2", "FgCLZV_");

		String iterateId = "";

		String currentComponent = "";
		s("none");
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row2Struct row2 = new row2Struct();

				/**
				 * [tAdvancedHash_row2 begin ] start
				 */

				sh("tAdvancedHash_row2");

				s(currentComponent = "tAdvancedHash_row2");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row2");

				int tos_count_tAdvancedHash_row2 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_row2", "tAdvancedHash_row2", "tAdvancedHash");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				// connection name:row2
				// source node:tFileInputExcel_2 - inputs:(after_tFileInputExcel_1)
				// outputs:(row2,row2) | target node:tAdvancedHash_row2 - inputs:(row2)
				// outputs:()
				// linked node: tMap_1 - inputs:(row1,row2) outputs:(departmentNames)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row2 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row2Struct>getLookup(matchingModeEnum_row2);

				globalMap.put("tHash_Lookup_row2", tHash_Lookup_row2);

				/**
				 * [tAdvancedHash_row2 begin ] stop
				 */

				/**
				 * [tFileInputExcel_2 begin ] start
				 */

				sh("tFileInputExcel_2");

				s(currentComponent = "tFileInputExcel_2");

				int tos_count_tFileInputExcel_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputExcel_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputExcel_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputExcel_2 = new StringBuilder();
							log4jParamters_tFileInputExcel_2.append("Parameters:");
							log4jParamters_tFileInputExcel_2.append("VERSION_2007" + " = " + "true");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("FILENAME" + " = "
									+ "\"C:/Users/valpakuser/Desktop/ValPak PoC Data/ETL-TestReference.xlsx\"");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("PASSWORD" + " = "
									+ String.valueOf(
											"enc:routine.encryption.key.v1:H8ghH8I/WCE3ccmerz9sV1m0aLu161hVAGIGUw==")
											.substring(0, 4)
									+ "...");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("ALL_SHEETS" + " = " + "false");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("SHEETLIST" + " = " + "[{USE_REGEX=" + ("false")
									+ ", SHEETNAME=" + ("\"Referencedata\"") + "}]");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("HEADER" + " = " + "1");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("LIMIT" + " = " + "");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("FIRST_COLUMN" + " = " + "1");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("LAST_COLUMN" + " = " + "");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("TRIMALL" + " = " + "false");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("TRIMSELECT" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("departmentCode") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("departmentName") + "}]");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("GENERATION_MODE" + " = " + "EVENT_MODE");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("INCLUDE_PHONETICRUNS" + " = " + "true");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("CONFIGURE_INFLATION_RATIO" + " = " + "false");
							log4jParamters_tFileInputExcel_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputExcel_2 - " + (log4jParamters_tFileInputExcel_2));
						}
					}
					new BytesLimit65535_tFileInputExcel_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileInputExcel_2", "tFileInputExcel_2", "tFileInputExcel");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				final String decryptedPassword_tFileInputExcel_2 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:KbP7heiiP7VY4bZh3CsNkFcqPn5p2dDzTW4Jtg==");
				String password_tFileInputExcel_2 = decryptedPassword_tFileInputExcel_2;
				if (password_tFileInputExcel_2.isEmpty()) {
					password_tFileInputExcel_2 = null;
				}
				Object source_tFileInputExcel_2 = "C:/Users/valpakuser/Desktop/ValPak PoC Data/ETL-TestReference.xlsx";
				com.talend.excel.xssf.event.ExcelReader excelReader_tFileInputExcel_2 = null;

				if (source_tFileInputExcel_2 instanceof java.io.InputStream
						|| source_tFileInputExcel_2 instanceof String) {
					excelReader_tFileInputExcel_2 = new com.talend.excel.xssf.event.ExcelReader();
					excelReader_tFileInputExcel_2.setIncludePhoneticRuns(true);
				} else {
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}

				try {
					excelReader_tFileInputExcel_2.addSheetName("Referencedata", false);
					int start_column_tFileInputExcel_2 = 1 - 1;
					int end_column_tFileInputExcel_2 = -1;
					if (start_column_tFileInputExcel_2 >= 0) {// follow start column

						end_column_tFileInputExcel_2 = start_column_tFileInputExcel_2 + 2 - 1;

					} else if (end_column_tFileInputExcel_2 >= 0) {// follow end column
						start_column_tFileInputExcel_2 = end_column_tFileInputExcel_2 - 2 + 1;
					}

					if (end_column_tFileInputExcel_2 < 0 || start_column_tFileInputExcel_2 < 0) {
						throw new RuntimeException("Error start column and end column.");
					}
					int actual_end_column_tFileInputExcel_2 = end_column_tFileInputExcel_2;

					int header_tFileInputExcel_2 = 1;
					int limit_tFileInputExcel_2 = -1;

					int nb_line_tFileInputExcel_2 = 0;

					// for the number format
					java.text.DecimalFormat df_tFileInputExcel_2 = new java.text.DecimalFormat(
							"#.####################################");
					char decimalChar_tFileInputExcel_2 = df_tFileInputExcel_2.getDecimalFormatSymbols()
							.getDecimalSeparator();

					if (source_tFileInputExcel_2 instanceof String) {
						excelReader_tFileInputExcel_2.parse((String) source_tFileInputExcel_2, "ISO-8859-15",
								password_tFileInputExcel_2);
					} else if (source_tFileInputExcel_2 instanceof java.io.InputStream) {
						excelReader_tFileInputExcel_2.parse((java.io.InputStream) source_tFileInputExcel_2,
								"ISO-8859-15", password_tFileInputExcel_2);
					}

					while ((header_tFileInputExcel_2--) > 0 && excelReader_tFileInputExcel_2.hasNext()) {// skip the
																											// header
						excelReader_tFileInputExcel_2.next();
					}

					log.debug("tFileInputExcel_2 - Retrieving records from the datasource.");

					while (excelReader_tFileInputExcel_2.hasNext()) {
						int emptyColumnCount_tFileInputExcel_2 = 0;

						if (limit_tFileInputExcel_2 != -1 && nb_line_tFileInputExcel_2 >= limit_tFileInputExcel_2) {
							excelReader_tFileInputExcel_2.stopRead();
							break;
						}

						java.util.List<String> row_tFileInputExcel_2 = excelReader_tFileInputExcel_2.next();
						row2 = null;
						row2 = null;
						int tempRowLength_tFileInputExcel_2 = 2;

						int columnIndex_tFileInputExcel_2 = 0;

						String[] temp_row_tFileInputExcel_2 = new String[tempRowLength_tFileInputExcel_2];

						for (int i_tFileInputExcel_2 = 0; i_tFileInputExcel_2 < tempRowLength_tFileInputExcel_2; i_tFileInputExcel_2++) {
							int current_tFileInputExcel_2 = i_tFileInputExcel_2 + start_column_tFileInputExcel_2;
							if (current_tFileInputExcel_2 <= actual_end_column_tFileInputExcel_2) {
								if (current_tFileInputExcel_2 < row_tFileInputExcel_2.size()) {
									String column_tFileInputExcel_2 = row_tFileInputExcel_2
											.get(current_tFileInputExcel_2);
									if (column_tFileInputExcel_2 != null) {
										temp_row_tFileInputExcel_2[i_tFileInputExcel_2] = column_tFileInputExcel_2;
									} else {
										temp_row_tFileInputExcel_2[i_tFileInputExcel_2] = "";
									}
								} else {
									temp_row_tFileInputExcel_2[i_tFileInputExcel_2] = "";
								}
							} else {
								temp_row_tFileInputExcel_2[i_tFileInputExcel_2] = "";
							}
						}

						boolean whetherReject_tFileInputExcel_2 = false;
						row2 = new row2Struct();
						int curColNum_tFileInputExcel_2 = -1;
						String curColName_tFileInputExcel_2 = "";

						try {
							columnIndex_tFileInputExcel_2 = 0;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "departmentCode";

								row2.departmentCode = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row2.departmentCode = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 1;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "departmentName";

								row2.departmentName = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row2.departmentName = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							nb_line_tFileInputExcel_2++;

							log.debug("tFileInputExcel_2 - Retrieving the record " + (nb_line_tFileInputExcel_2) + ".");

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputExcel_2_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputExcel_2 = true;
							System.err.println(e.getMessage());
							row2 = null;
						}

						/**
						 * [tFileInputExcel_2 begin ] stop
						 */

						/**
						 * [tFileInputExcel_2 main ] start
						 */

						s(currentComponent = "tFileInputExcel_2");

						tos_count_tFileInputExcel_2++;

						/**
						 * [tFileInputExcel_2 main ] stop
						 */

						/**
						 * [tFileInputExcel_2 process_data_begin ] start
						 */

						s(currentComponent = "tFileInputExcel_2");

						/**
						 * [tFileInputExcel_2 process_data_begin ] stop
						 */

// Start of branch "row2"
						if (row2 != null) {

							/**
							 * [tAdvancedHash_row2 main ] start
							 */

							s(currentComponent = "tAdvancedHash_row2");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row2", "tFileInputExcel_2", "tFileInputExcel_2", "tFileInputExcel",
									"tAdvancedHash_row2", "tAdvancedHash_row2", "tAdvancedHash"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row2 - " + (row2 == null ? "" : row2.toLogString()));
							}

							row2Struct row2_HashRow = new row2Struct();

							row2_HashRow.departmentCode = row2.departmentCode;

							row2_HashRow.departmentName = row2.departmentName;

							tHash_Lookup_row2.put(row2_HashRow);

							tos_count_tAdvancedHash_row2++;

							/**
							 * [tAdvancedHash_row2 main ] stop
							 */

							/**
							 * [tAdvancedHash_row2 process_data_begin ] start
							 */

							s(currentComponent = "tAdvancedHash_row2");

							/**
							 * [tAdvancedHash_row2 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row2 process_data_end ] start
							 */

							s(currentComponent = "tAdvancedHash_row2");

							/**
							 * [tAdvancedHash_row2 process_data_end ] stop
							 */

						} // End of branch "row2"

						/**
						 * [tFileInputExcel_2 process_data_end ] start
						 */

						s(currentComponent = "tFileInputExcel_2");

						/**
						 * [tFileInputExcel_2 process_data_end ] stop
						 */

						/**
						 * [tFileInputExcel_2 end ] start
						 */

						s(currentComponent = "tFileInputExcel_2");

					}

					try {
						if (excelReader_tFileInputExcel_2 != null) {
							excelReader_tFileInputExcel_2.handleException();
						}
					} catch (java.lang.Exception e_tFileInputExcel_2) {
						globalMap.put("tFileInputExcel_2_ERROR_MESSAGE", e_tFileInputExcel_2.getMessage());
						if (!(e_tFileInputExcel_2
								.getCause() instanceof com.talend.excel.xssf.event.EnoughDataException)) {

							log.error("tFileInputExcel_2 - " + e_tFileInputExcel_2.getMessage());

							System.err.println(e_tFileInputExcel_2.getMessage());

						}
					}

					log.debug("tFileInputExcel_2 - Retrieved records count: " + nb_line_tFileInputExcel_2 + " .");

					globalMap.put("tFileInputExcel_2_NB_LINE", nb_line_tFileInputExcel_2);
				} finally {

				}

				if (log.isDebugEnabled())
					log.debug("tFileInputExcel_2 - " + ("Done."));

				ok_Hash.put("tFileInputExcel_2", true);
				end_Hash.put("tFileInputExcel_2", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_2 end ] stop
				 */

				/**
				 * [tAdvancedHash_row2 end ] start
				 */

				s(currentComponent = "tAdvancedHash_row2");

				tHash_Lookup_row2.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row2", 2, 0,
						"tFileInputExcel_2", "tFileInputExcel_2", "tFileInputExcel", "tAdvancedHash_row2",
						"tAdvancedHash_row2", "tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_row2", true);
				end_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputExcel_2 finally ] start
				 */

				s(currentComponent = "tFileInputExcel_2");

				/**
				 * [tFileInputExcel_2 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row2 finally ] start
				 */

				s(currentComponent = "tAdvancedHash_row2");

				/**
				 * [tAdvancedHash_row2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputExcel_2_SUBPROCESS_STATE", 1);
	}

	public void talendJobLogProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("talendJobLog_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		s("none");
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [talendJobLog begin ] start
				 */

				sh("talendJobLog");

				s(currentComponent = "talendJobLog");

				int tos_count_talendJobLog = 0;

				for (JobStructureCatcherUtils.JobStructureCatcherMessage jcm : talendJobLog.getMessages()) {
					org.talend.job.audit.JobContextBuilder builder_talendJobLog = org.talend.job.audit.JobContextBuilder
							.create().jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
							.custom("process_id", jcm.pid).custom("thread_id", jcm.tid).custom("pid", pid)
							.custom("father_pid", fatherPid).custom("root_pid", rootPid);
					org.talend.logging.audit.Context log_context_talendJobLog = null;

					if (jcm.log_type == JobStructureCatcherUtils.LogType.PERFORMANCE) {
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.sourceId(jcm.sourceId)
								.sourceLabel(jcm.sourceLabel).sourceConnectorType(jcm.sourceComponentName)
								.targetId(jcm.targetId).targetLabel(jcm.targetLabel)
								.targetConnectorType(jcm.targetComponentName).connectionName(jcm.current_connector)
								.rows(jcm.row_count).duration(duration).build();
						auditLogger_talendJobLog.flowExecution(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBSTART) {
						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment).build();
						auditLogger_talendJobLog.jobstart(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBEND) {
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment).duration(duration)
								.status(jcm.status).build();
						auditLogger_talendJobLog.jobstop(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.RUNCOMPONENT) {
						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment)
								.connectorType(jcm.component_name).connectorId(jcm.component_id)
								.connectorLabel(jcm.component_label).build();
						auditLogger_talendJobLog.runcomponent(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.FLOWINPUT) {// log current component
																							// input line
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.connectorType(jcm.component_name)
								.connectorId(jcm.component_id).connectorLabel(jcm.component_label)
								.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
								.rows(jcm.total_row_number).duration(duration).build();
						auditLogger_talendJobLog.flowInput(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.FLOWOUTPUT) {// log current component
																								// output/reject line
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.connectorType(jcm.component_name)
								.connectorId(jcm.component_id).connectorLabel(jcm.component_label)
								.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
								.rows(jcm.total_row_number).duration(duration).build();
						auditLogger_talendJobLog.flowOutput(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBERROR) {
						java.lang.Exception e_talendJobLog = jcm.exception;
						if (e_talendJobLog != null) {
							try (java.io.StringWriter sw_talendJobLog = new java.io.StringWriter();
									java.io.PrintWriter pw_talendJobLog = new java.io.PrintWriter(sw_talendJobLog)) {
								e_talendJobLog.printStackTrace(pw_talendJobLog);
								builder_talendJobLog.custom("stacktrace", sw_talendJobLog.getBuffer().substring(0,
										java.lang.Math.min(sw_talendJobLog.getBuffer().length(), 512)));
							}
						}

						if (jcm.extra_info != null) {
							builder_talendJobLog.connectorId(jcm.component_id).custom("extra_info", jcm.extra_info);
						}

						log_context_talendJobLog = builder_talendJobLog
								.connectorType(jcm.component_id.substring(0, jcm.component_id.lastIndexOf('_')))
								.connectorId(jcm.component_id)
								.connectorLabel(jcm.component_label == null ? jcm.component_id : jcm.component_label)
								.build();

						auditLogger_talendJobLog.exception(log_context_talendJobLog);
					}

				}

				/**
				 * [talendJobLog begin ] stop
				 */

				/**
				 * [talendJobLog main ] start
				 */

				s(currentComponent = "talendJobLog");

				tos_count_talendJobLog++;

				/**
				 * [talendJobLog main ] stop
				 */

				/**
				 * [talendJobLog process_data_begin ] start
				 */

				s(currentComponent = "talendJobLog");

				/**
				 * [talendJobLog process_data_begin ] stop
				 */

				/**
				 * [talendJobLog process_data_end ] start
				 */

				s(currentComponent = "talendJobLog");

				/**
				 * [talendJobLog process_data_end ] stop
				 */

				/**
				 * [talendJobLog end ] start
				 */

				s(currentComponent = "talendJobLog");

				ok_Hash.put("talendJobLog", true);
				end_Hash.put("talendJobLog", System.currentTimeMillis());

				/**
				 * [talendJobLog end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [talendJobLog finally ] start
				 */

				s(currentComponent = "talendJobLog");

				/**
				 * [talendJobLog finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("talendJobLog_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	private final static java.util.Properties jobInfo = new java.util.Properties();
	private final static java.util.Map<String, String> mdcInfo = new java.util.HashMap<>();
	private final static java.util.concurrent.atomic.AtomicLong subJobPidCounter = new java.util.concurrent.atomic.AtomicLong();

	public static void main(String[] args) {
		final ETLTestReference ETLTestReferenceClass = new ETLTestReference();

		int exitCode = ETLTestReferenceClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'ETLTestReference' - Done.");
		}

		System.exit(exitCode);
	}

	private void getjobInfo() {
		final String TEMPLATE_PATH = "src/main/templates/jobInfo_template.properties";
		final String BUILD_PATH = "../jobInfo.properties";
		final String path = this.getClass().getResource("").getPath();
		if (path.lastIndexOf("target") > 0) {
			final java.io.File templateFile = new java.io.File(
					path.substring(0, path.lastIndexOf("target")).concat(TEMPLATE_PATH));
			if (templateFile.exists()) {
				readJobInfo(templateFile);
				return;
			}
		}
		readJobInfo(new java.io.File(BUILD_PATH));
	}

	private void readJobInfo(java.io.File jobInfoFile) {

		if (jobInfoFile.exists()) {
			try (java.io.InputStream is = new java.io.FileInputStream(jobInfoFile)) {
				jobInfo.load(is);
			} catch (IOException e) {

				log.debug("Read jobInfo.properties file fail: " + e.getMessage());

			}
		}
		log.info(String.format("Project name: %s\tJob name: %s\tGIT Commit ID: %s\tTalend Version: %s", projectName,
				jobName, jobInfo.getProperty("gitCommitId"), "8.0.1.20241121_1314-patch"));

	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}

		final boolean enableCBP = false;
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (!inOSGi) {
			if (org.talend.metrics.CBPClient.getInstanceForCurrentVM() == null) {
				try {
					org.talend.metrics.CBPClient.startListenIfNotStarted(enableCBP, true);
				} catch (java.lang.Exception e) {
					errorCode = 1;
					status = "failure";
					e.printStackTrace();
					return 1;
				}
			}
		}

		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (!"".equals(log4jLevel)) {

			if ("trace".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.TRACE);
			} else if ("debug".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.DEBUG);
			} else if ("info".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.INFO);
			} else if ("warn".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.WARN);
			} else if ("error".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.ERROR);
			} else if ("fatal".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.FATAL);
			} else if ("off".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.OFF);
			}
			org.apache.logging.log4j.core.config.Configurator
					.setLevel(org.apache.logging.log4j.LogManager.getRootLogger().getName(), log.getLevel());

		}

		getjobInfo();
		log.info("TalendJob: 'ETLTestReference' - Start.");

		java.util.Set<Object> jobInfoKeys = jobInfo.keySet();
		for (Object jobInfoKey : jobInfoKeys) {
			org.slf4j.MDC.put("_" + jobInfoKey.toString(), jobInfo.get(jobInfoKey).toString());
		}
		org.slf4j.MDC.put("_pid", pid);
		org.slf4j.MDC.put("_rootPid", rootPid);
		org.slf4j.MDC.put("_fatherPid", fatherPid);
		org.slf4j.MDC.put("_projectName", projectName);
		org.slf4j.MDC.put("_startTimestamp", java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC)
				.format(java.time.format.DateTimeFormatter.ISO_INSTANT));
		org.slf4j.MDC.put("_jobRepositoryId", "_xk2dkK2hEe-5pIHvCIRXWQ");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2024-12-09T17:01:40.403024500Z");

		java.lang.management.RuntimeMXBean mx = java.lang.management.ManagementFactory.getRuntimeMXBean();
		String[] mxNameTable = mx.getName().split("@"); //$NON-NLS-1$
		if (mxNameTable.length == 2) {
			org.slf4j.MDC.put("_systemPid", mxNameTable[0]);
		} else {
			org.slf4j.MDC.put("_systemPid", String.valueOf(java.lang.Thread.currentThread().getId()));
		}

		if (enableLogStash) {
			java.util.Properties properties_talendJobLog = new java.util.Properties();
			properties_talendJobLog.setProperty("root.logger", "audit");
			properties_talendJobLog.setProperty("encoding", "UTF-8");
			properties_talendJobLog.setProperty("application.name", "Talend Studio");
			properties_talendJobLog.setProperty("service.name", "Talend Studio Job");
			properties_talendJobLog.setProperty("instance.name", "Talend Studio Job Instance");
			properties_talendJobLog.setProperty("propagate.appender.exceptions", "none");
			properties_talendJobLog.setProperty("log.appender", "file");
			properties_talendJobLog.setProperty("appender.file.path", "audit.json");
			properties_talendJobLog.setProperty("appender.file.maxsize", "52428800");
			properties_talendJobLog.setProperty("appender.file.maxbackup", "20");
			properties_talendJobLog.setProperty("host", "false");

			System.getProperties().stringPropertyNames().stream().filter(it -> it.startsWith("audit.logger."))
					.forEach(key -> properties_talendJobLog.setProperty(key.substring("audit.logger.".length()),
							System.getProperty(key)));

			org.apache.logging.log4j.core.config.Configurator
					.setLevel(properties_talendJobLog.getProperty("root.logger"), org.apache.logging.log4j.Level.DEBUG);

			auditLogger_talendJobLog = org.talend.job.audit.JobEventAuditLoggerFactory
					.createJobAuditLogger(properties_talendJobLog);
		}

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		org.slf4j.MDC.put("_pid", pid);

		if (rootPid == null) {
			rootPid = pid;
		}

		org.slf4j.MDC.put("_rootPid", rootPid);

		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}
		org.slf4j.MDC.put("_fatherPid", fatherPid);

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}

		try {
			java.util.Dictionary<String, Object> jobProperties = null;
			if (inOSGi) {
				jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

				if (jobProperties != null && jobProperties.get("context") != null) {
					contextStr = (String) jobProperties.get("context");
				}

				if (jobProperties != null && jobProperties.get("taskExecutionId") != null) {
					taskExecutionId = (String) jobProperties.get("taskExecutionId");
				}

				// extract ids from parent route
				if (null == taskExecutionId || taskExecutionId.isEmpty()) {
					for (String arg : args) {
						if (arg.startsWith("--context_param")
								&& (arg.contains("taskExecutionId") || arg.contains("jobExecutionId"))) {

							String keyValue = arg.replace("--context_param", "");
							String[] parts = keyValue.split("=");
							String[] cleanParts = java.util.Arrays.stream(parts).filter(s -> !s.isEmpty())
									.toArray(String[]::new);
							if (cleanParts.length == 2) {
								String key = cleanParts[0];
								String value = cleanParts[1];
								if ("taskExecutionId".equals(key.trim()) && null != value) {
									taskExecutionId = value.trim();
								} else if ("jobExecutionId".equals(key.trim()) && null != value) {
									jobExecutionId = value.trim();
								}
							}
						}
					}
				}
			}

			// first load default key-value pairs from application.properties
			if (isStandaloneMS) {
				context.putAll(this.getDefaultProperties());
			}
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = ETLTestReference.class.getClassLoader()
					.getResourceAsStream("valpak_poc/etltestreference_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = ETLTestReference.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						if (inOSGi && jobProperties != null) {
							java.util.Enumeration<String> keys = jobProperties.keys();
							while (keys.hasMoreElements()) {
								String propKey = keys.nextElement();
								if (defaultProps.containsKey(propKey)) {
									defaultProps.put(propKey, (String) jobProperties.get(propKey));
								}
							}
						}
						context = new ContextProperties(defaultProps);
					}
					if (isStandaloneMS) {
						// override context key-value pairs if provided using --context=contextName
						defaultProps.load(inContext);
						context.putAll(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}
			// override key-value pairs if provided via --config.location=file1.file2 OR
			// --config.additional-location=file1,file2
			if (isStandaloneMS) {
				context.putAll(this.getAdditionalProperties());
			}

			// override key-value pairs if provide via command line like
			// --key1=value1,--key2=value2
			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, ContextProperties.class, parametersToEncrypt));

		org.slf4j.MDC.put("_context", contextStr);
		log.info("TalendJob: 'ETLTestReference' - Started.");
		java.util.Optional.ofNullable(org.slf4j.MDC.getCopyOfContextMap()).ifPresent(mdcInfo::putAll);

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		if (enableLogStash) {
			talendJobLog.addJobStartMessage();
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputExcel_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputExcel_1) {
			globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", -1);

			e_tFileInputExcel_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : ETLTestReference");
		}
		if (enableLogStash) {
			talendJobLog.addJobEndMessage(startTime, end, status);
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		if (!inOSGi) {
			if (org.talend.metrics.CBPClient.getInstanceForCurrentVM() != null) {
				s("none");
				org.talend.metrics.CBPClient.getInstanceForCurrentVM().sendData();
			}
		}

		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");
		resumeUtil.flush();

		org.slf4j.MDC.remove("_subJobName");
		org.slf4j.MDC.remove("_subJobPid");
		org.slf4j.MDC.remove("_systemPid");
		log.info("TalendJob: 'ETLTestReference' - Finished - status: " + status + " returnCode: " + returnCode);

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {
		// add CBP code for OSGI Executions
		if (null != taskExecutionId && !taskExecutionId.isEmpty()) {
			try {
				org.talend.metrics.DataReadTracker.setExecutionId(taskExecutionId, jobExecutionId, false);
				org.talend.metrics.DataReadTracker.sealCounter();
				org.talend.metrics.DataReadTracker.reset();
			} catch (Exception | NoClassDefFoundError e) {
				// ignore
			}
		}

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--context_file")) {
			String keyValue = arg.substring(15);
			String filePath = new String(java.util.Base64.getDecoder().decode(keyValue));
			java.nio.file.Path contextFile = java.nio.file.Paths.get(filePath);
			try (java.io.BufferedReader reader = java.nio.file.Files.newBufferedReader(contextFile)) {
				String line;
				while ((line = reader.readLine()) != null) {
					int index = -1;
					if ((index = line.indexOf('=')) > -1) {
						if (line.startsWith("--context_param")) {
							if ("id_Password".equals(context_param.getContextType(line.substring(16, index)))) {
								context_param.put(line.substring(16, index),
										routines.system.PasswordEncryptUtil.decryptPassword(line.substring(index + 1)));
							} else {
								context_param.put(line.substring(16, index), line.substring(index + 1));
							}
						} else {// --context_type
							context_param.setContextType(line.substring(15, index), line.substring(index + 1));
						}
					}
				}
			} catch (java.io.IOException e) {
				System.err.println("Could not load the context file: " + filePath);
				e.printStackTrace();
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 159191 characters generated by Talend Cloud Data Fabric on the December 9,
 * 2024 at 5:01:40 PM UTC
 ************************************************************************************************/