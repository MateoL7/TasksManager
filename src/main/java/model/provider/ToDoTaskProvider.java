package model.provider;

import db.MySQLConnection;
import entity.ToDoTaskLoaiza;
import model.dto.ToDoTaskLoaizaDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ToDoTaskProvider {
    public ArrayList<ToDoTaskLoaizaDTO> getAllTasks() {
        ArrayList<ToDoTaskLoaizaDTO> output = new ArrayList<>();
        MySQLConnection connection = new MySQLConnection();
        try {
            String sql = "SELECT id, date, description FROM toDoTasksLoaiza";
            ResultSet resultSet = connection.query(sql);
            while (resultSet.next()) {
                output.add(new ToDoTaskLoaizaDTO(
                        resultSet.getInt(1),
                        resultSet.getDate(2),
                        resultSet.getString(3)
                ));
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        connection.disconnect();
        return output;
    }

    public boolean insertToDoTask(ToDoTaskLoaiza toDoTaskLoaiza) {
        MySQLConnection connection = new MySQLConnection();
        Date date = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mySQLDate = formatter.format(date);
        String sql = "INSERT INTO toDoTasksLoaiza(date,description) values ('$DATE', '$DESCRIPTION')"
                .replace("$DATE", mySQLDate)
                .replace("$DESCRIPTION", toDoTaskLoaiza.getDescription());
        boolean possible = connection.executeSQL(sql);
        return possible;
    }

    public boolean deleteTask(String id) {
        MySQLConnection connection = new MySQLConnection();
        String sql = "DELETE FROM toDoTasksLoaiza WHERE id=" + id;
        return connection.executeSQL(sql);
    }

    public ToDoTaskLoaiza mapFromDTO(ToDoTaskLoaizaDTO dto) {
        ToDoTaskLoaiza task = new ToDoTaskLoaiza();
        task.setId(dto.getId());
        task.setDate(dto.getDate());
        task.setDescription(dto.getDescription());
        return task;
    }

    public ToDoTaskLoaizaDTO getTask(String id) {
        ToDoTaskLoaizaDTO dto = new ToDoTaskLoaizaDTO();
        MySQLConnection connection = new MySQLConnection();
        try {
            String sql = "SELECT FROM toDoTasksLoaiza WHERE id=" + id;
            ResultSet resultSet = connection.query(sql);
            while (resultSet.next()) {
                dto.setId(resultSet.getInt(1));
                dto.setDate(resultSet.getDate(2));
                dto.setDescription(resultSet.getString(3));
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return dto;
    }
}
