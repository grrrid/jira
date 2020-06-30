package com.atlassian.tutorial.ao.todo;

import net.java.ao.Entity;

public interface Todo extends Entity
{
    String getDescription();

    void setDescription(String description);

    boolean isComplete();

    void setComplete(boolean complete);

    void setIssueType(String issuetype);

    String getIssueType();

    void setProjectType(String projecttype);

    String getProjectType();

    void setIssueCode(String issuecode);

    String getIssueCode();
}