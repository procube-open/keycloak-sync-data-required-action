package spi.keycloak.authenticator;

import org.jboss.logging.Logger;
import org.keycloak.authentication.RequiredActionContext;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.sessions.AuthenticationSessionModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.RealmModel;

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
import org.json.JSONObject;


public class SyncDataRequiredAction implements RequiredActionProvider {

    private static final Logger logger = Logger.getLogger(SyncDataRequiredAction.class);

    public static final String PROVIDER_ID = "sync_data_config";

    @Override
    public void evaluateTriggers(RequiredActionContext context) {

    }

    @Override
    public void requiredActionChallenge(RequiredActionContext context) {
        UserModel currentUser = context.getUser();
        RealmModel realm = context.getRealm();

        logger.infof("Required Action 'Sync Data' is called by %s in realm '%s'.", currentUser.getUsername(), realm.getName());

        // // currentUser の credentials を取得する。これには secretData も含まれている。
        // List<CredentialRepresentation> credentials = currentUser.credentialManager()
        //                                                 .getStoredCredentialsStream()
        //                                                 .map(ModelToRepresentation::toRepresentation)
        //                                                 .collect(Collectors.toList());

        JSONObject userParams = new JSONObject();
        userParams.put("realm", realm.getName());
        userParams.put("userId", currentUser.getId());

        String url = "http://localhost:3000/sync";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString(userParams.toString()))
                                .build();

        logger.infof("Send request for user_id: %s, username: %s.", currentUser.getId(), currentUser.getUsername());
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(response -> {
                logger.infof("Response: %s", response);
            })
            .exceptionally(ex -> {
                logger.errorf("Fail to send request for user_id: %s, username: %s. Error message: %s", currentUser.getId(), currentUser.getUsername(), ex);
                ex.printStackTrace();
                return null;
            });

        context.success();
    }

    @Override
    public void processAction(RequiredActionContext context) {

    }

    @Override
    public void close() {

    }
}
