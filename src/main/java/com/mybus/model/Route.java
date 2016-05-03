package com.mybus.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.json.simple.JSONObject;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by skandula on 12/30/15.
 */
@ToString
@ApiModel(value = "Route")
@AllArgsConstructor
@NoArgsConstructor
public class Route extends AbstractDocument{

    @Getter
    @Setter
    @ApiModelProperty
    private String name;

    @Getter
    @Setter
    @ApiModelProperty
    private String fromCity;

    @Getter
    @Setter
    @ApiModelProperty
    private String toCity;

    @Getter
    @Setter
    @ApiModelProperty(value = "Ordered list of cityIds through which the bus can operate")
    private Set<String> viaCities = new LinkedHashSet<>();

    @Getter
    @Setter
    @ApiModelProperty
    private boolean active = true;

    public Route(JSONObject json){
        if(json.containsKey("id")) {
            this.setId(json.get("id").toString());
        }
        if(json.containsKey("name")) {
            this.name = json.get("name").toString();
        }
        if(json.containsKey("fromCity")) {
            this.fromCity = json.get("fromCity").toString();
        }
        if(json.containsKey("toCity")) {
            this.toCity = json.get("toCity").toString();
        }
        if(json.containsKey("viaCities")) {
            this.viaCities = (Set<String>)json.get("viaCities");
        }
    }
}
