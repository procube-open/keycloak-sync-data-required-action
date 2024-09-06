package com.twogenidentity.keycloak.authenticator;

import org.keycloak.authentication.RequiredActionContext;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.sessions.AuthenticationSessionModel;

// import jakarta.ws.rs.core.Response;

public class SyncDataRequiredAction implements RequiredActionProvider {

    public static final String PROVIDER_ID = "sync_data_config";

    @Override
    public void evaluateTriggers(RequiredActionContext context) {

    }

    @Override
    public void requiredActionChallenge(RequiredActionContext context) {
        System.out.println("Hello, World!");
        context.success();
    }

    @Override
    public void close() {

    }
}
