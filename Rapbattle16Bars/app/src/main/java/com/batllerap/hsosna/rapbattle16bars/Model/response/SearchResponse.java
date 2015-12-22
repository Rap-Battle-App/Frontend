package com.batllerap.hsosna.rapbattle16bars.Model.response;

import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;

import java.io.Serializable;

/**
 * Created by woors on 17.12.2015.
 */
public class SearchResponse implements Serializable{
    private ProfilePreview[] profiles;

    public SearchResponse(){

    }

    public ProfilePreview[] getProfiles() {
        return profiles;
    }

    public void setProfiles(ProfilePreview[] profiles) {
        this.profiles = profiles;
    }
}
