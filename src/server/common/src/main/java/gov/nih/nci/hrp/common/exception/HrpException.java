/**
 * HrpException.java
 * <p>
 * Copyright SVG, Inc.
 * Copyright Leidos Biomedical Research, Inc
 * <p>
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/HPC/LICENSE.txt for details.
 */
package gov.nih.nci.hrp.common.exception;

import gov.nih.nci.hrp.common.model.ErrorType;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * The HPC Reports Exception.
 *
 * @author <a href="mailto:eran.rosenberg@nih.gov">Eran Rosenberg</a>
 */
public class HrpException extends Exception implements java.io.Serializable {

    //---------------------------------------------------------------------//
    // Instance members
    //---------------------------------------------------------------------//

    // UID.
    private static final long serialVersionUID = 1L;

    // The error type value.
    private ErrorType errorType = null;

    //---------------------------------------------------------------------//
    // constructors
    //---------------------------------------------------------------------//

    /**
     * Default constructor is disabled.
     */
    @SuppressWarnings("unused")
    private HrpException() {
    }

    /**
     * Constructs a new HrpException with a given message and error type.
     *
     * @param message   The message for the exception, normally the cause.
     * @param errorType The type of the error, often the subsystem that is
     *                  the source of the error.
     */
    public HrpException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    /**
     * Constructs a new HrpException with a given message, and
     * a Throwable cause
     *
     * @param message The message for the exception.
     * @param cause   The root cause Throwable.
     */
    public HrpException(String message, Throwable cause) {
        super(message, cause);

        // Propagate the error type, and reject reason if the cause is a HrpException.
        if (cause instanceof HrpException) {
            this.errorType = ((HrpException) cause).getErrorType();
        }
    }

    /**
     * Constructs a new HrpException with a given message, error type and
     * a Throwable cause.
     *
     * @param message   The message for the exception.
     * @param errorType The type of the error, often the subsystem that is
     *                  the source of the error.
     * @param cause     The root cause Throwable.
     */
    public HrpException(String message, ErrorType errorType,
                        Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }

    //---------------------------------------------------------------------//
    // Methods
    //---------------------------------------------------------------------//

    @Override
    public String toString() {
        return super.toString() + "[" + errorType + "]";
    }

    /**
     * Get the error type.
     *
     * @return The error type.
     */
    public ErrorType getErrorType() {
        return errorType;
    }

    /**
     * Get the stack trace.
     *
     * @return The stack trace.
     */
    public String getStackTraceString() {
        StringWriter writer = new StringWriter();
        printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }
}


