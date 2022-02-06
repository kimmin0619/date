package com.date.jum5.login.controller;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.date.jum5.login.service.LoginService;
import com.date.jum5.login.vo.LoginVo;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/" , method = RequestMethod.GET)
	public String home() throws Exception{
		return "index";
	}
	
	//로그인 폼 이동
	@RequestMapping(value = "/loginForm" , method = RequestMethod.GET)
	public String login_form() throws Exception{
		return "/login/loginForm";
	}
	
	//마이페이지 이동
	@RequestMapping(value = "/mypage" , method = RequestMethod.GET)
	public String mypage() throws Exception{
		return "/mypage/myPage";
	}
	
	//로그인
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	public String checkId(@ModelAttribute LoginVo loginVo, HttpSession session, Model model) throws Exception{
		
		
		loginVo = loginService.checkId(loginVo);
		
		
		
		String page = "";
		
		
		
		if(loginVo == null) {
			page = "/login/loginFalse"; // 실패시 이동할 페이지 경로
			
		} else {
			System.out.println("!!!");
			System.out.println(loginVo.getNickname());
			page = "/index"; // 성공시 이동할 페이지 경로
			
			model.addAttribute("loginVo", loginVo);
			
			session.setAttribute("loginVo", loginVo);
		}
		
		
		
		return page;
	}
	
	//---------------------- 카카오 로그인---------------------------
	@RequestMapping(value = "/login/getKakaoAuthUrl")
	public @ResponseBody String getKakaoAuthUrl(
			HttpServletRequest request) throws Exception {
		String reqUrl = 
				"https://kauth.kakao.com/oauth/authorize"
				+ "?client_id=daf2661729b71cd6f6cb9dd95d756f70"
				+ "&redirect_uri=http://localhost8080/login"
				+ "&response_type=code";
		
		return reqUrl;
	}
	
	// 카카오 연동정보 조회
	@RequestMapping(value = "/login/oauth_kakao")
	public String oauthKakao(
			@RequestParam(value = "code", required = false) String code
			, Model model) throws Exception {

		System.out.println("#########" + code);
        String access_Token = getAccessToken(code);
        System.out.println("###access_Token#### : " + access_Token);
        
        
        HashMap<String, Object> userInfo = getUserInfo(access_Token);
        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###userInfo#### : " + userInfo.get("email"));
        System.out.println("###nickname#### : " + userInfo.get("nickname"));
       
        JSONObject kakaoInfo =  new JSONObject(userInfo);
        model.addAttribute("kakaoInfo", kakaoInfo);
        
        return "/index"; //본인 원하는 경로 설정
	}
	
    //토큰발급
	@SuppressWarnings("deprecation")
	public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //  URL연결은 입출력에 사용 될 수 있고, POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //	POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=daf2661729b71cd6f6cb9dd95d756f70");  //본인이 발급받은 key
            sb.append("&redirect_uri=http://localhost8080/login"); // 본인이 설정해 놓은 경로
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return access_Token;
    }
	
    //유저정보조회
    @SuppressWarnings("deprecation")
	public HashMap<String, Object> getUserInfo (String access_Token) {

        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
            
            userInfo.put("accessToken", access_Token);
            userInfo.put("nickname", nickname);
            userInfo.put("email", email);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return userInfo;
    }
	
    
    
	//--------------------로그아웃----------------------------------
	@RequestMapping(value = "/logout" , method = RequestMethod.GET)
	public String logOutPage(HttpServletRequest request) throws Exception{
		
		 HttpSession session = request.getSession();
		 
		 session.invalidate();
		
		return "/login/logout";
	}
	
	//logOut.jsp 에서 확인 버튼 눌렀을때 인덱스로 이동하는 경로
	@RequestMapping(value = "/logoutMain" , method = RequestMethod.GET)
	public String logoutMain() throws Exception{
		return "/index";
	}
	
	
	//--------------------아이디 / 패스워드 찾기 구역------------------------------
	
	//아이디 / 패스워드 찾기로 이동
	@RequestMapping(value = "/forGotPage" , method = RequestMethod.GET)
	public String forGotPage() throws Exception{
		return "/login/forGotPage";
	}
	//아이디 찾기로 이동
	@RequestMapping(value = "/idSearch" , method = RequestMethod.GET)
	public String idSearch() throws Exception{
		return "/login/idSearch";
		}
	//패스워드 찾기로 이동
	@RequestMapping(value = "/pwSearch" , method = RequestMethod.GET)
	public String pwSearch() throws Exception{
		return "/login/pwSearch";
		}	
	
	//아이디 찾기
	@RequestMapping(value = "/idForGot" , method = RequestMethod.POST)
	public String forGot(@ModelAttribute LoginVo idSearchOk, HttpSession session, Model model) throws Exception{
	
	
		
		idSearchOk = loginService.forGot(idSearchOk);
		
		String page = "";
		
		
		
		if(idSearchOk == null) {
			
			page = "/login/idSearch"; // 실패시 이동할 페이지 경로
			
		} else {
			
			System.out.println(idSearchOk.getId());
			
			page = "/login/idSearch"; // 성공시 이동할 페이지 경로
			
			model.addAttribute("idSearchOk", idSearchOk);
			
			session.setAttribute("loginVo", idSearchOk);
		}
		
		
		
		return page;	
	}
	
	//패스워드 찾기
	
	@RequestMapping(value = "/pwForGot" , method = RequestMethod.POST)
	public String forGotPw(@ModelAttribute LoginVo pwSearchOk, HttpSession session, Model model) throws Exception{
	
		pwSearchOk = loginService.forGotPw(pwSearchOk);
		
		String page ="";
		
		if(pwSearchOk == null) {
			
			page = "login/pwSearch";
			
		}else {
			
			page = "login/pwSearch";
			
			
			model.addAttribute("pwSearchOk", pwSearchOk);
			
			session.setAttribute("loginVo", pwSearchOk);
		}
		return page;
	}
}

























