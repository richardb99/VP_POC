#!/bin/sh
cd `dirname $0`
ROOT_PATH=`pwd`
java -Dtalend.component.manager.m2.repository=$ROOT_PATH/../lib -Xms256M -Xmx1024M -Dfile.encoding=UTF-8 -cp .:$ROOT_PATH:$ROOT_PATH/../lib/routines.jar:$ROOT_PATH/../lib/log4j-slf4j-impl-2.17.1.jar:$ROOT_PATH/../lib/log4j-api-2.17.1.jar:$ROOT_PATH/../lib/log4j-core-2.17.1.jar:$ROOT_PATH/../lib/log4j-layout-template-json-2.17.1.jar:$ROOT_PATH/../lib/talend_file_enhanced-1.3.1.jar:$ROOT_PATH/../lib/org.talend.dataquality.datamasking-9.4.8.jar:$ROOT_PATH/../lib/json-smart-2.4.11.jar:$ROOT_PATH/../lib/ezmorph-1.0.6.jar:$ROOT_PATH/../lib/job-audit-1.5.jar:$ROOT_PATH/../lib/commons-collections-3.2.2.jar:$ROOT_PATH/../lib/system-routines-dq.jar:$ROOT_PATH/../lib/org.talend.dataquality.semantic.model-9.4.8.jar:$ROOT_PATH/../lib/jackson-core-2.16.0.jar:$ROOT_PATH/../lib/org.talend.dataquality.record.linkage-9.2.8.jar:$ROOT_PATH/../lib/org.talend.dataquality.parser.jar:$ROOT_PATH/../lib/lucene-suggest-8.11.2.jar:$ROOT_PATH/../lib/talendboot-1.0.9.jar:$ROOT_PATH/../lib/rules-1733491391115.jar:$ROOT_PATH/../lib/dq-rule-runtime-1.0.15.jar:$ROOT_PATH/../lib/commons-lang3-3.10.jar:$ROOT_PATH/../lib/libphonenumber-8.12.3.jar:$ROOT_PATH/../lib/commons-beanutils-1.9.4.jar:$ROOT_PATH/../lib/crypto-utils-7.1.20.jar:$ROOT_PATH/../lib/audit-common-1.16.1.jar:$ROOT_PATH/../lib/commons-logging-1.2.jar:$ROOT_PATH/../lib/jboss-marshalling-2.0.12.Final.jar:$ROOT_PATH/../lib/org.talend.dataquality.phone-9.2.8.jar:$ROOT_PATH/../lib/javassist-3.30.2-GA.jar:$ROOT_PATH/../lib/org.talend.dataquality.common-9.2.8.jar:$ROOT_PATH/../lib/dom4j-2.1.3.jar:$ROOT_PATH/../lib/slf4j-api-1.7.34.jar:$ROOT_PATH/../lib/jackson-annotations-2.16.0.jar:$ROOT_PATH/../lib/re2j-1.6.jar:$ROOT_PATH/../lib/spring-core-5.3.33.jar:$ROOT_PATH/../lib/accessors-smart-2.4.11.jar:$ROOT_PATH/../lib/antlr-runtime-3.5.2.jar:$ROOT_PATH/../lib/commons-text-1.10.0.jar:$ROOT_PATH/../lib/lucene-backward-codecs-8.11.2.jar:$ROOT_PATH/../lib/talendagent-1.0.2.jar:$ROOT_PATH/../lib/commons-lang-2.6.jar:$ROOT_PATH/../lib/audit-log4j2-1.16.1.jar:$ROOT_PATH/../lib/lucene-core-8.11.2.jar:$ROOT_PATH/../lib/guava-32.1.2-jre.jar:$ROOT_PATH/../lib/json-lib-2.4.6-talend.jar:$ROOT_PATH/../lib/logging-event-layout-1.16.1.jar:$ROOT_PATH/../lib/asm-9.5.jar:$ROOT_PATH/../lib/org.talend.dataquality.semantic-9.4.8.jar:$ROOT_PATH/../lib/system-routines.jar:$ROOT_PATH/../lib/jackson-databind-2.16.0.jar:$ROOT_PATH/../lib/org.talend.dataquality.statistics-9.2.8.jar:$ROOT_PATH/../lib/lucene-analyzers-common-8.11.2.jar:$ROOT_PATH/../lib/talendcsv-1.1.0.jar:$ROOT_PATH/../lib/talendCBP-1.1.5.jar:$ROOT_PATH/mandatorycolumns_0_1.jar: valpak_poc.mandatorycolumns_0_1.MandatoryColumns "$@"