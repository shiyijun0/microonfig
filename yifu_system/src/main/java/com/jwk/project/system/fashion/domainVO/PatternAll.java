package com.jwk.project.system.fashion.domainVO;

import lombok.Data;

@Data
public class PatternAll {
	private String fqId;//配件区域
    private String name;//配件名称
    private String num;//配件编码
    private String price;
    private AreaLimit areaLimit;
    private String icopicurl;
    private String attachmenturl;
   
    
    public PatternAll(String fqId, String name, String num, String price,
			AreaLimit areaLimit, String icopicurl, String attachmenturl) {
		super();
		this.fqId = fqId;
		this.name = name;
		this.num = num;
		this.price = price;
		this.areaLimit = areaLimit;
		this.icopicurl = icopicurl;
		this.attachmenturl = attachmenturl;
	}


	@Data
    public static class AreaLimit{
    	private String X;
        private String Y;
        private String Z;
		public AreaLimit(String x, String y, String z) {
			super();
			X = x;
			Y = y;
			Z = z;
		}
        
        
    }
}
