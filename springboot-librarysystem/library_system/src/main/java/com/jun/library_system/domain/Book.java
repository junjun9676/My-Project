package com.jun.library_system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;

@Data
@NoArgsConstructor
@TableName("libraryitems")
public class Book {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String type;
    private String name;
    @TableField(jdbcType = JdbcType.VARCHAR)
    private String description;

}
