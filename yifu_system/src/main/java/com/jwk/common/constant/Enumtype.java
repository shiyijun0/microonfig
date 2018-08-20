package com.jwk.common.constant;

public enum Enumtype {
	 ANDROID("android", 1), IOS("ios", 2), PC("pc", 3);
     // 成员变量
     private String name;
     private int index;

     // 构造方法
     private Enumtype(String name, int index) {
         this.name = name;
         this.index = index;
     }

     // 普通方法
     public static String getName(int index) {
         for (Enumtype c : Enumtype.values()) {
             if (c.getIndex() == index) {
                 return c.name;
             }
         }
         return null;
     }

     // get set 方法
     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public int getIndex() {
         return index;
     }

     public void setIndex(int index) {
         this.index = index;
}
     public static void main(String[] args) {
		System.out.println(Enumtype.getName(1));
	}
}
