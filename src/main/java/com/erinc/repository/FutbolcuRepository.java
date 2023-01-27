package com.erinc.repository;

import com.erinc.repository.entity.Futbolcu;
import com.erinc.utility.ConnectionProvider;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * ICrud'ı oluşturalım
 * save method'u yazalım
 */

public class FutbolcuRepository implements ICrud<Futbolcu> {

    private String SQL;
    private ConnectionProvider connectionProvider;
    public FutbolcuRepository(){
        this.connectionProvider=new ConnectionProvider();
    }
    @Override
    public void save(Futbolcu futbolcu) {
        SQL="insert into futbolcu(isim,mevki,forma_no,takim_id) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement= connectionProvider.getPreparedStatement(SQL);
            preparedStatement.setString(1, futbolcu.getIsim());
            preparedStatement.setString(2, futbolcu.getMevki());
            preparedStatement.setInt(3, futbolcu.getFormaNo());
            preparedStatement.setLong(4,futbolcu.getTakimId());
            preparedStatement.executeUpdate();
            connectionProvider.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Futbolcu futbolcu) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Futbolcu> findAll() {
        return null;
    }

    @Override
    public Futbolcu findById(Long id) {
        return null;
    }

    @Override
    public List<Futbolcu> findByAny(String findKey) {
        return null;
    }
}
