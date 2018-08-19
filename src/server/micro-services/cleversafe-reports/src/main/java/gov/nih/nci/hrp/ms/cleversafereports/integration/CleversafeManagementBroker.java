/**
 * CleversafeManagementBroker.java
 * <p>
 * Copyright SVG, Inc. Copyright Leidos Biomedical Research, Inc
 * <p>
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/HPC/LICENSE.txt for details.
 */
package gov.nih.nci.hrp.ms.cleversafereports.integration;

import gov.nih.nci.hrp.common.exception.HrpException;
import gov.nih.nci.hrp.ms.cleversafereports.model.VaultSummary;

import java.util.Collection;

/**
 * Broker Interface for Cleversafe Management API.
 *
 * @author <a href="mailto:eran.rosenberg@nih.gov">Eran Rosenberg</a>
 */
public interface CleversafeManagementBroker {

    /**
     * Get summary report for all cloud storage vaults.
     *
     * @return A collection of vault summary.
     * @throws Exception on cloud storage management system failure.
     */
    Collection<VaultSummary> getVaultsSummary() throws HrpException;
}
