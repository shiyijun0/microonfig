package com.jwk.project.system.goods.domain;

import java.util.Date;

import lombok.Data;
@Data
public class JeansFile {
	private int id;
	/** 文件类型 */
    private String type;
    /** 文件大小 */
    private int size;
    /** 保存文件名 */
    private String saveName;
    /** 保存文件路径 */
    private String savePath;
    /** 图片云url */
    private String url;

    /*private String md5;

    private String sha1;*/
    /** 1正常 0禁用 */
    private int status;
    
    private Date ctime;
    
    public static final int FILE_STATUS_SUCCESS = 1;//正常
    public static final int FILE_STATUS_FILE = 0;//禁用

	public JeansFile(String type, int size, String saveName, String savePath,
			int status, Date ctime) {
		super();
		this.type = type;
		this.size = size;
		this.saveName = saveName;
		this.savePath = savePath;
		this.status = status;
		this.ctime = ctime;
	}

	public JeansFile() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
