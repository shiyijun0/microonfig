package com.jwk.project.system.web.domainVO;

import java.util.List;
import java.util.Map;

import com.jwk.project.system.web.domain.SysWebButton;
import com.jwk.project.system.web.domain.SysWebColor;
import com.jwk.project.system.web.domain.SysWebJeans;
import com.jwk.project.system.web.domain.SysWebParts;
import com.jwk.project.system.web.domain.SysWebSizes;
import com.jwk.project.system.web.domain.SysWebThread;
import com.jwk.project.system.web.domain.SysWebWash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JeansVO {

	private String name;
	
	private Map<String,Object> jeansName;
	
	private List<SysWebJeans> jeansList;
	
	private Integer jeansId;
	
	private Integer type;
	
	private Map<String,Object> colors;
	
	private Map<String,Object> sizes;
	
	
	private Map<String,Object> buttons;
	
	
	private Map<String,Object> threads;
	
	
	private Map<String,Object> washs;
	
	private Map<String,Object> parts1;
	
	private Map<String,Object> parts2;
	
	private Map<String,Object> parts3;
	
	private Map<String,Object> parts4;
	
	private Map<String,Object> parts5;
	
	private Map<String,Object> parts6;
	
	private Map<String,Object> parts7;
	
	private Map<String,Object> parts8;
	
	
   private List<SysWebColor> colorList;
	
	private List<SysWebSizes> sizeList;
	
	
	private List<SysWebButton> buttonList;
	
	
	private List<SysWebThread> threadList;
	
	
	private List<SysWebWash> washList;
	
	private List<SysWebParts> partList1;
	
	private List<SysWebParts> partList2;
	
	private List<SysWebParts> partList3;
	
	private List<SysWebParts> partList4;
	
    private List<SysWebParts> partList5;
	
	private List<SysWebParts> partList6;
	
	private List<SysWebParts> partList7;
	
	private List<SysWebParts> partList8;
	
	
   private SysWebColor color;
	
	private SysWebSizes size;
	
	
	private SysWebButton button;
	
	
	private SysWebThread thread;
	
	
	private SysWebWash wash;
	
	private SysWebParts part1;
	
	private SysWebParts part2;
	
	private SysWebParts part3;
	
	private SysWebParts part4;
	
    private SysWebParts part5;
	
	private SysWebParts part6;
	
	private SysWebParts part7;
	
	private SysWebParts part8;
	
}
