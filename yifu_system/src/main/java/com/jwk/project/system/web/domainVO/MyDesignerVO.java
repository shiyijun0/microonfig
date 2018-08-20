package com.jwk.project.system.web.domainVO;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyDesignerVO {

	private Integer id;
	private Integer webDesignerId;
	private String name;
	private String push;
	private String color;
	private String button;
	private  String thread;
	private  String webParts;
	private Map<String,String> parts;
	private String subtime;
	
	
}
