package Enumeration;


import DO.Playlist;

public enum CommentType {
    SONG(0,"song"),
    PLAYLIST(1,"playlist"),
    POST(2,"post");

    private final int code;
    private final String displayName; // 中文名

    CommentType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static CommentType fromCode(int code) {
        for (CommentType r : CommentType.values()) {
            if (r.code == code) {
                return r;
            }
        }
        throw new IllegalArgumentException("未知角色代码: " + code);
    }
}
