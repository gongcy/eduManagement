package com.zslin.book.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zslin.basic.auth.annotations.AdminAuth;
import com.zslin.basic.auth.annotations.Token;
import com.zslin.basic.controller.admin.AbstractBaseController;
import com.zslin.basic.model.Pager;
import com.zslin.basic.tools.TokenTools;
import com.zslin.book.iservice.ICategoryService;
import com.zslin.book.model.Category;

/**
 * 分类管理Controller
 * @author niit216.com
 *
 */
@Controller
@RequestMapping(value="admin/category")
@AdminAuth(name="分类管理", psn="系统管理", orderNum=1)
public class CategoryController extends AbstractBaseController {

	private ICategoryService categoryService;

	public ICategoryService getCategoryService() {
		return categoryService;
	}

	@Inject
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	/** 列表 */
	@AdminAuth(name = "分类列表", orderNum = 1, icon="icon-list")
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(Model model, Integer ownerId, HttpServletRequest request) {
		Pager<Category> datas = categoryService.findAll();
		model.addAttribute("datas", datas);
		return "admin/category/list";
	}
	
	@Token(flag=Token.READY)
	@AdminAuth(name = "添加分类", orderNum = 2, icon="icon-plus")
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String add(Model model, HttpServletRequest request) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "admin/category/add";
	}
	
	@Token(flag=Token.CHECK)
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(Model model, Category category, HttpServletRequest request) {
		if(TokenTools.isNoRepeat(request)) { //不是重复提交
			categoryService.add(category);
		}
		return "redirect:/admin/category/list";
	}

	@Token(flag=Token.READY)
	@AdminAuth(name="修改分类", orderNum=3, type="2")
	@RequestMapping(value="update/{id}", method=RequestMethod.GET)
	public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
		Category cate = categoryService.load(id);
		model.addAttribute("category", cate);
		return "admin/category/update";
	}
	
	@Token(flag=Token.CHECK)
	@RequestMapping(value="update/{id}", method=RequestMethod.POST)
	public String update(Model model, @PathVariable Integer id, Category category, HttpServletRequest request) {
//		Boolean isRepeat = (Boolean) request.getAttribute("isRepeat");
		if(TokenTools.isNoRepeat(request)) {
			Category c = categoryService.load(id);
			c.setName(category.getName());
			categoryService.update(c);
		}
		return "redirect:/admin/category/list";
	}
	
	@AdminAuth(name="删除分类", orderNum=4, type="2")
	@RequestMapping(value="delete/{id}", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable Integer id) {
		try {
			categoryService.delete(id);
			return "1";
		} catch (Exception e) {
			return "0";
		}
	}
}
