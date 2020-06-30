package com.atlassian.tutorial.ao.todo;

import com.atlassian.activeobjects.tx.Transactional;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.util.List;

@Transactional
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public interface TodoService
{
    Todo add(String description, String issuetype, String projecttype, String issuecode);

    List<Todo> all();

    List<Todo> todotest();

    List<Todo> todoofthisissuetype(String issuetype);

    List<Todo> todoofthisissuecode(String issuecode);

    List<Todo> getchecklistbytasktype(String tasktype);
}