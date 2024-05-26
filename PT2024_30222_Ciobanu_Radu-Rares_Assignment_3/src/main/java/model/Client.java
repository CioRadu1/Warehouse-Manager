package model;

public class Client {

    private int clientId;
    private String clientName;
    private String clientEmail;
    private int clientAge;

    /**
     * Constructs a client with his data
     * @param clientId
     * @param clientName
     * @param clientEmail
     * @param clientAge
     */
    public Client(int clientId, String clientName, String clientEmail, int clientAge) {

        this.clientId = clientId;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientAge = clientAge;

    }
    public Client(String clientName, String clientEmail, int clientAge) {

        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientAge = clientAge;

    }

    public int getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public int getClientAge() {
        return clientAge;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public void setClientAge(int clientAge) {
        this.clientAge = clientAge;
    }
    @Override
    public String toString() {
        return "Client{" +
                "id=" + clientId +
                ", name='" + clientName + '\'' +
                ", email='" + clientEmail + '\'' +
                ", age=" + clientAge +
                '}';
    }
}
