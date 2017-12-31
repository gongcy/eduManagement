package com.niit216.book.controller.web;

import com.niit216.basic.model.Pager;
import com.niit216.book.iservice.IReaderService;
import com.niit216.book.model.Reader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="web/reader")
public class WebReaderController {

	private IReaderService readerService;

	public IReaderService getReaderService() {
		return readerService;
	}

	@Inject
	public void setReaderService(IReaderService readerService) {
		this.readerService = readerService;
	}
	
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		Pager<Reader> datas = readerService.findAll();
		model.addAttribute("datas", datas);
		return "web/reader/index";
	}
}
