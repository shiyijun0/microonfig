package com.jwk.project.system.index.service;



import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.dao.IWebQuestionDao;
import com.jwk.project.system.index.domain.SysWebQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 常见问题解答业务层处理
 * 
 * @author 陈志辉
 */
@Service("webQuestionService")
public class WebQuestionServiceImpl implements IWebQuestionService
{

    @Autowired
    private IWebQuestionDao webQuestionDao;



    /**
     * 根据条件分页查询常见问题解答数据
     *
     * @param pageUtilEntity 分页对象
     * @return 常见问题解答数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webQuestionDao.pageInfoQuery(pageUtilEntity);
    }


    /**
     * 通过常见问题解答ID查询常见问题解答
     *
     * @param id 常见问题解答ID
     * @return 常见问题解答信息
     */
    @Override
    public SysWebQuestion selectSysWebQuestionById(int id)
    {
        return webQuestionDao.selectSysWebQuestionById(id);
    }


    /**
     * 前端查询常见问题解答
     *
     */
    @Override
    public SysWebQuestion selectSysWebQuestionAll()
    {
        return webQuestionDao.selectWebQuestionAll();
    }


    /**
     * 保存常见问题解答信息
     *
     * @param sysWebQuestion 常见问题解答信息
     * @return 结果
     */
    @Override
    public int saveSysWebQuestion(SysWebQuestion sysWebQuestion)
    {
        return webQuestionDao.updateSysWebQuestion(sysWebQuestion);

    }




}
