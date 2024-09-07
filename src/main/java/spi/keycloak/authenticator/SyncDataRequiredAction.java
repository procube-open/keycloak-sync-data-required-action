package spi.keycloak.authenticator;

import org.jboss.logging.Logger;
import org.keycloak.authentication.RequiredActionContext;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.sessions.AuthenticationSessionModel;

// import jakarta.ws.rs.core.Response;

public class SyncDataRequiredAction implements RequiredActionProvider {

    private static final Logger logger = Logger.getLogger(SyncDataRequiredAction.class);

    public static final String PROVIDER_ID = "sync_data_config";

    @Override
    public void evaluateTriggers(RequiredActionContext context) {

    }

    @Override
    public void requiredActionChallenge(RequiredActionContext context) {
        logger.infof("Required Action 'Sync Data' is called");
        IdpClient idpClient = new IdpClient();
        idpClient.getUserData();
        context.success();
    }

    @Override
    public void processAction(RequiredActionContext context) {

    }

    @Override
    public void close() {

    }
}
