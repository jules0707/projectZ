package house.my.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(value = { "house.my.dao.impl" })
// annotation de transaction doit se trouver au niveau du component scan des DAOs
@EnableTransactionManagement
public class DaoConfiguration {

}