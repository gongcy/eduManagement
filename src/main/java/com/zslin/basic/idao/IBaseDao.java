package com.zslin.basic.idao;

/**
 * Dao层公共处理接口，与Hibernate交互的公共操作
 * @author zslin.com 20160509
 *
 * @param <T> 范型，指定操作类型
 */
public interface IBaseDao<T> {

	/**
	 * 添加对象
	 * @param t 范型
	 * @return
	 */
	public T add(T t);
	
	/**
	 * 修改对象
	 * @param t 范型
	 */
	public void update(T t);
	
	/**
	 * 通过Id删除对象
	 * @param id 对象Id
	 */
	public void delete(Integer id);
	
	/**
	 * 通过Id获取对象
	 * @param id 对象Id
	 * @return 返回实体对象
	 */
	public T load(Integer id);
	
	/**
	 * 排序
	 * @param ids 对象主键列表
	 * @param clz 类名
	 * @param field 序号字段名
	 */
	public void updateSort(Integer[] ids,String clz, String field);
}
