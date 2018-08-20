package com.jwk.project.system.coupon.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.coupon.domain.Coupon;
import com.jwk.project.system.coupon.domain.CouponDetails;
import com.jwk.project.system.coupon.domain.CouponUser;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 优惠券 数据层处理
 * 
 * @author 陈志辉
 */
@Repository("CouponDao")
public class CouponDaoImpl extends DynamicObjectBaseDao implements ICouponDao
{

    /**
     * 根据条件分页查询优惠券数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 优惠券数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemCouponMapper.pageInfoQuery", pageUtilEntity);
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
        return this.delete("SystemCouponMapper.deleteCouponById", couponId);
    }



    /**
     * 通过优惠券ID删除优惠券详情信息
     *
     * @param cid 优惠券ID
     * @return 结果
     */
    @Override
    public int deleteCouponDetatilsById(Long cid)
    {
        return this.delete("SystemCouponMapper.deleteCouponDetailsById", cid);
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
    	
        return this.delete("SystemCouponMapper.batchDeleteCoupon", ids);
    }
    
    
    


    /**
     * 通过优惠券ID查询优惠券
     *
     * @param couponId 优惠券ID
     * @return 优惠券信息
     */
    @Override
    public Coupon selectCouponById(Long couponId) {
        return this.findForObject("SystemCouponMapper.selectCouponById", couponId);
    }


    /**
     * 通过优惠码查询优惠券
     *
     * @param code
     * @return 优惠券信息
     */
    @Override
    public Coupon selectCouponByCode(String code) {
        return this.findForObject("SystemCouponMapper.selectCouponDetailsByCode", code);
    }


    /**
     * 通过优惠券id查询优惠券详情
     *
     * @param cid
     * @return 优惠券信息
     */
    @Override
    public List<CouponDetails> selectCouponDetailsByCid(Long cid) throws Exception {
        return this.findForList("SystemCouponMapper.selectCouponDetailsByCid", cid);
    }

    /**
     * 通过优惠码id查询优惠券详情
     *
     * @param id
     * @return 优惠券信息
     */
    @Override
    public  CouponDetails selectCouponDetailsById(Long id){
        return this.findForObject("SystemCouponMapper.selectCouponDetailsById", id);
    }

    /**
     * 通过用户ID查询绑定的优惠券
     *
     * @param userId 用户ID
     * @return 优惠券信息
     */
    @Override
    public List<CouponUser> selectCouponUserById(Long userId) throws Exception {
        return this.findForList("SystemCouponMapper.selectCouponUserById", userId);
    }

    @Override
    public CouponUser selectCouUserById(Long id){
        return this.findForObject("SystemCouponMapper.selectCouUserById", id);
    }
    /**
     * 保存优惠券信息
     *
     * @param coupon 优惠券信息
     * @return 结果
     */
    @Override
    public int updateCoupon(Coupon coupon)
    {
        return this.update("SystemCouponMapper.updateCoupon",coupon);
    }


    /**
     * 保存优惠券详细信息
     *
     * @param coupon 优惠券信息
     * @return 结果
     */
    @Override
    public int updateCouponDetails(CouponDetails coupon)
    {
        return this.update("SystemCouponMapper.updateCouponDetails",coupon);
    }


    /**
     * 查找优惠券详细信息
     *
     * @param coupon 优惠券信息
     * @return 结果
     */
    @Override
    public List<CouponDetails> checkCouponDetails(CouponDetails coupon)
    {
    	/*List<CouponDetails> couponDetailsList=new ArrayList<CouponDetails>();
    	 try {
    		 couponDetailsList= this.findForList("SystemCouponMapper.selectCouponDetails", coupon);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
    	 return couponDetailsList;*/
    	return null;
    }
    
    
    
    /**
     * 新增优惠券信息
     *
     * @param coupon 优惠券信息
     * @return 结果
     */
    @Override
    public int insertCoupon(Coupon coupon)
    {
        return this.update("SystemCouponMapper.insertCoupon", coupon);
    }



    /**
     * 新增优惠券详情信息
     *
     * @param couponDetails 优惠券详情信息
     * @return 结果
     */
    @Override
    public int insertCouponDetatils(CouponDetails couponDetails)
    {
        return this.update("SystemCouponMapper.insertCouponDetatils", couponDetails);
    }



    /**
     * 优惠券绑定用户
     *
     * @param couponUser 优惠券信息
     * @return 结果
     */
    @Override
    public int insertCouponUser(CouponUser couponUser)
    {
        return this.update("SystemCouponMapper.insertCouponUser", couponUser);
    }


    /**
     * 优惠券绑定用户
     *
     * @param couponUser 优惠券信息
     * @return 结果
     */
    @Override
    public int updateCouponUser(CouponUser couponUser)
    {
        return this.update("SystemCouponMapper.updateCouponUser", couponUser);
    }


}
