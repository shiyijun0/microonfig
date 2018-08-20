package com.jwk.project.system.index.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebQuestion;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 常见问题解答 数据层处理
 *
 * @author 陈志辉
 */
@Repository("webQuestionDao")
public class WebQuestionDaoImpl extends DynamicObjectBaseDao implements IWebQuestionDao
{

    /**
     * 根据条件分页查询常见问题解答数据
     *
     * @param pageUtilEntity 分页对象
     * @return 常见问题解答数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemSysWebQuestionMapper.pageInfoQuery", pageUtilEntity);
    }


    /**
     * 前端查询常见问题解答
     * @return
     */

    @Override
    public SysWebQuestion selectWebQuestionAll()
    {
        List<SysWebQuestion> WebQuestionList = null;
        try
        {
            WebQuestionList = this.findForList("SystemSysWebQuestionMapper.selectWebQuestionAll");
            return WebQuestionList.get(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 通过常见问题解答ID查询常见问题解答
     *
     * @param id 常见问题解答ID
     * @return 常见问题解答信息
     */
    @Override
    public SysWebQuestion selectSysWebQuestionById(int id) {
        return this.findForObject("SystemSysWebQuestionMapper.selectSysWebQuestionById", id);
    }


    /**
     * 保存常见问题解答信息
     *
     * @param sysWebQuestion 常见问题解答信息
     * @return 结果
     */
    @Override
    public int updateSysWebQuestion(SysWebQuestion sysWebQuestion)
    {
        return this.update("SystemSysWebQuestionMapper.updateSysWebQuestion", sysWebQuestion);
    }

  


}
