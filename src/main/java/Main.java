
import dao.QueryExecutor;
import view.UserInterface;



public class Main {
    public static void main(String[] args) throws InterruptedException {
        String createProgramsTable = " DROP TABLE IF EXISTS PROGRAMS;" +
                " CREATE TABLE PROGRAMS " +
                " (id  SERIAL PRIMARY KEY, " +
                " program_name VARCHAR(255));";
        QueryExecutor.executeQuery(createProgramsTable);

        String createTemperatureOperationsTable = " DROP TABLE IF EXISTS TEMPERATURE_OPERATIONS;" +
                " CREATE TABLE TEMPERATURE_OPERATIONS " +
                " (id SERIAL PRIMARY KEY, " +
                " operation_name VARCHAR(255), " +
                " temperature DECIMAL, " +
                " speed DECIMAL, " +
                " minutes INTEGER, " +
                " operation_type VARCHAR(255), " +
                " program_id INTEGER);";
        QueryExecutor.executeQuery(createTemperatureOperationsTable);

        String createPackageOperationsTable = " DROP TABLE IF EXISTS PACKAGE_OPERATIONS;" +
                " CREATE TABLE PACKAGE_OPERATIONS " +
                " (id SERIAL PRIMARY KEY, " +
                " operation_name VARCHAR(255), " +
                " volume DECIMAL, " +
                " bottle_volume_enum VARCHAR(255), " +
                " keg_volume_enum VARCHAR(255), " +
                " program_id INTEGER);";
        QueryExecutor.executeQuery(createPackageOperationsTable);


        UserInterface userInterface = new UserInterface();
    }
}
