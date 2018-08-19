/**
 * CleversafeReportsController.java
 * <p>
 * Copyright SVG, Inc. Copyright Leidos Biomedical Research, Inc
 * <p>
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/HPC/LICENSE.txt for details.
 */
package gov.nih.nci.hrp.ms.cleversafereports.ws.rest.impl;

import gov.nih.nci.hrp.common.exception.HrpException;
import gov.nih.nci.hrp.ms.cleversafereports.model.NewVaultSummary;
import gov.nih.nci.hrp.ms.cleversafereports.model.VaultSummary;
import gov.nih.nci.hrp.ms.cleversafereports.model.VaultSummaryResponse;
import gov.nih.nci.hrp.ms.cleversafereports.service.CleversafeReportsService;
import gov.nih.nci.hrp.ms.cleversafereports.ws.rest.ReportsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Cleversafe Reports REST Controller.
 *
 * @author <a href="mailto:eran.rosenberg@nih.gov">Eran Rosenberg</a>
 */
@CrossOrigin
@RestController
class CleversafeReportsController implements ReportsApi {

    // ---------------------------------------------------------------------//
    // Instance members
    // ---------------------------------------------------------------------//

    // The reports service.
    @Autowired
    private CleversafeReportsService cleversafeReportsService;

    // ---------------------------------------------------------------------//
    // Constructors
    // ---------------------------------------------------------------------//

    /**
     * Constructor for Spring Dependency Injection.
     */
    private CleversafeReportsController() {
    }

    // ---------------------------------------------------------------------//
    // Methods
    // ---------------------------------------------------------------------//

    // ---------------------------------------------------------------------//
    // ReportsApi  Interface Implementation
    // ---------------------------------------------------------------------//

    @RequestMapping("/reports/vaultsummary")
    public VaultSummary[] getVaultSummary() throws HrpException {
        return cleversafeReportsService.getVaultSummary();
    }

    @Override
    public ResponseEntity<VaultSummaryResponse> getNewVaultSummary() {
        VaultSummaryResponse response = new VaultSummaryResponse();
        NewVaultSummary vs = new NewVaultSummary();
        vs.setName("Test");
        vs.setCapacity(new BigDecimal(123456));
        response.add(vs);
        return ResponseEntity.ok(response);
    }
}
