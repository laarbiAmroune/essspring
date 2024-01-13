package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Client;
import creditdirect.clientmicrocervice.entities.Particulier;

import java.util.List;
import java.util.Map;

public interface ClientService {
    List<Client> getAllClients();
    Client getClientById(Long id);
    Client createClient(Client client);
    Client updateClient(Long id, Client client);
    void deleteClient(Long id);




    String login(String email, String password);


    Map<String, Object> loginWithClientInfo(String email, String password);

    Particulier subscribeParticulier(Particulier particulier);

    Client updateClientPassword(Long clientId, String newPassword);
    void activateClientByEmail(String email);

    void resetPasswordByEmail(String email);


}
