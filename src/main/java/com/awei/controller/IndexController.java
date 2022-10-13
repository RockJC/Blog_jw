package com.awei.controller;

import com.awei.entity.Blog;
import com.awei.service.IBlogService;
import com.awei.service.ITagService;
import com.awei.service.ITypeService;
import com.awei.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jiangwei
 * @create 2022-05-05 22:15
 */
@RestController
public class IndexController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ITypeService typeService;
    @Autowired
    private ITagService tagService;
    @Autowired
    private IUserService userService;


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(value = "page", required = false, defaultValue = "1") String num,
                              Blog blog,ModelAndView modelAndView){
        if (num == null||num == "0") {
            num = "1";
        }
        int num1 = Integer.parseInt(num);
        modelAndView.addObject("page",blogService.listIndexBlog(num1,null));
        modelAndView.addObject("types",typeService.listTypeTop(6));
        modelAndView.addObject("tags",tagService.listTagTop(10));
        modelAndView.addObject("blogs",blogService.listIndexBlogByTime(8));
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam(value = "page", required = false, defaultValue = "1") String num,
                         @RequestParam String query,Blog blog,ModelAndView modelAndView){
        if (num == null||num == "0") {
            num = "1";
        }
        int num1 = Integer.parseInt(num);
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",query).or().like("content",query).or().like("description",query);
        modelAndView.addObject("page",blogService.listIndexBlog(num1,queryWrapper));
        modelAndView.addObject("query",query);
        modelAndView.setViewName("search");
        return modelAndView;

    }

    @RequestMapping("/blog/{id}")
    public ModelAndView blog(@PathVariable Long id,ModelAndView modelAndView){
        Blog blogs = blogService.getAndConveryBlog(id);
        modelAndView.addObject("blogs",blogs);
        System.out.println(blogs.getUser_id());
        modelAndView.addObject("user",userService.getUserById(blogs.getUser_id()));
        modelAndView.setViewName("blog");
        return modelAndView;
    }

}
