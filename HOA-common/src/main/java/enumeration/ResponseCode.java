package enumeration;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS(200, "Success"),
    ERROR(1, "Error"),
    ACCOUNT_NOT_EXIST(2, "Account Not Exist"),
    ACCOUNT_ALREADY_EXIST_OR_EMPTY(3, "账号已经存在或用户名为空"),
    WRONG_PASSWORD_OR_USERNAME(4,"用户名或密码错误"),
    NEED_LOGIN(11, "Need Login"),
    ILLEGAL_ARGUMENT(10, "Illegal Argument"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    // 通过 code 获取 ResponseCode
    public static ResponseCode fromCode(int code) {
        for (ResponseCode responseCode : values()) {
            if (responseCode.code == code) {
                return responseCode;
            }
        }
        return ERROR;
    }
}
