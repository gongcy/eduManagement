package com.zslin.basic.filter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.zslin.basic.common.PlatformCommonKit;

/**
 * 系统实体类条件生成器
 * @author KongHao
 *
 */
@SuppressWarnings("unchecked")
public class FilterEntityConditionGenerator {
	
	public static String generateQuery(HttpServletRequest req) {
		Map<String,String> maps = generateConMap(req);
		return generateConSql(maps);
	}
	/**
	 * 根据map获取条件的map参数
	 * @param req
	 * @return
	 */
	public static Map<String,String> generateConMap(HttpServletRequest req) {
		Enumeration<String> en = req.getParameterNames();
		Map<String,String> maps = new HashMap<String,String>();
		while(en.hasMoreElements()) {
			String name = en.nextElement();
			if(name.startsWith(FilterConstant.FILTER_PRE)) {
				maps.put(name, req.getParameter(name));
			}
		}
		req.setAttribute("filter_params", maps);
		return maps;
	}
	
	public static String generateConSql(Map<String,String> maps) {
		StringBuffer sqlBuffer = new StringBuffer();
		Set<String> keys = maps.keySet();
		String[] cons = null;
		for(String key:keys) {
			String value = maps.get(key);
			sqlBuffer.append(" and ");
			cons = key.split("_");
			String pre = generatePre(cons[1]);
			String name = cons[2];
			String type = cons[3];
			if(type.startsWith("DATE")) {
				//如果是日期类型
				sqlBuffer.append(generateDate(pre,name,type));
			} else {
				if(!PlatformCommonKit.isEmpty(pre)) {
					sqlBuffer.append(pre).append(".");
				}
				sqlBuffer.append(name);
			}
			
			sqlBuffer.append(generateOperator(cons[4],value));
		}
		return sqlBuffer.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(generateDate("ab", "bmsj", FilterConstant.DATA_DATE_M));
		
	}
	
	
	private static String generateDate(String pre, String name, String type) {
		/*
		 * concat(year(xx),'-',month(xx),'-',day(xx),' ',hour(xx),':',minute(xx),':',second(xx))
		 * 
		 * DATE_FORMAT(bmsj, '%Y-%m-%d %H:%i:%s') 
		 */
		StringBuffer sb = new StringBuffer();
		if(!PlatformCommonKit.isEmpty(pre)) name = pre+"."+name;
		sb.append("date_format(").append(name);
//		sb.append("concat(");
		if(type.equals(FilterConstant.DATA_DATE_Y)) {
//			concatYear(sb, name);
			sb.append(",'%Y'");
		}
		if(type.equals(FilterConstant.DATA_DATE_M)) {
			sb.append(",'%Y-%m'");
//			concatMonth(sb,name);
		}
		if(type.equals(FilterConstant.DATA_DATE_D)) {
			sb.append(",'%Y-%m-%d'");
//			concatDay(sb,name);
		}
		if(type.equals(FilterConstant.DATA_DATE_H)) {
			sb.append(",'%Y-%m-%d %H'");
//			concatHour(sb,name);
		}
		if(type.equals(FilterConstant.DATA_DATE_MI)) {
			sb.append(",'%Y-%m-%d %H:%i'");
//			concatMinute(sb,name);
		}
		if(type.equals(FilterConstant.DATA_DATE_S)) {
			sb.append(",'%Y-%m-%d %H:%i:%s'");
//			concatSecond(sb,name);
		}
		sb.append(")");
		return sb.toString();
	}
	
//	private static StringBuffer concatSecond(StringBuffer sb, String name) {
//		return concatMinute(sb, name).append(",':',").append("second(").append(name).append(")");
//	}
//
//	private static StringBuffer concatMinute(StringBuffer sb, String name) {
//		return concatHour(sb, name).append(",':',").append("minute(").append(name).append(")");
//	}
//
//	private static StringBuffer concatHour(StringBuffer sb, String name) {
//		return concatDay(sb, name).append(",' ',").append("hour(").append(name).append(")");
//	}
//
//	private static StringBuffer concatDay(StringBuffer sb, String name) {
//		return concatMonth(sb, name).append(",'-',").append("day(").append(name).append(")");
//	}
//
//	private static StringBuffer concatYear(StringBuffer sb,String name) {
//		return sb.append("year(").append(name).append(")");
//	}
//
//	private static StringBuffer concatMonth(StringBuffer sb, String name) {
//		return concatYear(sb, name).append(",'-',").append("month(").append(name).append(")");
//	}

	private static String generateOperator(String oper,String value) {
		StringBuffer sb = new StringBuffer();
		boolean isNum = oper.equals("NUM");
		if(oper.equals(FilterConstant.OPER_EQ)) {
			sb.append("=").append(generateVal(value,isNum));
		} else if(oper.equals(FilterConstant.OPER_LT)) {
			sb.append("<").append(generateVal(value, isNum));
		} else if(oper.equals(FilterConstant.OPER_GT)) {
			sb.append(">").append(generateVal(value, isNum));
		} else if(oper.equals(FilterConstant.OPER_LE)){
			sb.append(">=").append(generateVal(value, isNum));
		} else if(oper.equals(FilterConstant.OPER_GE)) {
			sb.append("<=").append(generateVal(value, isNum));
		} else if(oper.equals(FilterConstant.OPER_NOT)) {
			String[] vs = value.split(":");
			if(vs[0].equals("yes")) {
				sb.append("=");
			} else {
				sb.append("!=");
			}
			sb.append(generateVal(vs[1], isNum));
		}  else if(oper.equals(FilterConstant.OPER_LIKE_B)) {
			sb.append(" like '%").append(value).append("'");
		} else if(oper.equals(FilterConstant.OPER_LIKE_E)) {
			sb.append(" like '").append(value).append("%'");
		} else if(oper.equals(FilterConstant.OPER_LIKE_BE)) {
			sb.append(" like '%").append(value).append("%'");
		} else if(oper.startsWith(FilterConstant.OPER_SCOPE)) {
			//如果是SCOPE需要进行判断
			sb.append(generateScope(oper,value));
		}
		return sb.toString();
	}

	private static String generateScope(String type, String value) {
		String ts = type.split(":")[1];
		if(ts.equals("GT")) return ">'"+value+"'";
		else if(ts.equals("LT")) return "<'"+value+"'";
		else if(ts.equals("GE")) return ">='"+value+"'";
		else if(ts.equals("LE")) return "<='"+value+"'";
		return null;
	}

	private static String generateVal(String value, boolean isNum) {
		StringBuffer sb = new StringBuffer();
		if(isNum) sb.append(value);
		else sb.append("'").append(value).append("'");
		return sb.toString();
	}

	private static String generatePre(String pre) {
		String[] pres = pre.split(":");
		if(!PlatformCommonKit.isEmpty(pres[1])) return pres[1];
		return "";
	}
}
