package com.atlassian.tutorial.ao.todo;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TaskType {

    TaskTypeEnum id;
    String name;

    TaskType(TaskTypeEnum id, String name) {
        this.id=id;
        this.name=name;
    }

}
