package com.atlassian.tutorial.ao.todo;

import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

@Path("/test-api")
public class Resource {

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
     class OperationResult<T> {
        boolean success;
        T data;
        String[] errors;

        public OperationResult() {

        }
    }

    @ComponentImport
    private final TodoService todoService;

    @Inject
    public Resource(TodoService todoService) {
        this.todoService = checkNotNull(todoService);
    }

    @GET
    @Path("/checklists")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getchecklists(@Context HttpServletRequest request) {

        OperationResult<List<List<Todo>>> result = new OperationResult<>();

        List<Todo> checklist;
        List<List<Todo>> listofchecklist = new ArrayList<>();

        for (TaskTypeEnum s: TaskTypeEnum.values()) {
            checklist=todoService.getchecklistbytasktype(s.getName());
            listofchecklist.add(checklist);
        }

        result.data=listofchecklist;
        result.success=true;
        result.errors=null;
        return Response
                .ok(result)
                .build();
    }

    @GET
    @Path("/checklist")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getchecklist() {

        OperationResult<List<Todo>> result = new OperationResult<>();

        List<Todo> checklist=todoService.getchecklistbytasktype("EditContent");

        //List<Todo> checklist=todoService.all();

        result.data=checklist;
        result.success=true;
        result.errors=null;
        return Response
                .ok(result)
                .build();
    }

    @GET
    @Path("/tasktypes")
    @Produces({MediaType.APPLICATION_JSON})
    public Response gettasktypes(@Context HttpServletRequest request) {
        OperationResult<List<TaskType>> operRes = new OperationResult<>();
        TaskType taskType;
        List<TaskType> result = new ArrayList<>();

        for (TaskTypeEnum s: TaskTypeEnum.values()) {
            taskType = new TaskType(s, s.getName());
            result.add(taskType);
        }

        operRes.success=true;
        operRes.errors=null;
        operRes.data=result;
        return Response
                .ok(operRes)
                .build();
    }


    @POST
    @Path("/addchecklisttoissue")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response addchecklisttoissue(@FormParam("issuecode") String issuecode, @FormParam("issuetype") String issuetype) {
        String str = "CHECKLIST ATTACHED";
        for (Todo todo:todoService.todoofthisissuetype(issuetype)) {
            todoService.add(todo.getDescription(), issuetype, "1", issuecode);
        }
        return Response.ok(str).build();
    }

    @POST
    @Path("/post4")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    public Response post4(@FormParam("") String id1) {

        String str="";
        return Response.ok(str).build();
    }

}
