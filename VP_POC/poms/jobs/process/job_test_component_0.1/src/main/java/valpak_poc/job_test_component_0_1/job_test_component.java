
package valpak_poc.job_test_component_0_1;

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
 * Job: job_test_component Purpose: <br>
 * Description: <br>
 * 
 * @author alex.hicks@qlik.com
 * @version 8.0.1.20241121_1314-patch
 * @status
 */
public class job_test_component implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "job_test_component.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(job_test_component.class);

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
	private final String jobName = "job_test_component";
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
			"_kfaZ4HgCEe-f3cj5nWZGhw", "0.1");
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
					job_test_component.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(job_test_component.this, new Object[] { e, currentComponent, globalMap });
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

	public void tFileInputFullRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputFullRow_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tNormalize_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputFullRow_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputFullRow_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputFullRow_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputFullRow_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUnite_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputFullRow_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputFullRow_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputFullRow_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputFullRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputFullRow_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row8_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputFullRow_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDenormalize_1_DenormalizeOut_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tDenormalize_1_ArrayIn_error(exception, errorComponent, globalMap);

	}

	public void tDenormalize_1_ArrayIn_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputFullRow_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendJobLog_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendJobLog_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputFullRow_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row13Struct implements routines.system.IPersistableRow<row13Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_job_test_component = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_job_test_component = new byte[0];

		public String lines_after;

		public String getLines_after() {
			return this.lines_after;
		}

		public Boolean lines_afterIsNullable() {
			return true;
		}

		public Boolean lines_afterIsKey() {
			return false;
		}

		public Integer lines_afterLength() {
			return null;
		}

		public Integer lines_afterPrecision() {
			return null;
		}

		public String lines_afterDefault() {

			return null;

		}

		public String lines_afterComment() {

			return "";

		}

		public String lines_afterPattern() {

			return "";

		}

		public String lines_afterOriginalDbColumnName() {

			return "lines_after";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines_after = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines_after = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.lines_after, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.lines_after, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("lines_after=" + lines_after);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (lines_after == null) {
				sb.append("<null>");
			} else {
				sb.append(lines_after);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row13Struct other) {

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

	public static class row11Struct implements routines.system.IPersistableRow<row11Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_job_test_component = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_job_test_component = new byte[0];

		public String lines_after;

		public String getLines_after() {
			return this.lines_after;
		}

		public Boolean lines_afterIsNullable() {
			return true;
		}

		public Boolean lines_afterIsKey() {
			return false;
		}

		public Integer lines_afterLength() {
			return null;
		}

		public Integer lines_afterPrecision() {
			return null;
		}

		public String lines_afterDefault() {

			return null;

		}

		public String lines_afterComment() {

			return "";

		}

		public String lines_afterPattern() {

			return "";

		}

		public String lines_afterOriginalDbColumnName() {

			return "lines_after";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines_after = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines_after = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.lines_after, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.lines_after, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("lines_after=" + lines_after);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (lines_after == null) {
				sb.append("<null>");
			} else {
				sb.append(lines_after);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row11Struct other) {

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

	public static class row10Struct implements routines.system.IPersistableRow<row10Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_job_test_component = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_job_test_component = new byte[0];

		public String lines_after;

		public String getLines_after() {
			return this.lines_after;
		}

		public Boolean lines_afterIsNullable() {
			return true;
		}

		public Boolean lines_afterIsKey() {
			return false;
		}

		public Integer lines_afterLength() {
			return null;
		}

		public Integer lines_afterPrecision() {
			return null;
		}

		public String lines_afterDefault() {

			return null;

		}

		public String lines_afterComment() {

			return "";

		}

		public String lines_afterPattern() {

			return "";

		}

		public String lines_afterOriginalDbColumnName() {

			return "lines_after";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines_after = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines_after = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.lines_after, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.lines_after, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("lines_after=" + lines_after);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (lines_after == null) {
				sb.append("<null>");
			} else {
				sb.append(lines_after);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row10Struct other) {

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

	public static class OnRowsEndStructtDenormalize_1
			implements routines.system.IPersistableRow<OnRowsEndStructtDenormalize_1> {
		final static byte[] commonByteArrayLock_VALPAK_POC_job_test_component = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_job_test_component = new byte[0];

		public String lines_after;

		public String getLines_after() {
			return this.lines_after;
		}

		public Boolean lines_afterIsNullable() {
			return true;
		}

		public Boolean lines_afterIsKey() {
			return false;
		}

		public Integer lines_afterLength() {
			return null;
		}

		public Integer lines_afterPrecision() {
			return null;
		}

		public String lines_afterDefault() {

			return null;

		}

		public String lines_afterComment() {

			return "";

		}

		public String lines_afterPattern() {

			return "";

		}

		public String lines_afterOriginalDbColumnName() {

			return "lines_after";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines_after = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines_after = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.lines_after, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.lines_after, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("lines_after=" + lines_after);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (lines_after == null) {
				sb.append("<null>");
			} else {
				sb.append(lines_after);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtDenormalize_1 other) {

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

	public static class row9Struct implements routines.system.IPersistableRow<row9Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_job_test_component = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_job_test_component = new byte[0];

		public String lines_after;

		public String getLines_after() {
			return this.lines_after;
		}

		public Boolean lines_afterIsNullable() {
			return true;
		}

		public Boolean lines_afterIsKey() {
			return false;
		}

		public Integer lines_afterLength() {
			return null;
		}

		public Integer lines_afterPrecision() {
			return null;
		}

		public String lines_afterDefault() {

			return null;

		}

		public String lines_afterComment() {

			return "";

		}

		public String lines_afterPattern() {

			return "";

		}

		public String lines_afterOriginalDbColumnName() {

			return "lines_after";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines_after = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines_after = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.lines_after, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.lines_after, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("lines_after=" + lines_after);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (lines_after == null) {
				sb.append("<null>");
			} else {
				sb.append(lines_after);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

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

	public static class out2Struct implements routines.system.IPersistableRow<out2Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_job_test_component = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_job_test_component = new byte[0];

		public String lines_after;

		public String getLines_after() {
			return this.lines_after;
		}

		public Boolean lines_afterIsNullable() {
			return true;
		}

		public Boolean lines_afterIsKey() {
			return false;
		}

		public Integer lines_afterLength() {
			return null;
		}

		public Integer lines_afterPrecision() {
			return null;
		}

		public String lines_afterDefault() {

			return null;

		}

		public String lines_afterComment() {

			return "";

		}

		public String lines_afterPattern() {

			return "";

		}

		public String lines_afterOriginalDbColumnName() {

			return "lines_after";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines_after = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines_after = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.lines_after, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.lines_after, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("lines_after=" + lines_after);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (lines_after == null) {
				sb.append("<null>");
			} else {
				sb.append(lines_after);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out2Struct other) {

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

	public static class row7Struct implements routines.system.IPersistableRow<row7Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_job_test_component = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_job_test_component = new byte[0];

		public String lines;

		public String getLines() {
			return this.lines;
		}

		public Boolean linesIsNullable() {
			return true;
		}

		public Boolean linesIsKey() {
			return false;
		}

		public Integer linesLength() {
			return 255;
		}

		public Integer linesPrecision() {
			return 0;
		}

		public String linesDefault() {

			return null;

		}

		public String linesComment() {

			return null;

		}

		public String linesPattern() {

			return null;

		}

		public String linesOriginalDbColumnName() {

			return "lines";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.lines, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.lines, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("lines=" + lines);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (lines == null) {
				sb.append("<null>");
			} else {
				sb.append(lines);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row7Struct other) {

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

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_job_test_component = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_job_test_component = new byte[0];

		public String lines;

		public String getLines() {
			return this.lines;
		}

		public Boolean linesIsNullable() {
			return true;
		}

		public Boolean linesIsKey() {
			return false;
		}

		public Integer linesLength() {
			return 255;
		}

		public Integer linesPrecision() {
			return 0;
		}

		public String linesDefault() {

			return null;

		}

		public String linesComment() {

			return null;

		}

		public String linesPattern() {

			return null;

		}

		public String linesOriginalDbColumnName() {

			return "lines";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.lines, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.lines, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("lines=" + lines);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (lines == null) {
				sb.append("<null>");
			} else {
				sb.append(lines);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

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

	public static class after_tFileInputFullRow_1Struct
			implements routines.system.IPersistableRow<after_tFileInputFullRow_1Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_job_test_component = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_job_test_component = new byte[0];

		public String lines;

		public String getLines() {
			return this.lines;
		}

		public Boolean linesIsNullable() {
			return true;
		}

		public Boolean linesIsKey() {
			return false;
		}

		public Integer linesLength() {
			return 255;
		}

		public Integer linesPrecision() {
			return 0;
		}

		public String linesDefault() {

			return null;

		}

		public String linesComment() {

			return null;

		}

		public String linesPattern() {

			return null;

		}

		public String linesOriginalDbColumnName() {

			return "lines";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.lines, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.lines, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("lines=" + lines);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (lines == null) {
				sb.append("<null>");
			} else {
				sb.append(lines);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputFullRow_1Struct other) {

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

	public static class row12Struct implements routines.system.IPersistableRow<row12Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_job_test_component = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_job_test_component = new byte[0];

		public String lines_after;

		public String getLines_after() {
			return this.lines_after;
		}

		public Boolean lines_afterIsNullable() {
			return true;
		}

		public Boolean lines_afterIsKey() {
			return false;
		}

		public Integer lines_afterLength() {
			return null;
		}

		public Integer lines_afterPrecision() {
			return 0;
		}

		public String lines_afterDefault() {

			return null;

		}

		public String lines_afterComment() {

			return null;

		}

		public String lines_afterPattern() {

			return null;

		}

		public String lines_afterOriginalDbColumnName() {

			return "lines_after";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines_after = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.lines_after = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.lines_after, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.lines_after, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("lines_after=" + lines_after);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (lines_after == null) {
				sb.append("<null>");
			} else {
				sb.append(lines_after);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row12Struct other) {

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

	public void tFileInputFullRow_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputFullRow_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tFileInputFullRow_1", "pot1yj_");

		String currentVirtualComponent = null;

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

				tFileInputDelimited_4Process(globalMap);

				row5Struct row5 = new row5Struct();
				row7Struct row7 = new row7Struct();
				out2Struct out2 = new out2Struct();
				out2Struct row9 = out2;
				row10Struct row10 = new row10Struct();
				row10Struct row11 = row10;

				row12Struct row12 = new row12Struct();

				row13Struct row13 = new row13Struct();

				/**
				 * [tLogRow_3 begin ] start
				 */

				sh("tLogRow_3");

				s(currentComponent = "tLogRow_3");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row13");

				int tos_count_tLogRow_3 = 0;

				if (log.isDebugEnabled())
					log.debug("tLogRow_3 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tLogRow_3 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tLogRow_3 = new StringBuilder();
							log4jParamters_tLogRow_3.append("Parameters:");
							log4jParamters_tLogRow_3.append("BASIC_MODE" + " = " + "false");
							log4jParamters_tLogRow_3.append(" | ");
							log4jParamters_tLogRow_3.append("TABLE_PRINT" + " = " + "true");
							log4jParamters_tLogRow_3.append(" | ");
							log4jParamters_tLogRow_3.append("VERTICAL" + " = " + "false");
							log4jParamters_tLogRow_3.append(" | ");
							log4jParamters_tLogRow_3.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
							log4jParamters_tLogRow_3.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tLogRow_3 - " + (log4jParamters_tLogRow_3));
						}
					}
					new BytesLimit65535_tLogRow_3().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tLogRow_3", "tLogRow_3", "tLogRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				///////////////////////

				class Util_tLogRow_3 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[1];

					public void addRow(String[] row) {

						for (int i = 0; i < 1; i++) {
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
						for (k = 0; k < (totals + 0 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 0 - name.length() - k; i++) {
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

						// last column
						for (int i = 0; i < colLengths[0] - fillChars[0].length() - fillChars[1].length() + 2; i++) {
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
				Util_tLogRow_3 util_tLogRow_3 = new Util_tLogRow_3();
				util_tLogRow_3.setTableName("tLogRow_3");
				util_tLogRow_3.addRow(new String[] { "lines_after", });
				StringBuilder strBuffer_tLogRow_3 = null;
				int nb_line_tLogRow_3 = 0;
///////////////////////    			

				/**
				 * [tLogRow_3 begin ] stop
				 */

				/**
				 * [tUnite_1 begin ] start
				 */

				sh("tUnite_1");

				s(currentComponent = "tUnite_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row12", "row11");

				int tos_count_tUnite_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tUnite_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tUnite_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tUnite_1 = new StringBuilder();
							log4jParamters_tUnite_1.append("Parameters:");
							if (log.isDebugEnabled())
								log.debug("tUnite_1 - " + (log4jParamters_tUnite_1));
						}
					}
					new BytesLimit65535_tUnite_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tUnite_1", "tUnite_1", "tUnite");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				int nb_line_tUnite_1 = 0;

				/**
				 * [tUnite_1 begin ] stop
				 */

				/**
				 * [tDenormalize_1_DenormalizeOut begin ] start
				 */

				sh("tDenormalize_1_DenormalizeOut");

				currentVirtualComponent = "tDenormalize_1";

				s(currentComponent = "tDenormalize_1_DenormalizeOut");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row9");

				int tos_count_tDenormalize_1_DenormalizeOut = 0;

				if (log.isDebugEnabled())
					log.debug("tDenormalize_1_DenormalizeOut - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDenormalize_1_DenormalizeOut {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDenormalize_1_DenormalizeOut = new StringBuilder();
							log4jParamters_tDenormalize_1_DenormalizeOut.append("Parameters:");
							log4jParamters_tDenormalize_1_DenormalizeOut
									.append("DESTINATION" + " = " + "tDenormalize_1");
							log4jParamters_tDenormalize_1_DenormalizeOut.append(" | ");
							log4jParamters_tDenormalize_1_DenormalizeOut
									.append("DENORMALIZE_COLUMNS" + " = " + "[{MERGE=" + ("false") + ", INPUT_COLUMN="
											+ ("lines_after") + ", DELIMITER=" + ("\";\"") + "}]");
							log4jParamters_tDenormalize_1_DenormalizeOut.append(" | ");
							log4jParamters_tDenormalize_1_DenormalizeOut.append("NULL_AS_EMPTY" + " = " + "false");
							log4jParamters_tDenormalize_1_DenormalizeOut.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDenormalize_1_DenormalizeOut - "
										+ (log4jParamters_tDenormalize_1_DenormalizeOut));
						}
					}
					new BytesLimit65535_tDenormalize_1_DenormalizeOut().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDenormalize_1_DenormalizeOut", "tDenormalize_1_DenormalizeOut",
							"tDenormalizeOut");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				class DenormalizeStructtDenormalize_1_DenormalizeOut {
					StringBuilder lines_after = new StringBuilder();
				}
				DenormalizeStructtDenormalize_1_DenormalizeOut denormalize_result_tDenormalize_1_DenormalizeOut = null;

				/**
				 * [tDenormalize_1_DenormalizeOut begin ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				sh("tLogRow_1");

				s(currentComponent = "tLogRow_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "out2");

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

					int[] colLengths = new int[1];

					public void addRow(String[] row) {

						for (int i = 0; i < 1; i++) {
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
						for (k = 0; k < (totals + 0 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 0 - name.length() - k; i++) {
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

						// last column
						for (int i = 0; i < colLengths[0] - fillChars[0].length() - fillChars[1].length() + 2; i++) {
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
				util_tLogRow_1.addRow(new String[] { "lines_after", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tMap_2 begin ] start
				 */

				sh("tMap_2");

				s(currentComponent = "tMap_2");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row7");

				int tos_count_tMap_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tMap_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tMap_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tMap_2 = new StringBuilder();
							log4jParamters_tMap_2.append("Parameters:");
							log4jParamters_tMap_2.append("LINK_STYLE" + " = " + "AUTO");
							log4jParamters_tMap_2.append(" | ");
							log4jParamters_tMap_2.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
							log4jParamters_tMap_2.append(" | ");
							log4jParamters_tMap_2.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
							log4jParamters_tMap_2.append(" | ");
							log4jParamters_tMap_2.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
							log4jParamters_tMap_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tMap_2 - " + (log4jParamters_tMap_2));
						}
					}
					new BytesLimit65535_tMap_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tMap_2", "tMap_2", "tMap");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

// ###############################
// # Lookup's keys initialization
				int count_row7_tMap_2 = 0;

				int count_row8_tMap_2 = 0;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) globalMap
						.get("tHash_Lookup_row8"));

				row8Struct row8HashKey = new row8Struct();
				row8Struct row8Default = new row8Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_2__Struct {
				}
				Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_out2_tMap_2 = 0;

				out2Struct out2_tmp = new out2Struct();
// ###############################

				/**
				 * [tMap_2 begin ] stop
				 */

				/**
				 * [tNormalize_1 begin ] start
				 */

				sh("tNormalize_1");

				s(currentComponent = "tNormalize_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row5");

				int tos_count_tNormalize_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tNormalize_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tNormalize_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tNormalize_1 = new StringBuilder();
							log4jParamters_tNormalize_1.append("Parameters:");
							log4jParamters_tNormalize_1.append("NORMALIZE_COLUMN" + " = " + "lines");
							log4jParamters_tNormalize_1.append(" | ");
							log4jParamters_tNormalize_1.append("ITEMSEPARATOR" + " = " + "\";\"");
							log4jParamters_tNormalize_1.append(" | ");
							log4jParamters_tNormalize_1.append("DEDUPLICATE" + " = " + "false");
							log4jParamters_tNormalize_1.append(" | ");
							log4jParamters_tNormalize_1.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tNormalize_1.append(" | ");
							log4jParamters_tNormalize_1.append("DISCARD_TRAILING_EMPTY_STR" + " = " + "false");
							log4jParamters_tNormalize_1.append(" | ");
							log4jParamters_tNormalize_1.append("TRIM" + " = " + "false");
							log4jParamters_tNormalize_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tNormalize_1 - " + (log4jParamters_tNormalize_1));
						}
					}
					new BytesLimit65535_tNormalize_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tNormalize_1", "tNormalize_1", "tNormalize");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				int lastNoEmptyIndex_tNormalize_1 = 0;
				int nb_line_tNormalize_1 = 0;
				String tmp_tNormalize_1 = null;
				StringBuilder currentRecord_tNormalize_1 = null;
				String[] normalizeRecord_tNormalize_1 = null;
				java.util.Set<String> recordSet_tNormalize_1 = new java.util.HashSet<String>();

				if (((String) ";").length() == 0) {
					throw new IllegalArgumentException("Field Separator must be assigned a char.");
				}

				/**
				 * [tNormalize_1 begin ] stop
				 */

				/**
				 * [tFileInputFullRow_1 begin ] start
				 */

				sh("tFileInputFullRow_1");

				s(currentComponent = "tFileInputFullRow_1");

				int tos_count_tFileInputFullRow_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputFullRow_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputFullRow_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputFullRow_1 = new StringBuilder();
							log4jParamters_tFileInputFullRow_1.append("Parameters:");
							log4jParamters_tFileInputFullRow_1.append(
									"FILENAME" + " = " + "\"C:/Users/valpakuser/Desktop/Studio back ups/in.txt\"");
							log4jParamters_tFileInputFullRow_1.append(" | ");
							log4jParamters_tFileInputFullRow_1.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileInputFullRow_1.append(" | ");
							log4jParamters_tFileInputFullRow_1.append("HEADER" + " = " + "");
							log4jParamters_tFileInputFullRow_1.append(" | ");
							log4jParamters_tFileInputFullRow_1.append("FOOTER" + " = " + "");
							log4jParamters_tFileInputFullRow_1.append(" | ");
							log4jParamters_tFileInputFullRow_1.append("LIMIT" + " = " + "1");
							log4jParamters_tFileInputFullRow_1.append(" | ");
							log4jParamters_tFileInputFullRow_1.append("REMOVE_EMPTY_ROW" + " = " + "true");
							log4jParamters_tFileInputFullRow_1.append(" | ");
							log4jParamters_tFileInputFullRow_1.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileInputFullRow_1.append(" | ");
							log4jParamters_tFileInputFullRow_1.append("RANDOM" + " = " + "false");
							log4jParamters_tFileInputFullRow_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputFullRow_1 - " + (log4jParamters_tFileInputFullRow_1));
						}
					}
					new BytesLimit65535_tFileInputFullRow_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileInputFullRow_1", "tFileInputFullRow_1", "tFileInputFullRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				final StringBuffer log4jSb_tFileInputFullRow_1 = new StringBuffer();

				org.talend.fileprocess.FileInputDelimited fid_tFileInputFullRow_1 = null;

				log.debug("tFileInputFullRow_1 - Retrieving records from the datasource.");

				try {// }
					fid_tFileInputFullRow_1 = new org.talend.fileprocess.FileInputDelimited(
							"C:/Users/valpakuser/Desktop/Studio back ups/in.txt", "ISO-8859-15", "", "\n", true, 0, 0,
							1, -1, false);
					while (fid_tFileInputFullRow_1.nextRecord()) {// }
						row5 = null;
						boolean whetherReject_tFileInputFullRow_1 = false;
						row5 = new row5Struct();
						row5.lines = fid_tFileInputFullRow_1.get(0);

						/**
						 * [tFileInputFullRow_1 begin ] stop
						 */

						/**
						 * [tFileInputFullRow_1 main ] start
						 */

						s(currentComponent = "tFileInputFullRow_1");

						tos_count_tFileInputFullRow_1++;

						/**
						 * [tFileInputFullRow_1 main ] stop
						 */

						/**
						 * [tFileInputFullRow_1 process_data_begin ] start
						 */

						s(currentComponent = "tFileInputFullRow_1");

						/**
						 * [tFileInputFullRow_1 process_data_begin ] stop
						 */

						/**
						 * [tNormalize_1 main ] start
						 */

						s(currentComponent = "tNormalize_1");

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row5", "tFileInputFullRow_1", "tFileInputFullRow_1", "tFileInputFullRow",
								"tNormalize_1", "tNormalize_1", "tNormalize"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row5 - " + (row5 == null ? "" : row5.toLogString()));
						}

						normalizeRecord_tNormalize_1 = new String[1];
						if (row5.lines != null) {
							if ("".equals(row5.lines)) {
								normalizeRecord_tNormalize_1[0] = "";
							} else {

								normalizeRecord_tNormalize_1 = row5.lines.split(";", -1);

							}
						}
						lastNoEmptyIndex_tNormalize_1 = normalizeRecord_tNormalize_1.length;

						for (int i_tNormalize_1 = 0; i_tNormalize_1 < lastNoEmptyIndex_tNormalize_1; i_tNormalize_1++) {

							currentRecord_tNormalize_1 = new StringBuilder();
							nb_line_tNormalize_1++;

							row7.lines = normalizeRecord_tNormalize_1[i_tNormalize_1];

							tos_count_tNormalize_1++;

							/**
							 * [tNormalize_1 main ] stop
							 */

							/**
							 * [tNormalize_1 process_data_begin ] start
							 */

							s(currentComponent = "tNormalize_1");

							/**
							 * [tNormalize_1 process_data_begin ] stop
							 */

							/**
							 * [tMap_2 main ] start
							 */

							s(currentComponent = "tMap_2");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row7", "tNormalize_1", "tNormalize_1", "tNormalize", "tMap_2", "tMap_2", "tMap"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row7 - " + (row7 == null ? "" : row7.toLogString()));
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;

							row8Struct row8 = null;

							// ###############################
							// # Input tables (lookups)

							boolean rejectedInnerJoin_tMap_2 = false;
							boolean mainRowRejected_tMap_2 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row8"
							///////////////////////////////////////////////

							boolean forceLooprow8 = false;

							row8Struct row8ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_2 = false;

								row8HashKey.source_col = row7.lines;

								row8HashKey.hashCodeDirty = true;

								tHash_Lookup_row8.lookup(row8HashKey);

							} // G_TM_M_020

							if (tHash_Lookup_row8 != null && tHash_Lookup_row8.getCount(row8HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row8'
								// and it contains more one result from keys : row8.source_col = '" +
								// row8HashKey.source_col + "'");
							} // G 071

							row8Struct fromLookup_row8 = null;
							row8 = row8Default;

							if (tHash_Lookup_row8 != null && tHash_Lookup_row8.hasNext()) { // G 099

								fromLookup_row8 = tHash_Lookup_row8.next();

							} // G 099

							if (fromLookup_row8 != null) {
								row8 = fromLookup_row8;
							}

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
								// ###############################
								// # Output tables

								out2 = null;

// # Output table : 'out2'
								count_out2_tMap_2++;

								out2_tmp.lines_after = row7.lines.equals(row8.source_col) ? row8.target_col
										: row7.lines;
								out2 = out2_tmp;
								log.debug("tMap_2 - Outputting the record " + count_out2_tMap_2
										+ " of the output table 'out2'.");

// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_2 = false;

							tos_count_tMap_2++;

							/**
							 * [tMap_2 main ] stop
							 */

							/**
							 * [tMap_2 process_data_begin ] start
							 */

							s(currentComponent = "tMap_2");

							/**
							 * [tMap_2 process_data_begin ] stop
							 */

// Start of branch "out2"
							if (out2 != null) {

								/**
								 * [tLogRow_1 main ] start
								 */

								s(currentComponent = "tLogRow_1");

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "out2", "tMap_2", "tMap_2", "tMap", "tLogRow_1", "tLogRow_1", "tLogRow"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("out2 - " + (out2 == null ? "" : out2.toLogString()));
								}

///////////////////////		

								String[] row_tLogRow_1 = new String[1];

								if (out2.lines_after != null) { //
									row_tLogRow_1[0] = String.valueOf(out2.lines_after);

								} //

								util_tLogRow_1.addRow(row_tLogRow_1);
								nb_line_tLogRow_1++;
								log.info("tLogRow_1 - Content of row " + nb_line_tLogRow_1 + ": "
										+ TalendString.unionString("|", row_tLogRow_1));
//////

//////                    

///////////////////////    			

								row9 = out2;

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
								 * [tDenormalize_1_DenormalizeOut main ] start
								 */

								currentVirtualComponent = "tDenormalize_1";

								s(currentComponent = "tDenormalize_1_DenormalizeOut");

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "row9", "tLogRow_1", "tLogRow_1", "tLogRow", "tDenormalize_1_DenormalizeOut",
										"tDenormalize_1_DenormalizeOut", "tDenormalizeOut"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("row9 - " + (row9 == null ? "" : row9.toLogString()));
								}

								if (denormalize_result_tDenormalize_1_DenormalizeOut == null) {
									denormalize_result_tDenormalize_1_DenormalizeOut = new DenormalizeStructtDenormalize_1_DenormalizeOut();
									denormalize_result_tDenormalize_1_DenormalizeOut.lines_after
											.append(java.util.Optional.ofNullable(row9.lines_after).orElse(null));
								} else {
									denormalize_result_tDenormalize_1_DenormalizeOut.lines_after.append(";")
											.append(java.util.Optional.ofNullable(row9.lines_after).orElse(null));
								}

								tos_count_tDenormalize_1_DenormalizeOut++;

								/**
								 * [tDenormalize_1_DenormalizeOut main ] stop
								 */

								/**
								 * [tDenormalize_1_DenormalizeOut process_data_begin ] start
								 */

								currentVirtualComponent = "tDenormalize_1";

								s(currentComponent = "tDenormalize_1_DenormalizeOut");

								/**
								 * [tDenormalize_1_DenormalizeOut process_data_begin ] stop
								 */

								/**
								 * [tDenormalize_1_DenormalizeOut process_data_end ] start
								 */

								currentVirtualComponent = "tDenormalize_1";

								s(currentComponent = "tDenormalize_1_DenormalizeOut");

								/**
								 * [tDenormalize_1_DenormalizeOut process_data_end ] stop
								 */

								/**
								 * [tLogRow_1 process_data_end ] start
								 */

								s(currentComponent = "tLogRow_1");

								/**
								 * [tLogRow_1 process_data_end ] stop
								 */

							} // End of branch "out2"

							/**
							 * [tMap_2 process_data_end ] start
							 */

							s(currentComponent = "tMap_2");

							/**
							 * [tMap_2 process_data_end ] stop
							 */

							// end for
						}

						/**
						 * [tNormalize_1 process_data_end ] start
						 */

						s(currentComponent = "tNormalize_1");

						/**
						 * [tNormalize_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputFullRow_1 process_data_end ] start
						 */

						s(currentComponent = "tFileInputFullRow_1");

						/**
						 * [tFileInputFullRow_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputFullRow_1 end ] start
						 */

						s(currentComponent = "tFileInputFullRow_1");

					}
				} finally {
					if (fid_tFileInputFullRow_1 != null) {
						fid_tFileInputFullRow_1.close();
					}
				}
				globalMap.put("tFileInputFullRow_1_NB_LINE", fid_tFileInputFullRow_1.getRowNumber());
				log.debug("tFileInputFullRow_1 - Retrieved records count: "
						+ globalMap.get("tFileInputFullRow_1_NB_LINE") + " .");

				if (log.isDebugEnabled())
					log.debug("tFileInputFullRow_1 - " + ("Done."));

				ok_Hash.put("tFileInputFullRow_1", true);
				end_Hash.put("tFileInputFullRow_1", System.currentTimeMillis());

				/**
				 * [tFileInputFullRow_1 end ] stop
				 */

				/**
				 * [tNormalize_1 end ] start
				 */

				s(currentComponent = "tNormalize_1");

				globalMap.put("tNormalize_1_NB_LINE", nb_line_tNormalize_1);
				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row5", 2, 0,
						"tFileInputFullRow_1", "tFileInputFullRow_1", "tFileInputFullRow", "tNormalize_1",
						"tNormalize_1", "tNormalize", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tNormalize_1 - " + ("Done."));

				ok_Hash.put("tNormalize_1", true);
				end_Hash.put("tNormalize_1", System.currentTimeMillis());

				/**
				 * [tNormalize_1 end ] stop
				 */

				/**
				 * [tMap_2 end ] start
				 */

				s(currentComponent = "tMap_2");

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row8 != null) {
					tHash_Lookup_row8.endGet();
				}
				globalMap.remove("tHash_Lookup_row8");

// ###############################      
				log.debug("tMap_2 - Written records count in the table 'out2': " + count_out2_tMap_2 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row7", 2, 0,
						"tNormalize_1", "tNormalize_1", "tNormalize", "tMap_2", "tMap_2", "tMap", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tMap_2 - " + ("Done."));

				ok_Hash.put("tMap_2", true);
				end_Hash.put("tMap_2", System.currentTimeMillis());

				/**
				 * [tMap_2 end ] stop
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

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "out2", 2, 0, "tMap_2",
						"tMap_2", "tMap", "tLogRow_1", "tLogRow_1", "tLogRow", "output")) {
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
				 * [tDenormalize_1_DenormalizeOut end ] start
				 */

				currentVirtualComponent = "tDenormalize_1";

				s(currentComponent = "tDenormalize_1_DenormalizeOut");

				java.util.List<OnRowsEndStructtDenormalize_1> result_list_tDenormalize_1_DenormalizeOut = new java.util.ArrayList<OnRowsEndStructtDenormalize_1>();
				if (denormalize_result_tDenormalize_1_DenormalizeOut != null) {
//generate result begin
					OnRowsEndStructtDenormalize_1 denormalize_row_tDenormalize_1_DenormalizeOut = new OnRowsEndStructtDenormalize_1();

					denormalize_row_tDenormalize_1_DenormalizeOut.lines_after = denormalize_result_tDenormalize_1_DenormalizeOut.lines_after
							.toString();

					// in the deepest end

					result_list_tDenormalize_1_DenormalizeOut.add(denormalize_row_tDenormalize_1_DenormalizeOut);

				}
//generate result end
				globalMap.put("tDenormalize_1", result_list_tDenormalize_1_DenormalizeOut);
				globalMap.put("tDenormalize_1_DenormalizeOut_NB_LINE",
						result_list_tDenormalize_1_DenormalizeOut.size());
				log.info("tDenormalize_1_DenormalizeOut - Generated records count: "
						+ result_list_tDenormalize_1_DenormalizeOut.size() + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row9", 2, 0,
						"tLogRow_1", "tLogRow_1", "tLogRow", "tDenormalize_1_DenormalizeOut",
						"tDenormalize_1_DenormalizeOut", "tDenormalizeOut", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tDenormalize_1_DenormalizeOut - " + ("Done."));

				ok_Hash.put("tDenormalize_1_DenormalizeOut", true);
				end_Hash.put("tDenormalize_1_DenormalizeOut", System.currentTimeMillis());

				/**
				 * [tDenormalize_1_DenormalizeOut end ] stop
				 */

				/**
				 * [tLogRow_2 begin ] start
				 */

				sh("tLogRow_2");

				s(currentComponent = "tLogRow_2");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row10");

				int tos_count_tLogRow_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tLogRow_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tLogRow_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tLogRow_2 = new StringBuilder();
							log4jParamters_tLogRow_2.append("Parameters:");
							log4jParamters_tLogRow_2.append("BASIC_MODE" + " = " + "false");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("TABLE_PRINT" + " = " + "true");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("VERTICAL" + " = " + "false");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
							log4jParamters_tLogRow_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tLogRow_2 - " + (log4jParamters_tLogRow_2));
						}
					}
					new BytesLimit65535_tLogRow_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tLogRow_2", "tLogRow_2", "tLogRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				///////////////////////

				class Util_tLogRow_2 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[1];

					public void addRow(String[] row) {

						for (int i = 0; i < 1; i++) {
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
						for (k = 0; k < (totals + 0 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 0 - name.length() - k; i++) {
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

						// last column
						for (int i = 0; i < colLengths[0] - fillChars[0].length() - fillChars[1].length() + 2; i++) {
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
				Util_tLogRow_2 util_tLogRow_2 = new Util_tLogRow_2();
				util_tLogRow_2.setTableName("tLogRow_2");
				util_tLogRow_2.addRow(new String[] { "lines_after", });
				StringBuilder strBuffer_tLogRow_2 = null;
				int nb_line_tLogRow_2 = 0;
///////////////////////    			

				/**
				 * [tLogRow_2 begin ] stop
				 */

				/**
				 * [tDenormalize_1_ArrayIn begin ] start
				 */

				sh("tDenormalize_1_ArrayIn");

				currentVirtualComponent = "tDenormalize_1";

				s(currentComponent = "tDenormalize_1_ArrayIn");

				int tos_count_tDenormalize_1_ArrayIn = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tDenormalize_1_ArrayIn", "tDenormalize_1_ArrayIn", "tArrayIn");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				int nb_line_tDenormalize_1_ArrayIn = 0;
				java.util.List<OnRowsEndStructtDenormalize_1> list_tDenormalize_1_ArrayIn = (java.util.List<OnRowsEndStructtDenormalize_1>) globalMap
						.get("tDenormalize_1");
				if (list_tDenormalize_1_ArrayIn == null) {
					list_tDenormalize_1_ArrayIn = new java.util.ArrayList<OnRowsEndStructtDenormalize_1>();
				}
				for (OnRowsEndStructtDenormalize_1 row_tDenormalize_1_ArrayIn : list_tDenormalize_1_ArrayIn) {

					row10.lines_after = row_tDenormalize_1_ArrayIn.lines_after;

					/**
					 * [tDenormalize_1_ArrayIn begin ] stop
					 */

					/**
					 * [tDenormalize_1_ArrayIn main ] start
					 */

					currentVirtualComponent = "tDenormalize_1";

					s(currentComponent = "tDenormalize_1_ArrayIn");

					tos_count_tDenormalize_1_ArrayIn++;

					/**
					 * [tDenormalize_1_ArrayIn main ] stop
					 */

					/**
					 * [tDenormalize_1_ArrayIn process_data_begin ] start
					 */

					currentVirtualComponent = "tDenormalize_1";

					s(currentComponent = "tDenormalize_1_ArrayIn");

					/**
					 * [tDenormalize_1_ArrayIn process_data_begin ] stop
					 */

					/**
					 * [tLogRow_2 main ] start
					 */

					s(currentComponent = "tLogRow_2");

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row10", "tDenormalize_1_ArrayIn", "tDenormalize_1_ArrayIn", "tArrayIn", "tLogRow_2",
							"tLogRow_2", "tLogRow"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row10 - " + (row10 == null ? "" : row10.toLogString()));
					}

///////////////////////		

					String[] row_tLogRow_2 = new String[1];

					if (row10.lines_after != null) { //
						row_tLogRow_2[0] = String.valueOf(row10.lines_after);

					} //

					util_tLogRow_2.addRow(row_tLogRow_2);
					nb_line_tLogRow_2++;
					log.info("tLogRow_2 - Content of row " + nb_line_tLogRow_2 + ": "
							+ TalendString.unionString("|", row_tLogRow_2));
//////

//////                    

///////////////////////    			

					row11 = row10;

					tos_count_tLogRow_2++;

					/**
					 * [tLogRow_2 main ] stop
					 */

					/**
					 * [tLogRow_2 process_data_begin ] start
					 */

					s(currentComponent = "tLogRow_2");

					/**
					 * [tLogRow_2 process_data_begin ] stop
					 */

					/**
					 * [tUnite_1 main ] start
					 */

					s(currentComponent = "tUnite_1");

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row11", "tLogRow_2", "tLogRow_2", "tLogRow", "tUnite_1", "tUnite_1", "tUnite"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row12 - " + (row12 == null ? "" : row12.toLogString()));
					}

					if (log.isTraceEnabled()) {
						log.trace("row11 - " + (row11 == null ? "" : row11.toLogString()));
					}

//////////

// for output
					row13 = new row13Struct();

					row13.lines_after = row11.lines_after;

					nb_line_tUnite_1++;

//////////

					tos_count_tUnite_1++;

					/**
					 * [tUnite_1 main ] stop
					 */

					/**
					 * [tUnite_1 process_data_begin ] start
					 */

					s(currentComponent = "tUnite_1");

					/**
					 * [tUnite_1 process_data_begin ] stop
					 */

					/**
					 * [tLogRow_3 main ] start
					 */

					s(currentComponent = "tLogRow_3");

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row13", "tUnite_1", "tUnite_1", "tUnite", "tLogRow_3", "tLogRow_3", "tLogRow"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row13 - " + (row13 == null ? "" : row13.toLogString()));
					}

///////////////////////		

					String[] row_tLogRow_3 = new String[1];

					if (row13.lines_after != null) { //
						row_tLogRow_3[0] = String.valueOf(row13.lines_after);

					} //

					util_tLogRow_3.addRow(row_tLogRow_3);
					nb_line_tLogRow_3++;
					log.info("tLogRow_3 - Content of row " + nb_line_tLogRow_3 + ": "
							+ TalendString.unionString("|", row_tLogRow_3));
//////

//////                    

///////////////////////    			

					tos_count_tLogRow_3++;

					/**
					 * [tLogRow_3 main ] stop
					 */

					/**
					 * [tLogRow_3 process_data_begin ] start
					 */

					s(currentComponent = "tLogRow_3");

					/**
					 * [tLogRow_3 process_data_begin ] stop
					 */

					/**
					 * [tLogRow_3 process_data_end ] start
					 */

					s(currentComponent = "tLogRow_3");

					/**
					 * [tLogRow_3 process_data_end ] stop
					 */

					/**
					 * [tUnite_1 process_data_end ] start
					 */

					s(currentComponent = "tUnite_1");

					/**
					 * [tUnite_1 process_data_end ] stop
					 */

					/**
					 * [tLogRow_2 process_data_end ] start
					 */

					s(currentComponent = "tLogRow_2");

					/**
					 * [tLogRow_2 process_data_end ] stop
					 */

					/**
					 * [tDenormalize_1_ArrayIn process_data_end ] start
					 */

					currentVirtualComponent = "tDenormalize_1";

					s(currentComponent = "tDenormalize_1_ArrayIn");

					/**
					 * [tDenormalize_1_ArrayIn process_data_end ] stop
					 */

					/**
					 * [tDenormalize_1_ArrayIn end ] start
					 */

					currentVirtualComponent = "tDenormalize_1";

					s(currentComponent = "tDenormalize_1_ArrayIn");

					nb_line_tDenormalize_1_ArrayIn++;
				}
				globalMap.put("tDenormalize_1_ArrayIn_NB_LINE", nb_line_tDenormalize_1_ArrayIn);

				ok_Hash.put("tDenormalize_1_ArrayIn", true);
				end_Hash.put("tDenormalize_1_ArrayIn", System.currentTimeMillis());

				/**
				 * [tDenormalize_1_ArrayIn end ] stop
				 */

				/**
				 * [tLogRow_2 end ] start
				 */

				s(currentComponent = "tLogRow_2");

//////

				java.io.PrintStream consoleOut_tLogRow_2 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_2 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_2 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_2);
				}

				consoleOut_tLogRow_2.println(util_tLogRow_2.format().toString());
				consoleOut_tLogRow_2.flush();
//////
				globalMap.put("tLogRow_2_NB_LINE", nb_line_tLogRow_2);
				if (log.isInfoEnabled())
					log.info("tLogRow_2 - " + ("Printed row count: ") + (nb_line_tLogRow_2) + ("."));

///////////////////////    			

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row10", 2, 0,
						"tDenormalize_1_ArrayIn", "tDenormalize_1_ArrayIn", "tArrayIn", "tLogRow_2", "tLogRow_2",
						"tLogRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_2 - " + ("Done."));

				ok_Hash.put("tLogRow_2", true);
				end_Hash.put("tLogRow_2", System.currentTimeMillis());

				/**
				 * [tLogRow_2 end ] stop
				 */

				/**
				 * [tFileInputFullRow_2 begin ] start
				 */

				sh("tFileInputFullRow_2");

				s(currentComponent = "tFileInputFullRow_2");

				int tos_count_tFileInputFullRow_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputFullRow_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputFullRow_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputFullRow_2 = new StringBuilder();
							log4jParamters_tFileInputFullRow_2.append("Parameters:");
							log4jParamters_tFileInputFullRow_2.append(
									"FILENAME" + " = " + "\"C:/Users/valpakuser/Desktop/Studio back ups/in.txt\"");
							log4jParamters_tFileInputFullRow_2.append(" | ");
							log4jParamters_tFileInputFullRow_2.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileInputFullRow_2.append(" | ");
							log4jParamters_tFileInputFullRow_2.append("HEADER" + " = " + "1");
							log4jParamters_tFileInputFullRow_2.append(" | ");
							log4jParamters_tFileInputFullRow_2.append("FOOTER" + " = " + "");
							log4jParamters_tFileInputFullRow_2.append(" | ");
							log4jParamters_tFileInputFullRow_2.append("LIMIT" + " = " + "");
							log4jParamters_tFileInputFullRow_2.append(" | ");
							log4jParamters_tFileInputFullRow_2.append("REMOVE_EMPTY_ROW" + " = " + "true");
							log4jParamters_tFileInputFullRow_2.append(" | ");
							log4jParamters_tFileInputFullRow_2.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileInputFullRow_2.append(" | ");
							log4jParamters_tFileInputFullRow_2.append("RANDOM" + " = " + "false");
							log4jParamters_tFileInputFullRow_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputFullRow_2 - " + (log4jParamters_tFileInputFullRow_2));
						}
					}
					new BytesLimit65535_tFileInputFullRow_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileInputFullRow_2", "tFileInputFullRow_2", "tFileInputFullRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				final StringBuffer log4jSb_tFileInputFullRow_2 = new StringBuffer();

				org.talend.fileprocess.FileInputDelimited fid_tFileInputFullRow_2 = null;

				log.debug("tFileInputFullRow_2 - Retrieving records from the datasource.");

				try {// }
					fid_tFileInputFullRow_2 = new org.talend.fileprocess.FileInputDelimited(
							"C:/Users/valpakuser/Desktop/Studio back ups/in.txt", "ISO-8859-15", "", "\n", true, 1, 0,
							-1, -1, false);
					while (fid_tFileInputFullRow_2.nextRecord()) {// }
						row12 = null;
						boolean whetherReject_tFileInputFullRow_2 = false;
						row12 = new row12Struct();
						row12.lines_after = fid_tFileInputFullRow_2.get(0);

						/**
						 * [tFileInputFullRow_2 begin ] stop
						 */

						/**
						 * [tFileInputFullRow_2 main ] start
						 */

						s(currentComponent = "tFileInputFullRow_2");

						tos_count_tFileInputFullRow_2++;

						/**
						 * [tFileInputFullRow_2 main ] stop
						 */

						/**
						 * [tFileInputFullRow_2 process_data_begin ] start
						 */

						s(currentComponent = "tFileInputFullRow_2");

						/**
						 * [tFileInputFullRow_2 process_data_begin ] stop
						 */

						/**
						 * [tUnite_1 main ] start
						 */

						s(currentComponent = "tUnite_1");

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row12", "tFileInputFullRow_2", "tFileInputFullRow_2", "tFileInputFullRow",
								"tUnite_1", "tUnite_1", "tUnite"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row12 - " + (row12 == null ? "" : row12.toLogString()));
						}

						if (log.isTraceEnabled()) {
							log.trace("row11 - " + (row11 == null ? "" : row11.toLogString()));
						}

//////////

// for output
						row13 = new row13Struct();

						row13.lines_after = row12.lines_after;

						nb_line_tUnite_1++;

//////////

						tos_count_tUnite_1++;

						/**
						 * [tUnite_1 main ] stop
						 */

						/**
						 * [tUnite_1 process_data_begin ] start
						 */

						s(currentComponent = "tUnite_1");

						/**
						 * [tUnite_1 process_data_begin ] stop
						 */

						/**
						 * [tLogRow_3 main ] start
						 */

						s(currentComponent = "tLogRow_3");

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row13", "tUnite_1", "tUnite_1", "tUnite", "tLogRow_3", "tLogRow_3", "tLogRow"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row13 - " + (row13 == null ? "" : row13.toLogString()));
						}

///////////////////////		

						String[] row_tLogRow_3 = new String[1];

						if (row13.lines_after != null) { //
							row_tLogRow_3[0] = String.valueOf(row13.lines_after);

						} //

						util_tLogRow_3.addRow(row_tLogRow_3);
						nb_line_tLogRow_3++;
						log.info("tLogRow_3 - Content of row " + nb_line_tLogRow_3 + ": "
								+ TalendString.unionString("|", row_tLogRow_3));
//////

//////                    

///////////////////////    			

						tos_count_tLogRow_3++;

						/**
						 * [tLogRow_3 main ] stop
						 */

						/**
						 * [tLogRow_3 process_data_begin ] start
						 */

						s(currentComponent = "tLogRow_3");

						/**
						 * [tLogRow_3 process_data_begin ] stop
						 */

						/**
						 * [tLogRow_3 process_data_end ] start
						 */

						s(currentComponent = "tLogRow_3");

						/**
						 * [tLogRow_3 process_data_end ] stop
						 */

						/**
						 * [tUnite_1 process_data_end ] start
						 */

						s(currentComponent = "tUnite_1");

						/**
						 * [tUnite_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputFullRow_2 process_data_end ] start
						 */

						s(currentComponent = "tFileInputFullRow_2");

						/**
						 * [tFileInputFullRow_2 process_data_end ] stop
						 */

						/**
						 * [tFileInputFullRow_2 end ] start
						 */

						s(currentComponent = "tFileInputFullRow_2");

					}
				} finally {
					if (fid_tFileInputFullRow_2 != null) {
						fid_tFileInputFullRow_2.close();
					}
				}
				globalMap.put("tFileInputFullRow_2_NB_LINE", fid_tFileInputFullRow_2.getRowNumber());
				log.debug("tFileInputFullRow_2 - Retrieved records count: "
						+ globalMap.get("tFileInputFullRow_2_NB_LINE") + " .");

				if (log.isDebugEnabled())
					log.debug("tFileInputFullRow_2 - " + ("Done."));

				ok_Hash.put("tFileInputFullRow_2", true);
				end_Hash.put("tFileInputFullRow_2", System.currentTimeMillis());

				/**
				 * [tFileInputFullRow_2 end ] stop
				 */

				/**
				 * [tUnite_1 end ] start
				 */

				s(currentComponent = "tUnite_1");

				globalMap.put("tUnite_1_NB_LINE", nb_line_tUnite_1);
				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row12", "row11");
				}

				if (enableLogStash) {

					if (runStat.log(resourceMap, iterateId, "row12", 2, 0, "tFileInputFullRow_2", "tFileInputFullRow_2",
							"tFileInputFullRow", "tUnite_1", "tUnite_1", "tUnite", "output")) {
						talendJobLogProcess(globalMap);
					}

					if (runStat.log(resourceMap, iterateId, "row11", 2, 0, "tLogRow_2", "tLogRow_2", "tLogRow",
							"tUnite_1", "tUnite_1", "tUnite", "output")) {
						talendJobLogProcess(globalMap);
					}

				}

				if (log.isDebugEnabled())
					log.debug("tUnite_1 - " + ("Done."));

				ok_Hash.put("tUnite_1", true);
				end_Hash.put("tUnite_1", System.currentTimeMillis());

				/**
				 * [tUnite_1 end ] stop
				 */

				/**
				 * [tLogRow_3 end ] start
				 */

				s(currentComponent = "tLogRow_3");

//////

				java.io.PrintStream consoleOut_tLogRow_3 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_3 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_3 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_3);
				}

				consoleOut_tLogRow_3.println(util_tLogRow_3.format().toString());
				consoleOut_tLogRow_3.flush();
//////
				globalMap.put("tLogRow_3_NB_LINE", nb_line_tLogRow_3);
				if (log.isInfoEnabled())
					log.info("tLogRow_3 - " + ("Printed row count: ") + (nb_line_tLogRow_3) + ("."));

///////////////////////    			

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row13", 2, 0,
						"tUnite_1", "tUnite_1", "tUnite", "tLogRow_3", "tLogRow_3", "tLogRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_3 - " + ("Done."));

				ok_Hash.put("tLogRow_3", true);
				end_Hash.put("tLogRow_3", System.currentTimeMillis());

				/**
				 * [tLogRow_3 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tDenormalize_1_ArrayIn"
			globalMap.remove("tDenormalize_1");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row8");

			try {

				/**
				 * [tFileInputFullRow_1 finally ] start
				 */

				s(currentComponent = "tFileInputFullRow_1");

				/**
				 * [tFileInputFullRow_1 finally ] stop
				 */

				/**
				 * [tNormalize_1 finally ] start
				 */

				s(currentComponent = "tNormalize_1");

				/**
				 * [tNormalize_1 finally ] stop
				 */

				/**
				 * [tMap_2 finally ] start
				 */

				s(currentComponent = "tMap_2");

				/**
				 * [tMap_2 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				s(currentComponent = "tLogRow_1");

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tDenormalize_1_DenormalizeOut finally ] start
				 */

				currentVirtualComponent = "tDenormalize_1";

				s(currentComponent = "tDenormalize_1_DenormalizeOut");

				/**
				 * [tDenormalize_1_DenormalizeOut finally ] stop
				 */

				/**
				 * [tDenormalize_1_ArrayIn finally ] start
				 */

				currentVirtualComponent = "tDenormalize_1";

				s(currentComponent = "tDenormalize_1_ArrayIn");

				/**
				 * [tDenormalize_1_ArrayIn finally ] stop
				 */

				/**
				 * [tLogRow_2 finally ] start
				 */

				s(currentComponent = "tLogRow_2");

				/**
				 * [tLogRow_2 finally ] stop
				 */

				/**
				 * [tFileInputFullRow_2 finally ] start
				 */

				s(currentComponent = "tFileInputFullRow_2");

				/**
				 * [tFileInputFullRow_2 finally ] stop
				 */

				/**
				 * [tUnite_1 finally ] start
				 */

				s(currentComponent = "tUnite_1");

				/**
				 * [tUnite_1 finally ] stop
				 */

				/**
				 * [tLogRow_3 finally ] start
				 */

				s(currentComponent = "tLogRow_3");

				/**
				 * [tLogRow_3 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputFullRow_1_SUBPROCESS_STATE", 1);
	}

	public static class row8Struct implements routines.system.IPersistableComparableLookupRow<row8Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_job_test_component = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_job_test_component = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String source_col;

		public String getSource_col() {
			return this.source_col;
		}

		public Boolean source_colIsNullable() {
			return true;
		}

		public Boolean source_colIsKey() {
			return false;
		}

		public Integer source_colLength() {
			return null;
		}

		public Integer source_colPrecision() {
			return null;
		}

		public String source_colDefault() {

			return null;

		}

		public String source_colComment() {

			return "";

		}

		public String source_colPattern() {

			return "";

		}

		public String source_colOriginalDbColumnName() {

			return "source_col";

		}

		public String target_col;

		public String getTarget_col() {
			return this.target_col;
		}

		public Boolean target_colIsNullable() {
			return true;
		}

		public Boolean target_colIsKey() {
			return false;
		}

		public Integer target_colLength() {
			return null;
		}

		public Integer target_colPrecision() {
			return null;
		}

		public String target_colDefault() {

			return null;

		}

		public String target_colComment() {

			return "";

		}

		public String target_colPattern() {

			return "";

		}

		public String target_colOriginalDbColumnName() {

			return "target_col";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.source_col == null) ? 0 : this.source_col.hashCode());

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
			final row8Struct other = (row8Struct) obj;

			if (this.source_col == null) {
				if (other.source_col != null)
					return false;

			} else if (!this.source_col.equals(other.source_col))

				return false;

			return true;
		}

		public void copyDataTo(row8Struct other) {

			other.source_col = this.source_col;
			other.target_col = this.target_col;

		}

		public void copyKeysDataTo(row8Struct other) {

			other.source_col = this.source_col;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_job_test_component.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_job_test_component.length == 0) {
						commonByteArray_VALPAK_POC_job_test_component = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_job_test_component = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_job_test_component, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_job_test_component, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.source_col = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_job_test_component) {

				try {

					int length = 0;

					this.source_col = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.source_col, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.source_col, dos);

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

				this.target_col = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.target_col = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.target_col, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.target_col, dos, objectOut);

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
			sb.append("source_col=" + source_col);
			sb.append(",target_col=" + target_col);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (source_col == null) {
				sb.append("<null>");
			} else {
				sb.append(source_col);
			}

			sb.append("|");

			if (target_col == null) {
				sb.append("<null>");
			} else {
				sb.append(target_col);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row8Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.source_col, other.source_col);
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

	public void tFileInputDelimited_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tFileInputDelimited_4", "JHIxCB_");

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

				row8Struct row8 = new row8Struct();

				/**
				 * [tAdvancedHash_row8 begin ] start
				 */

				sh("tAdvancedHash_row8");

				s(currentComponent = "tAdvancedHash_row8");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row8");

				int tos_count_tAdvancedHash_row8 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_row8", "tAdvancedHash_row8", "tAdvancedHash");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				// connection name:row8
				// source node:tFileInputDelimited_4 - inputs:(after_tFileInputFullRow_1)
				// outputs:(row8,row8) | target node:tAdvancedHash_row8 - inputs:(row8)
				// outputs:()
				// linked node: tMap_2 - inputs:(row7,row8) outputs:(out2)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row8 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row8Struct>getLookup(matchingModeEnum_row8);

				globalMap.put("tHash_Lookup_row8", tHash_Lookup_row8);

				/**
				 * [tAdvancedHash_row8 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_4 begin ] start
				 */

				sh("tFileInputDelimited_4");

				s(currentComponent = "tFileInputDelimited_4");

				int tos_count_tFileInputDelimited_4 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_4 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputDelimited_4 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputDelimited_4 = new StringBuilder();
							log4jParamters_tFileInputDelimited_4.append("Parameters:");
							log4jParamters_tFileInputDelimited_4.append("USE_EXISTING_DYNAMIC" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append(
									"FILENAME" + " = " + "\"C:/Users/valpakuser/Desktop/Studio back ups/ref.txt\"");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("FIELDSEPARATOR" + " = " + "\";\"");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("HEADER" + " = " + "0");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("FOOTER" + " = " + "0");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("LIMIT" + " = " + "");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("REMOVE_EMPTY_ROW" + " = " + "true");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("UNCOMPRESS" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("RANDOM" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("TRIMALL" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append(
									"TRIMSELECT" + " = " + "[{TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("source_col")
											+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("target_col") + "}]");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("CHECK_FIELDS_NUM" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("CHECK_DATE" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("SPLITRECORD" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("ENABLE_DECODE" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("USE_HEADER_AS_IS" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputDelimited_4 - " + (log4jParamters_tFileInputDelimited_4));
						}
					}
					new BytesLimit65535_tFileInputDelimited_4().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileInputDelimited_4", "tFileInputDelimited_4", "tFileInputDelimited");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				final routines.system.RowState rowstate_tFileInputDelimited_4 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_4 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_4 = null;
				int limit_tFileInputDelimited_4 = -1;
				try {

					Object filename_tFileInputDelimited_4 = "C:/Users/valpakuser/Desktop/Studio back ups/ref.txt";
					if (filename_tFileInputDelimited_4 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_4 = 0, random_value_tFileInputDelimited_4 = -1;
						if (footer_value_tFileInputDelimited_4 > 0 || random_value_tFileInputDelimited_4 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_4 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/valpakuser/Desktop/Studio back ups/ref.txt", "ISO-8859-15", ";", "\n", true,
								0, 0, limit_tFileInputDelimited_4, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE", e.getMessage());

						log.error("tFileInputDelimited_4 - " + e.getMessage());

						System.err.println(e.getMessage());

					}

					log.info("tFileInputDelimited_4 - Retrieving records from the datasource.");

					while (fid_tFileInputDelimited_4 != null && fid_tFileInputDelimited_4.nextRecord()) {
						rowstate_tFileInputDelimited_4.reset();

						row8 = null;

						row8 = null;

						boolean whetherReject_tFileInputDelimited_4 = false;
						row8 = new row8Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_4 = 0;

							columnIndexWithD_tFileInputDelimited_4 = 0;

							row8.source_col = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 1;

							row8.target_col = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

							if (rowstate_tFileInputDelimited_4.getException() != null) {
								throw rowstate_tFileInputDelimited_4.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_4 = true;

							log.error("tFileInputDelimited_4 - " + e.getMessage());

							System.err.println(e.getMessage());
							row8 = null;

						}

						log.debug("tFileInputDelimited_4 - Retrieving the record "
								+ fid_tFileInputDelimited_4.getRowNumber() + ".");

						/**
						 * [tFileInputDelimited_4 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_4 main ] start
						 */

						s(currentComponent = "tFileInputDelimited_4");

						tos_count_tFileInputDelimited_4++;

						/**
						 * [tFileInputDelimited_4 main ] stop
						 */

						/**
						 * [tFileInputDelimited_4 process_data_begin ] start
						 */

						s(currentComponent = "tFileInputDelimited_4");

						/**
						 * [tFileInputDelimited_4 process_data_begin ] stop
						 */

// Start of branch "row8"
						if (row8 != null) {

							/**
							 * [tAdvancedHash_row8 main ] start
							 */

							s(currentComponent = "tAdvancedHash_row8");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row8", "tFileInputDelimited_4", "tFileInputDelimited_4", "tFileInputDelimited",
									"tAdvancedHash_row8", "tAdvancedHash_row8", "tAdvancedHash"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row8 - " + (row8 == null ? "" : row8.toLogString()));
							}

							row8Struct row8_HashRow = new row8Struct();

							row8_HashRow.source_col = row8.source_col;

							row8_HashRow.target_col = row8.target_col;

							tHash_Lookup_row8.put(row8_HashRow);

							tos_count_tAdvancedHash_row8++;

							/**
							 * [tAdvancedHash_row8 main ] stop
							 */

							/**
							 * [tAdvancedHash_row8 process_data_begin ] start
							 */

							s(currentComponent = "tAdvancedHash_row8");

							/**
							 * [tAdvancedHash_row8 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row8 process_data_end ] start
							 */

							s(currentComponent = "tAdvancedHash_row8");

							/**
							 * [tAdvancedHash_row8 process_data_end ] stop
							 */

						} // End of branch "row8"

						/**
						 * [tFileInputDelimited_4 process_data_end ] start
						 */

						s(currentComponent = "tFileInputDelimited_4");

						/**
						 * [tFileInputDelimited_4 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_4 end ] start
						 */

						s(currentComponent = "tFileInputDelimited_4");

					}
				} finally {
					if (!((Object) ("C:/Users/valpakuser/Desktop/Studio back ups/ref.txt") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_4 != null) {
							fid_tFileInputDelimited_4.close();
						}
					}
					if (fid_tFileInputDelimited_4 != null) {
						globalMap.put("tFileInputDelimited_4_NB_LINE", fid_tFileInputDelimited_4.getRowNumber());

						log.info("tFileInputDelimited_4 - Retrieved records count: "
								+ fid_tFileInputDelimited_4.getRowNumber() + ".");

					}
				}

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_4 - " + ("Done."));

				ok_Hash.put("tFileInputDelimited_4", true);
				end_Hash.put("tFileInputDelimited_4", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_4 end ] stop
				 */

				/**
				 * [tAdvancedHash_row8 end ] start
				 */

				s(currentComponent = "tAdvancedHash_row8");

				tHash_Lookup_row8.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row8", 2, 0,
						"tFileInputDelimited_4", "tFileInputDelimited_4", "tFileInputDelimited", "tAdvancedHash_row8",
						"tAdvancedHash_row8", "tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_row8", true);
				end_Hash.put("tAdvancedHash_row8", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row8 end ] stop
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
				 * [tFileInputDelimited_4 finally ] start
				 */

				s(currentComponent = "tFileInputDelimited_4");

				/**
				 * [tFileInputDelimited_4 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row8 finally ] start
				 */

				s(currentComponent = "tAdvancedHash_row8");

				/**
				 * [tAdvancedHash_row8 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", 1);
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
		final job_test_component job_test_componentClass = new job_test_component();

		int exitCode = job_test_componentClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'job_test_component' - Done.");
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
		log.info("TalendJob: 'job_test_component' - Start.");

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
		org.slf4j.MDC.put("_jobRepositoryId", "_kfaZ4HgCEe-f3cj5nWZGhw");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2024-12-05T11:46:33.017888200Z");

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
			java.io.InputStream inContext = job_test_component.class.getClassLoader()
					.getResourceAsStream("valpak_poc/job_test_component_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = job_test_component.class.getClassLoader()
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
		log.info("TalendJob: 'job_test_component' - Started.");
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
			tFileInputFullRow_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputFullRow_1) {
			globalMap.put("tFileInputFullRow_1_SUBPROCESS_STATE", -1);

			e_tFileInputFullRow_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : job_test_component");
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
		log.info("TalendJob: 'job_test_component' - Finished - status: " + status + " returnCode: " + returnCode);

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
 * 203042 characters generated by Talend Cloud Data Fabric on the December 5,
 * 2024 at 11:46:33 AM UTC
 ************************************************************************************************/