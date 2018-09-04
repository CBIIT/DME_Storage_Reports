/**
 * CleversafeReportsService.java
 * <p>
 * Copyright SVG, Inc. Copyright Leidos Biomedical Research, Inc
 * <p>
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/HPC/LICENSE.txt for details.
 */
package gov.nih.nci.hrp.ms.cleversafereports.service;

import gov.nih.nci.hrp.common.exception.HrpException;
import gov.nih.nci.hrp.ms.cleversafereports.dto.VaultSummaryResponse;

/**
 * Cleversafe Reports Service Interface.
 *
 * @author <a href="mailto:eran.rosenberg@nih.gov">Eran Rosenberg</a>
 */
public interface CleversafeReportsService {

    /**
     * Get summary report of all vaults.
     *
     * @return An array of vault summary.
     * @throws Exception on cloud storage management system failure.
     */
    //VaultSummaryOld[] getVaultSummary() throws HrpException;

    /**
     * Get summary report of all vaults.
     *
     * @return Vault summary response DTO.
     * @throws HrpException on cloud storage management system failure.
     */
    VaultSummaryResponse getVaultSummary() throws HrpException;
}
