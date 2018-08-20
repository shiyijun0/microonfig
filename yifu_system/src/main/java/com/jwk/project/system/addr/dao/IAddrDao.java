package com.jwk.project.system.addr.dao;

import java.util.List;

import com.jwk.project.system.addr.domain.Addr;
import org.apache.ibatis.annotations.Param;

/**
 * 收货地址 数据层
 * 
 * @author system
 */
public interface IAddrDao
{

    /**
     * 查询是否存在默认收货地址
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int checkDefaultAddr(Long userId);

    /**
     * 查询所有收货地址
     * 
     * @return 所有部门信息
     */
    public List<Addr> selectAddrAll(@Param("userId") Long userId) throws Exception;

    /**
     * 删除收货地址
     * 
     * @param addrId 地址ID
     * @return 结果
     */
    public int deleteAddrById(Long addrId);

    /**
     * 新增收货信息
     * 
     * @param addr 地址信息
     * @return 结果
     */
    public int insertAddr(Addr addr);

    /**
     * 修改收货信息
     * 
     * @param addr 地址信息
     * @return 结果
     */
    public int updateAddr(Addr addr);
    
    /**
     * 根据地址id设置默认地址
     * 
     * @param addr 地址信息
     * @return 结果
     */
    public int updateDefaultFlag(Long addrId);
    
    /**
     * 根据用户id取消默认地址
     * 
     * @param addr 地址信息
     * @return 结果
     */
    public int cancelDefaultFlag(Long userId);

    /**
     * 根据地址ID查询信息
     * 
     * @param addrId 地址ID
     * @return 部门信息
     */
    public Addr selectAddrById(Long addrId);
    
    public List<Addr> selectAddrs(Addr addr);
    
    
    /**
     * 根据用户id查询默认地址
     */
    public Addr selectDeafultByUserId(Long userId);
}
