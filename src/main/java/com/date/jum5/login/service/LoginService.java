package com.date.jum5.login.service;

import org.springframework.stereotype.Service;

import com.date.jum5.login.vo.LoginVo;

@Service
public interface LoginService {
	//�α���
	public LoginVo checkId(LoginVo id) throws Exception;
	
	//���̵� ã��
	public LoginVo forGot(LoginVo forGot) throws Exception;
	
	//��й�ȣ ã��
	public LoginVo forGotPw(LoginVo forGotPw) throws Exception;

}
