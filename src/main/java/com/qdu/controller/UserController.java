package com.qdu.controller;

import com.qdu.beans.CModule;
import com.qdu.beans.CRole;
import com.qdu.beans.CUser;
import com.qdu.beans.CUserRole;
import com.qdu.service.LoginService;
import com.qdu.service.RoleService;
import com.qdu.service.UserService;
import com.qdu.utils.ResultMsg;
import com.qdu.utils.TreeNode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by Alpha-LGC on 2019/2/12.
 */
@Controller
public class UserController {

    @Autowired
    private UserService us;
    @Autowired
    private LoginService logservice;
    @Autowired
    private RoleService roleService;




    @RequestMapping(value = "/user/query")
    @ResponseBody
    @RequiresPermissions({"系统权限_查看"})
    public Map<String,Object> query(Integer page, Integer rows){
        return us.queryAllUser(page, rows);
    }

    @RequiresPermissions({"系统权限_查看"})
    @RequestMapping(value = "/user/userinfo")
    public String goUserInfo(){
        return "redirect:/pages/user/userinfo.jsp";
    }

    @RequiresPermissions({"系统权限_查看"})
    @RequestMapping(value = "/ad/ad")
    public String goad(){
        return "redirect:/pages/user/ad.jsp";
    }



    @RequiresPermissions({"系统权限_修改","系统权限_添加"})
    @RequestMapping(value = "/user/addOrUpdate")
    @ResponseBody
    public ResultMsg addOrUpdateUser(CUser user, HttpSession session, HttpServletResponse resp, HttpServletRequest req){
        try {
            if (user.getCreatetime() == null || user.getCreatetime().equals("")) {
                if (logservice.getUserByName(user) != null) {
                    return new ResultMsg(500, "该用户名已存在！");
                }
                if (1 == us.insert(user))
                    return new ResultMsg(200, "用户已添加！");
                return new ResultMsg(500, "添加失败！系统异常！");
            }
            Subject subject = SecurityUtils.getSubject();
            if (us.update(user) == 1) {

                if (((CUser) (subject.getPrincipal())).getUsername().equals(user.getUsername()) && (user.getStatus() + "").equals("0")) {
                    try {


                        return new ResultMsg(300, "已被踢出请重新登录！");
                    } catch (Exception e) {
                        e.printStackTrace();
                        return new ResultMsg(500, "修改失败！");
                    }

                }
                return new ResultMsg(200, "用户已修改！");
            }
            return new ResultMsg(500, "修改失败！");
        }catch (Exception e){
            return new ResultMsg(500,"修改失败！系统错误！");
        }
    }


    @RequiresPermissions({"系统权限_修改","系统权限_添加"})
    @RequestMapping(value="/user/editRoles", method = RequestMethod.POST)
    @ResponseBody
    public List<TreeNode> empAddAndUpdate(Integer id,Model model, HttpSession session){

        List<TreeNode> list = new ArrayList<>();
        List<TreeNode> sonlist = new ArrayList<>();
        TreeNode node = new TreeNode();
        node.setId(0l);
        node.setText("(全部角色)");
        if(id!=null){
            CUser user = new CUser();
            user.setId(Long.parseLong(id+""));
            ArrayList<CRole> roles = (ArrayList<CRole>)logservice.queryRolesByUserId(user);
            ArrayList<CRole> allRoles = (ArrayList<CRole>) roleService.queryAllRoles();
            if(null!=roles){
                for(CRole r : roles){
                    TreeNode son = new TreeNode();

                    son.setId((r.getId()));
                    son.setText(r.getRolename());
                    son.setChecked(true);
                    sonlist.add(son);
                }
            }
            if(allRoles != null) {
                if(roles!=null)
                    System.out.println(roles);
                    allRoles.removeAll(roles);
                System.out.println(allRoles);
                if(!allRoles.isEmpty()) {
                   for (CRole role : allRoles) {
                       TreeNode son = new TreeNode();
                       son.setId((role.getId()));
                       son.setText(role.getRolename());
                       //node.setChecked(true);
                       sonlist.add(son);

                   }
                }
            }
            node.setChildren(sonlist);
            System.out.println(sonlist);
        }
        list.add(node);
        System.out.println(list);
        return list;
    }
    @RequiresPermissions({"系统权限_修改","系统权限_添加"})
    @RequestMapping(value = "/user/addOrSubRole",method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg addOrSubRole(Integer userid,Integer roleid,Integer flag){
        /*删除角色*/
        try {
            roleService.addOrDelUsersRole(new CUserRole(null,Long.parseLong(userid+""),Long.parseLong(roleid+"")),flag);
            return new ResultMsg(200,"操作成功！");
        }catch (Exception e){
            return new ResultMsg(500,"操作失败系统错误！");
        }


    }


    @RequiresPermissions({"系统权限_删除"})
    @RequestMapping(value = "/user/remove",method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg uremove(@RequestBody List<Long> ids){
        /*删除角色*/
        try{
            us.deleteByPrimaryKeys(ids);
            return new ResultMsg(200,"删除 "+ids.size()+" 条记录成功！");
        } catch(Exception e){
            e.printStackTrace();
            return new ResultMsg(500,e.getMessage());
        }
    }

}
