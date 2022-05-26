package com.awei.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.dreamyoung.mprelation.JoinColumn;
import com.github.dreamyoung.mprelation.ManyToOne;
import com.github.dreamyoung.mprelation.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_comment")
public class Comment implements Serializable {
    /**
     * 评论ID
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    /**
     * 评论用户昵称
     */
    @TableField(value = "comment_nickname")
    private String commentNickname;

    /**
     * 评论用户邮箱
     */
    @TableField(value = "comment_email")
    private String commentEmail;

    /**
     * 评论内容
     */
    @TableField(value = "comment_content")
    private String commentContent;

    /**
     * 评论用户头像
     */
    @TableField(value = "comment_avatar")
    private String commentAvatar;

    /**
     * 评论时间
     */
    @TableField(value = "comment_cretime")
    private Date commentCretime;

    /**
     * 评论对应blog
     */
    @TableField(value = "comment_blogid")
    private Long commentBlogid;

    /**
     * 评论父类
     */
    @TableField(value = "comment_parentid")
    private Long commentParentid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private com.awei.entity.Comment parentComment;

    @TableField(exist = false)
    private List<com.awei.entity.Comment> comments = new ArrayList<>();
}
