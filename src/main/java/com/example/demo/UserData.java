package com.example.demo;

import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "userData", eager = true)
@SessionScoped
public class UserData implements Serializable {
    private static final long serialVersionUID = 1L;
    public String[] data = {"1","2","3"};
    public String dataa = "1";
    public Date date;
    private String name;
    private String dept;
    private int age;
    private double salary;
    private Employee employee;
    private String realtimeString;
    private static Map<String,String> countryMap;
    private String selectedCountry = "United Kingdom"; //default value

    static {
        countryMap = new LinkedHashMap<String,String>();
        countryMap.put("en", "United Kingdom"); //locale, country name
        countryMap.put("fr", "French");
        countryMap.put("de", "German");
    }

    public void localeChanged(ValueChangeEvent e) {
        //assign new value to country
        selectedCountry = e.getNewValue().toString();
    }

    public Map<String, String> getCountries() {
        return countryMap;
    }

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public String getRealtimeString() {
        return realtimeString;
    }

    public void setRealtimeString(String realtimeString) {
        this.realtimeString = realtimeString;
    }

    public String getWelcomeMessage() {
        return "Hello " + name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Author> getAuthors() {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = getConnection();
        String stm = "Select * from authors";
        List<Author> records = new ArrayList<Author>();

        try {
            pst = con.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();

            while(rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt(1));
                author.setName(rs.getString(2));
                records.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public Connection getConnection() {
        Connection con = null;
        String url = "jdbc:mariadb://localhost:3306/testdb";
        String user = "user1";
        String password = "user1";

        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection completed.");
        } catch (SQLException ex) {
            System.out.println("we have an issue connecting to the database");
            System.out.println(ex.getMessage());
        }
        return con;
    }

    private static final ArrayList<Employee> employees
            = new ArrayList<Employee>(Arrays.asList(
            new Employee("John", "Marketing", 30,2000.00),
            new Employee("Robert", "Marketing", 35,3000.00),
            new Employee("Mark", "Sales", 25,2500.00),
            new Employee("Chris", "Marketing", 33,2500.00),
            new Employee("Peter", "Customer Care", 20,1500.00)
    ));

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public String addEmployee() {
        Employee employee = new Employee(name,dept,age,salary);
        employees.add(employee);
        return null;
    }

    public String deleteEmployee(Employee employee) {
        System.out.println("delete");
        employees.remove(employee);
        return null;
    }

    public String editEmployee(Employee employee) {
        employee.setCanEdit(true);
        return null;
    }

    public String saveEmployees() {

        //set "canEdit" of all employees to false

        for (Employee employee : employees) {
            employee.setCanEdit(false);
        }
        return null;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDataa() {
        return dataa;
    }

    public void setDataa(String dataa) {
        this.dataa = dataa;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public void attributeListener(ActionEvent event) {
        dataa = (String)event.getComponent().getAttributes().get("username");
    }

    public String showResult() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params =
                fc.getExternalContext().getRequestParameterMap();
        dataa =  params.get("username");
        return "result";
    }
}
