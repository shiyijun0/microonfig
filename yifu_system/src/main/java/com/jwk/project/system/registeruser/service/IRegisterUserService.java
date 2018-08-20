package com.jwk.project.system.registeruser.service;


import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import org.springframework.web.multipart.MultipartFile;


/**
 * 用户业务层
 * 
 * @author 陈志辉
 */
public interface IRegisterUserService
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
     * @param mobile
     * @return 用户信息
     */
    public RegisterUser selectRegisterUserByMobile(String mobile);



    /**
     * 保存用户信息
     *
     * @param registerUser 用户信息
     * @return 结果
     */
    public int saveRegisterUser(RegisterUser registerUser);



    /**
     * 更改用户头像
     *
     * @param registerUser 用户信息
     * @return 结果
     */
    public int saveRegisterUserPhoto(RegisterUser registerUser,MultipartFile file);


    /**
     * 用户密码重置
     *
     * @param registerUser 用户信息
     * @return 结果
     */
    public int saveRegisterUserPassword(RegisterUser registerUser);


    /**
     * 校验手机号名称是否唯一
     *
     * @param mobile 手机号
     * @return
     */
    public String checkMobileUnique(String mobile);


    /**
     * 前端校验用户登陆密码是否正确
     *
     * @param registerUser
     * @return
     */
    public String checkPassword(RegisterUser registerUser);


}
