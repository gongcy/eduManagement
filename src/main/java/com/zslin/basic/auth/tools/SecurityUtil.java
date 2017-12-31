package com.zslin.basic.auth.tools;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.MethodUtils;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.MetadataReader;

import com.zslin.basic.auth.annotations.EncryptProperty;
import com.zslin.basic.model.Pager;
/**
 * 安全工具类，可以进行数据的加密和解密，并且可以完成对象的加密和解密
 * @author KongHao
 *
 */
@SuppressWarnings("rawtypes")
public class SecurityUtil {
	public static String md5(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		return new BigInteger(1,md.digest()).toString(16);
	}
	
	public static String md5(String username,String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(username.getBytes());
		md.update(password.getBytes());
		return new BigInteger(1,md.digest()).toString(16);
	}
	
	/**
	 * 对该对象中被Annotation为EncryptProperty的属性进行加密
	 * @param obj
	 */
	public static void encryptObj(Object obj) {
		if(obj instanceof Collection) {
			//是列表对象分开处理
			handleListEncrypt((Collection)obj,true);
		} else if(obj instanceof Map) {
			//是Map对象重新处理
			handleMapEncrypt((Map)obj,true);
		} else {
			//是普通对象时的处理
			handleEncrypt(obj,true);
		}
	}
	
	public static void unencryptObj(Object obj) {
		if(obj instanceof Collection) {
			//是列表对象分开处理
			handleListEncrypt((Collection)obj,false);
		} else if(obj instanceof Map) {
			//是Map对象重新处理
			handleMapEncrypt((Map)obj,false);
		} else if (obj instanceof Pager){
			//分页对象需要重新处理
			handleListEncrypt(((Pager)obj).getDatas(), false);
		}else {
			//是普通对象时的处理
			handleEncrypt(obj,false);
		}
	}

	private static void handleEncrypt(Object obj,boolean isEncrypt) {
		try {
			MetadataReader mr = AnnotationUtil.getInstance().getMetadataReader(obj.getClass());
			Set<MethodMetadata> mms = mr.getAnnotationMetadata()
						.getAnnotatedMethods(EncryptProperty.class.getName());
			for(MethodMetadata mm:mms) {
				String gname = mm.getMethodName();
				String sname = gname.replace("get", "set");
				String str = (String)MethodUtils.invokeExactMethod(obj, gname, null);
				//进行加密
				if(isEncrypt) {
					str = DesUtil.password(str);
				} else {
					str = DesUtil.unPassword(str);
				}
				MethodUtils.invokeExactMethod(obj,sname,str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void handleMapEncrypt(Map mobj, boolean b) {
		for(Object o:mobj.keySet()) {
			handleEncrypt(mobj.get(o), b);
		}
	}

	private static void handleListEncrypt(Collection lobj, boolean b) {
		for(Object o:lobj) {
			handleEncrypt(o, b);
		}
	}
	
	
}
