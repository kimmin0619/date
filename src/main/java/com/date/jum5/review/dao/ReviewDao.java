package com.date.jum5.review.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.date.jum5.review.vo.ReviewVo;

@Repository
public class ReviewDao {

	@Autowired
	SqlSession sqlsession = null;
	
	//기시판 정보 가져오기
	public ReviewVo Reviewlist(ReviewVo reviewVo) throws Exception{
		return (ReviewVo)sqlsession.selectOne("ReviewMapper.list", reviewVo);
	}
}
