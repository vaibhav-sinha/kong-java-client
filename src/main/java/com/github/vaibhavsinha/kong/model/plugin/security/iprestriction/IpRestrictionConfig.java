package com.github.vaibhavsinha.kong.model.plugin.security.iprestriction;

import lombok.Data;

import java.util.List;

/**
 * Created by vaibhav on 18/06/17.
 */
@Data
public class IpRestrictionConfig {
    List<String> whitelist;
    List<String> blacklist;
}
