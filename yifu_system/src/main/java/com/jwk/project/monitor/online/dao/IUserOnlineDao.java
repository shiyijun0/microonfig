package com.jwk.project.monitor.online.dao;

import java.util.List;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.monitor.online.domain.UserOnline;

/**
 * 在线用户 数据层
 * 
 * @author system
 */
public interface IUserOnlineDao
{
    /**
     * 通过会话序号查询信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public UserOnline selectOnlineById(String sessionId);

    /**
     * 通过会话序号删除信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public int deleteOnlineById(String sessionId);

    /**
     * 保存会话信息
     * 
     * @param online 会话信息
     * @return 结果
     */
    public int saveOnline(UserOnline online);

    /**
     * 查询会话集合
     * 
     * @param pageUtilEntity 分页参数
     * @return 会话集合
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 查询过期会话集合
     * 
     * @param lastAccessTime 过期时间
     * @return 会话集合
     */
    public List<UserOnline> selectOnlineByExpired(String lastAccessTime);
}
