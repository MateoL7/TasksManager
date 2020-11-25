package services;

import model.dto.DoneTaskLoaizaDTO;
import model.dto.Response;
import model.dto.ToDoTaskLoaizaDTO;
import model.provider.DoneTaskProvider;
import model.provider.ToDoTaskProvider;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;
@Stateless
@Path("doneTask")
public class DoneTaskServices {
    @POST
    @Consumes("application/json")
    @Path("create")
    public Response createTask(DoneTaskLoaizaDTO dto){
        DoneTaskProvider provider = new DoneTaskProvider();
        boolean could = provider.insertDoneTask(provider.mapFromDTO(dto));
        if(could) return new Response("Task created successfully");
        else return new Response("Task could not be created");
    }
    @DELETE
    @Produces("application/json")
    @Path("delete/{id}")
    public Response deleteTaskByID(@PathParam("id") String id){
        DoneTaskProvider provider = new DoneTaskProvider();
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
    public ArrayList<DoneTaskLoaizaDTO> getAllTasks(){
        DoneTaskProvider provider = new DoneTaskProvider();
        ArrayList<DoneTaskLoaizaDTO> tasks = provider.getAllTasks();
        return tasks;
    }
    @GET
    @Produces("application/json")
    @Path("task/{id}")
    public DoneTaskLoaizaDTO getTaskById(@PathParam("id") String id){
        DoneTaskProvider provider = new DoneTaskProvider();
        return provider.getTask(id);
    }

}
