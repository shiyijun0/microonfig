package com.jwk.project.system.registeruser.dao;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.registeruser.domain.RegisterUser;

/**
 * 用户表 数据层
 * 
 * @author 陈志辉
 */
public interface IRegisterUserDao
{

    /**
     * 根据条件分页查询用户数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 用户数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 通过用户ID删除用户
     *
     * @param id 用户ID
     * @return 结果
     */
    public int deleteRegisterUserById(Long id);


    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteRegisterUser(Long[] ids);


    /**
     * 通过用户ID查询用户
     *
     * @param id 用户ID
     * @return 用户信息
     */

    public RegisterUser selectRegisterUserById(Long id);



    /**
     * 通过用户手机号查询用户
     *
     * @param mobile 用户手机号
     * @return 用户信息
     */
    public RegisterUser selectRegisterUserByMobile(String mobile);



    /**
     * 保存用户信息
     *
     * @param registerUser 用户信息
     * @return 结果
     */

    public int updateRegisterUser(RegisterUser registerUser);



    /**
     * 用户密码重置
     *
     * @param registerUser 用户信息
     * @return 结果
     */
    public int updateRegisterUserPassword(RegisterUser registerUser);


    /**
     * 新增用户信息
     *
     * @param registerUser 用户信息
     * @return 结果
     */

    public int insertRegisterUser(RegisterUser registerUser);


    /**
     * 校验手机号是否唯一
     *
     * @param mobile 手机号
     * @return 结果
     */
    public int checkMobileUnique(String mobile);


    /**
     * 校验用户密码是否正确
     *
     * @param registerUser
     * @return 结果
     */
    public int checkPassword(RegisterUser registerUser);

}
