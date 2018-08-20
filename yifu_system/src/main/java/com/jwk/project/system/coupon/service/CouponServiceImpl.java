package com.jwk.project.system.coupon.service;


import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.coupon.dao.ICouponDao;
import com.jwk.project.system.coupon.domain.Coupon;
import com.jwk.project.system.coupon.domain.CouponDetails;
import com.jwk.project.system.coupon.domain.CouponUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 优惠券 业务层处理
 * 
 * @author 陈志辉
 */
@Service("CouponService")
public class CouponServiceImpl implements ICouponService
{

    @Autowired
    private ICouponDao couponDao;

    /**
     * 根据条件分页查询优惠券数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 优惠券数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return couponDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 通过优惠券ID删除优惠券
     *
     * @param couponId 优惠券ID
     * @return 结果
     */
    @Override
    public int deleteCouponById(Long couponId)
    {
        return couponDao.deleteCouponById(couponId);
    }


    /**
     * 通过优惠券ID删除优惠券详情
     *
     * @param cid 优惠券ID
     * @return 结果
     */
    @Override
    public int deleteCouponDetatilsById(Long cid)
    {
        return couponDao.deleteCouponDetatilsById(cid);
    }


    /**
     * 批量删除优惠券信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteCoupon(Long[] ids)
    {
        return couponDao.batchDeleteCoupon(ids);
    }



    /**
     * 通过优惠券ID查询优惠券
     *
     * @param couponId 优惠券ID
     * @return 优惠券信息
     */
    @Override
    public Coupon selectCouponById(Long couponId)
    {
        return couponDao.selectCouponById(couponId);
    }



    /**
     * 通过优惠码查询优惠券
     *
     * @param code
     * @return 优惠券信息
     */
    @Override
    public Coupon selectCouponByCode(String code)
    {
        return couponDao.selectCouponByCode(code);
    }



    /**
     * 通过优惠码id查询优惠券详情
     *
     * @param cid
     * @return 优惠券信息
     */
    @Override
    public  List<CouponDetails> selectCouponDetailsByCid(Long cid) throws Exception {
        return couponDao.selectCouponDetailsByCid(cid);
    }
    @Override
    public  CouponDetails selectCouponDetailsById(Long id){
        return couponDao.selectCouponDetailsById(id);
    }

    /**
     * 通过用户id查询关联优惠券
     *
     * @param userId
     * @return 优惠券信息
     */
    @Override
    public List<CouponUser> selectCouponUserById(Long userId) throws Exception {
        return couponDao.selectCouponUserById(userId);
    }
    @Override
    public CouponUser selectCouUserById(Long id){
        return couponDao.selectCouUserById(id);
    }
    /**
     * 保存优惠券信息
     *
     * @param Coupon 优惠券信息
     * @return 结果
     */
    @Override
    public int saveCoupon(Coupon Coupon)
    {
        Long couponId = Coupon.getCouponId();
        if (StringUtils.isNotNull(couponId))
        {
            // 修改优惠券的信息
            return couponDao.updateCoupon(Coupon);
        }
        return couponDao.insertCoupon(Coupon);
    }


    /**
     * 保存优惠券详情信息
     *
     * @param couponDetails 优惠券详情信息
     * @return 结果
     */
    @Override
    public int saveCouponDetatils(CouponDetails couponDetails)
    {
        Long couponId = couponDetails.getDetailsId();
        if (StringUtils.isNotNull(couponId))
        {
            // 修改优惠券的信息
            //return couponDao.updateCoupon(Coupon);
        }
        return couponDao.insertCouponDetatils(couponDetails);
    }


    /**
     * 更新优惠券详细信息
     *
     * @param coupon 优惠券信息
     * @return 结果
     */
    @Override
    public int updateCouponDetails(CouponDetails coupon)
    {
            return couponDao.updateCouponDetails(coupon);
    }

    
    


    /**
     * 绑定优惠券和用户
     *
     * @param couponUser 优惠券信息
     * @return 结果
     */
    @Override
    public int saveCouponUser(CouponUser couponUser)
    {
        return couponDao.insertCouponUser(couponUser);
    }


    /**
     * 绑定优惠券和用户
     *
     * @param couponUser 优惠券信息
     * @return 结果
     */
    @Override
    public int updateCouponUser(CouponUser couponUser)
    {
        return couponDao.updateCouponUser(couponUser);
    }



}
