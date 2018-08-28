/**
 * HrpExceptionHandler.java
 * <p>
 * Copyright SVG, Inc.
 * Copyright Leidos Biomedical Research, Inc
 * <p>
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/HPC/LICENSE.txt for details.
 */
package gov.nih.nci.hrp.common.ws.rest.impl;

import gov.nih.nci.hrp.common.dto.ErrorResponse;
import gov.nih.nci.hrp.common.exception.HrpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * The HPC Reports Exception Handler. Creates a REST response from HrpException.
 *
 * @author <a href="mailto:eran.rosenberg@nih.gov">Eran Rosenberg</a>
 */
@ControllerAdvice
class HrpExceptionHandler {

    // ---------------------------------------------------------------------//
    // Methods
    // ---------------------------------------------------------------------//

    /**
     * Create a ResponseEntity<ErrorResponse> from HrpException
     *
     * @param e       The HrpException.
     * @param request The web request.
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(value
            = {HrpException.class})
    protected ResponseEntity<ErrorResponse> handleConflict(
            HrpException e, WebRequest request) {
        // Create an Error Response DTO.
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setType(e.getErrorType());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStackTrace(e.getStackTraceString());

        // Determine the status.
        HttpStatus status = null;
        switch (e.getErrorType()) {
            case INVALID_REQUEST:
            case REQUEST_REJECTED:
                status = HttpStatus.BAD_REQUEST;
                break;

            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        return ResponseEntity.status(status).body(errorResponse);
    }
}
