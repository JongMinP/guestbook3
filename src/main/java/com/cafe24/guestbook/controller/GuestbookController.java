package com.cafe24.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.guestbook.dao.GuestBookDao;
import com.cafe24.guestbook.vo.GuestBookVo;

@Controller
public class GuestbookController {

	@Autowired
	private GuestBookDao dao;

	@RequestMapping("/list")
	public String list(Model model) {

		List<GuestBookVo> list = dao.getList();

		model.addAttribute("list", list);
		return "/WEB-INF/views/index.jsp";
	}

	@RequestMapping("/form")
	public String form(Model model, @RequestParam("no") int no) {
		model.addAttribute("no", no);

		return "/WEB-INF/views/deleteform.jsp";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {

		GuestBookVo vo = dao.getGusetBook(no);
		if (password.equals(vo.getPassword())) {
			dao.delete(no);
		}

		return "redirect:/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute GuestBookVo vo) {

		dao.insert(vo);
		return "redirect:/list";
	}

}
