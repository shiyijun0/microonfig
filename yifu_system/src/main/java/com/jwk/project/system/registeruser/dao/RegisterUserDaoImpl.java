package com.jwk.project.system.registeruser.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import org.springframework.stereotype.Repository;

/**
 * 用户 数据层处理
 * 
 * @author 陈志辉
 */
@Repository("registerUserDao")
public class RegisterUserDaoImpl extends DynamicObjectBaseDao implements IRegisterUserDao
{

    /**
     * 根据条件分页查询用户数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 用户数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemRegisterUserMapper.pageInfoQuery", pageUtilEntity);
    }



    /**
     * 通过用户ID删除用户
     *
     * @param id 用户ID
     * @return 结果
     */
    @Override
    public int deleteRegisterUserById(Long id)
    {
        return this.delete("SystemRegisterUserMapper.deleteRegisterUserById", id);
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteRegisterUser(Long[] ids)
    {
        return this.delete("SystemRegisterUserMapper.batchDeleteRegisterUser", ids);
    }


    /**
     * 通过用户ID查询用户
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
    public RegisterUser selectRegisterUserById(Long id) {
        return this.findForObject("SystemRegisterUserMapper.selectRegisterUserById", id);
    }


    /**
     * 通过用户手机号查询用户
     *
     * @param mobile 用户手机号
     * @return 用户信息
     */
    @Override
    public RegisterUser selectRegisterUserByMobile(String mobile) {
        return this.findForObject("SystemRegisterUserMapper.selectRegisterUserByMobile", mobile);
    }



    /**
     * 保存用户信息
     *
     * @param registerUser 用户信息
     * @return 结果
     */
    @Override
    public int updateRegisterUser(RegisterUser registerUser)
    {
        return this.update("SystemRegisterUserMapper.updateRegisterUser", registerUser);
    }



    /**
     * 用户密码重置
     *
     * @param registerUser 用户信息
     * @return 结果
     */
    @Override
    public int updateRegisterUserPassword(RegisterUser registerUser)
    {
        return this.update("SystemRegisterUserMapper.updateRegisterUserPassword", registerUser);
    }





    /**
     * 新增用户信息
     *
     * @param registerUser 用户信息
     * @return 结果
     */
    @Override
    public int insertRegisterUser(RegisterUser registerUser)
    {
        return this.update("SystemRegisterUserMapper.insertRegisterUser", registerUser);
    }


    /**
     * 校验手机号是否唯一
     *
     * @param mobile 手机号
     * @return 结果
     */
    @Override
    public int checkMobileUnique(String mobile)
    {
        return this.count("SystemRegisterUserMapper.checkMobileUnique", mobile);
    }


    /**
     * 校验用户密码是否正确
     *
     * @param registerUser
     * @return 结果
     */
    @Override
    public int checkPassword(RegisterUser registerUser)
    {
        return this.count("SystemRegisterUserMapper.checkPassword",registerUser);
    }


}
