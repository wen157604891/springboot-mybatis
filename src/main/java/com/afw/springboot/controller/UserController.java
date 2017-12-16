package com.afw.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.afw.springboot.config.RedisUtils;
import com.afw.springboot.domain.entity.User;
import com.afw.springboot.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	RedisUtils redisUtils;

	/** 返回页面
	 * @author wsw
	 * @date 2017年12月6日 下午8:25:31
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="findInfo",method=RequestMethod.GET)
	public String getUserInfoById(@RequestParam("id") int id,Model model){
		User user=(User)redisUtils.get("findInfo"+id);
		if(user==null){
			user=userService.findUserById(id);
			if(user!=null){
				redisUtils.set("findInfo"+id, user);
			}
		}
		List<String> list =new ArrayList<String>();
		list.add("niu");
		list.add("niu1");
		redisUtils.setList("bootList", list);
		model.addAttribute("user", user);
		return "page/hello";
	}
	
	/** 返回json
	 * @author wsw
	 * @date 2017年12月6日 下午8:28:30
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="findJsonInfo",method=RequestMethod.GET)
	@ResponseBody
	public User getJsonUserInfo(@RequestParam("id") int id){
		User user=userService.findUserById(id);
		return user;
	}
	
	    @RequestMapping(value="/{userId}",method=RequestMethod.GET)
		@ResponseBody
	    public User getUserById(@PathVariable String userId){
    	   System.out.println("id"+userId);
		   User user=userService.findUserById(Integer.parseInt(userId));
	       return user;
	    }
}
