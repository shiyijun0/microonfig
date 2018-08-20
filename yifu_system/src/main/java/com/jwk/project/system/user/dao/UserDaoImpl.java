package com.jwk.project.system.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.user.domain.User;
import com.jwk.project.system.user.domain.UserRole;

/**
 * 用户 数据层处理
 * 
 * @author system
 */
@Repository("userDao")
public class UserDaoImpl extends DynamicObjectBaseDao implements IUserDao
{

    /**
     * 根据条件分页查询用户对象
     * 
     * @param page 分页对象
     * @return 用户对象信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemUserMapper.pageInfoQuery", pageUtilEntity);
    }

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByName(String username)
    {
        return this.findForObject("SystemUserMapper.selectUserByName", username);
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
        return this.findForObject("SystemUserMapper.selectUserById", userId);
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
        return this.delete("SystemUserMapper.deleteUserById", userId);
    }

    /**
     * 通过用户ID删除用户和角色关联
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserRoleByUserId(Long userId)
    {
        return this.delete("SystemUserRoleMapper.deleteUserRoleByUserId", userId);
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
        return this.delete("SystemUserMapper.batchDeleteUser", ids);
    }

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        return this.update("SystemUserMapper.updateUser", user);
    }

    /**
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int insertUser(User user)
    {
        return this.save("SystemUserMapper.insertUser", user);
    }

    /**
     * 批量新增用户角色信息
     * 
     * @param userRoleList 用户角色列表
     * @return 结果
     */
    @Override
    public int batchUserRole(List<UserRole> userRoleList)
    {
        return this.batchSave("SystemUserRoleMapper.batchUserRole", userRoleList);
    }

    /**
     * 校验用户名称是否唯一
     * 
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public int checkNameUnique(String loginName)
    {
        return this.count("SystemUserMapper.checkNameUnique", loginName);
    }

}
