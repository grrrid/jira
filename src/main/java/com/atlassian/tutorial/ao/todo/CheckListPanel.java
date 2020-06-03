package com.atlassian.tutorial.ao.todo;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.webfragment.contextproviders.AbstractJiraContextProvider;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CheckListPanel extends AbstractJiraContextProvider {
    private String IssueType;
    private String ProjectType;
    Map<String, String> hashmap = new HashMap<String, String>();

    @ComponentImport
    private DBManager db;

    @Override
    public Map<String, Object> getContextMap(ApplicationUser applicationUser, JiraHelper jiraHelper) {

        Map<String, Object> contextMap = new HashMap<>();



        Issue currentIssue = (Issue) jiraHelper.getContextParams().get("issue");
        String issuetype = currentIssue.getIssueTypeId();
        String first = "checked";


        Map<String, Object> map = null;
        Map<String, Object> map1 = null;
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("C://JiraPlugins/checklist/src/main/resources/json/conditions.json"));
            map = gson.fromJson(reader, Map.class);
            //reader = Files.newBufferedReader(Paths.get("C://JiraPlugins/checklist/src/main/resources/json/scheme.json"));
            //map1 = gson.fromJson(reader, Map.class);
            reader.close();

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }


        contextMap.put("issuetype", issuetype);
        contextMap.putAll(map);
        //contextMap.putAll(map1);
        //contextMap.putAll(map2);


        return contextMap;
    }

}
