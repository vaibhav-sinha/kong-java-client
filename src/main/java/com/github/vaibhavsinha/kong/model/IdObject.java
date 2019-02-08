package com.github.vaibhavsinha.kong.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by kgignatyev at 2019/02/04
 */
@Data
public class IdObject {

    @SerializedName("id")
    private String id;

    public IdObject(String id) {
        this.id = id;
    }

    public IdObject() {
    }
}
