/*
 * CleversafeReportsServiceImpl.java
 * <p>
 * Copyright SVG, Inc. Copyright Leidos Biomedical Research, Inc
 * <p>
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/HPC/LICENSE.txt for details.
 */
package gov.nih.nci.hrp.ms.cleversafereports.service.impl;

import gov.nih.nci.hrp.common.exception.HrpException;
import gov.nih.nci.hrp.ms.cleversafereports.dto.VaultSummaryResponse;
import gov.nih.nci.hrp.ms.cleversafereports.integration.CleversafeManagementBroker;
import gov.nih.nci.hrp.ms.cleversafereports.service.CleversafeReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Reports Service Implementation.
 *
 * @author <a href="mailto:eran.rosenberg@nih.gov">Eran Rosenberg</a>
 */
@Service
class CleversafeReportsServiceImpl implements CleversafeReportsService {

    // ---------------------------------------------------------------------//
    // Instance members
    // ---------------------------------------------------------------------//

    // The Cleversafe management broker.
    @Autowired
    private CleversafeManagementBroker cleversafeManagementBroker;

    // ---------------------------------------------------------------------//
    // Methods
    // ---------------------------------------------------------------------//

    // ---------------------------------------------------------------------//
    // CleversafeReportsService Interface Implementation
    // ---------------------------------------------------------------------//

    @Override
    public VaultSummaryResponse getVaultSummary() throws HrpException {
        VaultSummaryResponse vaultSummaryResponse = new VaultSummaryResponse();

        // Get the vault summary for all vaults from Cleversafe.
        vaultSummaryResponse.addAll(cleversafeManagementBroker.getVaultSummary());

        return vaultSummaryResponse;
    }
}


