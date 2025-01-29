package com.javahibernateapp.entity;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public enum Features {

    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");
    //set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')

    private final String value;

    Features(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Features getFeatureBYValue(String value) {
        if(isNull(value) || value.isEmpty()){
            return null;
        }

        Features[] feature = Features.values();
        for (Features f : feature) {
            if(f.value.equals(value)){
                return f;
            }
        }
        return null;
    }

}
