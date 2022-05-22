package com.awei.service;

import com.awei.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
public interface IUserService extends IService<User> {
    User checkLogin(String username, String password);
    User getUserById(Long id);
}
