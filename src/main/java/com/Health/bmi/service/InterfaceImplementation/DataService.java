package com.Health.bmi.service.InterfaceImplementation;

import com.Health.bmi.model.Data;
import com.Health.bmi.repository.DataRepository;
import com.Health.bmi.service.DataInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DataService implements DataInterface {

    @Autowired
    private DataRepository dataRepo;
    @Autowired
    private JavaMailSender mailSender;
    @Override
//    @CachePut(value = "data-cache", key="'dataCache'+#data.id")
    public Data SaveData(Data data) {
        if (data.getWeight() / (data.getHeight() * data.getHeight()) < 18.5) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("murengerantwarielvis03@gmail.com");
            message.setTo(data.getEmail());
            message.setText("Under Weight !!");
            message.setSubject("Confirmation Message");
            mailSender.send(message);

        } else if (data.getWeight() / (data.getHeight() * data.getHeight()) > 18.5 && data.getWeight() / (data.getHeight() * data.getHeight())<25) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("murengerantwarielvis03@gmail.com");
            message.setTo(data.getEmail());
            message.setText("Normal!!");
            message.setSubject("Confirmation Message");
            mailSender.send(message);
        } else if (data.getWeight() / (data.getHeight() * data.getHeight()) >25 && data.getWeight() / (data.getHeight() * data.getHeight()) < 30 ) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("murengerantwarielvis03@gmail.com");
            message.setTo(data.getEmail());
            message.setText("Over Weight");
            message.setSubject("Confirmation Message");
            mailSender.send(message);
        }else {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("murengerantwarielvis03@gmail.com");
            message.setTo(data.getEmail());
            message.setText("Obese!!");
            message.setSubject("Confirmation Message");
            mailSender.send(message);
        }
        return dataRepo.save(data);
    }

    @Override
    @CachePut(value = "data-cache", key="'dataCache'+#data.user_id")
    public Data UpdateData(Data data) {
        Data p = findDataById(data);
        if (p != null) {
            p.setId(data.getId());
            p.setUser_id(data.getUser_id());
            p.setFname(data.getFname());
            p.setLname(data.getLname());
            p.setEmail(data.getEmail());
            p.setGender(data.getGender());
            p.setHeight(data.getHeight());
            p.setWeight(data.getWeight());
            return dataRepo.save(p);
        } else {
            return SaveData(data);
        }
    }

    @Override
    @CacheEvict(value = "data-cache", key="'dataCache'+#data.user_id")
    public void deleteDataById(Data data) {
        dataRepo.delete(data);
    }

    @Override
//    @Cacheable(value = "data-cache", key="'dataCache'+#data.user_id")
    public List<Data> Datalist(String keyword, Data data) {
        if (keyword!=null){
            return dataRepo.search(keyword);
        }
        return dataRepo.findAll();
    }

    @Override
//    @Cacheable(value = "dataCache", key="#data.id")
    public Data findDataById(Data data) {
        return dataRepo.findById(data.getId()).get();
    }
}
