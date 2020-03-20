package com.example.demo.shiro;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


/**
 * 自定义Realm
 *
 */
public class MyRealm extends AuthorizingRealm {

	@Resource
	private UserDao userDao;

	/**
	 * #授权--验证url
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String name = (String) SecurityUtils.getSubject().getPrincipal();

		User user = userDao.findByName(name);
		//有了用户可以拿到，角色，  有角色，就有对应的菜单 list集合。  --- permissions

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<String>();
		roles.add("管理员");

		//  遍历角色所有menu
		info.addStringPermission("添加用户权限");//添加权限   permissions
		info.setRoles(roles);
		return info;
	}

	/**
	 * 权限认证--登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String name = (String) token.getPrincipal();//用户名  UsernamePasswordTokenr的第一个参数  name
		User user = userDao.findByName(name);
		if (user != null) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getName(), user.getPwd(), "xxx");
			return authcInfo;
		} else {
			return null;
		}
	}
}
