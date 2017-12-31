package com.zslin.basic.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 创建在线统计的监听器
 * - 实现HttpSessionListener接口
 * - 该接口有session创建和session销毁两个方法
 * - 只用在这两个方法里加两个的统计即可
 * @author niit216.com
 *
 */
public class OnlineListener implements HttpSessionListener {

	/**
	 * session创建时的事件
	 */
	public void sessionCreated(HttpSessionEvent event) {
		//1、获取Servlet上下文
        ServletContext context=event.getSession().getServletContext(); 
        //2、取存在的变量值
        Integer count=(Integer)context.getAttribute("count"); 
        //3、如果不存在则为1，如果存在则加1
        if(count==null){ 
            count=new Integer(1); 
        }else{ 
            int co = count.intValue( ); 
            count= new Integer(co+1); 
        } 
        //4、将处理后的值以count为名存入context中
        context.setAttribute("count", count);//保存人数 
	}

	/**
	 * session销毁时事件
	 */
	public void sessionDestroyed(HttpSessionEvent event) {
		//1、获取Servlet上下文
        ServletContext context=event.getSession().getServletContext(); 
        //2、取出count对应的值
        Integer count=(Integer)context.getAttribute("count"); 
        int co=count.intValue(); 
        count=new Integer(co-1); 
        //3、将数量减1后重新存入context的count中
        context.setAttribute("count", count); 
       // System.out.println("当前用户人数："+count);
	}
}
