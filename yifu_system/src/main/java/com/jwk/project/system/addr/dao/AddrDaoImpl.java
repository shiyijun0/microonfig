package com.jwk.project.system.addr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.project.system.addr.domain.Addr;

/**
 * 用户地址管理 数据实现层
 * 
 * @author system
 */
@Repository("addrDao")
public class AddrDaoImpl extends DynamicObjectBaseDao implements IAddrDao {

	/**
	 * 检查是否存在默认地址
	 */
	@Override
	public int checkDefaultAddr(Long userId) {
		return this.count("SystemAddrMapper.checkDefaultAddr", userId);
	}

	/**
	 * 查询所有地址
	 */
	@Override
	public List<Addr> selectAddrAll(Long userId){
		List<Addr> addrList = null;
		try {
			addrList = this.findForList("SystemAddrMapper.selectAddrAll",userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addrList;
	}
	
	/**
	 * 查询对象地址
	 */
	@Override
	public List<Addr> selectAddrs(Addr addr) {
		List<Addr> addrList = null;
		try {
			addrList = this.findForList("SystemAddrMapper.selectAddrs",addr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addrList;
	}

	/**
	 * 根据地址id删除地址
	 */
	@Override
	public int deleteAddrById(Long addrId) {
		return this.delete("SystemAddrMapper.deleteAddrById", addrId);
	}

	/**
	 * 新增收货地址
	 */
	@Override
	public int insertAddr(Addr addr) {
		return this.save("SystemAddrMapper.insertAddr", addr);
	}

	/**
	 * 修改收货地址
	 */
	@Override
	public int updateAddr(Addr addr) {
		return this.save("SystemAddrMapper.updateAddr", addr);
	}

	/**
	 * 查询单笔用户收货地址
	 */
	@Override
	public Addr selectAddrById(Long addrId) {
		return this.findForObject("SystemAddrMapper.selectAddrById", addrId);
	}





	/**
     * 根据地址id设置默认地址
     * 
     * @param addrId 地址信息
     * @return 结果
     */
	@Override
	public int updateDefaultFlag(Long addrId) {
		return this.update("SystemAddrMapper.updateDefaultFlag", addrId);
	}

	/**
     * 根据用户id取消默认地址
     * 
     * @param userId 地址信息
     * @return 结果
     */
	@Override
	public int cancelDefaultFlag(Long userId) {
		return this.update("SystemAddrMapper.cancelDefaultFlag", userId);
	}

	/**
	 * 根据用户id查询默认地址
	 */
	@Override
	public Addr selectDeafultByUserId(Long userId) {
		return this.findForObject("SystemAddrMapper.selectDeafultByUserId", userId);
	}
}
