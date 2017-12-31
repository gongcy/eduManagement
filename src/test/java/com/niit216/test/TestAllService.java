package com.niit216.test;

import com.niit216.basic.auth.dto.MenuDto;
import com.niit216.basic.auth.dto.PMenuDto;
import com.niit216.basic.auth.iservice.IMenuService;
import com.niit216.basic.auth.model.Menu;
import com.niit216.basic.auth.tools.AuthTools;
import com.niit216.basic.auth.tools.SecurityUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestAllService {

	@Inject
	public IMenuService menuService;
	
	@Test
	public void testMenu() {
		//调用此方法生成系统菜单
		AuthTools.getInstance().buildSystemMenu(menuService);
	}
	
	@Test
	public void testMenuList() {
		List<Menu> list = menuService.listByUser(1);
		System.out.println(list.size());
	}
	
	@Test
	public void testUser() throws Exception {
		String pwd = "123456";
		System.out.println(SecurityUtil.md5("admin", pwd));
	}
	
	@Test
	public void testUserAuth() {
		List<PMenuDto> list = menuService.queryMenuDto(1);
		for(PMenuDto ppm : list) {
			System.out.println(ppm.getPm().getName());
			for(MenuDto pm : ppm.getChildren()) {
				System.out.println("\t"+pm.getPm().getName());
				for(Menu m : pm.getChildren()) {
					System.out.println("\t\t"+m.getName());
				}
			}
		}
	}
}