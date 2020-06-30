package com.atlassian.tutorial.ao.todo;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.sal.api.transaction.TransactionCallback;

public class DBManager {

    private final ActiveObjects ao;

    public DBManager(ActiveObjects ao) {
        this.ao = ao;
    }

    public Todo createrec(String name) {
        ao.executeInTransaction(new TransactionCallback<Todo>() // (1)
        {
            @Override
            public Todo doInTransaction()
            {
                final Todo todo = ao.create(Todo.class); // (2)
                todo.setDescription(name); // (3)
                todo.setComplete(false);
                todo.save(); // (4)
                return todo;
            }
        });
        return null;
    }
}
