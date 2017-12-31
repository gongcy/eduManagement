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
import com.zslin.basic.model.Pager;
import com.zslin.basic.tools.MyBeanUtils;
import com.zslin.basic.tools.PinyinToolkit;
import com.zslin.basic.tools.TokenTools;
import com.zslin.book.iservice.IReaderService;
import com.zslin.book.model.Reader;

/**
 * 读者管理Controller
 * @author zslin.com 20160612
 *
 */
@Controller
@RequestMapping(value="admin/reader")
@AdminAuth(name="读者管理", psn="系统管理", orderNum=3)
public class ReaderController {

	private IReaderService readerService;

	public IReaderService getReaderService() {
		return readerService;
	}

	@Inject
	public void setReaderService(IReaderService readerService) {
		this.readerService = readerService;
	}
	
	/** 列表 */
	@AdminAuth(name = "读者列表", orderNum = 1, icon="icon-list")
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(Model model, Integer ownerId, HttpServletRequest request) {
		Pager<Reader> datas = readerService.findAll();
		model.addAttribute("datas", datas);
		return "admin/reader/list";
	}
	
	@Token(flag=Token.READY)
	@AdminAuth(name = "添加读者", orderNum = 2, icon="icon-plus")
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String add(Model model, HttpServletRequest request) {
		Reader reader = new Reader();
		model.addAttribute("reader", reader);
		return "admin/reader/add";
	}
	
	@Token(flag=Token.CHECK)
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(Model model, Reader reader, HttpServletRequest request) {
		if(TokenTools.isNoRepeat(request)) { //不是重复提交
			reader.setNameFullNo(PinyinToolkit.cn2Spell(reader.getName(), " "));
			reader.setNameNo(PinyinToolkit.cn2FirstSpell(reader.getName()));
			readerService.add(reader);
		}
		return "redirect:/admin/reader/list";
	}

	@Token(flag=Token.READY)
	@AdminAuth(name="修改读者", orderNum=3, type="2")
	@RequestMapping(value="update/{id}", method=RequestMethod.GET)
	public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
		Reader r = readerService.load(id);
		model.addAttribute("reader", r);
		return "admin/reader/update";
	}
	
	@Token(flag=Token.CHECK)
	@RequestMapping(value="update/{id}", method=RequestMethod.POST)
	public String update(Model model, @PathVariable Integer id, Reader reader, HttpServletRequest request) {
//		Boolean isRepeat = (Boolean) request.getAttribute("isRepeat");
		if(TokenTools.isNoRepeat(request)) {
			Reader r = readerService.load(id);
			MyBeanUtils.copyProperties(reader, r, new String[]{"id"});
			readerService.update(r);
		}
		return "redirect:/admin/reader/list";
	}
	
	@AdminAuth(name="删除读者", orderNum=4, type="2")
	@RequestMapping(value="delete/{id}", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable Integer id) {
		try {
			readerService.delete(id);
			return "1";
		} catch (Exception e) {
			return "0";
		}
	}
}
