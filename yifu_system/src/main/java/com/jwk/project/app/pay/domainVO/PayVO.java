package com.jwk.project.app.pay.domainVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayVO {

    private  String mweb_url;

    private  String result;

    private  String userId;

    private  String addrId;

    private  String num;

    private  String money;

    private  String jeansId;

    private  String orderId;

}
