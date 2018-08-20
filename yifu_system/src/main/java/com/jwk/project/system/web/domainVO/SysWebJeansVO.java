package com.jwk.project.system.web.domainVO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.jwk.project.system.user.domain.User;
import com.jwk.project.system.web.domain.SysWebButton;
import com.jwk.project.system.web.domain.SysWebColor;
import com.jwk.project.system.web.domain.SysWebJeans;
import com.jwk.project.system.web.domain.SysWebParts;
import com.jwk.project.system.web.domain.SysWebSizes;
import com.jwk.project.system.web.domain.SysWebThread;
import com.jwk.project.system.web.domain.SysWebWash;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysWebJeansVO {
	
	private List<SysWebSizes> webSizesList;
	
	private List<SysWebColor> webColorList;
	
	private List<SysWebButton> webButtonList;
	
	private List<SysWebThread> webThreadList;
	
	private List<SysWebParts> webPartsList;
	
	private List<SysWebWash> webWashList;
	
	
	
}
