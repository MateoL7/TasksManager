package model.provider;

import db.MySQLConnection;
import entity.DoingTaskLoaiza;
import model.dto.DoingTaskLoaizaDTO;
import model.dto.DoneTaskLoaizaDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DoingTaskProvider {
    public ArrayList<DoingTaskLoaizaDTO> getAllTasks() {
        ArrayList<DoingTaskLoaizaDTO> output = new ArrayList<>();
        MySQLConnection connection = new MySQLConnection();
        try {
            String sql = "SELECT id, date, description FROM doingTasksLoaiza";
            ResultSet resultSet = connection.query(sql);
            while (resultSet.next()) {
                output.add(new DoingTaskLoaizaDTO(
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

    public boolean insertDoingTask(DoingTaskLoaiza task) {
        MySQLConnection connection = new MySQLConnection();
        Date date = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mySQLDate = formatter.format(date);
        String sql = "INSERT INTO doingTasksLoaiza(date,description) values ('$DATE', '$DESCRIPTION')"
                .replace("$DATE", mySQLDate)
                .replace("$DESCRIPTION", task.getDescription());
        boolean possible = connection.executeSQL(sql);
        return possible;
    }

    public boolean deleteTask(String id) {
        MySQLConnection connection = new MySQLConnection();
        String sql = "DELETE FROM doingTasksLoaiza WHERE id=" + id;
        return connection.executeSQL(sql);
    }

    public DoingTaskLoaiza mapFromDTO(DoingTaskLoaizaDTO dto) {
        DoingTaskLoaiza task = new DoingTaskLoaiza();
        task.setId(dto.getId());
        task.setDate(dto.getDate());
        task.setDescription(dto.getDescription());
        return task;
    }
    public DoingTaskLoaizaDTO getTask(String id) {
        DoingTaskLoaizaDTO dto = new DoingTaskLoaizaDTO();
        MySQLConnection connection = new MySQLConnection();
        try {
            String sql = "SELECT FROM doingTasksLoaiza WHERE id=" + id;
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
