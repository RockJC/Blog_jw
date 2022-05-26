package com.awei.service.impl;

import com.awei.entity.Blogtag;
import com.awei.mapper.BlogtagMapper;
import com.awei.service.IBlogtagService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-03
 */
@Transactional
@Service
public class BlogtagServiceImpl implements IBlogtagService {
    @Autowired
    private BlogtagMapper blogTagMapper;

    @Override
    public int insertListTags(Long blogid, List<Integer> tagIds) {
        Iterator<Integer> l=tagIds.iterator();
        while (l.hasNext()){
            blogTagMapper.insert(new Blogtag(blogid,l.next()));
        }
        return tagIds.size();
    }

    @Override
    public int updateListByBlogId(Long blogsid,List<Integer> tagsIds) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blog_id",blogsid);
        blogTagMapper.delete(queryWrapper);

        return this.insertListTags(blogsid,tagsIds);
    }
}
