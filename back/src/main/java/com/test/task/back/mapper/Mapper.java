package com.test.task.back.mapper;

import java.util.List;

public interface Mapper<E, F> {
    List<E> fromForms(List<F> forms);
    List<F> toForms(List<E> entities);
    F toForm(E entity);
    E fromForm(F form);
}
