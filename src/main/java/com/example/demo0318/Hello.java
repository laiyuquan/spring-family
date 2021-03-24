package com.example.demo0318;

import lombok.Data;

@Data
public class Hello {

    protected long id;

    protected String name;

    public Hello(long id, String format) {
        this.id = id;
        this.name = format;
    }

}
