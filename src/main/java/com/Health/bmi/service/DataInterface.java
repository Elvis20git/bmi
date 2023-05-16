package com.Health.bmi.service;


import com.Health.bmi.model.Data;
import java.util.List;

public interface DataInterface {

    Data SaveData(Data data);
    Data UpdateData(Data data);
    void deleteDataById(Data data);
    List<Data> Datalist(String keyword, Data data);
    Data findDataById(Data data);

}
