package com.jwk.project.system.order.dominVO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.jwk.project.system.order.domain.SysOrder;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import com.jwk.project.system.user.domain.User;
import com.jwk.project.system.web.domain.SysWebButton;
import com.jwk.project.system.web.domain.SysWebColor;
import com.jwk.project.system.web.domain.SysWebDesigner;
import com.jwk.project.system.web.domain.SysWebJeans;
import com.jwk.project.system.web.domain.SysWebParts;
import com.jwk.project.system.web.domain.SysWebSizes;
import com.jwk.project.system.web.domain.SysWebThread;
import com.jwk.project.system.web.domain.SysWebWash;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebOrderGoodsVO {
	
	

	private List<SysWebSizes> webSizesList;
	
	private List<SysWebColor> webColorList;
	
	private List<SysWebButton> webButtonList;
	
	private List<SysWebThread> webThreadList;
	
	private List<SysWebParts> webPartsList;
	
	private List<SysWebWash> webWashList;
	
    private SysWebJeans webJeans;
    
    private SysWebDesigner webDesigner;
	
	private RegisterUser user;
	
	private SysOrder order;
	
	
	public WebOrderGoodsVO(List<SysWebSizes> webSizesList,
			List<SysWebColor> webColorList, List<SysWebButton> webButtonList,
			List<SysWebThread> webThreadList, List<SysWebParts> webPartsList,
			List<SysWebWash> webWashList) {
		super();
		this.webSizesList = webSizesList;
		this.webColorList = webColorList;
		this.webButtonList = webButtonList;
		this.webThreadList = webThreadList;
		this.webPartsList = webPartsList;
		this.webWashList = webWashList;
	}
	
}
