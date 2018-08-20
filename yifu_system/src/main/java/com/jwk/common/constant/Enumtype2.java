package com.jwk.common.constant;

public enum Enumtype2 {
	FLATBASE("flatbase", 1),//平底
	HIGHHEEL("highheel", 2);//高跟
     // 成员变量
     private String name;
     private int index;

     // 构造方法
     private Enumtype2(String name, int index) {
         this.name = name;
         this.index = index;
     }

     // 普通方法
     public static String getName(int index) {
         for (Enumtype2 c : Enumtype2.values()) {
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
		System.out.println(Enumtype2.getName(1));
	}
}
