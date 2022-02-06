package com.date.jum5.login.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.date.jum5.login.vo.LoginVo;


@Repository
public class LoginDao {

	@Autowired
	SqlSession sqlsession = null;

	//아이디 검사
	public LoginVo checkId(LoginVo loginVo) throws Exception{
		return (LoginVo)sqlsession.selectOne("login.checkId", loginVo); // login.checkid = mapper login.xml	
	}
	
	//아이디 찾기
	
	public LoginVo forGot(LoginVo idSerchOk) throws Exception{
		return (LoginVo)sqlsession.selectOne("login.forGot", idSerchOk);
	}
	
	//비밀번호 찾기
	public LoginVo forGotPw(LoginVo pwSerchOk) throws Exception{
		return (LoginVo)sqlsession.selectOne("login.forGotPw", pwSerchOk);
	}
}
