package com.awei.service.impl;

import com.awei.NotFoundException;
import com.awei.entity.Blog;
import com.awei.mapper.BlogMapper;
import com.awei.service.IBlogService;
import com.awei.util.MarkdownUtils;
import com.awei.vo.BlogQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.sql.Timestamp;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
@Transactional
@Service
public class BlogServiceImpl implements IBlogService {

    @Autowired
    private BlogMapper blogMapper;


    @Override
    public Object listBlog(Integer num, BlogQuery blog) {
        Page<Blog> page = new Page<>(num,5);

        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();

        if (blog.getTypeid()!=null){
            queryWrapper.eq("type_id",blog.getTypeid());
        }
        if (blog.isRecommend()){
            queryWrapper.eq("recommend",blog.isRecommend());
        }
        if (StringUtils.hasText(blog.getTitle())){
            queryWrapper.and(qw->qw.like("title",blog.getTitle()));
        }

        return blogMapper.BlogListPage(page,queryWrapper);
    }

    @Override
    public Integer saveBlog(Blog blog) {
        System.out.println(blog.getBlogFlag());
        if (blog.getBlogFlag()==null||"".equals(blog.getBlogFlag())){blog.setBlogFlag("原创");}
        if (blog.getBlogId()==null){
            blog.setBlogCretime(new Timestamp(System.currentTimeMillis()));
            blog.setBlogUpdtime(new Timestamp(System.currentTimeMillis()));
            blog.setBlogViews(0);
            blogMapper.insert(blog);
            return 0;
        }else{
            blog.setBlogUpdtime(new Timestamp(System.currentTimeMillis()));
            blogMapper.updateById(blog);
            return 1;
        }
    }

    @Override
    public int deleteBlog(Long id) {
        return blogMapper.deleteById(id);
    }


    @Override
    public Blog getDetailBlog(Long id) {
        return blogMapper.BlogUpdateDetail(id);
    }

    @Override
    public Blog getAndConveryBlog(Long id) {
        Blog blog = blogMapper.BlogUpdateDetail(id);
        if (blog == null){
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getBlogContent();
        blog.setBlogContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return blog;
    }

    @Override
    public String getBlogTitle(Long id) {
       return blogMapper.selectById(id).getBlogTitle();
    }

    @Override
    public Object listIndexBlog(int num1,QueryWrapper<Blog> queryWrapper) {
        Page<Blog> page = new Page<>(num1,6);
        return blogMapper.BlogListIndex(page,queryWrapper);
    }

    @Override
    public Object listIndexBlogByTime(int num) {
        Page<Blog> page = new Page<>(1,num);
        return blogMapper.BlogListIndexByTime(page);
    }
}
