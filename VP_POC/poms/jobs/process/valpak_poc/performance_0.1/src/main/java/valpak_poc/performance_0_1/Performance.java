
package valpak_poc.performance_0_1;

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
 * Job: Performance Purpose: <br>
 * Description: <br>
 * 
 * @author alex.hicks@qlik.com
 * @version 8.0.1.20241121_1314-patch
 * @status
 */
public class Performance implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "Performance.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(Performance.class);

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
	private final String jobName = "Performance";
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
			"_obQ5ILJSEe-uK407vvt5gg", "0.1");
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
					Performance.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Performance.this, new Object[] { e, currentComponent, globalMap });
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

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendJobLog_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendJobLog_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_Performance = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_Performance = new byte[0];

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

		public Boolean ORIGINAL_MARK;

		public Boolean getORIGINAL_MARK() {
			return this.ORIGINAL_MARK;
		}

		public Boolean ORIGINAL_MARKIsNullable() {
			return true;
		}

		public Boolean ORIGINAL_MARKIsKey() {
			return false;
		}

		public Integer ORIGINAL_MARKLength() {
			return 0;
		}

		public Integer ORIGINAL_MARKPrecision() {
			return 0;
		}

		public String ORIGINAL_MARKDefault() {

			return "";

		}

		public String ORIGINAL_MARKComment() {

			return null;

		}

		public String ORIGINAL_MARKPattern() {

			return null;

		}

		public String ORIGINAL_MARKOriginalDbColumnName() {

			return "ORIGINAL_MARK";

		}

		public String newColumn1;

		public String getNewColumn1() {
			return this.newColumn1;
		}

		public Boolean newColumn1IsNullable() {
			return true;
		}

		public Boolean newColumn1IsKey() {
			return false;
		}

		public Integer newColumn1Length() {
			return null;
		}

		public Integer newColumn1Precision() {
			return null;
		}

		public String newColumn1Default() {

			return null;

		}

		public String newColumn1Comment() {

			return "";

		}

		public String newColumn1Pattern() {

			return "";

		}

		public String newColumn1OriginalDbColumnName() {

			return "newColumn1";

		}

		public String newColumn;

		public String getNewColumn() {
			return this.newColumn;
		}

		public Boolean newColumnIsNullable() {
			return true;
		}

		public Boolean newColumnIsKey() {
			return false;
		}

		public Integer newColumnLength() {
			return null;
		}

		public Integer newColumnPrecision() {
			return null;
		}

		public String newColumnDefault() {

			return null;

		}

		public String newColumnComment() {

			return "";

		}

		public String newColumnPattern() {

			return "";

		}

		public String newColumnOriginalDbColumnName() {

			return "newColumn";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_Performance.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_Performance.length == 0) {
						commonByteArray_VALPAK_POC_Performance = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_Performance = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_Performance, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_Performance, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_Performance.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_Performance.length == 0) {
						commonByteArray_VALPAK_POC_Performance = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_Performance = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_Performance, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_Performance, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_Performance) {

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

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ORIGINAL_MARK = null;
					} else {
						this.ORIGINAL_MARK = dis.readBoolean();
					}

					this.newColumn1 = readString(dis);

					this.newColumn = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_Performance) {

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

					this.uk_net_desp_units = readInteger(dis);

					this.int_net_desp_units = readInteger(dis);

					this.LocationCode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ORIGINAL_MARK = null;
					} else {
						this.ORIGINAL_MARK = dis.readBoolean();
					}

					this.newColumn1 = readString(dis);

					this.newColumn = readString(dis);

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

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// Boolean

				if (this.ORIGINAL_MARK == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.ORIGINAL_MARK);
				}

				// String

				writeString(this.newColumn1, dos);

				// String

				writeString(this.newColumn, dos);

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

				writeInteger(this.uk_net_desp_units, dos);

				// Integer

				writeInteger(this.int_net_desp_units, dos);

				// String

				writeString(this.LocationCode, dos);

				// Boolean

				if (this.ORIGINAL_MARK == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.ORIGINAL_MARK);
				}

				// String

				writeString(this.newColumn1, dos);

				// String

				writeString(this.newColumn, dos);

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
			sb.append(",uk_net_desp_units=" + String.valueOf(uk_net_desp_units));
			sb.append(",int_net_desp_units=" + String.valueOf(int_net_desp_units));
			sb.append(",LocationCode=" + LocationCode);
			sb.append(",ORIGINAL_MARK=" + String.valueOf(ORIGINAL_MARK));
			sb.append(",newColumn1=" + newColumn1);
			sb.append(",newColumn=" + newColumn);
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

			if (ORIGINAL_MARK == null) {
				sb.append("<null>");
			} else {
				sb.append(ORIGINAL_MARK);
			}

			sb.append("|");

			if (newColumn1 == null) {
				sb.append("<null>");
			} else {
				sb.append(newColumn1);
			}

			sb.append("|");

			if (newColumn == null) {
				sb.append("<null>");
			} else {
				sb.append(newColumn);
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

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_Performance = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_Performance = new byte[0];

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

		public Boolean ORIGINAL_MARK;

		public Boolean getORIGINAL_MARK() {
			return this.ORIGINAL_MARK;
		}

		public Boolean ORIGINAL_MARKIsNullable() {
			return true;
		}

		public Boolean ORIGINAL_MARKIsKey() {
			return false;
		}

		public Integer ORIGINAL_MARKLength() {
			return 0;
		}

		public Integer ORIGINAL_MARKPrecision() {
			return 0;
		}

		public String ORIGINAL_MARKDefault() {

			return "";

		}

		public String ORIGINAL_MARKComment() {

			return null;

		}

		public String ORIGINAL_MARKPattern() {

			return null;

		}

		public String ORIGINAL_MARKOriginalDbColumnName() {

			return "ORIGINAL_MARK";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_VALPAK_POC_Performance.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_Performance.length == 0) {
						commonByteArray_VALPAK_POC_Performance = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_Performance = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_Performance, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_Performance, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_Performance.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_Performance.length == 0) {
						commonByteArray_VALPAK_POC_Performance = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_Performance = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_Performance, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_Performance, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_Performance) {

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

					length = dis.readByte();
					if (length == -1) {
						this.ORIGINAL_MARK = null;
					} else {
						this.ORIGINAL_MARK = dis.readBoolean();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_Performance) {

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

					length = dis.readByte();
					if (length == -1) {
						this.ORIGINAL_MARK = null;
					} else {
						this.ORIGINAL_MARK = dis.readBoolean();
					}

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

				// Boolean

				if (this.ORIGINAL_MARK == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.ORIGINAL_MARK);
				}

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

				// Boolean

				if (this.ORIGINAL_MARK == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.ORIGINAL_MARK);
				}

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
			sb.append(",ORIGINAL_MARK=" + String.valueOf(ORIGINAL_MARK));
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

			if (ORIGINAL_MARK == null) {
				sb.append("<null>");
			} else {
				sb.append(ORIGINAL_MARK);
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

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tFileInputDelimited_1", "Lgysmm_");

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

				row3Struct row3 = new row3Struct();
				out1Struct out1 = new out1Struct();

				/**
				 * [tFileOutputDelimited_2 begin ] start
				 */

				sh("tFileOutputDelimited_2");

				s(currentComponent = "tFileOutputDelimited_2");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "out1");

				int tos_count_tFileOutputDelimited_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileOutputDelimited_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileOutputDelimited_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileOutputDelimited_2 = new StringBuilder();
							log4jParamters_tFileOutputDelimited_2.append("Parameters:");
							log4jParamters_tFileOutputDelimited_2.append("USESTREAM" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("FILENAME" + " = "
									+ "\"C:/Users/valpakuser/Desktop/Outputs/JDWilliams_performance_test_output\"");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("FIELDSEPARATOR" + " = " + "\";\"");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("APPEND" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("INCLUDEHEADER" + " = " + "true");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("COMPRESS" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("CREATE" + " = " + "true");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("SPLIT" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("FLUSHONROW" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("ROW_MODE" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("DELETE_EMPTYFILE" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("FILE_EXIST_EXCEPTION" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileOutputDelimited_2 - " + (log4jParamters_tFileOutputDelimited_2));
						}
					}
					new BytesLimit65535_tFileOutputDelimited_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileOutputDelimited_2", "tFileOutputDelimited_2", "tFileOutputDelimited");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				String fileName_tFileOutputDelimited_2 = "";
				fileName_tFileOutputDelimited_2 = (new java.io.File(
						"C:/Users/valpakuser/Desktop/Outputs/JDWilliams_performance_test_output")).getAbsolutePath()
						.replace("\\", "/");
				String fullName_tFileOutputDelimited_2 = null;
				String extension_tFileOutputDelimited_2 = null;
				String directory_tFileOutputDelimited_2 = null;
				if ((fileName_tFileOutputDelimited_2.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_2.lastIndexOf(".") < fileName_tFileOutputDelimited_2
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2;
						extension_tFileOutputDelimited_2 = "";
					} else {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
								fileName_tFileOutputDelimited_2.lastIndexOf("."));
						extension_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(fileName_tFileOutputDelimited_2.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
							fileName_tFileOutputDelimited_2.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_2.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
								fileName_tFileOutputDelimited_2.lastIndexOf("."));
						extension_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(fileName_tFileOutputDelimited_2.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2;
						extension_tFileOutputDelimited_2 = "";
					}
					directory_tFileOutputDelimited_2 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_2 = true;
				java.io.File filetFileOutputDelimited_2 = new java.io.File(fileName_tFileOutputDelimited_2);
				globalMap.put("tFileOutputDelimited_2_FILE_NAME", fileName_tFileOutputDelimited_2);
				int nb_line_tFileOutputDelimited_2 = 0;
				int splitedFileNo_tFileOutputDelimited_2 = 0;
				int currentRow_tFileOutputDelimited_2 = 0;

				final String OUT_DELIM_tFileOutputDelimited_2 = /** Start field tFileOutputDelimited_2:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_2:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_2 = /**
																		 * Start field
																		 * tFileOutputDelimited_2:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_2:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_2 != null && directory_tFileOutputDelimited_2.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_2 = new java.io.File(directory_tFileOutputDelimited_2);
					if (!dir_tFileOutputDelimited_2.exists()) {
						log.info("tFileOutputDelimited_2 - Creating directory '"
								+ dir_tFileOutputDelimited_2.getCanonicalPath() + "'.");
						dir_tFileOutputDelimited_2.mkdirs();
						log.info("tFileOutputDelimited_2 - The directory '"
								+ dir_tFileOutputDelimited_2.getCanonicalPath() + "' has been created successfully.");
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_2 = null;

				java.io.File fileToDelete_tFileOutputDelimited_2 = new java.io.File(fileName_tFileOutputDelimited_2);
				if (fileToDelete_tFileOutputDelimited_2.exists()) {
					fileToDelete_tFileOutputDelimited_2.delete();
				}
				outtFileOutputDelimited_2 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_2, false), "ISO-8859-15"));
				resourceMap.put("out_tFileOutputDelimited_2", outtFileOutputDelimited_2);
				if (filetFileOutputDelimited_2.length() == 0) {
					outtFileOutputDelimited_2.write("PRODUCT_LINE_CODE");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("calender_month");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("PRODUCT_NUMBER");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("product_desc");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("division");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("dept");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("rnge");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("import_flag");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("export_flag");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("SUPPLIER_NUMBER");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("supplier_name");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("SUPPLIER_REF_NO");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("brand_group");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("Nation_Sent");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("uk_net_desp_units");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("int_net_desp_units");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("LocationCode");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("ORIGINAL_MARK");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("newColumn1");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("newColumn");
					outtFileOutputDelimited_2.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.flush();
				}

				resourceMap.put("nb_line_tFileOutputDelimited_2", nb_line_tFileOutputDelimited_2);

				/**
				 * [tFileOutputDelimited_2 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				sh("tMap_1");

				s(currentComponent = "tMap_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row3");

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
				int count_row3_tMap_1 = 0;

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
				 * [tFileInputDelimited_1 begin ] start
				 */

				sh("tFileInputDelimited_1");

				s(currentComponent = "tFileInputDelimited_1");

				int tos_count_tFileInputDelimited_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputDelimited_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputDelimited_1 = new StringBuilder();
							log4jParamters_tFileInputDelimited_1.append("Parameters:");
							log4jParamters_tFileInputDelimited_1.append("USE_EXISTING_DYNAMIC" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("FILENAME" + " = "
									+ "\"C:/Users/valpakuser/Desktop/Outputs/JDWilliams_performance_test\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("FIELDSEPARATOR" + " = " + "\";\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("HEADER" + " = " + "1");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("FOOTER" + " = " + "0");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("LIMIT" + " = " + "");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("REMOVE_EMPTY_ROW" + " = " + "true");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("UNCOMPRESS" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("RANDOM" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("TRIMALL" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("TRIMSELECT" + " = " + "[{TRIM=" + ("false")
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
									+ ", SCHEMA_COLUMN=" + ("LocationCode") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("ORIGINAL_MARK") + "}]");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CHECK_FIELDS_NUM" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CHECK_DATE" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("SPLITRECORD" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ENABLE_DECODE" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("USE_HEADER_AS_IS" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputDelimited_1 - " + (log4jParamters_tFileInputDelimited_1));
						}
					}
					new BytesLimit65535_tFileInputDelimited_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileInputDelimited_1", "tFileInputDelimited_1", "tFileInputDelimited");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try {

					Object filename_tFileInputDelimited_1 = "C:/Users/valpakuser/Desktop/Outputs/JDWilliams_performance_test";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/valpakuser/Desktop/Outputs/JDWilliams_performance_test", "ISO-8859-15", ";",
								"\n", true, 1, 0, limit_tFileInputDelimited_1, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						log.error("tFileInputDelimited_1 - " + e.getMessage());

						System.err.println(e.getMessage());

					}

					log.info("tFileInputDelimited_1 - Retrieving records from the datasource.");

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row3 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row3 = new row3Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_1 = 0;

							row3.PRODUCT_LINE_CODE = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 1;

							row3.calender_month = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 2;

							row3.PRODUCT_NUMBER = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 3;

							row3.product_desc = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 4;

							row3.division = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 5;

							row3.dept = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 6;

							row3.rnge = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 7;

							row3.import_flag = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 8;

							row3.export_flag = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 9;

							row3.SUPPLIER_NUMBER = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 10;

							row3.supplier_name = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 11;

							row3.SUPPLIER_REF_NO = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 12;

							row3.brand_group = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 13;

							row3.Nation_Sent = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 14;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row3.net_desp_units = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"net_desp_units", "row3", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row3.net_desp_units = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 15;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row3.uk_net_desp_units = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"uk_net_desp_units", "row3", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row3.uk_net_desp_units = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 16;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row3.int_net_desp_units = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"int_net_desp_units", "row3", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row3.int_net_desp_units = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 17;

							row3.LocationCode = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 18;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row3.ORIGINAL_MARK = ParserUtils.parseTo_Boolean(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ORIGINAL_MARK", "row3", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row3.ORIGINAL_MARK = null;

							}

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							log.error("tFileInputDelimited_1 - " + e.getMessage());

							System.err.println(e.getMessage());
							row3 = null;

						}

						log.debug("tFileInputDelimited_1 - Retrieving the record "
								+ fid_tFileInputDelimited_1.getRowNumber() + ".");

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						s(currentComponent = "tFileInputDelimited_1");

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						s(currentComponent = "tFileInputDelimited_1");

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */

// Start of branch "row3"
						if (row3 != null) {

							/**
							 * [tMap_1 main ] start
							 */

							s(currentComponent = "tMap_1");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row3", "tFileInputDelimited_1", "tFileInputDelimited_1", "tFileInputDelimited",
									"tMap_1", "tMap_1", "tMap"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row3 - " + (row3 == null ? "" : row3.toLogString()));
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

								out1_tmp.PRODUCT_LINE_CODE = row3.PRODUCT_LINE_CODE;
								out1_tmp.calender_month = row3.calender_month;
								out1_tmp.PRODUCT_NUMBER = row3.PRODUCT_NUMBER;
								out1_tmp.product_desc = row3.product_desc;
								out1_tmp.division = row3.division;
								out1_tmp.dept = row3.dept;
								out1_tmp.rnge = row3.rnge;
								out1_tmp.import_flag = row3.import_flag;
								out1_tmp.export_flag = row3.export_flag;
								out1_tmp.SUPPLIER_NUMBER = row3.SUPPLIER_NUMBER;
								out1_tmp.supplier_name = row3.supplier_name;
								out1_tmp.SUPPLIER_REF_NO = row3.SUPPLIER_REF_NO;
								out1_tmp.brand_group = row3.brand_group;
								out1_tmp.Nation_Sent = row3.Nation_Sent;
								out1_tmp.uk_net_desp_units = row3.uk_net_desp_units;
								out1_tmp.int_net_desp_units = row3.int_net_desp_units;
								out1_tmp.LocationCode = row3.LocationCode;
								out1_tmp.ORIGINAL_MARK = row3.ORIGINAL_MARK;
								out1_tmp.newColumn1 = row3.LocationCode + " " + row3.Nation_Sent;
								out1_tmp.newColumn = "test";
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
								 * [tFileOutputDelimited_2 main ] start
								 */

								s(currentComponent = "tFileOutputDelimited_2");

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "out1", "tMap_1", "tMap_1", "tMap", "tFileOutputDelimited_2",
										"tFileOutputDelimited_2", "tFileOutputDelimited"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("out1 - " + (out1 == null ? "" : out1.toLogString()));
								}

								StringBuilder sb_tFileOutputDelimited_2 = new StringBuilder();
								if (out1.PRODUCT_LINE_CODE != null) {
									sb_tFileOutputDelimited_2.append(out1.PRODUCT_LINE_CODE);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.calender_month != null) {
									sb_tFileOutputDelimited_2.append(out1.calender_month);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.PRODUCT_NUMBER != null) {
									sb_tFileOutputDelimited_2.append(out1.PRODUCT_NUMBER);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.product_desc != null) {
									sb_tFileOutputDelimited_2.append(out1.product_desc);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.division != null) {
									sb_tFileOutputDelimited_2.append(out1.division);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.dept != null) {
									sb_tFileOutputDelimited_2.append(out1.dept);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.rnge != null) {
									sb_tFileOutputDelimited_2.append(out1.rnge);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.import_flag != null) {
									sb_tFileOutputDelimited_2.append(out1.import_flag);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.export_flag != null) {
									sb_tFileOutputDelimited_2.append(out1.export_flag);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.SUPPLIER_NUMBER != null) {
									sb_tFileOutputDelimited_2.append(out1.SUPPLIER_NUMBER);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.supplier_name != null) {
									sb_tFileOutputDelimited_2.append(out1.supplier_name);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.SUPPLIER_REF_NO != null) {
									sb_tFileOutputDelimited_2.append(out1.SUPPLIER_REF_NO);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.brand_group != null) {
									sb_tFileOutputDelimited_2.append(out1.brand_group);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.Nation_Sent != null) {
									sb_tFileOutputDelimited_2.append(out1.Nation_Sent);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.uk_net_desp_units != null) {
									sb_tFileOutputDelimited_2.append(out1.uk_net_desp_units);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.int_net_desp_units != null) {
									sb_tFileOutputDelimited_2.append(out1.int_net_desp_units);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.LocationCode != null) {
									sb_tFileOutputDelimited_2.append(out1.LocationCode);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.ORIGINAL_MARK != null) {
									sb_tFileOutputDelimited_2.append(out1.ORIGINAL_MARK);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.newColumn1 != null) {
									sb_tFileOutputDelimited_2.append(out1.newColumn1);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (out1.newColumn != null) {
									sb_tFileOutputDelimited_2.append(out1.newColumn);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_2);

								nb_line_tFileOutputDelimited_2++;
								resourceMap.put("nb_line_tFileOutputDelimited_2", nb_line_tFileOutputDelimited_2);

								outtFileOutputDelimited_2.write(sb_tFileOutputDelimited_2.toString());
								log.debug("tFileOutputDelimited_2 - Writing the record "
										+ nb_line_tFileOutputDelimited_2 + ".");

								tos_count_tFileOutputDelimited_2++;

								/**
								 * [tFileOutputDelimited_2 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_2 process_data_begin ] start
								 */

								s(currentComponent = "tFileOutputDelimited_2");

								/**
								 * [tFileOutputDelimited_2 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_2 process_data_end ] start
								 */

								s(currentComponent = "tFileOutputDelimited_2");

								/**
								 * [tFileOutputDelimited_2 process_data_end ] stop
								 */

							} // End of branch "out1"

							/**
							 * [tMap_1 process_data_end ] start
							 */

							s(currentComponent = "tMap_1");

							/**
							 * [tMap_1 process_data_end ] stop
							 */

						} // End of branch "row3"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						s(currentComponent = "tFileInputDelimited_1");

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						s(currentComponent = "tFileInputDelimited_1");

					}
				} finally {
					if (!((Object) ("C:/Users/valpakuser/Desktop/Outputs/JDWilliams_performance_test") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());

						log.info("tFileInputDelimited_1 - Retrieved records count: "
								+ fid_tFileInputDelimited_1.getRowNumber() + ".");

					}
				}

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_1 - " + ("Done."));

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				s(currentComponent = "tMap_1");

// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("tMap_1 - Written records count in the table 'out1': " + count_out1_tMap_1 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row3", 2, 0,
						"tFileInputDelimited_1", "tFileInputDelimited_1", "tFileInputDelimited", "tMap_1", "tMap_1",
						"tMap", "output")) {
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
				 * [tFileOutputDelimited_2 end ] start
				 */

				s(currentComponent = "tFileOutputDelimited_2");

				if (outtFileOutputDelimited_2 != null) {
					outtFileOutputDelimited_2.flush();
					outtFileOutputDelimited_2.close();
				}

				globalMap.put("tFileOutputDelimited_2_NB_LINE", nb_line_tFileOutputDelimited_2);
				globalMap.put("tFileOutputDelimited_2_FILE_NAME", fileName_tFileOutputDelimited_2);

				resourceMap.put("finish_tFileOutputDelimited_2", true);

				log.debug("tFileOutputDelimited_2 - Written records count: " + nb_line_tFileOutputDelimited_2 + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "out1", 2, 0, "tMap_1",
						"tMap_1", "tMap", "tFileOutputDelimited_2", "tFileOutputDelimited_2", "tFileOutputDelimited",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFileOutputDelimited_2 - " + ("Done."));

				ok_Hash.put("tFileOutputDelimited_2", true);
				end_Hash.put("tFileOutputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_2 end ] stop
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
				 * [tFileInputDelimited_1 finally ] start
				 */

				s(currentComponent = "tFileInputDelimited_1");

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				s(currentComponent = "tMap_1");

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 finally ] start
				 */

				s(currentComponent = "tFileOutputDelimited_2");

				if (resourceMap.get("finish_tFileOutputDelimited_2") == null) {

					java.io.Writer outtFileOutputDelimited_2 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_2");
					if (outtFileOutputDelimited_2 != null) {
						outtFileOutputDelimited_2.flush();
						outtFileOutputDelimited_2.close();
					}

				}

				/**
				 * [tFileOutputDelimited_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
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
		final Performance PerformanceClass = new Performance();

		int exitCode = PerformanceClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'Performance' - Done.");
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
		log.info("TalendJob: 'Performance' - Start.");

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
		org.slf4j.MDC.put("_jobRepositoryId", "_obQ5ILJSEe-uK407vvt5gg");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2024-12-04T15:41:40.193952200Z");

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
			java.io.InputStream inContext = Performance.class.getClassLoader()
					.getResourceAsStream("valpak_poc/performance_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Performance.class.getClassLoader()
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
		log.info("TalendJob: 'Performance' - Started.");
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
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Performance");
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
		log.info("TalendJob: 'Performance' - Finished - status: " + status + " returnCode: " + returnCode);

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
 * 155456 characters generated by Talend Cloud Data Fabric on the December 4,
 * 2024 at 3:41:40 PM UTC
 ************************************************************************************************/