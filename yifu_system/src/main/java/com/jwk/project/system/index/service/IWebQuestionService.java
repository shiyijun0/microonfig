package com.jwk.project.system.index.service;


import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebQuestion;


/**
 * 常见问题解答业务层
 *
 * @author 陈志辉
 */
public interface IWebQuestionService
{


    /**
     * 根据条件分页查询常见问题解答数据
     *
     * @param pageUtilEntity 分页对象
     * @return 常见问题解答数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 通过常见问题解答ID查询常见问题解答
     *
     * @param id 常见问题解答ID
     * @return 常见问题解答信息
     */

    public SysWebQuestion selectSysWebQuestionById(int id);


    /**
     * 前端查询常见问题解答
     *
     */
    public SysWebQuestion selectSysWebQuestionAll();


    /**
     * 保存常见问题解答信息
     *
     * @param sysWebQuestion 常见问题解答信息
     * @return 结果
     */
    public int saveSysWebQuestion(SysWebQuestion sysWebQuestion);






}
