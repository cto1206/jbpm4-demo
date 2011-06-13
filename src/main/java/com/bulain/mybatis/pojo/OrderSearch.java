package com.bulain.mybatis.pojo;

import com.bulain.common.page.Search;

public class OrderSearch extends Search {
    private static final long serialVersionUID = 567310980322824574L;

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
