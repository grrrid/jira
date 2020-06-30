package com.atlassian.tutorial.ao.todo;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.webfragment.contextproviders.AbstractJiraContextProvider;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.google.gson.Gson;

import javax.inject.Inject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class CheckListPanel extends AbstractJiraContextProvider {
    private String IssueType;
    private String ProjectType;
    Map<String, String> hashmap = new HashMap<String, String>();
    public String jsonpath = "C://JiraPlugins/ao-tutorial/src/main/resources/json/qwerty.json";
    Writer writer;

    private boolean exist=false; //ПЕРЕМЕННАЯ, ПОЛУЧАЮЩАЯ ИНФОРМАЦИЮ О ТОМ, ПРИВЯЗАН ЛИ К ЗАДАЧЕ ЧЕК-ЛИСТ

    @ComponentImport
    private final TodoService todoService; //компонент отвечающий за добавление значений в БД

    @Inject
    public CheckListPanel(TodoService todoService) {
        this.todoService = checkNotNull(todoService);
    } //конструктор

    @Override
    public Map<String, Object> getContextMap(ApplicationUser applicationUser, JiraHelper jiraHelper) {

        Map<String, Object> contextMap = new HashMap<>();
        Map<Integer, String> descmap = new HashMap<>();
        Map<Integer, String> issuecodemap = new HashMap<>();

        Issue currentIssue = (Issue) jiraHelper.getContextParams().get("issue"); //переменная содержащая параметры текущей задачи в джире
        String currentissuecode = String.valueOf(currentIssue.getId());


        try {
        for (Todo todo : todoService.todoofthisissuecode(currentissuecode)) {
                descmap.put(todo.getID(), todo.getDescription());
                issuecodemap.put(todo.getID(), todo.getIssueCode());
        }

            writer = Files.newBufferedWriter(Paths.get(jsonpath));
            new Gson().toJson(descmap, writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //ПРОВЕРКА СУЩЕСТВОВАНИЯ ЧЕК-ЛИСТА ДЛЯ ТЕКУЩЕЙ ЗАДАЧИ
        if (issuecodemap.containsValue(currentissuecode)) { //если для текущей задачи есть чек-лист
            exist=true; //показываем его в панели
        }
        else { //если нет
            exist=false; //не показываем в панели и пишем что для этой задачи нет чек-листа
        }


        String issuetype = currentIssue.getIssueTypeId();
        Long projecttype = currentIssue.getProjectId();
        contextMap.put("exist", exist);
        contextMap.put("currentissuecode", currentissuecode);
        contextMap.put("issuetype", issuetype);
        contextMap.put("descmap", descmap);
        return contextMap;
    }

}
