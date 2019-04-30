package com.order.main.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * {@code DataNotFoundException} is a runtime exception if no entities are found
 * in the database.
 * <p/>
 *
 * @author Luis Marin
 * @since 28/04/19
 */
@NoArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String s) {
        super(s);
    }
}
