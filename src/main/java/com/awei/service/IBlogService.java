package com.awei.service;

import com.awei.entity.Blog;
import com.awei.entity.Type;
import com.awei.vo.BlogQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
public interface IBlogService extends IService<Blog> {

    Object listBlog(Integer num, BlogQuery blog);

    Integer saveBlog(Blog blog);

    int deleteBlog(Long id);

    Blog getDetailBlog(Long id);

    Blog getAndConveryBlog(Long id);

    String getBlogTitle(Long id);

    Object listIndexBlog(int num1, QueryWrapper<Blog> queryWrapper);

    Object listIndexBlogByTime(int num);
}
