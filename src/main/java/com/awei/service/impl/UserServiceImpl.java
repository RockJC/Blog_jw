package com.awei.service.impl;

import com.awei.entity.User;
import com.awei.mapper.UserMapper;
import com.awei.service.IUserService;
import com.awei.util.MD5Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User checkLogin(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username)
                        .eq("user_password", MD5Utils.code(password));
        User u1 = userMapper.selectOne(queryWrapper);
        return u1;
    }

    @Override
    public User getUserById(Short id) {
        return userMapper.selectById(id);
    }

}
