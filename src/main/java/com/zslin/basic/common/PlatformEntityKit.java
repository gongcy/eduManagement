package com.zslin.basic.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

@SuppressWarnings("rawtypes")
public class PlatformEntityKit {
	private PlatformEntityKit() {
		
	}
	
	private static PlatformEntityKit kit=null;
	
	public static PlatformEntityKit getInstance() {
		if(kit==null) kit = new PlatformEntityKit();
		return kit;
	}
	
	
	@SuppressWarnings("unchecked")
	public SQLQuery addEntityBySql(Object obj,Session session) {
		try {
			StringBuffer sb = new StringBuffer();
			Class clz = obj.getClass();
			String tname = ((Table)(clz.getAnnotation(Table.class))).name();
			SQLLen sl = entity2Sql(clz);
			sb.append("insert into ").append(tname)
				.append(sl.getSql()).append(" value ").append(entity2Sql(sl.getLen()));
//			System.out.println(sb);
			SQLQuery sq = session.createSQLQuery(sb.toString());
			List<Method> methods = sl.getMethods();
			List<String> joins = sl.joins;
			int index = 0;
			Object val = null;
			for(Method m:methods) {
				if(joins.contains(m.getName())) {
					val = BeanUtils.getProperty(m.invoke(obj), "id");
				} else {
					val = m.invoke(obj);
				}
				sq.setParameter(index++, val);
			}
			return sq;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String entity2Sql(int len) {
		StringBuffer sb = new StringBuffer("(");
		for(int i=0;i<len;i++) {
			if(i>0)
				sb.append(",");
			sb.append("?");
		}
		sb.append(")");
		return sb.toString();
	}

	private SQLLen entity2Sql(Class clz) {
		StringBuffer sb = new StringBuffer("(");
		Method [] ms = clz.getDeclaredMethods();
		int index = 0;
		List<Method> methods = new ArrayList<Method>();
		List<String> joins = new ArrayList<String>();
		for(Method m :ms) {
			String mname = m.getName();
			if(mname.startsWith("get")) {
				if(index>0)
					sb.append(",");
				index++;
				methods.add(m);
				sb.append(method2Field(m,joins));
			}
		}
		sb.append(")");
		SQLLen sl = new SQLLen();
		sl.setSql(sb.toString());
		sl.setLen(index);
		sl.setMethods(methods);
		return sl;
	}
	
	private String method2Field(Method m,List<String> joins) {
		String mname = m.getName();
		if(m.isAnnotationPresent(Column.class)) {
			String cname = (m.getAnnotation(Column.class)).name();
			if(cname!=null&&!cname.trim().equals("")) {
				return cname;
			}
		}
		if(m.isAnnotationPresent(JoinColumn.class)) {
			String cname = (m.getAnnotation(JoinColumn.class)).name();
			if(cname!=null&&!cname.trim().equals("")) {
				joins.add(mname);
				return cname;
			}
		}
		String n = mname.substring(3);
		String ns = n.substring(0, 1).toLowerCase()+n.substring(1);
		return ns;
	}

	private class SQLLen {
		private String sql;
		private int len;
		private List<Method> methods = new ArrayList<Method>();
		private List<String> joins = new ArrayList<String>();
		
		public List<Method> getMethods() {
			return methods;
		}
		public void setMethods(List<Method> methods) {
			this.methods = methods;
		}
		public String getSql() {
			return sql;
		}
		public void setSql(String sql) {
			this.sql = sql;
		}
		public int getLen() {
			return len;
		}
		public void setLen(int len) {
			this.len = len;
		}
	}
}
