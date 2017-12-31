package com.niit216.basic.iservice;

/**
 * 业务层通用功能
 * @author zsl-pc 20160511
 *
 * @param <T> 范型
 */
public interface IBaseService<T> {

	/** 添加 */
	public void add(T t);
	
	/** 修改 */
	public void update(T t);
	
	/** 删除 */
	public void delete(Integer id);
	
	/** 通过Id获取对象 */
	public T load(Integer id);
}
