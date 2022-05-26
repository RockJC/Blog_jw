package com.awei.controller;


import com.awei.entity.Type;
import com.awei.service.ITypeService;
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
 * 前端控制器
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
@RestController
@RequestMapping("/user")
public class TypeController {
    @Autowired
    private ITypeService typeService;

    @RequestMapping("/types")
    public ModelAndView list(@RequestParam(value = "page", required = false, defaultValue = "1") String num,
                             ModelAndView modelAndView) {
        if (num == null) {
            num = "1";
        }
        int num1 = Integer.parseInt(num);
        modelAndView.addObject("page", typeService.listType(num1));
        modelAndView.setViewName("admin/types");
        return modelAndView;
    }

    @RequestMapping("/types/input")
    public ModelAndView input(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/type-input");
        modelAndView.addObject("type", new Type());
        return modelAndView;
    }

    @RequestMapping("/types/{typeid}/input")
    public ModelAndView editInput(@PathVariable Long typeid, ModelAndView modelAndView) {
        modelAndView.addObject("type", typeService.getType(typeid));
        modelAndView.setViewName("admin/type-input");
        return modelAndView;
    }

    @RequestMapping("/types/save")
    public ModelAndView save(@Valid Type type, BindingResult result,
                             ModelAndView modelAndView, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getTypeName());
        if (type1 != null) {
            result.rejectValue("typename", "nameError", "不能添加重复信息");
        }
        if (result.hasErrors()) {
            modelAndView.setViewName("admin/type-input");
            return modelAndView;
        }
        Type type2 = typeService.saveType(type);
        if (type2 == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        modelAndView.setViewName("redirect:/user/types");
        return modelAndView;
    }

    @RequestMapping("/types/{typeid}")
    public ModelAndView edit(@Valid Type type, BindingResult result,
                             @PathVariable Long typeid, ModelAndView modelAndView, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getTypeName());
        if (type1 != null) {
            result.rejectValue("typename", "nameError", "不能添加重复信息");
        }
        if (result.hasErrors()) {
            modelAndView.setViewName("admin/type-input");
            return modelAndView;
        }
        Type type2 = typeService.updateType(typeid, type);
        if (type2 == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        modelAndView.setViewName("redirect:/user/types");
        return modelAndView;
    }

    @RequestMapping("/types/{typeid}/delete")
    public ModelAndView delete(@PathVariable Long typeid, ModelAndView modelAndView, RedirectAttributes attributes){
        int i = typeService.deleteType(typeid);
        if (i < 1){
            attributes.addFlashAttribute("message", "删除失败");
        } else {
            attributes.addFlashAttribute("message", "删除成功");
        }
        modelAndView.setViewName("redirect:/user/types");
        return modelAndView;
    }

}

