package com.github.vaibhavsinha.kong.api.admin;

import com.github.vaibhavsinha.kong.model.admin.sni.Sni;
import com.github.vaibhavsinha.kong.model.admin.sni.SniList;

/**
 * Created by vaibhav on 13/06/17.
 */
@Deprecated
public interface SniService {
    Sni createSni(Sni request);
    Sni getSni(String name);
    Sni updateSni(String name, Sni request);
    Sni createOrUpdateSni(Sni request);
    Sni deleteSni(String name);
    SniList listSnis();
}
