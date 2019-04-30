package com.order.main.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * {@code DataAlreadyRegisteredException} is a runtime exception if no entities are found
 * in the database.
 * <p/>
 *
 * @author lmarin
 * @since 28/04/19
 */
@NoArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
public class DataAlreadyRegisteredException extends RuntimeException {
    public DataAlreadyRegisteredException(String s) {
        super(s);
    }
}
