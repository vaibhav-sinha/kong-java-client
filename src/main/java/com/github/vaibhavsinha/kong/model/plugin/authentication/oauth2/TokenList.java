package com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2;

import java.util.List;

import com.github.vaibhavsinha.kong.model.common.AbstractEntityList;

import lombok.Data;

/**
 * Created by fanhua on 2017-08-07.
 */
@Data
public class TokenList extends AbstractEntityList {

	Long total;

	List<Token> data;
}
