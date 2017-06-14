package com.github.vaibhavsinha.kong.model.upstream;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Created by vaibhav on 13/06/17.
 */
@Data
public class Upstream {

    @SerializedName("id")
    private String id;
    @SerializedName("slots")
    private Integer slots;
    @SerializedName("name")
    private String name;
    @SerializedName("orderlist")
    private List<Integer> orderList;
    @SerializedName("created_at")
    private Long createdAt;
}
