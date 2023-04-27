package course.crud.spring.models;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Component
public class DBtoExcel {

    public DBtoExcel() {
    }

    public void getTable() {
        // Устанавливаем параметры подключения к базе данных
        String jdbcURL = "jdbc:postgresql://localhost:5432/first_db"; // URL базы данных
        String username = "postgres"; // Имя пользователя
        String password = "123"; // Пароль пользователя
        String excelFilePath = "file.xlsx"; // Путь и имя файла для сохранения

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            // Создаем SQL-запрос для выборки данных из таблицы
            String sql = "SELECT * FROM person";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // Создаем объект для работы с файлом Excel и создаем новую страницу в нем
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Data");

            // Записываем заголовок таблицы
            writeHeaderLine(sheet);

            // Записываем данные из базы данных в таблицу Excel
            writeDataLines(result, workbook, sheet);


            try (OutputStream outputStream = Files.newOutputStream(Paths.get(excelFilePath))) {
                workbook.write(outputStream);
                outputStream.flush();
            } catch (IOException e) {
                // Обработка ошибок
            }

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }

    // Метод для записи заголовка таблицы
    private static void writeHeaderLine(XSSFSheet sheet) {
        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("id");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("name");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("age");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("email");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("address");
    }

    // Метод для записи данных из базы данных в таблицу Excel
    private static void writeDataLines(ResultSet result, XSSFWorkbook workbook,
                                       XSSFSheet sheet) throws SQLException {
        int rowCount = 1;

        while (result.next()) {
            // Получаем значения из текущей строки результата SQL-запроса
            int column1 = result.getInt("id");
            String column2 = result.getString("name");
            int column3 = result.getInt("age");
            String column4 = result.getString("email");
            String column5 = result.getString("address");

            // Создаем новую строку в таблице Excel и заполняем ее данными из базы данных
            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(column1);

            cell = row.createCell(columnCount++);
            cell.setCellValue(column2);

            cell = row.createCell(columnCount++);
            cell.setCellValue(column3);

            cell = row.createCell(columnCount++);
            cell.setCellValue(column4);

            cell = row.createCell(columnCount++);
            cell.setCellValue(column5);
        }
    }
}
