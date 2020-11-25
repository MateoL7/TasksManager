package services;

import model.dto.Response;
import model.dto.ToDoTaskLoaizaDTO;
import model.provider.ToDoTaskProvider;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;

@Stateless
@Path("toDoTask")
public class ToDoTaskServices {
    @POST
    @Consumes("application/json")
    @Path("create")
    public Response createTask(ToDoTaskLoaizaDTO dto){
        ToDoTaskProvider provider = new ToDoTaskProvider();
        boolean could = provider.insertToDoTask(provider.mapFromDTO(dto));
        if(could) return new Response("Task created successfully");
        else return new Response("Task could not be created");
    }
    @DELETE
    @Produces("application/json")
    @Path("delete/{id}")
    public Response deleteTaskByID(@PathParam("id") String id){
        ToDoTaskProvider provider = new ToDoTaskProvider();
        boolean success = provider.deleteTask(id);
        if(success){
            return new Response("Task deleted");
        }
        else{
            return new Response("Task not deleted");
        }
    }
    @GET
    @Produces("application/json")
    @Path("all")
    public ArrayList<ToDoTaskLoaizaDTO> getAllTasks(){
        ToDoTaskProvider provider = new ToDoTaskProvider();
        ArrayList<ToDoTaskLoaizaDTO> tasks = provider.getAllTasks();
        return tasks;
    }
    @GET
    @Produces("application/json")
    @Path("task/{id}")
    public ToDoTaskLoaizaDTO getTaskById(@PathParam("id") String id){
        ToDoTaskProvider provider = new ToDoTaskProvider();
        return provider.getTask(id);
    }
}
