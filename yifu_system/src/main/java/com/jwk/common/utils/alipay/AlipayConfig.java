package com.jwk.common.utils.alipay;

/**
 * 支付宝支付配置信息
 * 
 * @author Administrator createTime:2018-04-19
 */
public class AlipayConfig {

		// 商户appid
		public static String APPID = "2016091000478547";
		// 私钥 pkcs8格式的
		public static String RSA_PRIVATE_KEY = "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQCsj+1ewe+/bJ2qx1WOm9b/wbEtVBucoGhHqhDRvKGczUvy+dB96iP+3Wa1BlGz+uvtcuyPQz8MUND+uVxsNXCF3s1cpE39s82H2PKDuz7pYxiChlYjvtzIeCcIVtmXDn2qA2Ya301WyPyyQlk2QH7OXsFdhWnMUtwG9VFaA+Yvgn1sx8ZvFHgBepx06WlXqO8AASY3u5AQbUcFTUu6RWkdHQq0yQWbpb+RMbqcbOeGeir3tfiMzbgppFjZnC0easxHrtGckxdkOafcG7AhVjI7qwFUIzJeZOGXuaoCkCYymNa1pyFml7VfPVgUmyZuT4EtQ+5CZLbnxa2KI47oUQGrAgMBAAECggEBAKJUhtT0Faf68lscR2TvPxK7UAQ3Mf5me5Km7kmPbGzIR6Eb1kENCK2R8zNp7WQlMNnfmZ+vf9kDEYf+MW5t2xtkG8YBnoiCI1+e+4xmkekVWpI280OPIbzIItfac7iBXj+YEO5IkEWKrwS2G5xIkcFrK/XhMkSO0gMNtWi7cEfRqaj0uZcvEV0BWKXYkn+wQTI4UnaLmATKA2v33tQzK41nbr08UrsBCskXCG1O9r2WBhdrppCmGNm1jHuhTz5AKT+cvAE9DRDklqg3LTKHnbWq9cB+7M/xIXNLK7tXYZfqHonFpW9ZOK9aFii6x1LJRan25f6w7bAEsuktb4fpVikCgYEA41RRw0CVjBB5//0af66hU0jeVymP708FJJ7o5e99hxb7E+6C88Bff8qhR9n1aFc1VZqbUr5zPnQqERRKVqiIm2kwABPBdxWl/AbVdkDLu2q0zKOF/sUHlD56olSfOV+R5gaUsEIjUKQQOO64rAq0Nl/Zxi4Cg1ysEg9IH+SttS8CgYEAwlNdjF+UIk7+SYw8t7Bq+c+jHYYf+VVB2SsPttv3tQru05SAsY11kiE4aCUI9JVWBHd8QzOoLFVxfBlB3SwvWl10jRpYBp9Z0293YFvSi3Tpnj3/HTBokfUCOeizi2Qa5h7f87Xvhk8tYZMlrz8zMqN5GNq9fpxcJ3Mh2wNAlEUCgYEAqCD2j6e2cu7ErDUvYftvyT79Yg2VLHKO5TS6H0Tu2/h9HZKrO0et2b+tZIyo2dCV24gLFDlS1n/nlNYi3NCsp/Q10fjKfx59slYDjgACLcyPh0Hxmilyn4tN+70GPdKW/z8ivPFNWRjd/p1f663G9ugREmd46Fqj0H+I4j2o03UCgYEAk1ic/VxXuXAGNoRkYbAeXU9I0pEp22f/JwkUwEWoXJlun/XhYz/s3yHbYHR8huBZOcfT7CzWHs+m3j9Uhr0DJtToFDnedx8LAFxrux/E1MOQhNFjiKF1GP8bcgd1ZINCNi1BCM1yUnfk5DCsCWa1jncP0n3B46fx5GPTTvr3U7UCgYEAnA+G2zReS/ArhqeYn4EKD10b5M/rwloenzAPUw2TBb7wWrrrx225rRqh8rls3fqjO5is2pPqCuZo8JkrPj+xUjDxhY9i/7Mpcl1lh2jwDjbEjblwJ8k3lx3kpZkVM7ANHb+gYes1ec5biK7f+jWaMusLe0WPZ6WoX27H9ZhDBtA=";
		// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		//异步通知页面，就是接受支付宝支付结果返回信息的Controller，可以处理自己的支付后的逻辑
		//public static String notify_url = "http://jwk.bzvs.cn/alipayNotifies/backend";
		public static String notify_url = "http://test.bzvs.cn/alipayNotifies/backend";
		// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
		//同步跳转的页面，就是支付宝支付成功后页面跳转的url
		public static String return_url = "http://test.bzvs.cn/alipayNotifies/wap/frontend";
		// 请求网关地址
		//public static String URL = "https://openapi.alipay.com/gateway.do";
		public static String URL = "https://openapi.alipaydev.com/gateway.do";//沙箱测试
		// 编码
		public static String CHARSET = "UTF-8";
		// 返回格式
		public static String FORMAT = "json";
		// 支付宝公钥
		public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvlLj4GhTXmxv0Rvtuy9BQNkBh6reQlivy0gjPCyLUW54hjRPba+J6PPa4RycecNSqxJozktu1qIq6P/kQReVUvUkIKHYXvNyN+upG6bJnn3tNNXw14Pz/f4yEzV4nR0VkeApPcU1EBr9yu01yiFIvqH87X5t3DXAQGVsILh273JRn7NCMc58GjVWwF0uBcjZS+Ua5K4X10w3RRQQvNY18+w5wWA9WYzF6JfCbI5yA+WzqC/u1SfWjyLunGRq2iZhzwtWZyyP7xp7xH7W/ApzNDwTsOOdCz0YRC5aeSeNfux1Yk1J92XDqDj1PiV3UdqZt9sC+Q255v8CE50oGklU0QIDAQAB";
		// 日志记录目录
		public static String log_path = "/log";
		// RSA2
		public static String SIGNTYPE = "RSA2";
}
