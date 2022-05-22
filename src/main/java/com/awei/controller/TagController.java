package com.awei.controller;


import com.awei.entity.Tag;
import com.awei.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
public class TagController {
    @Autowired
    private ITagService tagService;

    @RequestMapping("/tags")
    public ModelAndView list(@RequestParam(value = "page", required = false, defaultValue = "1") String num,
                             ModelAndView modelAndView) {
        if (num == null) {
            num = "1";
        }
        int num1 = Integer.parseInt(num);
        modelAndView.addObject("page", tagService.listTag(num1));
        modelAndView.setViewName("admin/tags");
        return modelAndView;
    }

    @RequestMapping("/tags/input")
    public ModelAndView input(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/tag-input");
        modelAndView.addObject("tag", new Tag());
        return modelAndView;
    }

    @RequestMapping("/tags/{tagid}/input")
    public ModelAndView editInput(@PathVariable Long tagid, ModelAndView modelAndView) {
        modelAndView.addObject("tag", tagService.getTag(tagid));
        modelAndView.setViewName("admin/tag-input");
        return modelAndView;
    }

    @RequestMapping("/tags/save")
    public ModelAndView save(@Valid Tag tag, BindingResult result,
                             ModelAndView modelAndView, RedirectAttributes attributes) {
        Tag tag1 = tagService.getTagByName(tag.getTagname());
        if (tag1 != null) {
            result.rejectValue("tagname", "nameError", "不能添加重复信息");
        }
        if (result.hasErrors()) {
            modelAndView.setViewName("admin/tag-input");
            return modelAndView;
        }
        Tag tag2 = tagService.saveTag(tag);
        if (tag2 == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        modelAndView.setViewName("redirect:/user/tags");
        return modelAndView;
    }
    @RequestMapping("/tags/{tagid}")
    public ModelAndView edit(@Valid Tag tag,BindingResult result,
                             @PathVariable Long tagid, ModelAndView modelAndView, RedirectAttributes attributes) {
        Tag tag1 = tagService.getTagByName(tag.getTagname());
        if (tag1 != null) {
            result.rejectValue("tagname", "nameError", "不能添加重复信息");
        }
        if (result.hasErrors()) {
            modelAndView.setViewName("admin/tag-input");
            return modelAndView;
        }
        Tag tag2 = tagService.updateTag(tagid,tag);
        if (tag2 == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        modelAndView.setViewName("redirect:/user/tags");
        return modelAndView;
    }
    @RequestMapping("/tags/{tagid}/delete")
    public ModelAndView delete(@PathVariable Long tagid, ModelAndView modelAndView, RedirectAttributes attributes){
        int i = tagService.deleteTag(tagid);
        if (i < 1){
            attributes.addFlashAttribute("message", "删除失败");
        } else {
            attributes.addFlashAttribute("message", "删除成功");
        }
        modelAndView.setViewName("redirect:/user/tags");
        return modelAndView;
    }
}

