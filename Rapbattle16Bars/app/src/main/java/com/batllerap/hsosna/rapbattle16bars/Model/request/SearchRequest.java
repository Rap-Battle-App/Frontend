package com.batllerap.hsosna.rapbattle16bars.Model.request;

import java.io.Serializable;

/**
 * Created by woors on 17.12.2015.
 */
public class SearchRequest implements Serializable {
    private String search_string;

    public SearchRequest(){

    }

    public String getSearch_string() {
        return search_string;
    }

    public void setSearch_string(String search_string) {
        this.search_string = search_string;
    }
}
