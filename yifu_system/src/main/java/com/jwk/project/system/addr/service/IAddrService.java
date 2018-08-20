package com.jwk.project.system.addr.service;

import java.util.List;

import com.jwk.project.system.addr.domain.Addr;

/**
 * 收货地址管理 服务层
 * 
 * @author system
 */
public interface IAddrService {
	/**
	 * 查询所有地址
	 * 
	 * @return 所有地址信息
	 */
	public List<Addr> selectAddrAll(Long userId) throws Exception;

	/**
	 * 查询是否存在默认地址
	 * 
	 * @param userId
	 *            用户ID
	 * @return 结果 true 存在 false 不存在
	 */
	public boolean checkDefaultAddr(Long userId);

	/**
	 * 删除收货地址
	 * 
	 * @param addrId
	 *            地址ID
	 * @return 结果
	 */
	public int deleteAddrById(Long addrId);

	/**
	 * 保存收货信息
	 * 
	 * @param addr
	 *            地址信息
	 * @return 结果
	 */
	public int saveAddr(Addr addr);

	/**
	 * 根据地址ID查询信息
	 * 
	 * @param addrId
	 *            地址ID
	 * @return 部门信息
	 */
	public Addr selectAddrById(Long addrId);

	/**
	 * 根据地址id设置默认地址
	 * 
	 * @param addr
	 *            地址信息
	 * @return 结果
	 */
	public int updateDefaultFlag(Long addrId);

	/**
	 * 根据用户id取消默认地址
	 * 
	 * @param addr
	 *            地址信息
	 * @return 结果
	 */
	public int cancelDefaultFlag(Long userId);
	
	 /**
     * 根据用户id查询默认地址
     */
    public Addr selectDeafultByUserId(Long userId);
}
