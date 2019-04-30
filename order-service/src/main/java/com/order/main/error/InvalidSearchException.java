package com.order.main.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * {@code InvalidSearchException} is a runtime exception if invalid search
 * criteria is entered.
 * <p/>
 *
 * @author Luis Marin
 * @since 28/04/19
 */
@NoArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
public class InvalidSearchException extends RuntimeException {
    public InvalidSearchException(String s) {
        super(s);
    }
}
