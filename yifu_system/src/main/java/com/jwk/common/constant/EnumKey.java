package com.jwk.common.constant;
/**1 添加动作 2 添加布料 3 添加区域 4 添加区域位置 5 完成 */
public enum EnumKey {
	
    ACTION("action", 1), CLOTH("cloth", 2), AREA("area", 3),AREA_lOCATION("arealoction", 4), COMPLATE("complate", 5);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private EnumKey(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (EnumKey c : EnumKey.values()) {
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
		System.out.println(EnumKey.getName(5));
	}
}
