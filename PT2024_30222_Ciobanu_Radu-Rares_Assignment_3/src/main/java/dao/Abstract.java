package dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;

import javax.swing.*;

public class Abstract<T> {

    protected static final Logger LOGGER = Logger.getLogger(Abstract.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public Abstract() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String createSelectAllQuery() {
        String query = "SELECT * FROM " + type.getSimpleName();
        return query;
    }

    private Object[][] createObjects(ResultSet resultSet) {
        List<Object[]> table = new ArrayList<>();
        try {
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                table.add(row);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error creating objects from ResultSet", e);
        }
        return table.toArray(new Object[0][]);
    }

    public Object[][] add(T t) {
        String tableName = type.getSimpleName();
        StringBuilder query = new StringBuilder("INSERT INTO ").append(tableName).append(" (");

        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            query.append(fields[i].getName());
            if (i < fields.length - 1) {
                query.append(", ");
            }
        }

        query.append(") VALUES (");
        for (int i = 0; i < fields.length; i++) {
            query.append("?");
            if (i < fields.length - 1) {
                query.append(", ");
            }
        }
        query.append(")");

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                statement.setObject(i + 1, fields[i].get(t));
            }

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, "Error adding record to " + tableName, e);
        }
        return getAll();
    }

    public Object[][] delete(int id) {
        String tableName = type.getSimpleName();
        String idColumnName =  tableName.substring(0) + "Id";
        String query = "DELETE FROM " + tableName + " WHERE " + idColumnName + " = ?";
        System.out.println(query);

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();


        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null, "Cannot delete the record. It is referenced by other records.");
            } else {
                LOGGER.log(Level.WARNING, "Error deleting record from " + tableName, e);
            }
        }
        return getAll();
    }

    public Object[][] update(T original, T updated) {
        String tableName = type.getSimpleName();
        StringBuilder query = new StringBuilder("UPDATE ").append(tableName).append(" SET ");

        Field[] fields = type.getDeclaredFields();
        String idColumnName = tableName.toLowerCase() + "Id";

        int fieldCount = 0;
        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase(idColumnName)) {
                if (fieldCount > 0) {
                    query.append(", ");
                }
                query.append(field.getName()).append(" = ?");
                fieldCount++;
            }
        }

        query.append(" WHERE ").append(idColumnName).append(" = ?");

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {

            int parameterIndex = 1;
            Object idValue = null;
            for (Field field : fields) {
                field.setAccessible(true);
                if (!field.getName().equalsIgnoreCase(idColumnName)) {
                    Object value = field.get(updated);
                    statement.setObject(parameterIndex++, value);
                } else {
                    idValue = field.get(original);
                }
            }
            statement.setObject(parameterIndex, idValue);

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, "Error updating record in " + tableName, e);
        }

        return getAll();
    }


    public Object[][] getAll() {
        String query = createSelectAllQuery();
        Object[][] resultList = new Object[0][0];

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            resultList = createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error retrieving all records from " + type.getSimpleName(), e);
        }
        return resultList;
    }
}