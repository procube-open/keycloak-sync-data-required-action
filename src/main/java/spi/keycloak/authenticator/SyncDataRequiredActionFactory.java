package com.twogenidentity.keycloak.authenticator;

import org.keycloak.Config;
import org.keycloak.authentication.RequiredActionFactory;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class SyncDataRequiredActionFactory implements RequiredActionFactory {

    private static final SyncDataRequiredAction SINGLETON = new SyncDataRequiredAction();

    @Override
    public RequiredActionProvider create(KeycloakSession session) {
        return SINGLETON;
    }


    @Override
    public String getId() {
        return SyncDataRequiredAction.PROVIDER_ID;
    }

    @Override
    public String getDisplayText() {
        return "Sync Data";
    }

    @Override
    public void init(Config.Scope config) {

    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {

    }

    @Override
    public void close() {

    }
}
