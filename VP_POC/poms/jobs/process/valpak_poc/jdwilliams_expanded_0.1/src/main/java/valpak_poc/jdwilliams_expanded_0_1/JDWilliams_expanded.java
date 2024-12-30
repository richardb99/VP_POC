
package valpak_poc.jdwilliams_expanded_0_1;

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
 * Job: JDWilliams_expanded Purpose: <br>
 * Description: <br>
 * 
 * @author alex.hicks@qlik.com
 * @version 8.0.1.20241121_1314-patch
 * @status
 */
public class JDWilliams_expanded implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "JDWilliams_expanded.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(JDWilliams_expanded.class);

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
	private final String jobName = "JDWilliams_expanded";
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
			"_i6-7oLL7Ee-CDa9ighBWHg", "0.1");
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
					JDWilliams_expanded.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(JDWilliams_expanded.this, new Object[] { e, currentComponent, globalMap });
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

	public void tPrejob_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tPrejob_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSchemaComplianceCheck_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMsgBox_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tMsgBox_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_11_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUniqRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUnite_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_30_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tRunJob_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRunJob_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDataStewardshipTaskOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMsgBox_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tMsgBox_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDie_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tMsgBox_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendJobLog_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendJobLog_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tPrejob_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputExcel_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tMsgBox_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputExcel_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tRunJob_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tMsgBox_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tPrejob_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tPrejob_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tPrejob_1", "UozLph_");

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
				 * [tPrejob_1 begin ] start
				 */

				sh("tPrejob_1");

				s(currentComponent = "tPrejob_1");

				int tos_count_tPrejob_1 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tPrejob_1", "tPrejob_1", "tPrejob");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				/**
				 * [tPrejob_1 begin ] stop
				 */

				/**
				 * [tPrejob_1 main ] start
				 */

				s(currentComponent = "tPrejob_1");

				tos_count_tPrejob_1++;

				/**
				 * [tPrejob_1 main ] stop
				 */

				/**
				 * [tPrejob_1 process_data_begin ] start
				 */

				s(currentComponent = "tPrejob_1");

				/**
				 * [tPrejob_1 process_data_begin ] stop
				 */

				/**
				 * [tPrejob_1 process_data_end ] start
				 */

				s(currentComponent = "tPrejob_1");

				/**
				 * [tPrejob_1 process_data_end ] stop
				 */

				/**
				 * [tPrejob_1 end ] start
				 */

				s(currentComponent = "tPrejob_1");

				ok_Hash.put("tPrejob_1", true);
				end_Hash.put("tPrejob_1", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk1", 0, "ok");
				}
				tFileInputExcel_1Process(globalMap);

				/**
				 * [tPrejob_1 end ] stop
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
				 * [tPrejob_1 finally ] start
				 */

				s(currentComponent = "tPrejob_1");

				/**
				 * [tPrejob_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tPrejob_1_SUBPROCESS_STATE", 1);
	}

	public static class row12Struct implements routines.system.IPersistableRow<row12Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

	public static class row10Struct implements routines.system.IPersistableRow<row10Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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

		public String errorCode;

		public String getErrorCode() {
			return this.errorCode;
		}

		public Boolean errorCodeIsNullable() {
			return true;
		}

		public Boolean errorCodeIsKey() {
			return false;
		}

		public Integer errorCodeLength() {
			return 255;
		}

		public Integer errorCodePrecision() {
			return 0;
		}

		public String errorCodeDefault() {

			return "";

		}

		public String errorCodeComment() {

			return null;

		}

		public String errorCodePattern() {

			return null;

		}

		public String errorCodeOriginalDbColumnName() {

			return "errorCode";

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

					this.errorCode = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

					this.errorCode = readString(dis);

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

				// String

				writeString(this.errorCode, dos);

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

				// String

				writeString(this.errorCode, dos);

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
			sb.append(",errorCode=" + errorCode);
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

			if (errorCode == null) {
				sb.append("<null>");
			} else {
				sb.append(errorCode);
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

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

	public void tFileInputExcel_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tFileInputExcel_1", "KbxhlW_");

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
				row12Struct row12 = new row12Struct();
				row10Struct row10 = new row10Struct();

				/**
				 * [tLogRow_5 begin ] start
				 */

				sh("tLogRow_5");

				s(currentComponent = "tLogRow_5");

				cLabel = "schema_compliant";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row12");

				int tos_count_tLogRow_5 = 0;

				if (log.isDebugEnabled())
					log.debug("tLogRow_5 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tLogRow_5 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tLogRow_5 = new StringBuilder();
							log4jParamters_tLogRow_5.append("Parameters:");
							log4jParamters_tLogRow_5.append("BASIC_MODE" + " = " + "false");
							log4jParamters_tLogRow_5.append(" | ");
							log4jParamters_tLogRow_5.append("TABLE_PRINT" + " = " + "true");
							log4jParamters_tLogRow_5.append(" | ");
							log4jParamters_tLogRow_5.append("VERTICAL" + " = " + "false");
							log4jParamters_tLogRow_5.append(" | ");
							log4jParamters_tLogRow_5.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
							log4jParamters_tLogRow_5.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tLogRow_5 - " + (log4jParamters_tLogRow_5));
						}
					}
					new BytesLimit65535_tLogRow_5().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tLogRow_5", "schema_compliant", "tLogRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				///////////////////////

				class Util_tLogRow_5 {

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
				Util_tLogRow_5 util_tLogRow_5 = new Util_tLogRow_5();
				util_tLogRow_5.setTableName("schema_compliant");
				util_tLogRow_5.addRow(new String[] { "PRODUCT_LINE_CODE", "calender_month", "PRODUCT_NUMBER",
						"product_desc", "division", "dept", "rnge", "import_flag", "export_flag", "SUPPLIER_NUMBER",
						"supplier_name", "SUPPLIER_REF_NO", "brand_group", "Nation_Sent", "net_desp_units",
						"uk_net_desp_units", "int_net_desp_units", "LocationCode", });
				StringBuilder strBuffer_tLogRow_5 = null;
				int nb_line_tLogRow_5 = 0;
///////////////////////    			

				/**
				 * [tLogRow_5 begin ] stop
				 */

				/**
				 * [tLogRow_3 begin ] start
				 */

				sh("tLogRow_3");

				s(currentComponent = "tLogRow_3");

				cLabel = "schema_rejects";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row10");

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
					talendJobLog.addCM("tLogRow_3", "schema_rejects", "tLogRow");
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

					int[] colLengths = new int[20];

					public void addRow(String[] row) {

						for (int i = 0; i < 20; i++) {
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
						for (k = 0; k < (totals + 19 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 19 - name.length() - k; i++) {
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

						// last column
						for (int i = 0; i < colLengths[19] - fillChars[1].length() + 1; i++) {
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
				util_tLogRow_3.setTableName("schema_rejects");
				util_tLogRow_3.addRow(new String[] { "PRODUCT_LINE_CODE", "calender_month", "PRODUCT_NUMBER",
						"product_desc", "division", "dept", "rnge", "import_flag", "export_flag", "SUPPLIER_NUMBER",
						"supplier_name", "SUPPLIER_REF_NO", "brand_group", "Nation_Sent", "net_desp_units",
						"uk_net_desp_units", "int_net_desp_units", "LocationCode", "errorCode", "errorMessage", });
				StringBuilder strBuffer_tLogRow_3 = null;
				int nb_line_tLogRow_3 = 0;
///////////////////////    			

				/**
				 * [tLogRow_3 begin ] stop
				 */

				/**
				 * [tSchemaComplianceCheck_1 begin ] start
				 */

				sh("tSchemaComplianceCheck_1");

				s(currentComponent = "tSchemaComplianceCheck_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row1");

				int tos_count_tSchemaComplianceCheck_1 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tSchemaComplianceCheck_1", "tSchemaComplianceCheck_1",
							"tSchemaComplianceCheck");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				class RowSetValueUtil_tSchemaComplianceCheck_1 {

					boolean ifPassedThrough = true;
					int errorCodeThrough = 0;
					String errorMessageThrough = "";
					int resultErrorCodeThrough = 0;
					String resultErrorMessageThrough = "";
					String tmpContentThrough = null;

					boolean ifPassed = true;
					int errorCode = 0;
					String errorMessage = "";

					void handleBigdecimalPrecision(String data, int iPrecision, int maxLength) {
						// number of digits before the decimal point(ignoring frontend zeroes)
						int len1 = 0;
						int len2 = 0;
						ifPassed = true;
						errorCode = 0;
						errorMessage = "";
						if (data.startsWith("-")) {
							data = data.substring(1);
						}
						data = org.apache.commons.lang.StringUtils.stripStart(data, "0");

						if (data.indexOf(".") >= 0) {
							len1 = data.indexOf(".");
							data = org.apache.commons.lang.StringUtils.stripEnd(data, "0");
							len2 = data.length() - (len1 + 1);
						} else {
							len1 = data.length();
						}

						if (iPrecision < len2) {
							ifPassed = false;
							errorCode += 8;
							errorMessage += "|precision Non-matches";
						} else if (maxLength < len1 + iPrecision) {
							ifPassed = false;
							errorCode += 8;
							errorMessage += "|invalid Length setting is unsuitable for Precision";
						}
					}

					int handleErrorCode(int errorCode, int resultErrorCode) {
						if (errorCode > 0) {
							if (resultErrorCode > 0) {
								resultErrorCode = 16;
							} else {
								resultErrorCode = errorCode;
							}
						}
						return resultErrorCode;
					}

					String handleErrorMessage(String errorMessage, String resultErrorMessage, String columnLabel) {
						if (errorMessage.length() > 0) {
							if (resultErrorMessage.length() > 0) {
								resultErrorMessage += ";" + errorMessage.replaceFirst("\\|", columnLabel);
							} else {
								resultErrorMessage = errorMessage.replaceFirst("\\|", columnLabel);
							}
						}
						return resultErrorMessage;
					}

					void reset() {
						ifPassedThrough = true;
						errorCodeThrough = 0;
						errorMessageThrough = "";
						resultErrorCodeThrough = 0;
						resultErrorMessageThrough = "";
						tmpContentThrough = null;

						ifPassed = true;
						errorCode = 0;
						errorMessage = "";
					}

					void setRowValue_0(row1Struct row1) {
						// validate nullable (empty as null)
						if ((row1.PRODUCT_LINE_CODE == null) || ("".equals(row1.PRODUCT_LINE_CODE))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						try {
							if (row1.PRODUCT_LINE_CODE != null) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.PRODUCT_LINE_CODE);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"PRODUCT_LINE_CODE:");
						errorMessageThrough = "";
						// validate nullable (empty as null)
						if ((row1.calender_month == null) || ("".equals(row1.calender_month))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						try {
							if (row1.calender_month != null) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.calender_month);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"calender_month:");
						errorMessageThrough = "";
						try {
							if (row1.PRODUCT_NUMBER != null && (!"".equals(row1.PRODUCT_NUMBER))) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.PRODUCT_NUMBER);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"PRODUCT_NUMBER:");
						errorMessageThrough = "";
						// validate nullable (empty as null)
						if ((row1.product_desc == null) || ("".equals(row1.product_desc))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						try {
							if (row1.product_desc != null) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.product_desc);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"product_desc:");
						errorMessageThrough = "";
						// validate nullable (empty as null)
						if ((row1.division == null) || ("".equals(row1.division))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						try {
							if (row1.division != null) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.division);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"division:");
						errorMessageThrough = "";
						// validate nullable (empty as null)
						if ((row1.dept == null) || ("".equals(row1.dept))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						try {
							if (row1.dept != null) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.dept);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"dept:");
						errorMessageThrough = "";
						// validate nullable (empty as null)
						if ((row1.rnge == null) || ("".equals(row1.rnge))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						try {
							if (row1.rnge != null) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.rnge);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"rnge:");
						errorMessageThrough = "";
						// validate nullable (empty as null)
						if ((row1.import_flag == null) || ("".equals(row1.import_flag))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						try {
							if (row1.import_flag != null) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.import_flag);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"import_flag:");
						errorMessageThrough = "";
						// validate nullable (empty as null)
						if ((row1.export_flag == null) || ("".equals(row1.export_flag))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						try {
							if (row1.export_flag != null) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.export_flag);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"export_flag:");
						errorMessageThrough = "";
						// validate nullable (empty as null)
						if ((row1.SUPPLIER_NUMBER == null) || ("".equals(row1.SUPPLIER_NUMBER))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						try {
							if (row1.SUPPLIER_NUMBER != null) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.SUPPLIER_NUMBER);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"SUPPLIER_NUMBER:");
						errorMessageThrough = "";
						// validate nullable (empty as null)
						if ((row1.supplier_name == null) || ("".equals(row1.supplier_name))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						try {
							if (row1.supplier_name != null) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.supplier_name);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"supplier_name:");
						errorMessageThrough = "";
						try {
							if (row1.SUPPLIER_REF_NO != null && (!"".equals(row1.SUPPLIER_REF_NO))) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.SUPPLIER_REF_NO);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"SUPPLIER_REF_NO:");
						errorMessageThrough = "";
						// validate nullable (empty as null)
						if ((row1.brand_group == null) || ("".equals(row1.brand_group))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						try {
							if (row1.brand_group != null) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.brand_group);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"brand_group:");
						errorMessageThrough = "";
						// validate nullable (empty as null)
						if ((row1.Nation_Sent == null) || ("".equals(row1.Nation_Sent))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						try {
							if (row1.Nation_Sent != null) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.Nation_Sent);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"Nation_Sent:");
						errorMessageThrough = "";
						// validate nullable (empty as null)
						if ((row1.net_desp_units == null) || ("".equals(row1.net_desp_units))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"net_desp_units:");
						errorMessageThrough = "";
						// validate nullable (empty as null)
						if ((row1.uk_net_desp_units == null) || ("".equals(row1.uk_net_desp_units))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"uk_net_desp_units:");
						errorMessageThrough = "";
						// validate nullable (empty as null)
						if ((row1.int_net_desp_units == null) || ("".equals(row1.int_net_desp_units))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"int_net_desp_units:");
						errorMessageThrough = "";
						// validate nullable (empty as null)
						if ((row1.LocationCode == null) || ("".equals(row1.LocationCode))) {
							ifPassedThrough = false;
							errorCodeThrough += 4;
							errorMessageThrough += "|empty or null";
						}
						try {
							if (row1.LocationCode != null) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.LocationCode);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"LocationCode:");
						errorMessageThrough = "";
					}
				}
				RowSetValueUtil_tSchemaComplianceCheck_1 rsvUtil_tSchemaComplianceCheck_1 = new RowSetValueUtil_tSchemaComplianceCheck_1();

				/**
				 * [tSchemaComplianceCheck_1 begin ] stop
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
											"enc:routine.encryption.key.v1:2kpm3vmxUXQb9YwWvteLoCpyYIKrV9MetyGDlA==")
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
							log4jParamters_tFileInputExcel_1.append("LIMIT" + " = " + "10");
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
						.decryptPassword("enc:routine.encryption.key.v1:3gEqCubB6/hf8ZrEahEwPfZLiOhwElELF68riQ==");
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
					int limit_tFileInputExcel_1 = 10;

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
							 * [tSchemaComplianceCheck_1 main ] start
							 */

							s(currentComponent = "tSchemaComplianceCheck_1");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row1", "tFileInputExcel_1", "tFileInputExcel_1", "tFileInputExcel",
									"tSchemaComplianceCheck_1", "tSchemaComplianceCheck_1", "tSchemaComplianceCheck"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row1 - " + (row1 == null ? "" : row1.toLogString()));
							}

							row12 = null;
							row10 = null;
							rsvUtil_tSchemaComplianceCheck_1.setRowValue_0(row1);
							if (rsvUtil_tSchemaComplianceCheck_1.ifPassedThrough) {
								row12 = new row12Struct();
								row12.PRODUCT_LINE_CODE = row1.PRODUCT_LINE_CODE;
								row12.calender_month = row1.calender_month;
								row12.PRODUCT_NUMBER = row1.PRODUCT_NUMBER;
								row12.product_desc = row1.product_desc;
								row12.division = row1.division;
								row12.dept = row1.dept;
								row12.rnge = row1.rnge;
								row12.import_flag = row1.import_flag;
								row12.export_flag = row1.export_flag;
								row12.SUPPLIER_NUMBER = row1.SUPPLIER_NUMBER;
								row12.supplier_name = row1.supplier_name;
								row12.SUPPLIER_REF_NO = row1.SUPPLIER_REF_NO;
								row12.brand_group = row1.brand_group;
								row12.Nation_Sent = row1.Nation_Sent;
								row12.net_desp_units = row1.net_desp_units;
								row12.uk_net_desp_units = row1.uk_net_desp_units;
								row12.int_net_desp_units = row1.int_net_desp_units;
								row12.LocationCode = row1.LocationCode;
							}
							if (!rsvUtil_tSchemaComplianceCheck_1.ifPassedThrough) {
								row10 = new row10Struct();
								row10.PRODUCT_LINE_CODE = row1.PRODUCT_LINE_CODE;
								row10.calender_month = row1.calender_month;
								row10.PRODUCT_NUMBER = row1.PRODUCT_NUMBER;
								row10.product_desc = row1.product_desc;
								row10.division = row1.division;
								row10.dept = row1.dept;
								row10.rnge = row1.rnge;
								row10.import_flag = row1.import_flag;
								row10.export_flag = row1.export_flag;
								row10.SUPPLIER_NUMBER = row1.SUPPLIER_NUMBER;
								row10.supplier_name = row1.supplier_name;
								row10.SUPPLIER_REF_NO = row1.SUPPLIER_REF_NO;
								row10.brand_group = row1.brand_group;
								row10.Nation_Sent = row1.Nation_Sent;
								row10.net_desp_units = row1.net_desp_units;
								row10.uk_net_desp_units = row1.uk_net_desp_units;
								row10.int_net_desp_units = row1.int_net_desp_units;
								row10.LocationCode = row1.LocationCode;
								row10.errorCode = String
										.valueOf(rsvUtil_tSchemaComplianceCheck_1.resultErrorCodeThrough);
								row10.errorMessage = rsvUtil_tSchemaComplianceCheck_1.resultErrorMessageThrough;
							}
							rsvUtil_tSchemaComplianceCheck_1.reset();

							tos_count_tSchemaComplianceCheck_1++;

							/**
							 * [tSchemaComplianceCheck_1 main ] stop
							 */

							/**
							 * [tSchemaComplianceCheck_1 process_data_begin ] start
							 */

							s(currentComponent = "tSchemaComplianceCheck_1");

							/**
							 * [tSchemaComplianceCheck_1 process_data_begin ] stop
							 */

// Start of branch "row12"
							if (row12 != null) {

								/**
								 * [tLogRow_5 main ] start
								 */

								s(currentComponent = "tLogRow_5");

								cLabel = "schema_compliant";

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "row12", "tSchemaComplianceCheck_1", "tSchemaComplianceCheck_1",
										"tSchemaComplianceCheck", "tLogRow_5", "schema_compliant", "tLogRow"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("row12 - " + (row12 == null ? "" : row12.toLogString()));
								}

///////////////////////		

								String[] row_tLogRow_5 = new String[18];

								if (row12.PRODUCT_LINE_CODE != null) { //
									row_tLogRow_5[0] = String.valueOf(row12.PRODUCT_LINE_CODE);

								} //

								if (row12.calender_month != null) { //
									row_tLogRow_5[1] = String.valueOf(row12.calender_month);

								} //

								if (row12.PRODUCT_NUMBER != null) { //
									row_tLogRow_5[2] = String.valueOf(row12.PRODUCT_NUMBER);

								} //

								if (row12.product_desc != null) { //
									row_tLogRow_5[3] = String.valueOf(row12.product_desc);

								} //

								if (row12.division != null) { //
									row_tLogRow_5[4] = String.valueOf(row12.division);

								} //

								if (row12.dept != null) { //
									row_tLogRow_5[5] = String.valueOf(row12.dept);

								} //

								if (row12.rnge != null) { //
									row_tLogRow_5[6] = String.valueOf(row12.rnge);

								} //

								if (row12.import_flag != null) { //
									row_tLogRow_5[7] = String.valueOf(row12.import_flag);

								} //

								if (row12.export_flag != null) { //
									row_tLogRow_5[8] = String.valueOf(row12.export_flag);

								} //

								if (row12.SUPPLIER_NUMBER != null) { //
									row_tLogRow_5[9] = String.valueOf(row12.SUPPLIER_NUMBER);

								} //

								if (row12.supplier_name != null) { //
									row_tLogRow_5[10] = String.valueOf(row12.supplier_name);

								} //

								if (row12.SUPPLIER_REF_NO != null) { //
									row_tLogRow_5[11] = String.valueOf(row12.SUPPLIER_REF_NO);

								} //

								if (row12.brand_group != null) { //
									row_tLogRow_5[12] = String.valueOf(row12.brand_group);

								} //

								if (row12.Nation_Sent != null) { //
									row_tLogRow_5[13] = String.valueOf(row12.Nation_Sent);

								} //

								if (row12.net_desp_units != null) { //
									row_tLogRow_5[14] = String.valueOf(row12.net_desp_units);

								} //

								if (row12.uk_net_desp_units != null) { //
									row_tLogRow_5[15] = String.valueOf(row12.uk_net_desp_units);

								} //

								if (row12.int_net_desp_units != null) { //
									row_tLogRow_5[16] = String.valueOf(row12.int_net_desp_units);

								} //

								if (row12.LocationCode != null) { //
									row_tLogRow_5[17] = String.valueOf(row12.LocationCode);

								} //

								util_tLogRow_5.addRow(row_tLogRow_5);
								nb_line_tLogRow_5++;
								log.info("tLogRow_5 - Content of row " + nb_line_tLogRow_5 + ": "
										+ TalendString.unionString("|", row_tLogRow_5));
//////

//////                    

///////////////////////    			

								tos_count_tLogRow_5++;

								/**
								 * [tLogRow_5 main ] stop
								 */

								/**
								 * [tLogRow_5 process_data_begin ] start
								 */

								s(currentComponent = "tLogRow_5");

								cLabel = "schema_compliant";

								/**
								 * [tLogRow_5 process_data_begin ] stop
								 */

								/**
								 * [tLogRow_5 process_data_end ] start
								 */

								s(currentComponent = "tLogRow_5");

								cLabel = "schema_compliant";

								/**
								 * [tLogRow_5 process_data_end ] stop
								 */

							} // End of branch "row12"

// Start of branch "row10"
							if (row10 != null) {

								/**
								 * [tLogRow_3 main ] start
								 */

								s(currentComponent = "tLogRow_3");

								cLabel = "schema_rejects";

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "row10", "tSchemaComplianceCheck_1", "tSchemaComplianceCheck_1",
										"tSchemaComplianceCheck", "tLogRow_3", "schema_rejects", "tLogRow"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("row10 - " + (row10 == null ? "" : row10.toLogString()));
								}

///////////////////////		

								String[] row_tLogRow_3 = new String[20];

								if (row10.PRODUCT_LINE_CODE != null) { //
									row_tLogRow_3[0] = String.valueOf(row10.PRODUCT_LINE_CODE);

								} //

								if (row10.calender_month != null) { //
									row_tLogRow_3[1] = String.valueOf(row10.calender_month);

								} //

								if (row10.PRODUCT_NUMBER != null) { //
									row_tLogRow_3[2] = String.valueOf(row10.PRODUCT_NUMBER);

								} //

								if (row10.product_desc != null) { //
									row_tLogRow_3[3] = String.valueOf(row10.product_desc);

								} //

								if (row10.division != null) { //
									row_tLogRow_3[4] = String.valueOf(row10.division);

								} //

								if (row10.dept != null) { //
									row_tLogRow_3[5] = String.valueOf(row10.dept);

								} //

								if (row10.rnge != null) { //
									row_tLogRow_3[6] = String.valueOf(row10.rnge);

								} //

								if (row10.import_flag != null) { //
									row_tLogRow_3[7] = String.valueOf(row10.import_flag);

								} //

								if (row10.export_flag != null) { //
									row_tLogRow_3[8] = String.valueOf(row10.export_flag);

								} //

								if (row10.SUPPLIER_NUMBER != null) { //
									row_tLogRow_3[9] = String.valueOf(row10.SUPPLIER_NUMBER);

								} //

								if (row10.supplier_name != null) { //
									row_tLogRow_3[10] = String.valueOf(row10.supplier_name);

								} //

								if (row10.SUPPLIER_REF_NO != null) { //
									row_tLogRow_3[11] = String.valueOf(row10.SUPPLIER_REF_NO);

								} //

								if (row10.brand_group != null) { //
									row_tLogRow_3[12] = String.valueOf(row10.brand_group);

								} //

								if (row10.Nation_Sent != null) { //
									row_tLogRow_3[13] = String.valueOf(row10.Nation_Sent);

								} //

								if (row10.net_desp_units != null) { //
									row_tLogRow_3[14] = String.valueOf(row10.net_desp_units);

								} //

								if (row10.uk_net_desp_units != null) { //
									row_tLogRow_3[15] = String.valueOf(row10.uk_net_desp_units);

								} //

								if (row10.int_net_desp_units != null) { //
									row_tLogRow_3[16] = String.valueOf(row10.int_net_desp_units);

								} //

								if (row10.LocationCode != null) { //
									row_tLogRow_3[17] = String.valueOf(row10.LocationCode);

								} //

								if (row10.errorCode != null) { //
									row_tLogRow_3[18] = String.valueOf(row10.errorCode);

								} //

								if (row10.errorMessage != null) { //
									row_tLogRow_3[19] = String.valueOf(row10.errorMessage);

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

								cLabel = "schema_rejects";

								/**
								 * [tLogRow_3 process_data_begin ] stop
								 */

								/**
								 * [tLogRow_3 process_data_end ] start
								 */

								s(currentComponent = "tLogRow_3");

								cLabel = "schema_rejects";

								/**
								 * [tLogRow_3 process_data_end ] stop
								 */

							} // End of branch "row10"

							/**
							 * [tSchemaComplianceCheck_1 process_data_end ] start
							 */

							s(currentComponent = "tSchemaComplianceCheck_1");

							/**
							 * [tSchemaComplianceCheck_1 process_data_end ] stop
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
				 * [tSchemaComplianceCheck_1 end ] start
				 */

				s(currentComponent = "tSchemaComplianceCheck_1");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row1", 2, 0,
						"tFileInputExcel_1", "tFileInputExcel_1", "tFileInputExcel", "tSchemaComplianceCheck_1",
						"tSchemaComplianceCheck_1", "tSchemaComplianceCheck", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tSchemaComplianceCheck_1", true);
				end_Hash.put("tSchemaComplianceCheck_1", System.currentTimeMillis());

				/**
				 * [tSchemaComplianceCheck_1 end ] stop
				 */

				/**
				 * [tLogRow_5 end ] start
				 */

				s(currentComponent = "tLogRow_5");

				cLabel = "schema_compliant";

//////

				java.io.PrintStream consoleOut_tLogRow_5 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_5 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_5 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_5);
				}

				consoleOut_tLogRow_5.println(util_tLogRow_5.format().toString());
				consoleOut_tLogRow_5.flush();
//////
				globalMap.put("tLogRow_5_NB_LINE", nb_line_tLogRow_5);
				if (log.isInfoEnabled())
					log.info("tLogRow_5 - " + ("Printed row count: ") + (nb_line_tLogRow_5) + ("."));

///////////////////////    			

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row12", 2, 0,
						"tSchemaComplianceCheck_1", "tSchemaComplianceCheck_1", "tSchemaComplianceCheck", "tLogRow_5",
						"schema_compliant", "tLogRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_5 - " + ("Done."));

				ok_Hash.put("tLogRow_5", true);
				end_Hash.put("tLogRow_5", System.currentTimeMillis());

				if (((Integer) globalMap.get("tLogRow_5_NB_LINE")) != 0) {

					if (execStat) {
						runStat.updateStatOnConnection("If3", 0, "true");
					}
					tMsgBox_2Process(globalMap);
				}

				else {
					if (execStat) {
						runStat.updateStatOnConnection("If3", 0, "false");
					}
				}

				/**
				 * [tLogRow_5 end ] stop
				 */

				/**
				 * [tLogRow_3 end ] start
				 */

				s(currentComponent = "tLogRow_3");

				cLabel = "schema_rejects";

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

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row10", 2, 0,
						"tSchemaComplianceCheck_1", "tSchemaComplianceCheck_1", "tSchemaComplianceCheck", "tLogRow_3",
						"schema_rejects", "tLogRow", "reject")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_3 - " + ("Done."));

				ok_Hash.put("tLogRow_3", true);
				end_Hash.put("tLogRow_3", System.currentTimeMillis());

				if (((Integer) globalMap.get("tLogRow_3_NB_LINE")) != 0) {

					if (execStat) {
						runStat.updateStatOnConnection("If1", 0, "true");
					}
					tMsgBox_1Process(globalMap);
				}

				else {
					if (execStat) {
						runStat.updateStatOnConnection("If1", 0, "false");
					}
				}

				/**
				 * [tLogRow_3 end ] stop
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
				 * [tSchemaComplianceCheck_1 finally ] start
				 */

				s(currentComponent = "tSchemaComplianceCheck_1");

				/**
				 * [tSchemaComplianceCheck_1 finally ] stop
				 */

				/**
				 * [tLogRow_5 finally ] start
				 */

				s(currentComponent = "tLogRow_5");

				cLabel = "schema_compliant";

				/**
				 * [tLogRow_5 finally ] stop
				 */

				/**
				 * [tLogRow_3 finally ] start
				 */

				s(currentComponent = "tLogRow_3");

				cLabel = "schema_rejects";

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

		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 1);
	}

	public void tMsgBox_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tMsgBox_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tMsgBox_2", "c2Vwql_");

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
				 * [tMsgBox_2 begin ] start
				 */

				sh("tMsgBox_2");

				s(currentComponent = "tMsgBox_2");

				int tos_count_tMsgBox_2 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tMsgBox_2", "tMsgBox_2", "tMsgBox");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				/**
				 * [tMsgBox_2 begin ] stop
				 */

				/**
				 * [tMsgBox_2 main ] start
				 */

				s(currentComponent = "tMsgBox_2");

				int messageIcontMsgBox_2 = javax.swing.JOptionPane.INFORMATION_MESSAGE;
				String titletMsgBox_2 = "Talend Studio";
				String messagetMsgBox_2 = "Schema validated succesfully";
				String resulttMsgBox_2 = null;

				javax.swing.JOptionPane.showMessageDialog(null, messagetMsgBox_2, titletMsgBox_2, messageIcontMsgBox_2);
				resulttMsgBox_2 = String.valueOf(1);

				globalMap.put("tMsgBox_2_RESULT", resulttMsgBox_2);

				tos_count_tMsgBox_2++;

				/**
				 * [tMsgBox_2 main ] stop
				 */

				/**
				 * [tMsgBox_2 process_data_begin ] start
				 */

				s(currentComponent = "tMsgBox_2");

				/**
				 * [tMsgBox_2 process_data_begin ] stop
				 */

				/**
				 * [tMsgBox_2 process_data_end ] start
				 */

				s(currentComponent = "tMsgBox_2");

				/**
				 * [tMsgBox_2 process_data_end ] stop
				 */

				/**
				 * [tMsgBox_2 end ] start
				 */

				s(currentComponent = "tMsgBox_2");

				ok_Hash.put("tMsgBox_2", true);
				end_Hash.put("tMsgBox_2", System.currentTimeMillis());

				if (((Integer) globalMap.get("tLogRow_5_NB_LINE")) != 0) {

					if (execStat) {
						runStat.updateStatOnConnection("If2", 0, "true");
					}
					tFileInputExcel_2Process(globalMap);
				}

				else {
					if (execStat) {
						runStat.updateStatOnConnection("If2", 0, "false");
					}
				}

				/**
				 * [tMsgBox_2 end ] stop
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
				 * [tMsgBox_2 finally ] start
				 */

				s(currentComponent = "tMsgBox_2");

				/**
				 * [tMsgBox_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tMsgBox_2_SUBPROCESS_STATE", 1);
	}

	public static class row14Struct implements routines.system.IPersistableRow<row14Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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
		public int compareTo(row14Struct other) {

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
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

	public static class row9Struct implements routines.system.IPersistableRow<row9Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

					this.Audit_BatchName = readString(dis);

					this.Audit_BatchName_Description = readString(dis);

					this.Audit_BatchNo = readString(dis);

					this.Audit_BatchTimeExecution = readString(dis);

					this.brand_indicator = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

					this.Audit_BatchName = readString(dis);

					this.Audit_BatchName_Description = readString(dis);

					this.Audit_BatchNo = readString(dis);

					this.Audit_BatchTimeExecution = readString(dis);

					this.brand_indicator = readString(dis);

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
			sb.append(",ExportFlag=" + ExportFlag);
			sb.append(",SalesQuantity=" + String.valueOf(SalesQuantity));
			sb.append(",Audit_BatchName=" + Audit_BatchName);
			sb.append(",Audit_BatchName_Description=" + Audit_BatchName_Description);
			sb.append(",Audit_BatchNo=" + Audit_BatchNo);
			sb.append(",Audit_BatchTimeExecution=" + Audit_BatchTimeExecution);
			sb.append(",brand_indicator=" + brand_indicator);
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

	public static class out4Struct implements routines.system.IPersistableRow<out4Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

	public static class row13Struct implements routines.system.IPersistableRow<row13Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

					this.ExportFlag = readString(dis);

					this.SalesQuantity = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

	public void tFileInputExcel_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tFileInputExcel_2", "R0EA11_");

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

				row13Struct row13 = new row13Struct();
				out1Struct out1 = new out1Struct();
				row2Struct row2 = new row2Struct();
				row4Struct row4 = new row4Struct();
				out2Struct out2 = new out2Struct();
				out3Struct out3 = new out3Struct();
				row6Struct row6 = new row6Struct();
				row6Struct row14 = row6;
				row3Struct row3 = new row3Struct();

				row5Struct row5 = new row5Struct();

				row7Struct row7 = new row7Struct();
				out4Struct out4 = new out4Struct();
				row8Struct row8 = new row8Struct();
				row9Struct row9 = new row9Struct();

				/**
				 * [tFileOutputDelimited_1 begin ] start
				 */

				sh("tFileOutputDelimited_1");

				s(currentComponent = "tFileOutputDelimited_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row8");

				int tos_count_tFileOutputDelimited_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileOutputDelimited_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileOutputDelimited_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileOutputDelimited_1 = new StringBuilder();
							log4jParamters_tFileOutputDelimited_1.append("Parameters:");
							log4jParamters_tFileOutputDelimited_1.append("USESTREAM" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("FILENAME" + " = "
									+ "\"C:/Users/valpakuser/Desktop/Outputs/JDWilliams Q1 2024 To Load Demo_dynamic_rename_source.csv\"");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("FIELDSEPARATOR" + " = " + "\";\"");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("APPEND" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("INCLUDEHEADER" + " = " + "true");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("COMPRESS" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("CREATE" + " = " + "true");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("SPLIT" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("FLUSHONROW" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("ROW_MODE" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("DELETE_EMPTYFILE" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("FILE_EXIST_EXCEPTION" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileOutputDelimited_1 - " + (log4jParamters_tFileOutputDelimited_1));
						}
					}
					new BytesLimit65535_tFileOutputDelimited_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileOutputDelimited_1", "tFileOutputDelimited_1", "tFileOutputDelimited");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				String fileName_tFileOutputDelimited_1 = "";
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						"C:/Users/valpakuser/Desktop/Outputs/JDWilliams Q1 2024 To Load Demo_dynamic_rename_source.csv"))
						.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_1 = null;
				String extension_tFileOutputDelimited_1 = null;
				String directory_tFileOutputDelimited_1 = null;
				if ((fileName_tFileOutputDelimited_1.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") < fileName_tFileOutputDelimited_1
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
							fileName_tFileOutputDelimited_1.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					}
					directory_tFileOutputDelimited_1 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_1 = true;
				java.io.File filetFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);
				int nb_line_tFileOutputDelimited_1 = 0;
				int splitedFileNo_tFileOutputDelimited_1 = 0;
				int currentRow_tFileOutputDelimited_1 = 0;

				final String OUT_DELIM_tFileOutputDelimited_1 = /** Start field tFileOutputDelimited_1:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
																		 * Start field
																		 * tFileOutputDelimited_1:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_1 != null && directory_tFileOutputDelimited_1.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_1 = new java.io.File(directory_tFileOutputDelimited_1);
					if (!dir_tFileOutputDelimited_1.exists()) {
						log.info("tFileOutputDelimited_1 - Creating directory '"
								+ dir_tFileOutputDelimited_1.getCanonicalPath() + "'.");
						dir_tFileOutputDelimited_1.mkdirs();
						log.info("tFileOutputDelimited_1 - The directory '"
								+ dir_tFileOutputDelimited_1.getCanonicalPath() + "' has been created successfully.");
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_1 = null;

				java.io.File fileToDelete_tFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				if (fileToDelete_tFileOutputDelimited_1.exists()) {
					fileToDelete_tFileOutputDelimited_1.delete();
				}
				outtFileOutputDelimited_1 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, false), "ISO-8859-15"));
				resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
				if (filetFileOutputDelimited_1.length() == 0) {
					outtFileOutputDelimited_1.write("PRODUCT_LINE_CODE");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("calender_month");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("PRODUCT_NUMBER");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("product_desc");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("division");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("dept");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("rnge");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("import_flag");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("export_flag");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("SUPPLIER_NUMBER");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("supplier_name");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("SUPPLIER_REF_NO");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("brand_group");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Nation_Sent");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("net_desp_units");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("uk_net_desp_units");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("int_net_desp_units");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("LocationCode");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("ExportFlag");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("SalesQuantity");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Audit_BatchName");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Audit_BatchName_Description");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Audit_BatchNo");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Audit_BatchTimeExecution");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("brand_indicator");
					outtFileOutputDelimited_1.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.flush();
				}

				resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				sh("tLogRow_1");

				s(currentComponent = "tLogRow_1");

				cLabel = "mandatory_column_failures";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row9");

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
					talendJobLog.addCM("tLogRow_1", "mandatory_column_failures", "tLogRow");
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

					int[] colLengths = new int[26];

					public void addRow(String[] row) {

						for (int i = 0; i < 26; i++) {
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
						for (k = 0; k < (totals + 25 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 25 - name.length() - k; i++) {
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

							sbformat.append("|%26$-");
							sbformat.append(colLengths[25]);
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
						for (int i = 0; i < colLengths[24] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[25] - fillChars[1].length() + 1; i++) {
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
				util_tLogRow_1.setTableName("mandatory_column_failures");
				util_tLogRow_1.addRow(new String[] { "PRODUCT_LINE_CODE", "calender_month", "PRODUCT_NUMBER",
						"product_desc", "division", "dept", "rnge", "import_flag", "export_flag", "SUPPLIER_NUMBER",
						"supplier_name", "SUPPLIER_REF_NO", "brand_group", "Nation_Sent", "net_desp_units",
						"uk_net_desp_units", "int_net_desp_units", "LocationCode", "ExportFlag", "SalesQuantity",
						"Audit_BatchName", "Audit_BatchName_Description", "Audit_BatchNo", "Audit_BatchTimeExecution",
						"brand_indicator", "errorMessage", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
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
									+ "// code sample : use input_row to define the condition. // input_row.columnName1.equals(\"foo\") ||!(input_row.columnName2.equals(\"bar\")) // replace the following expression by your own filter condition  !Relational.ISNULL(input_row.PRODUCT_NUMBER)&&!Relational.ISNULL(input_row.product_desc)&&!Relational.ISNULL(input_row.LocationCode)");
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
				String hashKey_tHashOutput_1 = "tHashFile_JDWilliams_expanded_" + pid + "_tHashOutput_1";
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
				 * [tDataStewardshipTaskOutput_1 begin ] start
				 */

				sh("tDataStewardshipTaskOutput_1");

				s(currentComponent = "tDataStewardshipTaskOutput_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row14");

				int tos_count_tDataStewardshipTaskOutput_1 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tDataStewardshipTaskOutput_1", "tDataStewardshipTaskOutput_1",
							"tDataStewardshipTaskOutput");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				boolean doesNodeBelongToRequest_tDataStewardshipTaskOutput_1 = 0 == 0;
				@SuppressWarnings("unchecked")
				java.util.Map<String, Object> restRequest_tDataStewardshipTaskOutput_1 = (java.util.Map<String, Object>) globalMap
						.get("restRequest");
				String currentTRestRequestOperation_tDataStewardshipTaskOutput_1 = (String) (restRequest_tDataStewardshipTaskOutput_1 != null
						? restRequest_tDataStewardshipTaskOutput_1.get("OPERATION")
						: null);

				org.talend.components.api.component.ComponentDefinition def_tDataStewardshipTaskOutput_1 = new org.talend.components.datastewardship.tdatastewardshiptaskoutput.TDataStewardshipTaskOutputDefinition();

				org.talend.components.api.component.runtime.Writer writer_tDataStewardshipTaskOutput_1 = null;
				org.talend.components.api.component.runtime.Reader reader_tDataStewardshipTaskOutput_1 = null;

				org.talend.components.datastewardship.tdatastewardshiptaskoutput.TDataStewardshipTaskOutputProperties props_tDataStewardshipTaskOutput_1 = (org.talend.components.datastewardship.tdatastewardshiptaskoutput.TDataStewardshipTaskOutputProperties) def_tDataStewardshipTaskOutput_1
						.createRuntimeProperties();
				props_tDataStewardshipTaskOutput_1.setValue("batchSize", 50);

				props_tDataStewardshipTaskOutput_1.setValue("campaignName", "cfc9ce7bd173a6c71f4e669a4a8b3a632");

				props_tDataStewardshipTaskOutput_1.setValue("campaignLabel", "JDW");

				props_tDataStewardshipTaskOutput_1.setValue("campaignType",
						org.talend.components.datastewardship.common.CampaignType.RESOLUTION);

				props_tDataStewardshipTaskOutput_1.setValue("laxSchema", true);

				props_tDataStewardshipTaskOutput_1.setValue("taskPriority", "4");

				props_tDataStewardshipTaskOutput_1.setValue("taskTags", "");

				props_tDataStewardshipTaskOutput_1.setValue("taskState", "New");

				props_tDataStewardshipTaskOutput_1.setValue("taskAssignee", "No Assignee");

				java.util.List<Object> tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName = new java.util.ArrayList<Object>();

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("PRODUCT_NUMBER");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("product_desc");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("SUPPLIER_NUMBER");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("PRODUCT_LINE_CODE");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("calender_month");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("Product_Code");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("Product_Name");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("division");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("dept");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("rnge");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("import_flag");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("export_flag");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("supplier_code");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("supplier_name");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("SUPPLIER_REF_NO");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("brand_group");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("Nation_Sent");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("net_desp_units");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("uk_net_desp_units");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("int_net_desp_units");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName.add("LocationCode");

				((org.talend.daikon.properties.Properties) props_tDataStewardshipTaskOutput_1.taskCommentsTable)
						.setValue("fieldName", tDataStewardshipTaskOutput_1_taskCommentsTable_fieldName);

				java.util.List<Object> tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment = new java.util.ArrayList<Object>();

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment.add("");

				((org.talend.daikon.properties.Properties) props_tDataStewardshipTaskOutput_1.taskCommentsTable)
						.setValue("fieldComment", tDataStewardshipTaskOutput_1_taskCommentsTable_fieldComment);

				java.util.List<Object> tDataStewardshipTaskOutput_1_httpConfig_keys = new java.util.ArrayList<Object>();

				((org.talend.daikon.properties.Properties) props_tDataStewardshipTaskOutput_1.httpConfig)
						.setValue("keys", tDataStewardshipTaskOutput_1_httpConfig_keys);

				java.util.List<Object> tDataStewardshipTaskOutput_1_httpConfig_values = new java.util.ArrayList<Object>();

				((org.talend.daikon.properties.Properties) props_tDataStewardshipTaskOutput_1.httpConfig)
						.setValue("values", tDataStewardshipTaskOutput_1_httpConfig_values);

				class SchemaSettingTool_tDataStewardshipTaskOutput_1_1_fisrt {

					String getSchemaValue() {

						StringBuilder s = new StringBuilder();

						a("{\"type\":\"record\",", s);

						a("\"name\":\"main\",\"fields\":[{", s);

						a("\"name\":\"PRODUCT_LINE_CODE\",\"type\":\"string\",\"di.table.comment\":\"text\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"PRODUCT_LINE_CODE\"},{",
								s);

						a("\"name\":\"calender_month\",\"type\":\"string\",\"di.table.comment\":\"text\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"calender_month\"},{",
								s);

						a("\"name\":\"PRODUCT_NUMBER\",\"type\":\"string\",\"di.table.comment\":\"text\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"PRODUCT_NUMBER\"},{",
								s);

						a("\"name\":\"product_desc\",\"type\":\"string\",\"di.table.comment\":\"text\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"product_desc\"},{",
								s);

						a("\"name\":\"division\",\"type\":\"string\",\"di.table.comment\":\"text\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"division\"},{",
								s);

						a("\"name\":\"dept\",\"type\":\"string\",\"di.table.comment\":\"text\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"dept\"},{",
								s);

						a("\"name\":\"rnge\",\"type\":\"string\",\"di.table.comment\":\"text\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"rnge\"},{",
								s);

						a("\"name\":\"import_flag\",\"type\":\"string\",\"di.table.comment\":\"text\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"import_flag\"},{",
								s);

						a("\"name\":\"export_flag\",\"type\":\"string\",\"di.table.comment\":\"text\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"export_flag\"},{",
								s);

						a("\"name\":\"SUPPLIER_NUMBER\",\"type\":\"string\",\"di.table.comment\":\"text\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"SUPPLIER_NUMBER\"},{",
								s);

						a("\"name\":\"supplier_name\",\"type\":\"string\",\"di.table.comment\":\"text\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"supplier_name\"},{",
								s);

						a("\"name\":\"SUPPLIER_REF_NO\",\"type\":\"string\",\"di.table.comment\":\"text\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"SUPPLIER_REF_NO\"},{",
								s);

						a("\"name\":\"brand_group\",\"type\":\"string\",\"di.table.comment\":\"text\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"brand_group\"},{",
								s);

						a("\"name\":\"Nation_Sent\",\"type\":\"string\",\"di.table.comment\":\"text\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"Nation_Sent\"},{",
								s);

						a("\"name\":\"net_desp_units\",\"type\":\"string\",\"di.table.comment\":\"integer\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"net_desp_units\"},{",
								s);

						a("\"name\":\"uk_net_desp_units\",\"type\":\"string\",\"di.table.comment\":\"integer\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"uk_net_desp_units\"},{",
								s);

						a("\"name\":\"int_net_desp_units\",\"type\":\"string\",\"di.table.comment\":\"integer\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"int_net_desp_units\"},{",
								s);

						a("\"name\":\"LocationCode\",\"type\":\"string\",\"di.table.comment\":\"text\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"talend.field.dbColumnName\":\"LocationCode\"},{",
								s);

						a("\"name\":\"TDS_DUE_DATE\",\"type\":\"long\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"di.column.isNullable\":\"true\",\"talend.field.dbColumnName\":\"TDS_DUE_DATE\"},{",
								s);

						a("\"name\":\"TDS_EXTERNAL_ID\",\"type\":\"string\",\"talend.isLocked\":\"true\",\"talend.field.generated\":\"true\",\"di.column.isNullable\":\"true\",\"talend.field.dbColumnName\":\"TDS_EXTERNAL_ID\"}]}",
								s);

						return s.toString();

					}

					void a(String part, StringBuilder strB) {
						strB.append(part);
					}

				}

				SchemaSettingTool_tDataStewardshipTaskOutput_1_1_fisrt sst_tDataStewardshipTaskOutput_1_1_fisrt = new SchemaSettingTool_tDataStewardshipTaskOutput_1_1_fisrt();

				props_tDataStewardshipTaskOutput_1.schema.setValue("schema", new org.apache.avro.Schema.Parser()
						.setValidateDefaults(false).parse(sst_tDataStewardshipTaskOutput_1_1_fisrt.getSchemaValue()));

				props_tDataStewardshipTaskOutput_1.connection.setValue("url",
						"https://tds.eu.cloud.talend.com/data-stewardship/");

				props_tDataStewardshipTaskOutput_1.connection.setValue("username", "token");

				props_tDataStewardshipTaskOutput_1.connection.setValue("password",
						routines.system.PasswordEncryptUtil.decryptPassword(
								"enc:routine.encryption.key.v1:NH8ZQFVNTgTpHs2GVZviOlZh03Wn6SwSLsicvU6hmi9WwgFSuQ9kjtFN3lSFff3hehOJIuOsTjccTnVn/UO0cSue5SFibRjkyd17uH/dzdkyZusYSiMPTEitKFo="));

				props_tDataStewardshipTaskOutput_1.connection.refConnection.setValue("referenceDefinitionName",
						"datastewardship");

				if (org.talend.components.api.properties.ComponentReferenceProperties.ReferenceType.COMPONENT_INSTANCE == props_tDataStewardshipTaskOutput_1.connection.refConnection.referenceType
						.getValue()) {
					final String referencedComponentInstanceId_tDataStewardshipTaskOutput_1 = props_tDataStewardshipTaskOutput_1.connection.refConnection.componentInstanceId
							.getStringValue();
					if (referencedComponentInstanceId_tDataStewardshipTaskOutput_1 != null) {
						org.talend.daikon.properties.Properties referencedComponentProperties_tDataStewardshipTaskOutput_1 = (org.talend.daikon.properties.Properties) globalMap
								.get(referencedComponentInstanceId_tDataStewardshipTaskOutput_1
										+ "_COMPONENT_RUNTIME_PROPERTIES");
						props_tDataStewardshipTaskOutput_1.connection.refConnection
								.setReference(referencedComponentProperties_tDataStewardshipTaskOutput_1);
					}
				}
				globalMap.put("tDataStewardshipTaskOutput_1_COMPONENT_RUNTIME_PROPERTIES",
						props_tDataStewardshipTaskOutput_1);
				globalMap.putIfAbsent("TALEND_PRODUCT_VERSION", "8.0");
				globalMap.put("TALEND_COMPONENTS_VERSION", "0.37.39");
				java.net.URL mappings_url_tDataStewardshipTaskOutput_1 = this.getClass().getResource("/xmlMappings");
				globalMap.put("tDataStewardshipTaskOutput_1_MAPPINGS_URL", mappings_url_tDataStewardshipTaskOutput_1);

				org.talend.components.api.container.RuntimeContainer container_tDataStewardshipTaskOutput_1 = new org.talend.components.api.container.RuntimeContainer() {
					public Object getComponentData(String componentId, String key) {
						return globalMap.get(componentId + "_" + key);
					}

					public void setComponentData(String componentId, String key, Object data) {
						globalMap.put(componentId + "_" + key, data);
					}

					public String getCurrentComponentId() {
						return "tDataStewardshipTaskOutput_1";
					}

					public Object getGlobalData(String key) {
						return globalMap.get(key);
					}
				};

				int nb_line_tDataStewardshipTaskOutput_1 = 0;

				org.talend.components.api.component.ConnectorTopology topology_tDataStewardshipTaskOutput_1 = null;
				topology_tDataStewardshipTaskOutput_1 = org.talend.components.api.component.ConnectorTopology.INCOMING;

				org.talend.daikon.runtime.RuntimeInfo runtime_info_tDataStewardshipTaskOutput_1 = def_tDataStewardshipTaskOutput_1
						.getRuntimeInfo(org.talend.components.api.component.runtime.ExecutionEngine.DI,
								props_tDataStewardshipTaskOutput_1, topology_tDataStewardshipTaskOutput_1);
				java.util.Set<org.talend.components.api.component.ConnectorTopology> supported_connector_topologies_tDataStewardshipTaskOutput_1 = def_tDataStewardshipTaskOutput_1
						.getSupportedConnectorTopologies();

				org.talend.components.api.component.runtime.RuntimableRuntime componentRuntime_tDataStewardshipTaskOutput_1 = (org.talend.components.api.component.runtime.RuntimableRuntime) (Class
						.forName(runtime_info_tDataStewardshipTaskOutput_1.getRuntimeClassName()).newInstance());
				org.talend.daikon.properties.ValidationResult initVr_tDataStewardshipTaskOutput_1 = componentRuntime_tDataStewardshipTaskOutput_1
						.initialize(container_tDataStewardshipTaskOutput_1, props_tDataStewardshipTaskOutput_1);

				if (initVr_tDataStewardshipTaskOutput_1
						.getStatus() == org.talend.daikon.properties.ValidationResult.Result.ERROR) {
					throw new RuntimeException(initVr_tDataStewardshipTaskOutput_1.getMessage());
				}

				if (componentRuntime_tDataStewardshipTaskOutput_1 instanceof org.talend.components.api.component.runtime.ComponentDriverInitialization) {
					org.talend.components.api.component.runtime.ComponentDriverInitialization compDriverInitialization_tDataStewardshipTaskOutput_1 = (org.talend.components.api.component.runtime.ComponentDriverInitialization) componentRuntime_tDataStewardshipTaskOutput_1;
					compDriverInitialization_tDataStewardshipTaskOutput_1
							.runAtDriver(container_tDataStewardshipTaskOutput_1);
				}

				org.talend.components.api.component.runtime.SourceOrSink sourceOrSink_tDataStewardshipTaskOutput_1 = null;
				if (componentRuntime_tDataStewardshipTaskOutput_1 instanceof org.talend.components.api.component.runtime.SourceOrSink) {
					sourceOrSink_tDataStewardshipTaskOutput_1 = (org.talend.components.api.component.runtime.SourceOrSink) componentRuntime_tDataStewardshipTaskOutput_1;
					if (doesNodeBelongToRequest_tDataStewardshipTaskOutput_1) {
						org.talend.daikon.properties.ValidationResult vr_tDataStewardshipTaskOutput_1 = sourceOrSink_tDataStewardshipTaskOutput_1
								.validate(container_tDataStewardshipTaskOutput_1);
						if (vr_tDataStewardshipTaskOutput_1
								.getStatus() == org.talend.daikon.properties.ValidationResult.Result.ERROR) {
							throw new RuntimeException(vr_tDataStewardshipTaskOutput_1.getMessage());
						}
					}
				}

				org.talend.codegen.enforcer.IncomingSchemaEnforcer incomingEnforcer_tDataStewardshipTaskOutput_1 = null;
				if (sourceOrSink_tDataStewardshipTaskOutput_1 instanceof org.talend.components.api.component.runtime.Sink) {
					org.talend.components.api.component.runtime.Sink sink_tDataStewardshipTaskOutput_1 = (org.talend.components.api.component.runtime.Sink) sourceOrSink_tDataStewardshipTaskOutput_1;
					org.talend.components.api.component.runtime.WriteOperation writeOperation_tDataStewardshipTaskOutput_1 = sink_tDataStewardshipTaskOutput_1
							.createWriteOperation();
					if (doesNodeBelongToRequest_tDataStewardshipTaskOutput_1) {
						writeOperation_tDataStewardshipTaskOutput_1.initialize(container_tDataStewardshipTaskOutput_1);
					}
					writer_tDataStewardshipTaskOutput_1 = writeOperation_tDataStewardshipTaskOutput_1
							.createWriter(container_tDataStewardshipTaskOutput_1);
					if (doesNodeBelongToRequest_tDataStewardshipTaskOutput_1) {
						writer_tDataStewardshipTaskOutput_1.open("tDataStewardshipTaskOutput_1");
					}

					resourceMap.put("writer_tDataStewardshipTaskOutput_1", writer_tDataStewardshipTaskOutput_1);
				} // end of "sourceOrSink_tDataStewardshipTaskOutput_1 instanceof ...Sink"
				org.talend.components.api.component.Connector c_tDataStewardshipTaskOutput_1 = null;
				for (org.talend.components.api.component.Connector currentConnector : props_tDataStewardshipTaskOutput_1
						.getAvailableConnectors(null, false)) {
					if (currentConnector.getName().equals("MAIN")) {
						c_tDataStewardshipTaskOutput_1 = currentConnector;
						break;
					}
				}
				org.apache.avro.Schema designSchema_tDataStewardshipTaskOutput_1 = props_tDataStewardshipTaskOutput_1
						.getSchema(c_tDataStewardshipTaskOutput_1, false);
				incomingEnforcer_tDataStewardshipTaskOutput_1 = new org.talend.codegen.enforcer.IncomingSchemaEnforcer(
						designSchema_tDataStewardshipTaskOutput_1);

				java.lang.Iterable<?> outgoingMainRecordsList_tDataStewardshipTaskOutput_1 = new java.util.ArrayList<Object>();
				java.util.Iterator outgoingMainRecordsIt_tDataStewardshipTaskOutput_1 = null;

				/**
				 * [tDataStewardshipTaskOutput_1 begin ] stop
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
				util_tLogRow_4.addRow(new String[] { "PRODUCT_LINE_CODE", "calender_month", "PRODUCT_NUMBER",
						"product_desc", "division", "dept", "rnge", "import_flag", "export_flag", "SUPPLIER_NUMBER",
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
									+ ", KEY_ATTRIBUTE=" + ("true") + ", SCHEMA_COLUMN=" + ("PRODUCT_NUMBER")
									+ "}, {CASE_SENSITIVE=" + ("true") + ", KEY_ATTRIBUTE=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("product_desc") + "}, {CASE_SENSITIVE=" + ("true")
									+ ", KEY_ATTRIBUTE=" + ("true") + ", SCHEMA_COLUMN=" + ("division")
									+ "}, {CASE_SENSITIVE=" + ("true") + ", KEY_ATTRIBUTE=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("dept") + "}, {CASE_SENSITIVE=" + ("true")
									+ ", KEY_ATTRIBUTE=" + ("true") + ", SCHEMA_COLUMN=" + ("rnge")
									+ "}, {CASE_SENSITIVE=" + ("true") + ", KEY_ATTRIBUTE=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("import_flag") + "}, {CASE_SENSITIVE=" + ("true")
									+ ", KEY_ATTRIBUTE=" + ("true") + ", SCHEMA_COLUMN=" + ("export_flag")
									+ "}, {CASE_SENSITIVE=" + ("true") + ", KEY_ATTRIBUTE=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("SUPPLIER_NUMBER") + "}, {CASE_SENSITIVE=" + ("true")
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
					String PRODUCT_NUMBER;
					String product_desc;
					String division;
					String dept;
					String rnge;
					String import_flag;
					String export_flag;
					String SUPPLIER_NUMBER;
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

							result = prime * result
									+ ((this.PRODUCT_NUMBER == null) ? 0 : this.PRODUCT_NUMBER.hashCode());

							result = prime * result + ((this.product_desc == null) ? 0 : this.product_desc.hashCode());

							result = prime * result + ((this.division == null) ? 0 : this.division.hashCode());

							result = prime * result + ((this.dept == null) ? 0 : this.dept.hashCode());

							result = prime * result + ((this.rnge == null) ? 0 : this.rnge.hashCode());

							result = prime * result + ((this.import_flag == null) ? 0 : this.import_flag.hashCode());

							result = prime * result + ((this.export_flag == null) ? 0 : this.export_flag.hashCode());

							result = prime * result
									+ ((this.SUPPLIER_NUMBER == null) ? 0 : this.SUPPLIER_NUMBER.hashCode());

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

						if (this.PRODUCT_NUMBER == null) {
							if (other.PRODUCT_NUMBER != null)
								return false;

						} else if (!this.PRODUCT_NUMBER.equals(other.PRODUCT_NUMBER))

							return false;

						if (this.product_desc == null) {
							if (other.product_desc != null)
								return false;

						} else if (!this.product_desc.equals(other.product_desc))

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

						if (this.SUPPLIER_NUMBER == null) {
							if (other.SUPPLIER_NUMBER != null)
								return false;

						} else if (!this.SUPPLIER_NUMBER.equals(other.SUPPLIER_NUMBER))

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

				cLabel = "sales_number_rejects";

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
					talendJobLog.addCM("tLogRow_2", "sales_number_rejects", "tLogRow");
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
				util_tLogRow_2.setTableName("sales_number_rejects");
				util_tLogRow_2.addRow(new String[] { "PRODUCT_LINE_CODE", "calender_month", "PRODUCT_NUMBER",
						"product_desc", "division", "dept", "rnge", "import_flag", "export_flag", "SUPPLIER_NUMBER",
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

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row13");

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
				int count_row13_tMap_1 = 0;

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
									+ "\"C:/Users/valpakuser/Desktop/ValPak PoC Data/JDWilliams Q1 2024 To Load Demo - source.xlsx\"");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("PASSWORD" + " = "
									+ String.valueOf(
											"enc:routine.encryption.key.v1:xbe+ds79nHSqlAxG8oTFc47uY5pflNI8q9q/jQ==")
											.substring(0, 4)
									+ "...");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("ALL_SHEETS" + " = " + "false");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append(
									"SHEETLIST" + " = " + "[{USE_REGEX=" + ("false") + ", SHEETNAME=" + ("1") + "}]");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("HEADER" + " = " + "1");
							log4jParamters_tFileInputExcel_2.append(" | ");
							log4jParamters_tFileInputExcel_2.append("LIMIT" + " = " + "100");
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
						.decryptPassword("enc:routine.encryption.key.v1:R4tFtisz/QfBO9cg0UbQmJlECpG2B47BOVgWcQ==");
				String password_tFileInputExcel_2 = decryptedPassword_tFileInputExcel_2;
				if (password_tFileInputExcel_2.isEmpty()) {
					password_tFileInputExcel_2 = null;
				}
				Object source_tFileInputExcel_2 = "C:/Users/valpakuser/Desktop/ValPak PoC Data/JDWilliams Q1 2024 To Load Demo - source.xlsx";
				com.talend.excel.xssf.event.ExcelReader excelReader_tFileInputExcel_2 = null;

				if (source_tFileInputExcel_2 instanceof java.io.InputStream
						|| source_tFileInputExcel_2 instanceof String) {
					excelReader_tFileInputExcel_2 = new com.talend.excel.xssf.event.ExcelReader();
					excelReader_tFileInputExcel_2.setIncludePhoneticRuns(true);
				} else {
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}

				try {
					excelReader_tFileInputExcel_2.addSheetName(1, false);
					int start_column_tFileInputExcel_2 = 1 - 1;
					int end_column_tFileInputExcel_2 = -1;
					if (start_column_tFileInputExcel_2 >= 0) {// follow start column

						end_column_tFileInputExcel_2 = start_column_tFileInputExcel_2 + 18 - 1;

					} else if (end_column_tFileInputExcel_2 >= 0) {// follow end column
						start_column_tFileInputExcel_2 = end_column_tFileInputExcel_2 - 18 + 1;
					}

					if (end_column_tFileInputExcel_2 < 0 || start_column_tFileInputExcel_2 < 0) {
						throw new RuntimeException("Error start column and end column.");
					}
					int actual_end_column_tFileInputExcel_2 = end_column_tFileInputExcel_2;

					int header_tFileInputExcel_2 = 1;
					int limit_tFileInputExcel_2 = 100;

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
						row13 = null;
						int tempRowLength_tFileInputExcel_2 = 18;

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
						row13 = new row13Struct();
						int curColNum_tFileInputExcel_2 = -1;
						String curColName_tFileInputExcel_2 = "";

						try {
							columnIndex_tFileInputExcel_2 = 0;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "PRODUCT_LINE_CODE";

								row13.PRODUCT_LINE_CODE = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row13.PRODUCT_LINE_CODE = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 1;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "calender_month";

								row13.calender_month = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row13.calender_month = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 2;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "PRODUCT_NUMBER";

								row13.PRODUCT_NUMBER = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row13.PRODUCT_NUMBER = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 3;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "product_desc";

								row13.product_desc = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row13.product_desc = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 4;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "division";

								row13.division = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row13.division = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 5;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "dept";

								row13.dept = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row13.dept = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 6;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "rnge";

								row13.rnge = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row13.rnge = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 7;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "import_flag";

								row13.import_flag = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row13.import_flag = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 8;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "export_flag";

								row13.export_flag = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row13.export_flag = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 9;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "SUPPLIER_NUMBER";

								row13.SUPPLIER_NUMBER = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row13.SUPPLIER_NUMBER = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 10;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "supplier_name";

								row13.supplier_name = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row13.supplier_name = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 11;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "SUPPLIER_REF_NO";

								row13.SUPPLIER_REF_NO = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row13.SUPPLIER_REF_NO = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 12;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "brand_group";

								row13.brand_group = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row13.brand_group = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 13;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "Nation_Sent";

								row13.Nation_Sent = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row13.Nation_Sent = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 14;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "net_desp_units";

								row13.net_desp_units = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
										temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2], null,
										'.' == decimalChar_tFileInputExcel_2 ? null : decimalChar_tFileInputExcel_2));
							} else {
								row13.net_desp_units = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 15;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "uk_net_desp_units";

								row13.uk_net_desp_units = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
										temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2], null,
										'.' == decimalChar_tFileInputExcel_2 ? null : decimalChar_tFileInputExcel_2));
							} else {
								row13.uk_net_desp_units = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 16;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "int_net_desp_units";

								row13.int_net_desp_units = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
										temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2], null,
										'.' == decimalChar_tFileInputExcel_2 ? null : decimalChar_tFileInputExcel_2));
							} else {
								row13.int_net_desp_units = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							columnIndex_tFileInputExcel_2 = 17;

							if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
								curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
										+ start_column_tFileInputExcel_2 + 1;
								curColName_tFileInputExcel_2 = "LocationCode";

								row13.LocationCode = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
							} else {
								row13.LocationCode = null;
								emptyColumnCount_tFileInputExcel_2++;
							}
							nb_line_tFileInputExcel_2++;

							log.debug("tFileInputExcel_2 - Retrieving the record " + (nb_line_tFileInputExcel_2) + ".");

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputExcel_2_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputExcel_2 = true;
							System.err.println(e.getMessage());
							row13 = null;
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

// Start of branch "row13"
						if (row13 != null) {

							/**
							 * [tMap_1 main ] start
							 */

							s(currentComponent = "tMap_1");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row13", "tFileInputExcel_2", "tFileInputExcel_2", "tFileInputExcel", "tMap_1",
									"tMap_1", "tMap"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row13 - " + (row13 == null ? "" : row13.toLogString()));
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

								out1_tmp.PRODUCT_LINE_CODE = row13.PRODUCT_LINE_CODE;
								out1_tmp.calender_month = row13.calender_month;
								out1_tmp.PRODUCT_NUMBER = row13.PRODUCT_NUMBER;
								out1_tmp.product_desc = row13.product_desc.replaceAll(" ", "");
								out1_tmp.division = row13.division;
								out1_tmp.dept = row13.dept;
								out1_tmp.rnge = row13.rnge;
								out1_tmp.import_flag = row13.import_flag;
								out1_tmp.export_flag = row13.export_flag;
								out1_tmp.SUPPLIER_NUMBER = row13.SUPPLIER_NUMBER;
								out1_tmp.supplier_name = row13.supplier_name;
								out1_tmp.SUPPLIER_REF_NO = row13.SUPPLIER_REF_NO;
								out1_tmp.brand_group = row13.brand_group;
								out1_tmp.Nation_Sent = row13.Nation_Sent;
								out1_tmp.net_desp_units = row13.net_desp_units;
								out1_tmp.uk_net_desp_units = row13.uk_net_desp_units;
								out1_tmp.int_net_desp_units = row13.int_net_desp_units;
								out1_tmp.LocationCode = row13.LocationCode;
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
									row2.PRODUCT_NUMBER = out1.PRODUCT_NUMBER;
									row2.product_desc = out1.product_desc;
									row2.division = out1.division;
									row2.dept = out1.dept;
									row2.rnge = out1.rnge;
									row2.import_flag = out1.import_flag;
									row2.export_flag = out1.export_flag;
									row2.SUPPLIER_NUMBER = out1.SUPPLIER_NUMBER;
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
									row3.PRODUCT_NUMBER = out1.PRODUCT_NUMBER;
									row3.product_desc = out1.product_desc;
									row3.division = out1.division;
									row3.dept = out1.dept;
									row3.rnge = out1.rnge;
									row3.import_flag = out1.import_flag;
									row3.export_flag = out1.export_flag;
									row3.SUPPLIER_NUMBER = out1.SUPPLIER_NUMBER;
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
									finder_tUniqRow_1.PRODUCT_NUMBER = row2.PRODUCT_NUMBER;
									finder_tUniqRow_1.product_desc = row2.product_desc;
									finder_tUniqRow_1.division = row2.division;
									finder_tUniqRow_1.dept = row2.dept;
									finder_tUniqRow_1.rnge = row2.rnge;
									finder_tUniqRow_1.import_flag = row2.import_flag;
									finder_tUniqRow_1.export_flag = row2.export_flag;
									finder_tUniqRow_1.SUPPLIER_NUMBER = row2.SUPPLIER_NUMBER;
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
										new_tUniqRow_1.PRODUCT_NUMBER = row2.PRODUCT_NUMBER;
										new_tUniqRow_1.product_desc = row2.product_desc;
										new_tUniqRow_1.division = row2.division;
										new_tUniqRow_1.dept = row2.dept;
										new_tUniqRow_1.rnge = row2.rnge;
										new_tUniqRow_1.import_flag = row2.import_flag;
										new_tUniqRow_1.export_flag = row2.export_flag;
										new_tUniqRow_1.SUPPLIER_NUMBER = row2.SUPPLIER_NUMBER;
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
										row4.PRODUCT_NUMBER = row2.PRODUCT_NUMBER;
										row4.product_desc = row2.product_desc;
										row4.division = row2.division;
										row4.dept = row2.dept;
										row4.rnge = row2.rnge;
										row4.import_flag = row2.import_flag;
										row4.export_flag = row2.export_flag;
										row4.SUPPLIER_NUMBER = row2.SUPPLIER_NUMBER;
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
										row6.PRODUCT_NUMBER = row2.PRODUCT_NUMBER;
										row6.product_desc = row2.product_desc;
										row6.division = row2.division;
										row6.dept = row2.dept;
										row6.rnge = row2.rnge;
										row6.import_flag = row2.import_flag;
										row6.export_flag = row2.export_flag;
										row6.SUPPLIER_NUMBER = row2.SUPPLIER_NUMBER;
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
											out2_tmp.PRODUCT_NUMBER = row4.PRODUCT_NUMBER;
											out2_tmp.product_desc = row4.product_desc;
											out2_tmp.division = row4.division;
											out2_tmp.dept = row4.dept;
											out2_tmp.rnge = row4.rnge;
											out2_tmp.import_flag = row4.import_flag;
											out2_tmp.export_flag = row4.export_flag;
											out2_tmp.SUPPLIER_NUMBER = row4.SUPPLIER_NUMBER;
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
											out3_tmp.PRODUCT_NUMBER = row4.PRODUCT_NUMBER;
											out3_tmp.product_desc = row4.product_desc;
											out3_tmp.division = row4.division;
											out3_tmp.dept = row4.dept;
											out3_tmp.rnge = row4.rnge;
											out3_tmp.import_flag = row4.import_flag;
											out3_tmp.export_flag = row4.export_flag;
											out3_tmp.SUPPLIER_NUMBER = row4.SUPPLIER_NUMBER;
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
											row7.PRODUCT_NUMBER = out2.PRODUCT_NUMBER;
											row7.product_desc = out2.product_desc;
											row7.division = out2.division;
											row7.dept = out2.dept;
											row7.rnge = out2.rnge;
											row7.import_flag = out2.import_flag;
											row7.export_flag = out2.export_flag;
											row7.SUPPLIER_NUMBER = out2.SUPPLIER_NUMBER;
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
												out4_tmp.PRODUCT_NUMBER = row7.PRODUCT_NUMBER;
												out4_tmp.product_desc = row7.product_desc;
												out4_tmp.division = row7.division;
												out4_tmp.dept = row7.dept;
												out4_tmp.rnge = row7.rnge;
												out4_tmp.import_flag = row7.import_flag;
												out4_tmp.export_flag = row7.export_flag;
												out4_tmp.SUPPLIER_NUMBER = row7.SUPPLIER_NUMBER;
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
												out4_tmp.brand_indicator = row7.brand_group.equals("Own Brand") ? "1"
														: "0";
												;
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

												row9 = null;
												row8 = null;
												Operator_tFilterRow_30 ope_tFilterRow_30 = new Operator_tFilterRow_30(
														"||");
												ope_tFilterRow_30.matches((// code sample : use out4 to define the
																			// condition.
// out4.columnName1.equals("foo") ||!(out4.columnName2.equals("bar"))
// replace the following expression by your own filter condition 
												!Relational.ISNULL(out4.PRODUCT_NUMBER)
														&& !Relational.ISNULL(out4.product_desc)
														&& !Relational.ISNULL(out4.LocationCode)),
														"advanced condition failed");

												if (ope_tFilterRow_30.getMatchFlag()) {
													if (row8 == null) {
														row8 = new row8Struct();
													}
													row8.PRODUCT_LINE_CODE = out4.PRODUCT_LINE_CODE;
													row8.calender_month = out4.calender_month;
													row8.PRODUCT_NUMBER = out4.PRODUCT_NUMBER;
													row8.product_desc = out4.product_desc;
													row8.division = out4.division;
													row8.dept = out4.dept;
													row8.rnge = out4.rnge;
													row8.import_flag = out4.import_flag;
													row8.export_flag = out4.export_flag;
													row8.SUPPLIER_NUMBER = out4.SUPPLIER_NUMBER;
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
													row8.brand_indicator = out4.brand_indicator;
													log.debug("tFilterRow_30 - Process the record "
															+ (nb_line_tFilterRow_30 + 1) + ".");

													nb_line_ok_tFilterRow_30++;
												} else {
													if (row9 == null) {
														row9 = new row9Struct();
													}
													row9.PRODUCT_LINE_CODE = out4.PRODUCT_LINE_CODE;
													row9.calender_month = out4.calender_month;
													row9.PRODUCT_NUMBER = out4.PRODUCT_NUMBER;
													row9.product_desc = out4.product_desc;
													row9.division = out4.division;
													row9.dept = out4.dept;
													row9.rnge = out4.rnge;
													row9.import_flag = out4.import_flag;
													row9.export_flag = out4.export_flag;
													row9.SUPPLIER_NUMBER = out4.SUPPLIER_NUMBER;
													row9.supplier_name = out4.supplier_name;
													row9.SUPPLIER_REF_NO = out4.SUPPLIER_REF_NO;
													row9.brand_group = out4.brand_group;
													row9.Nation_Sent = out4.Nation_Sent;
													row9.net_desp_units = out4.net_desp_units;
													row9.uk_net_desp_units = out4.uk_net_desp_units;
													row9.int_net_desp_units = out4.int_net_desp_units;
													row9.LocationCode = out4.LocationCode;
													row9.ExportFlag = out4.ExportFlag;
													row9.SalesQuantity = out4.SalesQuantity;
													row9.Audit_BatchName = out4.Audit_BatchName;
													row9.Audit_BatchName_Description = out4.Audit_BatchName_Description;
													row9.Audit_BatchNo = out4.Audit_BatchNo;
													row9.Audit_BatchTimeExecution = out4.Audit_BatchTimeExecution;
													row9.brand_indicator = out4.brand_indicator;
													row9.errorMessage = ope_tFilterRow_30.getErrorMsg();
													log.debug("tFilterRow_30 - Process the record "
															+ (nb_line_tFilterRow_30 + 1) + ".");

													log.debug("tFilterRow_30 - Reject the record "
															+ (nb_line_tFilterRow_30 + 1) + ". Caused by: "
															+ row9.errorMessage + ".");

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
													 * [tFileOutputDelimited_1 main ] start
													 */

													s(currentComponent = "tFileOutputDelimited_1");

													if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

															, "row8", "tFilterRow_30", "tFilterRow_30", "tFilterRow",
															"tFileOutputDelimited_1", "tFileOutputDelimited_1",
															"tFileOutputDelimited"

													)) {
														talendJobLogProcess(globalMap);
													}

													if (log.isTraceEnabled()) {
														log.trace("row8 - " + (row8 == null ? "" : row8.toLogString()));
													}

													StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
													if (row8.PRODUCT_LINE_CODE != null) {
														sb_tFileOutputDelimited_1.append(row8.PRODUCT_LINE_CODE);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.calender_month != null) {
														sb_tFileOutputDelimited_1.append(row8.calender_month);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.PRODUCT_NUMBER != null) {
														sb_tFileOutputDelimited_1.append(row8.PRODUCT_NUMBER);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.product_desc != null) {
														sb_tFileOutputDelimited_1.append(row8.product_desc);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.division != null) {
														sb_tFileOutputDelimited_1.append(row8.division);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.dept != null) {
														sb_tFileOutputDelimited_1.append(row8.dept);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.rnge != null) {
														sb_tFileOutputDelimited_1.append(row8.rnge);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.import_flag != null) {
														sb_tFileOutputDelimited_1.append(row8.import_flag);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.export_flag != null) {
														sb_tFileOutputDelimited_1.append(row8.export_flag);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.SUPPLIER_NUMBER != null) {
														sb_tFileOutputDelimited_1.append(row8.SUPPLIER_NUMBER);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.supplier_name != null) {
														sb_tFileOutputDelimited_1.append(row8.supplier_name);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.SUPPLIER_REF_NO != null) {
														sb_tFileOutputDelimited_1.append(row8.SUPPLIER_REF_NO);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.brand_group != null) {
														sb_tFileOutputDelimited_1.append(row8.brand_group);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.Nation_Sent != null) {
														sb_tFileOutputDelimited_1.append(row8.Nation_Sent);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.net_desp_units != null) {
														sb_tFileOutputDelimited_1.append(row8.net_desp_units);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.uk_net_desp_units != null) {
														sb_tFileOutputDelimited_1.append(row8.uk_net_desp_units);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.int_net_desp_units != null) {
														sb_tFileOutputDelimited_1.append(row8.int_net_desp_units);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.LocationCode != null) {
														sb_tFileOutputDelimited_1.append(row8.LocationCode);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.ExportFlag != null) {
														sb_tFileOutputDelimited_1.append(row8.ExportFlag);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.SalesQuantity != null) {
														sb_tFileOutputDelimited_1.append(row8.SalesQuantity);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.Audit_BatchName != null) {
														sb_tFileOutputDelimited_1.append(row8.Audit_BatchName);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.Audit_BatchName_Description != null) {
														sb_tFileOutputDelimited_1
																.append(row8.Audit_BatchName_Description);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.Audit_BatchNo != null) {
														sb_tFileOutputDelimited_1.append(row8.Audit_BatchNo);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.Audit_BatchTimeExecution != null) {
														sb_tFileOutputDelimited_1.append(row8.Audit_BatchTimeExecution);
													}
													sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
													if (row8.brand_indicator != null) {
														sb_tFileOutputDelimited_1.append(row8.brand_indicator);
													}
													sb_tFileOutputDelimited_1
															.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

													nb_line_tFileOutputDelimited_1++;
													resourceMap.put("nb_line_tFileOutputDelimited_1",
															nb_line_tFileOutputDelimited_1);

													outtFileOutputDelimited_1
															.write(sb_tFileOutputDelimited_1.toString());
													log.debug("tFileOutputDelimited_1 - Writing the record "
															+ nb_line_tFileOutputDelimited_1 + ".");

													tos_count_tFileOutputDelimited_1++;

													/**
													 * [tFileOutputDelimited_1 main ] stop
													 */

													/**
													 * [tFileOutputDelimited_1 process_data_begin ] start
													 */

													s(currentComponent = "tFileOutputDelimited_1");

													/**
													 * [tFileOutputDelimited_1 process_data_begin ] stop
													 */

													/**
													 * [tFileOutputDelimited_1 process_data_end ] start
													 */

													s(currentComponent = "tFileOutputDelimited_1");

													/**
													 * [tFileOutputDelimited_1 process_data_end ] stop
													 */

												} // End of branch "row8"

// Start of branch "row9"
												if (row9 != null) {

													/**
													 * [tLogRow_1 main ] start
													 */

													s(currentComponent = "tLogRow_1");

													cLabel = "mandatory_column_failures";

													if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

															, "row9", "tFilterRow_30", "tFilterRow_30", "tFilterRow",
															"tLogRow_1", "mandatory_column_failures", "tLogRow"

													)) {
														talendJobLogProcess(globalMap);
													}

													if (log.isTraceEnabled()) {
														log.trace("row9 - " + (row9 == null ? "" : row9.toLogString()));
													}

///////////////////////		

													String[] row_tLogRow_1 = new String[26];

													if (row9.PRODUCT_LINE_CODE != null) { //
														row_tLogRow_1[0] = String.valueOf(row9.PRODUCT_LINE_CODE);

													} //

													if (row9.calender_month != null) { //
														row_tLogRow_1[1] = String.valueOf(row9.calender_month);

													} //

													if (row9.PRODUCT_NUMBER != null) { //
														row_tLogRow_1[2] = String.valueOf(row9.PRODUCT_NUMBER);

													} //

													if (row9.product_desc != null) { //
														row_tLogRow_1[3] = String.valueOf(row9.product_desc);

													} //

													if (row9.division != null) { //
														row_tLogRow_1[4] = String.valueOf(row9.division);

													} //

													if (row9.dept != null) { //
														row_tLogRow_1[5] = String.valueOf(row9.dept);

													} //

													if (row9.rnge != null) { //
														row_tLogRow_1[6] = String.valueOf(row9.rnge);

													} //

													if (row9.import_flag != null) { //
														row_tLogRow_1[7] = String.valueOf(row9.import_flag);

													} //

													if (row9.export_flag != null) { //
														row_tLogRow_1[8] = String.valueOf(row9.export_flag);

													} //

													if (row9.SUPPLIER_NUMBER != null) { //
														row_tLogRow_1[9] = String.valueOf(row9.SUPPLIER_NUMBER);

													} //

													if (row9.supplier_name != null) { //
														row_tLogRow_1[10] = String.valueOf(row9.supplier_name);

													} //

													if (row9.SUPPLIER_REF_NO != null) { //
														row_tLogRow_1[11] = String.valueOf(row9.SUPPLIER_REF_NO);

													} //

													if (row9.brand_group != null) { //
														row_tLogRow_1[12] = String.valueOf(row9.brand_group);

													} //

													if (row9.Nation_Sent != null) { //
														row_tLogRow_1[13] = String.valueOf(row9.Nation_Sent);

													} //

													if (row9.net_desp_units != null) { //
														row_tLogRow_1[14] = String.valueOf(row9.net_desp_units);

													} //

													if (row9.uk_net_desp_units != null) { //
														row_tLogRow_1[15] = String.valueOf(row9.uk_net_desp_units);

													} //

													if (row9.int_net_desp_units != null) { //
														row_tLogRow_1[16] = String.valueOf(row9.int_net_desp_units);

													} //

													if (row9.LocationCode != null) { //
														row_tLogRow_1[17] = String.valueOf(row9.LocationCode);

													} //

													if (row9.ExportFlag != null) { //
														row_tLogRow_1[18] = String.valueOf(row9.ExportFlag);

													} //

													if (row9.SalesQuantity != null) { //
														row_tLogRow_1[19] = String.valueOf(row9.SalesQuantity);

													} //

													if (row9.Audit_BatchName != null) { //
														row_tLogRow_1[20] = String.valueOf(row9.Audit_BatchName);

													} //

													if (row9.Audit_BatchName_Description != null) { //
														row_tLogRow_1[21] = String
																.valueOf(row9.Audit_BatchName_Description);

													} //

													if (row9.Audit_BatchNo != null) { //
														row_tLogRow_1[22] = String.valueOf(row9.Audit_BatchNo);

													} //

													if (row9.Audit_BatchTimeExecution != null) { //
														row_tLogRow_1[23] = String
																.valueOf(row9.Audit_BatchTimeExecution);

													} //

													if (row9.brand_indicator != null) { //
														row_tLogRow_1[24] = String.valueOf(row9.brand_indicator);

													} //

													if (row9.errorMessage != null) { //
														row_tLogRow_1[25] = String.valueOf(row9.errorMessage);

													} //

													util_tLogRow_1.addRow(row_tLogRow_1);
													nb_line_tLogRow_1++;
													log.info("tLogRow_1 - Content of row " + nb_line_tLogRow_1 + ": "
															+ TalendString.unionString("|", row_tLogRow_1));
//////

//////                    

///////////////////////    			

													tos_count_tLogRow_1++;

													/**
													 * [tLogRow_1 main ] stop
													 */

													/**
													 * [tLogRow_1 process_data_begin ] start
													 */

													s(currentComponent = "tLogRow_1");

													cLabel = "mandatory_column_failures";

													/**
													 * [tLogRow_1 process_data_begin ] stop
													 */

													/**
													 * [tLogRow_1 process_data_end ] start
													 */

													s(currentComponent = "tLogRow_1");

													cLabel = "mandatory_column_failures";

													/**
													 * [tLogRow_1 process_data_end ] stop
													 */

												} // End of branch "row9"

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
											oneRow_tHashOutput_1.PRODUCT_NUMBER = out3.PRODUCT_NUMBER;
											oneRow_tHashOutput_1.product_desc = out3.product_desc;
											oneRow_tHashOutput_1.division = out3.division;
											oneRow_tHashOutput_1.dept = out3.dept;
											oneRow_tHashOutput_1.rnge = out3.rnge;
											oneRow_tHashOutput_1.import_flag = out3.import_flag;
											oneRow_tHashOutput_1.export_flag = out3.export_flag;
											oneRow_tHashOutput_1.SUPPLIER_NUMBER = out3.SUPPLIER_NUMBER;
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

										if (row6.PRODUCT_NUMBER != null) { //
											row_tLogRow_4[2] = String.valueOf(row6.PRODUCT_NUMBER);

										} //

										if (row6.product_desc != null) { //
											row_tLogRow_4[3] = String.valueOf(row6.product_desc);

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

										if (row6.SUPPLIER_NUMBER != null) { //
											row_tLogRow_4[9] = String.valueOf(row6.SUPPLIER_NUMBER);

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

										row14 = row6;

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
										 * [tDataStewardshipTaskOutput_1 main ] start
										 */

										s(currentComponent = "tDataStewardshipTaskOutput_1");

										if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

												, "row14", "tLogRow_4", "duplicates", "tLogRow",
												"tDataStewardshipTaskOutput_1", "tDataStewardshipTaskOutput_1",
												"tDataStewardshipTaskOutput"

										)) {
											talendJobLogProcess(globalMap);
										}

										if (log.isTraceEnabled()) {
											log.trace("row14 - " + (row14 == null ? "" : row14.toLogString()));
										}

										boolean shouldCreateRuntimeSchemaForIncomingNode = false;
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("PRODUCT_LINE_CODE") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"PRODUCT_LINE_CODE",
													((Object) row14.PRODUCT_LINE_CODE).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("calender_month") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"calender_month",
													((Object) row14.calender_month).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("PRODUCT_NUMBER") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"PRODUCT_NUMBER",
													((Object) row14.PRODUCT_NUMBER).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("product_desc") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"product_desc",
													((Object) row14.product_desc).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("division") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"division",
													((Object) row14.division).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("dept") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField("dept",
													((Object) row14.dept).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("rnge") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField("rnge",
													((Object) row14.rnge).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("import_flag") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"import_flag",
													((Object) row14.import_flag).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("export_flag") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"export_flag",
													((Object) row14.export_flag).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("SUPPLIER_NUMBER") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"SUPPLIER_NUMBER",
													((Object) row14.SUPPLIER_NUMBER).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("supplier_name") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"supplier_name",
													((Object) row14.supplier_name).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("SUPPLIER_REF_NO") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"SUPPLIER_REF_NO",
													((Object) row14.SUPPLIER_REF_NO).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("brand_group") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"brand_group",
													((Object) row14.brand_group).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("Nation_Sent") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"Nation_Sent",
													((Object) row14.Nation_Sent).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("net_desp_units") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"net_desp_units",
													((Object) row14.net_desp_units).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("uk_net_desp_units") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"uk_net_desp_units",
													((Object) row14.uk_net_desp_units).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("int_net_desp_units") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"int_net_desp_units",
													((Object) row14.int_net_desp_units).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getDesignSchema()
														.getField("LocationCode") == null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.addIncomingNodeField(
													"LocationCode",
													((Object) row14.LocationCode).getClass().getCanonicalName());
											shouldCreateRuntimeSchemaForIncomingNode = true;
										}
										if (shouldCreateRuntimeSchemaForIncomingNode
												&& incomingEnforcer_tDataStewardshipTaskOutput_1 != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.createRuntimeSchema();
										}
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.createNewRecord();
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("PRODUCT_LINE_CODE") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("PRODUCT_LINE_CODE",
													row14.PRODUCT_LINE_CODE);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("calender_month") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("calender_month",
													row14.calender_month);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("PRODUCT_NUMBER") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("PRODUCT_NUMBER",
													row14.PRODUCT_NUMBER);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("product_desc") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("product_desc",
													row14.product_desc);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("division") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("division",
													row14.division);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("dept") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("dept", row14.dept);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("rnge") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("rnge", row14.rnge);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("import_flag") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("import_flag",
													row14.import_flag);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("export_flag") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("export_flag",
													row14.export_flag);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("SUPPLIER_NUMBER") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("SUPPLIER_NUMBER",
													row14.SUPPLIER_NUMBER);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("supplier_name") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("supplier_name",
													row14.supplier_name);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("SUPPLIER_REF_NO") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("SUPPLIER_REF_NO",
													row14.SUPPLIER_REF_NO);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("brand_group") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("brand_group",
													row14.brand_group);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("Nation_Sent") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("Nation_Sent",
													row14.Nation_Sent);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("net_desp_units") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("net_desp_units",
													row14.net_desp_units);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("uk_net_desp_units") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("uk_net_desp_units",
													row14.uk_net_desp_units);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("int_net_desp_units") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("int_net_desp_units",
													row14.int_net_desp_units);
										}
										// skip the put action if the input column doesn't appear in component runtime
										// schema
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null
												&& incomingEnforcer_tDataStewardshipTaskOutput_1.getRuntimeSchema()
														.getField("LocationCode") != null) {
											incomingEnforcer_tDataStewardshipTaskOutput_1.put("LocationCode",
													row14.LocationCode);
										}

										org.apache.avro.generic.IndexedRecord data_tDataStewardshipTaskOutput_1 = null;
										if (incomingEnforcer_tDataStewardshipTaskOutput_1 != null) {
											data_tDataStewardshipTaskOutput_1 = incomingEnforcer_tDataStewardshipTaskOutput_1
													.getCurrentRecord();
										}

										if (writer_tDataStewardshipTaskOutput_1 != null
												&& data_tDataStewardshipTaskOutput_1 != null) {
											writer_tDataStewardshipTaskOutput_1
													.write(data_tDataStewardshipTaskOutput_1);
										}

										nb_line_tDataStewardshipTaskOutput_1++;

										tos_count_tDataStewardshipTaskOutput_1++;

										/**
										 * [tDataStewardshipTaskOutput_1 main ] stop
										 */

										/**
										 * [tDataStewardshipTaskOutput_1 process_data_begin ] start
										 */

										s(currentComponent = "tDataStewardshipTaskOutput_1");

										/**
										 * [tDataStewardshipTaskOutput_1 process_data_begin ] stop
										 */

										/**
										 * [tDataStewardshipTaskOutput_1 process_data_end ] start
										 */

										s(currentComponent = "tDataStewardshipTaskOutput_1");

										/**
										 * [tDataStewardshipTaskOutput_1 process_data_end ] stop
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

									cLabel = "sales_number_rejects";

									if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

											, "row3", "tFilterRow_11", "tFilterRow_11", "tFilterRow", "tLogRow_2",
											"sales_number_rejects", "tLogRow"

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

									if (row3.PRODUCT_NUMBER != null) { //
										row_tLogRow_2[2] = String.valueOf(row3.PRODUCT_NUMBER);

									} //

									if (row3.product_desc != null) { //
										row_tLogRow_2[3] = String.valueOf(row3.product_desc);

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

									if (row3.SUPPLIER_NUMBER != null) { //
										row_tLogRow_2[9] = String.valueOf(row3.SUPPLIER_NUMBER);

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

									cLabel = "sales_number_rejects";

									/**
									 * [tLogRow_2 process_data_begin ] stop
									 */

									/**
									 * [tLogRow_2 process_data_end ] start
									 */

									s(currentComponent = "tLogRow_2");

									cLabel = "sales_number_rejects";

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

						} // End of branch "row13"

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
				 * [tMap_1 end ] start
				 */

				s(currentComponent = "tMap_1");

// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("tMap_1 - Written records count in the table 'out1': " + count_out1_tMap_1 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row13", 2, 0,
						"tFileInputExcel_2", "tFileInputExcel_2", "tFileInputExcel", "tMap_1", "tMap_1", "tMap",
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
				 * [tDataStewardshipTaskOutput_1 end ] start
				 */

				s(currentComponent = "tDataStewardshipTaskOutput_1");

// end of generic

				resourceMap.put("finish_tDataStewardshipTaskOutput_1", Boolean.TRUE);

				java.util.Map<String, Object> resultMap_tDataStewardshipTaskOutput_1 = null;
				if (writer_tDataStewardshipTaskOutput_1 != null) {
					org.talend.components.api.component.runtime.Result resultObject_tDataStewardshipTaskOutput_1 = (org.talend.components.api.component.runtime.Result) writer_tDataStewardshipTaskOutput_1
							.close();
					resultMap_tDataStewardshipTaskOutput_1 = writer_tDataStewardshipTaskOutput_1.getWriteOperation()
							.finalize(
									java.util.Arrays.<org.talend.components.api.component.runtime.Result>asList(
											resultObject_tDataStewardshipTaskOutput_1),
									container_tDataStewardshipTaskOutput_1);
				}
				if (resultMap_tDataStewardshipTaskOutput_1 != null) {
					for (java.util.Map.Entry<String, Object> entry_tDataStewardshipTaskOutput_1 : resultMap_tDataStewardshipTaskOutput_1
							.entrySet()) {
						switch (entry_tDataStewardshipTaskOutput_1.getKey()) {
						case org.talend.components.api.component.ComponentDefinition.RETURN_ERROR_MESSAGE:
							container_tDataStewardshipTaskOutput_1.setComponentData("tDataStewardshipTaskOutput_1",
									"ERROR_MESSAGE", entry_tDataStewardshipTaskOutput_1.getValue());
							break;
						case org.talend.components.api.component.ComponentDefinition.RETURN_TOTAL_RECORD_COUNT:
							container_tDataStewardshipTaskOutput_1.setComponentData("tDataStewardshipTaskOutput_1",
									"NB_LINE", entry_tDataStewardshipTaskOutput_1.getValue());
							break;
						case org.talend.components.api.component.ComponentDefinition.RETURN_SUCCESS_RECORD_COUNT:
							container_tDataStewardshipTaskOutput_1.setComponentData("tDataStewardshipTaskOutput_1",
									"NB_SUCCESS", entry_tDataStewardshipTaskOutput_1.getValue());
							break;
						case org.talend.components.api.component.ComponentDefinition.RETURN_REJECT_RECORD_COUNT:
							container_tDataStewardshipTaskOutput_1.setComponentData("tDataStewardshipTaskOutput_1",
									"NB_REJECT", entry_tDataStewardshipTaskOutput_1.getValue());
							break;
						default:
							StringBuilder studio_key_tDataStewardshipTaskOutput_1 = new StringBuilder();
							for (int i_tDataStewardshipTaskOutput_1 = 0; i_tDataStewardshipTaskOutput_1 < entry_tDataStewardshipTaskOutput_1
									.getKey().length(); i_tDataStewardshipTaskOutput_1++) {
								char ch_tDataStewardshipTaskOutput_1 = entry_tDataStewardshipTaskOutput_1.getKey()
										.charAt(i_tDataStewardshipTaskOutput_1);
								if (Character.isUpperCase(ch_tDataStewardshipTaskOutput_1)
										&& i_tDataStewardshipTaskOutput_1 > 0) {
									studio_key_tDataStewardshipTaskOutput_1.append('_');
								}
								studio_key_tDataStewardshipTaskOutput_1.append(ch_tDataStewardshipTaskOutput_1);
							}
							container_tDataStewardshipTaskOutput_1.setComponentData(
									"tDataStewardshipTaskOutput_1", studio_key_tDataStewardshipTaskOutput_1.toString()
											.toUpperCase(java.util.Locale.ENGLISH),
									entry_tDataStewardshipTaskOutput_1.getValue());
							break;
						}
					}
				}

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row14", 2, 0,
						"tLogRow_4", "duplicates", "tLogRow", "tDataStewardshipTaskOutput_1",
						"tDataStewardshipTaskOutput_1", "tDataStewardshipTaskOutput", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tDataStewardshipTaskOutput_1", true);
				end_Hash.put("tDataStewardshipTaskOutput_1", System.currentTimeMillis());

				/**
				 * [tDataStewardshipTaskOutput_1 end ] stop
				 */

				/**
				 * [tLogRow_2 end ] start
				 */

				s(currentComponent = "tLogRow_2");

				cLabel = "sales_number_rejects";

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
						"tFilterRow_11", "tFilterRow_11", "tFilterRow", "tLogRow_2", "sales_number_rejects", "tLogRow",
						"reject")) {
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
						.getAdvancedMemoryHashFile("tHashFile_JDWilliams_expanded_" + pid + "_tHashOutput_1");
				if (tHashFile_tHashInput_1 == null) {
					throw new RuntimeException(
							"The hash is not initialized : The hash must exist before you read from it");
				}
				java.util.Iterator<out3Struct> iterator_tHashInput_1 = tHashFile_tHashInput_1.iterator();
				while (iterator_tHashInput_1.hasNext()) {
					out3Struct next_tHashInput_1 = iterator_tHashInput_1.next();

					row5.PRODUCT_LINE_CODE = next_tHashInput_1.PRODUCT_LINE_CODE;
					row5.calender_month = next_tHashInput_1.calender_month;
					row5.PRODUCT_NUMBER = next_tHashInput_1.PRODUCT_NUMBER;
					row5.product_desc = next_tHashInput_1.product_desc;
					row5.division = next_tHashInput_1.division;
					row5.dept = next_tHashInput_1.dept;
					row5.rnge = next_tHashInput_1.rnge;
					row5.import_flag = next_tHashInput_1.import_flag;
					row5.export_flag = next_tHashInput_1.export_flag;
					row5.SUPPLIER_NUMBER = next_tHashInput_1.SUPPLIER_NUMBER;
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
					row7.PRODUCT_NUMBER = row5.PRODUCT_NUMBER;
					row7.product_desc = row5.product_desc;
					row7.division = row5.division;
					row7.dept = row5.dept;
					row7.rnge = row5.rnge;
					row7.import_flag = row5.import_flag;
					row7.export_flag = row5.export_flag;
					row7.SUPPLIER_NUMBER = row5.SUPPLIER_NUMBER;
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
						out4_tmp.PRODUCT_NUMBER = row7.PRODUCT_NUMBER;
						out4_tmp.product_desc = row7.product_desc;
						out4_tmp.division = row7.division;
						out4_tmp.dept = row7.dept;
						out4_tmp.rnge = row7.rnge;
						out4_tmp.import_flag = row7.import_flag;
						out4_tmp.export_flag = row7.export_flag;
						out4_tmp.SUPPLIER_NUMBER = row7.SUPPLIER_NUMBER;
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
						out4_tmp.brand_indicator = row7.brand_group.equals("Own Brand") ? "1" : "0";
						;
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

						row9 = null;
						row8 = null;
						Operator_tFilterRow_30 ope_tFilterRow_30 = new Operator_tFilterRow_30("||");
						ope_tFilterRow_30.matches((// code sample : use out4 to define the condition.
// out4.columnName1.equals("foo") ||!(out4.columnName2.equals("bar"))
// replace the following expression by your own filter condition 
						!Relational.ISNULL(out4.PRODUCT_NUMBER) && !Relational.ISNULL(out4.product_desc)
								&& !Relational.ISNULL(out4.LocationCode)), "advanced condition failed");

						if (ope_tFilterRow_30.getMatchFlag()) {
							if (row8 == null) {
								row8 = new row8Struct();
							}
							row8.PRODUCT_LINE_CODE = out4.PRODUCT_LINE_CODE;
							row8.calender_month = out4.calender_month;
							row8.PRODUCT_NUMBER = out4.PRODUCT_NUMBER;
							row8.product_desc = out4.product_desc;
							row8.division = out4.division;
							row8.dept = out4.dept;
							row8.rnge = out4.rnge;
							row8.import_flag = out4.import_flag;
							row8.export_flag = out4.export_flag;
							row8.SUPPLIER_NUMBER = out4.SUPPLIER_NUMBER;
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
							row8.brand_indicator = out4.brand_indicator;
							log.debug("tFilterRow_30 - Process the record " + (nb_line_tFilterRow_30 + 1) + ".");

							nb_line_ok_tFilterRow_30++;
						} else {
							if (row9 == null) {
								row9 = new row9Struct();
							}
							row9.PRODUCT_LINE_CODE = out4.PRODUCT_LINE_CODE;
							row9.calender_month = out4.calender_month;
							row9.PRODUCT_NUMBER = out4.PRODUCT_NUMBER;
							row9.product_desc = out4.product_desc;
							row9.division = out4.division;
							row9.dept = out4.dept;
							row9.rnge = out4.rnge;
							row9.import_flag = out4.import_flag;
							row9.export_flag = out4.export_flag;
							row9.SUPPLIER_NUMBER = out4.SUPPLIER_NUMBER;
							row9.supplier_name = out4.supplier_name;
							row9.SUPPLIER_REF_NO = out4.SUPPLIER_REF_NO;
							row9.brand_group = out4.brand_group;
							row9.Nation_Sent = out4.Nation_Sent;
							row9.net_desp_units = out4.net_desp_units;
							row9.uk_net_desp_units = out4.uk_net_desp_units;
							row9.int_net_desp_units = out4.int_net_desp_units;
							row9.LocationCode = out4.LocationCode;
							row9.ExportFlag = out4.ExportFlag;
							row9.SalesQuantity = out4.SalesQuantity;
							row9.Audit_BatchName = out4.Audit_BatchName;
							row9.Audit_BatchName_Description = out4.Audit_BatchName_Description;
							row9.Audit_BatchNo = out4.Audit_BatchNo;
							row9.Audit_BatchTimeExecution = out4.Audit_BatchTimeExecution;
							row9.brand_indicator = out4.brand_indicator;
							row9.errorMessage = ope_tFilterRow_30.getErrorMsg();
							log.debug("tFilterRow_30 - Process the record " + (nb_line_tFilterRow_30 + 1) + ".");

							log.debug("tFilterRow_30 - Reject the record " + (nb_line_tFilterRow_30 + 1)
									+ ". Caused by: " + row9.errorMessage + ".");

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
							 * [tFileOutputDelimited_1 main ] start
							 */

							s(currentComponent = "tFileOutputDelimited_1");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row8", "tFilterRow_30", "tFilterRow_30", "tFilterRow", "tFileOutputDelimited_1",
									"tFileOutputDelimited_1", "tFileOutputDelimited"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row8 - " + (row8 == null ? "" : row8.toLogString()));
							}

							StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
							if (row8.PRODUCT_LINE_CODE != null) {
								sb_tFileOutputDelimited_1.append(row8.PRODUCT_LINE_CODE);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.calender_month != null) {
								sb_tFileOutputDelimited_1.append(row8.calender_month);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.PRODUCT_NUMBER != null) {
								sb_tFileOutputDelimited_1.append(row8.PRODUCT_NUMBER);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.product_desc != null) {
								sb_tFileOutputDelimited_1.append(row8.product_desc);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.division != null) {
								sb_tFileOutputDelimited_1.append(row8.division);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.dept != null) {
								sb_tFileOutputDelimited_1.append(row8.dept);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.rnge != null) {
								sb_tFileOutputDelimited_1.append(row8.rnge);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.import_flag != null) {
								sb_tFileOutputDelimited_1.append(row8.import_flag);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.export_flag != null) {
								sb_tFileOutputDelimited_1.append(row8.export_flag);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.SUPPLIER_NUMBER != null) {
								sb_tFileOutputDelimited_1.append(row8.SUPPLIER_NUMBER);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.supplier_name != null) {
								sb_tFileOutputDelimited_1.append(row8.supplier_name);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.SUPPLIER_REF_NO != null) {
								sb_tFileOutputDelimited_1.append(row8.SUPPLIER_REF_NO);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.brand_group != null) {
								sb_tFileOutputDelimited_1.append(row8.brand_group);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.Nation_Sent != null) {
								sb_tFileOutputDelimited_1.append(row8.Nation_Sent);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.net_desp_units != null) {
								sb_tFileOutputDelimited_1.append(row8.net_desp_units);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.uk_net_desp_units != null) {
								sb_tFileOutputDelimited_1.append(row8.uk_net_desp_units);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.int_net_desp_units != null) {
								sb_tFileOutputDelimited_1.append(row8.int_net_desp_units);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.LocationCode != null) {
								sb_tFileOutputDelimited_1.append(row8.LocationCode);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.ExportFlag != null) {
								sb_tFileOutputDelimited_1.append(row8.ExportFlag);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.SalesQuantity != null) {
								sb_tFileOutputDelimited_1.append(row8.SalesQuantity);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.Audit_BatchName != null) {
								sb_tFileOutputDelimited_1.append(row8.Audit_BatchName);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.Audit_BatchName_Description != null) {
								sb_tFileOutputDelimited_1.append(row8.Audit_BatchName_Description);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.Audit_BatchNo != null) {
								sb_tFileOutputDelimited_1.append(row8.Audit_BatchNo);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.Audit_BatchTimeExecution != null) {
								sb_tFileOutputDelimited_1.append(row8.Audit_BatchTimeExecution);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
							if (row8.brand_indicator != null) {
								sb_tFileOutputDelimited_1.append(row8.brand_indicator);
							}
							sb_tFileOutputDelimited_1.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

							nb_line_tFileOutputDelimited_1++;
							resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

							outtFileOutputDelimited_1.write(sb_tFileOutputDelimited_1.toString());
							log.debug("tFileOutputDelimited_1 - Writing the record " + nb_line_tFileOutputDelimited_1
									+ ".");

							tos_count_tFileOutputDelimited_1++;

							/**
							 * [tFileOutputDelimited_1 main ] stop
							 */

							/**
							 * [tFileOutputDelimited_1 process_data_begin ] start
							 */

							s(currentComponent = "tFileOutputDelimited_1");

							/**
							 * [tFileOutputDelimited_1 process_data_begin ] stop
							 */

							/**
							 * [tFileOutputDelimited_1 process_data_end ] start
							 */

							s(currentComponent = "tFileOutputDelimited_1");

							/**
							 * [tFileOutputDelimited_1 process_data_end ] stop
							 */

						} // End of branch "row8"

// Start of branch "row9"
						if (row9 != null) {

							/**
							 * [tLogRow_1 main ] start
							 */

							s(currentComponent = "tLogRow_1");

							cLabel = "mandatory_column_failures";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row9", "tFilterRow_30", "tFilterRow_30", "tFilterRow", "tLogRow_1",
									"mandatory_column_failures", "tLogRow"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row9 - " + (row9 == null ? "" : row9.toLogString()));
							}

///////////////////////		

							String[] row_tLogRow_1 = new String[26];

							if (row9.PRODUCT_LINE_CODE != null) { //
								row_tLogRow_1[0] = String.valueOf(row9.PRODUCT_LINE_CODE);

							} //

							if (row9.calender_month != null) { //
								row_tLogRow_1[1] = String.valueOf(row9.calender_month);

							} //

							if (row9.PRODUCT_NUMBER != null) { //
								row_tLogRow_1[2] = String.valueOf(row9.PRODUCT_NUMBER);

							} //

							if (row9.product_desc != null) { //
								row_tLogRow_1[3] = String.valueOf(row9.product_desc);

							} //

							if (row9.division != null) { //
								row_tLogRow_1[4] = String.valueOf(row9.division);

							} //

							if (row9.dept != null) { //
								row_tLogRow_1[5] = String.valueOf(row9.dept);

							} //

							if (row9.rnge != null) { //
								row_tLogRow_1[6] = String.valueOf(row9.rnge);

							} //

							if (row9.import_flag != null) { //
								row_tLogRow_1[7] = String.valueOf(row9.import_flag);

							} //

							if (row9.export_flag != null) { //
								row_tLogRow_1[8] = String.valueOf(row9.export_flag);

							} //

							if (row9.SUPPLIER_NUMBER != null) { //
								row_tLogRow_1[9] = String.valueOf(row9.SUPPLIER_NUMBER);

							} //

							if (row9.supplier_name != null) { //
								row_tLogRow_1[10] = String.valueOf(row9.supplier_name);

							} //

							if (row9.SUPPLIER_REF_NO != null) { //
								row_tLogRow_1[11] = String.valueOf(row9.SUPPLIER_REF_NO);

							} //

							if (row9.brand_group != null) { //
								row_tLogRow_1[12] = String.valueOf(row9.brand_group);

							} //

							if (row9.Nation_Sent != null) { //
								row_tLogRow_1[13] = String.valueOf(row9.Nation_Sent);

							} //

							if (row9.net_desp_units != null) { //
								row_tLogRow_1[14] = String.valueOf(row9.net_desp_units);

							} //

							if (row9.uk_net_desp_units != null) { //
								row_tLogRow_1[15] = String.valueOf(row9.uk_net_desp_units);

							} //

							if (row9.int_net_desp_units != null) { //
								row_tLogRow_1[16] = String.valueOf(row9.int_net_desp_units);

							} //

							if (row9.LocationCode != null) { //
								row_tLogRow_1[17] = String.valueOf(row9.LocationCode);

							} //

							if (row9.ExportFlag != null) { //
								row_tLogRow_1[18] = String.valueOf(row9.ExportFlag);

							} //

							if (row9.SalesQuantity != null) { //
								row_tLogRow_1[19] = String.valueOf(row9.SalesQuantity);

							} //

							if (row9.Audit_BatchName != null) { //
								row_tLogRow_1[20] = String.valueOf(row9.Audit_BatchName);

							} //

							if (row9.Audit_BatchName_Description != null) { //
								row_tLogRow_1[21] = String.valueOf(row9.Audit_BatchName_Description);

							} //

							if (row9.Audit_BatchNo != null) { //
								row_tLogRow_1[22] = String.valueOf(row9.Audit_BatchNo);

							} //

							if (row9.Audit_BatchTimeExecution != null) { //
								row_tLogRow_1[23] = String.valueOf(row9.Audit_BatchTimeExecution);

							} //

							if (row9.brand_indicator != null) { //
								row_tLogRow_1[24] = String.valueOf(row9.brand_indicator);

							} //

							if (row9.errorMessage != null) { //
								row_tLogRow_1[25] = String.valueOf(row9.errorMessage);

							} //

							util_tLogRow_1.addRow(row_tLogRow_1);
							nb_line_tLogRow_1++;
							log.info("tLogRow_1 - Content of row " + nb_line_tLogRow_1 + ": "
									+ TalendString.unionString("|", row_tLogRow_1));
//////

//////                    

///////////////////////    			

							tos_count_tLogRow_1++;

							/**
							 * [tLogRow_1 main ] stop
							 */

							/**
							 * [tLogRow_1 process_data_begin ] start
							 */

							s(currentComponent = "tLogRow_1");

							cLabel = "mandatory_column_failures";

							/**
							 * [tLogRow_1 process_data_begin ] stop
							 */

							/**
							 * [tLogRow_1 process_data_end ] start
							 */

							s(currentComponent = "tLogRow_1");

							cLabel = "mandatory_column_failures";

							/**
							 * [tLogRow_1 process_data_end ] stop
							 */

						} // End of branch "row9"

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
						.remove("tHashFile_JDWilliams_expanded_" + pid + "_tHashOutput_1");

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
				 * [tFileOutputDelimited_1 end ] start
				 */

				s(currentComponent = "tFileOutputDelimited_1");

				if (outtFileOutputDelimited_1 != null) {
					outtFileOutputDelimited_1.flush();
					outtFileOutputDelimited_1.close();
				}

				globalMap.put("tFileOutputDelimited_1_NB_LINE", nb_line_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);

				resourceMap.put("finish_tFileOutputDelimited_1", true);

				log.debug("tFileOutputDelimited_1 - Written records count: " + nb_line_tFileOutputDelimited_1 + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row8", 2, 0,
						"tFilterRow_30", "tFilterRow_30", "tFilterRow", "tFileOutputDelimited_1",
						"tFileOutputDelimited_1", "tFileOutputDelimited", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFileOutputDelimited_1 - " + ("Done."));

				ok_Hash.put("tFileOutputDelimited_1", true);
				end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk2", 0, "ok");
				}
				tRunJob_1Process(globalMap);

				/**
				 * [tFileOutputDelimited_1 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				s(currentComponent = "tLogRow_1");

				cLabel = "mandatory_column_failures";

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

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row9", 2, 0,
						"tFilterRow_30", "tFilterRow_30", "tFilterRow", "tLogRow_1", "mandatory_column_failures",
						"tLogRow", "reject")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_1 - " + ("Done."));

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
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
				 * [tDataStewardshipTaskOutput_1 finally ] start
				 */

				s(currentComponent = "tDataStewardshipTaskOutput_1");

// finally of generic

				if (resourceMap.get("finish_tDataStewardshipTaskOutput_1") == null) {
					if (resourceMap.get("writer_tDataStewardshipTaskOutput_1") != null) {
						try {
							((org.talend.components.api.component.runtime.Writer) resourceMap
									.get("writer_tDataStewardshipTaskOutput_1")).close();
						} catch (java.io.IOException e_tDataStewardshipTaskOutput_1) {
							String errorMessage_tDataStewardshipTaskOutput_1 = "failed to release the resource in tDataStewardshipTaskOutput_1 :"
									+ e_tDataStewardshipTaskOutput_1.getMessage();
							System.err.println(errorMessage_tDataStewardshipTaskOutput_1);
						}
					}
				}

				/**
				 * [tDataStewardshipTaskOutput_1 finally ] stop
				 */

				/**
				 * [tLogRow_2 finally ] start
				 */

				s(currentComponent = "tLogRow_2");

				cLabel = "sales_number_rejects";

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
				 * [tFileOutputDelimited_1 finally ] start
				 */

				s(currentComponent = "tFileOutputDelimited_1");

				if (resourceMap.get("finish_tFileOutputDelimited_1") == null) {

					java.io.Writer outtFileOutputDelimited_1 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_1");
					if (outtFileOutputDelimited_1 != null) {
						outtFileOutputDelimited_1.flush();
						outtFileOutputDelimited_1.close();
					}

				}

				/**
				 * [tFileOutputDelimited_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				s(currentComponent = "tLogRow_1");

				cLabel = "mandatory_column_failures";

				/**
				 * [tLogRow_1 finally ] stop
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

	public void tRunJob_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tRunJob_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tRunJob_1", "vKWFGL_");

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
				 * [tRunJob_1 begin ] start
				 */

				sh("tRunJob_1");

				s(currentComponent = "tRunJob_1");

				int tos_count_tRunJob_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tRunJob_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tRunJob_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tRunJob_1 = new StringBuilder();
							log4jParamters_tRunJob_1.append("Parameters:");
							log4jParamters_tRunJob_1.append("USE_DYNAMIC_JOB" + " = " + "false");
							log4jParamters_tRunJob_1.append(" | ");
							log4jParamters_tRunJob_1.append("PROCESS" + " = " + "JDWilliams_dynamic_columns");
							log4jParamters_tRunJob_1.append(" | ");
							log4jParamters_tRunJob_1.append("USE_INDEPENDENT_PROCESS" + " = " + "true");
							log4jParamters_tRunJob_1.append(" | ");
							log4jParamters_tRunJob_1.append("DIE_ON_CHILD_ERROR" + " = " + "true");
							log4jParamters_tRunJob_1.append(" | ");
							log4jParamters_tRunJob_1.append("TRANSMIT_WHOLE_CONTEXT" + " = " + "false");
							log4jParamters_tRunJob_1.append(" | ");
							log4jParamters_tRunJob_1.append("CONTEXTPARAMS" + " = " + "[]");
							log4jParamters_tRunJob_1.append(" | ");
							log4jParamters_tRunJob_1.append("PRINT_PARAMETER" + " = " + "false");
							log4jParamters_tRunJob_1.append(" | ");
							log4jParamters_tRunJob_1.append("USE_CHILD_JVM_SETTING" + " = " + "true");
							log4jParamters_tRunJob_1.append(" | ");
							log4jParamters_tRunJob_1.append("USE_CUSTOM_JVM_SETTING" + " = " + "false");
							log4jParamters_tRunJob_1.append(" | ");
							log4jParamters_tRunJob_1.append("USE_DYNAMIC_CONTEXT" + " = " + "false");
							log4jParamters_tRunJob_1.append(" | ");
							log4jParamters_tRunJob_1.append("USE_EXTRA_CLASSPATH" + " = " + "false");
							log4jParamters_tRunJob_1.append(" | ");
							log4jParamters_tRunJob_1.append("LOAD_CONTEXT_FROM_FILE" + " = " + "false");
							log4jParamters_tRunJob_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tRunJob_1 - " + (log4jParamters_tRunJob_1));
						}
					}
					new BytesLimit65535_tRunJob_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tRunJob_1", "tRunJob_1", "tRunJob");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				class DealChildJobLibrary_tRunJob_1 {

					public String replaceJarPathsFromCrcMap(String originalClassPathLine) throws java.lang.Exception {
						originalClassPathLine = enableJavaAgentIfNeed(originalClassPathLine);

						String classPathLine = "";
						String crcMapPath = new java.io.File("../crcMap").getCanonicalPath();
						if (isNeedAddLibsPath(crcMapPath)) {
							java.util.Map<String, String> crcMap = null;
							java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
									new java.io.FileInputStream(crcMapPath)) {
								@Override
								public Class<?> resolveClass(java.io.ObjectStreamClass desc)
										throws java.io.IOException, ClassNotFoundException {
									if (!"java.util.HashMap".equals(desc.getName())) {
										throw new java.io.InvalidClassException(
												"Unauthorized deserialization attempt : " + desc.getName());
									}
									return super.resolveClass(desc);
								}
							};
							crcMap = (java.util.Map<String, String>) ois.readObject();
							ois.close();
							classPathLine = addLibsPath(originalClassPathLine, crcMap);
						} else {
							classPathLine = originalClassPathLine;
						}
						return classPathLine;
					}

					private String enableJavaAgentIfNeed(String origin) {
						boolean withTalendAgent = Boolean.getBoolean("talendagent.enable");
						if (withTalendAgent && origin != null) {
							if (origin.startsWith("-Djavaagent:")) {
								StringBuilder strBuilder = new StringBuilder(origin.length());
								strBuilder.append("-").append(origin, 2, origin.length());
								return strBuilder.toString();
							}
						}
						return origin;
					}

					private boolean isNeedAddLibsPath(String crcMapPath) {
						if (!(new java.io.File(crcMapPath).exists())) {// when not use cache
							return false;
						}
						return true;
					}

					private String addLibsPath(String line, java.util.Map<String, String> crcMap) {
						for (java.util.Map.Entry<String, String> entry : crcMap.entrySet()) {
							line = adaptLibPaths(line, entry);
						}
						return line;
					}

					private String adaptLibPaths(String line, java.util.Map.Entry<String, String> entry) {
						String jarName = entry.getValue();
						String crc = entry.getKey();
						String libStringFinder = "../lib/" + jarName;
						if (line.contains(libStringFinder)) {
							line = line.replace(libStringFinder, "../../../cache/lib/" + crc + "/" + jarName);
						} else if (line.contains(":$ROOT_PATH/" + jarName + ":")) {
							line = line.replace(":$ROOT_PATH/" + jarName + ":",
									":$ROOT_PATH/../../../cache/lib/" + crc + "/" + jarName + ":");
						} else if (line.contains(";" + jarName + ";")) {
							line = line.replace(";" + jarName + ";",
									";../../../cache/lib/" + crc + "/" + jarName + ";");
						}
						return line;
					}

				}
				DealChildJobLibrary_tRunJob_1 dealChildJobLibrary_tRunJob_1 = new DealChildJobLibrary_tRunJob_1();

				class JVMArgumentHelper_tRunJob_1 {

					private void addClasspath(java.util.List<String> target_argument_list,
							String job_origin_classpath) {

						String extra_classpath = null;
						String path_separator = System.getProperty("path.separator");
						if (path_separator != null && path_separator.length() > 1) {
							throw new RuntimeException("path separator should be single character");
						}

						if (extra_classpath != null && !extra_classpath.isEmpty()) {
							if (extra_classpath.endsWith(path_separator)) {
								target_argument_list.add(extra_classpath + job_origin_classpath);
							} else if (extra_classpath.contains(path_separator)) {
								target_argument_list
										.add(concatStr(extra_classpath, path_separator, job_origin_classpath));
							} else if (extra_classpath.endsWith(":")) {
								target_argument_list
										.add(extra_classpath.replace(":", path_separator) + job_origin_classpath);
							} else if (extra_classpath.endsWith(";")) {
								target_argument_list
										.add(extra_classpath.replace(";", path_separator) + job_origin_classpath);
							} else if (extra_classpath.contains(":")) {
								target_argument_list.add(concatStr(extra_classpath.replace(":", path_separator),
										path_separator, job_origin_classpath));
							} else if (extra_classpath.contains(";")) {
								target_argument_list.add(concatStr(extra_classpath.replace(";", path_separator),
										path_separator, job_origin_classpath));
							} else {
								target_argument_list
										.add(concatStr(extra_classpath, path_separator, job_origin_classpath));
							}
							return;
						}

						target_argument_list.add(job_origin_classpath);
					}

					private String concatStr(String s1, String s2, String s3) {
						java.lang.StringBuilder strB = new java.lang.StringBuilder();
						strB.append(s1).append(s2).append(s3);
						return strB.toString();
					}

					public void addArgumentsTo(java.util.List<String> target_argument_list,
							String argument_from_child) {
						addArgumentsTo(target_argument_list, argument_from_child, false);
					}

					public void addArgumentsTo(java.util.List<String> target_argument_list, String argument_from_child,
							boolean isCP) {
						if (isCP) {
							addClasspath(target_argument_list, argument_from_child);
							return;
						}

						target_argument_list.add(argument_from_child);

					}

				}

				JVMArgumentHelper_tRunJob_1 jvm_argument_helper_tRunJob_1 = new JVMArgumentHelper_tRunJob_1();

				String audit_jar_path_tRunJob_1 = System.getProperty("classpath.extended");

				/**
				 * [tRunJob_1 begin ] stop
				 */

				/**
				 * [tRunJob_1 main ] start
				 */

				s(currentComponent = "tRunJob_1");

				java.util.List<String> paraList_tRunJob_1 = new java.util.ArrayList<String>();

				String osName_tRunJob_1 = System.getProperty("os.name");
				if (osName_tRunJob_1 != null && osName_tRunJob_1.toLowerCase(java.util.Locale.US).startsWith("win")) {

					paraList_tRunJob_1.add("C:/Program Files/Java/jdk-17/bin/java.exe");
					String m2 = System.getProperty("talend.component.manager.m2.repository");
					if (m2 != null) {
						paraList_tRunJob_1.add("-Dtalend.component.manager.m2.repository=" + m2);
					}

					if (Boolean.getBoolean("propagateLoggingConfiguration")) {
						String log4j1_config_tRunJob_1 = System.getProperty("log4j.configuration");
						if (log4j1_config_tRunJob_1 != null) {
							paraList_tRunJob_1.add("-Dlog4j.configuration=" + log4j1_config_tRunJob_1);
						}
						String log4j2_config_tRunJob_1 = System.getProperty("log4j.configurationFile");
						if (log4j2_config_tRunJob_1 != null) {
							paraList_tRunJob_1.add("-Dlog4j.configurationFile=" + log4j2_config_tRunJob_1);
						}
						if (log4j1_config_tRunJob_1 != null || log4j2_config_tRunJob_1 != null) {
							paraList_tRunJob_1.add("-DpropagateLoggingConfiguration=true");
						}
					}

					if (enableLogStash) {
						System.getProperties().stringPropertyNames().stream().filter(it -> it.startsWith("audit."))
								.forEach(key -> paraList_tRunJob_1.add("-D" + key + "=" + System.getProperty(key)));
					}

					System.getProperties().stringPropertyNames().stream()
							.filter(it -> it.startsWith("runtime.lineage.") || "classpath.extended".equals(it)
									|| it.startsWith("cbp.agent."))
							.forEach(key -> paraList_tRunJob_1.add("-D" + key + "=" + System.getProperty(key)));

					org.talend.metrics.CBPClient.setExecutionIdIfNeed(paraList_tRunJob_1);

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "-Xms256M");

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "-Xmx2048M");

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "-Dfile.encoding=UTF-8");

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1,
							"-Dtalend.component.manager.m2.repository=C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository");

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "-cp");

					String classpath_tRunJob_1_6 = "C:/Temp/Talend-Studio-20240527_1700-V8.0.1/workspace/VALPAK_POC/poms/jobs/process/jdwilliams_dynamic_columns_0.1/target/classpath.jar;/C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/apache/commons/commons-lang3/3.10/commons-lang3-3.10.jar;";

					if (audit_jar_path_tRunJob_1 != null && !audit_jar_path_tRunJob_1.isEmpty()) {
						classpath_tRunJob_1_6 += audit_jar_path_tRunJob_1;
					}

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1,
							dealChildJobLibrary_tRunJob_1.replaceJarPathsFromCrcMap(classpath_tRunJob_1_6), true);

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1,
							"valpak_poc.jdwilliams_dynamic_columns_0_1.JDWilliams_dynamic_columns");

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "--father_pid=" + pid);

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "--root_pid=" + rootPid);

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "--father_node=tRunJob_1");

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "--context=Default");

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "%*");

				} else {

					paraList_tRunJob_1.add("C:/Program Files/Java/jdk-17/bin/java.exe");
					String m2 = System.getProperty("talend.component.manager.m2.repository");
					if (m2 != null) {
						paraList_tRunJob_1.add("-Dtalend.component.manager.m2.repository=" + m2);
					}

					if (Boolean.getBoolean("propagateLoggingConfiguration")) {
						String log4j1_config_tRunJob_1 = System.getProperty("log4j.configuration");
						if (log4j1_config_tRunJob_1 != null) {
							paraList_tRunJob_1.add("-Dlog4j.configuration=" + log4j1_config_tRunJob_1);
						}
						String log4j2_config_tRunJob_1 = System.getProperty("log4j.configurationFile");
						if (log4j2_config_tRunJob_1 != null) {
							paraList_tRunJob_1.add("-Dlog4j.configurationFile=" + log4j2_config_tRunJob_1);
						}
						if (log4j1_config_tRunJob_1 != null || log4j2_config_tRunJob_1 != null) {
							paraList_tRunJob_1.add("-DpropagateLoggingConfiguration=true");
						}
					}

					if (enableLogStash) {
						System.getProperties().stringPropertyNames().stream().filter(it -> it.startsWith("audit."))
								.forEach(key -> paraList_tRunJob_1.add("-D" + key + "=" + System.getProperty(key)));
					}

					System.getProperties().stringPropertyNames().stream()
							.filter(it -> it.startsWith("runtime.lineage.") || "classpath.extended".equals(it)
									|| it.startsWith("cbp.agent."))
							.forEach(key -> paraList_tRunJob_1.add("-D" + key + "=" + System.getProperty(key)));

					org.talend.metrics.CBPClient.setExecutionIdIfNeed(paraList_tRunJob_1);

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "-Xms256M");

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "-Xmx2048M");

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "-Dfile.encoding=UTF-8");

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1,
							"-Dtalend.component.manager.m2.repository=C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository");

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "-cp");

					String classpath_tRunJob_1_6 = "C:/Temp/Talend-Studio-20240527_1700-V8.0.1/workspace/VALPAK_POC/poms/jobs/process/jdwilliams_dynamic_columns_0.1/target/classes:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/workspace/VALPAK_POC/poms/jobs/process/jdwilliams_dynamic_columns_0.1/src/main/ext-resources:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/workspace/VALPAK_POC/poms/code/routines/target/classes:.:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/apache/logging/log4j/log4j-slf4j-impl/2.17.1/log4j-slf4j-impl-2.17.1.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/apache/logging/log4j/log4j-api/2.17.1/log4j-api-2.17.1.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/apache/logging/log4j/log4j-core/2.17.1/log4j-core-2.17.1.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/apache/logging/log4j/log4j-layout-template-json/2.17.1/log4j-layout-template-json-2.17.1.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/apache/logging/log4j/log4j-1.2-api/2.17.1/log4j-1.2-api-2.17.1.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/commons-collections/commons-collections/3.2.2/commons-collections-3.2.2.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/dom4j/dom4j/2.1.3/dom4j-2.1.3.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/apache/commons/commons-math3/3.6.1/commons-math3-3.6.1.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/talend/components/talend_file_enhanced/1.3.1/talend_file_enhanced-1.3.1.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/commons-io/commons-io/2.15.1/commons-io-2.15.1.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/jboss/marshalling/jboss-marshalling/2.0.12.Final/jboss-marshalling-2.0.12.Final.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/net/minidev/json-smart/2.4.11/json-smart-2.4.11.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/javassist/javassist/3.30.2-GA/javassist-3.30.2-GA.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/talend/components/lib/advancedPersistentLookupLib/1.5/advancedPersistentLookupLib-1.5.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/slf4j/slf4j-api/1.7.34/slf4j-api-1.7.34.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/talend/components/lib/job-audit/1.5/job-audit-1.5.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/apache/poi/poi/4.1.2-20200903124306_modified_talend/poi-4.1.2-20200903124306_modified_talend.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/talend/components/simpleexcel/2.9-20230830/simpleexcel-2.9-20230830.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/commons-codec/commons-codec/1.14/commons-codec-1.14.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/apache/poi/poi-ooxml-schemas/4.1.2-20200903124306_modified_talend/poi-ooxml-schemas-4.1.2-20200903124306_modified_talend.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/apache/xmlbeans/xmlbeans/3.1.0/xmlbeans-3.1.0.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/talend/system-routines-dq/8.0.1/system-routines-dq-8.0.1.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/net/minidev/accessors-smart/2.4.11/accessors-smart-2.4.11.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/antlr/antlr-runtime/3.5.2/antlr-runtime-3.5.2.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/com/zaxxer/SparseBitSet/1.2/SparseBitSet-1.2.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/talend/metrics/talendagent/1.0.2/talendagent-1.0.2.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/apache/commons/commons-collections4/4.4/commons-collections4-4.4.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/talend/daikon/audit-log4j2/1.16.1/audit-log4j2-1.16.1.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/talend/daikon/logging-event-layout/1.16.1/logging-event-layout-1.16.1.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/talend/libraries/org.talend.dataquality.parser/8.0.1/org.talend.dataquality.parser-8.0.1.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/com/github/virtuald/curvesapi/1.06/curvesapi-1.06.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/talend/metrics/talendboot/1.0.9/talendboot-1.0.9.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/ow2/asm/asm/9.5/asm-9.5.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/apache/commons/commons-lang3/3.10/commons-lang3-3.10.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/talend/system-routines/8.0.1/system-routines-8.0.1.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/apache/poi/poi-scratchpad/5.2.2/poi-scratchpad-5.2.2.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/talend/components/talendcsv/1.1.0/talendcsv-1.1.0.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/talend/daikon/crypto-utils/7.1.20/crypto-utils-7.1.20.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/talend/metrics/talendCBP/1.1.5/talendCBP-1.1.5.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/apache/poi/poi-ooxml/4.1.2-20200903124306_modified_talend/poi-ooxml-4.1.2-20200903124306_modified_talend.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/apache/commons/commons-compress/1.26.0/commons-compress-1.26.0.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/talend/daikon/audit-common/1.16.1/audit-common-1.16.1.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/org/jboss/marshalling/jboss-marshalling-river/2.0.12.Final/jboss-marshalling-river-2.0.12.Final.jar:C:/Temp/Talend-Studio-20240527_1700-V8.0.1/configuration/.m2/repository/trove/trove/1.0.2/trove-1.0.2.jar:";

					if (audit_jar_path_tRunJob_1 != null && !audit_jar_path_tRunJob_1.isEmpty()) {
						classpath_tRunJob_1_6 += audit_jar_path_tRunJob_1;
					}

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1,
							dealChildJobLibrary_tRunJob_1.replaceJarPathsFromCrcMap(classpath_tRunJob_1_6)
									.replace("$ROOT_PATH", System.getProperty("user.dir")),
							true);

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1,
							"valpak_poc.jdwilliams_dynamic_columns_0_1.JDWilliams_dynamic_columns");

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "--father_pid=" + pid);

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "--root_pid=" + rootPid);

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "--father_node=tRunJob_1");

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "--context=Default");

					jvm_argument_helper_tRunJob_1.addArgumentsTo(paraList_tRunJob_1, "$@");

				}

				if (!"".equals(log4jLevel)) {
					paraList_tRunJob_1.add("--log4jLevel=" + log4jLevel);
				}

				if (enableLogStash) {
					paraList_tRunJob_1.add("--audit.enabled=" + enableLogStash);
				}

				// for feature:10589

				paraList_tRunJob_1.add("--stat_port=" + null);

				if (resuming_logs_dir_path != null) {
					paraList_tRunJob_1.add("--resuming_logs_dir_path=" + resuming_logs_dir_path);
				}
				String childResumePath_tRunJob_1 = ResumeUtil.getChildJobCheckPointPath(resuming_checkpoint_path);
				String tRunJobName_tRunJob_1 = ResumeUtil.getRighttRunJob(resuming_checkpoint_path);
				if ("tRunJob_1".equals(tRunJobName_tRunJob_1) && childResumePath_tRunJob_1 != null) {
					paraList_tRunJob_1.add("--resuming_checkpoint_path="
							+ ResumeUtil.getChildJobCheckPointPath(resuming_checkpoint_path));
				}
				paraList_tRunJob_1.add("--parent_part_launcher=JOB:" + jobName + "/NODE:tRunJob_1");

				java.util.Map<String, Object> parentContextMap_tRunJob_1 = new java.util.HashMap<String, Object>();

				Object obj_tRunJob_1 = null;

				class ConsoleHelper_tRunJob_1 {
					private Thread getNormalThread(Process process) {
						return new Thread() {
							public void run() {
								try {
									java.io.BufferedReader reader = new java.io.BufferedReader(
											new java.io.InputStreamReader(process.getInputStream()));
									String line = "";
									try {
										while ((line = reader.readLine()) != null) {
											System.out.println(line);
										}
									} finally {
										reader.close();
									}
								} catch (java.io.IOException ioe) {
									globalMap.put("tRunJob_1_ERROR_MESSAGE", ioe.getMessage());

									log.error("tRunJob_1 - " + ioe.getMessage());

									ioe.printStackTrace();
								}
							}
						};
					}

					private Thread getErrorThread(Process process, StringBuffer sb) {
						return new Thread() {
							public void run() {
								try {
									java.io.BufferedReader reader = new java.io.BufferedReader(
											new java.io.InputStreamReader(process.getErrorStream()));
									String line = "";
									try {
										while ((line = reader.readLine()) != null) {
											sb.append(line).append("\n");
										}
									} finally {
										reader.close();
									}
								} catch (java.io.IOException ioe) {
									globalMap.put("tRunJob_1_ERROR_MESSAGE", ioe.getMessage());

									log.error("tRunJob_1 - " + ioe.getMessage());

									ioe.printStackTrace();
								}
							}
						};
					}
				}
				ConsoleHelper_tRunJob_1 consoleHelper_tRunJob_1 = new ConsoleHelper_tRunJob_1();

				Runtime runtime_tRunJob_1 = Runtime.getRuntime();
				Process ps_tRunJob_1 = null;

				// 0 indicates normal termination
				int result_tRunJob_1;
				StringBuffer errorMsg_tRunJob_1 = new StringBuffer();
				try {
					ps_tRunJob_1 = runtime_tRunJob_1
							.exec((String[]) paraList_tRunJob_1.toArray(new String[paraList_tRunJob_1.size()]));

					Thread normal_tRunJob_1 = consoleHelper_tRunJob_1.getNormalThread(ps_tRunJob_1);
					log.info(
							"tRunJob_1 - The child job 'JDWilliams_dynamic_columns' starts on the version '0.1' with the context 'Default'.");
					normal_tRunJob_1.start();
					log.info("tRunJob_1 - The child job 'JDWilliams_dynamic_columns' is done.");

					Thread error_tRunJob_1 = consoleHelper_tRunJob_1.getErrorThread(ps_tRunJob_1, errorMsg_tRunJob_1);
					error_tRunJob_1.start();

					result_tRunJob_1 = ps_tRunJob_1.waitFor();
					normal_tRunJob_1.join();
					error_tRunJob_1.join();
				} catch (ThreadDeath tde) {
					globalMap.put("tRunJob_1_ERROR_MESSAGE", tde.getMessage());
					log.error("tRunJob_1 - thread was terminated.");
					ps_tRunJob_1.destroy();
					throw tde;
				}

				globalMap.put("tRunJob_1_CHILD_RETURN_CODE", result_tRunJob_1);
				if (result_tRunJob_1 != 0) {
					globalMap.put("tRunJob_1_CHILD_EXCEPTION_STACKTRACE", errorMsg_tRunJob_1.toString());

					throw new RuntimeException("Child job returns " + result_tRunJob_1
							+ ". It doesn't terminate normally.\n" + errorMsg_tRunJob_1.toString());

				}

				tos_count_tRunJob_1++;

				/**
				 * [tRunJob_1 main ] stop
				 */

				/**
				 * [tRunJob_1 process_data_begin ] start
				 */

				s(currentComponent = "tRunJob_1");

				/**
				 * [tRunJob_1 process_data_begin ] stop
				 */

				/**
				 * [tRunJob_1 process_data_end ] start
				 */

				s(currentComponent = "tRunJob_1");

				/**
				 * [tRunJob_1 process_data_end ] stop
				 */

				/**
				 * [tRunJob_1 end ] start
				 */

				s(currentComponent = "tRunJob_1");

				if (log.isDebugEnabled())
					log.debug("tRunJob_1 - " + ("Done."));

				ok_Hash.put("tRunJob_1", true);
				end_Hash.put("tRunJob_1", System.currentTimeMillis());

				/**
				 * [tRunJob_1 end ] stop
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
				 * [tRunJob_1 finally ] start
				 */

				s(currentComponent = "tRunJob_1");

				/**
				 * [tRunJob_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tRunJob_1_SUBPROCESS_STATE", 1);
	}

	public static class row11Struct implements routines.system.IPersistableRow<row11Struct> {
		final static byte[] commonByteArrayLock_VALPAK_POC_JDWilliams_expanded = new byte[0];
		static byte[] commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[0];

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

		public String errorCode;

		public String getErrorCode() {
			return this.errorCode;
		}

		public Boolean errorCodeIsNullable() {
			return true;
		}

		public Boolean errorCodeIsKey() {
			return false;
		}

		public Integer errorCodeLength() {
			return 255;
		}

		public Integer errorCodePrecision() {
			return 0;
		}

		public String errorCodeDefault() {

			return "";

		}

		public String errorCodeComment() {

			return null;

		}

		public String errorCodePattern() {

			return null;

		}

		public String errorCodeOriginalDbColumnName() {

			return "errorCode";

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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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
				if (length > commonByteArray_VALPAK_POC_JDWilliams_expanded.length) {
					if (length < 1024 && commonByteArray_VALPAK_POC_JDWilliams_expanded.length == 0) {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[1024];
					} else {
						commonByteArray_VALPAK_POC_JDWilliams_expanded = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length);
				strReturn = new String(commonByteArray_VALPAK_POC_JDWilliams_expanded, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

					this.errorCode = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_VALPAK_POC_JDWilliams_expanded) {

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

					this.errorCode = readString(dis);

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

				// String

				writeString(this.errorCode, dos);

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

				// String

				writeString(this.errorCode, dos);

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
			sb.append(",errorCode=" + errorCode);
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

			if (errorCode == null) {
				sb.append("<null>");
			} else {
				sb.append(errorCode);
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

	public void tMsgBox_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tMsgBox_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tMsgBox_1", "PRN1aO_");

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

				row11Struct row11 = new row11Struct();

				/**
				 * [tDie_1 begin ] start
				 */

				sh("tDie_1");

				s(currentComponent = "tDie_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row11");

				int tos_count_tDie_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tDie_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDie_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDie_1 = new StringBuilder();
							log4jParamters_tDie_1.append("Parameters:");
							log4jParamters_tDie_1.append("MESSAGE" + " = " + "\"Schema Error\"");
							log4jParamters_tDie_1.append(" | ");
							log4jParamters_tDie_1.append("CODE" + " = " + "4");
							log4jParamters_tDie_1.append(" | ");
							log4jParamters_tDie_1.append("PRIORITY" + " = " + "5");
							log4jParamters_tDie_1.append(" | ");
							log4jParamters_tDie_1.append("EXIT_JVM" + " = " + "false");
							log4jParamters_tDie_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDie_1 - " + (log4jParamters_tDie_1));
						}
					}
					new BytesLimit65535_tDie_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDie_1", "tDie_1", "tDie");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				/**
				 * [tDie_1 begin ] stop
				 */

				/**
				 * [tMsgBox_1 begin ] start
				 */

				sh("tMsgBox_1");

				s(currentComponent = "tMsgBox_1");

				int tos_count_tMsgBox_1 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tMsgBox_1", "tMsgBox_1", "tMsgBox");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				/**
				 * [tMsgBox_1 begin ] stop
				 */

				/**
				 * [tMsgBox_1 main ] start
				 */

				s(currentComponent = "tMsgBox_1");

				int messageIcontMsgBox_1 = javax.swing.JOptionPane.WARNING_MESSAGE;
				String titletMsgBox_1 = "Talend Studio";
				String messagetMsgBox_1 = "Schema error: please check input file";
				String resulttMsgBox_1 = null;

				javax.swing.JOptionPane.showMessageDialog(null, messagetMsgBox_1, titletMsgBox_1, messageIcontMsgBox_1);
				resulttMsgBox_1 = String.valueOf(1);

				globalMap.put("tMsgBox_1_RESULT", resulttMsgBox_1);

				tos_count_tMsgBox_1++;

				/**
				 * [tMsgBox_1 main ] stop
				 */

				/**
				 * [tMsgBox_1 process_data_begin ] start
				 */

				s(currentComponent = "tMsgBox_1");

				/**
				 * [tMsgBox_1 process_data_begin ] stop
				 */

				/**
				 * [tDie_1 main ] start
				 */

				s(currentComponent = "tDie_1");

				if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

						, "row11", "tMsgBox_1", "tMsgBox_1", "tMsgBox", "tDie_1", "tDie_1", "tDie"

				)) {
					talendJobLogProcess(globalMap);
				}

				if (log.isTraceEnabled()) {
					log.trace("row11 - " + (row11 == null ? "" : row11.toLogString()));
				}

				try {
					globalMap.put("tDie_1_DIE_PRIORITY", 5);
					System.err.println("Schema Error");

					log.error("tDie_1 - The die message: " + "Schema Error");

					globalMap.put("tDie_1_DIE_MESSAGE", "Schema Error");
					globalMap.put("tDie_1_DIE_MESSAGES", "Schema Error");

				} catch (Exception | Error e_tDie_1) {
					globalMap.put("tDie_1_ERROR_MESSAGE", e_tDie_1.getMessage());
					logIgnoredError(
							String.format("tDie_1 - tDie failed to log message due to internal error: %s", e_tDie_1),
							e_tDie_1);
				}

				currentComponent = "tDie_1";
				status = "failure";
				errorCode = new Integer(4);
				globalMap.put("tDie_1_DIE_CODE", errorCode);

				if (true) {
					TDieException e_tDie_1 = new TDieException();

					if (enableLogStash) {
						talendJobLog.addJobExceptionMessage(currentComponent, cLabel, "Schema Error", e_tDie_1);
						talendJobLogProcess(globalMap);
					}

					throw e_tDie_1;
				}

				tos_count_tDie_1++;

				/**
				 * [tDie_1 main ] stop
				 */

				/**
				 * [tDie_1 process_data_begin ] start
				 */

				s(currentComponent = "tDie_1");

				/**
				 * [tDie_1 process_data_begin ] stop
				 */

				/**
				 * [tDie_1 process_data_end ] start
				 */

				s(currentComponent = "tDie_1");

				/**
				 * [tDie_1 process_data_end ] stop
				 */

				/**
				 * [tMsgBox_1 process_data_end ] start
				 */

				s(currentComponent = "tMsgBox_1");

				/**
				 * [tMsgBox_1 process_data_end ] stop
				 */

				/**
				 * [tMsgBox_1 end ] start
				 */

				s(currentComponent = "tMsgBox_1");

				ok_Hash.put("tMsgBox_1", true);
				end_Hash.put("tMsgBox_1", System.currentTimeMillis());

				/**
				 * [tMsgBox_1 end ] stop
				 */

				/**
				 * [tDie_1 end ] start
				 */

				s(currentComponent = "tDie_1");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row11", 2, 0,
						"tMsgBox_1", "tMsgBox_1", "tMsgBox", "tDie_1", "tDie_1", "tDie", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tDie_1 - " + ("Done."));

				ok_Hash.put("tDie_1", true);
				end_Hash.put("tDie_1", System.currentTimeMillis());

				/**
				 * [tDie_1 end ] stop
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
				 * [tMsgBox_1 finally ] start
				 */

				s(currentComponent = "tMsgBox_1");

				/**
				 * [tMsgBox_1 finally ] stop
				 */

				/**
				 * [tDie_1 finally ] start
				 */

				s(currentComponent = "tDie_1");

				/**
				 * [tDie_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tMsgBox_1_SUBPROCESS_STATE", 1);
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

	private final org.talend.components.common.runtime.SharedConnectionsPool connectionPool = new org.talend.components.common.runtime.SharedConnectionsPool() {
		public java.sql.Connection getDBConnection(String dbDriver, String url, String userName, String password,
				String dbConnectionName) throws ClassNotFoundException, java.sql.SQLException {
			return SharedDBConnection.getDBConnection(dbDriver, url, userName, password, dbConnectionName);
		}

		public java.sql.Connection getDBConnection(String dbDriver, String url, String dbConnectionName)
				throws ClassNotFoundException, java.sql.SQLException {
			return SharedDBConnection.getDBConnection(dbDriver, url, dbConnectionName);
		}
	};

	private static final String GLOBAL_CONNECTION_POOL_KEY = "GLOBAL_CONNECTION_POOL";

	{
		globalMap.put(GLOBAL_CONNECTION_POOL_KEY, connectionPool);
	}

	private final static java.util.Properties jobInfo = new java.util.Properties();
	private final static java.util.Map<String, String> mdcInfo = new java.util.HashMap<>();
	private final static java.util.concurrent.atomic.AtomicLong subJobPidCounter = new java.util.concurrent.atomic.AtomicLong();

	public static void main(String[] args) {
		final JDWilliams_expanded JDWilliams_expandedClass = new JDWilliams_expanded();

		int exitCode = JDWilliams_expandedClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'JDWilliams_expanded' - Done.");
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
		log.info("TalendJob: 'JDWilliams_expanded' - Start.");

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
		org.slf4j.MDC.put("_jobRepositoryId", "_i6-7oLL7Ee-CDa9ighBWHg");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2024-12-16T16:14:18.999810600Z");

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
			java.io.InputStream inContext = JDWilliams_expanded.class.getClassLoader()
					.getResourceAsStream("valpak_poc/jdwilliams_expanded_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = JDWilliams_expanded.class.getClassLoader()
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
		log.info("TalendJob: 'JDWilliams_expanded' - Started.");
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

		try {
			errorCode = null;
			tPrejob_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tPrejob_1) {
			globalMap.put("tPrejob_1_SUBPROCESS_STATE", -1);

			e_tPrejob_1.printStackTrace();

		}

		if (enableLogStash) {
			talendJobLog.addJobStartMessage();
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		this.globalResumeTicket = false;// to run others jobs

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : JDWilliams_expanded");
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
		log.info("TalendJob: 'JDWilliams_expanded' - Finished - status: " + status + " returnCode: " + returnCode);

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
 * 1008792 characters generated by Talend Cloud Data Fabric on the December 16,
 * 2024 at 4:14:19 PM UTC
 ************************************************************************************************/