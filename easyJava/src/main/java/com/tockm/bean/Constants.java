package com.tockm.bean;

import com.tockm.utils.JsonUtils;
import com.tockm.utils.PropertiesUtils;

public class Constants {
    public static Boolean IGNORE_TABLE_PREFIX;
    public static String SUFFIX_BEAN_QUERY;
    public static String IGNORE_BEAN_TOJSON_FIELD;
    public static String IGNORE_BEAN_TOJSON_EXPRESSION;
    public static String IGNORE_BEAN_TOJSON_CLASS;
    public static String BEAN_DATE_FORMAT_EXPRESSION;
    public static String BEAN_DATE_FORMAT_CLASS;
    public static String BEAN_DATE_UNFORMAT_EXPRESSION;
    public static String BEAN_DATE_UNFORMAT_CLASS;
    // 模糊搜索
    public static String SUFFIX_BEAN_QUERY_FUZZY;
    public static String SUFFIX_MAPPERS;
    public static String SUFFIX_BEAN_QUERY_TIME_START;
    public static String SUFFIX_BEAN_QUERY_TIME_END;
    public static String PATH_BASE;
    public static String PATH_JAVA_NAME = "java";
    public static String PATH_RESOURCES_NAME = "resources";
    public static String PACKAGE_BASE;
    public static String PACKAGE_PO;
    public static String PACKAGE_VO;
    public static String PACKAGE_QUERY;
    public static String PACKAGE_UTILS;
    public static String PACKAGE_MAPPER;
    public static String PACKAGE_CONTROLLER;
    public static String PACKAGE_EXCEPTION;
    public static String PACKAGE_SERVICE;
    public static String PACKAGE_SERVICE_IMPL   ;
    public static String PACKAGE_ENUMS;
    public static String PATH_JAVA;
    public static String PATH_PO;
    public static String PATH_VO;
    public static String PATH_QUERY;
    public static String PATH_UTILS;
    public static String PATH_ENUMS;
    public static String PATH_MAPPER;
    public static String PATH_CONTROLLER;
    public static String PATH_EXCEPTION;
    public static String PATH_MAPPER_XML;
    public static String PATH_SERVICE;
    public static String PATH_SERVICE_IMPL;


    static {

        IGNORE_BEAN_TOJSON_FIELD = PropertiesUtils.getProperty("ignore.bean.tojson.field");
        IGNORE_BEAN_TOJSON_EXPRESSION =PropertiesUtils.getProperty("ignore.bean.tojson.expression");
        IGNORE_BEAN_TOJSON_CLASS =PropertiesUtils.getProperty("ignore.bean.tojson.class");
        BEAN_DATE_FORMAT_EXPRESSION =PropertiesUtils.getProperty("bean.date.format.expression");
        BEAN_DATE_FORMAT_CLASS =PropertiesUtils.getProperty("bean.date.format.class");
        BEAN_DATE_UNFORMAT_EXPRESSION = PropertiesUtils.getProperty("bean.date.unformat.expression");
        BEAN_DATE_UNFORMAT_CLASS =PropertiesUtils.getProperty("bean.date.unformat.class");
        IGNORE_TABLE_PREFIX = Boolean.valueOf(PropertiesUtils.getProperty("ignore.table.prefix"));
        SUFFIX_BEAN_QUERY = PropertiesUtils.getProperty("suffix.bean.query");
        SUFFIX_MAPPERS = PropertiesUtils.getProperty("suffix.mappers");
        SUFFIX_BEAN_QUERY_FUZZY = PropertiesUtils.getProperty("suffix.bean.query.fuzzy");
        SUFFIX_BEAN_QUERY_TIME_START = PropertiesUtils.getProperty("suffix.bean.query.time.start");
        SUFFIX_BEAN_QUERY_TIME_END = PropertiesUtils.getProperty("suffix.bean.query.time.end");
        PATH_BASE = PropertiesUtils.getProperty("path.base");

        PACKAGE_BASE = PropertiesUtils.getProperty("package.base");
        PATH_JAVA = PATH_BASE + "/"+ PATH_JAVA_NAME +"/" + PropertiesUtils.getProperty("package.base");
        PATH_JAVA = PATH_JAVA.replace('.', '/');


        PATH_PO = PATH_JAVA + "/"+ PropertiesUtils.getProperty("package.po").replace('.', '/');
        PATH_VO = PATH_JAVA + "/"+ PropertiesUtils.getProperty("package.vo").replace('.', '/');
        PATH_QUERY = PATH_JAVA + "/"+ PropertiesUtils.getProperty("package.query").replace('.', '/');
        PATH_UTILS = PATH_JAVA + "/"+ PropertiesUtils.getProperty("package.utils").replace('.', '/');
        PATH_ENUMS = PATH_JAVA + "/"+ PropertiesUtils.getProperty("package.enums").replace('.', '/');
        PATH_MAPPER = PATH_JAVA + "/"+ PropertiesUtils.getProperty("package.mappers").replace('.', '/');
        PATH_CONTROLLER = PATH_JAVA + "/"+ PropertiesUtils.getProperty("package.controller").replace('.', '/');
        PATH_EXCEPTION = PATH_JAVA + "/"+ PropertiesUtils.getProperty("package.exception").replace('.', '/');
        PATH_SERVICE = PATH_JAVA + "/"+ PropertiesUtils.getProperty("package.service").replace('.', '/');
        PATH_SERVICE_IMPL = PATH_JAVA + "/"+ PropertiesUtils.getProperty("package.service.impl").replace('.', '/');
        PACKAGE_PO = PACKAGE_BASE + "."+ PropertiesUtils.getProperty("package.po");
        PACKAGE_VO = PACKAGE_BASE + "."+ PropertiesUtils.getProperty("package.vo");
        PACKAGE_QUERY = PACKAGE_BASE + "."+ PropertiesUtils.getProperty("package.query");
        PACKAGE_UTILS = PACKAGE_BASE + "."+ PropertiesUtils.getProperty("package.utils");
        PACKAGE_ENUMS = PACKAGE_BASE + "."+ PropertiesUtils.getProperty("package.enums");
        PACKAGE_MAPPER = PACKAGE_BASE + "."+ PropertiesUtils.getProperty("package.mappers");
        PACKAGE_CONTROLLER = PACKAGE_BASE + "."+ PropertiesUtils.getProperty("package.controller");
        PACKAGE_EXCEPTION = PACKAGE_BASE + "."+ PropertiesUtils.getProperty("package.exception");
        PACKAGE_SERVICE = PACKAGE_BASE + "."+ PropertiesUtils.getProperty("package.service");
        PACKAGE_SERVICE_IMPL = PACKAGE_BASE + "."+ PropertiesUtils.getProperty("package.service.impl");

        PATH_MAPPER_XML = PropertiesUtils.getProperty("path.base")+"/"+PATH_RESOURCES_NAME+"/"+PACKAGE_MAPPER.replace(".","/");
        PATH_MAPPER_XML = PATH_MAPPER_XML.replace('.', '/');
    }

    public final static String[] SQL_DATE_TIME_TYPE = new String[]{"datetime", "timestamp"};
    public final static String[] SQL_DATE_TYPE = new String[]{"date"};
    public final static String[] SQL_DECIMAL_TYPE = new String[]{"decimal", "double", "float"};
    public final static String[] SQL_STRING_TYPE = new String[]{"char", "varchar", "text", "mediumtext", "longtext"};

    public final static String[] SQL_INTEGER_TYPE = new String[]{"int", "tinyint"};
    public final static String[] SQL_LONG_TYPE = new String[]{"bigint"};

    public static void main(String[] args) {
        System.out.println(PACKAGE_PO);
    }
}
