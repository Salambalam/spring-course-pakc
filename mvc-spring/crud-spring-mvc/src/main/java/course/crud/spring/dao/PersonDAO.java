package course.crud.spring.dao;

import course.crud.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        //query() - используем для запроса, чтобы получить данные
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
        // update - используется для обновления данных
        jdbcTemplate.update("INSERT  INTO  person(name, age, email) VALUES(?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE  FROM person WHERE id=?", id);
    }



    public void batchUpdate(){
        List<Person> people = create1000People();

        long before = System.currentTimeMillis();
        jdbcTemplate.batchUpdate("INSERT INTO person VALUES(?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        preparedStatement.setInt(1, people.get(i).getId());
                        preparedStatement.setString(2, people.get(i).getName());
                        preparedStatement.setInt(3, people.get(i).getAge());
                        preparedStatement.setString(4, people.get(i).getEmail());
                    }

                    @Override
                    public int getBatchSize() {
                        return 1000;
                    }
                });
        long after = System.currentTimeMillis();

        System.out.println("Time: " + (after - before));
    }



    // Этот метод нужен для теста batchUpdate
    private List<Person> create1000People(){
        List<Person> people = new ArrayList<>();

        for(int i =0; i < 1000; i++){
            people.add(new Person(i, "Name" + i, 30, "test" + i + "mail.ru"));
        }
        return people;
    }
}
