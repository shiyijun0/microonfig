package com.jwk.project.system.web.domainVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebLocationVO {
private String cover;
private String model;
private String color;
private String thread;
private String partsId;
private String fixeX;
private String fixeY;

private String url;

}
