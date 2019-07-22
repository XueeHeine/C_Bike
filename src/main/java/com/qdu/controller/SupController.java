package com.qdu.controller;

import com.qdu.beans.Bicycle;
import com.qdu.beans.Sup;
import com.qdu.service.SupService;
import com.qdu.utils.ResultMsg;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2019/4/16.
 */
@RequestMapping(value="/sup")
@Controller
public class SupController {
    @Autowired
    private SupService ss;

    @RequestMapping(value="/list")
    public String list(){
        return "bike/list12";
    }

    @RequestMapping(value="/empAddAndUpdate",method= RequestMethod.GET)
    public String empAddAndUpdate(Integer id,Model m){
        if(id!=null){
            Sup bicycle =  ss.getSupById(id);
            m.addAttribute("b", bicycle);
        }

        return "/bike/addAndUpdate1";
    }

    \

    @RequiresPermissions({"单车权限_修改","单车权限_添加"})
    @ResponseBody
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public ResultMsg add(Sup sup){


        try{
            ss.doAdd(sup);
            return new ResultMsg(200,"新增供应商"+sup.getSname()+"成功");
        }catch(Exception e){
            e.printStackTrace();
            return new ResultMsg(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value="/query",method=RequestMethod.POST)
    public Map<String,Object> query(Integer page, Integer rows, @RequestParam(value="sname",defaultValue="") String sname, @RequestParam(value="priority",defaultValue="-1") Integer priority){

        return ss.query(page, rows, sname,priority);

    }

    @RequiresPermissions({"单车权限_修改","单车权限_删除"})
    @ResponseBody
    @RequestMapping(value="/remove",method=RequestMethod.POST)
    public ResultMsg remove(@RequestBody List<Integer> ids){


        try{

            int a = ss.doRemove(ids);
            if(a!=0){
                return new ResultMsg(500,"删除失败！");
            }else{
                return new ResultMsg(200,"成功删除");
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ResultMsg(500,e.getMessage());
        }

    }
    @RequiresPermissions({"单车权限_修改","单车权限_删除"})
    @ResponseBody
    @RequestMapping(value="/remove1",method=RequestMethod.POST)
    public ResultMsg remove1(Integer idd){

        List<Integer> list = new ArrayList<Integer>();
        list.add(idd);
        int a = ss.doRemove(list);
        if(a!=0){
            return new ResultMsg(null,"删除失败！");
        }

        return new ResultMsg(null,"成功删除员工信息！");

    }
    @RequiresPermissions({"单车权限_修改"})
    @ResponseBody
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public ResultMsg update(Sup bicycle){

        try{
            ss.doUpdate(bicycle);
            return new ResultMsg(200,"更新成功!");
        }catch(Exception e){
            e.printStackTrace();
            return new ResultMsg(500,e.getMessage());
        }

    }
}
