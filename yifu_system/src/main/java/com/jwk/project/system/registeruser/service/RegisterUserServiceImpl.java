package com.jwk.project.system.registeruser.service;

import com.jwk.common.constant.UserConstants;
import com.jwk.common.utils.FileUtil;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.registeruser.dao.IRegisterUserDao;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * 用户 业务层处理
 * 
 * @author 陈志辉
 */
@Service("registerUserService")
public class RegisterUserServiceImpl implements IRegisterUserService
{

    @Autowired
    private IRegisterUserDao RegisterUserDao;

    /**
     * 根据条件分页查询用户数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 用户数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return RegisterUserDao.pageInfoQuery(pageUtilEntity);
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
        return RegisterUserDao.deleteRegisterUserById(id);
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteRegisterUser(Long[] ids) {
        return RegisterUserDao.batchDeleteRegisterUser(ids);
    }



    /**
     * 通过用户ID查询用户
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
    public RegisterUser selectRegisterUserById(Long id)
    {
        return RegisterUserDao.selectRegisterUserById(id);
    }


    /**
     * 通过用户手机号查询用户
     *
     * @param mobile
     * @return 用户信息
     */
    @Override
    public RegisterUser selectRegisterUserByMobile(String mobile)
    {
        return RegisterUserDao.selectRegisterUserByMobile(mobile);
    }


    /**
     * 保存用户信息
     *
     * @param registerUser 用户信息
     * @return 结果
     */
    @Override
    public int saveRegisterUser(RegisterUser registerUser)
    {
        Long id = registerUser.getId();
        if (StringUtils.isNotNull(id))
        {
            // 修改用户的信息
            return RegisterUserDao.updateRegisterUser(registerUser);
        }
        return RegisterUserDao.insertRegisterUser(registerUser);
    }



    /**
     * 更改用户头像
     *
     * @param registerUser 用户信息
     * @return 结果
     */
    @Override
    public int saveRegisterUserPhoto(RegisterUser registerUser,MultipartFile file)
    {
        Long id = registerUser.getId();
        if (StringUtils.isNotNull(id))
        {
            if(file.getSize()!=0){
                String filename= FileUtil.fileInput(file);
                registerUser.setPortraiturl(filename);
            }

        }
        return RegisterUserDao.updateRegisterUser(registerUser);
    }



    /**
     * 用户密码重置
     *
     * @param registerUser 用户信息
     * @return 结果
     */
    @Override
    public int saveRegisterUserPassword(RegisterUser registerUser)
    {
        return RegisterUserDao.updateRegisterUserPassword(registerUser);
    }



    /**
     * 校验手机号名称是否唯一
     *
     * @param mobile 手机号
     * @return
     */
    @Override
    public String checkMobileUnique(String mobile)
    {
        int count = RegisterUserDao.checkMobileUnique(mobile);
        if (count > 0)
        {
            return UserConstants.NAME_NOT_UNIQUE;
        }
        return UserConstants.NAME_UNIQUE;
    }


    /**
     * 前端校验用户登陆密码是否正确
     *
     * @param registerUser
     * @return
     */
    @Override
    public String checkPassword(RegisterUser registerUser)
    {
        int count = RegisterUserDao.checkPassword(registerUser);
        if (count > 0)
        {
            return UserConstants.NAME_NOT_UNIQUE;
        }
        return UserConstants.NAME_UNIQUE;
    }

}
