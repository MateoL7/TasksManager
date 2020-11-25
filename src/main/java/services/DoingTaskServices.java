package services;

import model.dto.DoingTaskLoaizaDTO;
import model.dto.DoneTaskLoaizaDTO;
import model.dto.Response;
import model.provider.DoingTaskProvider;
import model.provider.DoneTaskProvider;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;

@Stateless
@Path("doingTask")
public class DoingTaskServices {

    @POST
    @Consumes("application/json")
    @Path("create")
    public Response createTask(DoingTaskLoaizaDTO dto){
        DoingTaskProvider provider = new DoingTaskProvider();
        boolean could = provider.insertDoingTask(provider.mapFromDTO(dto));
        if(could) return new Response("Task created successfully");
        else return new Response("Task could not be created");
    }
    @DELETE
    @Produces("application/json")
    @Path("delete/{id}")
    public Response deleteTaskByID(@PathParam("id") String id){
        DoingTaskProvider provider = new DoingTaskProvider();
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
    public ArrayList<DoingTaskLoaizaDTO> getAllTasks(){
        DoingTaskProvider provider = new DoingTaskProvider();
        ArrayList<DoingTaskLoaizaDTO> tasks = provider.getAllTasks();
        return tasks;
    }
    @GET
    @Produces("application/json")
    @Path("task/{id}")
    public DoingTaskLoaizaDTO getTaskById(@PathParam("id") String id){
        DoingTaskProvider provider = new DoingTaskProvider();
        return provider.getTask(id);
    }
}
