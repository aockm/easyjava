package com.tockm.bean;

import com.tockm.utils.JsonUtils;
import com.tockm.utils.PropertiesUtils;

public class Constants {
    public static Boolean IGNORE_TABLE_PREFIX;
    public static String SUFFIX_BEAN_PARAM;
    public static String IGNORE_BEAN_TOJSON_FIELD;
    public static String IGNORE_BEAN_TOJSON_EXPRESSION;
    public static String IGNORE_BEAN_TOJSON_CLASS;
    public static String BEAN_DATE_FORMAT_EXPRESSION;
    public static String BEAN_DATE_FORMAT_CLASS;
    public static String BEAN_DATE_UNFORMAT_EXPRESSION;
    public static String BEAN_DATE_UNFORMAT_CLASS;


    public static String PATH_BASE;
    public static String PATH_JAVA = "java";
    public static String PATH_RESOURCES = "resources";
    public static String PACKAGE_BASE;
    public static String PATH_PO;
    public static String PACKAGE_PO;

    static {

        IGNORE_BEAN_TOJSON_FIELD = PropertiesUtils.getProperty("ignore.bean.tojson.field");
        IGNORE_BEAN_TOJSON_EXPRESSION =PropertiesUtils.getProperty("ignore.bean.tojson.expression");
        IGNORE_BEAN_TOJSON_CLASS =PropertiesUtils.getProperty("ignore.bean.tojson.class");
        BEAN_DATE_FORMAT_EXPRESSION =PropertiesUtils.getProperty("bean.date.format.expression");
        BEAN_DATE_FORMAT_CLASS =PropertiesUtils.getProperty("bean.date.format.class");
        BEAN_DATE_UNFORMAT_EXPRESSION = PropertiesUtils.getProperty("bean.date.unformat.expression");
        BEAN_DATE_UNFORMAT_CLASS =PropertiesUtils.getProperty("bean.date.unformat.class");
        IGNORE_TABLE_PREFIX = Boolean.valueOf(PropertiesUtils.getProperty("ignore.table.prefix"));
        SUFFIX_BEAN_PARAM = PropertiesUtils.getProperty("suffix.bean.param");
        PATH_BASE = PropertiesUtils.getProperty("path.base");

        PACKAGE_BASE = PropertiesUtils.getProperty("package.base");
        PATH_BASE = PATH_BASE + "/"+ PATH_JAVA +"/" + PropertiesUtils.getProperty("package.base");
        PATH_BASE = PATH_BASE.replace('.', '/');

        PATH_PO = PATH_BASE + "/"+ PropertiesUtils.getProperty("package.po").replace('.', '/');
        PACKAGE_PO = PACKAGE_BASE + "."+ PropertiesUtils.getProperty("package.po");
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
