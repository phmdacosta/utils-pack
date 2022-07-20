package com.pedrocosta.springutils.viewmapper;

import java.util.Arrays;

public class ArrayMapper<FROM, TO> extends TypeMapper<FROM, TO> {
    private Class<TO> resultElementClass;

    protected TO map(FROM from, Class<TO> resultElementClass) {
        if (!from.getClass().isArray()) {
            throw new IllegalArgumentException(
                    String.format("%s is not a array", from.getClass().getSimpleName()));
        }
        this.resultElementClass = resultElementClass;
        return map(from);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected TO map(FROM from) {
        Object[] array = (Object[]) from;
        return new CollectionMapper<FROM, TO>().map((FROM)Arrays.asList(array), resultElementClass);
    }
}
