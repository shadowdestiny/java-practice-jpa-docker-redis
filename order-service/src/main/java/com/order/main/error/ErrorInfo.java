package com.order.main.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {@code ErrorInfo} represents an error response object which is exposed to
 * the external client in a human readbable form.
 * <p/>
 *
 * @author Luis Marin
 * @since 29/04/19
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorInfo {

    private String path;

    private int code;

    private String type;

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setType(String type) {
        this.type = type;
    }
}