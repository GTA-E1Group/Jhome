package com.daxu.common.Identity;

import com.alibaba.fastjson.JSON;
import com.daxu.common.Cache.MemcachedManager;
import com.daxu.common.ToolKit.CookieUtil;
import com.daxu.common.ToolKit.JSONUtils;
import com.daxu.common.Vo.UserEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 * @author xu.da1
 */
public class AuthUtil {

	/**
	 * 登陆
	 * 
	 * @param value
	 * @param response
	 * @return 返回 Token
	 */
	public static String userLogin(String userId, Object value,
			HttpServletResponse response) {

		Map<String, Object> loginInfo = new HashMap<String, Object>();
		loginInfo.put("userInfo", JSON.toJSONString(value));
		String token = JavaWebToken.createJavaWebToken(loginInfo);
		if (!token.isEmpty()) {
			// System.out.println("token" + token);
			//写入Token到客户端缓存
			CookieUtil.set(response, "token", token, 5 * 365 * 24 * 60 * 60);
			// 写入Memcached 手机端不支持cookie 通过Header 传递
			Calendar calenda = Calendar.getInstance();
			calenda.add(Calendar.YEAR, 1);
			MemcachedManager.getMemCache().Set(
					String.format("user_%s", userId), value, calenda.getTime());
		}
		return token;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Object getUserInfo(HttpServletRequest request) {
		try {
			Map<String, Object> userMap = (Map<String, Object>) getClientLoginInfo(request);
			// token验证通过 返回用户字典
			if (userMap != null) {
				Object objectCached = new Object();
				// ...
				UserEntity user = (UserEntity) JSONUtils.jsonToBean(
						String.valueOf(userMap.get("userInfo")),
						new UserEntity());

				// 分布式缓存策略 从 memcached 中获取用户信息
				objectCached = MemcachedManager.getMemCache().Get(
						String.format("user_%s", user.getId()));
				if (objectCached == null) {
					// 非分布式换成策略 从其他cached中获取用户信息
					objectCached = user;
				} 
				if (objectCached != null) {
					return objectCached;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * 用户退出
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	public static boolean userOut(HttpServletResponse response,
                                  HttpServletRequest request) {
		boolean flag = false;
		try {
			CookieUtil.set(response, "token", "", -1 * 365 * 24 * 60 * 60);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return flag;
	}

	private static Map<String, Object> getClientLoginInfo(
			HttpServletRequest request) throws Exception {
		String token = "";
		Map<String, Object> r = new HashMap<String, Object>();
		// 移动端通过Header传递，服务器通过COOKIE传值 兼容两种方式
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().toLowerCase().equals("token")) {
					token = cookie.getValue();
				}
			}
		} else {
			token = request.getHeader("token");
		}
		if (!token.isEmpty()) {
			try {
				r = decodeSession(token);
				return r;
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		}
		return null;
	}

	public static Map<String, Object> decodeSession(String token) {
		try {
			return JavaWebToken.verifyJavaWebToken(token);
		} catch (Exception e) {
			System.err.println("");
			return null;
		}
	}
}
