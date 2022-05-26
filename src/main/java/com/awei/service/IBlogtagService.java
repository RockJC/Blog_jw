package com.awei.service;

import com.awei.entity.Blogtag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-03
 */
public interface IBlogtagService{
    int insertListTags(Long blogid, List<Integer> tagIds);
    int updateListByBlogId(Long blogid,List<Integer> tagIds);
}
