package com.jwk.project.system.fashion.domainVO;

import lombok.Data;
@Data
public class SelectSize {
private String descipt;
private String sizeIdx;
public SelectSize(String descipt, String sizeIdx) {
	super();
	this.descipt = descipt;
	this.sizeIdx = sizeIdx;
}
public SelectSize() {
	super();
	// TODO Auto-generated constructor stub
}


}
