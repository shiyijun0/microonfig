package com.jwk.project.monitor.online.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.monitor.online.domain.UserOnline;

/**
 * 在线用户数据层
 * 
 * @author system
 */
@Repository("userOnlineDao")
public class UserOnlineDaoImpl extends DynamicObjectBaseDao implements IUserOnlineDao
{
    /**
     * 通过会话序号查询信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Override
    public UserOnline selectOnlineById(String sessionId)
    {
        return this.findForObject("SystemOnlineMapper.selectOnlineById", sessionId);
    }

    /**
     * 通过会话序号删除信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Override
    public int deleteOnlineById(String sessionId)
    {
        return this.delete("SystemOnlineMapper.deleteOnlineById", sessionId);
    }

    /**
     * 保存会话信息
     * 
     * @param online 会话信息
     */
    @Override
    public int saveOnline(UserOnline online)
    {
        return this.save("SystemOnlineMapper.saveOnline", online);
    }

    /**
     * 查询会话集合
     * 
     * @param pageUtilEntity 分页参数
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return findForList("SystemOnlineMapper.pageInfoQueryUserOnline", pageUtilEntity);
    }

    /**
     * 查询过期会话集合
     * 
     * @param lastAccessTime 过期时间
     */
    @Override
    public List<UserOnline> selectOnlineByExpired(String lastAccessTime)
    {
        List<UserOnline> userOnlineList = null;
        try
        {
            userOnlineList = this.findForList("SystemOnlineMapper.selectOnlineByExpired", lastAccessTime);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userOnlineList;
    }
}
