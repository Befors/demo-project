package com.haulmont.demoproject.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import com.haulmont.demoproject.model.Activated;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessUtil {

    public static void checkAccessibility(Activated activated) {
        if (!activated.isActive()) {
            throw new RuntimeException();
        }
    }
}
