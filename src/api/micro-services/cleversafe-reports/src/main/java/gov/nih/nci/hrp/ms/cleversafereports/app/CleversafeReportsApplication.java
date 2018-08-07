package gov.nih.nci.hrp.ms.cleversafereports.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"gov.nih.nci.hrp.ms.cleversafereports.*"})
public class CleversafeReportsApplication {

	public static void main(String[] args) throws Exception {

		DisableSSLCertificateCheckUtil.disableChecks();

		SpringApplication.run(CleversafeReportsApplication.class, args);
	}
}
