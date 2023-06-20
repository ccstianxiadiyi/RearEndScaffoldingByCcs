package com.chen.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ccs
 * @since 2023-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */

    @TableId(type=IdType.ASSIGN_UUID)
    private Long id;

    private String username;

    private String password;

    private String role;

    private String avatarUrl;


}
