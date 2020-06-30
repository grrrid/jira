package com.atlassian.tutorial.ao.todo;

import com.atlassian.activeobjects.tx.Transactional;

import java.util.List;

@Transactional
public interface CheckListService {
    CheckList add(int issuetype);

    List<CheckList> all();
}
