package mattmck.mywebservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTransactionManagerConfig {

	@Bean
	public ChainedTransactionManager chainedTransactionManager(
			@Qualifier("db1TransactionManager") PlatformTransactionManager db1TransactionManager,
			@Qualifier("db2TransactionManager") PlatformTransactionManager db2TransactionManager) {
		return new ChainedTransactionManager(db1TransactionManager, db2TransactionManager);
	}

}
