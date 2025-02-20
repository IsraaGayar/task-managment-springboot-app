package banquemisr.challenge05.taskchallenge;

import banquemisr.challenge05.taskchallenge.config.RSAKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RSAKeyProperties.class)
@SpringBootApplication
public class TaskchallengeApplication {

	//	 Comment/uncomment ONLY ONE of these lines:
//	private static final String ACTIVE_PROFILE = "prod";
	private static final String ACTIVE_PROFILE = "dev"; // default for the challenge setup localy

	public static void main(String[] args) {
		if (ACTIVE_PROFILE!= null) {
			System.setProperty("spring.profiles.active", ACTIVE_PROFILE);
		}
		SpringApplication.run(TaskchallengeApplication.class, args);
	}

}