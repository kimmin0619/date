package com.date.jum5.review.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.date.jum5.login.service.LoginService;
import com.date.jum5.review.service.ReviewService;
import com.date.jum5.review.vo.ReviewVo;

@Controller
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;

	//���� ������ �̵� + �Խñ� ����Ʈ
	@RequestMapping(value = "/review" , method = RequestMethod.GET)
	public String reviewList(ReviewVo reviewVo , Model model) throws Exception{
		
		reviewVo = reviewService.Reviewlist(reviewVo);
		
		model.addAttribute("reviewList", reviewVo);
		
		return "/review/review";
	}
	//���� �ۼ� ������
	@RequestMapping(value = "/reviewForm" , method = RequestMethod.GET)
	public String reviewForm() throws Exception{
		return "/review/reviewForm";
	}

	
	
	//�Խñ� �ۼ�
//	@RequestMapping(value = "/reviewInsert" , method = RequestMethod.POST)
//	public String reviewInsert(@ModelAttribute ReviewVo reviewVo, HttpSession session, Model model) throws Exception{
//		
//		//reviewVo = ReviewService.reviewInsert(reviewVo);
//		
//	}
//	
}
