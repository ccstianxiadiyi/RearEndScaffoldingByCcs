package com.chen.aspect;

import com.chen.annoation.Permission;
import com.chen.entity.SysUser;
import com.chen.exception.NoRoleException;
import com.chen.service.SysUserService;
import com.chen.utils.ArrayUtils;
import com.chen.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Slf4j
@Aspect
public class RoleAspect {
    @Autowired
    private SysUserService userService;
    @Pointcut("@annotation(com.chen.annoation.Permission)")
    public void checkRole(){}
    @Before("checkRole()")
    public void failedOrSuccess(JoinPoint joinPoint){
        log.info("开始鉴权咯-------");
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Permission annotation = signature.getMethod().getAnnotation(Permission.class);
        String[] role = annotation.role();
        Object[] args = joinPoint.getArgs();
        if(args[0] instanceof HttpServletRequest){
            HttpServletRequest request = (HttpServletRequest)args[0];
            String userName = JwtUtils.getMemberNameJwtToken(request);
            SysUser user = userService.getByUserName(userName);
            String userRole = user.getRole();
            boolean exist = ArrayUtils.isExist(role, userRole);
            if(!exist){
                log.info("鉴权失败");
                throw new NoRoleException(20001L,"没有权限");
            }

        }else{
            throw new NoRoleException(20001L,"后台错误");
        }
    }
}
