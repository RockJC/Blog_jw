package com.awei.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("t_blog")
public class Blog implements Serializable {

    /**
     * 文章ID
     */
    @TableId(value = "blog_id", type = IdType.AUTO)
    private Long blogId;

    /**
     * 文章标题
     */
    @TableField(value = "blog_title")
    private String blogTitle;

    /**
     * 是否开启点赞
     */
    @TableField(value = "blog_appreciation")
    private Boolean blogAppreciation;

    /**
     * 是否开启评论
     */
    @TableField(value = "blog_commentabled")
    private Boolean blogCommentabled;

    /**
     * 文章内容
     */
    @TableField(value = "blog_content")
    private String blogContent;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "blog_cretime")
    private Date blogCretime;

    /**
     * 文章描述
     */
    @TableField(value = "blog_descri")
    private String blogDescri;

    /**
     * 首图地址
     */
    @TableField(value = "blog_firstpic")
    private String blogFirstpic;

    /**
     * 文章来源
     */
    @TableField(value = "blog_flag")
    private String blogFlag;

    /**
     * 是否已发布
     */
    @TableField(value = "blog_published")
    private Boolean blogPublished;

    /**
     * 是否推荐
     */
    @TableField(value = "blog_recommend")
    private Boolean blogRecommend;

    /**
     * 转载状态
     */
    @TableField(value = "blog_statement")
    private Boolean blogStatement;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "blog_updtime")
    private Date blogUpdtime;

    /**
     * 观看次数
     */
    @TableField(value = "blog_views")
    private Integer blogViews;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField("blog_ref_typeid")
    private Integer type_id;

    @TableField("blog_ref_userid")
    private Short user_id;

    @TableField(exist = false)
    private List<Comment> comments;

    @TableField(exist = false)
    private Type type;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Set<Tag> tags;

    @TableField(exist = false)
    private List<Blogtag> blogtags;

    @TableField(exist = false)
    private String TagIds;

    public void init(Set<Tag> tags){
//        this.tags = tags;
        this.TagIds = tagsToIds(tags);
    }

    private String tagsToIds(Set<Tag> tags){
        if (!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags){
                if (flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getTagId());
            }
            return ids.toString();
        }else {
            return TagIds;
        }
    }
}
