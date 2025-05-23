package org.bshg.productmanagement.zsecurity.helper;

import java.io.Serial;
import java.io.Serializable;

public class AuthenticationRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 2748928758378354357L;

    private String username;
    private String password;

    public AuthenticationRequest(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public AuthenticationRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
