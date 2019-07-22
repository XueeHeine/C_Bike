package com.qdu.controller;

import com.qdu.beans.CModule;
import com.qdu.beans.CRole;
import com.qdu.beans.CRoleModule;
import com.qdu.service.ModuleService;
import com.qdu.service.RoleService;
import com.qdu.utils.ResultMsg;
import com.qdu.utils.TreeNode;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Alpha-LGC on 2019/4/10.
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService rs;

    @Autowired
    private ModuleService ms;

    @RequestMapping(value ="/roleinfo")
    public String goRoleInfo(){
        return "redirect:/pages/user/roleinfo.jsp";
    }


    @RequestMapping(value = "/query")
    @ResponseBody
    public Map<String,Object> query(Integer page, Integer rows){
        return rs.queryAllRolesPage(page, rows);
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    public ResultMsg add(CRole role){
        System.out.println(role);
        try{

            int falg =  rs.addRoleByName(role);
            if (falg ==1)
                return new ResultMsg(200,"添加成功");
            return new ResultMsg(300,"角色未添加请查看角色是否已存在！");
        }catch (Exception e){
            return new ResultMsg(500,"添加失败！系统错误！");
        }
    }

    @RequiresPermissions({"系统权限_删除"})
    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg uremove(@RequestBody List<Long> ids){
        /*删除角色*/
        try{
            rs.batchdeleteRolesById(ids);
            return new ResultMsg(200,"删除 "+ids.size()+" 条记录成功！");
        }catch(Exception e){
            e.printStackTrace();
            return new ResultMsg(500,e.getMessage());
        }
    }


    @RequestMapping(value = "addOrSubModules")
    @ResponseBody
    public ResultMsg addOrNotModules(CRoleModule cm,Integer flag){
        try {
            rs.addRolesModules(cm, flag);
            return new ResultMsg(200,"操作成功");
        }catch (Exception e){
            return new ResultMsg(500,"系统错误,修改失败！");
        }
    }

    @RequiresPermissions({"系统权限_修改","系统权限_添加"})
    @RequestMapping(value="/editModules", method = RequestMethod.POST)
    @ResponseBody
    public List<TreeNode> editModules(CRole role){
        List<TreeNode> list = new ArrayList<>();
        List<TreeNode> xitonglist = new ArrayList<>();
        List<TreeNode> danchelist = new ArrayList<>();
        List<TreeNode> baobiaolist = new ArrayList<>();
        List<TreeNode> qitalist = new ArrayList<>();
        TreeNode node1 = new TreeNode();
        node1.setId(-1l);
        node1.setText("(系统权限)");
        TreeNode node2 = new TreeNode();
        node2.setId(-2l);
        node2.setText("(单车权限)");
        TreeNode node3 = new TreeNode();
        node3.setId(-3l);
        node3.setText("(报表权限)");
        TreeNode node4 = new TreeNode();
        node4.setId(-4l);
        node4.setText("(其他权限)");
        if(role!=null){
            ArrayList<CModule> modules = (ArrayList<CModule>)rs.queryModulesByRoleId(role);
            ArrayList<CModule> allModules = (ArrayList<CModule>) ms.queryAllModules();
            if(null!=modules){
                for(CModule m : modules){
                    TreeNode xitongson = new TreeNode();
                    TreeNode dancheson = new TreeNode();
                    TreeNode baobiaoson = new TreeNode();
                    TreeNode qitason = new TreeNode();
                    if(m.getModulename().contains("系统权限")) {
                        xitongson.setId((m.getId()));
                        xitongson.setText(m.getModulename());
                        xitongson.setChecked(true);
                        xitonglist.add(xitongson);
                    }else if(m.getModulename().contains("单车权限")){
                        dancheson.setId((m.getId()));
                        dancheson.setText(m.getModulename());
                        dancheson.setChecked(true);
                        danchelist.add(dancheson);

                    }else if(m.getModulename().contains("报表权限")){
                        baobiaoson.setId((m.getId()));
                        baobiaoson.setText(m.getModulename());
                        baobiaoson.setChecked(true);
                        baobiaolist.add(baobiaoson);


                    }else if(m.getModulename().contains("其它权限")){
                        qitason.setId((m.getId()));
                        qitason.setText(m.getModulename());
                        qitason.setChecked(true);
                        qitalist.add(qitason);
                    }
                }
            }
            if(allModules != null) {
                if(modules!=null)
                    System.out.println(modules);
                allModules.removeAll(modules);
                System.out.println(allModules);
                if(!allModules.isEmpty()) {
                    for (CModule m : allModules) {
                        TreeNode xitongson = new TreeNode();
                        TreeNode dancheson = new TreeNode();
                        TreeNode baobiaoson = new TreeNode();
                        TreeNode qitason = new TreeNode();
                        if(m.getModulename().contains("系统权限")) {
                            xitongson.setId((m.getId()));
                            xitongson.setText(m.getModulename());
                            xitonglist.add(xitongson);

                        }else if(m.getModulename().contains("单车权限")){
                            dancheson.setId((m.getId()));
                            dancheson.setText(m.getModulename());
                            danchelist.add(dancheson);

                        }else if(m.getModulename().contains("报表权限")){
                            baobiaoson.setId((m.getId()));
                            baobiaoson.setText(m.getModulename());
                            baobiaolist.add(baobiaoson);

                        }else if(m.getModulename().contains("其它权限")){
                            qitason.setId((m.getId()));
                            qitason.setText(m.getModulename());
                            qitalist.add(qitason);
                        }

                    }
                }
            }
            node1.setChildren(xitonglist);
            node2.setChildren(danchelist);
            node3.setChildren(baobiaolist);
            node4.setChildren(qitalist);
            //System.out.println(sonlist);
        }
        Collections.addAll(list,node1,node2,node3,node4);
        System.out.println(list);
        return list;
    }

}
