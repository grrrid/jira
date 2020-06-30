package com.atlassian.tutorial.ao.todo;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CheckList  {
    Integer id;
    Integer ProjectID;
    TaskType TaskType;
    List<Todo> items;

    public CheckList(TaskType taskType) {
        this.TaskType=taskType;
    }
}
