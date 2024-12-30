
package valpak_poc.excelfilesformulas_0_1;

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
 * Job: ExcelFilesFormulas Purpose: <br>
 * Description: <br>
 * 
 * @author alex.hicks@qlik.com
 * @version 8.0.1.20241121_1314-patch
 * @status
 */
public class ExcelFilesFormulas implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "ExcelFilesFormulas.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(ExcelFilesFormulas.class);

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
	private final String jobName = "ExcelFilesFormulas";
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
			"_ydTGUK2SEe-R9K8WvBgLMQ", "0.1");
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
					ExcelFilesFormulas.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(ExcelFilesFormulas.this, new Object[] { e, currentComponent, globalMap });
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

	public void tFileOutputExcel_1_error(Exception exception, String errorComponent,
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

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_ExcelFilesFormulas = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_ExcelFilesFormulas = new byte[0];

		public String productLineCode;

		public String getProductLineCode() {
			return this.productLineCode;
		}

		public Boolean productLineCodeIsNullable() {
			return true;
		}

		public Boolean productLineCodeIsKey() {
			return false;
		}

		public Integer productLineCodeLength() {
			return null;
		}

		public Integer productLineCodePrecision() {
			return null;
		}

		public String productLineCodeDefault() {

			return null;

		}

		public String productLineCodeComment() {

			return "";

		}

		public String productLineCodePattern() {

			return "";

		}

		public String productLineCodeOriginalDbColumnName() {

			return "productLineCode";

		}

		public String calenderMonth;

		public String getCalenderMonth() {
			return this.calenderMonth;
		}

		public Boolean calenderMonthIsNullable() {
			return true;
		}

		public Boolean calenderMonthIsKey() {
			return false;
		}

		public Integer calenderMonthLength() {
			return null;
		}

		public Integer calenderMonthPrecision() {
			return null;
		}

		public String calenderMonthDefault() {

			return null;

		}

		public String calenderMonthComment() {

			return "";

		}

		public String calenderMonthPattern() {

			return "";

		}

		public String calenderMonthOriginalDbColumnName() {

			return "calenderMonth";

		}

		public String productNumber;

		public String getProductNumber() {
			return this.productNumber;
		}

		public Boolean productNumberIsNullable() {
			return true;
		}

		public Boolean productNumberIsKey() {
			return false;
		}

		public Integer productNumberLength() {
			return null;
		}

		public Integer productNumberPrecision() {
			return null;
		}

		public String productNumberDefault() {

			return null;

		}

		public String productNumberComment() {

			return "";

		}

		public String productNumberPattern() {

			return "";

		}

		public String productNumberOriginalDbColumnName() {

			return "productNumber";

		}

		public String productDesc;

		public String getProductDesc() {
			return this.productDesc;
		}

		public Boolean productDescIsNullable() {
			return true;
		}

		public Boolean productDescIsKey() {
			return false;
		}

		public Integer productDescLength() {
			return null;
		}

		public Integer productDescPrecision() {
			return null;
		}

		public String productDescDefault() {

			return null;

		}

		public String productDescComment() {

			return "";

		}

		public String productDescPattern() {

			return "";

		}

		public String productDescOriginalDbColumnName() {

			return "productDesc";

		}

		public String division;

		public String getDivision() {
			return this.division;
		}

		public Boolean divisionIsNullable() {
			return true;
		}

		public Boolean divisionIsKey() {
			return false;
		}

		public Integer divisionLength() {
			return null;
		}

		public Integer divisionPrecision() {
			return null;
		}

		public String divisionDefault() {

			return null;

		}

		public String divisionComment() {

			return "";

		}

		public String divisionPattern() {

			return "";

		}

		public String divisionOriginalDbColumnName() {

			return "division";

		}

		public String dept;

		public String getDept() {
			return this.dept;
		}

		public Boolean deptIsNullable() {
			return true;
		}

		public Boolean deptIsKey() {
			return false;
		}

		public Integer deptLength() {
			return null;
		}

		public Integer deptPrecision() {
			return null;
		}

		public String deptDefault() {

			return null;

		}

		public String deptComment() {

			return "";

		}

		public String deptPattern() {

			return "";

		}

		public String deptOriginalDbColumnName() {

			return "dept";

		}

		public String rnge;

		public String getRnge() {
			return this.rnge;
		}

		public Boolean rngeIsNullable() {
			return true;
		}

		public Boolean rngeIsKey() {
			return false;
		}

		public Integer rngeLength() {
			return null;
		}

		public Integer rngePrecision() {
			return null;
		}

		public String rngeDefault() {

			return null;

		}

		public String rngeComment() {

			return "";

		}

		public String rngePattern() {

			return "";

		}

		public String rngeOriginalDbColumnName() {

			return "rnge";

		}

		public String importFlag;

		public String getImportFlag() {
			return this.importFlag;
		}

		public Boolean importFlagIsNullable() {
			return true;
		}

		public Boolean importFlagIsKey() {
			return false;
		}

		public Integer importFlagLength() {
			return null;
		}

		public Integer importFlagPrecision() {
			return null;
		}

		public String importFlagDefault() {

			return null;

		}

		public String importFlagComment() {

			return "";

		}

		public String importFlagPattern() {

			return "";

		}

		public String importFlagOriginalDbColumnName() {

			return "importFlag";

		}

		public String exportFlag;

		public String getExportFlag() {
			return this.exportFlag;
		}

		public Boolean exportFlagIsNullable() {
			return true;
		}

		public Boolean exportFlagIsKey() {
			return false;
		}

		public Integer exportFlagLength() {
			return null;
		}

		public Integer exportFlagPrecision() {
			return null;
		}

		public String exportFlagDefault() {

			return null;

		}

		public String exportFlagComment() {

			return "";

		}

		public String exportFlagPattern() {

			return "";

		}

		public String exportFlagOriginalDbColumnName() {

			return "exportFlag";

		}

		public String supplierNumber;

		public String getSupplierNumber() {
			return this.supplierNumber;
		}

		public Boolean supplierNumberIsNullable() {
			return true;
		}

		public Boolean supplierNumberIsKey() {
			return false;
		}

		public Integer supplierNumberLength() {
			return null;
		}

		public Integer supplierNumberPrecision() {
			return null;
		}

		public String supplierNumberDefault() {

			return null;

		}

		public String supplierNumberComment() {

			return "";

		}

		public String supplierNumberPattern() {

			return "";

		}

		public String supplierNumberOriginalDbColumnName() {

			return "supplierNumber";

		}

		public String supplierName;

		public String getSupplierName() {
			return this.supplierName;
		}

		public Boolean supplierNameIsNullable() {
			return true;
		}

		public Boolean supplierNameIsKey() {
			return false;
		}

		public Integer supplierNameLength() {
			return null;
		}

		public Integer supplierNamePrecision() {
			return null;
		}

		public String supplierNameDefault() {

			return null;

		}

		public String supplierNameComment() {

			return "";

		}

		public String supplierNamePattern() {

			return "";

		}

		public String supplierNameOriginalDbColumnName() {

			return "supplierName";

		}

		public String supplierRefNo;

		public String getSupplierRefNo() {
			return this.supplierRefNo;
		}

		public Boolean supplierRefNoIsNullable() {
			return true;
		}

		public Boolean supplierRefNoIsKey() {
			return false;
		}

		public Integer supplierRefNoLength() {
			return null;
		}

		public Integer supplierRefNoPrecision() {
			return null;
		}

		public String supplierRefNoDefault() {

			return null;

		}

		public String supplierRefNoComment() {

			return "";

		}

		public String supplierRefNoPattern() {

			return "";

		}

		public String supplierRefNoOriginalDbColumnName() {

			return "supplierRefNo";

		}

		public String brandGroup;

		public String getBrandGroup() {
			return this.brandGroup;
		}

		public Boolean brandGroupIsNullable() {
			return true;
		}

		public Boolean brandGroupIsKey() {
			return false;
		}

		public Integer brandGroupLength() {
			return null;
		}

		public Integer brandGroupPrecision() {
			return null;
		}

		public String brandGroupDefault() {

			return null;

		}

		public String brandGroupComment() {

			return "";

		}

		public String brandGroupPattern() {

			return "";

		}

		public String brandGroupOriginalDbColumnName() {

			return "brandGroup";

		}

		public String nationSent;

		public String getNationSent() {
			return this.nationSent;
		}

		public Boolean nationSentIsNullable() {
			return true;
		}

		public Boolean nationSentIsKey() {
			return false;
		}

		public Integer nationSentLength() {
			return null;
		}

		public Integer nationSentPrecision() {
			return null;
		}

		public String nationSentDefault() {

			return null;

		}

		public String nationSentComment() {

			return "";

		}

		public String nationSentPattern() {

			return "";

		}

		public String nationSentOriginalDbColumnName() {

			return "nationSent";

		}

		public String netDespUnits;

		public String getNetDespUnits() {
			return this.netDespUnits;
		}

		public Boolean netDespUnitsIsNullable() {
			return true;
		}

		public Boolean netDespUnitsIsKey() {
			return false;
		}

		public Integer netDespUnitsLength() {
			return null;
		}

		public Integer netDespUnitsPrecision() {
			return null;
		}

		public String netDespUnitsDefault() {

			return null;

		}

		public String netDespUnitsComment() {

			return "";

		}

		public String netDespUnitsPattern() {

			return "";

		}

		public String netDespUnitsOriginalDbColumnName() {

			return "netDespUnits";

		}

		public String ukNetDespUnits;

		public String getUkNetDespUnits() {
			return this.ukNetDespUnits;
		}

		public Boolean ukNetDespUnitsIsNullable() {
			return true;
		}

		public Boolean ukNetDespUnitsIsKey() {
			return false;
		}

		public Integer ukNetDespUnitsLength() {
			return null;
		}

		public Integer ukNetDespUnitsPrecision() {
			return null;
		}

		public String ukNetDespUnitsDefault() {

			return null;

		}

		public String ukNetDespUnitsComment() {

			return "";

		}

		public String ukNetDespUnitsPattern() {

			return "";

		}

		public String ukNetDespUnitsOriginalDbColumnName() {

			return "ukNetDespUnits";

		}

		public String intNetDespUnits;

		public String getIntNetDespUnits() {
			return this.intNetDespUnits;
		}

		public Boolean intNetDespUnitsIsNullable() {
			return true;
		}

		public Boolean intNetDespUnitsIsKey() {
			return false;
		}

		public Integer intNetDespUnitsLength() {
			return null;
		}

		public Integer intNetDespUnitsPrecision() {
			return null;
		}

		public String intNetDespUnitsDefault() {

			return null;

		}

		public String intNetDespUnitsComment() {

			return "";

		}

		public String intNetDespUnitsPattern() {

			return "";

		}

		public String intNetDespUnitsOriginalDbColumnName() {

			return "intNetDespUnits";

		}

		public String locationCode;

		public String getLocationCode() {
			return this.locationCode;
		}

		public Boolean locationCodeIsNullable() {
			return true;
		}

		public Boolean locationCodeIsKey() {
			return false;
		}

		public Integer locationCodeLength() {
			return null;
		}

		public Integer locationCodePrecision() {
			return null;
		}

		public String locationCodeDefault() {

			return null;

		}

		public String locationCodeComment() {

			return "";

		}

		public String locationCodePattern() {

			return "";

		}

		public String locationCodeOriginalDbColumnName() {

			return "locationCode";

		}

		public String formulaColumn;

		public String getFormulaColumn() {
			return this.formulaColumn;
		}

		public Boolean formulaColumnIsNullable() {
			return true;
		}

		public Boolean formulaColumnIsKey() {
			return false;
		}

		public Integer formulaColumnLength() {
			return null;
		}

		public Integer formulaColumnPrecision() {
			return null;
		}

		public String formulaColumnDefault() {

			return null;

		}

		public String formulaColumnComment() {

			return "";

		}

		public String formulaColumnPattern() {

			return "";

		}

		public String formulaColumnOriginalDbColumnName() {

			return "formulaColumn";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_ExcelFilesFormulas.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_ExcelFilesFormulas.length == 0) {
						commonByteArray_VALPAK_POC_ExcelFilesFormulas = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_ExcelFilesFormulas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_ExcelFilesFormulas, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_ExcelFilesFormulas, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_ExcelFilesFormulas.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_ExcelFilesFormulas.length == 0) {
						commonByteArray_VALPAK_POC_ExcelFilesFormulas = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_ExcelFilesFormulas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_ExcelFilesFormulas, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_ExcelFilesFormulas, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_ExcelFilesFormulas) {

				try {

					int length = 0;

					this.productLineCode = readString(dis);

					this.calenderMonth = readString(dis);

					this.productNumber = readString(dis);

					this.productDesc = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.importFlag = readString(dis);

					this.exportFlag = readString(dis);

					this.supplierNumber = readString(dis);

					this.supplierName = readString(dis);

					this.supplierRefNo = readString(dis);

					this.brandGroup = readString(dis);

					this.nationSent = readString(dis);

					this.netDespUnits = readString(dis);

					this.ukNetDespUnits = readString(dis);

					this.intNetDespUnits = readString(dis);

					this.locationCode = readString(dis);

					this.formulaColumn = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_ExcelFilesFormulas) {

				try {

					int length = 0;

					this.productLineCode = readString(dis);

					this.calenderMonth = readString(dis);

					this.productNumber = readString(dis);

					this.productDesc = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.importFlag = readString(dis);

					this.exportFlag = readString(dis);

					this.supplierNumber = readString(dis);

					this.supplierName = readString(dis);

					this.supplierRefNo = readString(dis);

					this.brandGroup = readString(dis);

					this.nationSent = readString(dis);

					this.netDespUnits = readString(dis);

					this.ukNetDespUnits = readString(dis);

					this.intNetDespUnits = readString(dis);

					this.locationCode = readString(dis);

					this.formulaColumn = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.productLineCode, dos);

				// String

				writeString(this.calenderMonth, dos);

				// String

				writeString(this.productNumber, dos);

				// String

				writeString(this.productDesc, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.importFlag, dos);

				// String

				writeString(this.exportFlag, dos);

				// String

				writeString(this.supplierNumber, dos);

				// String

				writeString(this.supplierName, dos);

				// String

				writeString(this.supplierRefNo, dos);

				// String

				writeString(this.brandGroup, dos);

				// String

				writeString(this.nationSent, dos);

				// String

				writeString(this.netDespUnits, dos);

				// String

				writeString(this.ukNetDespUnits, dos);

				// String

				writeString(this.intNetDespUnits, dos);

				// String

				writeString(this.locationCode, dos);

				// String

				writeString(this.formulaColumn, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.productLineCode, dos);

				// String

				writeString(this.calenderMonth, dos);

				// String

				writeString(this.productNumber, dos);

				// String

				writeString(this.productDesc, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.importFlag, dos);

				// String

				writeString(this.exportFlag, dos);

				// String

				writeString(this.supplierNumber, dos);

				// String

				writeString(this.supplierName, dos);

				// String

				writeString(this.supplierRefNo, dos);

				// String

				writeString(this.brandGroup, dos);

				// String

				writeString(this.nationSent, dos);

				// String

				writeString(this.netDespUnits, dos);

				// String

				writeString(this.ukNetDespUnits, dos);

				// String

				writeString(this.intNetDespUnits, dos);

				// String

				writeString(this.locationCode, dos);

				// String

				writeString(this.formulaColumn, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("productLineCode=" + productLineCode);
			sb.append(",calenderMonth=" + calenderMonth);
			sb.append(",productNumber=" + productNumber);
			sb.append(",productDesc=" + productDesc);
			sb.append(",division=" + division);
			sb.append(",dept=" + dept);
			sb.append(",rnge=" + rnge);
			sb.append(",importFlag=" + importFlag);
			sb.append(",exportFlag=" + exportFlag);
			sb.append(",supplierNumber=" + supplierNumber);
			sb.append(",supplierName=" + supplierName);
			sb.append(",supplierRefNo=" + supplierRefNo);
			sb.append(",brandGroup=" + brandGroup);
			sb.append(",nationSent=" + nationSent);
			sb.append(",netDespUnits=" + netDespUnits);
			sb.append(",ukNetDespUnits=" + ukNetDespUnits);
			sb.append(",intNetDespUnits=" + intNetDespUnits);
			sb.append(",locationCode=" + locationCode);
			sb.append(",formulaColumn=" + formulaColumn);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (productLineCode == null) {
				sb.append("<null>");
			} else {
				sb.append(productLineCode);
			}

			sb.append("|");

			if (calenderMonth == null) {
				sb.append("<null>");
			} else {
				sb.append(calenderMonth);
			}

			sb.append("|");

			if (productNumber == null) {
				sb.append("<null>");
			} else {
				sb.append(productNumber);
			}

			sb.append("|");

			if (productDesc == null) {
				sb.append("<null>");
			} else {
				sb.append(productDesc);
			}

			sb.append("|");

			if (division == null) {
				sb.append("<null>");
			} else {
				sb.append(division);
			}

			sb.append("|");

			if (dept == null) {
				sb.append("<null>");
			} else {
				sb.append(dept);
			}

			sb.append("|");

			if (rnge == null) {
				sb.append("<null>");
			} else {
				sb.append(rnge);
			}

			sb.append("|");

			if (importFlag == null) {
				sb.append("<null>");
			} else {
				sb.append(importFlag);
			}

			sb.append("|");

			if (exportFlag == null) {
				sb.append("<null>");
			} else {
				sb.append(exportFlag);
			}

			sb.append("|");

			if (supplierNumber == null) {
				sb.append("<null>");
			} else {
				sb.append(supplierNumber);
			}

			sb.append("|");

			if (supplierName == null) {
				sb.append("<null>");
			} else {
				sb.append(supplierName);
			}

			sb.append("|");

			if (supplierRefNo == null) {
				sb.append("<null>");
			} else {
				sb.append(supplierRefNo);
			}

			sb.append("|");

			if (brandGroup == null) {
				sb.append("<null>");
			} else {
				sb.append(brandGroup);
			}

			sb.append("|");

			if (nationSent == null) {
				sb.append("<null>");
			} else {
				sb.append(nationSent);
			}

			sb.append("|");

			if (netDespUnits == null) {
				sb.append("<null>");
			} else {
				sb.append(netDespUnits);
			}

			sb.append("|");

			if (ukNetDespUnits == null) {
				sb.append("<null>");
			} else {
				sb.append(ukNetDespUnits);
			}

			sb.append("|");

			if (intNetDespUnits == null) {
				sb.append("<null>");
			} else {
				sb.append(intNetDespUnits);
			}

			sb.append("|");

			if (locationCode == null) {
				sb.append("<null>");
			} else {
				sb.append(locationCode);
			}

			sb.append("|");

			if (formulaColumn == null) {
				sb.append("<null>");
			} else {
				sb.append(formulaColumn);
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

	public void tFileInputExcel_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tFileInputExcel_1", "IGyE3f_");

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

				row1Struct row1 = new row1Struct();

				/**
				 * [tFileOutputExcel_1 begin ] start
				 */

				sh("tFileOutputExcel_1");

				s(currentComponent = "tFileOutputExcel_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row1");

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
									+ "\"C:/Users/valpakuser/Desktop/Outputs/JDWilliams Q1 2024 To Load Demo - Formula_output.xlsx\"");
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
									+ ("false") + ", SCHEMA_COLUMN=" + ("productLineCode") + "}, {IS_AUTO_SIZE="
									+ ("false") + ", SCHEMA_COLUMN=" + ("calenderMonth") + "}, {IS_AUTO_SIZE="
									+ ("false") + ", SCHEMA_COLUMN=" + ("productNumber") + "}, {IS_AUTO_SIZE="
									+ ("false") + ", SCHEMA_COLUMN=" + ("productDesc") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("division") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("dept") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("rnge") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("importFlag") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("exportFlag") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("supplierNumber") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("supplierName") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("supplierRefNo") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("brandGroup") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("nationSent") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("netDespUnits") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("ukNetDespUnits") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("intNetDespUnits") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("locationCode") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("formulaColumn") + "}]");
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

				String fileName_tFileOutputExcel_1 = "C:/Users/valpakuser/Desktop/Outputs/JDWilliams Q1 2024 To Load Demo - Formula_output.xlsx";
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

					xlsxTool_tFileOutputExcel_1.addCellValue("productLineCode");

					xlsxTool_tFileOutputExcel_1.addCellValue("calenderMonth");

					xlsxTool_tFileOutputExcel_1.addCellValue("productNumber");

					xlsxTool_tFileOutputExcel_1.addCellValue("productDesc");

					xlsxTool_tFileOutputExcel_1.addCellValue("division");

					xlsxTool_tFileOutputExcel_1.addCellValue("dept");

					xlsxTool_tFileOutputExcel_1.addCellValue("rnge");

					xlsxTool_tFileOutputExcel_1.addCellValue("importFlag");

					xlsxTool_tFileOutputExcel_1.addCellValue("exportFlag");

					xlsxTool_tFileOutputExcel_1.addCellValue("supplierNumber");

					xlsxTool_tFileOutputExcel_1.addCellValue("supplierName");

					xlsxTool_tFileOutputExcel_1.addCellValue("supplierRefNo");

					xlsxTool_tFileOutputExcel_1.addCellValue("brandGroup");

					xlsxTool_tFileOutputExcel_1.addCellValue("nationSent");

					xlsxTool_tFileOutputExcel_1.addCellValue("netDespUnits");

					xlsxTool_tFileOutputExcel_1.addCellValue("ukNetDespUnits");

					xlsxTool_tFileOutputExcel_1.addCellValue("intNetDespUnits");

					xlsxTool_tFileOutputExcel_1.addCellValue("locationCode");

					xlsxTool_tFileOutputExcel_1.addCellValue("formulaColumn");

					nb_line_tFileOutputExcel_1++;
					headerIsInserted_tFileOutputExcel_1 = true;

				}

				/**
				 * [tFileOutputExcel_1 begin ] stop
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
									+ "\"C:/Users/valpakuser/Desktop/ValPak PoC Data/JDWilliams Q1 2024 To Load Demo - Formula.xlsx\"");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("PASSWORD" + " = "
									+ String.valueOf(
											"enc:routine.encryption.key.v1:asCT79d6kkARe/FlCy34FaeKpkr6DliNNxXpcQ==")
											.substring(0, 4)
									+ "...");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("ALL_SHEETS" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append(
									"SHEETLIST" + " = " + "[{USE_REGEX=" + ("false") + ", SHEETNAME=" + ("1") + "}]");
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
							log4jParamters_tFileInputExcel_1.append("TRIMSELECT" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("productLineCode") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("calenderMonth") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("productNumber") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("productDesc") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("division") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("dept") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("rnge") + "}, {TRIM="
									+ ("false") + ", SCHEMA_COLUMN=" + ("importFlag") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("exportFlag") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("supplierNumber") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("supplierName") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("supplierRefNo") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("brandGroup")
									+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("nationSent") + "}, {TRIM="
									+ ("false") + ", SCHEMA_COLUMN=" + ("netDespUnits") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("ukNetDespUnits") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("intNetDespUnits") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("locationCode") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("formulaColumn") + "}]");
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
						.decryptPassword("enc:routine.encryption.key.v1:ZdpUxz+QhMSR5Jhn5qwFjj6vU/GfmJ0Cg6rm/Q==");
				String password_tFileInputExcel_1 = decryptedPassword_tFileInputExcel_1;
				if (password_tFileInputExcel_1.isEmpty()) {
					password_tFileInputExcel_1 = null;
				}
				Object source_tFileInputExcel_1 = "C:/Users/valpakuser/Desktop/ValPak PoC Data/JDWilliams Q1 2024 To Load Demo - Formula.xlsx";
				com.talend.excel.xssf.event.ExcelReader excelReader_tFileInputExcel_1 = null;

				if (source_tFileInputExcel_1 instanceof java.io.InputStream
						|| source_tFileInputExcel_1 instanceof String) {
					excelReader_tFileInputExcel_1 = new com.talend.excel.xssf.event.ExcelReader();
					excelReader_tFileInputExcel_1.setIncludePhoneticRuns(true);
				} else {
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}

				try {
					excelReader_tFileInputExcel_1.addSheetName(1, false);
					int start_column_tFileInputExcel_1 = 1 - 1;
					int end_column_tFileInputExcel_1 = -1;
					if (start_column_tFileInputExcel_1 >= 0) {// follow start column

						end_column_tFileInputExcel_1 = start_column_tFileInputExcel_1 + 19 - 1;

					} else if (end_column_tFileInputExcel_1 >= 0) {// follow end column
						start_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 - 19 + 1;
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
						int tempRowLength_tFileInputExcel_1 = 19;

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
								curColName_tFileInputExcel_1 = "productLineCode";

								row1.productLineCode = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.productLineCode = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 1;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "calenderMonth";

								row1.calenderMonth = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.calenderMonth = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 2;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "productNumber";

								row1.productNumber = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.productNumber = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 3;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "productDesc";

								row1.productDesc = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.productDesc = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 4;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "division";

								row1.division = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.division = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 5;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "dept";

								row1.dept = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.dept = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 6;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "rnge";

								row1.rnge = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.rnge = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 7;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "importFlag";

								row1.importFlag = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.importFlag = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 8;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "exportFlag";

								row1.exportFlag = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.exportFlag = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 9;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "supplierNumber";

								row1.supplierNumber = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.supplierNumber = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 10;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "supplierName";

								row1.supplierName = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.supplierName = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 11;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "supplierRefNo";

								row1.supplierRefNo = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.supplierRefNo = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 12;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "brandGroup";

								row1.brandGroup = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.brandGroup = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 13;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "nationSent";

								row1.nationSent = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.nationSent = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 14;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "netDespUnits";

								row1.netDespUnits = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.netDespUnits = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 15;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "ukNetDespUnits";

								row1.ukNetDespUnits = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.ukNetDespUnits = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 16;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "intNetDespUnits";

								row1.intNetDespUnits = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.intNetDespUnits = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 17;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "locationCode";

								row1.locationCode = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.locationCode = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 18;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "formulaColumn";

								row1.formulaColumn = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.formulaColumn = null;
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
							 * [tFileOutputExcel_1 main ] start
							 */

							s(currentComponent = "tFileOutputExcel_1");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row1", "tFileInputExcel_1", "tFileInputExcel_1", "tFileInputExcel",
									"tFileOutputExcel_1", "tFileOutputExcel_1", "tFileOutputExcel"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row1 - " + (row1 == null ? "" : row1.toLogString()));
							}

							xlsxTool_tFileOutputExcel_1.addRow();

							if (row1.productLineCode != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.productLineCode));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.calenderMonth != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.calenderMonth));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.productNumber != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.productNumber));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.productDesc != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.productDesc));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.division != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.division));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.dept != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.dept));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.rnge != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.rnge));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.importFlag != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.importFlag));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.exportFlag != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.exportFlag));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.supplierNumber != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.supplierNumber));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.supplierName != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.supplierName));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.supplierRefNo != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.supplierRefNo));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.brandGroup != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.brandGroup));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.nationSent != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.nationSent));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.netDespUnits != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.netDespUnits));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.ukNetDespUnits != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.ukNetDespUnits));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.intNetDespUnits != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.intNetDespUnits));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.locationCode != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.locationCode));
							} else {
								xlsxTool_tFileOutputExcel_1.addCellNullValue();
							}

							if (row1.formulaColumn != null) {

								xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row1.formulaColumn));
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
				 * [tFileOutputExcel_1 end ] start
				 */

				s(currentComponent = "tFileOutputExcel_1");

				xlsxTool_tFileOutputExcel_1.writeExcel(fileName_tFileOutputExcel_1, true);

				if (headerIsInserted_tFileOutputExcel_1 && nb_line_tFileOutputExcel_1 > 0) {
					nb_line_tFileOutputExcel_1 = nb_line_tFileOutputExcel_1 - 1;
				}
				globalMap.put("tFileOutputExcel_1_NB_LINE", nb_line_tFileOutputExcel_1);

				log.debug("tFileOutputExcel_1 - Written records count: " + nb_line_tFileOutputExcel_1 + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row1", 2, 0,
						"tFileInputExcel_1", "tFileInputExcel_1", "tFileInputExcel", "tFileOutputExcel_1",
						"tFileOutputExcel_1", "tFileOutputExcel", "output")) {
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

			try {

				/**
				 * [tFileInputExcel_1 finally ] start
				 */

				s(currentComponent = "tFileInputExcel_1");

				/**
				 * [tFileInputExcel_1 finally ] stop
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
		final ExcelFilesFormulas ExcelFilesFormulasClass = new ExcelFilesFormulas();

		int exitCode = ExcelFilesFormulasClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'ExcelFilesFormulas' - Done.");
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
		log.info("TalendJob: 'ExcelFilesFormulas' - Start.");

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
		org.slf4j.MDC.put("_jobRepositoryId", "_ydTGUK2SEe-R9K8WvBgLMQ");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2024-12-09T16:54:08.432858Z");

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
			java.io.InputStream inContext = ExcelFilesFormulas.class.getClassLoader()
					.getResourceAsStream("valpak_poc/excelfilesformulas_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = ExcelFilesFormulas.class.getClassLoader()
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
		log.info("TalendJob: 'ExcelFilesFormulas' - Started.");
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
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : ExcelFilesFormulas");
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
		log.info("TalendJob: 'ExcelFilesFormulas' - Finished - status: " + status + " returnCode: " + returnCode);

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
 * 112773 characters generated by Talend Cloud Data Fabric on the December 9,
 * 2024 at 4:54:08 PM UTC
 ************************************************************************************************/