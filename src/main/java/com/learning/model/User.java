/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/07/02
 */
package com.learning.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Represents an user of the system")
public class User {

    @ApiModelProperty(value = "The ID of the user", required = true)
    @NotNull
    private Integer id;

    @ApiModelProperty(value = "The name of the user", required = true)
    @NotEmpty
    private String name;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
