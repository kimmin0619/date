package com.date.jum5.review.service;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.date.jum5.review.dao.ReviewDao;
import com.date.jum5.review.vo.ReviewVo;

@Repository
public class ReviewServiceImpl implements ReviewService{
	
	@Inject
	private ReviewDao reviewdao;
	
	//�Խñ� ����Ʈ
	@Override
	public ReviewVo Reviewlist(ReviewVo reviewVo) throws Exception{
		return reviewdao.Reviewlist(reviewVo);
	}
}
