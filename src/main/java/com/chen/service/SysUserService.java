package com.chen.service;

import com.chen.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ccs
 * @since 2023-06-19
 */
public interface SysUserService extends IService<SysUser> {

    SysUser getByUserName(String username);
}
