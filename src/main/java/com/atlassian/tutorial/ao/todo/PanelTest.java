package com.atlassian.tutorial.ao.todo;

import com.atlassian.plugin.web.model.WebPanel;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class PanelTest implements WebPanel {

    private final TodoService todoService;

    public PanelTest(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public String getHtml(Map<String, Object> map) {
        return "AAAAAAAAAA";
    }

    @Override
    public void writeHtml(Writer writer, Map<String, Object> map) throws IOException {
        String str=null;
        map.put("HELLO WORLD", str);
        writer.write(str);
    }
}
