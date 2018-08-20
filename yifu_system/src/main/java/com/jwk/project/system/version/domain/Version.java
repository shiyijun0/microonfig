package com.jwk.project.system.version.domain;

import lombok.Data;

/**
 * 系统版本  陈志辉
 */

@Data
public class Version {

    /**版本id */
    private int versionId;
    /**ios版本号 */
    private String ios;
    /**安卓版本号*/
    private String android;

}
