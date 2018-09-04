/*
 * CleversafeReportsController.java
 * <p>
 * Copyright SVG, Inc. Copyright Leidos Biomedical Research, Inc
 * <p>
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/HPC/LICENSE.txt for details.
 */
package gov.nih.nci.hrp.ms.cleversafereports.ws.rest.impl;

import gov.nih.nci.hrp.common.exception.HrpException;
import gov.nih.nci.hrp.ms.cleversafereports.dto.VaultSummaryResponse;
import gov.nih.nci.hrp.ms.cleversafereports.service.CleversafeReportsService;
import gov.nih.nci.hrp.ms.cleversafereports.ws.rest.CleversafeReportsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * Cleversafe Reports REST Controller.
 *
 * @author <a href="mailto:eran.rosenberg@nih.gov">Eran Rosenberg</a>
 */
@CrossOrigin
@RestController
class CleversafeReportsController implements CleversafeReportsApi {

    // ---------------------------------------------------------------------//
    // Instance members
    // ---------------------------------------------------------------------//

    // The reports service.
    @Autowired
    private CleversafeReportsService cleversafeReportsService;

    // ---------------------------------------------------------------------//
    // Methods
    // ---------------------------------------------------------------------//

    // ---------------------------------------------------------------------//
    // CleversafeReportsApi Interface Implementation
    // ---------------------------------------------------------------------//

    @Override
    public ResponseEntity<VaultSummaryResponse> getVaultSummary() throws HrpException {
        return ResponseEntity.ok(cleversafeReportsService.getVaultSummary());
    }
}
