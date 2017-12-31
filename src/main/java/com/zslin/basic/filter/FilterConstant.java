package com.zslin.basic.filter;

/**
 * 筛选功能的常量，把每一个表单的name用这个来封装
 * FILTERPRE_PRE:aa_NAME_STR|NUM|DATEY|DATEM|DATAE_EQ|LT|GT|LE|GE|NOT|LIKEB|LIKEE|LIKEBE|SCOPE:S
 * 
 * FILTERTYPE:以这个开头都是需要处理的过滤表单
 * 
 * PRE:aa:表示前缀为aa
 * 
 * NAME:表示字段名称
 * 
 * STR|NUM|DATEY|DATEM|DATEM:查询类型，
 * STR:字符串，NUM:数字，
 * DATAY:日期按年查询，DATAM:日期按月查询，DATED:日期按日查，DATEH:按小时，DATEMI:按分钟，DATES:按照秒
 * 
 * EQ|LT|....:操作类型
 * EQ|LE|GE|LT|GT:=,<=,>=,>,<
 * NOT:二选一,值的格式必须固定:yes:xx|no:xx-->当前缀为yes时用等于（=），当前缀为no时用不等于(!=)
 * LIKEB|LIKEE|LIKEBE:'%xx','xx%','%xx%'
 * SCOPE:区间
 * @author KongHao
 *
 */
public class FilterConstant {
	/**
	 * 筛选字段的总前缀
	 */
	public final static String FILTER_PRE = "FILTERTYPE";
	
	/**
	 * 操作符类型
	 */
	public final static String OPER_EQ = "EQ";
	public final static String OPER_LE = "LE";
	public final static String OPER_GE = "GE";
	public final static String OPER_LT = "LT";
	public final static String OPER_GT = "GT";
	public final static String OPER_NOT = "NOT";
	public final static String OPER_LIKE_B = "LIKEB"; //like begin
	public final static String OPER_LIKE_E = "LIKEE"; //like end
	public final static String OPER_LIKE_BE = "LIKEBE"; //like begin  end
	public final static String OPER_SCOPE = "SCOPE";//scope类型
	public final static String OPER_SCOPE_E = "SCOPEE";//等于的scope类型
	public final static String OPER_SCOPE_NE = "SCOPENE";//不等于的scope类型
	
	/**
	 * 操作数类型
	 */
	public final static String DATA_NUM = "NUM";
	public final static String DATA_STRING = "STR";
	public final static String DATA_DATE_Y = "DATEY";
	public final static String DATA_DATE_M = "DATEM";
	public final static String DATA_DATE_D = "DATED";
	public final static String DATA_DATE_H = "DATEH";
	public final static String DATA_DATE_MI = "DATEMI";
	public final static String DATA_DATE_S = "DATES";
	
	/**
	 * 表单类型
	 */
	public final static String FORM_TEXT = "TEXT";
	public final static String FORM_SELECT = "SELECT";
}
