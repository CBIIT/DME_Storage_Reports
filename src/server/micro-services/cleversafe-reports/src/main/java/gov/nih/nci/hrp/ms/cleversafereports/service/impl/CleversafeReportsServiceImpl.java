/**
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    // The Logger instance.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    // ---------------------------------------------------------------------//
    // Methods
    // ---------------------------------------------------------------------//

    // ---------------------------------------------------------------------//
    // CleversafeReportsService Interface Implementation
    // ---------------------------------------------------------------------//
/*
    @Override
    public VaultSummaryOld[] getVaultSummary() throws HrpException {
        // Get the vaults summary report.
        Collection<VaultSummaryOld> vaultsSummary = cleversafeManagementBroker.getVaultsSummary();
        logger.info("Vault Summary Report size: {}", vaultsSummary.size());

        // Return an array.
        VaultSummaryOld[] vaultSummariesArray = new VaultSummaryOld[vaultsSummary.size()];
        return vaultsSummary.toArray(vaultSummariesArray);
    }*/

    @Override
    public VaultSummaryResponse getVaultSummary() throws HrpException {
        VaultSummaryResponse vaultSummaryResponse = new VaultSummaryResponse();

        // Get the vault summary for all vaults from Cleversafe.
        vaultSummaryResponse.addAll(cleversafeManagementBroker.getVaultSummary());

        return vaultSummaryResponse;
    }
}


