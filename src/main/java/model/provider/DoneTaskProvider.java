package model.provider;

import db.MySQLConnection;
import entity.DoneTaskLoaiza;
import model.dto.DoneTaskLoaizaDTO;
import model.dto.ToDoTaskLoaizaDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DoneTaskProvider {
    public ArrayList<DoneTaskLoaizaDTO> getAllTasks() {
        ArrayList<DoneTaskLoaizaDTO> output = new ArrayList<>();
        MySQLConnection connection = new MySQLConnection();
        try {
            String sql = "SELECT id, date, description FROM doneTasksLoaiza";
            ResultSet resultSet = connection.query(sql);
            while (resultSet.next()) {
                output.add(new DoneTaskLoaizaDTO(
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
    public boolean insertDoneTask(DoneTaskLoaiza task) {
        MySQLConnection connection = new MySQLConnection();
        Date date = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mySQLDate = formatter.format(date);
        String sql = "INSERT INTO doneTasksLoaiza(date,description) values ('$DATE', '$DESCRIPTION')"
                .replace("$DATE", mySQLDate)
                .replace("$DESCRIPTION", task.getDescription());
        boolean possible = connection.executeSQL(sql);
        return possible;
    }

    public boolean deleteTask(String id) {
        MySQLConnection connection = new MySQLConnection();
        String sql = "DELETE FROM doneTasksLoaiza WHERE id=" + id;
        return connection.executeSQL(sql);
    }
    public DoneTaskLoaiza mapFromDTO(DoneTaskLoaizaDTO dto) {
        DoneTaskLoaiza task = new DoneTaskLoaiza();
        task.setId(dto.getId());
        task.setDate(dto.getDate());
        task.setDescription(dto.getDescription());
        return task;
    }
    public DoneTaskLoaizaDTO getTask(String id) {
        DoneTaskLoaizaDTO dto = new DoneTaskLoaizaDTO();
        MySQLConnection connection = new MySQLConnection();
        try {
            String sql = "SELECT FROM doneTasksLoaiza WHERE id=" + id;
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
