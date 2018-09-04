/*
 * CleversafeManagementBrokerImpl.java
 * <p>
 * Copyright SVG, Inc. Copyright Leidos Biomedical Research, Inc
 * <p>
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/HPC/LICENSE.txt for details.
 */
package gov.nih.nci.hrp.ms.cleversafereports.integration.impl;

import gov.nih.nci.hrp.common.exception.HrpException;
import gov.nih.nci.hrp.common.model.ErrorType;
import gov.nih.nci.hrp.ms.cleversafereports.integration.CleversafeManagementBroker;
import gov.nih.nci.hrp.ms.cleversafereports.model.VaultSummary;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Cleversafe Management Broker Implementation.
 *
 * @author <a href="mailto:eran.rosenberg@nih.gov">Eran Rosenberg</a>
 */
@Component
class CleversafeManagementBrokerImpl implements CleversafeManagementBroker {

    // ---------------------------------------------------------------------//
    // Constants
    // ---------------------------------------------------------------------//

    // 1TB in bytes.
    private static final double TERA_BYTES = 1099511627776.0;

    // ---------------------------------------------------------------------//
    // Instance members
    // ---------------------------------------------------------------------//

    // The REST template.
    private RestTemplate restTemplate;

    // The Cleverafe List Vaults endpoint URL.
    @Value("${hpc.reports.integration.cleversafe.list-vaults-endpoint}")
    private String listVaultsUrl;

    // JSON Parser
    private JSONParser jsonParser = new JSONParser();

    // ---------------------------------------------------------------------//
    // Constructors
    // ---------------------------------------------------------------------//

    /**
     * Default Constructor is disabled.
     */
    private CleversafeManagementBrokerImpl() {
    }

    /**
     * Constructor for Spring Dependency Injection.
     *
     * @param username The cleversafe management API username.
     * @param password The cleversafe management API password.
     */
    @Autowired
    private CleversafeManagementBrokerImpl(@Value("${hpc.reports.integration.cleversafe.username}") String username,
                                           @Value("${hpc.reports.integration.cleversafe.password}") String password) {
        restTemplate = new RestTemplateBuilder().basicAuthorization(username, password).build();
    }

    // ---------------------------------------------------------------------//
    // Methods
    // ---------------------------------------------------------------------//

    // ---------------------------------------------------------------------//
    // CleversafeManagementBroker Interface Implementation
    // ---------------------------------------------------------------------//

    @Override
    public Collection<VaultSummary> getVaultSummary() throws HrpException {
        // Invoke Cleversafe list-vaults API.
        ResponseEntity<String> response = restTemplate.getForEntity(listVaultsUrl, String.class);
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new HrpException("Failed to call Cleversafe list-vaults service: [" + response.getStatusCode() + "] :" + response.getBody(), ErrorType.INTEGRATION_ERROR);
        }

        // Parse the response JSON.
        Collection<VaultSummary> vaultsSummary = new ArrayList<>();
        JSONObject responseData;
        try {
            responseData = (JSONObject) ((JSONObject) jsonParser.parse(response.getBody())).get("responseData");

        } catch (ParseException e) {
            throw new HrpException("Failed to parse Cleversafelist-vaults response: " + e.getMessage(), ErrorType.INTEGRATION_ERROR, e);
        }

        // Iterate through the vaults and add a summary object into the collection.
        JSONArray vaults = (JSONArray) responseData.get("vaults");
        if (vaults != null) {
            Iterator<JSONObject> vaultsIterator = vaults.iterator();
            while (vaultsIterator.hasNext()) {
                JSONObject vault = vaultsIterator.next();
                VaultSummary vaultSummary = new VaultSummary();
                vaultSummary.setName((String) vault.get("name"));
                vaultSummary.setDescription((String) vault.get("description"));
                vaultSummary.setCapacity((Long) vault.get("usableSize") / TERA_BYTES);
                vaultSummary.setUsed((Long) vault.get("usedLogicalSizeFromStorage") / TERA_BYTES);
                vaultSummary.setCreationDate(((String) vault.get("creationDate")).substring(0, 16));
                vaultsSummary.add(vaultSummary);
            }
        }

        return vaultsSummary;
    }


}
