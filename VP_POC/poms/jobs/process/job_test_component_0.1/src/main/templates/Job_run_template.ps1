$fileDir = Split-Path -Parent $MyInvocation.MyCommand.Path
cd $fileDir
java '-Dtalend.component.manager.m2.repository=%cd%/../lib' '-Xms256M' '-Xmx1024M' '-Dfile.encoding=UTF-8' -cp '.;../lib/routines.jar;../lib/log4j-slf4j-impl-2.17.1.jar;../lib/log4j-api-2.17.1.jar;../lib/log4j-core-2.17.1.jar;../lib/log4j-layout-template-json-2.17.1.jar;../lib/log4j-1.2-api-2.17.1.jar;../lib/commons-collections-3.2.2.jar;../lib/talend_file_enhanced-1.3.1.jar;../lib/jboss-marshalling-2.0.12.Final.jar;../lib/json-smart-2.4.11.jar;../lib/javassist-3.30.2-GA.jar;../lib/advancedPersistentLookupLib-1.5.jar;../lib/dom4j-2.1.3.jar;../lib/slf4j-api-1.7.34.jar;../lib/job-audit-1.5.jar;../lib/system-routines-dq.jar;../lib/accessors-smart-2.4.11.jar;../lib/antlr-runtime-3.5.2.jar;../lib/talendagent-1.0.2.jar;../lib/audit-log4j2-1.16.1.jar;../lib/logging-event-layout-1.16.1.jar;../lib/org.talend.dataquality.parser.jar;../lib/talendboot-1.0.9.jar;../lib/asm-9.5.jar;../lib/commons-lang3-3.10.jar;../lib/system-routines.jar;../lib/talendcsv-1.1.0.jar;../lib/crypto-utils-7.1.20.jar;../lib/talendCBP-1.1.5.jar;../lib/audit-common-1.16.1.jar;../lib/jboss-marshalling-river-2.0.12.Final.jar;../lib/trove-1.0.2.jar;job_test_component_0_1.jar;' valpak_poc.job_test_component_0_1.job_test_component $args
