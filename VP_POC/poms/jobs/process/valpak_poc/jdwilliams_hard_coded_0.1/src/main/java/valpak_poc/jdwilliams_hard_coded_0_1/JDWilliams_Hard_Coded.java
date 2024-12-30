
package valpak_poc.jdwilliams_hard_coded_0_1;

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
 * Job: JDWilliams_Hard_Coded Purpose: <br>
 * Description: <br>
 * 
 * @author alex.hicks@qlik.com
 * @version 8.0.1.20241121_1314-patch
 * @status
 */
public class JDWilliams_Hard_Coded implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "JDWilliams_Hard_Coded.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(JDWilliams_Hard_Coded.class);

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

			if (PRODUCT_NUMBER != null) {

				this.setProperty("PRODUCT_NUMBER", PRODUCT_NUMBER.toString());

			}

			if (product_desc != null) {

				this.setProperty("product_desc", product_desc.toString());

			}

			if (SUPPLIER_NUMBER != null) {

				this.setProperty("SUPPLIER_NUMBER", SUPPLIER_NUMBER.toString());

			}

			if (supplier_name != null) {

				this.setProperty("supplier_name", supplier_name.toString());

			}

			if (brand_group != null) {

				this.setProperty("brand_group", brand_group.toString());

			}

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

		public String PRODUCT_NUMBER;

		public String getPRODUCT_NUMBER() {
			return this.PRODUCT_NUMBER;
		}

		public String product_desc;

		public String getProduct_desc() {
			return this.product_desc;
		}

		public String SUPPLIER_NUMBER;

		public String getSUPPLIER_NUMBER() {
			return this.SUPPLIER_NUMBER;
		}

		public String supplier_name;

		public String getSupplier_name() {
			return this.supplier_name;
		}

		public String brand_group;

		public String getBrand_group() {
			return this.brand_group;
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
	private final String jobName = "JDWilliams_Hard_Coded";
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
			"_sRjUgLCtEe-zrp84tBKbig", "0.1");
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
					JDWilliams_Hard_Coded.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(JDWilliams_Hard_Coded.this, new Object[] { e, currentComponent, globalMap });
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

	public void tFilterRow_11_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUniqRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUnite_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_30_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
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

	public void tHashOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashInput_1_error(Exception exception, String errorComponent,
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

	public static class row9Struct implements routines.system.IPersistableRow<row9Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];

		public String PRODUCT_LINE_CODE;

		public String getPRODUCT_LINE_CODE() {
			return this.PRODUCT_LINE_CODE;
		}

		public Boolean PRODUCT_LINE_CODEIsNullable() {
			return true;
		}

		public Boolean PRODUCT_LINE_CODEIsKey() {
			return false;
		}

		public Integer PRODUCT_LINE_CODELength() {
			return null;
		}

		public Integer PRODUCT_LINE_CODEPrecision() {
			return null;
		}

		public String PRODUCT_LINE_CODEDefault() {

			return null;

		}

		public String PRODUCT_LINE_CODEComment() {

			return "";

		}

		public String PRODUCT_LINE_CODEPattern() {

			return "";

		}

		public String PRODUCT_LINE_CODEOriginalDbColumnName() {

			return "PRODUCT_LINE_CODE";

		}

		public String calender_month;

		public String getCalender_month() {
			return this.calender_month;
		}

		public Boolean calender_monthIsNullable() {
			return true;
		}

		public Boolean calender_monthIsKey() {
			return false;
		}

		public Integer calender_monthLength() {
			return null;
		}

		public Integer calender_monthPrecision() {
			return null;
		}

		public String calender_monthDefault() {

			return null;

		}

		public String calender_monthComment() {

			return "";

		}

		public String calender_monthPattern() {

			return "";

		}

		public String calender_monthOriginalDbColumnName() {

			return "calender_month";

		}

		public String Product_Code;

		public String getProduct_Code() {
			return this.Product_Code;
		}

		public Boolean Product_CodeIsNullable() {
			return true;
		}

		public Boolean Product_CodeIsKey() {
			return false;
		}

		public Integer Product_CodeLength() {
			return null;
		}

		public Integer Product_CodePrecision() {
			return null;
		}

		public String Product_CodeDefault() {

			return null;

		}

		public String Product_CodeComment() {

			return "";

		}

		public String Product_CodePattern() {

			return "";

		}

		public String Product_CodeOriginalDbColumnName() {

			return "Product_Code";

		}

		public String Product_Name;

		public String getProduct_Name() {
			return this.Product_Name;
		}

		public Boolean Product_NameIsNullable() {
			return true;
		}

		public Boolean Product_NameIsKey() {
			return false;
		}

		public Integer Product_NameLength() {
			return null;
		}

		public Integer Product_NamePrecision() {
			return null;
		}

		public String Product_NameDefault() {

			return null;

		}

		public String Product_NameComment() {

			return "";

		}

		public String Product_NamePattern() {

			return "";

		}

		public String Product_NameOriginalDbColumnName() {

			return "Product_Name";

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

		public String import_flag;

		public String getImport_flag() {
			return this.import_flag;
		}

		public Boolean import_flagIsNullable() {
			return true;
		}

		public Boolean import_flagIsKey() {
			return false;
		}

		public Integer import_flagLength() {
			return null;
		}

		public Integer import_flagPrecision() {
			return null;
		}

		public String import_flagDefault() {

			return null;

		}

		public String import_flagComment() {

			return "";

		}

		public String import_flagPattern() {

			return "";

		}

		public String import_flagOriginalDbColumnName() {

			return "import_flag";

		}

		public String export_flag;

		public String getExport_flag() {
			return this.export_flag;
		}

		public Boolean export_flagIsNullable() {
			return true;
		}

		public Boolean export_flagIsKey() {
			return false;
		}

		public Integer export_flagLength() {
			return null;
		}

		public Integer export_flagPrecision() {
			return null;
		}

		public String export_flagDefault() {

			return null;

		}

		public String export_flagComment() {

			return "";

		}

		public String export_flagPattern() {

			return "";

		}

		public String export_flagOriginalDbColumnName() {

			return "export_flag";

		}

		public String supplier_code;

		public String getSupplier_code() {
			return this.supplier_code;
		}

		public Boolean supplier_codeIsNullable() {
			return true;
		}

		public Boolean supplier_codeIsKey() {
			return false;
		}

		public Integer supplier_codeLength() {
			return null;
		}

		public Integer supplier_codePrecision() {
			return null;
		}

		public String supplier_codeDefault() {

			return null;

		}

		public String supplier_codeComment() {

			return "";

		}

		public String supplier_codePattern() {

			return "";

		}

		public String supplier_codeOriginalDbColumnName() {

			return "supplier_code";

		}

		public String supplier_name;

		public String getSupplier_name() {
			return this.supplier_name;
		}

		public Boolean supplier_nameIsNullable() {
			return true;
		}

		public Boolean supplier_nameIsKey() {
			return false;
		}

		public Integer supplier_nameLength() {
			return null;
		}

		public Integer supplier_namePrecision() {
			return null;
		}

		public String supplier_nameDefault() {

			return null;

		}

		public String supplier_nameComment() {

			return "";

		}

		public String supplier_namePattern() {

			return "";

		}

		public String supplier_nameOriginalDbColumnName() {

			return "supplier_name";

		}

		public String SUPPLIER_REF_NO;

		public String getSUPPLIER_REF_NO() {
			return this.SUPPLIER_REF_NO;
		}

		public Boolean SUPPLIER_REF_NOIsNullable() {
			return true;
		}

		public Boolean SUPPLIER_REF_NOIsKey() {
			return false;
		}

		public Integer SUPPLIER_REF_NOLength() {
			return null;
		}

		public Integer SUPPLIER_REF_NOPrecision() {
			return null;
		}

		public String SUPPLIER_REF_NODefault() {

			return null;

		}

		public String SUPPLIER_REF_NOComment() {

			return "";

		}

		public String SUPPLIER_REF_NOPattern() {

			return "";

		}

		public String SUPPLIER_REF_NOOriginalDbColumnName() {

			return "SUPPLIER_REF_NO";

		}

		public String brand_group;

		public String getBrand_group() {
			return this.brand_group;
		}

		public Boolean brand_groupIsNullable() {
			return true;
		}

		public Boolean brand_groupIsKey() {
			return false;
		}

		public Integer brand_groupLength() {
			return null;
		}

		public Integer brand_groupPrecision() {
			return null;
		}

		public String brand_groupDefault() {

			return null;

		}

		public String brand_groupComment() {

			return "";

		}

		public String brand_groupPattern() {

			return "";

		}

		public String brand_groupOriginalDbColumnName() {

			return "brand_group";

		}

		public String Nation_Sent;

		public String getNation_Sent() {
			return this.Nation_Sent;
		}

		public Boolean Nation_SentIsNullable() {
			return true;
		}

		public Boolean Nation_SentIsKey() {
			return false;
		}

		public Integer Nation_SentLength() {
			return null;
		}

		public Integer Nation_SentPrecision() {
			return null;
		}

		public String Nation_SentDefault() {

			return null;

		}

		public String Nation_SentComment() {

			return "";

		}

		public String Nation_SentPattern() {

			return "";

		}

		public String Nation_SentOriginalDbColumnName() {

			return "Nation_Sent";

		}

		public Integer net_desp_units;

		public Integer getNet_desp_units() {
			return this.net_desp_units;
		}

		public Boolean net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean net_desp_unitsIsKey() {
			return false;
		}

		public Integer net_desp_unitsLength() {
			return null;
		}

		public Integer net_desp_unitsPrecision() {
			return null;
		}

		public String net_desp_unitsDefault() {

			return null;

		}

		public String net_desp_unitsComment() {

			return "";

		}

		public String net_desp_unitsPattern() {

			return "";

		}

		public String net_desp_unitsOriginalDbColumnName() {

			return "net_desp_units";

		}

		public Integer uk_net_desp_units;

		public Integer getUk_net_desp_units() {
			return this.uk_net_desp_units;
		}

		public Boolean uk_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean uk_net_desp_unitsIsKey() {
			return false;
		}

		public Integer uk_net_desp_unitsLength() {
			return null;
		}

		public Integer uk_net_desp_unitsPrecision() {
			return null;
		}

		public String uk_net_desp_unitsDefault() {

			return null;

		}

		public String uk_net_desp_unitsComment() {

			return "";

		}

		public String uk_net_desp_unitsPattern() {

			return "";

		}

		public String uk_net_desp_unitsOriginalDbColumnName() {

			return "uk_net_desp_units";

		}

		public Integer int_net_desp_units;

		public Integer getInt_net_desp_units() {
			return this.int_net_desp_units;
		}

		public Boolean int_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean int_net_desp_unitsIsKey() {
			return false;
		}

		public Integer int_net_desp_unitsLength() {
			return null;
		}

		public Integer int_net_desp_unitsPrecision() {
			return null;
		}

		public String int_net_desp_unitsDefault() {

			return null;

		}

		public String int_net_desp_unitsComment() {

			return "";

		}

		public String int_net_desp_unitsPattern() {

			return "";

		}

		public String int_net_desp_unitsOriginalDbColumnName() {

			return "int_net_desp_units";

		}

		public String LocationCode;

		public String getLocationCode() {
			return this.LocationCode;
		}

		public Boolean LocationCodeIsNullable() {
			return true;
		}

		public Boolean LocationCodeIsKey() {
			return false;
		}

		public Integer LocationCodeLength() {
			return null;
		}

		public Integer LocationCodePrecision() {
			return null;
		}

		public String LocationCodeDefault() {

			return null;

		}

		public String LocationCodeComment() {

			return "";

		}

		public String LocationCodePattern() {

			return "";

		}

		public String LocationCodeOriginalDbColumnName() {

			return "LocationCode";

		}

		public String ExportFlag;

		public String getExportFlag() {
			return this.ExportFlag;
		}

		public Boolean ExportFlagIsNullable() {
			return true;
		}

		public Boolean ExportFlagIsKey() {
			return false;
		}

		public Integer ExportFlagLength() {
			return null;
		}

		public Integer ExportFlagPrecision() {
			return null;
		}

		public String ExportFlagDefault() {

			return null;

		}

		public String ExportFlagComment() {

			return "";

		}

		public String ExportFlagPattern() {

			return "";

		}

		public String ExportFlagOriginalDbColumnName() {

			return "ExportFlag";

		}

		public Integer SalesQuantity;

		public Integer getSalesQuantity() {
			return this.SalesQuantity;
		}

		public Boolean SalesQuantityIsNullable() {
			return true;
		}

		public Boolean SalesQuantityIsKey() {
			return false;
		}

		public Integer SalesQuantityLength() {
			return null;
		}

		public Integer SalesQuantityPrecision() {
			return null;
		}

		public String SalesQuantityDefault() {

			return null;

		}

		public String SalesQuantityComment() {

			return "";

		}

		public String SalesQuantityPattern() {

			return "";

		}

		public String SalesQuantityOriginalDbColumnName() {

			return "SalesQuantity";

		}

		public String Audit_BatchName;

		public String getAudit_BatchName() {
			return this.Audit_BatchName;
		}

		public Boolean Audit_BatchNameIsNullable() {
			return true;
		}

		public Boolean Audit_BatchNameIsKey() {
			return false;
		}

		public Integer Audit_BatchNameLength() {
			return null;
		}

		public Integer Audit_BatchNamePrecision() {
			return null;
		}

		public String Audit_BatchNameDefault() {

			return null;

		}

		public String Audit_BatchNameComment() {

			return "";

		}

		public String Audit_BatchNamePattern() {

			return "";

		}

		public String Audit_BatchNameOriginalDbColumnName() {

			return "Audit_BatchName";

		}

		public String Audit_BatchName_Description;

		public String getAudit_BatchName_Description() {
			return this.Audit_BatchName_Description;
		}

		public Boolean Audit_BatchName_DescriptionIsNullable() {
			return true;
		}

		public Boolean Audit_BatchName_DescriptionIsKey() {
			return false;
		}

		public Integer Audit_BatchName_DescriptionLength() {
			return null;
		}

		public Integer Audit_BatchName_DescriptionPrecision() {
			return null;
		}

		public String Audit_BatchName_DescriptionDefault() {

			return null;

		}

		public String Audit_BatchName_DescriptionComment() {

			return "";

		}

		public String Audit_BatchName_DescriptionPattern() {

			return "";

		}

		public String Audit_BatchName_DescriptionOriginalDbColumnName() {

			return "Audit_BatchName_Description";

		}

		public String Audit_BatchNo;

		public String getAudit_BatchNo() {
			return this.Audit_BatchNo;
		}

		public Boolean Audit_BatchNoIsNullable() {
			return true;
		}

		public Boolean Audit_BatchNoIsKey() {
			return false;
		}

		public Integer Audit_BatchNoLength() {
			return null;
		}

		public Integer Audit_BatchNoPrecision() {
			return null;
		}

		public String Audit_BatchNoDefault() {

			return null;

		}

		public String Audit_BatchNoComment() {

			return "";

		}

		public String Audit_BatchNoPattern() {

			return "";

		}

		public String Audit_BatchNoOriginalDbColumnName() {

			return "Audit_BatchNo";

		}

		public String Audit_BatchTimeExecution;

		public String getAudit_BatchTimeExecution() {
			return this.Audit_BatchTimeExecution;
		}

		public Boolean Audit_BatchTimeExecutionIsNullable() {
			return true;
		}

		public Boolean Audit_BatchTimeExecutionIsKey() {
			return false;
		}

		public Integer Audit_BatchTimeExecutionLength() {
			return null;
		}

		public Integer Audit_BatchTimeExecutionPrecision() {
			return null;
		}

		public String Audit_BatchTimeExecutionDefault() {

			return null;

		}

		public String Audit_BatchTimeExecutionComment() {

			return "";

		}

		public String Audit_BatchTimeExecutionPattern() {

			return "";

		}

		public String Audit_BatchTimeExecutionOriginalDbColumnName() {

			return "Audit_BatchTimeExecution";

		}

		public String brand_indicator;

		public String getBrand_indicator() {
			return this.brand_indicator;
		}

		public Boolean brand_indicatorIsNullable() {
			return true;
		}

		public Boolean brand_indicatorIsKey() {
			return false;
		}

		public Integer brand_indicatorLength() {
			return null;
		}

		public Integer brand_indicatorPrecision() {
			return null;
		}

		public String brand_indicatorDefault() {

			return null;

		}

		public String brand_indicatorComment() {

			return "";

		}

		public String brand_indicatorPattern() {

			return "";

		}

		public String brand_indicatorOriginalDbColumnName() {

			return "brand_indicator";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

					this.Audit_BatchName = readString(dis);

					this.Audit_BatchName_Description = readString(dis);

					this.Audit_BatchNo = readString(dis);

					this.Audit_BatchTimeExecution = readString(dis);

					this.brand_indicator = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

					this.Audit_BatchName = readString(dis);

					this.Audit_BatchName_Description = readString(dis);

					this.Audit_BatchNo = readString(dis);

					this.Audit_BatchTimeExecution = readString(dis);

					this.brand_indicator = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

				// String

				writeString(this.Audit_BatchName, dos);

				// String

				writeString(this.Audit_BatchName_Description, dos);

				// String

				writeString(this.Audit_BatchNo, dos);

				// String

				writeString(this.Audit_BatchTimeExecution, dos);

				// String

				writeString(this.brand_indicator, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

				// String

				writeString(this.Audit_BatchName, dos);

				// String

				writeString(this.Audit_BatchName_Description, dos);

				// String

				writeString(this.Audit_BatchNo, dos);

				// String

				writeString(this.Audit_BatchTimeExecution, dos);

				// String

				writeString(this.brand_indicator, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PRODUCT_LINE_CODE=" + PRODUCT_LINE_CODE);
			sb.append(",calender_month=" + calender_month);
			sb.append(",Product_Code=" + Product_Code);
			sb.append(",Product_Name=" + Product_Name);
			sb.append(",division=" + division);
			sb.append(",dept=" + dept);
			sb.append(",rnge=" + rnge);
			sb.append(",import_flag=" + import_flag);
			sb.append(",export_flag=" + export_flag);
			sb.append(",supplier_code=" + supplier_code);
			sb.append(",supplier_name=" + supplier_name);
			sb.append(",SUPPLIER_REF_NO=" + SUPPLIER_REF_NO);
			sb.append(",brand_group=" + brand_group);
			sb.append(",Nation_Sent=" + Nation_Sent);
			sb.append(",net_desp_units=" + String.valueOf(net_desp_units));
			sb.append(",uk_net_desp_units=" + String.valueOf(uk_net_desp_units));
			sb.append(",int_net_desp_units=" + String.valueOf(int_net_desp_units));
			sb.append(",LocationCode=" + LocationCode);
			sb.append(",ExportFlag=" + ExportFlag);
			sb.append(",SalesQuantity=" + String.valueOf(SalesQuantity));
			sb.append(",Audit_BatchName=" + Audit_BatchName);
			sb.append(",Audit_BatchName_Description=" + Audit_BatchName_Description);
			sb.append(",Audit_BatchNo=" + Audit_BatchNo);
			sb.append(",Audit_BatchTimeExecution=" + Audit_BatchTimeExecution);
			sb.append(",brand_indicator=" + brand_indicator);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PRODUCT_LINE_CODE == null) {
				sb.append("<null>");
			} else {
				sb.append(PRODUCT_LINE_CODE);
			}

			sb.append("|");

			if (calender_month == null) {
				sb.append("<null>");
			} else {
				sb.append(calender_month);
			}

			sb.append("|");

			if (Product_Code == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Code);
			}

			sb.append("|");

			if (Product_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Name);
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

			if (import_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(import_flag);
			}

			sb.append("|");

			if (export_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(export_flag);
			}

			sb.append("|");

			if (supplier_code == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_code);
			}

			sb.append("|");

			if (supplier_name == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_name);
			}

			sb.append("|");

			if (SUPPLIER_REF_NO == null) {
				sb.append("<null>");
			} else {
				sb.append(SUPPLIER_REF_NO);
			}

			sb.append("|");

			if (brand_group == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_group);
			}

			sb.append("|");

			if (Nation_Sent == null) {
				sb.append("<null>");
			} else {
				sb.append(Nation_Sent);
			}

			sb.append("|");

			if (net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(net_desp_units);
			}

			sb.append("|");

			if (uk_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(uk_net_desp_units);
			}

			sb.append("|");

			if (int_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(int_net_desp_units);
			}

			sb.append("|");

			if (LocationCode == null) {
				sb.append("<null>");
			} else {
				sb.append(LocationCode);
			}

			sb.append("|");

			if (ExportFlag == null) {
				sb.append("<null>");
			} else {
				sb.append(ExportFlag);
			}

			sb.append("|");

			if (SalesQuantity == null) {
				sb.append("<null>");
			} else {
				sb.append(SalesQuantity);
			}

			sb.append("|");

			if (Audit_BatchName == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchName);
			}

			sb.append("|");

			if (Audit_BatchName_Description == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchName_Description);
			}

			sb.append("|");

			if (Audit_BatchNo == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchNo);
			}

			sb.append("|");

			if (Audit_BatchTimeExecution == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchTimeExecution);
			}

			sb.append("|");

			if (brand_indicator == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_indicator);
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

	public static class out5Struct implements routines.system.IPersistableRow<out5Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];

		public String PRODUCT_LINE_CODE;

		public String getPRODUCT_LINE_CODE() {
			return this.PRODUCT_LINE_CODE;
		}

		public Boolean PRODUCT_LINE_CODEIsNullable() {
			return true;
		}

		public Boolean PRODUCT_LINE_CODEIsKey() {
			return false;
		}

		public Integer PRODUCT_LINE_CODELength() {
			return null;
		}

		public Integer PRODUCT_LINE_CODEPrecision() {
			return null;
		}

		public String PRODUCT_LINE_CODEDefault() {

			return null;

		}

		public String PRODUCT_LINE_CODEComment() {

			return "";

		}

		public String PRODUCT_LINE_CODEPattern() {

			return "";

		}

		public String PRODUCT_LINE_CODEOriginalDbColumnName() {

			return "PRODUCT_LINE_CODE";

		}

		public String calender_month;

		public String getCalender_month() {
			return this.calender_month;
		}

		public Boolean calender_monthIsNullable() {
			return true;
		}

		public Boolean calender_monthIsKey() {
			return false;
		}

		public Integer calender_monthLength() {
			return null;
		}

		public Integer calender_monthPrecision() {
			return null;
		}

		public String calender_monthDefault() {

			return null;

		}

		public String calender_monthComment() {

			return "";

		}

		public String calender_monthPattern() {

			return "";

		}

		public String calender_monthOriginalDbColumnName() {

			return "calender_month";

		}

		public String Product_Code;

		public String getProduct_Code() {
			return this.Product_Code;
		}

		public Boolean Product_CodeIsNullable() {
			return true;
		}

		public Boolean Product_CodeIsKey() {
			return false;
		}

		public Integer Product_CodeLength() {
			return null;
		}

		public Integer Product_CodePrecision() {
			return null;
		}

		public String Product_CodeDefault() {

			return null;

		}

		public String Product_CodeComment() {

			return "";

		}

		public String Product_CodePattern() {

			return "";

		}

		public String Product_CodeOriginalDbColumnName() {

			return "Product_Code";

		}

		public String Product_Name;

		public String getProduct_Name() {
			return this.Product_Name;
		}

		public Boolean Product_NameIsNullable() {
			return true;
		}

		public Boolean Product_NameIsKey() {
			return false;
		}

		public Integer Product_NameLength() {
			return null;
		}

		public Integer Product_NamePrecision() {
			return null;
		}

		public String Product_NameDefault() {

			return null;

		}

		public String Product_NameComment() {

			return "";

		}

		public String Product_NamePattern() {

			return "";

		}

		public String Product_NameOriginalDbColumnName() {

			return "Product_Name";

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

		public String import_flag;

		public String getImport_flag() {
			return this.import_flag;
		}

		public Boolean import_flagIsNullable() {
			return true;
		}

		public Boolean import_flagIsKey() {
			return false;
		}

		public Integer import_flagLength() {
			return null;
		}

		public Integer import_flagPrecision() {
			return null;
		}

		public String import_flagDefault() {

			return null;

		}

		public String import_flagComment() {

			return "";

		}

		public String import_flagPattern() {

			return "";

		}

		public String import_flagOriginalDbColumnName() {

			return "import_flag";

		}

		public String export_flag;

		public String getExport_flag() {
			return this.export_flag;
		}

		public Boolean export_flagIsNullable() {
			return true;
		}

		public Boolean export_flagIsKey() {
			return false;
		}

		public Integer export_flagLength() {
			return null;
		}

		public Integer export_flagPrecision() {
			return null;
		}

		public String export_flagDefault() {

			return null;

		}

		public String export_flagComment() {

			return "";

		}

		public String export_flagPattern() {

			return "";

		}

		public String export_flagOriginalDbColumnName() {

			return "export_flag";

		}

		public String supplier_code;

		public String getSupplier_code() {
			return this.supplier_code;
		}

		public Boolean supplier_codeIsNullable() {
			return true;
		}

		public Boolean supplier_codeIsKey() {
			return false;
		}

		public Integer supplier_codeLength() {
			return null;
		}

		public Integer supplier_codePrecision() {
			return null;
		}

		public String supplier_codeDefault() {

			return null;

		}

		public String supplier_codeComment() {

			return "";

		}

		public String supplier_codePattern() {

			return "";

		}

		public String supplier_codeOriginalDbColumnName() {

			return "supplier_code";

		}

		public String supplier_name;

		public String getSupplier_name() {
			return this.supplier_name;
		}

		public Boolean supplier_nameIsNullable() {
			return true;
		}

		public Boolean supplier_nameIsKey() {
			return false;
		}

		public Integer supplier_nameLength() {
			return null;
		}

		public Integer supplier_namePrecision() {
			return null;
		}

		public String supplier_nameDefault() {

			return null;

		}

		public String supplier_nameComment() {

			return "";

		}

		public String supplier_namePattern() {

			return "";

		}

		public String supplier_nameOriginalDbColumnName() {

			return "supplier_name";

		}

		public String SUPPLIER_REF_NO;

		public String getSUPPLIER_REF_NO() {
			return this.SUPPLIER_REF_NO;
		}

		public Boolean SUPPLIER_REF_NOIsNullable() {
			return true;
		}

		public Boolean SUPPLIER_REF_NOIsKey() {
			return false;
		}

		public Integer SUPPLIER_REF_NOLength() {
			return null;
		}

		public Integer SUPPLIER_REF_NOPrecision() {
			return null;
		}

		public String SUPPLIER_REF_NODefault() {

			return null;

		}

		public String SUPPLIER_REF_NOComment() {

			return "";

		}

		public String SUPPLIER_REF_NOPattern() {

			return "";

		}

		public String SUPPLIER_REF_NOOriginalDbColumnName() {

			return "SUPPLIER_REF_NO";

		}

		public String brand_group;

		public String getBrand_group() {
			return this.brand_group;
		}

		public Boolean brand_groupIsNullable() {
			return true;
		}

		public Boolean brand_groupIsKey() {
			return false;
		}

		public Integer brand_groupLength() {
			return null;
		}

		public Integer brand_groupPrecision() {
			return null;
		}

		public String brand_groupDefault() {

			return null;

		}

		public String brand_groupComment() {

			return "";

		}

		public String brand_groupPattern() {

			return "";

		}

		public String brand_groupOriginalDbColumnName() {

			return "brand_group";

		}

		public String Nation_Sent;

		public String getNation_Sent() {
			return this.Nation_Sent;
		}

		public Boolean Nation_SentIsNullable() {
			return true;
		}

		public Boolean Nation_SentIsKey() {
			return false;
		}

		public Integer Nation_SentLength() {
			return null;
		}

		public Integer Nation_SentPrecision() {
			return null;
		}

		public String Nation_SentDefault() {

			return null;

		}

		public String Nation_SentComment() {

			return "";

		}

		public String Nation_SentPattern() {

			return "";

		}

		public String Nation_SentOriginalDbColumnName() {

			return "Nation_Sent";

		}

		public Integer net_desp_units;

		public Integer getNet_desp_units() {
			return this.net_desp_units;
		}

		public Boolean net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean net_desp_unitsIsKey() {
			return false;
		}

		public Integer net_desp_unitsLength() {
			return null;
		}

		public Integer net_desp_unitsPrecision() {
			return null;
		}

		public String net_desp_unitsDefault() {

			return null;

		}

		public String net_desp_unitsComment() {

			return "";

		}

		public String net_desp_unitsPattern() {

			return "";

		}

		public String net_desp_unitsOriginalDbColumnName() {

			return "net_desp_units";

		}

		public Integer uk_net_desp_units;

		public Integer getUk_net_desp_units() {
			return this.uk_net_desp_units;
		}

		public Boolean uk_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean uk_net_desp_unitsIsKey() {
			return false;
		}

		public Integer uk_net_desp_unitsLength() {
			return null;
		}

		public Integer uk_net_desp_unitsPrecision() {
			return null;
		}

		public String uk_net_desp_unitsDefault() {

			return null;

		}

		public String uk_net_desp_unitsComment() {

			return "";

		}

		public String uk_net_desp_unitsPattern() {

			return "";

		}

		public String uk_net_desp_unitsOriginalDbColumnName() {

			return "uk_net_desp_units";

		}

		public Integer int_net_desp_units;

		public Integer getInt_net_desp_units() {
			return this.int_net_desp_units;
		}

		public Boolean int_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean int_net_desp_unitsIsKey() {
			return false;
		}

		public Integer int_net_desp_unitsLength() {
			return null;
		}

		public Integer int_net_desp_unitsPrecision() {
			return null;
		}

		public String int_net_desp_unitsDefault() {

			return null;

		}

		public String int_net_desp_unitsComment() {

			return "";

		}

		public String int_net_desp_unitsPattern() {

			return "";

		}

		public String int_net_desp_unitsOriginalDbColumnName() {

			return "int_net_desp_units";

		}

		public String LocationCode;

		public String getLocationCode() {
			return this.LocationCode;
		}

		public Boolean LocationCodeIsNullable() {
			return true;
		}

		public Boolean LocationCodeIsKey() {
			return false;
		}

		public Integer LocationCodeLength() {
			return null;
		}

		public Integer LocationCodePrecision() {
			return null;
		}

		public String LocationCodeDefault() {

			return null;

		}

		public String LocationCodeComment() {

			return "";

		}

		public String LocationCodePattern() {

			return "";

		}

		public String LocationCodeOriginalDbColumnName() {

			return "LocationCode";

		}

		public String ExportFlag;

		public String getExportFlag() {
			return this.ExportFlag;
		}

		public Boolean ExportFlagIsNullable() {
			return true;
		}

		public Boolean ExportFlagIsKey() {
			return false;
		}

		public Integer ExportFlagLength() {
			return null;
		}

		public Integer ExportFlagPrecision() {
			return null;
		}

		public String ExportFlagDefault() {

			return null;

		}

		public String ExportFlagComment() {

			return "";

		}

		public String ExportFlagPattern() {

			return "";

		}

		public String ExportFlagOriginalDbColumnName() {

			return "ExportFlag";

		}

		public Integer SalesQuantity;

		public Integer getSalesQuantity() {
			return this.SalesQuantity;
		}

		public Boolean SalesQuantityIsNullable() {
			return true;
		}

		public Boolean SalesQuantityIsKey() {
			return false;
		}

		public Integer SalesQuantityLength() {
			return null;
		}

		public Integer SalesQuantityPrecision() {
			return null;
		}

		public String SalesQuantityDefault() {

			return null;

		}

		public String SalesQuantityComment() {

			return "";

		}

		public String SalesQuantityPattern() {

			return "";

		}

		public String SalesQuantityOriginalDbColumnName() {

			return "SalesQuantity";

		}

		public String Audit_BatchName;

		public String getAudit_BatchName() {
			return this.Audit_BatchName;
		}

		public Boolean Audit_BatchNameIsNullable() {
			return true;
		}

		public Boolean Audit_BatchNameIsKey() {
			return false;
		}

		public Integer Audit_BatchNameLength() {
			return null;
		}

		public Integer Audit_BatchNamePrecision() {
			return null;
		}

		public String Audit_BatchNameDefault() {

			return null;

		}

		public String Audit_BatchNameComment() {

			return "";

		}

		public String Audit_BatchNamePattern() {

			return "";

		}

		public String Audit_BatchNameOriginalDbColumnName() {

			return "Audit_BatchName";

		}

		public String Audit_BatchName_Description;

		public String getAudit_BatchName_Description() {
			return this.Audit_BatchName_Description;
		}

		public Boolean Audit_BatchName_DescriptionIsNullable() {
			return true;
		}

		public Boolean Audit_BatchName_DescriptionIsKey() {
			return false;
		}

		public Integer Audit_BatchName_DescriptionLength() {
			return null;
		}

		public Integer Audit_BatchName_DescriptionPrecision() {
			return null;
		}

		public String Audit_BatchName_DescriptionDefault() {

			return null;

		}

		public String Audit_BatchName_DescriptionComment() {

			return "";

		}

		public String Audit_BatchName_DescriptionPattern() {

			return "";

		}

		public String Audit_BatchName_DescriptionOriginalDbColumnName() {

			return "Audit_BatchName_Description";

		}

		public String Audit_BatchNo;

		public String getAudit_BatchNo() {
			return this.Audit_BatchNo;
		}

		public Boolean Audit_BatchNoIsNullable() {
			return true;
		}

		public Boolean Audit_BatchNoIsKey() {
			return false;
		}

		public Integer Audit_BatchNoLength() {
			return null;
		}

		public Integer Audit_BatchNoPrecision() {
			return null;
		}

		public String Audit_BatchNoDefault() {

			return null;

		}

		public String Audit_BatchNoComment() {

			return "";

		}

		public String Audit_BatchNoPattern() {

			return "";

		}

		public String Audit_BatchNoOriginalDbColumnName() {

			return "Audit_BatchNo";

		}

		public String Audit_BatchTimeExecution;

		public String getAudit_BatchTimeExecution() {
			return this.Audit_BatchTimeExecution;
		}

		public Boolean Audit_BatchTimeExecutionIsNullable() {
			return true;
		}

		public Boolean Audit_BatchTimeExecutionIsKey() {
			return false;
		}

		public Integer Audit_BatchTimeExecutionLength() {
			return null;
		}

		public Integer Audit_BatchTimeExecutionPrecision() {
			return null;
		}

		public String Audit_BatchTimeExecutionDefault() {

			return null;

		}

		public String Audit_BatchTimeExecutionComment() {

			return "";

		}

		public String Audit_BatchTimeExecutionPattern() {

			return "";

		}

		public String Audit_BatchTimeExecutionOriginalDbColumnName() {

			return "Audit_BatchTimeExecution";

		}

		public String brand_indicator;

		public String getBrand_indicator() {
			return this.brand_indicator;
		}

		public Boolean brand_indicatorIsNullable() {
			return true;
		}

		public Boolean brand_indicatorIsKey() {
			return false;
		}

		public Integer brand_indicatorLength() {
			return null;
		}

		public Integer brand_indicatorPrecision() {
			return null;
		}

		public String brand_indicatorDefault() {

			return null;

		}

		public String brand_indicatorComment() {

			return "";

		}

		public String brand_indicatorPattern() {

			return "";

		}

		public String brand_indicatorOriginalDbColumnName() {

			return "brand_indicator";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

					this.Audit_BatchName = readString(dis);

					this.Audit_BatchName_Description = readString(dis);

					this.Audit_BatchNo = readString(dis);

					this.Audit_BatchTimeExecution = readString(dis);

					this.brand_indicator = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

					this.Audit_BatchName = readString(dis);

					this.Audit_BatchName_Description = readString(dis);

					this.Audit_BatchNo = readString(dis);

					this.Audit_BatchTimeExecution = readString(dis);

					this.brand_indicator = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

				// String

				writeString(this.Audit_BatchName, dos);

				// String

				writeString(this.Audit_BatchName_Description, dos);

				// String

				writeString(this.Audit_BatchNo, dos);

				// String

				writeString(this.Audit_BatchTimeExecution, dos);

				// String

				writeString(this.brand_indicator, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

				// String

				writeString(this.Audit_BatchName, dos);

				// String

				writeString(this.Audit_BatchName_Description, dos);

				// String

				writeString(this.Audit_BatchNo, dos);

				// String

				writeString(this.Audit_BatchTimeExecution, dos);

				// String

				writeString(this.brand_indicator, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PRODUCT_LINE_CODE=" + PRODUCT_LINE_CODE);
			sb.append(",calender_month=" + calender_month);
			sb.append(",Product_Code=" + Product_Code);
			sb.append(",Product_Name=" + Product_Name);
			sb.append(",division=" + division);
			sb.append(",dept=" + dept);
			sb.append(",rnge=" + rnge);
			sb.append(",import_flag=" + import_flag);
			sb.append(",export_flag=" + export_flag);
			sb.append(",supplier_code=" + supplier_code);
			sb.append(",supplier_name=" + supplier_name);
			sb.append(",SUPPLIER_REF_NO=" + SUPPLIER_REF_NO);
			sb.append(",brand_group=" + brand_group);
			sb.append(",Nation_Sent=" + Nation_Sent);
			sb.append(",net_desp_units=" + String.valueOf(net_desp_units));
			sb.append(",uk_net_desp_units=" + String.valueOf(uk_net_desp_units));
			sb.append(",int_net_desp_units=" + String.valueOf(int_net_desp_units));
			sb.append(",LocationCode=" + LocationCode);
			sb.append(",ExportFlag=" + ExportFlag);
			sb.append(",SalesQuantity=" + String.valueOf(SalesQuantity));
			sb.append(",Audit_BatchName=" + Audit_BatchName);
			sb.append(",Audit_BatchName_Description=" + Audit_BatchName_Description);
			sb.append(",Audit_BatchNo=" + Audit_BatchNo);
			sb.append(",Audit_BatchTimeExecution=" + Audit_BatchTimeExecution);
			sb.append(",brand_indicator=" + brand_indicator);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PRODUCT_LINE_CODE == null) {
				sb.append("<null>");
			} else {
				sb.append(PRODUCT_LINE_CODE);
			}

			sb.append("|");

			if (calender_month == null) {
				sb.append("<null>");
			} else {
				sb.append(calender_month);
			}

			sb.append("|");

			if (Product_Code == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Code);
			}

			sb.append("|");

			if (Product_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Name);
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

			if (import_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(import_flag);
			}

			sb.append("|");

			if (export_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(export_flag);
			}

			sb.append("|");

			if (supplier_code == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_code);
			}

			sb.append("|");

			if (supplier_name == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_name);
			}

			sb.append("|");

			if (SUPPLIER_REF_NO == null) {
				sb.append("<null>");
			} else {
				sb.append(SUPPLIER_REF_NO);
			}

			sb.append("|");

			if (brand_group == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_group);
			}

			sb.append("|");

			if (Nation_Sent == null) {
				sb.append("<null>");
			} else {
				sb.append(Nation_Sent);
			}

			sb.append("|");

			if (net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(net_desp_units);
			}

			sb.append("|");

			if (uk_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(uk_net_desp_units);
			}

			sb.append("|");

			if (int_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(int_net_desp_units);
			}

			sb.append("|");

			if (LocationCode == null) {
				sb.append("<null>");
			} else {
				sb.append(LocationCode);
			}

			sb.append("|");

			if (ExportFlag == null) {
				sb.append("<null>");
			} else {
				sb.append(ExportFlag);
			}

			sb.append("|");

			if (SalesQuantity == null) {
				sb.append("<null>");
			} else {
				sb.append(SalesQuantity);
			}

			sb.append("|");

			if (Audit_BatchName == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchName);
			}

			sb.append("|");

			if (Audit_BatchName_Description == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchName_Description);
			}

			sb.append("|");

			if (Audit_BatchNo == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchNo);
			}

			sb.append("|");

			if (Audit_BatchTimeExecution == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchTimeExecution);
			}

			sb.append("|");

			if (brand_indicator == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_indicator);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out5Struct other) {

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

	public static class row8Struct implements routines.system.IPersistableRow<row8Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];

		public String PRODUCT_LINE_CODE;

		public String getPRODUCT_LINE_CODE() {
			return this.PRODUCT_LINE_CODE;
		}

		public Boolean PRODUCT_LINE_CODEIsNullable() {
			return true;
		}

		public Boolean PRODUCT_LINE_CODEIsKey() {
			return false;
		}

		public Integer PRODUCT_LINE_CODELength() {
			return null;
		}

		public Integer PRODUCT_LINE_CODEPrecision() {
			return null;
		}

		public String PRODUCT_LINE_CODEDefault() {

			return null;

		}

		public String PRODUCT_LINE_CODEComment() {

			return "";

		}

		public String PRODUCT_LINE_CODEPattern() {

			return "";

		}

		public String PRODUCT_LINE_CODEOriginalDbColumnName() {

			return "PRODUCT_LINE_CODE";

		}

		public String calender_month;

		public String getCalender_month() {
			return this.calender_month;
		}

		public Boolean calender_monthIsNullable() {
			return true;
		}

		public Boolean calender_monthIsKey() {
			return false;
		}

		public Integer calender_monthLength() {
			return null;
		}

		public Integer calender_monthPrecision() {
			return null;
		}

		public String calender_monthDefault() {

			return null;

		}

		public String calender_monthComment() {

			return "";

		}

		public String calender_monthPattern() {

			return "";

		}

		public String calender_monthOriginalDbColumnName() {

			return "calender_month";

		}

		public String Product_Code;

		public String getProduct_Code() {
			return this.Product_Code;
		}

		public Boolean Product_CodeIsNullable() {
			return true;
		}

		public Boolean Product_CodeIsKey() {
			return false;
		}

		public Integer Product_CodeLength() {
			return null;
		}

		public Integer Product_CodePrecision() {
			return null;
		}

		public String Product_CodeDefault() {

			return null;

		}

		public String Product_CodeComment() {

			return "";

		}

		public String Product_CodePattern() {

			return "";

		}

		public String Product_CodeOriginalDbColumnName() {

			return "Product_Code";

		}

		public String Product_Name;

		public String getProduct_Name() {
			return this.Product_Name;
		}

		public Boolean Product_NameIsNullable() {
			return true;
		}

		public Boolean Product_NameIsKey() {
			return false;
		}

		public Integer Product_NameLength() {
			return null;
		}

		public Integer Product_NamePrecision() {
			return null;
		}

		public String Product_NameDefault() {

			return null;

		}

		public String Product_NameComment() {

			return "";

		}

		public String Product_NamePattern() {

			return "";

		}

		public String Product_NameOriginalDbColumnName() {

			return "Product_Name";

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

		public String import_flag;

		public String getImport_flag() {
			return this.import_flag;
		}

		public Boolean import_flagIsNullable() {
			return true;
		}

		public Boolean import_flagIsKey() {
			return false;
		}

		public Integer import_flagLength() {
			return null;
		}

		public Integer import_flagPrecision() {
			return null;
		}

		public String import_flagDefault() {

			return null;

		}

		public String import_flagComment() {

			return "";

		}

		public String import_flagPattern() {

			return "";

		}

		public String import_flagOriginalDbColumnName() {

			return "import_flag";

		}

		public String export_flag;

		public String getExport_flag() {
			return this.export_flag;
		}

		public Boolean export_flagIsNullable() {
			return true;
		}

		public Boolean export_flagIsKey() {
			return false;
		}

		public Integer export_flagLength() {
			return null;
		}

		public Integer export_flagPrecision() {
			return null;
		}

		public String export_flagDefault() {

			return null;

		}

		public String export_flagComment() {

			return "";

		}

		public String export_flagPattern() {

			return "";

		}

		public String export_flagOriginalDbColumnName() {

			return "export_flag";

		}

		public String supplier_code;

		public String getSupplier_code() {
			return this.supplier_code;
		}

		public Boolean supplier_codeIsNullable() {
			return true;
		}

		public Boolean supplier_codeIsKey() {
			return false;
		}

		public Integer supplier_codeLength() {
			return null;
		}

		public Integer supplier_codePrecision() {
			return null;
		}

		public String supplier_codeDefault() {

			return null;

		}

		public String supplier_codeComment() {

			return "";

		}

		public String supplier_codePattern() {

			return "";

		}

		public String supplier_codeOriginalDbColumnName() {

			return "supplier_code";

		}

		public String supplier_name;

		public String getSupplier_name() {
			return this.supplier_name;
		}

		public Boolean supplier_nameIsNullable() {
			return true;
		}

		public Boolean supplier_nameIsKey() {
			return false;
		}

		public Integer supplier_nameLength() {
			return null;
		}

		public Integer supplier_namePrecision() {
			return null;
		}

		public String supplier_nameDefault() {

			return null;

		}

		public String supplier_nameComment() {

			return "";

		}

		public String supplier_namePattern() {

			return "";

		}

		public String supplier_nameOriginalDbColumnName() {

			return "supplier_name";

		}

		public String SUPPLIER_REF_NO;

		public String getSUPPLIER_REF_NO() {
			return this.SUPPLIER_REF_NO;
		}

		public Boolean SUPPLIER_REF_NOIsNullable() {
			return true;
		}

		public Boolean SUPPLIER_REF_NOIsKey() {
			return false;
		}

		public Integer SUPPLIER_REF_NOLength() {
			return null;
		}

		public Integer SUPPLIER_REF_NOPrecision() {
			return null;
		}

		public String SUPPLIER_REF_NODefault() {

			return null;

		}

		public String SUPPLIER_REF_NOComment() {

			return "";

		}

		public String SUPPLIER_REF_NOPattern() {

			return "";

		}

		public String SUPPLIER_REF_NOOriginalDbColumnName() {

			return "SUPPLIER_REF_NO";

		}

		public String brand_group;

		public String getBrand_group() {
			return this.brand_group;
		}

		public Boolean brand_groupIsNullable() {
			return true;
		}

		public Boolean brand_groupIsKey() {
			return false;
		}

		public Integer brand_groupLength() {
			return null;
		}

		public Integer brand_groupPrecision() {
			return null;
		}

		public String brand_groupDefault() {

			return null;

		}

		public String brand_groupComment() {

			return "";

		}

		public String brand_groupPattern() {

			return "";

		}

		public String brand_groupOriginalDbColumnName() {

			return "brand_group";

		}

		public String Nation_Sent;

		public String getNation_Sent() {
			return this.Nation_Sent;
		}

		public Boolean Nation_SentIsNullable() {
			return true;
		}

		public Boolean Nation_SentIsKey() {
			return false;
		}

		public Integer Nation_SentLength() {
			return null;
		}

		public Integer Nation_SentPrecision() {
			return null;
		}

		public String Nation_SentDefault() {

			return null;

		}

		public String Nation_SentComment() {

			return "";

		}

		public String Nation_SentPattern() {

			return "";

		}

		public String Nation_SentOriginalDbColumnName() {

			return "Nation_Sent";

		}

		public Integer net_desp_units;

		public Integer getNet_desp_units() {
			return this.net_desp_units;
		}

		public Boolean net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean net_desp_unitsIsKey() {
			return false;
		}

		public Integer net_desp_unitsLength() {
			return null;
		}

		public Integer net_desp_unitsPrecision() {
			return null;
		}

		public String net_desp_unitsDefault() {

			return null;

		}

		public String net_desp_unitsComment() {

			return "";

		}

		public String net_desp_unitsPattern() {

			return "";

		}

		public String net_desp_unitsOriginalDbColumnName() {

			return "net_desp_units";

		}

		public Integer uk_net_desp_units;

		public Integer getUk_net_desp_units() {
			return this.uk_net_desp_units;
		}

		public Boolean uk_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean uk_net_desp_unitsIsKey() {
			return false;
		}

		public Integer uk_net_desp_unitsLength() {
			return null;
		}

		public Integer uk_net_desp_unitsPrecision() {
			return null;
		}

		public String uk_net_desp_unitsDefault() {

			return null;

		}

		public String uk_net_desp_unitsComment() {

			return "";

		}

		public String uk_net_desp_unitsPattern() {

			return "";

		}

		public String uk_net_desp_unitsOriginalDbColumnName() {

			return "uk_net_desp_units";

		}

		public Integer int_net_desp_units;

		public Integer getInt_net_desp_units() {
			return this.int_net_desp_units;
		}

		public Boolean int_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean int_net_desp_unitsIsKey() {
			return false;
		}

		public Integer int_net_desp_unitsLength() {
			return null;
		}

		public Integer int_net_desp_unitsPrecision() {
			return null;
		}

		public String int_net_desp_unitsDefault() {

			return null;

		}

		public String int_net_desp_unitsComment() {

			return "";

		}

		public String int_net_desp_unitsPattern() {

			return "";

		}

		public String int_net_desp_unitsOriginalDbColumnName() {

			return "int_net_desp_units";

		}

		public String LocationCode;

		public String getLocationCode() {
			return this.LocationCode;
		}

		public Boolean LocationCodeIsNullable() {
			return true;
		}

		public Boolean LocationCodeIsKey() {
			return false;
		}

		public Integer LocationCodeLength() {
			return null;
		}

		public Integer LocationCodePrecision() {
			return null;
		}

		public String LocationCodeDefault() {

			return null;

		}

		public String LocationCodeComment() {

			return "";

		}

		public String LocationCodePattern() {

			return "";

		}

		public String LocationCodeOriginalDbColumnName() {

			return "LocationCode";

		}

		public String ExportFlag;

		public String getExportFlag() {
			return this.ExportFlag;
		}

		public Boolean ExportFlagIsNullable() {
			return true;
		}

		public Boolean ExportFlagIsKey() {
			return false;
		}

		public Integer ExportFlagLength() {
			return null;
		}

		public Integer ExportFlagPrecision() {
			return null;
		}

		public String ExportFlagDefault() {

			return null;

		}

		public String ExportFlagComment() {

			return "";

		}

		public String ExportFlagPattern() {

			return "";

		}

		public String ExportFlagOriginalDbColumnName() {

			return "ExportFlag";

		}

		public Integer SalesQuantity;

		public Integer getSalesQuantity() {
			return this.SalesQuantity;
		}

		public Boolean SalesQuantityIsNullable() {
			return true;
		}

		public Boolean SalesQuantityIsKey() {
			return false;
		}

		public Integer SalesQuantityLength() {
			return null;
		}

		public Integer SalesQuantityPrecision() {
			return null;
		}

		public String SalesQuantityDefault() {

			return null;

		}

		public String SalesQuantityComment() {

			return "";

		}

		public String SalesQuantityPattern() {

			return "";

		}

		public String SalesQuantityOriginalDbColumnName() {

			return "SalesQuantity";

		}

		public String Audit_BatchName;

		public String getAudit_BatchName() {
			return this.Audit_BatchName;
		}

		public Boolean Audit_BatchNameIsNullable() {
			return true;
		}

		public Boolean Audit_BatchNameIsKey() {
			return false;
		}

		public Integer Audit_BatchNameLength() {
			return null;
		}

		public Integer Audit_BatchNamePrecision() {
			return null;
		}

		public String Audit_BatchNameDefault() {

			return null;

		}

		public String Audit_BatchNameComment() {

			return "";

		}

		public String Audit_BatchNamePattern() {

			return "";

		}

		public String Audit_BatchNameOriginalDbColumnName() {

			return "Audit_BatchName";

		}

		public String Audit_BatchName_Description;

		public String getAudit_BatchName_Description() {
			return this.Audit_BatchName_Description;
		}

		public Boolean Audit_BatchName_DescriptionIsNullable() {
			return true;
		}

		public Boolean Audit_BatchName_DescriptionIsKey() {
			return false;
		}

		public Integer Audit_BatchName_DescriptionLength() {
			return null;
		}

		public Integer Audit_BatchName_DescriptionPrecision() {
			return null;
		}

		public String Audit_BatchName_DescriptionDefault() {

			return null;

		}

		public String Audit_BatchName_DescriptionComment() {

			return "";

		}

		public String Audit_BatchName_DescriptionPattern() {

			return "";

		}

		public String Audit_BatchName_DescriptionOriginalDbColumnName() {

			return "Audit_BatchName_Description";

		}

		public String Audit_BatchNo;

		public String getAudit_BatchNo() {
			return this.Audit_BatchNo;
		}

		public Boolean Audit_BatchNoIsNullable() {
			return true;
		}

		public Boolean Audit_BatchNoIsKey() {
			return false;
		}

		public Integer Audit_BatchNoLength() {
			return null;
		}

		public Integer Audit_BatchNoPrecision() {
			return null;
		}

		public String Audit_BatchNoDefault() {

			return null;

		}

		public String Audit_BatchNoComment() {

			return "";

		}

		public String Audit_BatchNoPattern() {

			return "";

		}

		public String Audit_BatchNoOriginalDbColumnName() {

			return "Audit_BatchNo";

		}

		public String Audit_BatchTimeExecution;

		public String getAudit_BatchTimeExecution() {
			return this.Audit_BatchTimeExecution;
		}

		public Boolean Audit_BatchTimeExecutionIsNullable() {
			return true;
		}

		public Boolean Audit_BatchTimeExecutionIsKey() {
			return false;
		}

		public Integer Audit_BatchTimeExecutionLength() {
			return null;
		}

		public Integer Audit_BatchTimeExecutionPrecision() {
			return null;
		}

		public String Audit_BatchTimeExecutionDefault() {

			return null;

		}

		public String Audit_BatchTimeExecutionComment() {

			return "";

		}

		public String Audit_BatchTimeExecutionPattern() {

			return "";

		}

		public String Audit_BatchTimeExecutionOriginalDbColumnName() {

			return "Audit_BatchTimeExecution";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

					this.Audit_BatchName = readString(dis);

					this.Audit_BatchName_Description = readString(dis);

					this.Audit_BatchNo = readString(dis);

					this.Audit_BatchTimeExecution = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

					this.Audit_BatchName = readString(dis);

					this.Audit_BatchName_Description = readString(dis);

					this.Audit_BatchNo = readString(dis);

					this.Audit_BatchTimeExecution = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

				// String

				writeString(this.Audit_BatchName, dos);

				// String

				writeString(this.Audit_BatchName_Description, dos);

				// String

				writeString(this.Audit_BatchNo, dos);

				// String

				writeString(this.Audit_BatchTimeExecution, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

				// String

				writeString(this.Audit_BatchName, dos);

				// String

				writeString(this.Audit_BatchName_Description, dos);

				// String

				writeString(this.Audit_BatchNo, dos);

				// String

				writeString(this.Audit_BatchTimeExecution, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PRODUCT_LINE_CODE=" + PRODUCT_LINE_CODE);
			sb.append(",calender_month=" + calender_month);
			sb.append(",Product_Code=" + Product_Code);
			sb.append(",Product_Name=" + Product_Name);
			sb.append(",division=" + division);
			sb.append(",dept=" + dept);
			sb.append(",rnge=" + rnge);
			sb.append(",import_flag=" + import_flag);
			sb.append(",export_flag=" + export_flag);
			sb.append(",supplier_code=" + supplier_code);
			sb.append(",supplier_name=" + supplier_name);
			sb.append(",SUPPLIER_REF_NO=" + SUPPLIER_REF_NO);
			sb.append(",brand_group=" + brand_group);
			sb.append(",Nation_Sent=" + Nation_Sent);
			sb.append(",net_desp_units=" + String.valueOf(net_desp_units));
			sb.append(",uk_net_desp_units=" + String.valueOf(uk_net_desp_units));
			sb.append(",int_net_desp_units=" + String.valueOf(int_net_desp_units));
			sb.append(",LocationCode=" + LocationCode);
			sb.append(",ExportFlag=" + ExportFlag);
			sb.append(",SalesQuantity=" + String.valueOf(SalesQuantity));
			sb.append(",Audit_BatchName=" + Audit_BatchName);
			sb.append(",Audit_BatchName_Description=" + Audit_BatchName_Description);
			sb.append(",Audit_BatchNo=" + Audit_BatchNo);
			sb.append(",Audit_BatchTimeExecution=" + Audit_BatchTimeExecution);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PRODUCT_LINE_CODE == null) {
				sb.append("<null>");
			} else {
				sb.append(PRODUCT_LINE_CODE);
			}

			sb.append("|");

			if (calender_month == null) {
				sb.append("<null>");
			} else {
				sb.append(calender_month);
			}

			sb.append("|");

			if (Product_Code == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Code);
			}

			sb.append("|");

			if (Product_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Name);
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

			if (import_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(import_flag);
			}

			sb.append("|");

			if (export_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(export_flag);
			}

			sb.append("|");

			if (supplier_code == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_code);
			}

			sb.append("|");

			if (supplier_name == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_name);
			}

			sb.append("|");

			if (SUPPLIER_REF_NO == null) {
				sb.append("<null>");
			} else {
				sb.append(SUPPLIER_REF_NO);
			}

			sb.append("|");

			if (brand_group == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_group);
			}

			sb.append("|");

			if (Nation_Sent == null) {
				sb.append("<null>");
			} else {
				sb.append(Nation_Sent);
			}

			sb.append("|");

			if (net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(net_desp_units);
			}

			sb.append("|");

			if (uk_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(uk_net_desp_units);
			}

			sb.append("|");

			if (int_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(int_net_desp_units);
			}

			sb.append("|");

			if (LocationCode == null) {
				sb.append("<null>");
			} else {
				sb.append(LocationCode);
			}

			sb.append("|");

			if (ExportFlag == null) {
				sb.append("<null>");
			} else {
				sb.append(ExportFlag);
			}

			sb.append("|");

			if (SalesQuantity == null) {
				sb.append("<null>");
			} else {
				sb.append(SalesQuantity);
			}

			sb.append("|");

			if (Audit_BatchName == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchName);
			}

			sb.append("|");

			if (Audit_BatchName_Description == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchName_Description);
			}

			sb.append("|");

			if (Audit_BatchNo == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchNo);
			}

			sb.append("|");

			if (Audit_BatchTimeExecution == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchTimeExecution);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row8Struct other) {

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

	public static class out4Struct implements routines.system.IPersistableRow<out4Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];

		public String PRODUCT_LINE_CODE;

		public String getPRODUCT_LINE_CODE() {
			return this.PRODUCT_LINE_CODE;
		}

		public Boolean PRODUCT_LINE_CODEIsNullable() {
			return true;
		}

		public Boolean PRODUCT_LINE_CODEIsKey() {
			return false;
		}

		public Integer PRODUCT_LINE_CODELength() {
			return null;
		}

		public Integer PRODUCT_LINE_CODEPrecision() {
			return null;
		}

		public String PRODUCT_LINE_CODEDefault() {

			return null;

		}

		public String PRODUCT_LINE_CODEComment() {

			return "";

		}

		public String PRODUCT_LINE_CODEPattern() {

			return "";

		}

		public String PRODUCT_LINE_CODEOriginalDbColumnName() {

			return "PRODUCT_LINE_CODE";

		}

		public String calender_month;

		public String getCalender_month() {
			return this.calender_month;
		}

		public Boolean calender_monthIsNullable() {
			return true;
		}

		public Boolean calender_monthIsKey() {
			return false;
		}

		public Integer calender_monthLength() {
			return null;
		}

		public Integer calender_monthPrecision() {
			return null;
		}

		public String calender_monthDefault() {

			return null;

		}

		public String calender_monthComment() {

			return "";

		}

		public String calender_monthPattern() {

			return "";

		}

		public String calender_monthOriginalDbColumnName() {

			return "calender_month";

		}

		public String Product_Code;

		public String getProduct_Code() {
			return this.Product_Code;
		}

		public Boolean Product_CodeIsNullable() {
			return true;
		}

		public Boolean Product_CodeIsKey() {
			return false;
		}

		public Integer Product_CodeLength() {
			return null;
		}

		public Integer Product_CodePrecision() {
			return null;
		}

		public String Product_CodeDefault() {

			return null;

		}

		public String Product_CodeComment() {

			return "";

		}

		public String Product_CodePattern() {

			return "";

		}

		public String Product_CodeOriginalDbColumnName() {

			return "Product_Code";

		}

		public String Product_Name;

		public String getProduct_Name() {
			return this.Product_Name;
		}

		public Boolean Product_NameIsNullable() {
			return true;
		}

		public Boolean Product_NameIsKey() {
			return false;
		}

		public Integer Product_NameLength() {
			return null;
		}

		public Integer Product_NamePrecision() {
			return null;
		}

		public String Product_NameDefault() {

			return null;

		}

		public String Product_NameComment() {

			return "";

		}

		public String Product_NamePattern() {

			return "";

		}

		public String Product_NameOriginalDbColumnName() {

			return "Product_Name";

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

		public String import_flag;

		public String getImport_flag() {
			return this.import_flag;
		}

		public Boolean import_flagIsNullable() {
			return true;
		}

		public Boolean import_flagIsKey() {
			return false;
		}

		public Integer import_flagLength() {
			return null;
		}

		public Integer import_flagPrecision() {
			return null;
		}

		public String import_flagDefault() {

			return null;

		}

		public String import_flagComment() {

			return "";

		}

		public String import_flagPattern() {

			return "";

		}

		public String import_flagOriginalDbColumnName() {

			return "import_flag";

		}

		public String export_flag;

		public String getExport_flag() {
			return this.export_flag;
		}

		public Boolean export_flagIsNullable() {
			return true;
		}

		public Boolean export_flagIsKey() {
			return false;
		}

		public Integer export_flagLength() {
			return null;
		}

		public Integer export_flagPrecision() {
			return null;
		}

		public String export_flagDefault() {

			return null;

		}

		public String export_flagComment() {

			return "";

		}

		public String export_flagPattern() {

			return "";

		}

		public String export_flagOriginalDbColumnName() {

			return "export_flag";

		}

		public String supplier_code;

		public String getSupplier_code() {
			return this.supplier_code;
		}

		public Boolean supplier_codeIsNullable() {
			return true;
		}

		public Boolean supplier_codeIsKey() {
			return false;
		}

		public Integer supplier_codeLength() {
			return null;
		}

		public Integer supplier_codePrecision() {
			return null;
		}

		public String supplier_codeDefault() {

			return null;

		}

		public String supplier_codeComment() {

			return "";

		}

		public String supplier_codePattern() {

			return "";

		}

		public String supplier_codeOriginalDbColumnName() {

			return "supplier_code";

		}

		public String supplier_name;

		public String getSupplier_name() {
			return this.supplier_name;
		}

		public Boolean supplier_nameIsNullable() {
			return true;
		}

		public Boolean supplier_nameIsKey() {
			return false;
		}

		public Integer supplier_nameLength() {
			return null;
		}

		public Integer supplier_namePrecision() {
			return null;
		}

		public String supplier_nameDefault() {

			return null;

		}

		public String supplier_nameComment() {

			return "";

		}

		public String supplier_namePattern() {

			return "";

		}

		public String supplier_nameOriginalDbColumnName() {

			return "supplier_name";

		}

		public String SUPPLIER_REF_NO;

		public String getSUPPLIER_REF_NO() {
			return this.SUPPLIER_REF_NO;
		}

		public Boolean SUPPLIER_REF_NOIsNullable() {
			return true;
		}

		public Boolean SUPPLIER_REF_NOIsKey() {
			return false;
		}

		public Integer SUPPLIER_REF_NOLength() {
			return null;
		}

		public Integer SUPPLIER_REF_NOPrecision() {
			return null;
		}

		public String SUPPLIER_REF_NODefault() {

			return null;

		}

		public String SUPPLIER_REF_NOComment() {

			return "";

		}

		public String SUPPLIER_REF_NOPattern() {

			return "";

		}

		public String SUPPLIER_REF_NOOriginalDbColumnName() {

			return "SUPPLIER_REF_NO";

		}

		public String brand_group;

		public String getBrand_group() {
			return this.brand_group;
		}

		public Boolean brand_groupIsNullable() {
			return true;
		}

		public Boolean brand_groupIsKey() {
			return false;
		}

		public Integer brand_groupLength() {
			return null;
		}

		public Integer brand_groupPrecision() {
			return null;
		}

		public String brand_groupDefault() {

			return null;

		}

		public String brand_groupComment() {

			return "";

		}

		public String brand_groupPattern() {

			return "";

		}

		public String brand_groupOriginalDbColumnName() {

			return "brand_group";

		}

		public String Nation_Sent;

		public String getNation_Sent() {
			return this.Nation_Sent;
		}

		public Boolean Nation_SentIsNullable() {
			return true;
		}

		public Boolean Nation_SentIsKey() {
			return false;
		}

		public Integer Nation_SentLength() {
			return null;
		}

		public Integer Nation_SentPrecision() {
			return null;
		}

		public String Nation_SentDefault() {

			return null;

		}

		public String Nation_SentComment() {

			return "";

		}

		public String Nation_SentPattern() {

			return "";

		}

		public String Nation_SentOriginalDbColumnName() {

			return "Nation_Sent";

		}

		public Integer net_desp_units;

		public Integer getNet_desp_units() {
			return this.net_desp_units;
		}

		public Boolean net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean net_desp_unitsIsKey() {
			return false;
		}

		public Integer net_desp_unitsLength() {
			return null;
		}

		public Integer net_desp_unitsPrecision() {
			return null;
		}

		public String net_desp_unitsDefault() {

			return null;

		}

		public String net_desp_unitsComment() {

			return "";

		}

		public String net_desp_unitsPattern() {

			return "";

		}

		public String net_desp_unitsOriginalDbColumnName() {

			return "net_desp_units";

		}

		public Integer uk_net_desp_units;

		public Integer getUk_net_desp_units() {
			return this.uk_net_desp_units;
		}

		public Boolean uk_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean uk_net_desp_unitsIsKey() {
			return false;
		}

		public Integer uk_net_desp_unitsLength() {
			return null;
		}

		public Integer uk_net_desp_unitsPrecision() {
			return null;
		}

		public String uk_net_desp_unitsDefault() {

			return null;

		}

		public String uk_net_desp_unitsComment() {

			return "";

		}

		public String uk_net_desp_unitsPattern() {

			return "";

		}

		public String uk_net_desp_unitsOriginalDbColumnName() {

			return "uk_net_desp_units";

		}

		public Integer int_net_desp_units;

		public Integer getInt_net_desp_units() {
			return this.int_net_desp_units;
		}

		public Boolean int_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean int_net_desp_unitsIsKey() {
			return false;
		}

		public Integer int_net_desp_unitsLength() {
			return null;
		}

		public Integer int_net_desp_unitsPrecision() {
			return null;
		}

		public String int_net_desp_unitsDefault() {

			return null;

		}

		public String int_net_desp_unitsComment() {

			return "";

		}

		public String int_net_desp_unitsPattern() {

			return "";

		}

		public String int_net_desp_unitsOriginalDbColumnName() {

			return "int_net_desp_units";

		}

		public String LocationCode;

		public String getLocationCode() {
			return this.LocationCode;
		}

		public Boolean LocationCodeIsNullable() {
			return true;
		}

		public Boolean LocationCodeIsKey() {
			return false;
		}

		public Integer LocationCodeLength() {
			return null;
		}

		public Integer LocationCodePrecision() {
			return null;
		}

		public String LocationCodeDefault() {

			return null;

		}

		public String LocationCodeComment() {

			return "";

		}

		public String LocationCodePattern() {

			return "";

		}

		public String LocationCodeOriginalDbColumnName() {

			return "LocationCode";

		}

		public String ExportFlag;

		public String getExportFlag() {
			return this.ExportFlag;
		}

		public Boolean ExportFlagIsNullable() {
			return true;
		}

		public Boolean ExportFlagIsKey() {
			return false;
		}

		public Integer ExportFlagLength() {
			return null;
		}

		public Integer ExportFlagPrecision() {
			return null;
		}

		public String ExportFlagDefault() {

			return null;

		}

		public String ExportFlagComment() {

			return "";

		}

		public String ExportFlagPattern() {

			return "";

		}

		public String ExportFlagOriginalDbColumnName() {

			return "ExportFlag";

		}

		public Integer SalesQuantity;

		public Integer getSalesQuantity() {
			return this.SalesQuantity;
		}

		public Boolean SalesQuantityIsNullable() {
			return true;
		}

		public Boolean SalesQuantityIsKey() {
			return false;
		}

		public Integer SalesQuantityLength() {
			return null;
		}

		public Integer SalesQuantityPrecision() {
			return null;
		}

		public String SalesQuantityDefault() {

			return null;

		}

		public String SalesQuantityComment() {

			return "";

		}

		public String SalesQuantityPattern() {

			return "";

		}

		public String SalesQuantityOriginalDbColumnName() {

			return "SalesQuantity";

		}

		public String Audit_BatchName;

		public String getAudit_BatchName() {
			return this.Audit_BatchName;
		}

		public Boolean Audit_BatchNameIsNullable() {
			return true;
		}

		public Boolean Audit_BatchNameIsKey() {
			return false;
		}

		public Integer Audit_BatchNameLength() {
			return null;
		}

		public Integer Audit_BatchNamePrecision() {
			return null;
		}

		public String Audit_BatchNameDefault() {

			return null;

		}

		public String Audit_BatchNameComment() {

			return "";

		}

		public String Audit_BatchNamePattern() {

			return "";

		}

		public String Audit_BatchNameOriginalDbColumnName() {

			return "Audit_BatchName";

		}

		public String Audit_BatchName_Description;

		public String getAudit_BatchName_Description() {
			return this.Audit_BatchName_Description;
		}

		public Boolean Audit_BatchName_DescriptionIsNullable() {
			return true;
		}

		public Boolean Audit_BatchName_DescriptionIsKey() {
			return false;
		}

		public Integer Audit_BatchName_DescriptionLength() {
			return null;
		}

		public Integer Audit_BatchName_DescriptionPrecision() {
			return null;
		}

		public String Audit_BatchName_DescriptionDefault() {

			return null;

		}

		public String Audit_BatchName_DescriptionComment() {

			return "";

		}

		public String Audit_BatchName_DescriptionPattern() {

			return "";

		}

		public String Audit_BatchName_DescriptionOriginalDbColumnName() {

			return "Audit_BatchName_Description";

		}

		public String Audit_BatchNo;

		public String getAudit_BatchNo() {
			return this.Audit_BatchNo;
		}

		public Boolean Audit_BatchNoIsNullable() {
			return true;
		}

		public Boolean Audit_BatchNoIsKey() {
			return false;
		}

		public Integer Audit_BatchNoLength() {
			return null;
		}

		public Integer Audit_BatchNoPrecision() {
			return null;
		}

		public String Audit_BatchNoDefault() {

			return null;

		}

		public String Audit_BatchNoComment() {

			return "";

		}

		public String Audit_BatchNoPattern() {

			return "";

		}

		public String Audit_BatchNoOriginalDbColumnName() {

			return "Audit_BatchNo";

		}

		public String Audit_BatchTimeExecution;

		public String getAudit_BatchTimeExecution() {
			return this.Audit_BatchTimeExecution;
		}

		public Boolean Audit_BatchTimeExecutionIsNullable() {
			return true;
		}

		public Boolean Audit_BatchTimeExecutionIsKey() {
			return false;
		}

		public Integer Audit_BatchTimeExecutionLength() {
			return null;
		}

		public Integer Audit_BatchTimeExecutionPrecision() {
			return null;
		}

		public String Audit_BatchTimeExecutionDefault() {

			return null;

		}

		public String Audit_BatchTimeExecutionComment() {

			return "";

		}

		public String Audit_BatchTimeExecutionPattern() {

			return "";

		}

		public String Audit_BatchTimeExecutionOriginalDbColumnName() {

			return "Audit_BatchTimeExecution";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

					this.Audit_BatchName = readString(dis);

					this.Audit_BatchName_Description = readString(dis);

					this.Audit_BatchNo = readString(dis);

					this.Audit_BatchTimeExecution = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

					this.Audit_BatchName = readString(dis);

					this.Audit_BatchName_Description = readString(dis);

					this.Audit_BatchNo = readString(dis);

					this.Audit_BatchTimeExecution = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

				// String

				writeString(this.Audit_BatchName, dos);

				// String

				writeString(this.Audit_BatchName_Description, dos);

				// String

				writeString(this.Audit_BatchNo, dos);

				// String

				writeString(this.Audit_BatchTimeExecution, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

				// String

				writeString(this.Audit_BatchName, dos);

				// String

				writeString(this.Audit_BatchName_Description, dos);

				// String

				writeString(this.Audit_BatchNo, dos);

				// String

				writeString(this.Audit_BatchTimeExecution, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PRODUCT_LINE_CODE=" + PRODUCT_LINE_CODE);
			sb.append(",calender_month=" + calender_month);
			sb.append(",Product_Code=" + Product_Code);
			sb.append(",Product_Name=" + Product_Name);
			sb.append(",division=" + division);
			sb.append(",dept=" + dept);
			sb.append(",rnge=" + rnge);
			sb.append(",import_flag=" + import_flag);
			sb.append(",export_flag=" + export_flag);
			sb.append(",supplier_code=" + supplier_code);
			sb.append(",supplier_name=" + supplier_name);
			sb.append(",SUPPLIER_REF_NO=" + SUPPLIER_REF_NO);
			sb.append(",brand_group=" + brand_group);
			sb.append(",Nation_Sent=" + Nation_Sent);
			sb.append(",net_desp_units=" + String.valueOf(net_desp_units));
			sb.append(",uk_net_desp_units=" + String.valueOf(uk_net_desp_units));
			sb.append(",int_net_desp_units=" + String.valueOf(int_net_desp_units));
			sb.append(",LocationCode=" + LocationCode);
			sb.append(",ExportFlag=" + ExportFlag);
			sb.append(",SalesQuantity=" + String.valueOf(SalesQuantity));
			sb.append(",Audit_BatchName=" + Audit_BatchName);
			sb.append(",Audit_BatchName_Description=" + Audit_BatchName_Description);
			sb.append(",Audit_BatchNo=" + Audit_BatchNo);
			sb.append(",Audit_BatchTimeExecution=" + Audit_BatchTimeExecution);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PRODUCT_LINE_CODE == null) {
				sb.append("<null>");
			} else {
				sb.append(PRODUCT_LINE_CODE);
			}

			sb.append("|");

			if (calender_month == null) {
				sb.append("<null>");
			} else {
				sb.append(calender_month);
			}

			sb.append("|");

			if (Product_Code == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Code);
			}

			sb.append("|");

			if (Product_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Name);
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

			if (import_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(import_flag);
			}

			sb.append("|");

			if (export_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(export_flag);
			}

			sb.append("|");

			if (supplier_code == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_code);
			}

			sb.append("|");

			if (supplier_name == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_name);
			}

			sb.append("|");

			if (SUPPLIER_REF_NO == null) {
				sb.append("<null>");
			} else {
				sb.append(SUPPLIER_REF_NO);
			}

			sb.append("|");

			if (brand_group == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_group);
			}

			sb.append("|");

			if (Nation_Sent == null) {
				sb.append("<null>");
			} else {
				sb.append(Nation_Sent);
			}

			sb.append("|");

			if (net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(net_desp_units);
			}

			sb.append("|");

			if (uk_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(uk_net_desp_units);
			}

			sb.append("|");

			if (int_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(int_net_desp_units);
			}

			sb.append("|");

			if (LocationCode == null) {
				sb.append("<null>");
			} else {
				sb.append(LocationCode);
			}

			sb.append("|");

			if (ExportFlag == null) {
				sb.append("<null>");
			} else {
				sb.append(ExportFlag);
			}

			sb.append("|");

			if (SalesQuantity == null) {
				sb.append("<null>");
			} else {
				sb.append(SalesQuantity);
			}

			sb.append("|");

			if (Audit_BatchName == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchName);
			}

			sb.append("|");

			if (Audit_BatchName_Description == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchName_Description);
			}

			sb.append("|");

			if (Audit_BatchNo == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchNo);
			}

			sb.append("|");

			if (Audit_BatchTimeExecution == null) {
				sb.append("<null>");
			} else {
				sb.append(Audit_BatchTimeExecution);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out4Struct other) {

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
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];

		public String PRODUCT_LINE_CODE;

		public String getPRODUCT_LINE_CODE() {
			return this.PRODUCT_LINE_CODE;
		}

		public Boolean PRODUCT_LINE_CODEIsNullable() {
			return true;
		}

		public Boolean PRODUCT_LINE_CODEIsKey() {
			return false;
		}

		public Integer PRODUCT_LINE_CODELength() {
			return null;
		}

		public Integer PRODUCT_LINE_CODEPrecision() {
			return null;
		}

		public String PRODUCT_LINE_CODEDefault() {

			return null;

		}

		public String PRODUCT_LINE_CODEComment() {

			return "";

		}

		public String PRODUCT_LINE_CODEPattern() {

			return "";

		}

		public String PRODUCT_LINE_CODEOriginalDbColumnName() {

			return "PRODUCT_LINE_CODE";

		}

		public String calender_month;

		public String getCalender_month() {
			return this.calender_month;
		}

		public Boolean calender_monthIsNullable() {
			return true;
		}

		public Boolean calender_monthIsKey() {
			return false;
		}

		public Integer calender_monthLength() {
			return null;
		}

		public Integer calender_monthPrecision() {
			return null;
		}

		public String calender_monthDefault() {

			return null;

		}

		public String calender_monthComment() {

			return "";

		}

		public String calender_monthPattern() {

			return "";

		}

		public String calender_monthOriginalDbColumnName() {

			return "calender_month";

		}

		public String Product_Code;

		public String getProduct_Code() {
			return this.Product_Code;
		}

		public Boolean Product_CodeIsNullable() {
			return true;
		}

		public Boolean Product_CodeIsKey() {
			return false;
		}

		public Integer Product_CodeLength() {
			return null;
		}

		public Integer Product_CodePrecision() {
			return null;
		}

		public String Product_CodeDefault() {

			return null;

		}

		public String Product_CodeComment() {

			return "";

		}

		public String Product_CodePattern() {

			return "";

		}

		public String Product_CodeOriginalDbColumnName() {

			return "Product_Code";

		}

		public String Product_Name;

		public String getProduct_Name() {
			return this.Product_Name;
		}

		public Boolean Product_NameIsNullable() {
			return true;
		}

		public Boolean Product_NameIsKey() {
			return false;
		}

		public Integer Product_NameLength() {
			return null;
		}

		public Integer Product_NamePrecision() {
			return null;
		}

		public String Product_NameDefault() {

			return null;

		}

		public String Product_NameComment() {

			return "";

		}

		public String Product_NamePattern() {

			return "";

		}

		public String Product_NameOriginalDbColumnName() {

			return "Product_Name";

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

		public String import_flag;

		public String getImport_flag() {
			return this.import_flag;
		}

		public Boolean import_flagIsNullable() {
			return true;
		}

		public Boolean import_flagIsKey() {
			return false;
		}

		public Integer import_flagLength() {
			return null;
		}

		public Integer import_flagPrecision() {
			return null;
		}

		public String import_flagDefault() {

			return null;

		}

		public String import_flagComment() {

			return "";

		}

		public String import_flagPattern() {

			return "";

		}

		public String import_flagOriginalDbColumnName() {

			return "import_flag";

		}

		public String export_flag;

		public String getExport_flag() {
			return this.export_flag;
		}

		public Boolean export_flagIsNullable() {
			return true;
		}

		public Boolean export_flagIsKey() {
			return false;
		}

		public Integer export_flagLength() {
			return null;
		}

		public Integer export_flagPrecision() {
			return null;
		}

		public String export_flagDefault() {

			return null;

		}

		public String export_flagComment() {

			return "";

		}

		public String export_flagPattern() {

			return "";

		}

		public String export_flagOriginalDbColumnName() {

			return "export_flag";

		}

		public String supplier_code;

		public String getSupplier_code() {
			return this.supplier_code;
		}

		public Boolean supplier_codeIsNullable() {
			return true;
		}

		public Boolean supplier_codeIsKey() {
			return false;
		}

		public Integer supplier_codeLength() {
			return null;
		}

		public Integer supplier_codePrecision() {
			return null;
		}

		public String supplier_codeDefault() {

			return null;

		}

		public String supplier_codeComment() {

			return "";

		}

		public String supplier_codePattern() {

			return "";

		}

		public String supplier_codeOriginalDbColumnName() {

			return "supplier_code";

		}

		public String supplier_name;

		public String getSupplier_name() {
			return this.supplier_name;
		}

		public Boolean supplier_nameIsNullable() {
			return true;
		}

		public Boolean supplier_nameIsKey() {
			return false;
		}

		public Integer supplier_nameLength() {
			return null;
		}

		public Integer supplier_namePrecision() {
			return null;
		}

		public String supplier_nameDefault() {

			return null;

		}

		public String supplier_nameComment() {

			return "";

		}

		public String supplier_namePattern() {

			return "";

		}

		public String supplier_nameOriginalDbColumnName() {

			return "supplier_name";

		}

		public String SUPPLIER_REF_NO;

		public String getSUPPLIER_REF_NO() {
			return this.SUPPLIER_REF_NO;
		}

		public Boolean SUPPLIER_REF_NOIsNullable() {
			return true;
		}

		public Boolean SUPPLIER_REF_NOIsKey() {
			return false;
		}

		public Integer SUPPLIER_REF_NOLength() {
			return null;
		}

		public Integer SUPPLIER_REF_NOPrecision() {
			return null;
		}

		public String SUPPLIER_REF_NODefault() {

			return null;

		}

		public String SUPPLIER_REF_NOComment() {

			return "";

		}

		public String SUPPLIER_REF_NOPattern() {

			return "";

		}

		public String SUPPLIER_REF_NOOriginalDbColumnName() {

			return "SUPPLIER_REF_NO";

		}

		public String brand_group;

		public String getBrand_group() {
			return this.brand_group;
		}

		public Boolean brand_groupIsNullable() {
			return true;
		}

		public Boolean brand_groupIsKey() {
			return false;
		}

		public Integer brand_groupLength() {
			return null;
		}

		public Integer brand_groupPrecision() {
			return null;
		}

		public String brand_groupDefault() {

			return null;

		}

		public String brand_groupComment() {

			return "";

		}

		public String brand_groupPattern() {

			return "";

		}

		public String brand_groupOriginalDbColumnName() {

			return "brand_group";

		}

		public String Nation_Sent;

		public String getNation_Sent() {
			return this.Nation_Sent;
		}

		public Boolean Nation_SentIsNullable() {
			return true;
		}

		public Boolean Nation_SentIsKey() {
			return false;
		}

		public Integer Nation_SentLength() {
			return null;
		}

		public Integer Nation_SentPrecision() {
			return null;
		}

		public String Nation_SentDefault() {

			return null;

		}

		public String Nation_SentComment() {

			return "";

		}

		public String Nation_SentPattern() {

			return "";

		}

		public String Nation_SentOriginalDbColumnName() {

			return "Nation_Sent";

		}

		public Integer net_desp_units;

		public Integer getNet_desp_units() {
			return this.net_desp_units;
		}

		public Boolean net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean net_desp_unitsIsKey() {
			return false;
		}

		public Integer net_desp_unitsLength() {
			return null;
		}

		public Integer net_desp_unitsPrecision() {
			return null;
		}

		public String net_desp_unitsDefault() {

			return null;

		}

		public String net_desp_unitsComment() {

			return "";

		}

		public String net_desp_unitsPattern() {

			return "";

		}

		public String net_desp_unitsOriginalDbColumnName() {

			return "net_desp_units";

		}

		public Integer uk_net_desp_units;

		public Integer getUk_net_desp_units() {
			return this.uk_net_desp_units;
		}

		public Boolean uk_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean uk_net_desp_unitsIsKey() {
			return false;
		}

		public Integer uk_net_desp_unitsLength() {
			return null;
		}

		public Integer uk_net_desp_unitsPrecision() {
			return null;
		}

		public String uk_net_desp_unitsDefault() {

			return null;

		}

		public String uk_net_desp_unitsComment() {

			return "";

		}

		public String uk_net_desp_unitsPattern() {

			return "";

		}

		public String uk_net_desp_unitsOriginalDbColumnName() {

			return "uk_net_desp_units";

		}

		public Integer int_net_desp_units;

		public Integer getInt_net_desp_units() {
			return this.int_net_desp_units;
		}

		public Boolean int_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean int_net_desp_unitsIsKey() {
			return false;
		}

		public Integer int_net_desp_unitsLength() {
			return null;
		}

		public Integer int_net_desp_unitsPrecision() {
			return null;
		}

		public String int_net_desp_unitsDefault() {

			return null;

		}

		public String int_net_desp_unitsComment() {

			return "";

		}

		public String int_net_desp_unitsPattern() {

			return "";

		}

		public String int_net_desp_unitsOriginalDbColumnName() {

			return "int_net_desp_units";

		}

		public String LocationCode;

		public String getLocationCode() {
			return this.LocationCode;
		}

		public Boolean LocationCodeIsNullable() {
			return true;
		}

		public Boolean LocationCodeIsKey() {
			return false;
		}

		public Integer LocationCodeLength() {
			return null;
		}

		public Integer LocationCodePrecision() {
			return null;
		}

		public String LocationCodeDefault() {

			return null;

		}

		public String LocationCodeComment() {

			return "";

		}

		public String LocationCodePattern() {

			return "";

		}

		public String LocationCodeOriginalDbColumnName() {

			return "LocationCode";

		}

		public String ExportFlag;

		public String getExportFlag() {
			return this.ExportFlag;
		}

		public Boolean ExportFlagIsNullable() {
			return true;
		}

		public Boolean ExportFlagIsKey() {
			return false;
		}

		public Integer ExportFlagLength() {
			return null;
		}

		public Integer ExportFlagPrecision() {
			return null;
		}

		public String ExportFlagDefault() {

			return null;

		}

		public String ExportFlagComment() {

			return "";

		}

		public String ExportFlagPattern() {

			return "";

		}

		public String ExportFlagOriginalDbColumnName() {

			return "ExportFlag";

		}

		public Integer SalesQuantity;

		public Integer getSalesQuantity() {
			return this.SalesQuantity;
		}

		public Boolean SalesQuantityIsNullable() {
			return true;
		}

		public Boolean SalesQuantityIsKey() {
			return false;
		}

		public Integer SalesQuantityLength() {
			return null;
		}

		public Integer SalesQuantityPrecision() {
			return null;
		}

		public String SalesQuantityDefault() {

			return null;

		}

		public String SalesQuantityComment() {

			return "";

		}

		public String SalesQuantityPattern() {

			return "";

		}

		public String SalesQuantityOriginalDbColumnName() {

			return "SalesQuantity";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PRODUCT_LINE_CODE=" + PRODUCT_LINE_CODE);
			sb.append(",calender_month=" + calender_month);
			sb.append(",Product_Code=" + Product_Code);
			sb.append(",Product_Name=" + Product_Name);
			sb.append(",division=" + division);
			sb.append(",dept=" + dept);
			sb.append(",rnge=" + rnge);
			sb.append(",import_flag=" + import_flag);
			sb.append(",export_flag=" + export_flag);
			sb.append(",supplier_code=" + supplier_code);
			sb.append(",supplier_name=" + supplier_name);
			sb.append(",SUPPLIER_REF_NO=" + SUPPLIER_REF_NO);
			sb.append(",brand_group=" + brand_group);
			sb.append(",Nation_Sent=" + Nation_Sent);
			sb.append(",net_desp_units=" + String.valueOf(net_desp_units));
			sb.append(",uk_net_desp_units=" + String.valueOf(uk_net_desp_units));
			sb.append(",int_net_desp_units=" + String.valueOf(int_net_desp_units));
			sb.append(",LocationCode=" + LocationCode);
			sb.append(",ExportFlag=" + ExportFlag);
			sb.append(",SalesQuantity=" + String.valueOf(SalesQuantity));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PRODUCT_LINE_CODE == null) {
				sb.append("<null>");
			} else {
				sb.append(PRODUCT_LINE_CODE);
			}

			sb.append("|");

			if (calender_month == null) {
				sb.append("<null>");
			} else {
				sb.append(calender_month);
			}

			sb.append("|");

			if (Product_Code == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Code);
			}

			sb.append("|");

			if (Product_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Name);
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

			if (import_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(import_flag);
			}

			sb.append("|");

			if (export_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(export_flag);
			}

			sb.append("|");

			if (supplier_code == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_code);
			}

			sb.append("|");

			if (supplier_name == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_name);
			}

			sb.append("|");

			if (SUPPLIER_REF_NO == null) {
				sb.append("<null>");
			} else {
				sb.append(SUPPLIER_REF_NO);
			}

			sb.append("|");

			if (brand_group == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_group);
			}

			sb.append("|");

			if (Nation_Sent == null) {
				sb.append("<null>");
			} else {
				sb.append(Nation_Sent);
			}

			sb.append("|");

			if (net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(net_desp_units);
			}

			sb.append("|");

			if (uk_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(uk_net_desp_units);
			}

			sb.append("|");

			if (int_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(int_net_desp_units);
			}

			sb.append("|");

			if (LocationCode == null) {
				sb.append("<null>");
			} else {
				sb.append(LocationCode);
			}

			sb.append("|");

			if (ExportFlag == null) {
				sb.append("<null>");
			} else {
				sb.append(ExportFlag);
			}

			sb.append("|");

			if (SalesQuantity == null) {
				sb.append("<null>");
			} else {
				sb.append(SalesQuantity);
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

	public static class out2Struct implements routines.system.IPersistableRow<out2Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];

		public String PRODUCT_LINE_CODE;

		public String getPRODUCT_LINE_CODE() {
			return this.PRODUCT_LINE_CODE;
		}

		public Boolean PRODUCT_LINE_CODEIsNullable() {
			return true;
		}

		public Boolean PRODUCT_LINE_CODEIsKey() {
			return false;
		}

		public Integer PRODUCT_LINE_CODELength() {
			return null;
		}

		public Integer PRODUCT_LINE_CODEPrecision() {
			return null;
		}

		public String PRODUCT_LINE_CODEDefault() {

			return null;

		}

		public String PRODUCT_LINE_CODEComment() {

			return "";

		}

		public String PRODUCT_LINE_CODEPattern() {

			return "";

		}

		public String PRODUCT_LINE_CODEOriginalDbColumnName() {

			return "PRODUCT_LINE_CODE";

		}

		public String calender_month;

		public String getCalender_month() {
			return this.calender_month;
		}

		public Boolean calender_monthIsNullable() {
			return true;
		}

		public Boolean calender_monthIsKey() {
			return false;
		}

		public Integer calender_monthLength() {
			return null;
		}

		public Integer calender_monthPrecision() {
			return null;
		}

		public String calender_monthDefault() {

			return null;

		}

		public String calender_monthComment() {

			return "";

		}

		public String calender_monthPattern() {

			return "";

		}

		public String calender_monthOriginalDbColumnName() {

			return "calender_month";

		}

		public String Product_Code;

		public String getProduct_Code() {
			return this.Product_Code;
		}

		public Boolean Product_CodeIsNullable() {
			return true;
		}

		public Boolean Product_CodeIsKey() {
			return false;
		}

		public Integer Product_CodeLength() {
			return null;
		}

		public Integer Product_CodePrecision() {
			return null;
		}

		public String Product_CodeDefault() {

			return null;

		}

		public String Product_CodeComment() {

			return "";

		}

		public String Product_CodePattern() {

			return "";

		}

		public String Product_CodeOriginalDbColumnName() {

			return "Product_Code";

		}

		public String Product_Name;

		public String getProduct_Name() {
			return this.Product_Name;
		}

		public Boolean Product_NameIsNullable() {
			return true;
		}

		public Boolean Product_NameIsKey() {
			return false;
		}

		public Integer Product_NameLength() {
			return null;
		}

		public Integer Product_NamePrecision() {
			return null;
		}

		public String Product_NameDefault() {

			return null;

		}

		public String Product_NameComment() {

			return "";

		}

		public String Product_NamePattern() {

			return "";

		}

		public String Product_NameOriginalDbColumnName() {

			return "Product_Name";

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

		public String import_flag;

		public String getImport_flag() {
			return this.import_flag;
		}

		public Boolean import_flagIsNullable() {
			return true;
		}

		public Boolean import_flagIsKey() {
			return false;
		}

		public Integer import_flagLength() {
			return null;
		}

		public Integer import_flagPrecision() {
			return null;
		}

		public String import_flagDefault() {

			return null;

		}

		public String import_flagComment() {

			return "";

		}

		public String import_flagPattern() {

			return "";

		}

		public String import_flagOriginalDbColumnName() {

			return "import_flag";

		}

		public String export_flag;

		public String getExport_flag() {
			return this.export_flag;
		}

		public Boolean export_flagIsNullable() {
			return true;
		}

		public Boolean export_flagIsKey() {
			return false;
		}

		public Integer export_flagLength() {
			return null;
		}

		public Integer export_flagPrecision() {
			return null;
		}

		public String export_flagDefault() {

			return null;

		}

		public String export_flagComment() {

			return "";

		}

		public String export_flagPattern() {

			return "";

		}

		public String export_flagOriginalDbColumnName() {

			return "export_flag";

		}

		public String supplier_code;

		public String getSupplier_code() {
			return this.supplier_code;
		}

		public Boolean supplier_codeIsNullable() {
			return true;
		}

		public Boolean supplier_codeIsKey() {
			return false;
		}

		public Integer supplier_codeLength() {
			return null;
		}

		public Integer supplier_codePrecision() {
			return null;
		}

		public String supplier_codeDefault() {

			return null;

		}

		public String supplier_codeComment() {

			return "";

		}

		public String supplier_codePattern() {

			return "";

		}

		public String supplier_codeOriginalDbColumnName() {

			return "supplier_code";

		}

		public String supplier_name;

		public String getSupplier_name() {
			return this.supplier_name;
		}

		public Boolean supplier_nameIsNullable() {
			return true;
		}

		public Boolean supplier_nameIsKey() {
			return false;
		}

		public Integer supplier_nameLength() {
			return null;
		}

		public Integer supplier_namePrecision() {
			return null;
		}

		public String supplier_nameDefault() {

			return null;

		}

		public String supplier_nameComment() {

			return "";

		}

		public String supplier_namePattern() {

			return "";

		}

		public String supplier_nameOriginalDbColumnName() {

			return "supplier_name";

		}

		public String SUPPLIER_REF_NO;

		public String getSUPPLIER_REF_NO() {
			return this.SUPPLIER_REF_NO;
		}

		public Boolean SUPPLIER_REF_NOIsNullable() {
			return true;
		}

		public Boolean SUPPLIER_REF_NOIsKey() {
			return false;
		}

		public Integer SUPPLIER_REF_NOLength() {
			return null;
		}

		public Integer SUPPLIER_REF_NOPrecision() {
			return null;
		}

		public String SUPPLIER_REF_NODefault() {

			return null;

		}

		public String SUPPLIER_REF_NOComment() {

			return "";

		}

		public String SUPPLIER_REF_NOPattern() {

			return "";

		}

		public String SUPPLIER_REF_NOOriginalDbColumnName() {

			return "SUPPLIER_REF_NO";

		}

		public String brand_group;

		public String getBrand_group() {
			return this.brand_group;
		}

		public Boolean brand_groupIsNullable() {
			return true;
		}

		public Boolean brand_groupIsKey() {
			return false;
		}

		public Integer brand_groupLength() {
			return null;
		}

		public Integer brand_groupPrecision() {
			return null;
		}

		public String brand_groupDefault() {

			return null;

		}

		public String brand_groupComment() {

			return "";

		}

		public String brand_groupPattern() {

			return "";

		}

		public String brand_groupOriginalDbColumnName() {

			return "brand_group";

		}

		public String Nation_Sent;

		public String getNation_Sent() {
			return this.Nation_Sent;
		}

		public Boolean Nation_SentIsNullable() {
			return true;
		}

		public Boolean Nation_SentIsKey() {
			return false;
		}

		public Integer Nation_SentLength() {
			return null;
		}

		public Integer Nation_SentPrecision() {
			return null;
		}

		public String Nation_SentDefault() {

			return null;

		}

		public String Nation_SentComment() {

			return "";

		}

		public String Nation_SentPattern() {

			return "";

		}

		public String Nation_SentOriginalDbColumnName() {

			return "Nation_Sent";

		}

		public Integer net_desp_units;

		public Integer getNet_desp_units() {
			return this.net_desp_units;
		}

		public Boolean net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean net_desp_unitsIsKey() {
			return false;
		}

		public Integer net_desp_unitsLength() {
			return null;
		}

		public Integer net_desp_unitsPrecision() {
			return null;
		}

		public String net_desp_unitsDefault() {

			return null;

		}

		public String net_desp_unitsComment() {

			return "";

		}

		public String net_desp_unitsPattern() {

			return "";

		}

		public String net_desp_unitsOriginalDbColumnName() {

			return "net_desp_units";

		}

		public Integer uk_net_desp_units;

		public Integer getUk_net_desp_units() {
			return this.uk_net_desp_units;
		}

		public Boolean uk_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean uk_net_desp_unitsIsKey() {
			return false;
		}

		public Integer uk_net_desp_unitsLength() {
			return null;
		}

		public Integer uk_net_desp_unitsPrecision() {
			return null;
		}

		public String uk_net_desp_unitsDefault() {

			return null;

		}

		public String uk_net_desp_unitsComment() {

			return "";

		}

		public String uk_net_desp_unitsPattern() {

			return "";

		}

		public String uk_net_desp_unitsOriginalDbColumnName() {

			return "uk_net_desp_units";

		}

		public Integer int_net_desp_units;

		public Integer getInt_net_desp_units() {
			return this.int_net_desp_units;
		}

		public Boolean int_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean int_net_desp_unitsIsKey() {
			return false;
		}

		public Integer int_net_desp_unitsLength() {
			return null;
		}

		public Integer int_net_desp_unitsPrecision() {
			return null;
		}

		public String int_net_desp_unitsDefault() {

			return null;

		}

		public String int_net_desp_unitsComment() {

			return "";

		}

		public String int_net_desp_unitsPattern() {

			return "";

		}

		public String int_net_desp_unitsOriginalDbColumnName() {

			return "int_net_desp_units";

		}

		public String LocationCode;

		public String getLocationCode() {
			return this.LocationCode;
		}

		public Boolean LocationCodeIsNullable() {
			return true;
		}

		public Boolean LocationCodeIsKey() {
			return false;
		}

		public Integer LocationCodeLength() {
			return null;
		}

		public Integer LocationCodePrecision() {
			return null;
		}

		public String LocationCodeDefault() {

			return null;

		}

		public String LocationCodeComment() {

			return "";

		}

		public String LocationCodePattern() {

			return "";

		}

		public String LocationCodeOriginalDbColumnName() {

			return "LocationCode";

		}

		public String ExportFlag;

		public String getExportFlag() {
			return this.ExportFlag;
		}

		public Boolean ExportFlagIsNullable() {
			return true;
		}

		public Boolean ExportFlagIsKey() {
			return false;
		}

		public Integer ExportFlagLength() {
			return null;
		}

		public Integer ExportFlagPrecision() {
			return null;
		}

		public String ExportFlagDefault() {

			return null;

		}

		public String ExportFlagComment() {

			return "";

		}

		public String ExportFlagPattern() {

			return "";

		}

		public String ExportFlagOriginalDbColumnName() {

			return "ExportFlag";

		}

		public Integer SalesQuantity;

		public Integer getSalesQuantity() {
			return this.SalesQuantity;
		}

		public Boolean SalesQuantityIsNullable() {
			return true;
		}

		public Boolean SalesQuantityIsKey() {
			return false;
		}

		public Integer SalesQuantityLength() {
			return null;
		}

		public Integer SalesQuantityPrecision() {
			return null;
		}

		public String SalesQuantityDefault() {

			return null;

		}

		public String SalesQuantityComment() {

			return "";

		}

		public String SalesQuantityPattern() {

			return "";

		}

		public String SalesQuantityOriginalDbColumnName() {

			return "SalesQuantity";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PRODUCT_LINE_CODE=" + PRODUCT_LINE_CODE);
			sb.append(",calender_month=" + calender_month);
			sb.append(",Product_Code=" + Product_Code);
			sb.append(",Product_Name=" + Product_Name);
			sb.append(",division=" + division);
			sb.append(",dept=" + dept);
			sb.append(",rnge=" + rnge);
			sb.append(",import_flag=" + import_flag);
			sb.append(",export_flag=" + export_flag);
			sb.append(",supplier_code=" + supplier_code);
			sb.append(",supplier_name=" + supplier_name);
			sb.append(",SUPPLIER_REF_NO=" + SUPPLIER_REF_NO);
			sb.append(",brand_group=" + brand_group);
			sb.append(",Nation_Sent=" + Nation_Sent);
			sb.append(",net_desp_units=" + String.valueOf(net_desp_units));
			sb.append(",uk_net_desp_units=" + String.valueOf(uk_net_desp_units));
			sb.append(",int_net_desp_units=" + String.valueOf(int_net_desp_units));
			sb.append(",LocationCode=" + LocationCode);
			sb.append(",ExportFlag=" + ExportFlag);
			sb.append(",SalesQuantity=" + String.valueOf(SalesQuantity));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PRODUCT_LINE_CODE == null) {
				sb.append("<null>");
			} else {
				sb.append(PRODUCT_LINE_CODE);
			}

			sb.append("|");

			if (calender_month == null) {
				sb.append("<null>");
			} else {
				sb.append(calender_month);
			}

			sb.append("|");

			if (Product_Code == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Code);
			}

			sb.append("|");

			if (Product_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Name);
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

			if (import_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(import_flag);
			}

			sb.append("|");

			if (export_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(export_flag);
			}

			sb.append("|");

			if (supplier_code == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_code);
			}

			sb.append("|");

			if (supplier_name == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_name);
			}

			sb.append("|");

			if (SUPPLIER_REF_NO == null) {
				sb.append("<null>");
			} else {
				sb.append(SUPPLIER_REF_NO);
			}

			sb.append("|");

			if (brand_group == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_group);
			}

			sb.append("|");

			if (Nation_Sent == null) {
				sb.append("<null>");
			} else {
				sb.append(Nation_Sent);
			}

			sb.append("|");

			if (net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(net_desp_units);
			}

			sb.append("|");

			if (uk_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(uk_net_desp_units);
			}

			sb.append("|");

			if (int_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(int_net_desp_units);
			}

			sb.append("|");

			if (LocationCode == null) {
				sb.append("<null>");
			} else {
				sb.append(LocationCode);
			}

			sb.append("|");

			if (ExportFlag == null) {
				sb.append("<null>");
			} else {
				sb.append(ExportFlag);
			}

			sb.append("|");

			if (SalesQuantity == null) {
				sb.append("<null>");
			} else {
				sb.append(SalesQuantity);
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

	public static class out3Struct implements routines.system.IPersistableRow<out3Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];

		public String PRODUCT_LINE_CODE;

		public String getPRODUCT_LINE_CODE() {
			return this.PRODUCT_LINE_CODE;
		}

		public Boolean PRODUCT_LINE_CODEIsNullable() {
			return true;
		}

		public Boolean PRODUCT_LINE_CODEIsKey() {
			return false;
		}

		public Integer PRODUCT_LINE_CODELength() {
			return null;
		}

		public Integer PRODUCT_LINE_CODEPrecision() {
			return null;
		}

		public String PRODUCT_LINE_CODEDefault() {

			return null;

		}

		public String PRODUCT_LINE_CODEComment() {

			return "";

		}

		public String PRODUCT_LINE_CODEPattern() {

			return "";

		}

		public String PRODUCT_LINE_CODEOriginalDbColumnName() {

			return "PRODUCT_LINE_CODE";

		}

		public String calender_month;

		public String getCalender_month() {
			return this.calender_month;
		}

		public Boolean calender_monthIsNullable() {
			return true;
		}

		public Boolean calender_monthIsKey() {
			return false;
		}

		public Integer calender_monthLength() {
			return null;
		}

		public Integer calender_monthPrecision() {
			return null;
		}

		public String calender_monthDefault() {

			return null;

		}

		public String calender_monthComment() {

			return "";

		}

		public String calender_monthPattern() {

			return "";

		}

		public String calender_monthOriginalDbColumnName() {

			return "calender_month";

		}

		public String Product_Code;

		public String getProduct_Code() {
			return this.Product_Code;
		}

		public Boolean Product_CodeIsNullable() {
			return true;
		}

		public Boolean Product_CodeIsKey() {
			return false;
		}

		public Integer Product_CodeLength() {
			return null;
		}

		public Integer Product_CodePrecision() {
			return null;
		}

		public String Product_CodeDefault() {

			return null;

		}

		public String Product_CodeComment() {

			return "";

		}

		public String Product_CodePattern() {

			return "";

		}

		public String Product_CodeOriginalDbColumnName() {

			return "Product_Code";

		}

		public String Product_Name;

		public String getProduct_Name() {
			return this.Product_Name;
		}

		public Boolean Product_NameIsNullable() {
			return true;
		}

		public Boolean Product_NameIsKey() {
			return false;
		}

		public Integer Product_NameLength() {
			return null;
		}

		public Integer Product_NamePrecision() {
			return null;
		}

		public String Product_NameDefault() {

			return null;

		}

		public String Product_NameComment() {

			return "";

		}

		public String Product_NamePattern() {

			return "";

		}

		public String Product_NameOriginalDbColumnName() {

			return "Product_Name";

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

		public String import_flag;

		public String getImport_flag() {
			return this.import_flag;
		}

		public Boolean import_flagIsNullable() {
			return true;
		}

		public Boolean import_flagIsKey() {
			return false;
		}

		public Integer import_flagLength() {
			return null;
		}

		public Integer import_flagPrecision() {
			return null;
		}

		public String import_flagDefault() {

			return null;

		}

		public String import_flagComment() {

			return "";

		}

		public String import_flagPattern() {

			return "";

		}

		public String import_flagOriginalDbColumnName() {

			return "import_flag";

		}

		public String export_flag;

		public String getExport_flag() {
			return this.export_flag;
		}

		public Boolean export_flagIsNullable() {
			return true;
		}

		public Boolean export_flagIsKey() {
			return false;
		}

		public Integer export_flagLength() {
			return null;
		}

		public Integer export_flagPrecision() {
			return null;
		}

		public String export_flagDefault() {

			return null;

		}

		public String export_flagComment() {

			return "";

		}

		public String export_flagPattern() {

			return "";

		}

		public String export_flagOriginalDbColumnName() {

			return "export_flag";

		}

		public String supplier_code;

		public String getSupplier_code() {
			return this.supplier_code;
		}

		public Boolean supplier_codeIsNullable() {
			return true;
		}

		public Boolean supplier_codeIsKey() {
			return false;
		}

		public Integer supplier_codeLength() {
			return null;
		}

		public Integer supplier_codePrecision() {
			return null;
		}

		public String supplier_codeDefault() {

			return null;

		}

		public String supplier_codeComment() {

			return "";

		}

		public String supplier_codePattern() {

			return "";

		}

		public String supplier_codeOriginalDbColumnName() {

			return "supplier_code";

		}

		public String supplier_name;

		public String getSupplier_name() {
			return this.supplier_name;
		}

		public Boolean supplier_nameIsNullable() {
			return true;
		}

		public Boolean supplier_nameIsKey() {
			return false;
		}

		public Integer supplier_nameLength() {
			return null;
		}

		public Integer supplier_namePrecision() {
			return null;
		}

		public String supplier_nameDefault() {

			return null;

		}

		public String supplier_nameComment() {

			return "";

		}

		public String supplier_namePattern() {

			return "";

		}

		public String supplier_nameOriginalDbColumnName() {

			return "supplier_name";

		}

		public String SUPPLIER_REF_NO;

		public String getSUPPLIER_REF_NO() {
			return this.SUPPLIER_REF_NO;
		}

		public Boolean SUPPLIER_REF_NOIsNullable() {
			return true;
		}

		public Boolean SUPPLIER_REF_NOIsKey() {
			return false;
		}

		public Integer SUPPLIER_REF_NOLength() {
			return null;
		}

		public Integer SUPPLIER_REF_NOPrecision() {
			return null;
		}

		public String SUPPLIER_REF_NODefault() {

			return null;

		}

		public String SUPPLIER_REF_NOComment() {

			return "";

		}

		public String SUPPLIER_REF_NOPattern() {

			return "";

		}

		public String SUPPLIER_REF_NOOriginalDbColumnName() {

			return "SUPPLIER_REF_NO";

		}

		public String brand_group;

		public String getBrand_group() {
			return this.brand_group;
		}

		public Boolean brand_groupIsNullable() {
			return true;
		}

		public Boolean brand_groupIsKey() {
			return false;
		}

		public Integer brand_groupLength() {
			return null;
		}

		public Integer brand_groupPrecision() {
			return null;
		}

		public String brand_groupDefault() {

			return null;

		}

		public String brand_groupComment() {

			return "";

		}

		public String brand_groupPattern() {

			return "";

		}

		public String brand_groupOriginalDbColumnName() {

			return "brand_group";

		}

		public String Nation_Sent;

		public String getNation_Sent() {
			return this.Nation_Sent;
		}

		public Boolean Nation_SentIsNullable() {
			return true;
		}

		public Boolean Nation_SentIsKey() {
			return false;
		}

		public Integer Nation_SentLength() {
			return null;
		}

		public Integer Nation_SentPrecision() {
			return null;
		}

		public String Nation_SentDefault() {

			return null;

		}

		public String Nation_SentComment() {

			return "";

		}

		public String Nation_SentPattern() {

			return "";

		}

		public String Nation_SentOriginalDbColumnName() {

			return "Nation_Sent";

		}

		public Integer net_desp_units;

		public Integer getNet_desp_units() {
			return this.net_desp_units;
		}

		public Boolean net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean net_desp_unitsIsKey() {
			return false;
		}

		public Integer net_desp_unitsLength() {
			return null;
		}

		public Integer net_desp_unitsPrecision() {
			return null;
		}

		public String net_desp_unitsDefault() {

			return null;

		}

		public String net_desp_unitsComment() {

			return "";

		}

		public String net_desp_unitsPattern() {

			return "";

		}

		public String net_desp_unitsOriginalDbColumnName() {

			return "net_desp_units";

		}

		public Integer uk_net_desp_units;

		public Integer getUk_net_desp_units() {
			return this.uk_net_desp_units;
		}

		public Boolean uk_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean uk_net_desp_unitsIsKey() {
			return false;
		}

		public Integer uk_net_desp_unitsLength() {
			return null;
		}

		public Integer uk_net_desp_unitsPrecision() {
			return null;
		}

		public String uk_net_desp_unitsDefault() {

			return null;

		}

		public String uk_net_desp_unitsComment() {

			return "";

		}

		public String uk_net_desp_unitsPattern() {

			return "";

		}

		public String uk_net_desp_unitsOriginalDbColumnName() {

			return "uk_net_desp_units";

		}

		public Integer int_net_desp_units;

		public Integer getInt_net_desp_units() {
			return this.int_net_desp_units;
		}

		public Boolean int_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean int_net_desp_unitsIsKey() {
			return false;
		}

		public Integer int_net_desp_unitsLength() {
			return null;
		}

		public Integer int_net_desp_unitsPrecision() {
			return null;
		}

		public String int_net_desp_unitsDefault() {

			return null;

		}

		public String int_net_desp_unitsComment() {

			return "";

		}

		public String int_net_desp_unitsPattern() {

			return "";

		}

		public String int_net_desp_unitsOriginalDbColumnName() {

			return "int_net_desp_units";

		}

		public String LocationCode;

		public String getLocationCode() {
			return this.LocationCode;
		}

		public Boolean LocationCodeIsNullable() {
			return true;
		}

		public Boolean LocationCodeIsKey() {
			return false;
		}

		public Integer LocationCodeLength() {
			return null;
		}

		public Integer LocationCodePrecision() {
			return null;
		}

		public String LocationCodeDefault() {

			return null;

		}

		public String LocationCodeComment() {

			return "";

		}

		public String LocationCodePattern() {

			return "";

		}

		public String LocationCodeOriginalDbColumnName() {

			return "LocationCode";

		}

		public String ExportFlag;

		public String getExportFlag() {
			return this.ExportFlag;
		}

		public Boolean ExportFlagIsNullable() {
			return true;
		}

		public Boolean ExportFlagIsKey() {
			return false;
		}

		public Integer ExportFlagLength() {
			return null;
		}

		public Integer ExportFlagPrecision() {
			return null;
		}

		public String ExportFlagDefault() {

			return null;

		}

		public String ExportFlagComment() {

			return "";

		}

		public String ExportFlagPattern() {

			return "";

		}

		public String ExportFlagOriginalDbColumnName() {

			return "ExportFlag";

		}

		public Integer SalesQuantity;

		public Integer getSalesQuantity() {
			return this.SalesQuantity;
		}

		public Boolean SalesQuantityIsNullable() {
			return true;
		}

		public Boolean SalesQuantityIsKey() {
			return false;
		}

		public Integer SalesQuantityLength() {
			return null;
		}

		public Integer SalesQuantityPrecision() {
			return null;
		}

		public String SalesQuantityDefault() {

			return null;

		}

		public String SalesQuantityComment() {

			return "";

		}

		public String SalesQuantityPattern() {

			return "";

		}

		public String SalesQuantityOriginalDbColumnName() {

			return "SalesQuantity";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PRODUCT_LINE_CODE=" + PRODUCT_LINE_CODE);
			sb.append(",calender_month=" + calender_month);
			sb.append(",Product_Code=" + Product_Code);
			sb.append(",Product_Name=" + Product_Name);
			sb.append(",division=" + division);
			sb.append(",dept=" + dept);
			sb.append(",rnge=" + rnge);
			sb.append(",import_flag=" + import_flag);
			sb.append(",export_flag=" + export_flag);
			sb.append(",supplier_code=" + supplier_code);
			sb.append(",supplier_name=" + supplier_name);
			sb.append(",SUPPLIER_REF_NO=" + SUPPLIER_REF_NO);
			sb.append(",brand_group=" + brand_group);
			sb.append(",Nation_Sent=" + Nation_Sent);
			sb.append(",net_desp_units=" + String.valueOf(net_desp_units));
			sb.append(",uk_net_desp_units=" + String.valueOf(uk_net_desp_units));
			sb.append(",int_net_desp_units=" + String.valueOf(int_net_desp_units));
			sb.append(",LocationCode=" + LocationCode);
			sb.append(",ExportFlag=" + ExportFlag);
			sb.append(",SalesQuantity=" + String.valueOf(SalesQuantity));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PRODUCT_LINE_CODE == null) {
				sb.append("<null>");
			} else {
				sb.append(PRODUCT_LINE_CODE);
			}

			sb.append("|");

			if (calender_month == null) {
				sb.append("<null>");
			} else {
				sb.append(calender_month);
			}

			sb.append("|");

			if (Product_Code == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Code);
			}

			sb.append("|");

			if (Product_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Name);
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

			if (import_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(import_flag);
			}

			sb.append("|");

			if (export_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(export_flag);
			}

			sb.append("|");

			if (supplier_code == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_code);
			}

			sb.append("|");

			if (supplier_name == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_name);
			}

			sb.append("|");

			if (SUPPLIER_REF_NO == null) {
				sb.append("<null>");
			} else {
				sb.append(SUPPLIER_REF_NO);
			}

			sb.append("|");

			if (brand_group == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_group);
			}

			sb.append("|");

			if (Nation_Sent == null) {
				sb.append("<null>");
			} else {
				sb.append(Nation_Sent);
			}

			sb.append("|");

			if (net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(net_desp_units);
			}

			sb.append("|");

			if (uk_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(uk_net_desp_units);
			}

			sb.append("|");

			if (int_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(int_net_desp_units);
			}

			sb.append("|");

			if (LocationCode == null) {
				sb.append("<null>");
			} else {
				sb.append(LocationCode);
			}

			sb.append("|");

			if (ExportFlag == null) {
				sb.append("<null>");
			} else {
				sb.append(ExportFlag);
			}

			sb.append("|");

			if (SalesQuantity == null) {
				sb.append("<null>");
			} else {
				sb.append(SalesQuantity);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out3Struct other) {

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

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];

		public String PRODUCT_LINE_CODE;

		public String getPRODUCT_LINE_CODE() {
			return this.PRODUCT_LINE_CODE;
		}

		public Boolean PRODUCT_LINE_CODEIsNullable() {
			return true;
		}

		public Boolean PRODUCT_LINE_CODEIsKey() {
			return false;
		}

		public Integer PRODUCT_LINE_CODELength() {
			return null;
		}

		public Integer PRODUCT_LINE_CODEPrecision() {
			return null;
		}

		public String PRODUCT_LINE_CODEDefault() {

			return null;

		}

		public String PRODUCT_LINE_CODEComment() {

			return "";

		}

		public String PRODUCT_LINE_CODEPattern() {

			return "";

		}

		public String PRODUCT_LINE_CODEOriginalDbColumnName() {

			return "PRODUCT_LINE_CODE";

		}

		public String calender_month;

		public String getCalender_month() {
			return this.calender_month;
		}

		public Boolean calender_monthIsNullable() {
			return true;
		}

		public Boolean calender_monthIsKey() {
			return false;
		}

		public Integer calender_monthLength() {
			return null;
		}

		public Integer calender_monthPrecision() {
			return null;
		}

		public String calender_monthDefault() {

			return null;

		}

		public String calender_monthComment() {

			return "";

		}

		public String calender_monthPattern() {

			return "";

		}

		public String calender_monthOriginalDbColumnName() {

			return "calender_month";

		}

		public String Product_Code;

		public String getProduct_Code() {
			return this.Product_Code;
		}

		public Boolean Product_CodeIsNullable() {
			return true;
		}

		public Boolean Product_CodeIsKey() {
			return false;
		}

		public Integer Product_CodeLength() {
			return null;
		}

		public Integer Product_CodePrecision() {
			return null;
		}

		public String Product_CodeDefault() {

			return null;

		}

		public String Product_CodeComment() {

			return "";

		}

		public String Product_CodePattern() {

			return "";

		}

		public String Product_CodeOriginalDbColumnName() {

			return "Product_Code";

		}

		public String Product_Name;

		public String getProduct_Name() {
			return this.Product_Name;
		}

		public Boolean Product_NameIsNullable() {
			return true;
		}

		public Boolean Product_NameIsKey() {
			return false;
		}

		public Integer Product_NameLength() {
			return null;
		}

		public Integer Product_NamePrecision() {
			return null;
		}

		public String Product_NameDefault() {

			return null;

		}

		public String Product_NameComment() {

			return "";

		}

		public String Product_NamePattern() {

			return "";

		}

		public String Product_NameOriginalDbColumnName() {

			return "Product_Name";

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

		public String import_flag;

		public String getImport_flag() {
			return this.import_flag;
		}

		public Boolean import_flagIsNullable() {
			return true;
		}

		public Boolean import_flagIsKey() {
			return false;
		}

		public Integer import_flagLength() {
			return null;
		}

		public Integer import_flagPrecision() {
			return null;
		}

		public String import_flagDefault() {

			return null;

		}

		public String import_flagComment() {

			return "";

		}

		public String import_flagPattern() {

			return "";

		}

		public String import_flagOriginalDbColumnName() {

			return "import_flag";

		}

		public String export_flag;

		public String getExport_flag() {
			return this.export_flag;
		}

		public Boolean export_flagIsNullable() {
			return true;
		}

		public Boolean export_flagIsKey() {
			return false;
		}

		public Integer export_flagLength() {
			return null;
		}

		public Integer export_flagPrecision() {
			return null;
		}

		public String export_flagDefault() {

			return null;

		}

		public String export_flagComment() {

			return "";

		}

		public String export_flagPattern() {

			return "";

		}

		public String export_flagOriginalDbColumnName() {

			return "export_flag";

		}

		public String supplier_code;

		public String getSupplier_code() {
			return this.supplier_code;
		}

		public Boolean supplier_codeIsNullable() {
			return true;
		}

		public Boolean supplier_codeIsKey() {
			return false;
		}

		public Integer supplier_codeLength() {
			return null;
		}

		public Integer supplier_codePrecision() {
			return null;
		}

		public String supplier_codeDefault() {

			return null;

		}

		public String supplier_codeComment() {

			return "";

		}

		public String supplier_codePattern() {

			return "";

		}

		public String supplier_codeOriginalDbColumnName() {

			return "supplier_code";

		}

		public String supplier_name;

		public String getSupplier_name() {
			return this.supplier_name;
		}

		public Boolean supplier_nameIsNullable() {
			return true;
		}

		public Boolean supplier_nameIsKey() {
			return false;
		}

		public Integer supplier_nameLength() {
			return null;
		}

		public Integer supplier_namePrecision() {
			return null;
		}

		public String supplier_nameDefault() {

			return null;

		}

		public String supplier_nameComment() {

			return "";

		}

		public String supplier_namePattern() {

			return "";

		}

		public String supplier_nameOriginalDbColumnName() {

			return "supplier_name";

		}

		public String SUPPLIER_REF_NO;

		public String getSUPPLIER_REF_NO() {
			return this.SUPPLIER_REF_NO;
		}

		public Boolean SUPPLIER_REF_NOIsNullable() {
			return true;
		}

		public Boolean SUPPLIER_REF_NOIsKey() {
			return false;
		}

		public Integer SUPPLIER_REF_NOLength() {
			return null;
		}

		public Integer SUPPLIER_REF_NOPrecision() {
			return null;
		}

		public String SUPPLIER_REF_NODefault() {

			return null;

		}

		public String SUPPLIER_REF_NOComment() {

			return "";

		}

		public String SUPPLIER_REF_NOPattern() {

			return "";

		}

		public String SUPPLIER_REF_NOOriginalDbColumnName() {

			return "SUPPLIER_REF_NO";

		}

		public String brand_group;

		public String getBrand_group() {
			return this.brand_group;
		}

		public Boolean brand_groupIsNullable() {
			return true;
		}

		public Boolean brand_groupIsKey() {
			return false;
		}

		public Integer brand_groupLength() {
			return null;
		}

		public Integer brand_groupPrecision() {
			return null;
		}

		public String brand_groupDefault() {

			return null;

		}

		public String brand_groupComment() {

			return "";

		}

		public String brand_groupPattern() {

			return "";

		}

		public String brand_groupOriginalDbColumnName() {

			return "brand_group";

		}

		public String Nation_Sent;

		public String getNation_Sent() {
			return this.Nation_Sent;
		}

		public Boolean Nation_SentIsNullable() {
			return true;
		}

		public Boolean Nation_SentIsKey() {
			return false;
		}

		public Integer Nation_SentLength() {
			return null;
		}

		public Integer Nation_SentPrecision() {
			return null;
		}

		public String Nation_SentDefault() {

			return null;

		}

		public String Nation_SentComment() {

			return "";

		}

		public String Nation_SentPattern() {

			return "";

		}

		public String Nation_SentOriginalDbColumnName() {

			return "Nation_Sent";

		}

		public Integer net_desp_units;

		public Integer getNet_desp_units() {
			return this.net_desp_units;
		}

		public Boolean net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean net_desp_unitsIsKey() {
			return false;
		}

		public Integer net_desp_unitsLength() {
			return null;
		}

		public Integer net_desp_unitsPrecision() {
			return null;
		}

		public String net_desp_unitsDefault() {

			return null;

		}

		public String net_desp_unitsComment() {

			return "";

		}

		public String net_desp_unitsPattern() {

			return "";

		}

		public String net_desp_unitsOriginalDbColumnName() {

			return "net_desp_units";

		}

		public Integer uk_net_desp_units;

		public Integer getUk_net_desp_units() {
			return this.uk_net_desp_units;
		}

		public Boolean uk_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean uk_net_desp_unitsIsKey() {
			return false;
		}

		public Integer uk_net_desp_unitsLength() {
			return null;
		}

		public Integer uk_net_desp_unitsPrecision() {
			return null;
		}

		public String uk_net_desp_unitsDefault() {

			return null;

		}

		public String uk_net_desp_unitsComment() {

			return "";

		}

		public String uk_net_desp_unitsPattern() {

			return "";

		}

		public String uk_net_desp_unitsOriginalDbColumnName() {

			return "uk_net_desp_units";

		}

		public Integer int_net_desp_units;

		public Integer getInt_net_desp_units() {
			return this.int_net_desp_units;
		}

		public Boolean int_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean int_net_desp_unitsIsKey() {
			return false;
		}

		public Integer int_net_desp_unitsLength() {
			return null;
		}

		public Integer int_net_desp_unitsPrecision() {
			return null;
		}

		public String int_net_desp_unitsDefault() {

			return null;

		}

		public String int_net_desp_unitsComment() {

			return "";

		}

		public String int_net_desp_unitsPattern() {

			return "";

		}

		public String int_net_desp_unitsOriginalDbColumnName() {

			return "int_net_desp_units";

		}

		public String LocationCode;

		public String getLocationCode() {
			return this.LocationCode;
		}

		public Boolean LocationCodeIsNullable() {
			return true;
		}

		public Boolean LocationCodeIsKey() {
			return false;
		}

		public Integer LocationCodeLength() {
			return null;
		}

		public Integer LocationCodePrecision() {
			return null;
		}

		public String LocationCodeDefault() {

			return null;

		}

		public String LocationCodeComment() {

			return "";

		}

		public String LocationCodePattern() {

			return "";

		}

		public String LocationCodeOriginalDbColumnName() {

			return "LocationCode";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PRODUCT_LINE_CODE=" + PRODUCT_LINE_CODE);
			sb.append(",calender_month=" + calender_month);
			sb.append(",Product_Code=" + Product_Code);
			sb.append(",Product_Name=" + Product_Name);
			sb.append(",division=" + division);
			sb.append(",dept=" + dept);
			sb.append(",rnge=" + rnge);
			sb.append(",import_flag=" + import_flag);
			sb.append(",export_flag=" + export_flag);
			sb.append(",supplier_code=" + supplier_code);
			sb.append(",supplier_name=" + supplier_name);
			sb.append(",SUPPLIER_REF_NO=" + SUPPLIER_REF_NO);
			sb.append(",brand_group=" + brand_group);
			sb.append(",Nation_Sent=" + Nation_Sent);
			sb.append(",net_desp_units=" + String.valueOf(net_desp_units));
			sb.append(",uk_net_desp_units=" + String.valueOf(uk_net_desp_units));
			sb.append(",int_net_desp_units=" + String.valueOf(int_net_desp_units));
			sb.append(",LocationCode=" + LocationCode);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PRODUCT_LINE_CODE == null) {
				sb.append("<null>");
			} else {
				sb.append(PRODUCT_LINE_CODE);
			}

			sb.append("|");

			if (calender_month == null) {
				sb.append("<null>");
			} else {
				sb.append(calender_month);
			}

			sb.append("|");

			if (Product_Code == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Code);
			}

			sb.append("|");

			if (Product_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Name);
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

			if (import_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(import_flag);
			}

			sb.append("|");

			if (export_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(export_flag);
			}

			sb.append("|");

			if (supplier_code == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_code);
			}

			sb.append("|");

			if (supplier_name == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_name);
			}

			sb.append("|");

			if (SUPPLIER_REF_NO == null) {
				sb.append("<null>");
			} else {
				sb.append(SUPPLIER_REF_NO);
			}

			sb.append("|");

			if (brand_group == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_group);
			}

			sb.append("|");

			if (Nation_Sent == null) {
				sb.append("<null>");
			} else {
				sb.append(Nation_Sent);
			}

			sb.append("|");

			if (net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(net_desp_units);
			}

			sb.append("|");

			if (uk_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(uk_net_desp_units);
			}

			sb.append("|");

			if (int_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(int_net_desp_units);
			}

			sb.append("|");

			if (LocationCode == null) {
				sb.append("<null>");
			} else {
				sb.append(LocationCode);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

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

	public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];

		public String PRODUCT_LINE_CODE;

		public String getPRODUCT_LINE_CODE() {
			return this.PRODUCT_LINE_CODE;
		}

		public Boolean PRODUCT_LINE_CODEIsNullable() {
			return true;
		}

		public Boolean PRODUCT_LINE_CODEIsKey() {
			return false;
		}

		public Integer PRODUCT_LINE_CODELength() {
			return null;
		}

		public Integer PRODUCT_LINE_CODEPrecision() {
			return null;
		}

		public String PRODUCT_LINE_CODEDefault() {

			return null;

		}

		public String PRODUCT_LINE_CODEComment() {

			return "";

		}

		public String PRODUCT_LINE_CODEPattern() {

			return "";

		}

		public String PRODUCT_LINE_CODEOriginalDbColumnName() {

			return "PRODUCT_LINE_CODE";

		}

		public String calender_month;

		public String getCalender_month() {
			return this.calender_month;
		}

		public Boolean calender_monthIsNullable() {
			return true;
		}

		public Boolean calender_monthIsKey() {
			return false;
		}

		public Integer calender_monthLength() {
			return null;
		}

		public Integer calender_monthPrecision() {
			return null;
		}

		public String calender_monthDefault() {

			return null;

		}

		public String calender_monthComment() {

			return "";

		}

		public String calender_monthPattern() {

			return "";

		}

		public String calender_monthOriginalDbColumnName() {

			return "calender_month";

		}

		public String Product_Code;

		public String getProduct_Code() {
			return this.Product_Code;
		}

		public Boolean Product_CodeIsNullable() {
			return true;
		}

		public Boolean Product_CodeIsKey() {
			return false;
		}

		public Integer Product_CodeLength() {
			return null;
		}

		public Integer Product_CodePrecision() {
			return null;
		}

		public String Product_CodeDefault() {

			return null;

		}

		public String Product_CodeComment() {

			return "";

		}

		public String Product_CodePattern() {

			return "";

		}

		public String Product_CodeOriginalDbColumnName() {

			return "Product_Code";

		}

		public String Product_Name;

		public String getProduct_Name() {
			return this.Product_Name;
		}

		public Boolean Product_NameIsNullable() {
			return true;
		}

		public Boolean Product_NameIsKey() {
			return false;
		}

		public Integer Product_NameLength() {
			return null;
		}

		public Integer Product_NamePrecision() {
			return null;
		}

		public String Product_NameDefault() {

			return null;

		}

		public String Product_NameComment() {

			return "";

		}

		public String Product_NamePattern() {

			return "";

		}

		public String Product_NameOriginalDbColumnName() {

			return "Product_Name";

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

		public String import_flag;

		public String getImport_flag() {
			return this.import_flag;
		}

		public Boolean import_flagIsNullable() {
			return true;
		}

		public Boolean import_flagIsKey() {
			return false;
		}

		public Integer import_flagLength() {
			return null;
		}

		public Integer import_flagPrecision() {
			return null;
		}

		public String import_flagDefault() {

			return null;

		}

		public String import_flagComment() {

			return "";

		}

		public String import_flagPattern() {

			return "";

		}

		public String import_flagOriginalDbColumnName() {

			return "import_flag";

		}

		public String export_flag;

		public String getExport_flag() {
			return this.export_flag;
		}

		public Boolean export_flagIsNullable() {
			return true;
		}

		public Boolean export_flagIsKey() {
			return false;
		}

		public Integer export_flagLength() {
			return null;
		}

		public Integer export_flagPrecision() {
			return null;
		}

		public String export_flagDefault() {

			return null;

		}

		public String export_flagComment() {

			return "";

		}

		public String export_flagPattern() {

			return "";

		}

		public String export_flagOriginalDbColumnName() {

			return "export_flag";

		}

		public String supplier_code;

		public String getSupplier_code() {
			return this.supplier_code;
		}

		public Boolean supplier_codeIsNullable() {
			return true;
		}

		public Boolean supplier_codeIsKey() {
			return false;
		}

		public Integer supplier_codeLength() {
			return null;
		}

		public Integer supplier_codePrecision() {
			return null;
		}

		public String supplier_codeDefault() {

			return null;

		}

		public String supplier_codeComment() {

			return "";

		}

		public String supplier_codePattern() {

			return "";

		}

		public String supplier_codeOriginalDbColumnName() {

			return "supplier_code";

		}

		public String supplier_name;

		public String getSupplier_name() {
			return this.supplier_name;
		}

		public Boolean supplier_nameIsNullable() {
			return true;
		}

		public Boolean supplier_nameIsKey() {
			return false;
		}

		public Integer supplier_nameLength() {
			return null;
		}

		public Integer supplier_namePrecision() {
			return null;
		}

		public String supplier_nameDefault() {

			return null;

		}

		public String supplier_nameComment() {

			return "";

		}

		public String supplier_namePattern() {

			return "";

		}

		public String supplier_nameOriginalDbColumnName() {

			return "supplier_name";

		}

		public String SUPPLIER_REF_NO;

		public String getSUPPLIER_REF_NO() {
			return this.SUPPLIER_REF_NO;
		}

		public Boolean SUPPLIER_REF_NOIsNullable() {
			return true;
		}

		public Boolean SUPPLIER_REF_NOIsKey() {
			return false;
		}

		public Integer SUPPLIER_REF_NOLength() {
			return null;
		}

		public Integer SUPPLIER_REF_NOPrecision() {
			return null;
		}

		public String SUPPLIER_REF_NODefault() {

			return null;

		}

		public String SUPPLIER_REF_NOComment() {

			return "";

		}

		public String SUPPLIER_REF_NOPattern() {

			return "";

		}

		public String SUPPLIER_REF_NOOriginalDbColumnName() {

			return "SUPPLIER_REF_NO";

		}

		public String brand_group;

		public String getBrand_group() {
			return this.brand_group;
		}

		public Boolean brand_groupIsNullable() {
			return true;
		}

		public Boolean brand_groupIsKey() {
			return false;
		}

		public Integer brand_groupLength() {
			return null;
		}

		public Integer brand_groupPrecision() {
			return null;
		}

		public String brand_groupDefault() {

			return null;

		}

		public String brand_groupComment() {

			return "";

		}

		public String brand_groupPattern() {

			return "";

		}

		public String brand_groupOriginalDbColumnName() {

			return "brand_group";

		}

		public String Nation_Sent;

		public String getNation_Sent() {
			return this.Nation_Sent;
		}

		public Boolean Nation_SentIsNullable() {
			return true;
		}

		public Boolean Nation_SentIsKey() {
			return false;
		}

		public Integer Nation_SentLength() {
			return null;
		}

		public Integer Nation_SentPrecision() {
			return null;
		}

		public String Nation_SentDefault() {

			return null;

		}

		public String Nation_SentComment() {

			return "";

		}

		public String Nation_SentPattern() {

			return "";

		}

		public String Nation_SentOriginalDbColumnName() {

			return "Nation_Sent";

		}

		public Integer net_desp_units;

		public Integer getNet_desp_units() {
			return this.net_desp_units;
		}

		public Boolean net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean net_desp_unitsIsKey() {
			return false;
		}

		public Integer net_desp_unitsLength() {
			return null;
		}

		public Integer net_desp_unitsPrecision() {
			return null;
		}

		public String net_desp_unitsDefault() {

			return null;

		}

		public String net_desp_unitsComment() {

			return "";

		}

		public String net_desp_unitsPattern() {

			return "";

		}

		public String net_desp_unitsOriginalDbColumnName() {

			return "net_desp_units";

		}

		public Integer uk_net_desp_units;

		public Integer getUk_net_desp_units() {
			return this.uk_net_desp_units;
		}

		public Boolean uk_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean uk_net_desp_unitsIsKey() {
			return false;
		}

		public Integer uk_net_desp_unitsLength() {
			return null;
		}

		public Integer uk_net_desp_unitsPrecision() {
			return null;
		}

		public String uk_net_desp_unitsDefault() {

			return null;

		}

		public String uk_net_desp_unitsComment() {

			return "";

		}

		public String uk_net_desp_unitsPattern() {

			return "";

		}

		public String uk_net_desp_unitsOriginalDbColumnName() {

			return "uk_net_desp_units";

		}

		public Integer int_net_desp_units;

		public Integer getInt_net_desp_units() {
			return this.int_net_desp_units;
		}

		public Boolean int_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean int_net_desp_unitsIsKey() {
			return false;
		}

		public Integer int_net_desp_unitsLength() {
			return null;
		}

		public Integer int_net_desp_unitsPrecision() {
			return null;
		}

		public String int_net_desp_unitsDefault() {

			return null;

		}

		public String int_net_desp_unitsComment() {

			return "";

		}

		public String int_net_desp_unitsPattern() {

			return "";

		}

		public String int_net_desp_unitsOriginalDbColumnName() {

			return "int_net_desp_units";

		}

		public String LocationCode;

		public String getLocationCode() {
			return this.LocationCode;
		}

		public Boolean LocationCodeIsNullable() {
			return true;
		}

		public Boolean LocationCodeIsKey() {
			return false;
		}

		public Integer LocationCodeLength() {
			return null;
		}

		public Integer LocationCodePrecision() {
			return null;
		}

		public String LocationCodeDefault() {

			return null;

		}

		public String LocationCodeComment() {

			return "";

		}

		public String LocationCodePattern() {

			return "";

		}

		public String LocationCodeOriginalDbColumnName() {

			return "LocationCode";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PRODUCT_LINE_CODE=" + PRODUCT_LINE_CODE);
			sb.append(",calender_month=" + calender_month);
			sb.append(",Product_Code=" + Product_Code);
			sb.append(",Product_Name=" + Product_Name);
			sb.append(",division=" + division);
			sb.append(",dept=" + dept);
			sb.append(",rnge=" + rnge);
			sb.append(",import_flag=" + import_flag);
			sb.append(",export_flag=" + export_flag);
			sb.append(",supplier_code=" + supplier_code);
			sb.append(",supplier_name=" + supplier_name);
			sb.append(",SUPPLIER_REF_NO=" + SUPPLIER_REF_NO);
			sb.append(",brand_group=" + brand_group);
			sb.append(",Nation_Sent=" + Nation_Sent);
			sb.append(",net_desp_units=" + String.valueOf(net_desp_units));
			sb.append(",uk_net_desp_units=" + String.valueOf(uk_net_desp_units));
			sb.append(",int_net_desp_units=" + String.valueOf(int_net_desp_units));
			sb.append(",LocationCode=" + LocationCode);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PRODUCT_LINE_CODE == null) {
				sb.append("<null>");
			} else {
				sb.append(PRODUCT_LINE_CODE);
			}

			sb.append("|");

			if (calender_month == null) {
				sb.append("<null>");
			} else {
				sb.append(calender_month);
			}

			sb.append("|");

			if (Product_Code == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Code);
			}

			sb.append("|");

			if (Product_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Name);
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

			if (import_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(import_flag);
			}

			sb.append("|");

			if (export_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(export_flag);
			}

			sb.append("|");

			if (supplier_code == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_code);
			}

			sb.append("|");

			if (supplier_name == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_name);
			}

			sb.append("|");

			if (SUPPLIER_REF_NO == null) {
				sb.append("<null>");
			} else {
				sb.append(SUPPLIER_REF_NO);
			}

			sb.append("|");

			if (brand_group == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_group);
			}

			sb.append("|");

			if (Nation_Sent == null) {
				sb.append("<null>");
			} else {
				sb.append(Nation_Sent);
			}

			sb.append("|");

			if (net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(net_desp_units);
			}

			sb.append("|");

			if (uk_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(uk_net_desp_units);
			}

			sb.append("|");

			if (int_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(int_net_desp_units);
			}

			sb.append("|");

			if (LocationCode == null) {
				sb.append("<null>");
			} else {
				sb.append(LocationCode);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

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

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];

		public String PRODUCT_LINE_CODE;

		public String getPRODUCT_LINE_CODE() {
			return this.PRODUCT_LINE_CODE;
		}

		public Boolean PRODUCT_LINE_CODEIsNullable() {
			return true;
		}

		public Boolean PRODUCT_LINE_CODEIsKey() {
			return false;
		}

		public Integer PRODUCT_LINE_CODELength() {
			return null;
		}

		public Integer PRODUCT_LINE_CODEPrecision() {
			return null;
		}

		public String PRODUCT_LINE_CODEDefault() {

			return null;

		}

		public String PRODUCT_LINE_CODEComment() {

			return "";

		}

		public String PRODUCT_LINE_CODEPattern() {

			return "";

		}

		public String PRODUCT_LINE_CODEOriginalDbColumnName() {

			return "PRODUCT_LINE_CODE";

		}

		public String calender_month;

		public String getCalender_month() {
			return this.calender_month;
		}

		public Boolean calender_monthIsNullable() {
			return true;
		}

		public Boolean calender_monthIsKey() {
			return false;
		}

		public Integer calender_monthLength() {
			return null;
		}

		public Integer calender_monthPrecision() {
			return null;
		}

		public String calender_monthDefault() {

			return null;

		}

		public String calender_monthComment() {

			return "";

		}

		public String calender_monthPattern() {

			return "";

		}

		public String calender_monthOriginalDbColumnName() {

			return "calender_month";

		}

		public String Product_Code;

		public String getProduct_Code() {
			return this.Product_Code;
		}

		public Boolean Product_CodeIsNullable() {
			return true;
		}

		public Boolean Product_CodeIsKey() {
			return false;
		}

		public Integer Product_CodeLength() {
			return null;
		}

		public Integer Product_CodePrecision() {
			return null;
		}

		public String Product_CodeDefault() {

			return null;

		}

		public String Product_CodeComment() {

			return "";

		}

		public String Product_CodePattern() {

			return "";

		}

		public String Product_CodeOriginalDbColumnName() {

			return "Product_Code";

		}

		public String Product_Name;

		public String getProduct_Name() {
			return this.Product_Name;
		}

		public Boolean Product_NameIsNullable() {
			return true;
		}

		public Boolean Product_NameIsKey() {
			return false;
		}

		public Integer Product_NameLength() {
			return null;
		}

		public Integer Product_NamePrecision() {
			return null;
		}

		public String Product_NameDefault() {

			return null;

		}

		public String Product_NameComment() {

			return "";

		}

		public String Product_NamePattern() {

			return "";

		}

		public String Product_NameOriginalDbColumnName() {

			return "Product_Name";

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

		public String import_flag;

		public String getImport_flag() {
			return this.import_flag;
		}

		public Boolean import_flagIsNullable() {
			return true;
		}

		public Boolean import_flagIsKey() {
			return false;
		}

		public Integer import_flagLength() {
			return null;
		}

		public Integer import_flagPrecision() {
			return null;
		}

		public String import_flagDefault() {

			return null;

		}

		public String import_flagComment() {

			return "";

		}

		public String import_flagPattern() {

			return "";

		}

		public String import_flagOriginalDbColumnName() {

			return "import_flag";

		}

		public String export_flag;

		public String getExport_flag() {
			return this.export_flag;
		}

		public Boolean export_flagIsNullable() {
			return true;
		}

		public Boolean export_flagIsKey() {
			return false;
		}

		public Integer export_flagLength() {
			return null;
		}

		public Integer export_flagPrecision() {
			return null;
		}

		public String export_flagDefault() {

			return null;

		}

		public String export_flagComment() {

			return "";

		}

		public String export_flagPattern() {

			return "";

		}

		public String export_flagOriginalDbColumnName() {

			return "export_flag";

		}

		public String supplier_code;

		public String getSupplier_code() {
			return this.supplier_code;
		}

		public Boolean supplier_codeIsNullable() {
			return true;
		}

		public Boolean supplier_codeIsKey() {
			return false;
		}

		public Integer supplier_codeLength() {
			return null;
		}

		public Integer supplier_codePrecision() {
			return null;
		}

		public String supplier_codeDefault() {

			return null;

		}

		public String supplier_codeComment() {

			return "";

		}

		public String supplier_codePattern() {

			return "";

		}

		public String supplier_codeOriginalDbColumnName() {

			return "supplier_code";

		}

		public String supplier_name;

		public String getSupplier_name() {
			return this.supplier_name;
		}

		public Boolean supplier_nameIsNullable() {
			return true;
		}

		public Boolean supplier_nameIsKey() {
			return false;
		}

		public Integer supplier_nameLength() {
			return null;
		}

		public Integer supplier_namePrecision() {
			return null;
		}

		public String supplier_nameDefault() {

			return null;

		}

		public String supplier_nameComment() {

			return "";

		}

		public String supplier_namePattern() {

			return "";

		}

		public String supplier_nameOriginalDbColumnName() {

			return "supplier_name";

		}

		public String SUPPLIER_REF_NO;

		public String getSUPPLIER_REF_NO() {
			return this.SUPPLIER_REF_NO;
		}

		public Boolean SUPPLIER_REF_NOIsNullable() {
			return true;
		}

		public Boolean SUPPLIER_REF_NOIsKey() {
			return false;
		}

		public Integer SUPPLIER_REF_NOLength() {
			return null;
		}

		public Integer SUPPLIER_REF_NOPrecision() {
			return null;
		}

		public String SUPPLIER_REF_NODefault() {

			return null;

		}

		public String SUPPLIER_REF_NOComment() {

			return "";

		}

		public String SUPPLIER_REF_NOPattern() {

			return "";

		}

		public String SUPPLIER_REF_NOOriginalDbColumnName() {

			return "SUPPLIER_REF_NO";

		}

		public String brand_group;

		public String getBrand_group() {
			return this.brand_group;
		}

		public Boolean brand_groupIsNullable() {
			return true;
		}

		public Boolean brand_groupIsKey() {
			return false;
		}

		public Integer brand_groupLength() {
			return null;
		}

		public Integer brand_groupPrecision() {
			return null;
		}

		public String brand_groupDefault() {

			return null;

		}

		public String brand_groupComment() {

			return "";

		}

		public String brand_groupPattern() {

			return "";

		}

		public String brand_groupOriginalDbColumnName() {

			return "brand_group";

		}

		public String Nation_Sent;

		public String getNation_Sent() {
			return this.Nation_Sent;
		}

		public Boolean Nation_SentIsNullable() {
			return true;
		}

		public Boolean Nation_SentIsKey() {
			return false;
		}

		public Integer Nation_SentLength() {
			return null;
		}

		public Integer Nation_SentPrecision() {
			return null;
		}

		public String Nation_SentDefault() {

			return null;

		}

		public String Nation_SentComment() {

			return "";

		}

		public String Nation_SentPattern() {

			return "";

		}

		public String Nation_SentOriginalDbColumnName() {

			return "Nation_Sent";

		}

		public Integer net_desp_units;

		public Integer getNet_desp_units() {
			return this.net_desp_units;
		}

		public Boolean net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean net_desp_unitsIsKey() {
			return false;
		}

		public Integer net_desp_unitsLength() {
			return null;
		}

		public Integer net_desp_unitsPrecision() {
			return null;
		}

		public String net_desp_unitsDefault() {

			return null;

		}

		public String net_desp_unitsComment() {

			return "";

		}

		public String net_desp_unitsPattern() {

			return "";

		}

		public String net_desp_unitsOriginalDbColumnName() {

			return "net_desp_units";

		}

		public Integer uk_net_desp_units;

		public Integer getUk_net_desp_units() {
			return this.uk_net_desp_units;
		}

		public Boolean uk_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean uk_net_desp_unitsIsKey() {
			return false;
		}

		public Integer uk_net_desp_unitsLength() {
			return null;
		}

		public Integer uk_net_desp_unitsPrecision() {
			return null;
		}

		public String uk_net_desp_unitsDefault() {

			return null;

		}

		public String uk_net_desp_unitsComment() {

			return "";

		}

		public String uk_net_desp_unitsPattern() {

			return "";

		}

		public String uk_net_desp_unitsOriginalDbColumnName() {

			return "uk_net_desp_units";

		}

		public Integer int_net_desp_units;

		public Integer getInt_net_desp_units() {
			return this.int_net_desp_units;
		}

		public Boolean int_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean int_net_desp_unitsIsKey() {
			return false;
		}

		public Integer int_net_desp_unitsLength() {
			return null;
		}

		public Integer int_net_desp_unitsPrecision() {
			return null;
		}

		public String int_net_desp_unitsDefault() {

			return null;

		}

		public String int_net_desp_unitsComment() {

			return "";

		}

		public String int_net_desp_unitsPattern() {

			return "";

		}

		public String int_net_desp_unitsOriginalDbColumnName() {

			return "int_net_desp_units";

		}

		public String LocationCode;

		public String getLocationCode() {
			return this.LocationCode;
		}

		public Boolean LocationCodeIsNullable() {
			return true;
		}

		public Boolean LocationCodeIsKey() {
			return false;
		}

		public Integer LocationCodeLength() {
			return null;
		}

		public Integer LocationCodePrecision() {
			return null;
		}

		public String LocationCodeDefault() {

			return null;

		}

		public String LocationCodeComment() {

			return "";

		}

		public String LocationCodePattern() {

			return "";

		}

		public String LocationCodeOriginalDbColumnName() {

			return "LocationCode";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PRODUCT_LINE_CODE=" + PRODUCT_LINE_CODE);
			sb.append(",calender_month=" + calender_month);
			sb.append(",Product_Code=" + Product_Code);
			sb.append(",Product_Name=" + Product_Name);
			sb.append(",division=" + division);
			sb.append(",dept=" + dept);
			sb.append(",rnge=" + rnge);
			sb.append(",import_flag=" + import_flag);
			sb.append(",export_flag=" + export_flag);
			sb.append(",supplier_code=" + supplier_code);
			sb.append(",supplier_name=" + supplier_name);
			sb.append(",SUPPLIER_REF_NO=" + SUPPLIER_REF_NO);
			sb.append(",brand_group=" + brand_group);
			sb.append(",Nation_Sent=" + Nation_Sent);
			sb.append(",net_desp_units=" + String.valueOf(net_desp_units));
			sb.append(",uk_net_desp_units=" + String.valueOf(uk_net_desp_units));
			sb.append(",int_net_desp_units=" + String.valueOf(int_net_desp_units));
			sb.append(",LocationCode=" + LocationCode);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PRODUCT_LINE_CODE == null) {
				sb.append("<null>");
			} else {
				sb.append(PRODUCT_LINE_CODE);
			}

			sb.append("|");

			if (calender_month == null) {
				sb.append("<null>");
			} else {
				sb.append(calender_month);
			}

			sb.append("|");

			if (Product_Code == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Code);
			}

			sb.append("|");

			if (Product_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Name);
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

			if (import_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(import_flag);
			}

			sb.append("|");

			if (export_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(export_flag);
			}

			sb.append("|");

			if (supplier_code == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_code);
			}

			sb.append("|");

			if (supplier_name == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_name);
			}

			sb.append("|");

			if (SUPPLIER_REF_NO == null) {
				sb.append("<null>");
			} else {
				sb.append(SUPPLIER_REF_NO);
			}

			sb.append("|");

			if (brand_group == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_group);
			}

			sb.append("|");

			if (Nation_Sent == null) {
				sb.append("<null>");
			} else {
				sb.append(Nation_Sent);
			}

			sb.append("|");

			if (net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(net_desp_units);
			}

			sb.append("|");

			if (uk_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(uk_net_desp_units);
			}

			sb.append("|");

			if (int_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(int_net_desp_units);
			}

			sb.append("|");

			if (LocationCode == null) {
				sb.append("<null>");
			} else {
				sb.append(LocationCode);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

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

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];

		public String PRODUCT_LINE_CODE;

		public String getPRODUCT_LINE_CODE() {
			return this.PRODUCT_LINE_CODE;
		}

		public Boolean PRODUCT_LINE_CODEIsNullable() {
			return true;
		}

		public Boolean PRODUCT_LINE_CODEIsKey() {
			return false;
		}

		public Integer PRODUCT_LINE_CODELength() {
			return null;
		}

		public Integer PRODUCT_LINE_CODEPrecision() {
			return null;
		}

		public String PRODUCT_LINE_CODEDefault() {

			return null;

		}

		public String PRODUCT_LINE_CODEComment() {

			return "";

		}

		public String PRODUCT_LINE_CODEPattern() {

			return "";

		}

		public String PRODUCT_LINE_CODEOriginalDbColumnName() {

			return "PRODUCT_LINE_CODE";

		}

		public String calender_month;

		public String getCalender_month() {
			return this.calender_month;
		}

		public Boolean calender_monthIsNullable() {
			return true;
		}

		public Boolean calender_monthIsKey() {
			return false;
		}

		public Integer calender_monthLength() {
			return null;
		}

		public Integer calender_monthPrecision() {
			return null;
		}

		public String calender_monthDefault() {

			return null;

		}

		public String calender_monthComment() {

			return "";

		}

		public String calender_monthPattern() {

			return "";

		}

		public String calender_monthOriginalDbColumnName() {

			return "calender_month";

		}

		public String Product_Code;

		public String getProduct_Code() {
			return this.Product_Code;
		}

		public Boolean Product_CodeIsNullable() {
			return true;
		}

		public Boolean Product_CodeIsKey() {
			return false;
		}

		public Integer Product_CodeLength() {
			return null;
		}

		public Integer Product_CodePrecision() {
			return null;
		}

		public String Product_CodeDefault() {

			return null;

		}

		public String Product_CodeComment() {

			return "";

		}

		public String Product_CodePattern() {

			return "";

		}

		public String Product_CodeOriginalDbColumnName() {

			return "Product_Code";

		}

		public String Product_Name;

		public String getProduct_Name() {
			return this.Product_Name;
		}

		public Boolean Product_NameIsNullable() {
			return true;
		}

		public Boolean Product_NameIsKey() {
			return false;
		}

		public Integer Product_NameLength() {
			return null;
		}

		public Integer Product_NamePrecision() {
			return null;
		}

		public String Product_NameDefault() {

			return null;

		}

		public String Product_NameComment() {

			return "";

		}

		public String Product_NamePattern() {

			return "";

		}

		public String Product_NameOriginalDbColumnName() {

			return "Product_Name";

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

		public String import_flag;

		public String getImport_flag() {
			return this.import_flag;
		}

		public Boolean import_flagIsNullable() {
			return true;
		}

		public Boolean import_flagIsKey() {
			return false;
		}

		public Integer import_flagLength() {
			return null;
		}

		public Integer import_flagPrecision() {
			return null;
		}

		public String import_flagDefault() {

			return null;

		}

		public String import_flagComment() {

			return "";

		}

		public String import_flagPattern() {

			return "";

		}

		public String import_flagOriginalDbColumnName() {

			return "import_flag";

		}

		public String export_flag;

		public String getExport_flag() {
			return this.export_flag;
		}

		public Boolean export_flagIsNullable() {
			return true;
		}

		public Boolean export_flagIsKey() {
			return false;
		}

		public Integer export_flagLength() {
			return null;
		}

		public Integer export_flagPrecision() {
			return null;
		}

		public String export_flagDefault() {

			return null;

		}

		public String export_flagComment() {

			return "";

		}

		public String export_flagPattern() {

			return "";

		}

		public String export_flagOriginalDbColumnName() {

			return "export_flag";

		}

		public String supplier_code;

		public String getSupplier_code() {
			return this.supplier_code;
		}

		public Boolean supplier_codeIsNullable() {
			return true;
		}

		public Boolean supplier_codeIsKey() {
			return false;
		}

		public Integer supplier_codeLength() {
			return null;
		}

		public Integer supplier_codePrecision() {
			return null;
		}

		public String supplier_codeDefault() {

			return null;

		}

		public String supplier_codeComment() {

			return "";

		}

		public String supplier_codePattern() {

			return "";

		}

		public String supplier_codeOriginalDbColumnName() {

			return "supplier_code";

		}

		public String supplier_name;

		public String getSupplier_name() {
			return this.supplier_name;
		}

		public Boolean supplier_nameIsNullable() {
			return true;
		}

		public Boolean supplier_nameIsKey() {
			return false;
		}

		public Integer supplier_nameLength() {
			return null;
		}

		public Integer supplier_namePrecision() {
			return null;
		}

		public String supplier_nameDefault() {

			return null;

		}

		public String supplier_nameComment() {

			return "";

		}

		public String supplier_namePattern() {

			return "";

		}

		public String supplier_nameOriginalDbColumnName() {

			return "supplier_name";

		}

		public String SUPPLIER_REF_NO;

		public String getSUPPLIER_REF_NO() {
			return this.SUPPLIER_REF_NO;
		}

		public Boolean SUPPLIER_REF_NOIsNullable() {
			return true;
		}

		public Boolean SUPPLIER_REF_NOIsKey() {
			return false;
		}

		public Integer SUPPLIER_REF_NOLength() {
			return null;
		}

		public Integer SUPPLIER_REF_NOPrecision() {
			return null;
		}

		public String SUPPLIER_REF_NODefault() {

			return null;

		}

		public String SUPPLIER_REF_NOComment() {

			return "";

		}

		public String SUPPLIER_REF_NOPattern() {

			return "";

		}

		public String SUPPLIER_REF_NOOriginalDbColumnName() {

			return "SUPPLIER_REF_NO";

		}

		public String brand_group;

		public String getBrand_group() {
			return this.brand_group;
		}

		public Boolean brand_groupIsNullable() {
			return true;
		}

		public Boolean brand_groupIsKey() {
			return false;
		}

		public Integer brand_groupLength() {
			return null;
		}

		public Integer brand_groupPrecision() {
			return null;
		}

		public String brand_groupDefault() {

			return null;

		}

		public String brand_groupComment() {

			return "";

		}

		public String brand_groupPattern() {

			return "";

		}

		public String brand_groupOriginalDbColumnName() {

			return "brand_group";

		}

		public String Nation_Sent;

		public String getNation_Sent() {
			return this.Nation_Sent;
		}

		public Boolean Nation_SentIsNullable() {
			return true;
		}

		public Boolean Nation_SentIsKey() {
			return false;
		}

		public Integer Nation_SentLength() {
			return null;
		}

		public Integer Nation_SentPrecision() {
			return null;
		}

		public String Nation_SentDefault() {

			return null;

		}

		public String Nation_SentComment() {

			return "";

		}

		public String Nation_SentPattern() {

			return "";

		}

		public String Nation_SentOriginalDbColumnName() {

			return "Nation_Sent";

		}

		public Integer net_desp_units;

		public Integer getNet_desp_units() {
			return this.net_desp_units;
		}

		public Boolean net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean net_desp_unitsIsKey() {
			return false;
		}

		public Integer net_desp_unitsLength() {
			return null;
		}

		public Integer net_desp_unitsPrecision() {
			return null;
		}

		public String net_desp_unitsDefault() {

			return null;

		}

		public String net_desp_unitsComment() {

			return "";

		}

		public String net_desp_unitsPattern() {

			return "";

		}

		public String net_desp_unitsOriginalDbColumnName() {

			return "net_desp_units";

		}

		public Integer uk_net_desp_units;

		public Integer getUk_net_desp_units() {
			return this.uk_net_desp_units;
		}

		public Boolean uk_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean uk_net_desp_unitsIsKey() {
			return false;
		}

		public Integer uk_net_desp_unitsLength() {
			return null;
		}

		public Integer uk_net_desp_unitsPrecision() {
			return null;
		}

		public String uk_net_desp_unitsDefault() {

			return null;

		}

		public String uk_net_desp_unitsComment() {

			return "";

		}

		public String uk_net_desp_unitsPattern() {

			return "";

		}

		public String uk_net_desp_unitsOriginalDbColumnName() {

			return "uk_net_desp_units";

		}

		public Integer int_net_desp_units;

		public Integer getInt_net_desp_units() {
			return this.int_net_desp_units;
		}

		public Boolean int_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean int_net_desp_unitsIsKey() {
			return false;
		}

		public Integer int_net_desp_unitsLength() {
			return null;
		}

		public Integer int_net_desp_unitsPrecision() {
			return null;
		}

		public String int_net_desp_unitsDefault() {

			return null;

		}

		public String int_net_desp_unitsComment() {

			return "";

		}

		public String int_net_desp_unitsPattern() {

			return "";

		}

		public String int_net_desp_unitsOriginalDbColumnName() {

			return "int_net_desp_units";

		}

		public String LocationCode;

		public String getLocationCode() {
			return this.LocationCode;
		}

		public Boolean LocationCodeIsNullable() {
			return true;
		}

		public Boolean LocationCodeIsKey() {
			return false;
		}

		public Integer LocationCodeLength() {
			return null;
		}

		public Integer LocationCodePrecision() {
			return null;
		}

		public String LocationCodeDefault() {

			return null;

		}

		public String LocationCodeComment() {

			return "";

		}

		public String LocationCodePattern() {

			return "";

		}

		public String LocationCodeOriginalDbColumnName() {

			return "LocationCode";

		}

		public String errorMessage;

		public String getErrorMessage() {
			return this.errorMessage;
		}

		public Boolean errorMessageIsNullable() {
			return true;
		}

		public Boolean errorMessageIsKey() {
			return false;
		}

		public Integer errorMessageLength() {
			return 255;
		}

		public Integer errorMessagePrecision() {
			return 0;
		}

		public String errorMessageDefault() {

			return "";

		}

		public String errorMessageComment() {

			return null;

		}

		public String errorMessagePattern() {

			return null;

		}

		public String errorMessageOriginalDbColumnName() {

			return "errorMessage";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PRODUCT_LINE_CODE=" + PRODUCT_LINE_CODE);
			sb.append(",calender_month=" + calender_month);
			sb.append(",Product_Code=" + Product_Code);
			sb.append(",Product_Name=" + Product_Name);
			sb.append(",division=" + division);
			sb.append(",dept=" + dept);
			sb.append(",rnge=" + rnge);
			sb.append(",import_flag=" + import_flag);
			sb.append(",export_flag=" + export_flag);
			sb.append(",supplier_code=" + supplier_code);
			sb.append(",supplier_name=" + supplier_name);
			sb.append(",SUPPLIER_REF_NO=" + SUPPLIER_REF_NO);
			sb.append(",brand_group=" + brand_group);
			sb.append(",Nation_Sent=" + Nation_Sent);
			sb.append(",net_desp_units=" + String.valueOf(net_desp_units));
			sb.append(",uk_net_desp_units=" + String.valueOf(uk_net_desp_units));
			sb.append(",int_net_desp_units=" + String.valueOf(int_net_desp_units));
			sb.append(",LocationCode=" + LocationCode);
			sb.append(",errorMessage=" + errorMessage);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PRODUCT_LINE_CODE == null) {
				sb.append("<null>");
			} else {
				sb.append(PRODUCT_LINE_CODE);
			}

			sb.append("|");

			if (calender_month == null) {
				sb.append("<null>");
			} else {
				sb.append(calender_month);
			}

			sb.append("|");

			if (Product_Code == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Code);
			}

			sb.append("|");

			if (Product_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Name);
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

			if (import_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(import_flag);
			}

			sb.append("|");

			if (export_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(export_flag);
			}

			sb.append("|");

			if (supplier_code == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_code);
			}

			sb.append("|");

			if (supplier_name == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_name);
			}

			sb.append("|");

			if (SUPPLIER_REF_NO == null) {
				sb.append("<null>");
			} else {
				sb.append(SUPPLIER_REF_NO);
			}

			sb.append("|");

			if (brand_group == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_group);
			}

			sb.append("|");

			if (Nation_Sent == null) {
				sb.append("<null>");
			} else {
				sb.append(Nation_Sent);
			}

			sb.append("|");

			if (net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(net_desp_units);
			}

			sb.append("|");

			if (uk_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(uk_net_desp_units);
			}

			sb.append("|");

			if (int_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(int_net_desp_units);
			}

			sb.append("|");

			if (LocationCode == null) {
				sb.append("<null>");
			} else {
				sb.append(LocationCode);
			}

			sb.append("|");

			if (errorMessage == null) {
				sb.append("<null>");
			} else {
				sb.append(errorMessage);
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

	public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];

		public String PRODUCT_LINE_CODE;

		public String getPRODUCT_LINE_CODE() {
			return this.PRODUCT_LINE_CODE;
		}

		public Boolean PRODUCT_LINE_CODEIsNullable() {
			return true;
		}

		public Boolean PRODUCT_LINE_CODEIsKey() {
			return false;
		}

		public Integer PRODUCT_LINE_CODELength() {
			return null;
		}

		public Integer PRODUCT_LINE_CODEPrecision() {
			return null;
		}

		public String PRODUCT_LINE_CODEDefault() {

			return null;

		}

		public String PRODUCT_LINE_CODEComment() {

			return "";

		}

		public String PRODUCT_LINE_CODEPattern() {

			return "";

		}

		public String PRODUCT_LINE_CODEOriginalDbColumnName() {

			return "PRODUCT_LINE_CODE";

		}

		public String calender_month;

		public String getCalender_month() {
			return this.calender_month;
		}

		public Boolean calender_monthIsNullable() {
			return true;
		}

		public Boolean calender_monthIsKey() {
			return false;
		}

		public Integer calender_monthLength() {
			return null;
		}

		public Integer calender_monthPrecision() {
			return null;
		}

		public String calender_monthDefault() {

			return null;

		}

		public String calender_monthComment() {

			return "";

		}

		public String calender_monthPattern() {

			return "";

		}

		public String calender_monthOriginalDbColumnName() {

			return "calender_month";

		}

		public String Product_Code;

		public String getProduct_Code() {
			return this.Product_Code;
		}

		public Boolean Product_CodeIsNullable() {
			return true;
		}

		public Boolean Product_CodeIsKey() {
			return false;
		}

		public Integer Product_CodeLength() {
			return null;
		}

		public Integer Product_CodePrecision() {
			return null;
		}

		public String Product_CodeDefault() {

			return null;

		}

		public String Product_CodeComment() {

			return "";

		}

		public String Product_CodePattern() {

			return "";

		}

		public String Product_CodeOriginalDbColumnName() {

			return "Product_Code";

		}

		public String Product_Name;

		public String getProduct_Name() {
			return this.Product_Name;
		}

		public Boolean Product_NameIsNullable() {
			return true;
		}

		public Boolean Product_NameIsKey() {
			return false;
		}

		public Integer Product_NameLength() {
			return null;
		}

		public Integer Product_NamePrecision() {
			return null;
		}

		public String Product_NameDefault() {

			return null;

		}

		public String Product_NameComment() {

			return "";

		}

		public String Product_NamePattern() {

			return "";

		}

		public String Product_NameOriginalDbColumnName() {

			return "Product_Name";

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

		public String import_flag;

		public String getImport_flag() {
			return this.import_flag;
		}

		public Boolean import_flagIsNullable() {
			return true;
		}

		public Boolean import_flagIsKey() {
			return false;
		}

		public Integer import_flagLength() {
			return null;
		}

		public Integer import_flagPrecision() {
			return null;
		}

		public String import_flagDefault() {

			return null;

		}

		public String import_flagComment() {

			return "";

		}

		public String import_flagPattern() {

			return "";

		}

		public String import_flagOriginalDbColumnName() {

			return "import_flag";

		}

		public String export_flag;

		public String getExport_flag() {
			return this.export_flag;
		}

		public Boolean export_flagIsNullable() {
			return true;
		}

		public Boolean export_flagIsKey() {
			return false;
		}

		public Integer export_flagLength() {
			return null;
		}

		public Integer export_flagPrecision() {
			return null;
		}

		public String export_flagDefault() {

			return null;

		}

		public String export_flagComment() {

			return "";

		}

		public String export_flagPattern() {

			return "";

		}

		public String export_flagOriginalDbColumnName() {

			return "export_flag";

		}

		public String supplier_code;

		public String getSupplier_code() {
			return this.supplier_code;
		}

		public Boolean supplier_codeIsNullable() {
			return true;
		}

		public Boolean supplier_codeIsKey() {
			return false;
		}

		public Integer supplier_codeLength() {
			return null;
		}

		public Integer supplier_codePrecision() {
			return null;
		}

		public String supplier_codeDefault() {

			return null;

		}

		public String supplier_codeComment() {

			return "";

		}

		public String supplier_codePattern() {

			return "";

		}

		public String supplier_codeOriginalDbColumnName() {

			return "supplier_code";

		}

		public String supplier_name;

		public String getSupplier_name() {
			return this.supplier_name;
		}

		public Boolean supplier_nameIsNullable() {
			return true;
		}

		public Boolean supplier_nameIsKey() {
			return false;
		}

		public Integer supplier_nameLength() {
			return null;
		}

		public Integer supplier_namePrecision() {
			return null;
		}

		public String supplier_nameDefault() {

			return null;

		}

		public String supplier_nameComment() {

			return "";

		}

		public String supplier_namePattern() {

			return "";

		}

		public String supplier_nameOriginalDbColumnName() {

			return "supplier_name";

		}

		public String SUPPLIER_REF_NO;

		public String getSUPPLIER_REF_NO() {
			return this.SUPPLIER_REF_NO;
		}

		public Boolean SUPPLIER_REF_NOIsNullable() {
			return true;
		}

		public Boolean SUPPLIER_REF_NOIsKey() {
			return false;
		}

		public Integer SUPPLIER_REF_NOLength() {
			return null;
		}

		public Integer SUPPLIER_REF_NOPrecision() {
			return null;
		}

		public String SUPPLIER_REF_NODefault() {

			return null;

		}

		public String SUPPLIER_REF_NOComment() {

			return "";

		}

		public String SUPPLIER_REF_NOPattern() {

			return "";

		}

		public String SUPPLIER_REF_NOOriginalDbColumnName() {

			return "SUPPLIER_REF_NO";

		}

		public String brand_group;

		public String getBrand_group() {
			return this.brand_group;
		}

		public Boolean brand_groupIsNullable() {
			return true;
		}

		public Boolean brand_groupIsKey() {
			return false;
		}

		public Integer brand_groupLength() {
			return null;
		}

		public Integer brand_groupPrecision() {
			return null;
		}

		public String brand_groupDefault() {

			return null;

		}

		public String brand_groupComment() {

			return "";

		}

		public String brand_groupPattern() {

			return "";

		}

		public String brand_groupOriginalDbColumnName() {

			return "brand_group";

		}

		public String Nation_Sent;

		public String getNation_Sent() {
			return this.Nation_Sent;
		}

		public Boolean Nation_SentIsNullable() {
			return true;
		}

		public Boolean Nation_SentIsKey() {
			return false;
		}

		public Integer Nation_SentLength() {
			return null;
		}

		public Integer Nation_SentPrecision() {
			return null;
		}

		public String Nation_SentDefault() {

			return null;

		}

		public String Nation_SentComment() {

			return "";

		}

		public String Nation_SentPattern() {

			return "";

		}

		public String Nation_SentOriginalDbColumnName() {

			return "Nation_Sent";

		}

		public Integer net_desp_units;

		public Integer getNet_desp_units() {
			return this.net_desp_units;
		}

		public Boolean net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean net_desp_unitsIsKey() {
			return false;
		}

		public Integer net_desp_unitsLength() {
			return null;
		}

		public Integer net_desp_unitsPrecision() {
			return null;
		}

		public String net_desp_unitsDefault() {

			return null;

		}

		public String net_desp_unitsComment() {

			return "";

		}

		public String net_desp_unitsPattern() {

			return "";

		}

		public String net_desp_unitsOriginalDbColumnName() {

			return "net_desp_units";

		}

		public Integer uk_net_desp_units;

		public Integer getUk_net_desp_units() {
			return this.uk_net_desp_units;
		}

		public Boolean uk_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean uk_net_desp_unitsIsKey() {
			return false;
		}

		public Integer uk_net_desp_unitsLength() {
			return null;
		}

		public Integer uk_net_desp_unitsPrecision() {
			return null;
		}

		public String uk_net_desp_unitsDefault() {

			return null;

		}

		public String uk_net_desp_unitsComment() {

			return "";

		}

		public String uk_net_desp_unitsPattern() {

			return "";

		}

		public String uk_net_desp_unitsOriginalDbColumnName() {

			return "uk_net_desp_units";

		}

		public Integer int_net_desp_units;

		public Integer getInt_net_desp_units() {
			return this.int_net_desp_units;
		}

		public Boolean int_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean int_net_desp_unitsIsKey() {
			return false;
		}

		public Integer int_net_desp_unitsLength() {
			return null;
		}

		public Integer int_net_desp_unitsPrecision() {
			return null;
		}

		public String int_net_desp_unitsDefault() {

			return null;

		}

		public String int_net_desp_unitsComment() {

			return "";

		}

		public String int_net_desp_unitsPattern() {

			return "";

		}

		public String int_net_desp_unitsOriginalDbColumnName() {

			return "int_net_desp_units";

		}

		public String LocationCode;

		public String getLocationCode() {
			return this.LocationCode;
		}

		public Boolean LocationCodeIsNullable() {
			return true;
		}

		public Boolean LocationCodeIsKey() {
			return false;
		}

		public Integer LocationCodeLength() {
			return null;
		}

		public Integer LocationCodePrecision() {
			return null;
		}

		public String LocationCodeDefault() {

			return null;

		}

		public String LocationCodeComment() {

			return "";

		}

		public String LocationCodePattern() {

			return "";

		}

		public String LocationCodeOriginalDbColumnName() {

			return "LocationCode";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PRODUCT_LINE_CODE=" + PRODUCT_LINE_CODE);
			sb.append(",calender_month=" + calender_month);
			sb.append(",Product_Code=" + Product_Code);
			sb.append(",Product_Name=" + Product_Name);
			sb.append(",division=" + division);
			sb.append(",dept=" + dept);
			sb.append(",rnge=" + rnge);
			sb.append(",import_flag=" + import_flag);
			sb.append(",export_flag=" + export_flag);
			sb.append(",supplier_code=" + supplier_code);
			sb.append(",supplier_name=" + supplier_name);
			sb.append(",SUPPLIER_REF_NO=" + SUPPLIER_REF_NO);
			sb.append(",brand_group=" + brand_group);
			sb.append(",Nation_Sent=" + Nation_Sent);
			sb.append(",net_desp_units=" + String.valueOf(net_desp_units));
			sb.append(",uk_net_desp_units=" + String.valueOf(uk_net_desp_units));
			sb.append(",int_net_desp_units=" + String.valueOf(int_net_desp_units));
			sb.append(",LocationCode=" + LocationCode);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PRODUCT_LINE_CODE == null) {
				sb.append("<null>");
			} else {
				sb.append(PRODUCT_LINE_CODE);
			}

			sb.append("|");

			if (calender_month == null) {
				sb.append("<null>");
			} else {
				sb.append(calender_month);
			}

			sb.append("|");

			if (Product_Code == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Code);
			}

			sb.append("|");

			if (Product_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Name);
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

			if (import_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(import_flag);
			}

			sb.append("|");

			if (export_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(export_flag);
			}

			sb.append("|");

			if (supplier_code == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_code);
			}

			sb.append("|");

			if (supplier_name == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_name);
			}

			sb.append("|");

			if (SUPPLIER_REF_NO == null) {
				sb.append("<null>");
			} else {
				sb.append(SUPPLIER_REF_NO);
			}

			sb.append("|");

			if (brand_group == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_group);
			}

			sb.append("|");

			if (Nation_Sent == null) {
				sb.append("<null>");
			} else {
				sb.append(Nation_Sent);
			}

			sb.append("|");

			if (net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(net_desp_units);
			}

			sb.append("|");

			if (uk_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(uk_net_desp_units);
			}

			sb.append("|");

			if (int_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(int_net_desp_units);
			}

			sb.append("|");

			if (LocationCode == null) {
				sb.append("<null>");
			} else {
				sb.append(LocationCode);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out1Struct other) {

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
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];

		public String PRODUCT_LINE_CODE;

		public String getPRODUCT_LINE_CODE() {
			return this.PRODUCT_LINE_CODE;
		}

		public Boolean PRODUCT_LINE_CODEIsNullable() {
			return true;
		}

		public Boolean PRODUCT_LINE_CODEIsKey() {
			return false;
		}

		public Integer PRODUCT_LINE_CODELength() {
			return null;
		}

		public Integer PRODUCT_LINE_CODEPrecision() {
			return null;
		}

		public String PRODUCT_LINE_CODEDefault() {

			return null;

		}

		public String PRODUCT_LINE_CODEComment() {

			return "";

		}

		public String PRODUCT_LINE_CODEPattern() {

			return "";

		}

		public String PRODUCT_LINE_CODEOriginalDbColumnName() {

			return "PRODUCT_LINE_CODE";

		}

		public String calender_month;

		public String getCalender_month() {
			return this.calender_month;
		}

		public Boolean calender_monthIsNullable() {
			return true;
		}

		public Boolean calender_monthIsKey() {
			return false;
		}

		public Integer calender_monthLength() {
			return null;
		}

		public Integer calender_monthPrecision() {
			return null;
		}

		public String calender_monthDefault() {

			return null;

		}

		public String calender_monthComment() {

			return "";

		}

		public String calender_monthPattern() {

			return "";

		}

		public String calender_monthOriginalDbColumnName() {

			return "calender_month";

		}

		public String PRODUCT_NUMBER;

		public String getPRODUCT_NUMBER() {
			return this.PRODUCT_NUMBER;
		}

		public Boolean PRODUCT_NUMBERIsNullable() {
			return true;
		}

		public Boolean PRODUCT_NUMBERIsKey() {
			return false;
		}

		public Integer PRODUCT_NUMBERLength() {
			return null;
		}

		public Integer PRODUCT_NUMBERPrecision() {
			return null;
		}

		public String PRODUCT_NUMBERDefault() {

			return null;

		}

		public String PRODUCT_NUMBERComment() {

			return "";

		}

		public String PRODUCT_NUMBERPattern() {

			return "";

		}

		public String PRODUCT_NUMBEROriginalDbColumnName() {

			return "PRODUCT_NUMBER";

		}

		public String product_desc;

		public String getProduct_desc() {
			return this.product_desc;
		}

		public Boolean product_descIsNullable() {
			return true;
		}

		public Boolean product_descIsKey() {
			return false;
		}

		public Integer product_descLength() {
			return null;
		}

		public Integer product_descPrecision() {
			return null;
		}

		public String product_descDefault() {

			return null;

		}

		public String product_descComment() {

			return "";

		}

		public String product_descPattern() {

			return "";

		}

		public String product_descOriginalDbColumnName() {

			return "product_desc";

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

		public String import_flag;

		public String getImport_flag() {
			return this.import_flag;
		}

		public Boolean import_flagIsNullable() {
			return true;
		}

		public Boolean import_flagIsKey() {
			return false;
		}

		public Integer import_flagLength() {
			return null;
		}

		public Integer import_flagPrecision() {
			return null;
		}

		public String import_flagDefault() {

			return null;

		}

		public String import_flagComment() {

			return "";

		}

		public String import_flagPattern() {

			return "";

		}

		public String import_flagOriginalDbColumnName() {

			return "import_flag";

		}

		public String export_flag;

		public String getExport_flag() {
			return this.export_flag;
		}

		public Boolean export_flagIsNullable() {
			return true;
		}

		public Boolean export_flagIsKey() {
			return false;
		}

		public Integer export_flagLength() {
			return null;
		}

		public Integer export_flagPrecision() {
			return null;
		}

		public String export_flagDefault() {

			return null;

		}

		public String export_flagComment() {

			return "";

		}

		public String export_flagPattern() {

			return "";

		}

		public String export_flagOriginalDbColumnName() {

			return "export_flag";

		}

		public String SUPPLIER_NUMBER;

		public String getSUPPLIER_NUMBER() {
			return this.SUPPLIER_NUMBER;
		}

		public Boolean SUPPLIER_NUMBERIsNullable() {
			return true;
		}

		public Boolean SUPPLIER_NUMBERIsKey() {
			return false;
		}

		public Integer SUPPLIER_NUMBERLength() {
			return null;
		}

		public Integer SUPPLIER_NUMBERPrecision() {
			return null;
		}

		public String SUPPLIER_NUMBERDefault() {

			return null;

		}

		public String SUPPLIER_NUMBERComment() {

			return "";

		}

		public String SUPPLIER_NUMBERPattern() {

			return "";

		}

		public String SUPPLIER_NUMBEROriginalDbColumnName() {

			return "SUPPLIER_NUMBER";

		}

		public String supplier_name;

		public String getSupplier_name() {
			return this.supplier_name;
		}

		public Boolean supplier_nameIsNullable() {
			return true;
		}

		public Boolean supplier_nameIsKey() {
			return false;
		}

		public Integer supplier_nameLength() {
			return null;
		}

		public Integer supplier_namePrecision() {
			return null;
		}

		public String supplier_nameDefault() {

			return null;

		}

		public String supplier_nameComment() {

			return "";

		}

		public String supplier_namePattern() {

			return "";

		}

		public String supplier_nameOriginalDbColumnName() {

			return "supplier_name";

		}

		public String SUPPLIER_REF_NO;

		public String getSUPPLIER_REF_NO() {
			return this.SUPPLIER_REF_NO;
		}

		public Boolean SUPPLIER_REF_NOIsNullable() {
			return true;
		}

		public Boolean SUPPLIER_REF_NOIsKey() {
			return false;
		}

		public Integer SUPPLIER_REF_NOLength() {
			return null;
		}

		public Integer SUPPLIER_REF_NOPrecision() {
			return null;
		}

		public String SUPPLIER_REF_NODefault() {

			return null;

		}

		public String SUPPLIER_REF_NOComment() {

			return "";

		}

		public String SUPPLIER_REF_NOPattern() {

			return "";

		}

		public String SUPPLIER_REF_NOOriginalDbColumnName() {

			return "SUPPLIER_REF_NO";

		}

		public String brand_group;

		public String getBrand_group() {
			return this.brand_group;
		}

		public Boolean brand_groupIsNullable() {
			return true;
		}

		public Boolean brand_groupIsKey() {
			return false;
		}

		public Integer brand_groupLength() {
			return null;
		}

		public Integer brand_groupPrecision() {
			return null;
		}

		public String brand_groupDefault() {

			return null;

		}

		public String brand_groupComment() {

			return "";

		}

		public String brand_groupPattern() {

			return "";

		}

		public String brand_groupOriginalDbColumnName() {

			return "brand_group";

		}

		public String Nation_Sent;

		public String getNation_Sent() {
			return this.Nation_Sent;
		}

		public Boolean Nation_SentIsNullable() {
			return true;
		}

		public Boolean Nation_SentIsKey() {
			return false;
		}

		public Integer Nation_SentLength() {
			return null;
		}

		public Integer Nation_SentPrecision() {
			return null;
		}

		public String Nation_SentDefault() {

			return null;

		}

		public String Nation_SentComment() {

			return "";

		}

		public String Nation_SentPattern() {

			return "";

		}

		public String Nation_SentOriginalDbColumnName() {

			return "Nation_Sent";

		}

		public Integer net_desp_units;

		public Integer getNet_desp_units() {
			return this.net_desp_units;
		}

		public Boolean net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean net_desp_unitsIsKey() {
			return false;
		}

		public Integer net_desp_unitsLength() {
			return null;
		}

		public Integer net_desp_unitsPrecision() {
			return null;
		}

		public String net_desp_unitsDefault() {

			return null;

		}

		public String net_desp_unitsComment() {

			return "";

		}

		public String net_desp_unitsPattern() {

			return "";

		}

		public String net_desp_unitsOriginalDbColumnName() {

			return "net_desp_units";

		}

		public Integer uk_net_desp_units;

		public Integer getUk_net_desp_units() {
			return this.uk_net_desp_units;
		}

		public Boolean uk_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean uk_net_desp_unitsIsKey() {
			return false;
		}

		public Integer uk_net_desp_unitsLength() {
			return null;
		}

		public Integer uk_net_desp_unitsPrecision() {
			return null;
		}

		public String uk_net_desp_unitsDefault() {

			return null;

		}

		public String uk_net_desp_unitsComment() {

			return "";

		}

		public String uk_net_desp_unitsPattern() {

			return "";

		}

		public String uk_net_desp_unitsOriginalDbColumnName() {

			return "uk_net_desp_units";

		}

		public Integer int_net_desp_units;

		public Integer getInt_net_desp_units() {
			return this.int_net_desp_units;
		}

		public Boolean int_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean int_net_desp_unitsIsKey() {
			return false;
		}

		public Integer int_net_desp_unitsLength() {
			return null;
		}

		public Integer int_net_desp_unitsPrecision() {
			return null;
		}

		public String int_net_desp_unitsDefault() {

			return null;

		}

		public String int_net_desp_unitsComment() {

			return "";

		}

		public String int_net_desp_unitsPattern() {

			return "";

		}

		public String int_net_desp_unitsOriginalDbColumnName() {

			return "int_net_desp_units";

		}

		public String LocationCode;

		public String getLocationCode() {
			return this.LocationCode;
		}

		public Boolean LocationCodeIsNullable() {
			return true;
		}

		public Boolean LocationCodeIsKey() {
			return false;
		}

		public Integer LocationCodeLength() {
			return null;
		}

		public Integer LocationCodePrecision() {
			return null;
		}

		public String LocationCodeDefault() {

			return null;

		}

		public String LocationCodeComment() {

			return "";

		}

		public String LocationCodePattern() {

			return "";

		}

		public String LocationCodeOriginalDbColumnName() {

			return "LocationCode";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.PRODUCT_NUMBER = readString(dis);

					this.product_desc = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.SUPPLIER_NUMBER = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.PRODUCT_NUMBER = readString(dis);

					this.product_desc = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.SUPPLIER_NUMBER = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.PRODUCT_NUMBER, dos);

				// String

				writeString(this.product_desc, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.SUPPLIER_NUMBER, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.PRODUCT_NUMBER, dos);

				// String

				writeString(this.product_desc, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.SUPPLIER_NUMBER, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PRODUCT_LINE_CODE=" + PRODUCT_LINE_CODE);
			sb.append(",calender_month=" + calender_month);
			sb.append(",PRODUCT_NUMBER=" + PRODUCT_NUMBER);
			sb.append(",product_desc=" + product_desc);
			sb.append(",division=" + division);
			sb.append(",dept=" + dept);
			sb.append(",rnge=" + rnge);
			sb.append(",import_flag=" + import_flag);
			sb.append(",export_flag=" + export_flag);
			sb.append(",SUPPLIER_NUMBER=" + SUPPLIER_NUMBER);
			sb.append(",supplier_name=" + supplier_name);
			sb.append(",SUPPLIER_REF_NO=" + SUPPLIER_REF_NO);
			sb.append(",brand_group=" + brand_group);
			sb.append(",Nation_Sent=" + Nation_Sent);
			sb.append(",net_desp_units=" + String.valueOf(net_desp_units));
			sb.append(",uk_net_desp_units=" + String.valueOf(uk_net_desp_units));
			sb.append(",int_net_desp_units=" + String.valueOf(int_net_desp_units));
			sb.append(",LocationCode=" + LocationCode);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PRODUCT_LINE_CODE == null) {
				sb.append("<null>");
			} else {
				sb.append(PRODUCT_LINE_CODE);
			}

			sb.append("|");

			if (calender_month == null) {
				sb.append("<null>");
			} else {
				sb.append(calender_month);
			}

			sb.append("|");

			if (PRODUCT_NUMBER == null) {
				sb.append("<null>");
			} else {
				sb.append(PRODUCT_NUMBER);
			}

			sb.append("|");

			if (product_desc == null) {
				sb.append("<null>");
			} else {
				sb.append(product_desc);
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

			if (import_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(import_flag);
			}

			sb.append("|");

			if (export_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(export_flag);
			}

			sb.append("|");

			if (SUPPLIER_NUMBER == null) {
				sb.append("<null>");
			} else {
				sb.append(SUPPLIER_NUMBER);
			}

			sb.append("|");

			if (supplier_name == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_name);
			}

			sb.append("|");

			if (SUPPLIER_REF_NO == null) {
				sb.append("<null>");
			} else {
				sb.append(SUPPLIER_REF_NO);
			}

			sb.append("|");

			if (brand_group == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_group);
			}

			sb.append("|");

			if (Nation_Sent == null) {
				sb.append("<null>");
			} else {
				sb.append(Nation_Sent);
			}

			sb.append("|");

			if (net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(net_desp_units);
			}

			sb.append("|");

			if (uk_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(uk_net_desp_units);
			}

			sb.append("|");

			if (int_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(int_net_desp_units);
			}

			sb.append("|");

			if (LocationCode == null) {
				sb.append("<null>");
			} else {
				sb.append(LocationCode);
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

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[0];

		public String PRODUCT_LINE_CODE;

		public String getPRODUCT_LINE_CODE() {
			return this.PRODUCT_LINE_CODE;
		}

		public Boolean PRODUCT_LINE_CODEIsNullable() {
			return true;
		}

		public Boolean PRODUCT_LINE_CODEIsKey() {
			return false;
		}

		public Integer PRODUCT_LINE_CODELength() {
			return null;
		}

		public Integer PRODUCT_LINE_CODEPrecision() {
			return null;
		}

		public String PRODUCT_LINE_CODEDefault() {

			return null;

		}

		public String PRODUCT_LINE_CODEComment() {

			return "";

		}

		public String PRODUCT_LINE_CODEPattern() {

			return "";

		}

		public String PRODUCT_LINE_CODEOriginalDbColumnName() {

			return "PRODUCT_LINE_CODE";

		}

		public String calender_month;

		public String getCalender_month() {
			return this.calender_month;
		}

		public Boolean calender_monthIsNullable() {
			return true;
		}

		public Boolean calender_monthIsKey() {
			return false;
		}

		public Integer calender_monthLength() {
			return null;
		}

		public Integer calender_monthPrecision() {
			return null;
		}

		public String calender_monthDefault() {

			return null;

		}

		public String calender_monthComment() {

			return "";

		}

		public String calender_monthPattern() {

			return "";

		}

		public String calender_monthOriginalDbColumnName() {

			return "calender_month";

		}

		public String Product_Code;

		public String getProduct_Code() {
			return this.Product_Code;
		}

		public Boolean Product_CodeIsNullable() {
			return true;
		}

		public Boolean Product_CodeIsKey() {
			return false;
		}

		public Integer Product_CodeLength() {
			return null;
		}

		public Integer Product_CodePrecision() {
			return null;
		}

		public String Product_CodeDefault() {

			return null;

		}

		public String Product_CodeComment() {

			return "";

		}

		public String Product_CodePattern() {

			return "";

		}

		public String Product_CodeOriginalDbColumnName() {

			return "Product_Code";

		}

		public String Product_Name;

		public String getProduct_Name() {
			return this.Product_Name;
		}

		public Boolean Product_NameIsNullable() {
			return true;
		}

		public Boolean Product_NameIsKey() {
			return false;
		}

		public Integer Product_NameLength() {
			return null;
		}

		public Integer Product_NamePrecision() {
			return null;
		}

		public String Product_NameDefault() {

			return null;

		}

		public String Product_NameComment() {

			return "";

		}

		public String Product_NamePattern() {

			return "";

		}

		public String Product_NameOriginalDbColumnName() {

			return "Product_Name";

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

		public String import_flag;

		public String getImport_flag() {
			return this.import_flag;
		}

		public Boolean import_flagIsNullable() {
			return true;
		}

		public Boolean import_flagIsKey() {
			return false;
		}

		public Integer import_flagLength() {
			return null;
		}

		public Integer import_flagPrecision() {
			return null;
		}

		public String import_flagDefault() {

			return null;

		}

		public String import_flagComment() {

			return "";

		}

		public String import_flagPattern() {

			return "";

		}

		public String import_flagOriginalDbColumnName() {

			return "import_flag";

		}

		public String export_flag;

		public String getExport_flag() {
			return this.export_flag;
		}

		public Boolean export_flagIsNullable() {
			return true;
		}

		public Boolean export_flagIsKey() {
			return false;
		}

		public Integer export_flagLength() {
			return null;
		}

		public Integer export_flagPrecision() {
			return null;
		}

		public String export_flagDefault() {

			return null;

		}

		public String export_flagComment() {

			return "";

		}

		public String export_flagPattern() {

			return "";

		}

		public String export_flagOriginalDbColumnName() {

			return "export_flag";

		}

		public String supplier_code;

		public String getSupplier_code() {
			return this.supplier_code;
		}

		public Boolean supplier_codeIsNullable() {
			return true;
		}

		public Boolean supplier_codeIsKey() {
			return false;
		}

		public Integer supplier_codeLength() {
			return null;
		}

		public Integer supplier_codePrecision() {
			return null;
		}

		public String supplier_codeDefault() {

			return null;

		}

		public String supplier_codeComment() {

			return "";

		}

		public String supplier_codePattern() {

			return "";

		}

		public String supplier_codeOriginalDbColumnName() {

			return "supplier_code";

		}

		public String supplier_name;

		public String getSupplier_name() {
			return this.supplier_name;
		}

		public Boolean supplier_nameIsNullable() {
			return true;
		}

		public Boolean supplier_nameIsKey() {
			return false;
		}

		public Integer supplier_nameLength() {
			return null;
		}

		public Integer supplier_namePrecision() {
			return null;
		}

		public String supplier_nameDefault() {

			return null;

		}

		public String supplier_nameComment() {

			return "";

		}

		public String supplier_namePattern() {

			return "";

		}

		public String supplier_nameOriginalDbColumnName() {

			return "supplier_name";

		}

		public String SUPPLIER_REF_NO;

		public String getSUPPLIER_REF_NO() {
			return this.SUPPLIER_REF_NO;
		}

		public Boolean SUPPLIER_REF_NOIsNullable() {
			return true;
		}

		public Boolean SUPPLIER_REF_NOIsKey() {
			return false;
		}

		public Integer SUPPLIER_REF_NOLength() {
			return null;
		}

		public Integer SUPPLIER_REF_NOPrecision() {
			return null;
		}

		public String SUPPLIER_REF_NODefault() {

			return null;

		}

		public String SUPPLIER_REF_NOComment() {

			return "";

		}

		public String SUPPLIER_REF_NOPattern() {

			return "";

		}

		public String SUPPLIER_REF_NOOriginalDbColumnName() {

			return "SUPPLIER_REF_NO";

		}

		public String brand_group;

		public String getBrand_group() {
			return this.brand_group;
		}

		public Boolean brand_groupIsNullable() {
			return true;
		}

		public Boolean brand_groupIsKey() {
			return false;
		}

		public Integer brand_groupLength() {
			return null;
		}

		public Integer brand_groupPrecision() {
			return null;
		}

		public String brand_groupDefault() {

			return null;

		}

		public String brand_groupComment() {

			return "";

		}

		public String brand_groupPattern() {

			return "";

		}

		public String brand_groupOriginalDbColumnName() {

			return "brand_group";

		}

		public String Nation_Sent;

		public String getNation_Sent() {
			return this.Nation_Sent;
		}

		public Boolean Nation_SentIsNullable() {
			return true;
		}

		public Boolean Nation_SentIsKey() {
			return false;
		}

		public Integer Nation_SentLength() {
			return null;
		}

		public Integer Nation_SentPrecision() {
			return null;
		}

		public String Nation_SentDefault() {

			return null;

		}

		public String Nation_SentComment() {

			return "";

		}

		public String Nation_SentPattern() {

			return "";

		}

		public String Nation_SentOriginalDbColumnName() {

			return "Nation_Sent";

		}

		public Integer net_desp_units;

		public Integer getNet_desp_units() {
			return this.net_desp_units;
		}

		public Boolean net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean net_desp_unitsIsKey() {
			return false;
		}

		public Integer net_desp_unitsLength() {
			return null;
		}

		public Integer net_desp_unitsPrecision() {
			return null;
		}

		public String net_desp_unitsDefault() {

			return null;

		}

		public String net_desp_unitsComment() {

			return "";

		}

		public String net_desp_unitsPattern() {

			return "";

		}

		public String net_desp_unitsOriginalDbColumnName() {

			return "net_desp_units";

		}

		public Integer uk_net_desp_units;

		public Integer getUk_net_desp_units() {
			return this.uk_net_desp_units;
		}

		public Boolean uk_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean uk_net_desp_unitsIsKey() {
			return false;
		}

		public Integer uk_net_desp_unitsLength() {
			return null;
		}

		public Integer uk_net_desp_unitsPrecision() {
			return null;
		}

		public String uk_net_desp_unitsDefault() {

			return null;

		}

		public String uk_net_desp_unitsComment() {

			return "";

		}

		public String uk_net_desp_unitsPattern() {

			return "";

		}

		public String uk_net_desp_unitsOriginalDbColumnName() {

			return "uk_net_desp_units";

		}

		public Integer int_net_desp_units;

		public Integer getInt_net_desp_units() {
			return this.int_net_desp_units;
		}

		public Boolean int_net_desp_unitsIsNullable() {
			return true;
		}

		public Boolean int_net_desp_unitsIsKey() {
			return false;
		}

		public Integer int_net_desp_unitsLength() {
			return null;
		}

		public Integer int_net_desp_unitsPrecision() {
			return null;
		}

		public String int_net_desp_unitsDefault() {

			return null;

		}

		public String int_net_desp_unitsComment() {

			return "";

		}

		public String int_net_desp_unitsPattern() {

			return "";

		}

		public String int_net_desp_unitsOriginalDbColumnName() {

			return "int_net_desp_units";

		}

		public String LocationCode;

		public String getLocationCode() {
			return this.LocationCode;
		}

		public Boolean LocationCodeIsNullable() {
			return true;
		}

		public Boolean LocationCodeIsKey() {
			return false;
		}

		public Integer LocationCodeLength() {
			return null;
		}

		public Integer LocationCodePrecision() {
			return null;
		}

		public String LocationCodeDefault() {

			return null;

		}

		public String LocationCodeComment() {

			return "";

		}

		public String LocationCodePattern() {

			return "";

		}

		public String LocationCodeOriginalDbColumnName() {

			return "LocationCode";

		}

		public String ExportFlag;

		public String getExportFlag() {
			return this.ExportFlag;
		}

		public Boolean ExportFlagIsNullable() {
			return true;
		}

		public Boolean ExportFlagIsKey() {
			return false;
		}

		public Integer ExportFlagLength() {
			return null;
		}

		public Integer ExportFlagPrecision() {
			return null;
		}

		public String ExportFlagDefault() {

			return null;

		}

		public String ExportFlagComment() {

			return "";

		}

		public String ExportFlagPattern() {

			return "";

		}

		public String ExportFlagOriginalDbColumnName() {

			return "ExportFlag";

		}

		public Integer SalesQuantity;

		public Integer getSalesQuantity() {
			return this.SalesQuantity;
		}

		public Boolean SalesQuantityIsNullable() {
			return true;
		}

		public Boolean SalesQuantityIsKey() {
			return false;
		}

		public Integer SalesQuantityLength() {
			return null;
		}

		public Integer SalesQuantityPrecision() {
			return null;
		}

		public String SalesQuantityDefault() {

			return null;

		}

		public String SalesQuantityComment() {

			return "";

		}

		public String SalesQuantityPattern() {

			return "";

		}

		public String SalesQuantityOriginalDbColumnName() {

			return "SalesQuantity";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_Hard_Coded, 0, length, utf8Charset);
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

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_Hard_Coded) {

				try {

					int length = 0;

					this.PRODUCT_LINE_CODE = readString(dis);

					this.calender_month = readString(dis);

					this.Product_Code = readString(dis);

					this.Product_Name = readString(dis);

					this.division = readString(dis);

					this.dept = readString(dis);

					this.rnge = readString(dis);

					this.import_flag = readString(dis);

					this.export_flag = readString(dis);

					this.supplier_code = readString(dis);

					this.supplier_name = readString(dis);

					this.SUPPLIER_REF_NO = readString(dis);

					this.brand_group = readString(dis);

					this.Nation_Sent = readString(dis);

					this.net_desp_units = readInteger(dis);

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PRODUCT_LINE_CODE, dos);

				// String

				writeString(this.calender_month, dos);

				// String

				writeString(this.Product_Code, dos);

				// String

				writeString(this.Product_Name, dos);

				// String

				writeString(this.division, dos);

				// String

				writeString(this.dept, dos);

				// String

				writeString(this.rnge, dos);

				// String

				writeString(this.import_flag, dos);

				// String

				writeString(this.export_flag, dos);

				// String

				writeString(this.supplier_code, dos);

				// String

				writeString(this.supplier_name, dos);

				// String

				writeString(this.SUPPLIER_REF_NO, dos);

				// String

				writeString(this.brand_group, dos);

				// String

				writeString(this.Nation_Sent, dos);

				// Integer

				writeInteger(this.net_desp_units, dos);

				// Integer

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// String

				writeString(this.ExportFlag, dos);

				// Integer

				writeInteger(this.SalesQuantity, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PRODUCT_LINE_CODE=" + PRODUCT_LINE_CODE);
			sb.append(",calender_month=" + calender_month);
			sb.append(",Product_Code=" + Product_Code);
			sb.append(",Product_Name=" + Product_Name);
			sb.append(",division=" + division);
			sb.append(",dept=" + dept);
			sb.append(",rnge=" + rnge);
			sb.append(",import_flag=" + import_flag);
			sb.append(",export_flag=" + export_flag);
			sb.append(",supplier_code=" + supplier_code);
			sb.append(",supplier_name=" + supplier_name);
			sb.append(",SUPPLIER_REF_NO=" + SUPPLIER_REF_NO);
			sb.append(",brand_group=" + brand_group);
			sb.append(",Nation_Sent=" + Nation_Sent);
			sb.append(",net_desp_units=" + String.valueOf(net_desp_units));
			sb.append(",uk_net_desp_units=" + String.valueOf(uk_net_desp_units));
			sb.append(",int_net_desp_units=" + String.valueOf(int_net_desp_units));
			sb.append(",LocationCode=" + LocationCode);
			sb.append(",ExportFlag=" + ExportFlag);
			sb.append(",SalesQuantity=" + String.valueOf(SalesQuantity));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PRODUCT_LINE_CODE == null) {
				sb.append("<null>");
			} else {
				sb.append(PRODUCT_LINE_CODE);
			}

			sb.append("|");

			if (calender_month == null) {
				sb.append("<null>");
			} else {
				sb.append(calender_month);
			}

			sb.append("|");

			if (Product_Code == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Code);
			}

			sb.append("|");

			if (Product_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Product_Name);
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

			if (import_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(import_flag);
			}

			sb.append("|");

			if (export_flag == null) {
				sb.append("<null>");
			} else {
				sb.append(export_flag);
			}

			sb.append("|");

			if (supplier_code == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_code);
			}

			sb.append("|");

			if (supplier_name == null) {
				sb.append("<null>");
			} else {
				sb.append(supplier_name);
			}

			sb.append("|");

			if (SUPPLIER_REF_NO == null) {
				sb.append("<null>");
			} else {
				sb.append(SUPPLIER_REF_NO);
			}

			sb.append("|");

			if (brand_group == null) {
				sb.append("<null>");
			} else {
				sb.append(brand_group);
			}

			sb.append("|");

			if (Nation_Sent == null) {
				sb.append("<null>");
			} else {
				sb.append(Nation_Sent);
			}

			sb.append("|");

			if (net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(net_desp_units);
			}

			sb.append("|");

			if (uk_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(uk_net_desp_units);
			}

			sb.append("|");

			if (int_net_desp_units == null) {
				sb.append("<null>");
			} else {
				sb.append(int_net_desp_units);
			}

			sb.append("|");

			if (LocationCode == null) {
				sb.append("<null>");
			} else {
				sb.append(LocationCode);
			}

			sb.append("|");

			if (ExportFlag == null) {
				sb.append("<null>");
			} else {
				sb.append(ExportFlag);
			}

			sb.append("|");

			if (SalesQuantity == null) {
				sb.append("<null>");
			} else {
				sb.append(SalesQuantity);
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

	public void tFileInputExcel_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tFileInputExcel_1", "Wvc2zK_");

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
				out1Struct out1 = new out1Struct();
				row2Struct row2 = new row2Struct();
				row4Struct row4 = new row4Struct();
				out2Struct out2 = new out2Struct();
				out3Struct out3 = new out3Struct();
				row6Struct row6 = new row6Struct();
				row3Struct row3 = new row3Struct();

				row5Struct row5 = new row5Struct();

				row7Struct row7 = new row7Struct();
				out4Struct out4 = new out4Struct();
				row8Struct row8 = new row8Struct();
				out5Struct out5 = new out5Struct();
				out5Struct row9 = out5;

				/**
				 * [tFileOutputExcel_1 begin ] start
				 */

				sh("tFileOutputExcel_1");

				s(currentComponent = "tFileOutputExcel_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row9");

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
									+ "\"C:/Users/valpakuser/Desktop/Outputs/JDWilliams Q1 2024 To Load Demo - target_output_\"+TalendDate.formatDate(\"yyyyMMdd_HHmmSS\",TalendDate.getCurrentDate())+\".xlsx\"");
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
									+ ("false") + ", SCHEMA_COLUMN=" + ("PRODUCT_LINE_CODE") + "}, {IS_AUTO_SIZE="
									+ ("false") + ", SCHEMA_COLUMN=" + ("calender_month") + "}, {IS_AUTO_SIZE="
									+ ("false") + ", SCHEMA_COLUMN=" + ("Product_Code") + "}, {IS_AUTO_SIZE="
									+ ("false") + ", SCHEMA_COLUMN=" + ("Product_Name") + "}, {IS_AUTO_SIZE="
									+ ("false") + ", SCHEMA_COLUMN=" + ("division") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("dept") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("rnge") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("import_flag") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("export_flag") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("supplier_code") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("supplier_name") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("SUPPLIER_REF_NO") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("brand_group") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Nation_Sent") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("net_desp_units") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("uk_net_desp_units") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("int_net_desp_units") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("LocationCode") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("ExportFlag") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("SalesQuantity") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Audit_BatchName") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Audit_BatchName_Description") + "}, {IS_AUTO_SIZE="
									+ ("false") + ", SCHEMA_COLUMN=" + ("Audit_BatchNo") + "}, {IS_AUTO_SIZE="
									+ ("false") + ", SCHEMA_COLUMN=" + ("Audit_BatchTimeExecution")
									+ "}, {IS_AUTO_SIZE=" + ("false") + ", SCHEMA_COLUMN=" + ("brand_indicator")
									+ "}]");
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

				String fileName_tFileOutputExcel_1 = "C:/Users/valpakuser/Desktop/Outputs/JDWilliams Q1 2024 To Load Demo - target_output_"
						+ TalendDate.formatDate("yyyyMMdd_HHmmSS", TalendDate.getCurrentDate()) + ".xlsx";
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

					xlsxTool_tFileOutputExcel_1.addCellValue("PRODUCT_LINE_CODE");

					xlsxTool_tFileOutputExcel_1.addCellValue("calender_month");

					xlsxTool_tFileOutputExcel_1.addCellValue("Product_Code");

					xlsxTool_tFileOutputExcel_1.addCellValue("Product_Name");

					xlsxTool_tFileOutputExcel_1.addCellValue("division");

					xlsxTool_tFileOutputExcel_1.addCellValue("dept");

					xlsxTool_tFileOutputExcel_1.addCellValue("rnge");

					xlsxTool_tFileOutputExcel_1.addCellValue("import_flag");

					xlsxTool_tFileOutputExcel_1.addCellValue("export_flag");

					xlsxTool_tFileOutputExcel_1.addCellValue("supplier_code");

					xlsxTool_tFileOutputExcel_1.addCellValue("supplier_name");

					xlsxTool_tFileOutputExcel_1.addCellValue("SUPPLIER_REF_NO");

					xlsxTool_tFileOutputExcel_1.addCellValue("brand_group");

					xlsxTool_tFileOutputExcel_1.addCellValue("Nation_Sent");

					xlsxTool_tFileOutputExcel_1.addCellValue("net_desp_units");

					xlsxTool_tFileOutputExcel_1.addCellValue("uk_net_desp_units");

					xlsxTool_tFileOutputExcel_1.addCellValue("int_net_desp_units");

					xlsxTool_tFileOutputExcel_1.addCellValue("LocationCode");

					xlsxTool_tFileOutputExcel_1.addCellValue("ExportFlag");

					xlsxTool_tFileOutputExcel_1.addCellValue("SalesQuantity");

					xlsxTool_tFileOutputExcel_1.addCellValue("Audit_BatchName");

					xlsxTool_tFileOutputExcel_1.addCellValue("Audit_BatchName_Description");

					xlsxTool_tFileOutputExcel_1.addCellValue("Audit_BatchNo");

					xlsxTool_tFileOutputExcel_1.addCellValue("Audit_BatchTimeExecution");

					xlsxTool_tFileOutputExcel_1.addCellValue("brand_indicator");

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

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "out5");

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

					int[] colLengths = new int[25];

					public void addRow(String[] row) {

						for (int i = 0; i < 25; i++) {
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
						for (k = 0; k < (totals + 24 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 24 - name.length() - k; i++) {
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

							sbformat.append("|%5$-");
							sbformat.append(colLengths[4]);
							sbformat.append("s");

							sbformat.append("|%6$-");
							sbformat.append(colLengths[5]);
							sbformat.append("s");

							sbformat.append("|%7$-");
							sbformat.append(colLengths[6]);
							sbformat.append("s");

							sbformat.append("|%8$-");
							sbformat.append(colLengths[7]);
							sbformat.append("s");

							sbformat.append("|%9$-");
							sbformat.append(colLengths[8]);
							sbformat.append("s");

							sbformat.append("|%10$-");
							sbformat.append(colLengths[9]);
							sbformat.append("s");

							sbformat.append("|%11$-");
							sbformat.append(colLengths[10]);
							sbformat.append("s");

							sbformat.append("|%12$-");
							sbformat.append(colLengths[11]);
							sbformat.append("s");

							sbformat.append("|%13$-");
							sbformat.append(colLengths[12]);
							sbformat.append("s");

							sbformat.append("|%14$-");
							sbformat.append(colLengths[13]);
							sbformat.append("s");

							sbformat.append("|%15$-");
							sbformat.append(colLengths[14]);
							sbformat.append("s");

							sbformat.append("|%16$-");
							sbformat.append(colLengths[15]);
							sbformat.append("s");

							sbformat.append("|%17$-");
							sbformat.append(colLengths[16]);
							sbformat.append("s");

							sbformat.append("|%18$-");
							sbformat.append(colLengths[17]);
							sbformat.append("s");

							sbformat.append("|%19$-");
							sbformat.append(colLengths[18]);
							sbformat.append("s");

							sbformat.append("|%20$-");
							sbformat.append(colLengths[19]);
							sbformat.append("s");

							sbformat.append("|%21$-");
							sbformat.append(colLengths[20]);
							sbformat.append("s");

							sbformat.append("|%22$-");
							sbformat.append(colLengths[21]);
							sbformat.append("s");

							sbformat.append("|%23$-");
							sbformat.append(colLengths[22]);
							sbformat.append("s");

							sbformat.append("|%24$-");
							sbformat.append(colLengths[23]);
							sbformat.append("s");

							sbformat.append("|%25$-");
							sbformat.append(colLengths[24]);
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
						for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[4] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[5] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[6] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[7] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[8] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[9] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[10] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[11] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[12] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[13] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[14] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[15] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[16] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[17] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[18] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[19] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[20] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[21] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[22] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[23] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[24] - fillChars[1].length() + 1; i++) {
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
				util_tLogRow_1.addRow(new String[] { "PRODUCT_LINE_CODE", "calender_month", "Product_Code",
						"Product_Name", "division", "dept", "rnge", "import_flag", "export_flag", "supplier_code",
						"supplier_name", "SUPPLIER_REF_NO", "brand_group", "Nation_Sent", "net_desp_units",
						"uk_net_desp_units", "int_net_desp_units", "LocationCode", "ExportFlag", "SalesQuantity",
						"Audit_BatchName", "Audit_BatchName_Description", "Audit_BatchNo", "Audit_BatchTimeExecution",
						"brand_indicator", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tMap_4 begin ] start
				 */

				sh("tMap_4");

				s(currentComponent = "tMap_4");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row8");

				int tos_count_tMap_4 = 0;

				if (log.isDebugEnabled())
					log.debug("tMap_4 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tMap_4 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tMap_4 = new StringBuilder();
							log4jParamters_tMap_4.append("Parameters:");
							log4jParamters_tMap_4.append("LINK_STYLE" + " = " + "AUTO");
							log4jParamters_tMap_4.append(" | ");
							log4jParamters_tMap_4.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
							log4jParamters_tMap_4.append(" | ");
							log4jParamters_tMap_4.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
							log4jParamters_tMap_4.append(" | ");
							log4jParamters_tMap_4.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
							log4jParamters_tMap_4.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tMap_4 - " + (log4jParamters_tMap_4));
						}
					}
					new BytesLimit65535_tMap_4().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tMap_4", "tMap_4", "tMap");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

// ###############################
// # Lookup's keys initialization
				int count_row8_tMap_4 = 0;

// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_4__Struct {
				}
				Var__tMap_4__Struct Var__tMap_4 = new Var__tMap_4__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_out5_tMap_4 = 0;

				out5Struct out5_tmp = new out5Struct();
// ###############################

				/**
				 * [tMap_4 begin ] stop
				 */

				/**
				 * [tFilterRow_30 begin ] start
				 */

				sh("tFilterRow_30");

				s(currentComponent = "tFilterRow_30");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "out4");

				int tos_count_tFilterRow_30 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_30 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_30 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_30 = new StringBuilder();
							log4jParamters_tFilterRow_30.append("Parameters:");
							log4jParamters_tFilterRow_30.append("LOGICAL_OP" + " = " + "||");
							log4jParamters_tFilterRow_30.append(" | ");
							log4jParamters_tFilterRow_30.append("CONDITIONS" + " = " + "[]");
							log4jParamters_tFilterRow_30.append(" | ");
							log4jParamters_tFilterRow_30.append("USE_ADVANCED" + " = " + "true");
							log4jParamters_tFilterRow_30.append(" | ");
							log4jParamters_tFilterRow_30.append("ADVANCED_COND" + " = "
									+ "// code sample : use input_row to define the condition. // input_row.columnName1.equals(\"foo\") ||!(input_row.columnName2.equals(\"bar\")) // replace the following expression by your own filter condition  !Relational.ISNULL(input_row.Product_Code)&&!Relational.ISNULL(input_row.Product_Name)&&!Relational.ISNULL(input_row.LocationCode)");
							log4jParamters_tFilterRow_30.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_30 - " + (log4jParamters_tFilterRow_30));
						}
					}
					new BytesLimit65535_tFilterRow_30().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_30", "tFilterRow_30", "tFilterRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				int nb_line_tFilterRow_30 = 0;
				int nb_line_ok_tFilterRow_30 = 0;
				int nb_line_reject_tFilterRow_30 = 0;

				class Operator_tFilterRow_30 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_30(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_30 begin ] stop
				 */

				/**
				 * [tMap_3 begin ] start
				 */

				sh("tMap_3");

				s(currentComponent = "tMap_3");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row7");

				int tos_count_tMap_3 = 0;

				if (log.isDebugEnabled())
					log.debug("tMap_3 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tMap_3 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tMap_3 = new StringBuilder();
							log4jParamters_tMap_3.append("Parameters:");
							log4jParamters_tMap_3.append("LINK_STYLE" + " = " + "AUTO");
							log4jParamters_tMap_3.append(" | ");
							log4jParamters_tMap_3.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
							log4jParamters_tMap_3.append(" | ");
							log4jParamters_tMap_3.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
							log4jParamters_tMap_3.append(" | ");
							log4jParamters_tMap_3.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
							log4jParamters_tMap_3.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tMap_3 - " + (log4jParamters_tMap_3));
						}
					}
					new BytesLimit65535_tMap_3().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tMap_3", "tMap_3", "tMap");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

// ###############################
// # Lookup's keys initialization
				int count_row7_tMap_3 = 0;

// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_3__Struct {
				}
				Var__tMap_3__Struct Var__tMap_3 = new Var__tMap_3__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_out4_tMap_3 = 0;

				out4Struct out4_tmp = new out4Struct();
// ###############################

				/**
				 * [tMap_3 begin ] stop
				 */

				/**
				 * [tUnite_1 begin ] start
				 */

				sh("tUnite_1");

				s(currentComponent = "tUnite_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "out2", "row5");

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
				 * [tHashOutput_1 begin ] start
				 */

				sh("tHashOutput_1");

				s(currentComponent = "tHashOutput_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "out3");

				int tos_count_tHashOutput_1 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tHashOutput_1", "tHashOutput_1", "tHashOutput");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				org.talend.designer.components.hashfile.common.MapHashFile mf_tHashOutput_1 = org.talend.designer.components.hashfile.common.MapHashFile
						.getMapHashFile();
				org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<out3Struct> tHashFile_tHashOutput_1 = null;
				String hashKey_tHashOutput_1 = "tHashFile_JDWilliams_Hard_Coded_" + pid + "_tHashOutput_1";
				synchronized (org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
						.get(hashKey_tHashOutput_1)) {
					if (mf_tHashOutput_1.getResourceMap().get(hashKey_tHashOutput_1) == null) {
						mf_tHashOutput_1.getResourceMap().put(hashKey_tHashOutput_1,
								new org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<out3Struct>(
										org.talend.designer.components.hashfile.common.MATCHING_MODE.KEEP_ALL));
						tHashFile_tHashOutput_1 = mf_tHashOutput_1.getResourceMap().get(hashKey_tHashOutput_1);
					} else {
						tHashFile_tHashOutput_1 = mf_tHashOutput_1.getResourceMap().get(hashKey_tHashOutput_1);
					}
				}
				int nb_line_tHashOutput_1 = 0;

				/**
				 * [tHashOutput_1 begin ] stop
				 */

				/**
				 * [tMap_2 begin ] start
				 */

				sh("tMap_2");

				s(currentComponent = "tMap_2");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row4");

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
				int count_row4_tMap_2 = 0;

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
				int count_out3_tMap_2 = 0;

				out3Struct out3_tmp = new out3Struct();
// ###############################

				/**
				 * [tMap_2 begin ] stop
				 */

				/**
				 * [tLogRow_4 begin ] start
				 */

				sh("tLogRow_4");

				s(currentComponent = "tLogRow_4");

				cLabel = "duplicates";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row6");

				int tos_count_tLogRow_4 = 0;

				if (log.isDebugEnabled())
					log.debug("tLogRow_4 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tLogRow_4 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tLogRow_4 = new StringBuilder();
							log4jParamters_tLogRow_4.append("Parameters:");
							log4jParamters_tLogRow_4.append("BASIC_MODE" + " = " + "false");
							log4jParamters_tLogRow_4.append(" | ");
							log4jParamters_tLogRow_4.append("TABLE_PRINT" + " = " + "true");
							log4jParamters_tLogRow_4.append(" | ");
							log4jParamters_tLogRow_4.append("VERTICAL" + " = " + "false");
							log4jParamters_tLogRow_4.append(" | ");
							log4jParamters_tLogRow_4.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
							log4jParamters_tLogRow_4.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tLogRow_4 - " + (log4jParamters_tLogRow_4));
						}
					}
					new BytesLimit65535_tLogRow_4().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tLogRow_4", "duplicates", "tLogRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				///////////////////////

				class Util_tLogRow_4 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[18];

					public void addRow(String[] row) {

						for (int i = 0; i < 18; i++) {
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
						for (k = 0; k < (totals + 17 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 17 - name.length() - k; i++) {
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

							sbformat.append("|%5$-");
							sbformat.append(colLengths[4]);
							sbformat.append("s");

							sbformat.append("|%6$-");
							sbformat.append(colLengths[5]);
							sbformat.append("s");

							sbformat.append("|%7$-");
							sbformat.append(colLengths[6]);
							sbformat.append("s");

							sbformat.append("|%8$-");
							sbformat.append(colLengths[7]);
							sbformat.append("s");

							sbformat.append("|%9$-");
							sbformat.append(colLengths[8]);
							sbformat.append("s");

							sbformat.append("|%10$-");
							sbformat.append(colLengths[9]);
							sbformat.append("s");

							sbformat.append("|%11$-");
							sbformat.append(colLengths[10]);
							sbformat.append("s");

							sbformat.append("|%12$-");
							sbformat.append(colLengths[11]);
							sbformat.append("s");

							sbformat.append("|%13$-");
							sbformat.append(colLengths[12]);
							sbformat.append("s");

							sbformat.append("|%14$-");
							sbformat.append(colLengths[13]);
							sbformat.append("s");

							sbformat.append("|%15$-");
							sbformat.append(colLengths[14]);
							sbformat.append("s");

							sbformat.append("|%16$-");
							sbformat.append(colLengths[15]);
							sbformat.append("s");

							sbformat.append("|%17$-");
							sbformat.append(colLengths[16]);
							sbformat.append("s");

							sbformat.append("|%18$-");
							sbformat.append(colLengths[17]);
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
						for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[4] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[5] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[6] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[7] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[8] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[9] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[10] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[11] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[12] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[13] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[14] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[15] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[16] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[17] - fillChars[1].length() + 1; i++) {
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
				Util_tLogRow_4 util_tLogRow_4 = new Util_tLogRow_4();
				util_tLogRow_4.setTableName("duplicates");
				util_tLogRow_4.addRow(new String[] { "PRODUCT_LINE_CODE", "calender_month", "Product_Code",
						"Product_Name", "division", "dept", "rnge", "import_flag", "export_flag", "supplier_code",
						"supplier_name", "SUPPLIER_REF_NO", "brand_group", "Nation_Sent", "net_desp_units",
						"uk_net_desp_units", "int_net_desp_units", "LocationCode", });
				StringBuilder strBuffer_tLogRow_4 = null;
				int nb_line_tLogRow_4 = 0;
///////////////////////    			

				/**
				 * [tLogRow_4 begin ] stop
				 */

				/**
				 * [tUniqRow_1 begin ] start
				 */

				sh("tUniqRow_1");

				s(currentComponent = "tUniqRow_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row2");

				int tos_count_tUniqRow_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tUniqRow_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tUniqRow_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tUniqRow_1 = new StringBuilder();
							log4jParamters_tUniqRow_1.append("Parameters:");
							log4jParamters_tUniqRow_1.append("UNIQUE_KEY" + " = " + "[{CASE_SENSITIVE=" + ("true")
									+ ", KEY_ATTRIBUTE=" + ("true") + ", SCHEMA_COLUMN=" + ("PRODUCT_LINE_CODE")
									+ "}, {CASE_SENSITIVE=" + ("true") + ", KEY_ATTRIBUTE=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("calender_month") + "}, {CASE_SENSITIVE=" + ("true")
									+ ", KEY_ATTRIBUTE=" + ("true") + ", SCHEMA_COLUMN=" + ("Product_Code")
									+ "}, {CASE_SENSITIVE=" + ("true") + ", KEY_ATTRIBUTE=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Product_Name") + "}, {CASE_SENSITIVE=" + ("true")
									+ ", KEY_ATTRIBUTE=" + ("true") + ", SCHEMA_COLUMN=" + ("division")
									+ "}, {CASE_SENSITIVE=" + ("true") + ", KEY_ATTRIBUTE=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("dept") + "}, {CASE_SENSITIVE=" + ("true")
									+ ", KEY_ATTRIBUTE=" + ("true") + ", SCHEMA_COLUMN=" + ("rnge")
									+ "}, {CASE_SENSITIVE=" + ("true") + ", KEY_ATTRIBUTE=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("import_flag") + "}, {CASE_SENSITIVE=" + ("true")
									+ ", KEY_ATTRIBUTE=" + ("true") + ", SCHEMA_COLUMN=" + ("export_flag")
									+ "}, {CASE_SENSITIVE=" + ("true") + ", KEY_ATTRIBUTE=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("supplier_code") + "}, {CASE_SENSITIVE=" + ("true")
									+ ", KEY_ATTRIBUTE=" + ("true") + ", SCHEMA_COLUMN=" + ("supplier_name")
									+ "}, {CASE_SENSITIVE=" + ("true") + ", KEY_ATTRIBUTE=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("SUPPLIER_REF_NO") + "}, {CASE_SENSITIVE=" + ("true")
									+ ", KEY_ATTRIBUTE=" + ("true") + ", SCHEMA_COLUMN=" + ("brand_group")
									+ "}, {CASE_SENSITIVE=" + ("true") + ", KEY_ATTRIBUTE=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Nation_Sent") + "}, {CASE_SENSITIVE=" + ("true")
									+ ", KEY_ATTRIBUTE=" + ("true") + ", SCHEMA_COLUMN=" + ("net_desp_units")
									+ "}, {CASE_SENSITIVE=" + ("true") + ", KEY_ATTRIBUTE=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("uk_net_desp_units") + "}, {CASE_SENSITIVE=" + ("true")
									+ ", KEY_ATTRIBUTE=" + ("true") + ", SCHEMA_COLUMN=" + ("int_net_desp_units")
									+ "}, {CASE_SENSITIVE=" + ("true") + ", KEY_ATTRIBUTE=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("LocationCode") + "}]");
							log4jParamters_tUniqRow_1.append(" | ");
							log4jParamters_tUniqRow_1.append("ONLY_ONCE_EACH_DUPLICATED_KEY" + " = " + "false");
							log4jParamters_tUniqRow_1.append(" | ");
							log4jParamters_tUniqRow_1.append("IS_VIRTUAL_COMPONENT" + " = " + "false");
							log4jParamters_tUniqRow_1.append(" | ");
							log4jParamters_tUniqRow_1.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "false");
							log4jParamters_tUniqRow_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tUniqRow_1 - " + (log4jParamters_tUniqRow_1));
						}
					}
					new BytesLimit65535_tUniqRow_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tUniqRow_1", "tUniqRow_1", "tUniqRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				class KeyStruct_tUniqRow_1 {

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					String PRODUCT_LINE_CODE;
					String calender_month;
					String Product_Code;
					String Product_Name;
					String division;
					String dept;
					String rnge;
					String import_flag;
					String export_flag;
					String supplier_code;
					String supplier_name;
					String SUPPLIER_REF_NO;
					String brand_group;
					String Nation_Sent;
					Integer net_desp_units;
					Integer uk_net_desp_units;
					Integer int_net_desp_units;
					String LocationCode;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result
									+ ((this.PRODUCT_LINE_CODE == null) ? 0 : this.PRODUCT_LINE_CODE.hashCode());

							result = prime * result
									+ ((this.calender_month == null) ? 0 : this.calender_month.hashCode());

							result = prime * result + ((this.Product_Code == null) ? 0 : this.Product_Code.hashCode());

							result = prime * result + ((this.Product_Name == null) ? 0 : this.Product_Name.hashCode());

							result = prime * result + ((this.division == null) ? 0 : this.division.hashCode());

							result = prime * result + ((this.dept == null) ? 0 : this.dept.hashCode());

							result = prime * result + ((this.rnge == null) ? 0 : this.rnge.hashCode());

							result = prime * result + ((this.import_flag == null) ? 0 : this.import_flag.hashCode());

							result = prime * result + ((this.export_flag == null) ? 0 : this.export_flag.hashCode());

							result = prime * result
									+ ((this.supplier_code == null) ? 0 : this.supplier_code.hashCode());

							result = prime * result
									+ ((this.supplier_name == null) ? 0 : this.supplier_name.hashCode());

							result = prime * result
									+ ((this.SUPPLIER_REF_NO == null) ? 0 : this.SUPPLIER_REF_NO.hashCode());

							result = prime * result + ((this.brand_group == null) ? 0 : this.brand_group.hashCode());

							result = prime * result + ((this.Nation_Sent == null) ? 0 : this.Nation_Sent.hashCode());

							result = prime * result
									+ ((this.net_desp_units == null) ? 0 : this.net_desp_units.hashCode());

							result = prime * result
									+ ((this.uk_net_desp_units == null) ? 0 : this.uk_net_desp_units.hashCode());

							result = prime * result
									+ ((this.int_net_desp_units == null) ? 0 : this.int_net_desp_units.hashCode());

							result = prime * result + ((this.LocationCode == null) ? 0 : this.LocationCode.hashCode());

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
						final KeyStruct_tUniqRow_1 other = (KeyStruct_tUniqRow_1) obj;

						if (this.PRODUCT_LINE_CODE == null) {
							if (other.PRODUCT_LINE_CODE != null)
								return false;

						} else if (!this.PRODUCT_LINE_CODE.equals(other.PRODUCT_LINE_CODE))

							return false;

						if (this.calender_month == null) {
							if (other.calender_month != null)
								return false;

						} else if (!this.calender_month.equals(other.calender_month))

							return false;

						if (this.Product_Code == null) {
							if (other.Product_Code != null)
								return false;

						} else if (!this.Product_Code.equals(other.Product_Code))

							return false;

						if (this.Product_Name == null) {
							if (other.Product_Name != null)
								return false;

						} else if (!this.Product_Name.equals(other.Product_Name))

							return false;

						if (this.division == null) {
							if (other.division != null)
								return false;

						} else if (!this.division.equals(other.division))

							return false;

						if (this.dept == null) {
							if (other.dept != null)
								return false;

						} else if (!this.dept.equals(other.dept))

							return false;

						if (this.rnge == null) {
							if (other.rnge != null)
								return false;

						} else if (!this.rnge.equals(other.rnge))

							return false;

						if (this.import_flag == null) {
							if (other.import_flag != null)
								return false;

						} else if (!this.import_flag.equals(other.import_flag))

							return false;

						if (this.export_flag == null) {
							if (other.export_flag != null)
								return false;

						} else if (!this.export_flag.equals(other.export_flag))

							return false;

						if (this.supplier_code == null) {
							if (other.supplier_code != null)
								return false;

						} else if (!this.supplier_code.equals(other.supplier_code))

							return false;

						if (this.supplier_name == null) {
							if (other.supplier_name != null)
								return false;

						} else if (!this.supplier_name.equals(other.supplier_name))

							return false;

						if (this.SUPPLIER_REF_NO == null) {
							if (other.SUPPLIER_REF_NO != null)
								return false;

						} else if (!this.SUPPLIER_REF_NO.equals(other.SUPPLIER_REF_NO))

							return false;

						if (this.brand_group == null) {
							if (other.brand_group != null)
								return false;

						} else if (!this.brand_group.equals(other.brand_group))

							return false;

						if (this.Nation_Sent == null) {
							if (other.Nation_Sent != null)
								return false;

						} else if (!this.Nation_Sent.equals(other.Nation_Sent))

							return false;

						if (this.net_desp_units == null) {
							if (other.net_desp_units != null)
								return false;

						} else if (!this.net_desp_units.equals(other.net_desp_units))

							return false;

						if (this.uk_net_desp_units == null) {
							if (other.uk_net_desp_units != null)
								return false;

						} else if (!this.uk_net_desp_units.equals(other.uk_net_desp_units))

							return false;

						if (this.int_net_desp_units == null) {
							if (other.int_net_desp_units != null)
								return false;

						} else if (!this.int_net_desp_units.equals(other.int_net_desp_units))

							return false;

						if (this.LocationCode == null) {
							if (other.LocationCode != null)
								return false;

						} else if (!this.LocationCode.equals(other.LocationCode))

							return false;

						return true;
					}

				}

				int nb_uniques_tUniqRow_1 = 0;
				int nb_duplicates_tUniqRow_1 = 0;
				log.debug("tUniqRow_1 - Start to process the data from datasource.");
				KeyStruct_tUniqRow_1 finder_tUniqRow_1 = new KeyStruct_tUniqRow_1();
				java.util.Set<KeyStruct_tUniqRow_1> keystUniqRow_1 = new java.util.HashSet<KeyStruct_tUniqRow_1>();

				/**
				 * [tUniqRow_1 begin ] stop
				 */

				/**
				 * [tLogRow_2 begin ] start
				 */

				sh("tLogRow_2");

				s(currentComponent = "tLogRow_2");

				cLabel = "rejects";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row3");

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
					talendJobLog.addCM("tLogRow_2", "rejects", "tLogRow");
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

					int[] colLengths = new int[19];

					public void addRow(String[] row) {

						for (int i = 0; i < 19; i++) {
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
						for (k = 0; k < (totals + 18 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 18 - name.length() - k; i++) {
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

							sbformat.append("|%5$-");
							sbformat.append(colLengths[4]);
							sbformat.append("s");

							sbformat.append("|%6$-");
							sbformat.append(colLengths[5]);
							sbformat.append("s");

							sbformat.append("|%7$-");
							sbformat.append(colLengths[6]);
							sbformat.append("s");

							sbformat.append("|%8$-");
							sbformat.append(colLengths[7]);
							sbformat.append("s");

							sbformat.append("|%9$-");
							sbformat.append(colLengths[8]);
							sbformat.append("s");

							sbformat.append("|%10$-");
							sbformat.append(colLengths[9]);
							sbformat.append("s");

							sbformat.append("|%11$-");
							sbformat.append(colLengths[10]);
							sbformat.append("s");

							sbformat.append("|%12$-");
							sbformat.append(colLengths[11]);
							sbformat.append("s");

							sbformat.append("|%13$-");
							sbformat.append(colLengths[12]);
							sbformat.append("s");

							sbformat.append("|%14$-");
							sbformat.append(colLengths[13]);
							sbformat.append("s");

							sbformat.append("|%15$-");
							sbformat.append(colLengths[14]);
							sbformat.append("s");

							sbformat.append("|%16$-");
							sbformat.append(colLengths[15]);
							sbformat.append("s");

							sbformat.append("|%17$-");
							sbformat.append(colLengths[16]);
							sbformat.append("s");

							sbformat.append("|%18$-");
							sbformat.append(colLengths[17]);
							sbformat.append("s");

							sbformat.append("|%19$-");
							sbformat.append(colLengths[18]);
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
						for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[4] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[5] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[6] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[7] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[8] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[9] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[10] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[11] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[12] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[13] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[14] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[15] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[16] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[17] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[18] - fillChars[1].length() + 1; i++) {
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
				util_tLogRow_2.setTableName("rejects");
				util_tLogRow_2.addRow(new String[] { "PRODUCT_LINE_CODE", "calender_month", "Product_Code",
						"Product_Name", "division", "dept", "rnge", "import_flag", "export_flag", "supplier_code",
						"supplier_name", "SUPPLIER_REF_NO", "brand_group", "Nation_Sent", "net_desp_units",
						"uk_net_desp_units", "int_net_desp_units", "LocationCode", "errorMessage", });
				StringBuilder strBuffer_tLogRow_2 = null;
				int nb_line_tLogRow_2 = 0;
///////////////////////    			

				/**
				 * [tLogRow_2 begin ] stop
				 */

				/**
				 * [tFilterRow_11 begin ] start
				 */

				sh("tFilterRow_11");

				s(currentComponent = "tFilterRow_11");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "out1");

				int tos_count_tFilterRow_11 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_11 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_11 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_11 = new StringBuilder();
							log4jParamters_tFilterRow_11.append("Parameters:");
							log4jParamters_tFilterRow_11.append("LOGICAL_OP" + " = " + "&&");
							log4jParamters_tFilterRow_11.append(" | ");
							log4jParamters_tFilterRow_11.append("CONDITIONS" + " = " + "[{OPERATOR=" + (">")
									+ ", RVALUE=" + ("0") + ", INPUT_COLUMN=" + ("net_desp_units") + ", FUNCTION="
									+ ("") + "}, {OPERATOR=" + (">=") + ", RVALUE=" + ("0") + ", INPUT_COLUMN="
									+ ("uk_net_desp_units") + ", FUNCTION=" + ("") + "}]");
							log4jParamters_tFilterRow_11.append(" | ");
							log4jParamters_tFilterRow_11.append("USE_ADVANCED" + " = " + "false");
							log4jParamters_tFilterRow_11.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_11 - " + (log4jParamters_tFilterRow_11));
						}
					}
					new BytesLimit65535_tFilterRow_11().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_11", "tFilterRow_11", "tFilterRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				int nb_line_tFilterRow_11 = 0;
				int nb_line_ok_tFilterRow_11 = 0;
				int nb_line_reject_tFilterRow_11 = 0;

				class Operator_tFilterRow_11 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_11(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_11 begin ] stop
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

// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_out1_tMap_1 = 0;

				out1Struct out1_tmp = new out1Struct();
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
									+ "\"C:/Users/valpakuser/Desktop/ValPak PoC Data/JDWilliams Q1 2024 To Load Demo - source.xlsx\"");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("PASSWORD" + " = "
									+ String.valueOf(
											"enc:routine.encryption.key.v1:M2LHQ+CiliJwS8yEpV9L4GX6t1AdlsUi7u+8aw==")
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
							log4jParamters_tFileInputExcel_1.append("LIMIT" + " = " + "100");
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
									+ ", SCHEMA_COLUMN=" + ("PRODUCT_LINE_CODE") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("calender_month") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("PRODUCT_NUMBER") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("product_desc") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("division") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("dept") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("rnge") + "}, {TRIM="
									+ ("false") + ", SCHEMA_COLUMN=" + ("import_flag") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("export_flag") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("SUPPLIER_NUMBER") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("supplier_name") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("SUPPLIER_REF_NO") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("brand_group") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Nation_Sent") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("net_desp_units") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("uk_net_desp_units") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("int_net_desp_units") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("LocationCode") + "}]");
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
						.decryptPassword("enc:routine.encryption.key.v1:S4jcrJxJepDCqI012afZ6yyGqWJ7C5uFlFpimg==");
				String password_tFileInputExcel_1 = decryptedPassword_tFileInputExcel_1;
				if (password_tFileInputExcel_1.isEmpty()) {
					password_tFileInputExcel_1 = null;
				}
				Object source_tFileInputExcel_1 = "C:/Users/valpakuser/Desktop/ValPak PoC Data/JDWilliams Q1 2024 To Load Demo - source.xlsx";
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

						end_column_tFileInputExcel_1 = start_column_tFileInputExcel_1 + 18 - 1;

					} else if (end_column_tFileInputExcel_1 >= 0) {// follow end column
						start_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 - 18 + 1;
					}

					if (end_column_tFileInputExcel_1 < 0 || start_column_tFileInputExcel_1 < 0) {
						throw new RuntimeException("Error start column and end column.");
					}
					int actual_end_column_tFileInputExcel_1 = end_column_tFileInputExcel_1;

					int header_tFileInputExcel_1 = 1;
					int limit_tFileInputExcel_1 = 100;

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
						int tempRowLength_tFileInputExcel_1 = 18;

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
								curColName_tFileInputExcel_1 = "PRODUCT_LINE_CODE";

								row1.PRODUCT_LINE_CODE = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.PRODUCT_LINE_CODE = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 1;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "calender_month";

								row1.calender_month = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.calender_month = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 2;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "PRODUCT_NUMBER";

								row1.PRODUCT_NUMBER = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.PRODUCT_NUMBER = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 3;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "product_desc";

								row1.product_desc = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.product_desc = null;
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
								curColName_tFileInputExcel_1 = "import_flag";

								row1.import_flag = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.import_flag = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 8;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "export_flag";

								row1.export_flag = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.export_flag = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 9;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "SUPPLIER_NUMBER";

								row1.SUPPLIER_NUMBER = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.SUPPLIER_NUMBER = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 10;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "supplier_name";

								row1.supplier_name = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.supplier_name = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 11;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "SUPPLIER_REF_NO";

								row1.SUPPLIER_REF_NO = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.SUPPLIER_REF_NO = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 12;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "brand_group";

								row1.brand_group = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.brand_group = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 13;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "Nation_Sent";

								row1.Nation_Sent = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.Nation_Sent = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 14;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "net_desp_units";

								row1.net_desp_units = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
										temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
										'.' == decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
							} else {
								row1.net_desp_units = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 15;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "uk_net_desp_units";

								row1.uk_net_desp_units = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
										temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
										'.' == decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
							} else {
								row1.uk_net_desp_units = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 16;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "int_net_desp_units";

								row1.int_net_desp_units = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
										temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
										'.' == decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
							} else {
								row1.int_net_desp_units = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 17;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "LocationCode";

								row1.LocationCode = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
							} else {
								row1.LocationCode = null;
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

							// ###############################
							// # Input tables (lookups)

							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;
							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
								// ###############################
								// # Output tables

								out1 = null;

// # Output table : 'out1'
								count_out1_tMap_1++;

								out1_tmp.PRODUCT_LINE_CODE = row1.PRODUCT_LINE_CODE;
								out1_tmp.calender_month = row1.calender_month;
								out1_tmp.Product_Code = row1.PRODUCT_NUMBER;
								out1_tmp.Product_Name = row1.product_desc.replaceAll(" ", "");
								out1_tmp.division = row1.division;
								out1_tmp.dept = row1.dept;
								out1_tmp.rnge = row1.rnge;
								out1_tmp.import_flag = row1.import_flag;
								out1_tmp.export_flag = row1.export_flag;
								out1_tmp.supplier_code = row1.SUPPLIER_NUMBER;
								out1_tmp.supplier_name = row1.supplier_name;
								out1_tmp.SUPPLIER_REF_NO = row1.SUPPLIER_REF_NO;
								out1_tmp.brand_group = row1.brand_group;
								out1_tmp.Nation_Sent = row1.Nation_Sent;
								out1_tmp.net_desp_units = row1.net_desp_units;
								out1_tmp.uk_net_desp_units = row1.uk_net_desp_units;
								out1_tmp.int_net_desp_units = row1.int_net_desp_units;
								out1_tmp.LocationCode = row1.LocationCode;
								out1 = out1_tmp;
								log.debug("tMap_1 - Outputting the record " + count_out1_tMap_1
										+ " of the output table 'out1'.");

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

// Start of branch "out1"
							if (out1 != null) {

								/**
								 * [tFilterRow_11 main ] start
								 */

								s(currentComponent = "tFilterRow_11");

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "out1", "tMap_1", "tMap_1", "tMap", "tFilterRow_11", "tFilterRow_11",
										"tFilterRow"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("out1 - " + (out1 == null ? "" : out1.toLogString()));
								}

								row3 = null;
								row2 = null;
								Operator_tFilterRow_11 ope_tFilterRow_11 = new Operator_tFilterRow_11("&&");
								ope_tFilterRow_11
										.matches(
												(out1.net_desp_units == null ? false
														: out1.net_desp_units.compareTo(
																ParserUtils.parseTo_Integer(String.valueOf(0))) > 0),
												"net_desp_units.compareTo(0) > 0 failed");

								ope_tFilterRow_11.matches(
										(out1.uk_net_desp_units == null ? false
												: out1.uk_net_desp_units.compareTo(
														ParserUtils.parseTo_Integer(String.valueOf(0))) >= 0),
										"uk_net_desp_units.compareTo(0) >= 0 failed");

								if (ope_tFilterRow_11.getMatchFlag()) {
									if (row2 == null) {
										row2 = new row2Struct();
									}
									row2.PRODUCT_LINE_CODE = out1.PRODUCT_LINE_CODE;
									row2.calender_month = out1.calender_month;
									row2.Product_Code = out1.Product_Code;
									row2.Product_Name = out1.Product_Name;
									row2.division = out1.division;
									row2.dept = out1.dept;
									row2.rnge = out1.rnge;
									row2.import_flag = out1.import_flag;
									row2.export_flag = out1.export_flag;
									row2.supplier_code = out1.supplier_code;
									row2.supplier_name = out1.supplier_name;
									row2.SUPPLIER_REF_NO = out1.SUPPLIER_REF_NO;
									row2.brand_group = out1.brand_group;
									row2.Nation_Sent = out1.Nation_Sent;
									row2.net_desp_units = out1.net_desp_units;
									row2.uk_net_desp_units = out1.uk_net_desp_units;
									row2.int_net_desp_units = out1.int_net_desp_units;
									row2.LocationCode = out1.LocationCode;
									log.debug(
											"tFilterRow_11 - Process the record " + (nb_line_tFilterRow_11 + 1) + ".");

									nb_line_ok_tFilterRow_11++;
								} else {
									if (row3 == null) {
										row3 = new row3Struct();
									}
									row3.PRODUCT_LINE_CODE = out1.PRODUCT_LINE_CODE;
									row3.calender_month = out1.calender_month;
									row3.Product_Code = out1.Product_Code;
									row3.Product_Name = out1.Product_Name;
									row3.division = out1.division;
									row3.dept = out1.dept;
									row3.rnge = out1.rnge;
									row3.import_flag = out1.import_flag;
									row3.export_flag = out1.export_flag;
									row3.supplier_code = out1.supplier_code;
									row3.supplier_name = out1.supplier_name;
									row3.SUPPLIER_REF_NO = out1.SUPPLIER_REF_NO;
									row3.brand_group = out1.brand_group;
									row3.Nation_Sent = out1.Nation_Sent;
									row3.net_desp_units = out1.net_desp_units;
									row3.uk_net_desp_units = out1.uk_net_desp_units;
									row3.int_net_desp_units = out1.int_net_desp_units;
									row3.LocationCode = out1.LocationCode;
									row3.errorMessage = ope_tFilterRow_11.getErrorMsg();
									log.debug(
											"tFilterRow_11 - Process the record " + (nb_line_tFilterRow_11 + 1) + ".");

									log.debug("tFilterRow_11 - Reject the record " + (nb_line_tFilterRow_11 + 1)
											+ ". Caused by: " + row3.errorMessage + ".");

									nb_line_reject_tFilterRow_11++;
								}

								nb_line_tFilterRow_11++;

								tos_count_tFilterRow_11++;

								/**
								 * [tFilterRow_11 main ] stop
								 */

								/**
								 * [tFilterRow_11 process_data_begin ] start
								 */

								s(currentComponent = "tFilterRow_11");

								/**
								 * [tFilterRow_11 process_data_begin ] stop
								 */

// Start of branch "row2"
								if (row2 != null) {

									/**
									 * [tUniqRow_1 main ] start
									 */

									s(currentComponent = "tUniqRow_1");

									if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

											, "row2", "tFilterRow_11", "tFilterRow_11", "tFilterRow", "tUniqRow_1",
											"tUniqRow_1", "tUniqRow"

									)) {
										talendJobLogProcess(globalMap);
									}

									if (log.isTraceEnabled()) {
										log.trace("row2 - " + (row2 == null ? "" : row2.toLogString()));
									}

									row6 = null;
									row4 = null;
									finder_tUniqRow_1.PRODUCT_LINE_CODE = row2.PRODUCT_LINE_CODE;
									finder_tUniqRow_1.calender_month = row2.calender_month;
									finder_tUniqRow_1.Product_Code = row2.Product_Code;
									finder_tUniqRow_1.Product_Name = row2.Product_Name;
									finder_tUniqRow_1.division = row2.division;
									finder_tUniqRow_1.dept = row2.dept;
									finder_tUniqRow_1.rnge = row2.rnge;
									finder_tUniqRow_1.import_flag = row2.import_flag;
									finder_tUniqRow_1.export_flag = row2.export_flag;
									finder_tUniqRow_1.supplier_code = row2.supplier_code;
									finder_tUniqRow_1.supplier_name = row2.supplier_name;
									finder_tUniqRow_1.SUPPLIER_REF_NO = row2.SUPPLIER_REF_NO;
									finder_tUniqRow_1.brand_group = row2.brand_group;
									finder_tUniqRow_1.Nation_Sent = row2.Nation_Sent;
									finder_tUniqRow_1.net_desp_units = row2.net_desp_units;
									finder_tUniqRow_1.uk_net_desp_units = row2.uk_net_desp_units;
									finder_tUniqRow_1.int_net_desp_units = row2.int_net_desp_units;
									finder_tUniqRow_1.LocationCode = row2.LocationCode;
									finder_tUniqRow_1.hashCodeDirty = true;
									if (!keystUniqRow_1.contains(finder_tUniqRow_1)) {
										KeyStruct_tUniqRow_1 new_tUniqRow_1 = new KeyStruct_tUniqRow_1();

										new_tUniqRow_1.PRODUCT_LINE_CODE = row2.PRODUCT_LINE_CODE;
										new_tUniqRow_1.calender_month = row2.calender_month;
										new_tUniqRow_1.Product_Code = row2.Product_Code;
										new_tUniqRow_1.Product_Name = row2.Product_Name;
										new_tUniqRow_1.division = row2.division;
										new_tUniqRow_1.dept = row2.dept;
										new_tUniqRow_1.rnge = row2.rnge;
										new_tUniqRow_1.import_flag = row2.import_flag;
										new_tUniqRow_1.export_flag = row2.export_flag;
										new_tUniqRow_1.supplier_code = row2.supplier_code;
										new_tUniqRow_1.supplier_name = row2.supplier_name;
										new_tUniqRow_1.SUPPLIER_REF_NO = row2.SUPPLIER_REF_NO;
										new_tUniqRow_1.brand_group = row2.brand_group;
										new_tUniqRow_1.Nation_Sent = row2.Nation_Sent;
										new_tUniqRow_1.net_desp_units = row2.net_desp_units;
										new_tUniqRow_1.uk_net_desp_units = row2.uk_net_desp_units;
										new_tUniqRow_1.int_net_desp_units = row2.int_net_desp_units;
										new_tUniqRow_1.LocationCode = row2.LocationCode;

										keystUniqRow_1.add(new_tUniqRow_1);
										if (row4 == null) {

											log.trace("tUniqRow_1 - Writing the unique record "
													+ (nb_uniques_tUniqRow_1 + 1) + " into row4.");

											row4 = new row4Struct();
										}
										row4.PRODUCT_LINE_CODE = row2.PRODUCT_LINE_CODE;
										row4.calender_month = row2.calender_month;
										row4.Product_Code = row2.Product_Code;
										row4.Product_Name = row2.Product_Name;
										row4.division = row2.division;
										row4.dept = row2.dept;
										row4.rnge = row2.rnge;
										row4.import_flag = row2.import_flag;
										row4.export_flag = row2.export_flag;
										row4.supplier_code = row2.supplier_code;
										row4.supplier_name = row2.supplier_name;
										row4.SUPPLIER_REF_NO = row2.SUPPLIER_REF_NO;
										row4.brand_group = row2.brand_group;
										row4.Nation_Sent = row2.Nation_Sent;
										row4.net_desp_units = row2.net_desp_units;
										row4.uk_net_desp_units = row2.uk_net_desp_units;
										row4.int_net_desp_units = row2.int_net_desp_units;
										row4.LocationCode = row2.LocationCode;
										nb_uniques_tUniqRow_1++;
									} else {
										if (row6 == null) {

											log.trace("tUniqRow_1 - Writing the duplicate record "
													+ (nb_duplicates_tUniqRow_1 + 1) + " into row6.");

											row6 = new row6Struct();
										}
										row6.PRODUCT_LINE_CODE = row2.PRODUCT_LINE_CODE;
										row6.calender_month = row2.calender_month;
										row6.Product_Code = row2.Product_Code;
										row6.Product_Name = row2.Product_Name;
										row6.division = row2.division;
										row6.dept = row2.dept;
										row6.rnge = row2.rnge;
										row6.import_flag = row2.import_flag;
										row6.export_flag = row2.export_flag;
										row6.supplier_code = row2.supplier_code;
										row6.supplier_name = row2.supplier_name;
										row6.SUPPLIER_REF_NO = row2.SUPPLIER_REF_NO;
										row6.brand_group = row2.brand_group;
										row6.Nation_Sent = row2.Nation_Sent;
										row6.net_desp_units = row2.net_desp_units;
										row6.uk_net_desp_units = row2.uk_net_desp_units;
										row6.int_net_desp_units = row2.int_net_desp_units;
										row6.LocationCode = row2.LocationCode;
										nb_duplicates_tUniqRow_1++;
									}

									tos_count_tUniqRow_1++;

									/**
									 * [tUniqRow_1 main ] stop
									 */

									/**
									 * [tUniqRow_1 process_data_begin ] start
									 */

									s(currentComponent = "tUniqRow_1");

									/**
									 * [tUniqRow_1 process_data_begin ] stop
									 */

// Start of branch "row4"
									if (row4 != null) {

										/**
										 * [tMap_2 main ] start
										 */

										s(currentComponent = "tMap_2");

										if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

												, "row4", "tUniqRow_1", "tUniqRow_1", "tUniqRow", "tMap_2", "tMap_2",
												"tMap"

										)) {
											talendJobLogProcess(globalMap);
										}

										if (log.isTraceEnabled()) {
											log.trace("row4 - " + (row4 == null ? "" : row4.toLogString()));
										}

										boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;

										// ###############################
										// # Input tables (lookups)

										boolean rejectedInnerJoin_tMap_2 = false;
										boolean mainRowRejected_tMap_2 = false;
										// ###############################
										{ // start of Var scope

											// ###############################
											// # Vars tables

											Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
											// ###############################
											// # Output tables

											out2 = null;
											out3 = null;

// # Output table : 'out2'
											count_out2_tMap_2++;

											out2_tmp.PRODUCT_LINE_CODE = row4.PRODUCT_LINE_CODE;
											out2_tmp.calender_month = row4.calender_month;
											out2_tmp.Product_Code = row4.Product_Code;
											out2_tmp.Product_Name = row4.Product_Name;
											out2_tmp.division = row4.division;
											out2_tmp.dept = row4.dept;
											out2_tmp.rnge = row4.rnge;
											out2_tmp.import_flag = row4.import_flag;
											out2_tmp.export_flag = row4.export_flag;
											out2_tmp.supplier_code = row4.supplier_code;
											out2_tmp.supplier_name = row4.supplier_name;
											out2_tmp.SUPPLIER_REF_NO = row4.SUPPLIER_REF_NO;
											out2_tmp.brand_group = row4.brand_group;
											out2_tmp.Nation_Sent = row4.Nation_Sent;
											out2_tmp.net_desp_units = row4.net_desp_units;
											out2_tmp.uk_net_desp_units = row4.uk_net_desp_units;
											out2_tmp.int_net_desp_units = row4.int_net_desp_units;
											out2_tmp.LocationCode = row4.LocationCode;
											out2_tmp.ExportFlag = "N";
											out2_tmp.SalesQuantity = row4.uk_net_desp_units;
											out2 = out2_tmp;
											log.debug("tMap_2 - Outputting the record " + count_out2_tMap_2
													+ " of the output table 'out2'.");

// # Output table : 'out3'
											count_out3_tMap_2++;

											out3_tmp.PRODUCT_LINE_CODE = row4.PRODUCT_LINE_CODE;
											out3_tmp.calender_month = row4.calender_month;
											out3_tmp.Product_Code = row4.Product_Code;
											out3_tmp.Product_Name = row4.Product_Name;
											out3_tmp.division = row4.division;
											out3_tmp.dept = row4.dept;
											out3_tmp.rnge = row4.rnge;
											out3_tmp.import_flag = row4.import_flag;
											out3_tmp.export_flag = row4.export_flag;
											out3_tmp.supplier_code = row4.supplier_code;
											out3_tmp.supplier_name = row4.supplier_name;
											out3_tmp.SUPPLIER_REF_NO = row4.SUPPLIER_REF_NO;
											out3_tmp.brand_group = row4.brand_group;
											out3_tmp.Nation_Sent = row4.Nation_Sent;
											out3_tmp.net_desp_units = row4.net_desp_units;
											out3_tmp.uk_net_desp_units = row4.uk_net_desp_units;
											out3_tmp.int_net_desp_units = row4.int_net_desp_units;
											out3_tmp.LocationCode = row4.LocationCode;
											out3_tmp.ExportFlag = "D";
											out3_tmp.SalesQuantity = row4.int_net_desp_units;
											out3 = out3_tmp;
											log.debug("tMap_2 - Outputting the record " + count_out3_tMap_2
													+ " of the output table 'out3'.");

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
											 * [tUnite_1 main ] start
											 */

											s(currentComponent = "tUnite_1");

											if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

													, "out2", "tMap_2", "tMap_2", "tMap", "tUnite_1", "tUnite_1",
													"tUnite"

											)) {
												talendJobLogProcess(globalMap);
											}

											if (log.isTraceEnabled()) {
												log.trace("out2 - " + (out2 == null ? "" : out2.toLogString()));
											}

											if (log.isTraceEnabled()) {
												log.trace("row5 - " + (row5 == null ? "" : row5.toLogString()));
											}

//////////

// for output
											row7 = new row7Struct();

											row7.PRODUCT_LINE_CODE = out2.PRODUCT_LINE_CODE;
											row7.calender_month = out2.calender_month;
											row7.Product_Code = out2.Product_Code;
											row7.Product_Name = out2.Product_Name;
											row7.division = out2.division;
											row7.dept = out2.dept;
											row7.rnge = out2.rnge;
											row7.import_flag = out2.import_flag;
											row7.export_flag = out2.export_flag;
											row7.supplier_code = out2.supplier_code;
											row7.supplier_name = out2.supplier_name;
											row7.SUPPLIER_REF_NO = out2.SUPPLIER_REF_NO;
											row7.brand_group = out2.brand_group;
											row7.Nation_Sent = out2.Nation_Sent;
											row7.net_desp_units = out2.net_desp_units;
											row7.uk_net_desp_units = out2.uk_net_desp_units;
											row7.int_net_desp_units = out2.int_net_desp_units;
											row7.LocationCode = out2.LocationCode;
											row7.ExportFlag = out2.ExportFlag;
											row7.SalesQuantity = out2.SalesQuantity;

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
											 * [tMap_3 main ] start
											 */

											s(currentComponent = "tMap_3");

											if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

													, "row7", "tUnite_1", "tUnite_1", "tUnite", "tMap_3", "tMap_3",
													"tMap"

											)) {
												talendJobLogProcess(globalMap);
											}

											if (log.isTraceEnabled()) {
												log.trace("row7 - " + (row7 == null ? "" : row7.toLogString()));
											}

											boolean hasCasePrimitiveKeyWithNull_tMap_3 = false;

											// ###############################
											// # Input tables (lookups)

											boolean rejectedInnerJoin_tMap_3 = false;
											boolean mainRowRejected_tMap_3 = false;
											// ###############################
											{ // start of Var scope

												// ###############################
												// # Vars tables

												Var__tMap_3__Struct Var = Var__tMap_3;// ###############################
												// ###############################
												// # Output tables

												out4 = null;

// # Output table : 'out4'
												count_out4_tMap_3++;

												out4_tmp.PRODUCT_LINE_CODE = row7.PRODUCT_LINE_CODE;
												out4_tmp.calender_month = row7.calender_month;
												out4_tmp.Product_Code = row7.Product_Code;
												out4_tmp.Product_Name = row7.Product_Name;
												out4_tmp.division = row7.division;
												out4_tmp.dept = row7.dept;
												out4_tmp.rnge = row7.rnge;
												out4_tmp.import_flag = row7.import_flag;
												out4_tmp.export_flag = row7.export_flag;
												out4_tmp.supplier_code = row7.supplier_code;
												out4_tmp.supplier_name = row7.supplier_name;
												out4_tmp.SUPPLIER_REF_NO = row7.SUPPLIER_REF_NO;
												out4_tmp.brand_group = row7.brand_group;
												out4_tmp.Nation_Sent = row7.Nation_Sent;
												out4_tmp.net_desp_units = row7.net_desp_units;
												out4_tmp.uk_net_desp_units = row7.uk_net_desp_units;
												out4_tmp.int_net_desp_units = row7.int_net_desp_units;
												out4_tmp.LocationCode = row7.LocationCode;
												out4_tmp.ExportFlag = row7.ExportFlag;
												out4_tmp.SalesQuantity = row7.SalesQuantity;
												out4_tmp.Audit_BatchName = null;
												out4_tmp.Audit_BatchName_Description = null;
												out4_tmp.Audit_BatchNo = null;
												out4_tmp.Audit_BatchTimeExecution = TalendDate
														.getDate("CCYY-MM-DD hh:mm:ss");
												out4 = out4_tmp;
												log.debug("tMap_3 - Outputting the record " + count_out4_tMap_3
														+ " of the output table 'out4'.");

// ###############################

											} // end of Var scope

											rejectedInnerJoin_tMap_3 = false;

											tos_count_tMap_3++;

											/**
											 * [tMap_3 main ] stop
											 */

											/**
											 * [tMap_3 process_data_begin ] start
											 */

											s(currentComponent = "tMap_3");

											/**
											 * [tMap_3 process_data_begin ] stop
											 */

// Start of branch "out4"
											if (out4 != null) {

												/**
												 * [tFilterRow_30 main ] start
												 */

												s(currentComponent = "tFilterRow_30");

												if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

														, "out4", "tMap_3", "tMap_3", "tMap", "tFilterRow_30",
														"tFilterRow_30", "tFilterRow"

												)) {
													talendJobLogProcess(globalMap);
												}

												if (log.isTraceEnabled()) {
													log.trace("out4 - " + (out4 == null ? "" : out4.toLogString()));
												}

												row8 = null;
												Operator_tFilterRow_30 ope_tFilterRow_30 = new Operator_tFilterRow_30(
														"||");
												ope_tFilterRow_30.matches((// code sample : use out4 to define the
																			// condition.
// out4.columnName1.equals("foo") ||!(out4.columnName2.equals("bar"))
// replace the following expression by your own filter condition 
												!Relational.ISNULL(out4.Product_Code)
														&& !Relational.ISNULL(out4.Product_Name)
														&& !Relational.ISNULL(out4.LocationCode)),
														"advanced condition failed");

												if (ope_tFilterRow_30.getMatchFlag()) {
													if (row8 == null) {
														row8 = new row8Struct();
													}
													row8.PRODUCT_LINE_CODE = out4.PRODUCT_LINE_CODE;
													row8.calender_month = out4.calender_month;
													row8.Product_Code = out4.Product_Code;
													row8.Product_Name = out4.Product_Name;
													row8.division = out4.division;
													row8.dept = out4.dept;
													row8.rnge = out4.rnge;
													row8.import_flag = out4.import_flag;
													row8.export_flag = out4.export_flag;
													row8.supplier_code = out4.supplier_code;
													row8.supplier_name = out4.supplier_name;
													row8.SUPPLIER_REF_NO = out4.SUPPLIER_REF_NO;
													row8.brand_group = out4.brand_group;
													row8.Nation_Sent = out4.Nation_Sent;
													row8.net_desp_units = out4.net_desp_units;
													row8.uk_net_desp_units = out4.uk_net_desp_units;
													row8.int_net_desp_units = out4.int_net_desp_units;
													row8.LocationCode = out4.LocationCode;
													row8.ExportFlag = out4.ExportFlag;
													row8.SalesQuantity = out4.SalesQuantity;
													row8.Audit_BatchName = out4.Audit_BatchName;
													row8.Audit_BatchName_Description = out4.Audit_BatchName_Description;
													row8.Audit_BatchNo = out4.Audit_BatchNo;
													row8.Audit_BatchTimeExecution = out4.Audit_BatchTimeExecution;
													log.debug("tFilterRow_30 - Process the record "
															+ (nb_line_tFilterRow_30 + 1) + ".");

													nb_line_ok_tFilterRow_30++;
												} else {
													nb_line_reject_tFilterRow_30++;
												}

												nb_line_tFilterRow_30++;

												tos_count_tFilterRow_30++;

												/**
												 * [tFilterRow_30 main ] stop
												 */

												/**
												 * [tFilterRow_30 process_data_begin ] start
												 */

												s(currentComponent = "tFilterRow_30");

												/**
												 * [tFilterRow_30 process_data_begin ] stop
												 */

// Start of branch "row8"
												if (row8 != null) {

													/**
													 * [tMap_4 main ] start
													 */

													s(currentComponent = "tMap_4");

													if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

															, "row8", "tFilterRow_30", "tFilterRow_30", "tFilterRow",
															"tMap_4", "tMap_4", "tMap"

													)) {
														talendJobLogProcess(globalMap);
													}

													if (log.isTraceEnabled()) {
														log.trace("row8 - " + (row8 == null ? "" : row8.toLogString()));
													}

													boolean hasCasePrimitiveKeyWithNull_tMap_4 = false;

													// ###############################
													// # Input tables (lookups)

													boolean rejectedInnerJoin_tMap_4 = false;
													boolean mainRowRejected_tMap_4 = false;
													// ###############################
													{ // start of Var scope

														// ###############################
														// # Vars tables

														Var__tMap_4__Struct Var = Var__tMap_4;// ###############################
														// ###############################
														// # Output tables

														out5 = null;

// # Output table : 'out5'
														count_out5_tMap_4++;

														out5_tmp.PRODUCT_LINE_CODE = row8.PRODUCT_LINE_CODE;
														out5_tmp.calender_month = row8.calender_month;
														out5_tmp.Product_Code = row8.Product_Code;
														out5_tmp.Product_Name = row8.Product_Name;
														out5_tmp.division = row8.division;
														out5_tmp.dept = row8.dept;
														out5_tmp.rnge = row8.rnge;
														out5_tmp.import_flag = row8.import_flag;
														out5_tmp.export_flag = row8.export_flag;
														out5_tmp.supplier_code = row8.supplier_code;
														out5_tmp.supplier_name = row8.supplier_name;
														out5_tmp.SUPPLIER_REF_NO = row8.SUPPLIER_REF_NO;
														out5_tmp.brand_group = row8.brand_group;
														out5_tmp.Nation_Sent = row8.Nation_Sent;
														out5_tmp.net_desp_units = row8.net_desp_units;
														out5_tmp.uk_net_desp_units = row8.uk_net_desp_units;
														out5_tmp.int_net_desp_units = row8.int_net_desp_units;
														out5_tmp.LocationCode = row8.LocationCode;
														out5_tmp.ExportFlag = row8.ExportFlag;
														out5_tmp.SalesQuantity = row8.SalesQuantity;
														out5_tmp.Audit_BatchName = row8.Audit_BatchName;
														out5_tmp.Audit_BatchName_Description = row8.Audit_BatchName_Description;
														out5_tmp.Audit_BatchNo = row8.Audit_BatchNo;
														out5_tmp.Audit_BatchTimeExecution = row8.Audit_BatchTimeExecution;
														out5_tmp.brand_indicator = row8.brand_group.equals("Own Brand")
																? "1"
																: "0";
														;
														out5 = out5_tmp;
														log.debug("tMap_4 - Outputting the record " + count_out5_tMap_4
																+ " of the output table 'out5'.");

// ###############################

													} // end of Var scope

													rejectedInnerJoin_tMap_4 = false;

													tos_count_tMap_4++;

													/**
													 * [tMap_4 main ] stop
													 */

													/**
													 * [tMap_4 process_data_begin ] start
													 */

													s(currentComponent = "tMap_4");

													/**
													 * [tMap_4 process_data_begin ] stop
													 */

// Start of branch "out5"
													if (out5 != null) {

														/**
														 * [tLogRow_1 main ] start
														 */

														s(currentComponent = "tLogRow_1");

														if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

																, "out5", "tMap_4", "tMap_4", "tMap", "tLogRow_1",
																"tLogRow_1", "tLogRow"

														)) {
															talendJobLogProcess(globalMap);
														}

														if (log.isTraceEnabled()) {
															log.trace("out5 - "
																	+ (out5 == null ? "" : out5.toLogString()));
														}

///////////////////////		

														String[] row_tLogRow_1 = new String[25];

														if (out5.PRODUCT_LINE_CODE != null) { //
															row_tLogRow_1[0] = String.valueOf(out5.PRODUCT_LINE_CODE);

														} //

														if (out5.calender_month != null) { //
															row_tLogRow_1[1] = String.valueOf(out5.calender_month);

														} //

														if (out5.Product_Code != null) { //
															row_tLogRow_1[2] = String.valueOf(out5.Product_Code);

														} //

														if (out5.Product_Name != null) { //
															row_tLogRow_1[3] = String.valueOf(out5.Product_Name);

														} //

														if (out5.division != null) { //
															row_tLogRow_1[4] = String.valueOf(out5.division);

														} //

														if (out5.dept != null) { //
															row_tLogRow_1[5] = String.valueOf(out5.dept);

														} //

														if (out5.rnge != null) { //
															row_tLogRow_1[6] = String.valueOf(out5.rnge);

														} //

														if (out5.import_flag != null) { //
															row_tLogRow_1[7] = String.valueOf(out5.import_flag);

														} //

														if (out5.export_flag != null) { //
															row_tLogRow_1[8] = String.valueOf(out5.export_flag);

														} //

														if (out5.supplier_code != null) { //
															row_tLogRow_1[9] = String.valueOf(out5.supplier_code);

														} //

														if (out5.supplier_name != null) { //
															row_tLogRow_1[10] = String.valueOf(out5.supplier_name);

														} //

														if (out5.SUPPLIER_REF_NO != null) { //
															row_tLogRow_1[11] = String.valueOf(out5.SUPPLIER_REF_NO);

														} //

														if (out5.brand_group != null) { //
															row_tLogRow_1[12] = String.valueOf(out5.brand_group);

														} //

														if (out5.Nation_Sent != null) { //
															row_tLogRow_1[13] = String.valueOf(out5.Nation_Sent);

														} //

														if (out5.net_desp_units != null) { //
															row_tLogRow_1[14] = String.valueOf(out5.net_desp_units);

														} //

														if (out5.uk_net_desp_units != null) { //
															row_tLogRow_1[15] = String.valueOf(out5.uk_net_desp_units);

														} //

														if (out5.int_net_desp_units != null) { //
															row_tLogRow_1[16] = String.valueOf(out5.int_net_desp_units);

														} //

														if (out5.LocationCode != null) { //
															row_tLogRow_1[17] = String.valueOf(out5.LocationCode);

														} //

														if (out5.ExportFlag != null) { //
															row_tLogRow_1[18] = String.valueOf(out5.ExportFlag);

														} //

														if (out5.SalesQuantity != null) { //
															row_tLogRow_1[19] = String.valueOf(out5.SalesQuantity);

														} //

														if (out5.Audit_BatchName != null) { //
															row_tLogRow_1[20] = String.valueOf(out5.Audit_BatchName);

														} //

														if (out5.Audit_BatchName_Description != null) { //
															row_tLogRow_1[21] = String
																	.valueOf(out5.Audit_BatchName_Description);

														} //

														if (out5.Audit_BatchNo != null) { //
															row_tLogRow_1[22] = String.valueOf(out5.Audit_BatchNo);

														} //

														if (out5.Audit_BatchTimeExecution != null) { //
															row_tLogRow_1[23] = String
																	.valueOf(out5.Audit_BatchTimeExecution);

														} //

														if (out5.brand_indicator != null) { //
															row_tLogRow_1[24] = String.valueOf(out5.brand_indicator);

														} //

														util_tLogRow_1.addRow(row_tLogRow_1);
														nb_line_tLogRow_1++;
														log.info("tLogRow_1 - Content of row " + nb_line_tLogRow_1
																+ ": " + TalendString.unionString("|", row_tLogRow_1));
//////

//////                    

///////////////////////    			

														row9 = out5;

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

																, "row9", "tLogRow_1", "tLogRow_1", "tLogRow",
																"tFileOutputExcel_1", "tFileOutputExcel_1",
																"tFileOutputExcel"

														)) {
															talendJobLogProcess(globalMap);
														}

														if (log.isTraceEnabled()) {
															log.trace("row9 - "
																	+ (row9 == null ? "" : row9.toLogString()));
														}

														xlsxTool_tFileOutputExcel_1.addRow();

														if (row9.PRODUCT_LINE_CODE != null) {

															xlsxTool_tFileOutputExcel_1.addCellValue(
																	String.valueOf(row9.PRODUCT_LINE_CODE));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.calender_month != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.calender_month));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.Product_Code != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.Product_Code));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.Product_Name != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.Product_Name));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.division != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.division));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.dept != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.dept));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.rnge != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.rnge));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.import_flag != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.import_flag));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.export_flag != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.export_flag));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.supplier_code != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.supplier_code));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.supplier_name != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.supplier_name));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.SUPPLIER_REF_NO != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.SUPPLIER_REF_NO));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.brand_group != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.brand_group));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.Nation_Sent != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.Nation_Sent));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.net_desp_units != null) {

															xlsxTool_tFileOutputExcel_1.addCellValue(Double
																	.parseDouble(String.valueOf(row9.net_desp_units)));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.uk_net_desp_units != null) {

															xlsxTool_tFileOutputExcel_1.addCellValue(Double.parseDouble(
																	String.valueOf(row9.uk_net_desp_units)));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.int_net_desp_units != null) {

															xlsxTool_tFileOutputExcel_1.addCellValue(Double.parseDouble(
																	String.valueOf(row9.int_net_desp_units)));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.LocationCode != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.LocationCode));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.ExportFlag != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.ExportFlag));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.SalesQuantity != null) {

															xlsxTool_tFileOutputExcel_1.addCellValue(Double
																	.parseDouble(String.valueOf(row9.SalesQuantity)));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.Audit_BatchName != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.Audit_BatchName));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.Audit_BatchName_Description != null) {

															xlsxTool_tFileOutputExcel_1.addCellValue(
																	String.valueOf(row9.Audit_BatchName_Description));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.Audit_BatchNo != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.Audit_BatchNo));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.Audit_BatchTimeExecution != null) {

															xlsxTool_tFileOutputExcel_1.addCellValue(
																	String.valueOf(row9.Audit_BatchTimeExecution));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														if (row9.brand_indicator != null) {

															xlsxTool_tFileOutputExcel_1
																	.addCellValue(String.valueOf(row9.brand_indicator));
														} else {
															xlsxTool_tFileOutputExcel_1.addCellNullValue();
														}

														nb_line_tFileOutputExcel_1++;

														log.debug("tFileOutputExcel_1 - Writing the record "
																+ nb_line_tFileOutputExcel_1 + " to the file.");

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

													} // End of branch "out5"

													/**
													 * [tMap_4 process_data_end ] start
													 */

													s(currentComponent = "tMap_4");

													/**
													 * [tMap_4 process_data_end ] stop
													 */

												} // End of branch "row8"

												/**
												 * [tFilterRow_30 process_data_end ] start
												 */

												s(currentComponent = "tFilterRow_30");

												/**
												 * [tFilterRow_30 process_data_end ] stop
												 */

											} // End of branch "out4"

											/**
											 * [tMap_3 process_data_end ] start
											 */

											s(currentComponent = "tMap_3");

											/**
											 * [tMap_3 process_data_end ] stop
											 */

											/**
											 * [tUnite_1 process_data_end ] start
											 */

											s(currentComponent = "tUnite_1");

											/**
											 * [tUnite_1 process_data_end ] stop
											 */

										} // End of branch "out2"

// Start of branch "out3"
										if (out3 != null) {

											/**
											 * [tHashOutput_1 main ] start
											 */

											s(currentComponent = "tHashOutput_1");

											if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

													, "out3", "tMap_2", "tMap_2", "tMap", "tHashOutput_1",
													"tHashOutput_1", "tHashOutput"

											)) {
												talendJobLogProcess(globalMap);
											}

											if (log.isTraceEnabled()) {
												log.trace("out3 - " + (out3 == null ? "" : out3.toLogString()));
											}

											out3Struct oneRow_tHashOutput_1 = new out3Struct();

											oneRow_tHashOutput_1.PRODUCT_LINE_CODE = out3.PRODUCT_LINE_CODE;
											oneRow_tHashOutput_1.calender_month = out3.calender_month;
											oneRow_tHashOutput_1.Product_Code = out3.Product_Code;
											oneRow_tHashOutput_1.Product_Name = out3.Product_Name;
											oneRow_tHashOutput_1.division = out3.division;
											oneRow_tHashOutput_1.dept = out3.dept;
											oneRow_tHashOutput_1.rnge = out3.rnge;
											oneRow_tHashOutput_1.import_flag = out3.import_flag;
											oneRow_tHashOutput_1.export_flag = out3.export_flag;
											oneRow_tHashOutput_1.supplier_code = out3.supplier_code;
											oneRow_tHashOutput_1.supplier_name = out3.supplier_name;
											oneRow_tHashOutput_1.SUPPLIER_REF_NO = out3.SUPPLIER_REF_NO;
											oneRow_tHashOutput_1.brand_group = out3.brand_group;
											oneRow_tHashOutput_1.Nation_Sent = out3.Nation_Sent;
											oneRow_tHashOutput_1.net_desp_units = out3.net_desp_units;
											oneRow_tHashOutput_1.uk_net_desp_units = out3.uk_net_desp_units;
											oneRow_tHashOutput_1.int_net_desp_units = out3.int_net_desp_units;
											oneRow_tHashOutput_1.LocationCode = out3.LocationCode;
											oneRow_tHashOutput_1.ExportFlag = out3.ExportFlag;
											oneRow_tHashOutput_1.SalesQuantity = out3.SalesQuantity;

											tHashFile_tHashOutput_1.put(oneRow_tHashOutput_1);
											nb_line_tHashOutput_1++;

											tos_count_tHashOutput_1++;

											/**
											 * [tHashOutput_1 main ] stop
											 */

											/**
											 * [tHashOutput_1 process_data_begin ] start
											 */

											s(currentComponent = "tHashOutput_1");

											/**
											 * [tHashOutput_1 process_data_begin ] stop
											 */

											/**
											 * [tHashOutput_1 process_data_end ] start
											 */

											s(currentComponent = "tHashOutput_1");

											/**
											 * [tHashOutput_1 process_data_end ] stop
											 */

										} // End of branch "out3"

										/**
										 * [tMap_2 process_data_end ] start
										 */

										s(currentComponent = "tMap_2");

										/**
										 * [tMap_2 process_data_end ] stop
										 */

									} // End of branch "row4"

// Start of branch "row6"
									if (row6 != null) {

										/**
										 * [tLogRow_4 main ] start
										 */

										s(currentComponent = "tLogRow_4");

										cLabel = "duplicates";

										if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

												, "row6", "tUniqRow_1", "tUniqRow_1", "tUniqRow", "tLogRow_4",
												"duplicates", "tLogRow"

										)) {
											talendJobLogProcess(globalMap);
										}

										if (log.isTraceEnabled()) {
											log.trace("row6 - " + (row6 == null ? "" : row6.toLogString()));
										}

///////////////////////		

										String[] row_tLogRow_4 = new String[18];

										if (row6.PRODUCT_LINE_CODE != null) { //
											row_tLogRow_4[0] = String.valueOf(row6.PRODUCT_LINE_CODE);

										} //

										if (row6.calender_month != null) { //
											row_tLogRow_4[1] = String.valueOf(row6.calender_month);

										} //

										if (row6.Product_Code != null) { //
											row_tLogRow_4[2] = String.valueOf(row6.Product_Code);

										} //

										if (row6.Product_Name != null) { //
											row_tLogRow_4[3] = String.valueOf(row6.Product_Name);

										} //

										if (row6.division != null) { //
											row_tLogRow_4[4] = String.valueOf(row6.division);

										} //

										if (row6.dept != null) { //
											row_tLogRow_4[5] = String.valueOf(row6.dept);

										} //

										if (row6.rnge != null) { //
											row_tLogRow_4[6] = String.valueOf(row6.rnge);

										} //

										if (row6.import_flag != null) { //
											row_tLogRow_4[7] = String.valueOf(row6.import_flag);

										} //

										if (row6.export_flag != null) { //
											row_tLogRow_4[8] = String.valueOf(row6.export_flag);

										} //

										if (row6.supplier_code != null) { //
											row_tLogRow_4[9] = String.valueOf(row6.supplier_code);

										} //

										if (row6.supplier_name != null) { //
											row_tLogRow_4[10] = String.valueOf(row6.supplier_name);

										} //

										if (row6.SUPPLIER_REF_NO != null) { //
											row_tLogRow_4[11] = String.valueOf(row6.SUPPLIER_REF_NO);

										} //

										if (row6.brand_group != null) { //
											row_tLogRow_4[12] = String.valueOf(row6.brand_group);

										} //

										if (row6.Nation_Sent != null) { //
											row_tLogRow_4[13] = String.valueOf(row6.Nation_Sent);

										} //

										if (row6.net_desp_units != null) { //
											row_tLogRow_4[14] = String.valueOf(row6.net_desp_units);

										} //

										if (row6.uk_net_desp_units != null) { //
											row_tLogRow_4[15] = String.valueOf(row6.uk_net_desp_units);

										} //

										if (row6.int_net_desp_units != null) { //
											row_tLogRow_4[16] = String.valueOf(row6.int_net_desp_units);

										} //

										if (row6.LocationCode != null) { //
											row_tLogRow_4[17] = String.valueOf(row6.LocationCode);

										} //

										util_tLogRow_4.addRow(row_tLogRow_4);
										nb_line_tLogRow_4++;
										log.info("tLogRow_4 - Content of row " + nb_line_tLogRow_4 + ": "
												+ TalendString.unionString("|", row_tLogRow_4));
//////

//////                    

///////////////////////    			

										tos_count_tLogRow_4++;

										/**
										 * [tLogRow_4 main ] stop
										 */

										/**
										 * [tLogRow_4 process_data_begin ] start
										 */

										s(currentComponent = "tLogRow_4");

										cLabel = "duplicates";

										/**
										 * [tLogRow_4 process_data_begin ] stop
										 */

										/**
										 * [tLogRow_4 process_data_end ] start
										 */

										s(currentComponent = "tLogRow_4");

										cLabel = "duplicates";

										/**
										 * [tLogRow_4 process_data_end ] stop
										 */

									} // End of branch "row6"

									/**
									 * [tUniqRow_1 process_data_end ] start
									 */

									s(currentComponent = "tUniqRow_1");

									/**
									 * [tUniqRow_1 process_data_end ] stop
									 */

								} // End of branch "row2"

// Start of branch "row3"
								if (row3 != null) {

									/**
									 * [tLogRow_2 main ] start
									 */

									s(currentComponent = "tLogRow_2");

									cLabel = "rejects";

									if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

											, "row3", "tFilterRow_11", "tFilterRow_11", "tFilterRow", "tLogRow_2",
											"rejects", "tLogRow"

									)) {
										talendJobLogProcess(globalMap);
									}

									if (log.isTraceEnabled()) {
										log.trace("row3 - " + (row3 == null ? "" : row3.toLogString()));
									}

///////////////////////		

									String[] row_tLogRow_2 = new String[19];

									if (row3.PRODUCT_LINE_CODE != null) { //
										row_tLogRow_2[0] = String.valueOf(row3.PRODUCT_LINE_CODE);

									} //

									if (row3.calender_month != null) { //
										row_tLogRow_2[1] = String.valueOf(row3.calender_month);

									} //

									if (row3.Product_Code != null) { //
										row_tLogRow_2[2] = String.valueOf(row3.Product_Code);

									} //

									if (row3.Product_Name != null) { //
										row_tLogRow_2[3] = String.valueOf(row3.Product_Name);

									} //

									if (row3.division != null) { //
										row_tLogRow_2[4] = String.valueOf(row3.division);

									} //

									if (row3.dept != null) { //
										row_tLogRow_2[5] = String.valueOf(row3.dept);

									} //

									if (row3.rnge != null) { //
										row_tLogRow_2[6] = String.valueOf(row3.rnge);

									} //

									if (row3.import_flag != null) { //
										row_tLogRow_2[7] = String.valueOf(row3.import_flag);

									} //

									if (row3.export_flag != null) { //
										row_tLogRow_2[8] = String.valueOf(row3.export_flag);

									} //

									if (row3.supplier_code != null) { //
										row_tLogRow_2[9] = String.valueOf(row3.supplier_code);

									} //

									if (row3.supplier_name != null) { //
										row_tLogRow_2[10] = String.valueOf(row3.supplier_name);

									} //

									if (row3.SUPPLIER_REF_NO != null) { //
										row_tLogRow_2[11] = String.valueOf(row3.SUPPLIER_REF_NO);

									} //

									if (row3.brand_group != null) { //
										row_tLogRow_2[12] = String.valueOf(row3.brand_group);

									} //

									if (row3.Nation_Sent != null) { //
										row_tLogRow_2[13] = String.valueOf(row3.Nation_Sent);

									} //

									if (row3.net_desp_units != null) { //
										row_tLogRow_2[14] = String.valueOf(row3.net_desp_units);

									} //

									if (row3.uk_net_desp_units != null) { //
										row_tLogRow_2[15] = String.valueOf(row3.uk_net_desp_units);

									} //

									if (row3.int_net_desp_units != null) { //
										row_tLogRow_2[16] = String.valueOf(row3.int_net_desp_units);

									} //

									if (row3.LocationCode != null) { //
										row_tLogRow_2[17] = String.valueOf(row3.LocationCode);

									} //

									if (row3.errorMessage != null) { //
										row_tLogRow_2[18] = String.valueOf(row3.errorMessage);

									} //

									util_tLogRow_2.addRow(row_tLogRow_2);
									nb_line_tLogRow_2++;
									log.info("tLogRow_2 - Content of row " + nb_line_tLogRow_2 + ": "
											+ TalendString.unionString("|", row_tLogRow_2));
//////

//////                    

///////////////////////    			

									tos_count_tLogRow_2++;

									/**
									 * [tLogRow_2 main ] stop
									 */

									/**
									 * [tLogRow_2 process_data_begin ] start
									 */

									s(currentComponent = "tLogRow_2");

									cLabel = "rejects";

									/**
									 * [tLogRow_2 process_data_begin ] stop
									 */

									/**
									 * [tLogRow_2 process_data_end ] start
									 */

									s(currentComponent = "tLogRow_2");

									cLabel = "rejects";

									/**
									 * [tLogRow_2 process_data_end ] stop
									 */

								} // End of branch "row3"

								/**
								 * [tFilterRow_11 process_data_end ] start
								 */

								s(currentComponent = "tFilterRow_11");

								/**
								 * [tFilterRow_11 process_data_end ] stop
								 */

							} // End of branch "out1"

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
// ###############################      
				log.debug("tMap_1 - Written records count in the table 'out1': " + count_out1_tMap_1 + ".");

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
				 * [tFilterRow_11 end ] start
				 */

				s(currentComponent = "tFilterRow_11");

				globalMap.put("tFilterRow_11_NB_LINE", nb_line_tFilterRow_11);
				globalMap.put("tFilterRow_11_NB_LINE_OK", nb_line_ok_tFilterRow_11);
				globalMap.put("tFilterRow_11_NB_LINE_REJECT", nb_line_reject_tFilterRow_11);

				log.info("tFilterRow_11 - Processed records count:" + nb_line_tFilterRow_11 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_11 + ". Rejected records count:" + nb_line_reject_tFilterRow_11 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "out1", 2, 0, "tMap_1",
						"tMap_1", "tMap", "tFilterRow_11", "tFilterRow_11", "tFilterRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFilterRow_11 - " + ("Done."));

				ok_Hash.put("tFilterRow_11", true);
				end_Hash.put("tFilterRow_11", System.currentTimeMillis());

				/**
				 * [tFilterRow_11 end ] stop
				 */

				/**
				 * [tUniqRow_1 end ] start
				 */

				s(currentComponent = "tUniqRow_1");

				globalMap.put("tUniqRow_1_NB_UNIQUES", nb_uniques_tUniqRow_1);
				globalMap.put("tUniqRow_1_NB_DUPLICATES", nb_duplicates_tUniqRow_1);
				log.info("tUniqRow_1 - Unique records count: " + (nb_uniques_tUniqRow_1) + " .");
				log.info("tUniqRow_1 - Duplicate records count: " + (nb_duplicates_tUniqRow_1) + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row2", 2, 0,
						"tFilterRow_11", "tFilterRow_11", "tFilterRow", "tUniqRow_1", "tUniqRow_1", "tUniqRow",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tUniqRow_1 - " + ("Done."));

				ok_Hash.put("tUniqRow_1", true);
				end_Hash.put("tUniqRow_1", System.currentTimeMillis());

				/**
				 * [tUniqRow_1 end ] stop
				 */

				/**
				 * [tMap_2 end ] start
				 */

				s(currentComponent = "tMap_2");

// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("tMap_2 - Written records count in the table 'out2': " + count_out2_tMap_2 + ".");
				log.debug("tMap_2 - Written records count in the table 'out3': " + count_out3_tMap_2 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row4", 2, 0,
						"tUniqRow_1", "tUniqRow_1", "tUniqRow", "tMap_2", "tMap_2", "tMap", "output")) {
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
				 * [tHashOutput_1 end ] start
				 */

				s(currentComponent = "tHashOutput_1");

				globalMap.put("tHashOutput_1_NB_LINE", nb_line_tHashOutput_1);
				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "out3", 2, 0, "tMap_2",
						"tMap_2", "tMap", "tHashOutput_1", "tHashOutput_1", "tHashOutput", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tHashOutput_1", true);
				end_Hash.put("tHashOutput_1", System.currentTimeMillis());

				/**
				 * [tHashOutput_1 end ] stop
				 */

				/**
				 * [tLogRow_4 end ] start
				 */

				s(currentComponent = "tLogRow_4");

				cLabel = "duplicates";

//////

				java.io.PrintStream consoleOut_tLogRow_4 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_4 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_4 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_4);
				}

				consoleOut_tLogRow_4.println(util_tLogRow_4.format().toString());
				consoleOut_tLogRow_4.flush();
//////
				globalMap.put("tLogRow_4_NB_LINE", nb_line_tLogRow_4);
				if (log.isInfoEnabled())
					log.info("tLogRow_4 - " + ("Printed row count: ") + (nb_line_tLogRow_4) + ("."));

///////////////////////    			

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row6", 2, 0,
						"tUniqRow_1", "tUniqRow_1", "tUniqRow", "tLogRow_4", "duplicates", "tLogRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_4 - " + ("Done."));

				ok_Hash.put("tLogRow_4", true);
				end_Hash.put("tLogRow_4", System.currentTimeMillis());

				/**
				 * [tLogRow_4 end ] stop
				 */

				/**
				 * [tLogRow_2 end ] start
				 */

				s(currentComponent = "tLogRow_2");

				cLabel = "rejects";

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

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row3", 2, 0,
						"tFilterRow_11", "tFilterRow_11", "tFilterRow", "tLogRow_2", "rejects", "tLogRow", "reject")) {
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
				 * [tHashInput_1 begin ] start
				 */

				sh("tHashInput_1");

				s(currentComponent = "tHashInput_1");

				int tos_count_tHashInput_1 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tHashInput_1", "tHashInput_1", "tHashInput");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				int nb_line_tHashInput_1 = 0;

				org.talend.designer.components.hashfile.common.MapHashFile mf_tHashInput_1 = org.talend.designer.components.hashfile.common.MapHashFile
						.getMapHashFile();
				org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<out3Struct> tHashFile_tHashInput_1 = mf_tHashInput_1
						.getAdvancedMemoryHashFile("tHashFile_JDWilliams_Hard_Coded_" + pid + "_tHashOutput_1");
				if (tHashFile_tHashInput_1 == null) {
					throw new RuntimeException(
							"The hash is not initialized : The hash must exist before you read from it");
				}
				java.util.Iterator<out3Struct> iterator_tHashInput_1 = tHashFile_tHashInput_1.iterator();
				while (iterator_tHashInput_1.hasNext()) {
					out3Struct next_tHashInput_1 = iterator_tHashInput_1.next();

					row5.PRODUCT_LINE_CODE = next_tHashInput_1.PRODUCT_LINE_CODE;
					row5.calender_month = next_tHashInput_1.calender_month;
					row5.Product_Code = next_tHashInput_1.Product_Code;
					row5.Product_Name = next_tHashInput_1.Product_Name;
					row5.division = next_tHashInput_1.division;
					row5.dept = next_tHashInput_1.dept;
					row5.rnge = next_tHashInput_1.rnge;
					row5.import_flag = next_tHashInput_1.import_flag;
					row5.export_flag = next_tHashInput_1.export_flag;
					row5.supplier_code = next_tHashInput_1.supplier_code;
					row5.supplier_name = next_tHashInput_1.supplier_name;
					row5.SUPPLIER_REF_NO = next_tHashInput_1.SUPPLIER_REF_NO;
					row5.brand_group = next_tHashInput_1.brand_group;
					row5.Nation_Sent = next_tHashInput_1.Nation_Sent;
					row5.net_desp_units = next_tHashInput_1.net_desp_units;
					row5.uk_net_desp_units = next_tHashInput_1.uk_net_desp_units;
					row5.int_net_desp_units = next_tHashInput_1.int_net_desp_units;
					row5.LocationCode = next_tHashInput_1.LocationCode;
					row5.ExportFlag = next_tHashInput_1.ExportFlag;
					row5.SalesQuantity = next_tHashInput_1.SalesQuantity;

					/**
					 * [tHashInput_1 begin ] stop
					 */

					/**
					 * [tHashInput_1 main ] start
					 */

					s(currentComponent = "tHashInput_1");

					tos_count_tHashInput_1++;

					/**
					 * [tHashInput_1 main ] stop
					 */

					/**
					 * [tHashInput_1 process_data_begin ] start
					 */

					s(currentComponent = "tHashInput_1");

					/**
					 * [tHashInput_1 process_data_begin ] stop
					 */

					/**
					 * [tUnite_1 main ] start
					 */

					s(currentComponent = "tUnite_1");

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row5", "tHashInput_1", "tHashInput_1", "tHashInput", "tUnite_1", "tUnite_1", "tUnite"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("out2 - " + (out2 == null ? "" : out2.toLogString()));
					}

					if (log.isTraceEnabled()) {
						log.trace("row5 - " + (row5 == null ? "" : row5.toLogString()));
					}

//////////

// for output
					row7 = new row7Struct();

					row7.PRODUCT_LINE_CODE = row5.PRODUCT_LINE_CODE;
					row7.calender_month = row5.calender_month;
					row7.Product_Code = row5.Product_Code;
					row7.Product_Name = row5.Product_Name;
					row7.division = row5.division;
					row7.dept = row5.dept;
					row7.rnge = row5.rnge;
					row7.import_flag = row5.import_flag;
					row7.export_flag = row5.export_flag;
					row7.supplier_code = row5.supplier_code;
					row7.supplier_name = row5.supplier_name;
					row7.SUPPLIER_REF_NO = row5.SUPPLIER_REF_NO;
					row7.brand_group = row5.brand_group;
					row7.Nation_Sent = row5.Nation_Sent;
					row7.net_desp_units = row5.net_desp_units;
					row7.uk_net_desp_units = row5.uk_net_desp_units;
					row7.int_net_desp_units = row5.int_net_desp_units;
					row7.LocationCode = row5.LocationCode;
					row7.ExportFlag = row5.ExportFlag;
					row7.SalesQuantity = row5.SalesQuantity;

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
					 * [tMap_3 main ] start
					 */

					s(currentComponent = "tMap_3");

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row7", "tUnite_1", "tUnite_1", "tUnite", "tMap_3", "tMap_3", "tMap"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row7 - " + (row7 == null ? "" : row7.toLogString()));
					}

					boolean hasCasePrimitiveKeyWithNull_tMap_3 = false;

					// ###############################
					// # Input tables (lookups)

					boolean rejectedInnerJoin_tMap_3 = false;
					boolean mainRowRejected_tMap_3 = false;
					// ###############################
					{ // start of Var scope

						// ###############################
						// # Vars tables

						Var__tMap_3__Struct Var = Var__tMap_3;// ###############################
						// ###############################
						// # Output tables

						out4 = null;

// # Output table : 'out4'
						count_out4_tMap_3++;

						out4_tmp.PRODUCT_LINE_CODE = row7.PRODUCT_LINE_CODE;
						out4_tmp.calender_month = row7.calender_month;
						out4_tmp.Product_Code = row7.Product_Code;
						out4_tmp.Product_Name = row7.Product_Name;
						out4_tmp.division = row7.division;
						out4_tmp.dept = row7.dept;
						out4_tmp.rnge = row7.rnge;
						out4_tmp.import_flag = row7.import_flag;
						out4_tmp.export_flag = row7.export_flag;
						out4_tmp.supplier_code = row7.supplier_code;
						out4_tmp.supplier_name = row7.supplier_name;
						out4_tmp.SUPPLIER_REF_NO = row7.SUPPLIER_REF_NO;
						out4_tmp.brand_group = row7.brand_group;
						out4_tmp.Nation_Sent = row7.Nation_Sent;
						out4_tmp.net_desp_units = row7.net_desp_units;
						out4_tmp.uk_net_desp_units = row7.uk_net_desp_units;
						out4_tmp.int_net_desp_units = row7.int_net_desp_units;
						out4_tmp.LocationCode = row7.LocationCode;
						out4_tmp.ExportFlag = row7.ExportFlag;
						out4_tmp.SalesQuantity = row7.SalesQuantity;
						out4_tmp.Audit_BatchName = null;
						out4_tmp.Audit_BatchName_Description = null;
						out4_tmp.Audit_BatchNo = null;
						out4_tmp.Audit_BatchTimeExecution = TalendDate.getDate("CCYY-MM-DD hh:mm:ss");
						out4 = out4_tmp;
						log.debug(
								"tMap_3 - Outputting the record " + count_out4_tMap_3 + " of the output table 'out4'.");

// ###############################

					} // end of Var scope

					rejectedInnerJoin_tMap_3 = false;

					tos_count_tMap_3++;

					/**
					 * [tMap_3 main ] stop
					 */

					/**
					 * [tMap_3 process_data_begin ] start
					 */

					s(currentComponent = "tMap_3");

					/**
					 * [tMap_3 process_data_begin ] stop
					 */

// Start of branch "out4"
					if (out4 != null) {

						/**
						 * [tFilterRow_30 main ] start
						 */

						s(currentComponent = "tFilterRow_30");

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "out4", "tMap_3", "tMap_3", "tMap", "tFilterRow_30", "tFilterRow_30", "tFilterRow"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("out4 - " + (out4 == null ? "" : out4.toLogString()));
						}

						row8 = null;
						Operator_tFilterRow_30 ope_tFilterRow_30 = new Operator_tFilterRow_30("||");
						ope_tFilterRow_30.matches((// code sample : use out4 to define the condition.
// out4.columnName1.equals("foo") ||!(out4.columnName2.equals("bar"))
// replace the following expression by your own filter condition 
						!Relational.ISNULL(out4.Product_Code) && !Relational.ISNULL(out4.Product_Name)
								&& !Relational.ISNULL(out4.LocationCode)), "advanced condition failed");

						if (ope_tFilterRow_30.getMatchFlag()) {
							if (row8 == null) {
								row8 = new row8Struct();
							}
							row8.PRODUCT_LINE_CODE = out4.PRODUCT_LINE_CODE;
							row8.calender_month = out4.calender_month;
							row8.Product_Code = out4.Product_Code;
							row8.Product_Name = out4.Product_Name;
							row8.division = out4.division;
							row8.dept = out4.dept;
							row8.rnge = out4.rnge;
							row8.import_flag = out4.import_flag;
							row8.export_flag = out4.export_flag;
							row8.supplier_code = out4.supplier_code;
							row8.supplier_name = out4.supplier_name;
							row8.SUPPLIER_REF_NO = out4.SUPPLIER_REF_NO;
							row8.brand_group = out4.brand_group;
							row8.Nation_Sent = out4.Nation_Sent;
							row8.net_desp_units = out4.net_desp_units;
							row8.uk_net_desp_units = out4.uk_net_desp_units;
							row8.int_net_desp_units = out4.int_net_desp_units;
							row8.LocationCode = out4.LocationCode;
							row8.ExportFlag = out4.ExportFlag;
							row8.SalesQuantity = out4.SalesQuantity;
							row8.Audit_BatchName = out4.Audit_BatchName;
							row8.Audit_BatchName_Description = out4.Audit_BatchName_Description;
							row8.Audit_BatchNo = out4.Audit_BatchNo;
							row8.Audit_BatchTimeExecution = out4.Audit_BatchTimeExecution;
							log.debug("tFilterRow_30 - Process the record " + (nb_line_tFilterRow_30 + 1) + ".");

							nb_line_ok_tFilterRow_30++;
						} else {
							nb_line_reject_tFilterRow_30++;
						}

						nb_line_tFilterRow_30++;

						tos_count_tFilterRow_30++;

						/**
						 * [tFilterRow_30 main ] stop
						 */

						/**
						 * [tFilterRow_30 process_data_begin ] start
						 */

						s(currentComponent = "tFilterRow_30");

						/**
						 * [tFilterRow_30 process_data_begin ] stop
						 */

// Start of branch "row8"
						if (row8 != null) {

							/**
							 * [tMap_4 main ] start
							 */

							s(currentComponent = "tMap_4");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row8", "tFilterRow_30", "tFilterRow_30", "tFilterRow", "tMap_4", "tMap_4", "tMap"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row8 - " + (row8 == null ? "" : row8.toLogString()));
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_4 = false;

							// ###############################
							// # Input tables (lookups)

							boolean rejectedInnerJoin_tMap_4 = false;
							boolean mainRowRejected_tMap_4 = false;
							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_4__Struct Var = Var__tMap_4;// ###############################
								// ###############################
								// # Output tables

								out5 = null;

// # Output table : 'out5'
								count_out5_tMap_4++;

								out5_tmp.PRODUCT_LINE_CODE = row8.PRODUCT_LINE_CODE;
								out5_tmp.calender_month = row8.calender_month;
								out5_tmp.Product_Code = row8.Product_Code;
								out5_tmp.Product_Name = row8.Product_Name;
								out5_tmp.division = row8.division;
								out5_tmp.dept = row8.dept;
								out5_tmp.rnge = row8.rnge;
								out5_tmp.import_flag = row8.import_flag;
								out5_tmp.export_flag = row8.export_flag;
								out5_tmp.supplier_code = row8.supplier_code;
								out5_tmp.supplier_name = row8.supplier_name;
								out5_tmp.SUPPLIER_REF_NO = row8.SUPPLIER_REF_NO;
								out5_tmp.brand_group = row8.brand_group;
								out5_tmp.Nation_Sent = row8.Nation_Sent;
								out5_tmp.net_desp_units = row8.net_desp_units;
								out5_tmp.uk_net_desp_units = row8.uk_net_desp_units;
								out5_tmp.int_net_desp_units = row8.int_net_desp_units;
								out5_tmp.LocationCode = row8.LocationCode;
								out5_tmp.ExportFlag = row8.ExportFlag;
								out5_tmp.SalesQuantity = row8.SalesQuantity;
								out5_tmp.Audit_BatchName = row8.Audit_BatchName;
								out5_tmp.Audit_BatchName_Description = row8.Audit_BatchName_Description;
								out5_tmp.Audit_BatchNo = row8.Audit_BatchNo;
								out5_tmp.Audit_BatchTimeExecution = row8.Audit_BatchTimeExecution;
								out5_tmp.brand_indicator = row8.brand_group.equals("Own Brand") ? "1" : "0";
								;
								out5 = out5_tmp;
								log.debug("tMap_4 - Outputting the record " + count_out5_tMap_4
										+ " of the output table 'out5'.");

// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_4 = false;

							tos_count_tMap_4++;

							/**
							 * [tMap_4 main ] stop
							 */

							/**
							 * [tMap_4 process_data_begin ] start
							 */

							s(currentComponent = "tMap_4");

							/**
							 * [tMap_4 process_data_begin ] stop
							 */

// Start of branch "out5"
							if (out5 != null) {

								/**
								 * [tLogRow_1 main ] start
								 */

								s(currentComponent = "tLogRow_1");

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "out5", "tMap_4", "tMap_4", "tMap", "tLogRow_1", "tLogRow_1", "tLogRow"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("out5 - " + (out5 == null ? "" : out5.toLogString()));
								}

///////////////////////		

								String[] row_tLogRow_1 = new String[25];

								if (out5.PRODUCT_LINE_CODE != null) { //
									row_tLogRow_1[0] = String.valueOf(out5.PRODUCT_LINE_CODE);

								} //

								if (out5.calender_month != null) { //
									row_tLogRow_1[1] = String.valueOf(out5.calender_month);

								} //

								if (out5.Product_Code != null) { //
									row_tLogRow_1[2] = String.valueOf(out5.Product_Code);

								} //

								if (out5.Product_Name != null) { //
									row_tLogRow_1[3] = String.valueOf(out5.Product_Name);

								} //

								if (out5.division != null) { //
									row_tLogRow_1[4] = String.valueOf(out5.division);

								} //

								if (out5.dept != null) { //
									row_tLogRow_1[5] = String.valueOf(out5.dept);

								} //

								if (out5.rnge != null) { //
									row_tLogRow_1[6] = String.valueOf(out5.rnge);

								} //

								if (out5.import_flag != null) { //
									row_tLogRow_1[7] = String.valueOf(out5.import_flag);

								} //

								if (out5.export_flag != null) { //
									row_tLogRow_1[8] = String.valueOf(out5.export_flag);

								} //

								if (out5.supplier_code != null) { //
									row_tLogRow_1[9] = String.valueOf(out5.supplier_code);

								} //

								if (out5.supplier_name != null) { //
									row_tLogRow_1[10] = String.valueOf(out5.supplier_name);

								} //

								if (out5.SUPPLIER_REF_NO != null) { //
									row_tLogRow_1[11] = String.valueOf(out5.SUPPLIER_REF_NO);

								} //

								if (out5.brand_group != null) { //
									row_tLogRow_1[12] = String.valueOf(out5.brand_group);

								} //

								if (out5.Nation_Sent != null) { //
									row_tLogRow_1[13] = String.valueOf(out5.Nation_Sent);

								} //

								if (out5.net_desp_units != null) { //
									row_tLogRow_1[14] = String.valueOf(out5.net_desp_units);

								} //

								if (out5.uk_net_desp_units != null) { //
									row_tLogRow_1[15] = String.valueOf(out5.uk_net_desp_units);

								} //

								if (out5.int_net_desp_units != null) { //
									row_tLogRow_1[16] = String.valueOf(out5.int_net_desp_units);

								} //

								if (out5.LocationCode != null) { //
									row_tLogRow_1[17] = String.valueOf(out5.LocationCode);

								} //

								if (out5.ExportFlag != null) { //
									row_tLogRow_1[18] = String.valueOf(out5.ExportFlag);

								} //

								if (out5.SalesQuantity != null) { //
									row_tLogRow_1[19] = String.valueOf(out5.SalesQuantity);

								} //

								if (out5.Audit_BatchName != null) { //
									row_tLogRow_1[20] = String.valueOf(out5.Audit_BatchName);

								} //

								if (out5.Audit_BatchName_Description != null) { //
									row_tLogRow_1[21] = String.valueOf(out5.Audit_BatchName_Description);

								} //

								if (out5.Audit_BatchNo != null) { //
									row_tLogRow_1[22] = String.valueOf(out5.Audit_BatchNo);

								} //

								if (out5.Audit_BatchTimeExecution != null) { //
									row_tLogRow_1[23] = String.valueOf(out5.Audit_BatchTimeExecution);

								} //

								if (out5.brand_indicator != null) { //
									row_tLogRow_1[24] = String.valueOf(out5.brand_indicator);

								} //

								util_tLogRow_1.addRow(row_tLogRow_1);
								nb_line_tLogRow_1++;
								log.info("tLogRow_1 - Content of row " + nb_line_tLogRow_1 + ": "
										+ TalendString.unionString("|", row_tLogRow_1));
//////

//////                    

///////////////////////    			

								row9 = out5;

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

										, "row9", "tLogRow_1", "tLogRow_1", "tLogRow", "tFileOutputExcel_1",
										"tFileOutputExcel_1", "tFileOutputExcel"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("row9 - " + (row9 == null ? "" : row9.toLogString()));
								}

								xlsxTool_tFileOutputExcel_1.addRow();

								if (row9.PRODUCT_LINE_CODE != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.PRODUCT_LINE_CODE));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.calender_month != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.calender_month));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.Product_Code != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.Product_Code));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.Product_Name != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.Product_Name));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.division != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.division));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.dept != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.dept));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.rnge != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.rnge));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.import_flag != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.import_flag));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.export_flag != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.export_flag));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.supplier_code != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.supplier_code));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.supplier_name != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.supplier_name));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.SUPPLIER_REF_NO != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.SUPPLIER_REF_NO));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.brand_group != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.brand_group));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.Nation_Sent != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.Nation_Sent));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.net_desp_units != null) {

									xlsxTool_tFileOutputExcel_1
											.addCellValue(Double.parseDouble(String.valueOf(row9.net_desp_units)));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.uk_net_desp_units != null) {

									xlsxTool_tFileOutputExcel_1
											.addCellValue(Double.parseDouble(String.valueOf(row9.uk_net_desp_units)));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.int_net_desp_units != null) {

									xlsxTool_tFileOutputExcel_1
											.addCellValue(Double.parseDouble(String.valueOf(row9.int_net_desp_units)));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.LocationCode != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.LocationCode));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.ExportFlag != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.ExportFlag));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.SalesQuantity != null) {

									xlsxTool_tFileOutputExcel_1
											.addCellValue(Double.parseDouble(String.valueOf(row9.SalesQuantity)));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.Audit_BatchName != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.Audit_BatchName));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.Audit_BatchName_Description != null) {

									xlsxTool_tFileOutputExcel_1
											.addCellValue(String.valueOf(row9.Audit_BatchName_Description));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.Audit_BatchNo != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.Audit_BatchNo));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.Audit_BatchTimeExecution != null) {

									xlsxTool_tFileOutputExcel_1
											.addCellValue(String.valueOf(row9.Audit_BatchTimeExecution));
								} else {
									xlsxTool_tFileOutputExcel_1.addCellNullValue();
								}

								if (row9.brand_indicator != null) {

									xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row9.brand_indicator));
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

							} // End of branch "out5"

							/**
							 * [tMap_4 process_data_end ] start
							 */

							s(currentComponent = "tMap_4");

							/**
							 * [tMap_4 process_data_end ] stop
							 */

						} // End of branch "row8"

						/**
						 * [tFilterRow_30 process_data_end ] start
						 */

						s(currentComponent = "tFilterRow_30");

						/**
						 * [tFilterRow_30 process_data_end ] stop
						 */

					} // End of branch "out4"

					/**
					 * [tMap_3 process_data_end ] start
					 */

					s(currentComponent = "tMap_3");

					/**
					 * [tMap_3 process_data_end ] stop
					 */

					/**
					 * [tUnite_1 process_data_end ] start
					 */

					s(currentComponent = "tUnite_1");

					/**
					 * [tUnite_1 process_data_end ] stop
					 */

					/**
					 * [tHashInput_1 process_data_end ] start
					 */

					s(currentComponent = "tHashInput_1");

					/**
					 * [tHashInput_1 process_data_end ] stop
					 */

					/**
					 * [tHashInput_1 end ] start
					 */

					s(currentComponent = "tHashInput_1");

					nb_line_tHashInput_1++;
				}

				org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
						.remove("tHashFile_JDWilliams_Hard_Coded_" + pid + "_tHashOutput_1");

				globalMap.put("tHashInput_1_NB_LINE", nb_line_tHashInput_1);

				ok_Hash.put("tHashInput_1", true);
				end_Hash.put("tHashInput_1", System.currentTimeMillis());

				/**
				 * [tHashInput_1 end ] stop
				 */

				/**
				 * [tUnite_1 end ] start
				 */

				s(currentComponent = "tUnite_1");

				globalMap.put("tUnite_1_NB_LINE", nb_line_tUnite_1);
				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "out2", "row5");
				}

				if (enableLogStash) {

					if (runStat.log(resourceMap, iterateId, "out2", 2, 0, "tMap_2", "tMap_2", "tMap", "tUnite_1",
							"tUnite_1", "tUnite", "output")) {
						talendJobLogProcess(globalMap);
					}

					if (runStat.log(resourceMap, iterateId, "row5", 2, 0, "tHashInput_1", "tHashInput_1", "tHashInput",
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
				 * [tMap_3 end ] start
				 */

				s(currentComponent = "tMap_3");

// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("tMap_3 - Written records count in the table 'out4': " + count_out4_tMap_3 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row7", 2, 0, "tUnite_1",
						"tUnite_1", "tUnite", "tMap_3", "tMap_3", "tMap", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tMap_3 - " + ("Done."));

				ok_Hash.put("tMap_3", true);
				end_Hash.put("tMap_3", System.currentTimeMillis());

				/**
				 * [tMap_3 end ] stop
				 */

				/**
				 * [tFilterRow_30 end ] start
				 */

				s(currentComponent = "tFilterRow_30");

				globalMap.put("tFilterRow_30_NB_LINE", nb_line_tFilterRow_30);
				globalMap.put("tFilterRow_30_NB_LINE_OK", nb_line_ok_tFilterRow_30);
				globalMap.put("tFilterRow_30_NB_LINE_REJECT", nb_line_reject_tFilterRow_30);

				log.info("tFilterRow_30 - Processed records count:" + nb_line_tFilterRow_30 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_30 + ". Rejected records count:" + nb_line_reject_tFilterRow_30 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "out4", 2, 0, "tMap_3",
						"tMap_3", "tMap", "tFilterRow_30", "tFilterRow_30", "tFilterRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFilterRow_30 - " + ("Done."));

				ok_Hash.put("tFilterRow_30", true);
				end_Hash.put("tFilterRow_30", System.currentTimeMillis());

				/**
				 * [tFilterRow_30 end ] stop
				 */

				/**
				 * [tMap_4 end ] start
				 */

				s(currentComponent = "tMap_4");

// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("tMap_4 - Written records count in the table 'out5': " + count_out5_tMap_4 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row8", 2, 0,
						"tFilterRow_30", "tFilterRow_30", "tFilterRow", "tMap_4", "tMap_4", "tMap", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tMap_4 - " + ("Done."));

				ok_Hash.put("tMap_4", true);
				end_Hash.put("tMap_4", System.currentTimeMillis());

				/**
				 * [tMap_4 end ] stop
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

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "out5", 2, 0, "tMap_4",
						"tMap_4", "tMap", "tLogRow_1", "tLogRow_1", "tLogRow", "output")) {
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

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row9", 2, 0,
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
				 * [tFilterRow_11 finally ] start
				 */

				s(currentComponent = "tFilterRow_11");

				/**
				 * [tFilterRow_11 finally ] stop
				 */

				/**
				 * [tUniqRow_1 finally ] start
				 */

				s(currentComponent = "tUniqRow_1");

				/**
				 * [tUniqRow_1 finally ] stop
				 */

				/**
				 * [tMap_2 finally ] start
				 */

				s(currentComponent = "tMap_2");

				/**
				 * [tMap_2 finally ] stop
				 */

				/**
				 * [tHashOutput_1 finally ] start
				 */

				s(currentComponent = "tHashOutput_1");

				/**
				 * [tHashOutput_1 finally ] stop
				 */

				/**
				 * [tLogRow_4 finally ] start
				 */

				s(currentComponent = "tLogRow_4");

				cLabel = "duplicates";

				/**
				 * [tLogRow_4 finally ] stop
				 */

				/**
				 * [tLogRow_2 finally ] start
				 */

				s(currentComponent = "tLogRow_2");

				cLabel = "rejects";

				/**
				 * [tLogRow_2 finally ] stop
				 */

				/**
				 * [tHashInput_1 finally ] start
				 */

				s(currentComponent = "tHashInput_1");

				/**
				 * [tHashInput_1 finally ] stop
				 */

				/**
				 * [tUnite_1 finally ] start
				 */

				s(currentComponent = "tUnite_1");

				/**
				 * [tUnite_1 finally ] stop
				 */

				/**
				 * [tMap_3 finally ] start
				 */

				s(currentComponent = "tMap_3");

				/**
				 * [tMap_3 finally ] stop
				 */

				/**
				 * [tFilterRow_30 finally ] start
				 */

				s(currentComponent = "tFilterRow_30");

				/**
				 * [tFilterRow_30 finally ] stop
				 */

				/**
				 * [tMap_4 finally ] start
				 */

				s(currentComponent = "tMap_4");

				/**
				 * [tMap_4 finally ] stop
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
		final JDWilliams_Hard_Coded JDWilliams_Hard_CodedClass = new JDWilliams_Hard_Coded();

		int exitCode = JDWilliams_Hard_CodedClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'JDWilliams_Hard_Coded' - Done.");
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
		log.info("TalendJob: 'JDWilliams_Hard_Coded' - Start.");

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
		org.slf4j.MDC.put("_jobRepositoryId", "_sRjUgLCtEe-zrp84tBKbig");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2024-12-04T09:27:48.837350100Z");

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
			java.io.InputStream inContext = JDWilliams_Hard_Coded.class.getClassLoader()
					.getResourceAsStream("valpak_poc/jdwilliams_hard_coded_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = JDWilliams_Hard_Coded.class.getClassLoader()
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
					context.setContextType("PRODUCT_NUMBER", "id_String");
					if (context.getStringValue("PRODUCT_NUMBER") == null) {
						context.PRODUCT_NUMBER = null;
					} else {
						context.PRODUCT_NUMBER = (String) context.getProperty("PRODUCT_NUMBER");
					}
					context.setContextType("product_desc", "id_String");
					if (context.getStringValue("product_desc") == null) {
						context.product_desc = null;
					} else {
						context.product_desc = (String) context.getProperty("product_desc");
					}
					context.setContextType("SUPPLIER_NUMBER", "id_String");
					if (context.getStringValue("SUPPLIER_NUMBER") == null) {
						context.SUPPLIER_NUMBER = null;
					} else {
						context.SUPPLIER_NUMBER = (String) context.getProperty("SUPPLIER_NUMBER");
					}
					context.setContextType("supplier_name", "id_String");
					if (context.getStringValue("supplier_name") == null) {
						context.supplier_name = null;
					} else {
						context.supplier_name = (String) context.getProperty("supplier_name");
					}
					context.setContextType("brand_group", "id_String");
					if (context.getStringValue("brand_group") == null) {
						context.brand_group = null;
					} else {
						context.brand_group = (String) context.getProperty("brand_group");
					}
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
			if (parentContextMap.containsKey("PRODUCT_NUMBER")) {
				context.PRODUCT_NUMBER = (String) parentContextMap.get("PRODUCT_NUMBER");
			}
			if (parentContextMap.containsKey("product_desc")) {
				context.product_desc = (String) parentContextMap.get("product_desc");
			}
			if (parentContextMap.containsKey("SUPPLIER_NUMBER")) {
				context.SUPPLIER_NUMBER = (String) parentContextMap.get("SUPPLIER_NUMBER");
			}
			if (parentContextMap.containsKey("supplier_name")) {
				context.supplier_name = (String) parentContextMap.get("supplier_name");
			}
			if (parentContextMap.containsKey("brand_group")) {
				context.brand_group = (String) parentContextMap.get("brand_group");
			}
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
		log.info("TalendJob: 'JDWilliams_Hard_Coded' - Started.");
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
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : JDWilliams_Hard_Coded");
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
		log.info("TalendJob: 'JDWilliams_Hard_Coded' - Finished - status: " + status + " returnCode: " + returnCode);

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
 * 705136 characters generated by Talend Cloud Data Fabric on the December 4,
 * 2024 at 9:27:48 AM UTC
 ************************************************************************************************/