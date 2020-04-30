package com.company.entity;

import lombok.Data;

import javax.persistence.Entity;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
public class Change {
    public String[] fieldsName;
}
