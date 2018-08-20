package com.jwk.project.system.coupon.dao;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.coupon.domain.Coupon;
import com.jwk.project.system.coupon.domain.CouponDetails;
import com.jwk.project.system.coupon.domain.CouponUser;

import java.util.List;

/**
 * 优惠券表 数据层
 * 
 * @author 陈志辉
 */
public interface ICouponDao
{

    /**
     * 根据条件分页查询优惠券数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 优惠券数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 通过优惠券ID删除优惠券
     *
     * @param couponId 优惠券ID
     * @return 结果
     */
    public int deleteCouponById(Long couponId);



    /**
     * 通过优惠券ID删除优惠券详情信息
     *
     * @param cid 优惠券ID
     * @return 结果
     */
    public int deleteCouponDetatilsById(Long cid);


    /**
     * 批量删除优惠券信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteCoupon(Long[] ids);


    /**
     * 通过优惠券ID查询优惠券
     *
     * @param couponId 优惠券ID
     * @return 优惠券信息
     */

    public Coupon selectCouponById(Long couponId);



    /**
     * 通过优惠码查询优惠券
     *
     * @param code
     * @return 优惠券信息
     */
    public Coupon selectCouponByCode(String code);


    /**
     * 通过用户ID查询绑定的优惠券
     *
     * @param userId 用户ID
     * @return 优惠券信息
     */
    public List<CouponUser> selectCouponUserById(Long userId) throws Exception;

    /**
     * 通过用户ID查询绑定的优惠券
     *
     * @param id 用户ID
     * @return 优惠券信息
     */
    public CouponUser selectCouUserById(Long id) ;


    /**
     * 通过优惠券cid查询优惠券详情
     *
     * @param cid
     * @return 优惠券信息
     */
    public List<CouponDetails> selectCouponDetailsByCid(Long cid) throws Exception;

    /**
     * 通过优惠码id查询优惠券详情
     *
     * @param id
     * @return 优惠券信息
     */
    public  CouponDetails selectCouponDetailsById(Long id) ;

    /**
     * 保存优惠券信息
     *
     * @param coupon 优惠券信息
     * @return 结果
     */

    public int updateCoupon(Coupon coupon);


    /**
     * 保存优惠券详细信息
     *
     * @param coupon 优惠券信息
     * @return 结果
     */
    public int updateCouponDetails(CouponDetails coupon);


    /**
     * 新增优惠券信息
     *
     * @param coupon 优惠券信息
     * @return 结果
     */

    public int insertCoupon(Coupon coupon);



    /**
     * 新增优惠券详情信息
     *
     * @param couponDetails 优惠券详情信息
     * @return 结果
     */
    public int insertCouponDetatils(CouponDetails couponDetails);



    /**
     * 优惠券绑定用户
     *
     * @param couponUser 优惠券信息
     * @return 结果
     */
    public int insertCouponUser(CouponUser couponUser);
    
    public List<CouponDetails> checkCouponDetails(CouponDetails coupon);
    /**
     * 优惠券绑定用户
     *
     * @param couponUser 优惠券信息
     * @return 结果
     */
    public int updateCouponUser(CouponUser couponUser);
}
