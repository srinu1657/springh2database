package org.example;

import org.example.config.AppConfig;
import org.example.dao.EmployeeDao;
import org.example.model.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.h2.tools.Server;

import java.sql.SQLException;
import java.util.List;

public class AppMain {
    public static void main(String[] args) throws SQLException {
        // Start H2 Web Console (Optional)
        // ✅ Start H2 Web Console
        Server webConsole = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        EmployeeDao dao = context.getBean(EmployeeDao.class);
        dao.createTable();
        dao.insertEmployee(101, "Srinivas");
        dao.insertEmployee(102, "Samala");

        List<Employee> list = dao.getAllEmployees();
        for (Employee emp : list) {
            System.out.println(emp.getId() + " - " + emp.getName());
        }

        context.close();



    }
}