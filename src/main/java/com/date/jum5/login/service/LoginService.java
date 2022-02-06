package com.date.jum5.login.service;

import org.springframework.stereotype.Service;

import com.date.jum5.login.vo.LoginVo;

@Service
public interface LoginService {
	//로그인
	public LoginVo checkId(LoginVo id) throws Exception;
	
	//아이디 찾기
	public LoginVo forGot(LoginVo forGot) throws Exception;
	
	//비밀번호 찾기
	public LoginVo forGotPw(LoginVo forGotPw) throws Exception;

}
