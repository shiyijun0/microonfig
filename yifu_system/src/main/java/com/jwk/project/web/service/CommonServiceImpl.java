package com.jwk.project.web.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwk.common.utils.DateUtils;
import com.jwk.common.utils.StrUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.project.system.limitconfig.dao.ILimitConfigDao;
import com.jwk.project.system.limitconfig.domain.SysLimitConfig;
import com.jwk.project.system.preselljeans.domain.SysPresellJeans;
import com.jwk.project.system.web.dao.WebColorDao;
import com.jwk.project.system.web.dao.WebSizesDao;
import com.jwk.project.system.web.domain.SysWebColor;
import com.jwk.project.system.web.domain.SysWebSizes;

@Repository("commonService")
public class CommonServiceImpl implements ICommonService {

	@Autowired
	WebSizesDao sizeDao;

	@Autowired
	WebColorDao colorDao;

	@Autowired
	ILimitConfigDao configDao;

	/**
	 * 获取牛仔对应的尺码
	 */
	@Override
	public List<SysWebSizes> getSizes(String sizeIds) {
		List<SysWebSizes> webSizesList = new ArrayList<SysWebSizes>();
		// 查询牛仔裤对应的尺码
		if (StrUtils.isNotEmpty(sizeIds)) {
			String[] sizesArr = sizeIds.split(",");
			for (String sizesId : sizesArr) {
				SysWebSizes webSizes = sizeDao.selectSysWebSizesById(Long.valueOf(sizesId));
				webSizesList.add(webSizes);
			}
		}
		Collections.sort(webSizesList, new Comparator<SysWebSizes>() {
			@Override
			public int compare(SysWebSizes s1, SysWebSizes s2) {
				int i = s1.getSize() - s2.getSize();
				return i;
			}
		});
		return webSizesList;
	}

	/**
	 * 获取牛仔对应的颜色
	 */
	@Override
	public List<SysWebColor> getColors(String colorIds) {
		List<SysWebColor> webColorList = new ArrayList<SysWebColor>();
		if (StrUtils.isNotEmpty(colorIds)) {
			String[] colorArr = colorIds.split(",");
			for (String colorId : colorArr) {
				SysWebColor webColor = colorDao.selectwebColorById(Long.valueOf(colorId));
				webColorList.add(webColor);
			}
		}
		return webColorList;
	}

	/**
	 * 获取预售款限量信息
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Override
	public SysPresellJeans getLimitConfig(Long jeansId, SysPresellJeans info) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = DateUtils.dateTimeStr();
		Date ntime = sdf.parse(time);// 当前系统时间

		SysLimitConfig config = configDao.selectUseByPresellId(jeansId);
		if (config != null) {
			if (config.getLimited() == 0)
				config.setLimited(info.getInventory());// 如果没有限量，就默认是库存量
			String startTime = config.getStartTime();
			String endTime = config.getEndTime();
			// 1.限时开始时间，但没有限时结束时间
			if (StringUtils.isNotEmpty(startTime) && StringUtils.isEmpty(endTime)) {
				Date stime = sdf.parse(startTime);
				if (stime.getTime() >= ntime.getTime()) {// 如果限时开始时间大于当前时间
					info.setSysLimitConfig(config);
				}
			}
			// 2.没有限时开始时间，有限时结束时间
			if (StringUtils.isEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
				Date etime = sdf.parse(endTime);
				if (etime.getTime() <= ntime.getTime()) {// 如果限时结束时间小于当前时间
					info.setSysLimitConfig(config);
				}
			}
			// 3.既限时开始时间也限时结束时间
			if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
				Date stime = sdf.parse(startTime);
				Date etime = sdf.parse(endTime);
				if (stime.getTime() <= ntime.getTime() && etime.getTime() >= ntime.getTime()) {// 如果限时结束时间小于当前时间
					info.setSysLimitConfig(config);
				}
			}
		}
		return info;
	}

}
