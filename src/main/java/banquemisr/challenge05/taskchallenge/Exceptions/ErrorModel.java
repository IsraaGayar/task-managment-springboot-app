package banquemisr.challenge05.taskchallenge.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorModel {
    private String code;
    private String msg;

    public ErrorModel() {
    }

    public ErrorModel(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

