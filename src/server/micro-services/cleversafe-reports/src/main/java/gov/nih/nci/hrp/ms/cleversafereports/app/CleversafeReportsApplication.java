/*
 * CleversafeReportsApplication.java
 * <p>
 * Copyright SVG, Inc.
 * Copyright Leidos Biomedical Research, Inc
 * <p>
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/HPC/LICENSE.txt for details.
 */
package gov.nih.nci.hrp.ms.cleversafereports.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The Cleversafe Reports Microservice Application main class.
 *
 * @author <a href="mailto:eran.rosenberg@nih.gov">Eran Rosenberg</a>
 */
@SpringBootApplication
@ComponentScan({"gov.nih.nci.hrp.*"})
public class CleversafeReportsApplication {

    // ---------------------------------------------------------------------//
    // Methods
    // ---------------------------------------------------------------------//

    /**
     * Application's main().
     *
     * @param args Application arguments.
     */
    public static void main(String[] args) {

        // TODO - Fix it.
        DisableSSLCertificateCheckUtil.disableChecks();

        // Run the spring boot application.
        SpringApplication.run(CleversafeReportsApplication.class, args);
    }
}
