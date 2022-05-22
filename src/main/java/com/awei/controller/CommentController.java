package com.awei.controller;


import com.awei.entity.Comment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
@RestController
public class CommentController {
    @RequestMapping("/comments/{blog_id}")
    public ModelAndView comments(@PathVariable Long blog_id, ModelAndView modelAndView) {
        modelAndView.addObject("comments","");
        modelAndView.setViewName("blog :: commentList");
        return modelAndView;
    }
    @RequestMapping("/comments")
    public ModelAndView post(Comment comment,ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/comments" + comment.getBlog_id());
        return modelAndView;
    }
}

