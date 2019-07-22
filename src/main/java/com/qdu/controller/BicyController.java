package com.qdu.controller;



import com.qdu.beans.Bicy;
import com.qdu.service.BicyService;
import com.qdu.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Alpha-LGC on 2018/11/7.
 */
@Controller
@RequestMapping(value="/bicy")
public class BicyController {

    @Autowired
    private BicyService service;

  /*  @RequestMapping(value="/empAddAndUpdate", method = RequestMethod.GET)
    public String empAddAndUpdate(Integer id,Model model){
        if(id!=null){
            Emp e = service.fineEmpById(id);
            model.addAttribute("e",e);
        }

        return "bic/addAndUpdate";
    }
*/


    @ResponseBody
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResultMsg add(Bicy bicy){
        try{
            bicy.setBno(UUID.randomUUID().toString().replaceAll("-",""));
           service.doAdd(bicy);
           return new ResultMsg(200,"新增员工 "+bicy.getBno()+" 成功！");//200表示插入成功了
        }catch(Exception e){
            //e.printStackTrace();
            return new ResultMsg(500,"同一位供应商的型号不能重复");
        }

    }
   /* @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(){
        return "emp/list";
    }
    @ResponseBody
    @RequestMapping(value="/query")
    public Map<String,Object> query(Integer page,Integer rows ,Bicy bicy){//page 跟 rows easyui datagirad自动发送的
        System.out.println(service.query(page,rows,emp));
        return service.query(page,rows,emp);
    }
*/

    @RequestMapping(value="/edlist",method = RequestMethod.GET)
    public String edlist(){
        return "bicy/editablelist1";
    }


    @ResponseBody
    @RequestMapping(value="/queryed")
    public Map<String,Object> queryed(Integer page,Integer rows ,Bicy  bicy){//page 跟 rows easyui datagirad自动发送的
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //System.out.println(bicy.getBxinghao());
        return service.query(page,rows,bicy);
    }




   /* @ResponseBody
    @RequestMapping(value="/changepwd", method = RequestMethod.POST)
    public ResultMsg changepwd(String newpwd,String oldpwd, HttpSession session){
        try{
            Emp emp = (Emp)session.getAttribute("emp");
            emp = service.getEno(emp.getEno());

            if(newpwd.equals(oldpwd)){
                return new ResultMsg(300,"密码修改前后一致！");
            }
            if(emp.getPassword().equals(DigestUtils.md5DigestAsHex(oldpwd.getBytes()))){
                emp.setPassword(DigestUtils.md5DigestAsHex(newpwd.getBytes()));
                service.update(emp);
                return new ResultMsg(200,"修改密码成功！");//200表示插入成功了
            }


            return new ResultMsg(500,"修改失败！");//200表示插入成功了
        }catch(Exception e){
            //e.printStackTrace();
            return new ResultMsg(500,e.getMessage());
        }

    }*/
    @ResponseBody
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public ResultMsg update(Bicy bicy){
        System.out.println(bicy);
        try{
            service.update(bicy);
            return new ResultMsg(200,"修改员工 "+bicy.getBno()+" 成功！");//200表示插入成功了
        }catch(Exception e){
            e.printStackTrace();
            return new ResultMsg(500,"同一型号供应商不可以");
        }

    }

    @ResponseBody
    @RequestMapping(value="/remove", method = RequestMethod.POST)
    public ResultMsg remove(@RequestBody List<Integer> ids){
        System.out.println(ids);
        try{
            service.remove(ids);
            return new ResultMsg(200,"删除 "+ids.size()+" 条记录成功！");//200表示插入成功了
        }catch(Exception e){
            e.printStackTrace();
            return new ResultMsg(500,e.getMessage());
        }

    }
    @ResponseBody
    @RequestMapping(value="/getAllXinghao", method = RequestMethod.POST)
    public List<Map<String,Object>> getAllXinghao(){

      /*  try{
            service.remove(ids);
            return new ResultMsg(200,"删除 "+ids.size()+" 条记录成功！");//200表示插入成功了
        }catch(Exception e){
            e.printStackTrace();
            return new ResultMsg(500,e.getMessage());
        }*/
      List<Map<String,Object>> list = new ArrayList<>();
      Map<String,Object> map = null;
      for (Bicy b : service.getAllXinghao()){
          map = new HashMap<>();
          map.put("id", b.getBno());

          map.put("text",b.getBxinghao());
          list.add(map);
      }
      return list;
    }

}
