import io.swagger.client.ApiClient;
import io.swagger.client.api.AuthenticationApi;
import io.swagger.client.model.LoginRequest;
import io.swagger.client.model.LoginResponse;

public class ClientTest {
    public static void main(String[] args) {
        try {
            // Configure API client
            ApiClient client = new ApiClient();
            client.setBasePath("http://localhost:8080/api");

            // Add default headers
            client.addDefaultHeader("Content-Type", "application/json");
            client.addDefaultHeader("Accept", "application/json");

            // Create API instance
            AuthenticationApi authApi = new AuthenticationApi(client);

            // Create login request
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setEmail("alice@mail.com");
            loginRequest.setUserType(LoginRequest.UserTypeEnum.USER);

            // Call the API with error details
            LoginResponse response = authApi.login(loginRequest);

            System.out.println("Login successful!");
            System.out.println("User ID: " + response.getId());
            System.out.println("Name: " + response.getName());
            System.out.println("Email: " + response.getEmail());

        } catch (io.swagger.client.ApiException e) {
            System.err.println("API Exception occurred:");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Response body: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("General exception:");
            e.printStackTrace();
        }
    }
}