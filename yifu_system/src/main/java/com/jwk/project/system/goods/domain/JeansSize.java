package com.jwk.project.system.goods.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 牛仔对象 sys_jeans
 * 
 * @author system
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JeansSize {
	private int id;
	/**牛仔裤id */
    private int nzId;
    /** 尺寸大小 */
    private int size;
    /** 描述信息 */
    private String des;
	public JeansSize(int nzId, int size, String des) {
		super();
		this.nzId = nzId;
		this.size = size;
		this.des = des;
	}
    
    
}
