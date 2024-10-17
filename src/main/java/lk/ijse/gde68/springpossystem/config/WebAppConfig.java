package lk.ijse.gde68.springpossystem.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("lk.ijse.gde68.springpossystem")
@EnableWebMvc
@EnableJpaRepositories("lk.ijse.gde68.springpossystem")
@EnableTransactionManagement
public class WebAppConfig {
}
