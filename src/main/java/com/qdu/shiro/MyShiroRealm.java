package com.qdu.shiro;

import com.qdu.beans.CModule;
import com.qdu.beans.CRole;
import com.qdu.beans.CUser;
import com.qdu.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;


import java.util.*;

/**
 * Created by Alpha-LGC on 2019/1/25.
 */
public class MyShiroRealm extends AuthorizingRealm{
    @Autowired
    private LoginService logService;
    @Autowired
    private SessionDAO sessionDao;



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        CUser user = (CUser) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
         CUser user1 = logService.getUserByUS(user);

        if(user1 == null){
            return  null;
        }
        if(user1.getStatus().toString().equals("0")){

            Subject subject = SecurityUtils.getSubject();
            subject.logout();
           /* return null;*/
           /* throw  new LockedAccountException("账户 【"+ user1.getUsername()+"】 被锁定！");*/
            return null;
        }

        List<CRole> rs = logService.queryRolesByUserId(user);
        if(!rs.isEmpty()){
            for(CRole r : rs){
                sai.addRole(r.getRolename());
                List<CModule> ms = logService.queryModulesByRoleId(r);
                if(!ms.isEmpty()) {
                    for (CModule M : ms) {
                        sai.addStringPermission(M.getModulename());
                    }
                }
            }
        }
        System.out.println(sai.getRoles()+"\t"+sai.getStringPermissions());
        System.out.println("添加用户信息");
        return sai;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }
        UsernamePasswordToken ustoken = (UsernamePasswordToken) token;
        String username = ustoken.getUsername();

        //String pwd = new String(ustoken.getPassword());

        CUser user = new CUser();
        //user.setPassword(pwd);
        user.setUsername(username);
        CUser user1 = logService.getUserByName(user);
        System.out.println("验证用户信息");
        if (user1 == null) {
            //这里返回后会报出对应异常
            return null;
        }
        if(user1.getStatus().toString().equals("0")){
           // return null;
            throw  new LockedAccountException("账户 【"+ user1.getUsername()+"】 被锁定！");
        }

        Collection<Session> sessions = sessionDao.getActiveSessions();
       /* for(Session session:sessions){
            CUser sysUser = (CUser)session.getAttribute("USER_SESSION");                // 如果session里面有当前登陆的，则证明是重复登陆的，则将其剔除                if( sysUser!=null ){                    if( username.equals( sysUser.getUsername() ) ){                        session.setTimeout(0);                    }                }
            if(null!=sysUser){
                System.out.println(sysUser.getUsername());
                if (username.equals(sysUser.getUsername())){
                    session.setTimeout(0);
                    System.out.println("!!!!!!!!!!!!");
                }
            }
        }*/
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("USER_SESSION", user1);
       // session.setAttribute(USER_SESSION, user);
        //这里验证authenticationToken和simpleAuthenticationInfo的信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user1, user1.getPassword(), getName());
        return simpleAuthenticationInfo;


    }
}
