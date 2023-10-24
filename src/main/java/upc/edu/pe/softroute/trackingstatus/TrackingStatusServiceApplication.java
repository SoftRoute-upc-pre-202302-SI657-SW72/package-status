package upc.edu.pe.softroute.trackingstatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TrackingStatusServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackingStatusServiceApplication.class, args);
	}

}
