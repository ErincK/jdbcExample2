package com.erinc;

import com.erinc.repository.FutbolcuRepository;
import com.erinc.repository.entity.Futbolcu;
import com.erinc.utility.ConnectionProvider;

public class Main {
    public static void main(String[] args) {

        FutbolcuRepository futbolcuRepository = new FutbolcuRepository();
        Futbolcu futbolcu = new Futbolcu("Erinc","Orta Saha",8,8l);
        futbolcuRepository.save(futbolcu);
    }
}