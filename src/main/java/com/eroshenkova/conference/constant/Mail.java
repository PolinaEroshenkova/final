package com.eroshenkova.conference.constant;


/**
 * Constant class contains constant parameters for mail server configuration.
 * Final parameters is used as key for {@see mail.property} file.
 */
final public class Mail {

    /**
     * Parameter is used for retrieving host data from property file
     */
    public static final String SMTP_HOST = "mail.smtp.host";

    /**
     * Parameter is used for retrieving port data from property file
     */

    public static final String SMTP_PORT = "mail.smtp.port";

    /**
     * Parameter is used for retrieving username data from property file.
     * This username is used for authentication in mail server.
     */

    public static final String USERNAME = "mail.user.name";

    /**
     * Parameter is used for retrieving password data from property file.
     * This password is used for authentication in mail server.
     */

    public static final String PASSWORD = "mail.user.password";

    /**
     * Parameter is used for configuration mail server on certain PORT
     */

    public static final String SMTP_SOCKETFACTORY_PORT = "mail.smtp.socketFactory.port";

    /**
     * Parameter is used for configuration mail server
     */

    public static final String SMTP_SOCKETFACTORY_CLASS = "mail.smtp.socketFactory.class";

    /**
     * Parameter is used for configuration mail server
     */

    public static final String SSLSOCKETFACTORY = "javax.net.ssl.SSLSocketFactory";

    /**
     * Parameter is used for configuration mail server
     */

    public static final String AUTENTIFICATION = "mail.smtp.auth";
}
