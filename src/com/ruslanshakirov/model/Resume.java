package com.ruslanshakirov.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Initial resume class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resume implements Comparable<Resume>{

    // Unique identifier
    private String uuid;

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}
