package com.date.jum5.review.service;

import org.springframework.stereotype.Service;

import com.date.jum5.review.vo.ReviewVo;

@Service
public interface ReviewService {

	// �Խñ� ����Ʈ
	public ReviewVo Reviewlist(ReviewVo vo) throws Exception;
	

}
