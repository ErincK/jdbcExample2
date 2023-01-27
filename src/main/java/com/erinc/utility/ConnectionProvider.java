package com.erinc.utility;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class ConnectionProvider {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public boolean  openConnection(){
        try {
            Driver.class.forName("org.postgresql.Driver");
            DatabaseConnection.getConnection();
            System.out.println(DatabaseConnection.dbName+" veritabanına başarılı bir şekilde bağlanıldı");
            return  true;
        } catch (Exception exception) {
            System.out.println("Veri tabanina baglanilamadi.");
            System.out.println(exception.getMessage());
            return  false;
        }
    }

    public void closeConnection(){
        try{
            if (!DatabaseConnection.getConnection().isClosed()){
                DatabaseConnection.getConnection().close();
                System.out.println("Baglanti sonlandirildi.");
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException();
        }
    }

    public boolean executeQuery(String SQL){
        if (openConnection()){
            try{
                preparedStatement=DatabaseConnection.getConnection().prepareStatement(SQL);
                preparedStatement.executeUpdate();
                closeConnection();
                return true;
            }catch (Exception exception){
                System.out.println(exception.getMessage());
                closeConnection();
            }
        }
        return false;
    }

    public PreparedStatement  getPreparedStatement(String SQL){
        if (openConnection()){
            try {
                preparedStatement=DatabaseConnection.getConnection().prepareStatement(SQL);
            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        }
        return  preparedStatement;
    }

    public Optional<ResultSet> getData(String SQL){
        if(openConnection()){
            try{
                preparedStatement=DatabaseConnection.getConnection().prepareStatement(SQL);
                resultSet=preparedStatement.executeQuery();
                closeConnection();
            }catch (Exception exception){
                throw new RuntimeException(exception);
            }
        }
        return Optional.ofNullable(resultSet);
    }




}
