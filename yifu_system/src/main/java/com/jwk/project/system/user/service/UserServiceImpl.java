package com.jwk.project.system.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.constant.UserConstants;
import com.jwk.common.utils.StringUtils;
import com.jwk.common.utils.security.ShiroUtils;
import com.jwk.framework.shiro.service.PasswordService;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.user.dao.IUserDao;
import com.jwk.project.system.user.domain.User;
import com.jwk.project.system.user.domain.UserRole;

/**
 * 用户 业务层处理
 * 
 * @author system
 */
@Service("userService")
public class UserServiceImpl implements IUserService
{

    @Autowired
    private IUserDao userDao;

    /**
     * 根据条件分页查询用户对象
     * 
     * @param page 分页对象
     * 
     * @return 用户信息集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return userDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByName(String userName)
    {
        return userDao.selectUserByName(userName);
    }

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public User selectUserById(Long userId)
    {
        return userDao.selectUserById(userId);
    }

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long userId)
    {
        // 删除用户与角色关联
        userDao.deleteUserRoleByUserId(userId);
        return userDao.deleteUserById(userId);
    }

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteUser(Long[] ids)
    {
        return userDao.batchDeleteUser(ids);
    }

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int saveUser(User user)
    {
        Long userId = user.getUserId();
        String password = new PasswordService().encryptPassword(user.getLoginName(), user.getPassword(), "");
        user.setPassword(password);
        if (StringUtils.isNotNull(userId))
        {
            user.setUpdateBy(ShiroUtils.getLoginName());
            // 修改用户信息
            userDao.updateUser(user);
            // 删除用户与角色关联
            userDao.deleteUserRoleByUserId(userId);
        }
        else
        {
            user.setCreateBy(ShiroUtils.getLoginName());
            // 新增用户信息
            userDao.insertUser(user);
        }
        return insertUserRole(user);
    }
    
    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        String password = new PasswordService().encryptPassword(user.getLoginName(), user.getPassword(), "");
        user.setPassword(password);
        return userDao.updateUser(user);
    }

    /**
     * 新增用户角色信息
     * 
     * @param user 用户对象
     */
    public int insertUserRole(User user)
    {
        int rows = 1;
        // 新增用户与角色管理
        List<UserRole> list = new ArrayList<UserRole>();
        for (Long roleId : user.getRoleIds())
        {
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0)
        {
            rows = userDao.batchUserRole(list);
        }
        return rows;
    }

    /**
     * 校验用户名称是否唯一
     * 
     * @param userName 用户名
     * @return
     */
    @Override
    public String checkNameUnique(String loginName)
    {
        int count = userDao.checkNameUnique(loginName);
        if (count > 0)
        {
            return UserConstants.NAME_NOT_UNIQUE;
        }
        return UserConstants.NAME_UNIQUE;
    }
}
