package com.atlassian.tutorial.ao.todo;

import com.atlassian.activeobjects.external.ActiveObjects;

import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import net.java.ao.EntityStreamCallback;
import net.java.ao.Query;
import net.java.ao.RawEntity;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.inject.Inject;
import javax.inject.Named;

@Scanned
@Named
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TodoServiceImpl implements TodoService
{
    @ComponentImport
    private final ActiveObjects ao;

    @Inject
    public TodoServiceImpl(ActiveObjects ao)
    {
        this.ao = checkNotNull(ao);
    }

    @Override
    public Todo add(String description, String issuetype, String projecttype, String issuecode)
    {
        final Todo todo = ao.create(Todo.class);
        todo.setDescription(description);
        todo.setComplete(false);
        todo.setIssueType(issuetype);
        todo.setProjectType(projecttype);
        todo.setIssueCode(issuecode);
        todo.save();
        return todo;
    }

    @Override
    public List<Todo> all()
    {
        return newArrayList(ao.find(Todo.class));
    }

    @Override
    public List<Todo> todotest() {
        return newArrayList(ao.find(Todo.class, Query.select().where("ISSUE_CODE=?", "test")));
    }

    @Override
    public List<Todo> todoofthisissuetype(String issuetype) {
        return newArrayList(ao.find(Todo.class, Query.select().where("ISSUE_TYPE=?", issuetype)));
    }

    @Override
    public List<Todo> todoofthisissuecode(String issuecode) {
        return newArrayList(ao.find(Todo.class, Query.select().where("ISSUE_CODE=?", issuecode)));
    }

    @Override
    public List<Todo> getchecklistbytasktype(String tasktype) {
        return newArrayList(ao.find(Todo.class, Query.select().alias(Todo.class, "ID").where("ISSUE_TYPE=?", tasktype)));
        //return newArrayList(ao.find(Todo.class, Query.select().where("ISSUE_TYPE=?", tasktype).group("ID")));
    }
}