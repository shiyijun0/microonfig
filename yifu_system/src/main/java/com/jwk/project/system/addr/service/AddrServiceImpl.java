package com.jwk.project.system.addr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwk.common.utils.StringUtils;
import com.jwk.project.system.addr.dao.IAddrDao;
import com.jwk.project.system.addr.domain.Addr;

/**
 * 收货地址管理  服务实现
 * 
 * @author system
 */
@Repository("addrService")
public class AddrServiceImpl implements IAddrService
{
    @Autowired
    private IAddrDao addrDao;

	@Override
	public List<Addr> selectAddrAll(Long userId) throws Exception {
		if (userId != null && userId > 0) {
			return addrDao.selectAddrAll(userId);
		}else
			return null;
	}

	@Override
	public boolean checkDefaultAddr(Long userId) {
		 int result =addrDao.checkDefaultAddr(userId);
	     return result > 0 ? true : false;
	}

	@Override
	public int deleteAddrById(Long addrId) {
		return addrDao.deleteAddrById(addrId);
	}

	@Override
	public Addr selectAddrById(Long addrId) {
		return addrDao.selectAddrById(addrId);
	}

	@Override
	public int saveAddr(Addr addr) {
		if (StringUtils.isNotNull(addr.getAddrId()))
        {
            return addrDao.updateAddr(addr);
        }
        else
        {
            return addrDao.insertAddr(addr);
        }
	}

	@Override
	public int updateDefaultFlag(Long addrId) {
		return addrDao.updateDefaultFlag(addrId);
	}

	@Override
	public int cancelDefaultFlag(Long userId) {
		return addrDao.cancelDefaultFlag(userId);
	}

	/**
     * 根据用户id查询默认地址
     */
	@Override
	public Addr selectDeafultByUserId(Long userId) {
		return addrDao.selectDeafultByUserId(userId);
	}
}
