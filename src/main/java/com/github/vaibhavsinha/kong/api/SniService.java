package com.github.vaibhavsinha.kong.api;

import com.github.vaibhavsinha.kong.model.sni.Sni;
import com.github.vaibhavsinha.kong.model.sni.SniList;

/**
 * Created by vaibhav on 13/06/17.
 */
public interface SniService {
    Sni createSni(Sni request);
    Sni getSni(String name);
    Sni updateSni(String name, Sni request);
    Sni createOrUpdateSni(Sni request);
    Sni deleteSni(String name);
    SniList listSnis();
}
