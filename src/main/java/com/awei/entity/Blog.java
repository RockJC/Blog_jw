package com.awei.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    private String title;

    private Boolean appreciation;

    private Boolean commentabled;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp create_time;

    private String description;

    @TableField("first_picture")
    private String firstPicture;

    private String flag;

    private Boolean published;

    private Boolean recommend;

    @TableField("share_statement")
    private Boolean shareStatement;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp update_time;

    private Integer views;

    @TableField("ref_type_id")
    private Long type_id;

    @TableField("ref_user_id")
    private Long user_id;

    @TableField(exist = false)
    private List<Comment> comments;

    @TableField(exist = false)
    private Type type;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private List<Tag> tags;

    @TableField(exist = false)
    private List<Blogtag> blogtags;

    @TableField(exist = false)
    private String TagIds;

    public void init(List<Tag> tags){
//        this.tags = tags;
        this.TagIds = tagsToIds(tags);
    }

    private String tagsToIds(List<Tag> tags){
        if (!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags){
                if (flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getTagid());
            }
            return ids.toString();
        }else {
            return TagIds;
        }
    }
}
