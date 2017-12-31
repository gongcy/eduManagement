package com.niit216.basic.auth.service;

import com.niit216.basic.auth.dto.MenuDto;
import com.niit216.basic.auth.dto.PMenuDto;
import com.niit216.basic.auth.idao.IMenuDao;
import com.niit216.basic.auth.idao.IUserDao;
import com.niit216.basic.auth.iservice.IMenuService;
import com.niit216.basic.auth.model.Menu;
import com.niit216.basic.model.Pager;
import com.niit216.basic.tools.PinyinToolkit;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;

@Service("menuService")
public class MenuService implements IMenuService {
	
	private IMenuDao menuDao;
	private IUserDao userDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IMenuDao getMenuDao() {
		return menuDao;
	}

	@Inject
	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void add(Menu t) {
		menuDao.add(t);
	}

	public void update(Menu t) {
		menuDao.update(t);
	}

	public void delete(Integer id) {
		menuDao.delete(id);
	}

	public Menu load(Integer id) {
		return menuDao.load(id);
	}

	public String queryTreeJson(String type) {
		return menuDao.queryTreeJson(type);
	}

	public List<Menu> listByPid(Integer pid, String type) {
		return menuDao.listByPid(pid, type);
	}

	public Pager<Menu> findAll(Integer pid) {
		return menuDao.findAll(pid);
	}

	public Menu loadBySn(String sn) {
		return menuDao.loadBySn(sn);
	}
	
	public void addOrUpdate(Menu menu) {
		Menu m = this.loadBySn(menu.getSn());
		try {
			String prSn = menu.getPsn();
			if(prSn!=null && !"".equals(prSn)) {
				boolean isEnglish = prSn.getBytes().length==prSn.length(); //无汉字
				if(isEnglish) {
					Menu pr = this.loadBySn(menu.getPsn());
					if(pr!=null) {m.setPid(pr.getId());}
				} else {
					String pinyin = PinyinToolkit.cn2Spell(prSn, "_");
					Menu pr = this.loadBySn(pinyin);
					boolean isAdd = false;
					if(pr==null) {pr = new Menu(); isAdd = true;}
					pr.setDisplay(1); pr.setName(prSn); pr.setOrderNum(10); 
					pr.setSn(pinyin); pr.setPsn("root"); 
					pr.setType("1"); pr.setHref("#");
					if(isAdd) { this.add(pr); }
					//else {this.update(pr);} //这里不作修改，只通过系统后台修改
					menu.setPsn(pr.getSn()); menu.setPid(pr.getId());
				}
			}
		} catch (Exception e) {
		}
		if(m==null) {this.add(menu);}
		else {
			m.setDisplay(menu.getDisplay());
			m.setIcon(menu.getIcon());
			m.setName(menu.getName());
			m.setOrderNum(menu.getOrderNum());
			m.setPsn(menu.getPsn());
			if(menu.getPid()!=null && menu.getPid()>0) { //
				m.setPid(menu.getPid());
			}
			m.setType(menu.getType());
			m.setHref(menu.getHref());
			this.update(m);
		}
	}

	public Map<Menu, Map<Menu, List<Menu>>> queryAuthMenu(Integer userId) {
//		Integer isAdmin = userDao.isAdmin(userId);
//		List<List<MenuDto>> list = new ArrayList<List<MenuDto>>();
//		List<Map<Menu, List<Menu>>> list = new ArrayList<Map<Menu,List<Menu>>>();
		Map<Menu, Map<Menu, List<Menu>>> map = new HashMap<Menu, Map<Menu,List<Menu>>>();
		List<Menu> menuList = this.listByUser(userId);
		for(Menu m : menuList) {
			if(m.getPsn()==null || "root".equalsIgnoreCase(m.getPsn())) { //说明是一级菜单
//				if(!list.contains(m)) {list.add(new TreeMap<Menu, List<Menu>>());}
				if(!map.containsKey(m)) {map.put(m, new TreeMap<Menu, List<Menu>>());}
			} else if(!"root".equalsIgnoreCase(m.getPsn()) && m.getSn().indexOf(".")<0) { //说明是二级菜单
				Menu pm = this.loadBySn(m.getPsn());
				if(!map.containsKey(pm)) {
					map.put(pm, new TreeMap<Menu, List<Menu>>());
				}
				map.get(pm).put(m, new ArrayList<Menu>());
			} else if(m.getSn().indexOf(".")>0) { //说明是三级菜单
				Menu pm = this.loadBySn(m.getPsn()); //这是二级菜单
				Menu ppm = this.loadBySn(pm.getPsn()); //这是一级菜单
				if(map.containsKey(ppm)) {
					if(!map.get(ppm).containsKey(pm)) {map.get(ppm).put(pm, new ArrayList<Menu>());} //如果不包含二级菜单则先添加
				} else { //如果一级菜单都不包含
					map.put(ppm, new HashMap<Menu, List<Menu>>()); //先添加一级菜单 
					map.get(ppm).put(pm, new ArrayList<Menu>()); //再添加二级菜单
				}
				map.get(ppm).get(pm).add(m); //添加三级菜单
			}
		}
		for(Menu pm : map.keySet()) {
			//Collections.sort(map.get(pm));
			for(Menu m : map.get(pm).keySet()) {
				Collections.sort(map.get(pm).get(m));
				Collections.sort(map.get(pm).get(m));
			}
		}
		return map;
	}
	
	public List<PMenuDto> queryMenuDto(Integer userId) {
		List<PMenuDto> res = new ArrayList<PMenuDto>();
		List<Menu> menuList = this.listByUser(userId);
		for(Menu m : menuList) {
			if(m.getPsn()==null || "root".equalsIgnoreCase(m.getPsn())) { //说明是一级菜单
				PMenuDto ppmDto = new PMenuDto(m.getId(), m, new ArrayList<MenuDto>());
				if(!res.contains(ppmDto)) {res.add(ppmDto);}
			} else if(!"root".equalsIgnoreCase(m.getPsn()) && m.getSn().indexOf(".")<0) { //说明是二级菜单
				Menu pm = this.loadBySn(m.getPsn());
				PMenuDto ppmDto = new PMenuDto(pm.getId(), pm, new ArrayList<MenuDto>());
				if(!res.contains(pm)) {
					res.add(ppmDto);
				}
				res.get(res.indexOf(ppmDto)).getChildren().add(new MenuDto(m.getId(), m, new ArrayList<Menu>()));
			} else if(m.getSn().indexOf(".")>0) { //说明是三级菜单
				Menu pm = this.loadBySn(m.getPsn()); //这是二级菜单
				MenuDto pmDto = new MenuDto(pm.getId(), pm, new ArrayList<Menu>());
				Menu ppm = this.loadBySn(pm.getPsn()); //这是一级菜单
				PMenuDto ppmDto = new PMenuDto(ppm.getId(), ppm, new ArrayList<MenuDto>());
				if(!res.contains(ppmDto)) { //如果不包含一级菜单
					res.add(new PMenuDto(ppm.getId(), ppm, new ArrayList<MenuDto>()));
				}
				if(!res.get(res.indexOf(ppmDto)).getChildren().contains(pmDto)) { //如果不包含二级菜单
					res.get(res.indexOf(ppmDto)).getChildren().add(pmDto);
				}
				res.get(res.indexOf(ppmDto)).getChildren().get(res.get(res.indexOf(ppmDto)).getChildren().indexOf(pmDto)).getChildren().add(m);
			}
		}
		for(PMenuDto ppm : res) {
			Collections.sort(ppm.getChildren());
		}
		Collections.sort(res);
		return res;
	}

	/***
	 * 获取用户权限范围内的菜单对象
	 * @param userId
	 * @return
	 */
	public List<Menu> listByUser(Integer userId) {
		return menuDao.listByUser(userId);
	}

	public List<String> listAuthByUser(Integer userId) {
		return menuDao.listAuthByUser(userId);
	}

	public List<Menu> listAllUrlMenu() {
		return menuDao.listAllUrlMenu();
	}

	public void updateSort(Integer[] ids) {
		menuDao.updateSort(ids);
	}
}
