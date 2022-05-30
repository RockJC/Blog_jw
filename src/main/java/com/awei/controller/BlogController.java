package com.awei.controller;


import com.awei.entity.Blog;
import com.awei.entity.Blogtag;
import com.awei.entity.Tag;
import com.awei.entity.User;
import com.awei.service.IBlogService;
import com.awei.service.IBlogtagService;
import com.awei.service.ITagService;
import com.awei.service.ITypeService;
import com.awei.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
@RestController
@RequestMapping("/user")
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ITypeService typeService;
    @Autowired
    private ITagService tagService;
    @Autowired
    private IBlogtagService blogTagService;

    @RequestMapping("/blogs")
    public ModelAndView blogs(@RequestParam(value = "page", required = false, defaultValue = "1") String num, BlogQuery blog,
                       ModelAndView modelAndView) {
        if (num == null||"0".equals(num)) {
            num = "1";
        }
        int num1 = Integer.parseInt(num);
        System.out.printf(blog.toString()+"1");
        modelAndView.addObject("types",typeService.listAllType());
        modelAndView.addObject("page",blogService.listBlog(num1,blog));
        modelAndView.setViewName("admin/blogs");
        return modelAndView;
//        return blogService.listBlog(num1,blog);
    }
    @RequestMapping("/blogs/search")
    public ModelAndView search(BlogQuery blog, @RequestParam(value = "page", required = false, defaultValue = "1") String num,
                               ModelAndView modelAndView){
        if (num == null) {
            num = "1";
        }
        int num1 = Integer.parseInt(num);
        modelAndView.addObject("page",blogService.listBlog(num1,blog));
        modelAndView.setViewName("admin/blogs :: blogList");
        return modelAndView;
//        return blogService.listBlog(num1,blog);
    }
    @RequestMapping("/blogs/input")
    public ModelAndView input(ModelAndView modelAndView){
        modelAndView.addObject("types",typeService.listAllType());
        modelAndView.addObject("tags",tagService.listAllTag());
        modelAndView.addObject("blog",new Blog());
        modelAndView.setViewName("admin/blogs-input");
        return modelAndView;
    }
    @RequestMapping("/blogs/{id}/input")
    public Object editInput(@PathVariable Long id,ModelAndView modelAndView){
        modelAndView.addObject("types",typeService.listAllType());
        modelAndView.addObject("tags",tagService.listAllTag());
        Blog blog1 = blogService.getDetailBlog(id);
//        blog1.setBlogTitle(blogService.getBlogTitle(id));
        Set<Tag> tagList = blog1.getTags();
        blog1.init(tagList);
        modelAndView.addObject("blog",blog1);
        modelAndView.setViewName("admin/blogs-input");
        return modelAndView;
    }

    @RequestMapping("/blogs/insert")
    public ModelAndView post(Blog blog, HttpSession session, RedirectAttributes attributes, ModelAndView modelAndView){
        User u1 = (User) session.getAttribute("user");
        blog.setUser_id(u1.getUserId());
        List<Integer> longs = tagService.listTagById(blog.getTagIds());
        int i;
        if (blogService.saveBlog(blog)==0){
            i = blogTagService.insertListTags(blog.getBlogId(), longs);
        }else {
            i = blogTagService.updateListByBlogId(blog.getBlogId(), longs);
        }
        System.out.printf("************"+String.valueOf(blog.isBlogAppreciation()));
        if (i < 1){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        modelAndView.setViewName("redirect:/user/blogs");
        return modelAndView;
    }
    @RequestMapping("/blogs/{id}/delete")
    public ModelAndView delete(@PathVariable Long id,ModelAndView modelAndView,RedirectAttributes attributes){
        int i = blogService.deleteBlog(id);
        if (i < 1){
            attributes.addFlashAttribute("message", "删除失败");
        } else {
            attributes.addFlashAttribute("message", "删除成功");
        }
        modelAndView.setViewName("redirect:/user/blogs");
        return modelAndView;
    }
}

