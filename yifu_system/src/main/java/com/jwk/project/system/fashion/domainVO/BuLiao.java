package com.jwk.project.system.fashion.domainVO;

import lombok.Data;

@Data
public class BuLiao {
	
		private String buliaoID;
        private String num;
        private String className;
        private String price;
        private String attachmentUrl;
        private String name;
        private String iconPicUrl;
        private String des;
        private String leftpocket;
        private String rightpocket;
        private String fNumber;
        private String blimg;
		public BuLiao(String num, String price, String attachmentUrl,
				String name, String iconPicUrl, String des) {
			super();
			this.num = num;
			this.price = price;
			this.attachmentUrl = attachmentUrl;
			this.name = name;
			this.iconPicUrl = iconPicUrl;
			this.des = des;
		}
        
}
