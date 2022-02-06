package com.date.jum5.login.service;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.date.jum5.login.dao.LoginDao;
import com.date.jum5.login.vo.LoginVo;

@Repository
public class LoginServiceImpl implements LoginService{
	
	@Inject
	private LoginDao loginDao;

	//�α���
	@Override
	public LoginVo checkId(LoginVo loginVo) throws Exception {
		return loginDao.checkId(loginVo);
	}
	
	//���̵� ã��
	@Override
	public LoginVo forGot(LoginVo loginVo) throws Exception{
		return loginDao.forGot(loginVo);
	}
	
	//��й�ȣ ã��
	@Override
	public LoginVo forGotPw(LoginVo loginVo) throws Exception{
		return loginDao.forGotPw(loginVo);
	}
}