package com.Health.bmi.service;

//import com.Employee3.model.Employee;
//import com.Employee3.repository.EmployeeRepository;
import com.Health.bmi.model.User;
import com.Health.bmi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JavaMailSender mailSender;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registeruser(String login, String email, String password){

        if (email == null || password == null) {
            return null;
        } else {
            if(userRepository.findFirstByEmail(email).isPresent()){
                System.out.println("Duplicated email");
                return null;
            }
            User user = new User();
            user.setEmail(email);
            user.setLogin(login);
            user.setPassword(password);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("murengerantwarielvis03@gmail.com");
            message.setTo(user.getEmail());
            message.setText("Welcome!! Now You Can Login and Register a new Product !!");
            message.setSubject("Confirmation Message");
            mailSender.send(message);
            return userRepository.save(user);
        }
    }

    public User authenticate(String email, String password){
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}
