package tk.vky.epublearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import tk.vky.epublearning.dao.UserDao;
import tk.vky.epublearning.model.Role;
import tk.vky.epublearning.model.User;
import tk.vky.epublearning.model.UserName;

@SpringBootApplication
public class EpublearningApplication{

	@Autowired
	UserDao userDao;
	
	public static void main(String[] args) {
		SpringApplication.run(EpublearningApplication.class, args);
	}


}
