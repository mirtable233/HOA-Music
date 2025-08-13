package Enumeration;

public enum Role {
    USER(0,"普通用户"),
    SINGER(1,"音乐人"),
    MERCHANT(2,"商家"),
    LABEL(3,"唱片公司"),
    ADMIN(4,"管理员");

    private final int code;
    private final String displayName; // 中文名

    Role(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Role fromCode(int code) {
        for (Role r : Role.values()) {
            if (r.code == code) {
                return r;
            }
        }
        throw new IllegalArgumentException("未知角色代码: " + code);
    }
}
