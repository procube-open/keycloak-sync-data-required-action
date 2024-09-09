package spi.keycloak.authenticator;

import org.jboss.logging.Logger;
import org.keycloak.authentication.RequiredActionContext;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.sessions.AuthenticationSessionModel;
import org.keycloak.models.UserModel;

// import org.keycloak.models.utils.ModelToRepresentation;
// import org.keycloak.representations.idm.CredentialRepresentation;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class SyncDataRequiredAction implements RequiredActionProvider {

    private static final Logger logger = Logger.getLogger(SyncDataRequiredAction.class);

    public static final String PROVIDER_ID = "sync_data_config";

    @Override
    public void evaluateTriggers(RequiredActionContext context) {

    }

    @Override
    public void requiredActionChallenge(RequiredActionContext context) {
        UserModel currentUser = context.getUser();

        logger.infof("Required Action 'Sync Data' is called by %s.", currentUser.getUsername());

        // // currentUser の credentials を取得する。これには secretData も含まれている。
        // List<CredentialRepresentation> credentials = currentUser.credentialManager()
        //                                                 .getStoredCredentialsStream()
        //                                                 .map(ModelToRepresentation::toRepresentation)
        //                                                 .collect(Collectors.toList());

        String url = String.format("http://localhost:3000/user/%s", currentUser.getId());
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .GET()
                                .build();

        try {
            logger.infof("Send id to flask. Username: %s", currentUser.getUsername());
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.infof("Response: %s", response);
        } catch (IOException | InterruptedException ex) {
            logger.errorf("Fail to send id. Username: %s. Error message: %s", currentUser.getUsername(), ex);
            ex.printStackTrace();
        }

        context.success();
    }

    @Override
    public void processAction(RequiredActionContext context) {

    }

    @Override
    public void close() {

    }
}
