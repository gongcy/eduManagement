package com.niit216.basic.auth.tools;

import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;

/**
 * 
 * @author KongH
 *
 */
public class AnnotationUtil {
	private static AnnotationUtil au;
	private static MetadataReaderFactory mrf = null;
	
	private AnnotationUtil() {
	}
	
	public static AnnotationUtil getInstance() {
		if(au==null) {
			au = new AnnotationUtil();
			mrf = new CachingMetadataReaderFactory();
		}
		return au;
	}
	
	public MetadataReader getMetadataReader(Class<?> clz) {
		try {
			return mrf.getMetadataReader(clz.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
