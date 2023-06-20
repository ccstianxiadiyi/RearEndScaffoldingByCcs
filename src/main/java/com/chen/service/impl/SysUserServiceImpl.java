package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chen.entity.SysUser;
import com.chen.mapper.SysUserMapper;
import com.chen.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ccs
 * @since 2023-06-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser getByUserName(String username) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getUsername,username);
        SysUser sysUser = baseMapper.selectOne(lambdaQueryWrapper);
        return sysUser;
    }
}
