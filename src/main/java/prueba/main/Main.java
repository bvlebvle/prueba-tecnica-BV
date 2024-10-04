/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package prueba.main;

/**
 *
 * @author valentinadiaz
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static List<Cliente> clients = List.of(
            new Cliente(1, "86620855", "DANIEL BUSTOS"),
            new Cliente(2, "7317855K", "NICOLAS PEREZ"),
            new Cliente(3, "73826497", "ERNESTO GRANADO"),
            new Cliente(4, "88587715", "JORDAN MARTINEZ"),
            new Cliente(5, "94020190", "ALEJANDRO ZELADA"),
            new Cliente(6, "99804238", "DENIS ROJAS"));

    private static List<Cuenta> accounts = List.of(
            new Cuenta(6, 1, 15000),
            new Cuenta(1, 3, 18000),
            new Cuenta(5, 3, 135000),
            new Cuenta(2, 2, 5600),
            new Cuenta(3, 1, 23000),
            new Cuenta(5, 2, 15000),
            new Cuenta(3, 3, 45900),
            new Cuenta(2, 3, 19000),
            new Cuenta(4, 3, 51000),
            new Cuenta(5, 1, 89000),
            new Cuenta(1, 2, 1600),
            new Cuenta(5, 3, 37500),
            new Cuenta(6, 1, 19200),
            new Cuenta(2, 3, 10000),
            new Cuenta(3, 2, 5400),
            new Cuenta(3, 1, 9000),
            new Cuenta(4, 3, 13500),
            new Cuenta(2, 1, 38200),
            new Cuenta(5, 2, 17000),
            new Cuenta(1, 3, 1000),
            new Cuenta(5, 2, 600),
            new Cuenta(6, 1, 16200),
            new Cuenta(2, 2, 10000));

    private static final List<Seguro> insurances = List.of(
            new Seguro(1, "SEGURO APV"),
            new Seguro(2, "SEGURO DE VIDA"),
            new Seguro(3, "SEGURO COMPLEMENTARIO DE SALUD"));

    // Método para listar los IDs de clientes
    public static List<Integer> listClientsIds() {
        List<Integer> ids = new ArrayList<>();

        for (Cliente client : clients) {
            ids.add(client.getId());
        }

        return ids;
    }

    // Método para listar los IDs de clientes ordenados por RUT
    public static List<Integer> listClientsIdsSortedByRUT() {
        List<Integer> ids = listClientsIds();

        ids.sort((id1, id2) -> {
            String rut1 = clients.get(id1 - 1).getRut();
            String rut2 = clients.get(id2 - 1).getRut();

            return rut1.compareTo(rut2);
        });

        return ids;
    }

    // Método para listar los nombres de clientes ordenados de mayor a menor por la
    // suma TOTAL de los saldos de cada cliente en los seguros que participa
    public static List<String> sortClientsTotalBalances() {
        List<String> namesClient = new ArrayList<>();
        Map<Integer, Integer> balancePerClient = new HashMap<>();

        for (Cuenta account : accounts) {
            int clientIDAccount = account.getClientId();
            int balance = account.getBalance();

            balancePerClient.put(clientIDAccount, balancePerClient.getOrDefault(clientIDAccount, 0) + balance);
        }

        Map<String, Integer> balancePerClientName = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry : balancePerClient.entrySet()) {
            balancePerClientName.put(clients.get(entry.getKey() - 1).getName(), entry.getValue());
        }

        balancePerClientName.entrySet().stream().sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
                .forEach(entry -> namesClient.add(entry.getKey()));

        return namesClient;
    }

    // Método para generar un objeto en que las claves sean los nombres de los
    // seguros y los valores un arreglo con los RUTs de sus clientes ordenados
    // alfabéticamente por nombre
    public static Map<String, List<String>> insuranceClientsByRUT() {

        Map<String, List<String>> clientsByInsuranceRUT = new HashMap<>();

        for (Seguro insurance : insurances) {
            List<Cliente> clientsByInsuranceList = new ArrayList<>();

            for (Cuenta account : accounts) {
                int clientID = account.getClientId();
                int insuranceID = account.getInsuranceId();

                if (insuranceID == insurance.getId()) {
                    if (!clientsByInsuranceList.contains(clients.get(clientID - 1))) {
                        clientsByInsuranceList.add(clients.get(clientID - 1));
                    }
                }
            }

            clientsByInsuranceList.sort((client1, client2) -> client1.getName().compareTo(client2.getName()));

            List<String> clientRUTs = new ArrayList<>();
            for (Cliente client : clientsByInsuranceList) {
                clientRUTs.add(client.getRut());
            }

            clientsByInsuranceRUT.put(insurance.getName(), clientRUTs);
        }

        return clientsByInsuranceRUT;
    }

    // Método para generar un arreglo ordenado decrecientemente con los saldos de
    // clientes que tengan más de 30.000 en el "Seguro APV"
    public static List<Integer> higherClientsBalances() {
        Map<Integer, Integer> balances = new HashMap<>();

        for (Cuenta account : accounts) {
            int clientID = account.getClientId();
            int secureID = account.getInsuranceId();
            int balance = account.getBalance();

            if (secureID == 1) {
                balances.put(clientID, balances.getOrDefault(clientID, 0) + balance);
            }
        }

        List<Integer> clientBalaces = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : balances.entrySet()) {
            if (entry.getValue() > 30000) {
                clientBalaces.add(entry.getValue());
            }
        }

        clientBalaces.sort((saldo1, saldo2) -> saldo2 - saldo1);

        return clientBalaces;
    }

    // Método para generar un arreglo con IDs de los seguros ordenados
    // crecientemente por la cantidad TOTAL de dinero que administran
    public static List<Integer> insuranceSortedByHighestBalance() {
        List<Integer> insuranceReturn = new ArrayList<>();

        for (Seguro insurance : insurances) {
            insuranceReturn.add(insurance.getId());
        }

        Map<Integer, Integer> secureBalance = new HashMap<>();

        for (Cuenta account : accounts) {
            int insuranceID = account.getInsuranceId();

            secureBalance.put(insuranceID, secureBalance.getOrDefault(insuranceID, 0) + account.getBalance());
        }

        insuranceReturn.sort((id1, id2) -> secureBalance.get(id1) - secureBalance.get(id2));

        return insuranceReturn;
    }

    // Método para generar un objeto en que las claves sean los nombres de los
    // Seguros y los valores el número de clientes que solo tengan cuentas en ese
    // seguro
    public static Map<String, Long> uniqueInsurance() {
        Map<Integer, ArrayList<Integer>> securePerClient = new HashMap<>();

        for (Cuenta account : accounts) {
            int clientID = account.getClientId();
            int secureID = account.getInsuranceId();

            if (!securePerClient.containsKey(clientID)) {
                securePerClient.put(clientID, new ArrayList<>());
            }

            if (!securePerClient.get(clientID).contains(secureID)) {
                securePerClient.get(clientID).add(secureID);
            }
        }

        Map<String, Long> uniqueClientsForInsurance = new HashMap<>();

        for (Seguro insurance : insurances) {
            long quantity = 0;

            for (ArrayList<Integer> clientInsurances : securePerClient.values()) {
                if (clientInsurances.size() == 1 && clientInsurances.get(0) == insurance.getId()) {
                    quantity++;
                }
            }

            uniqueClientsForInsurance.put(insurance.getName(), quantity);
        }

        return uniqueClientsForInsurance;
    }

    // Método para generar un objeto en que las claves sean los nombres de los
    // Seguros y los valores el ID de su cliente con menos fondos
    public static Map<String, Integer> clientWithLessFunds() {
        Map<Integer, Map<Integer, Integer>> balancePerClientPerInsurance = new HashMap<>();

        for (Seguro insurance : insurances) {
            for (Cuenta account : accounts) {
                int clientID = account.getClientId();
                int insuranceID = account.getInsuranceId();
                int balance = account.getBalance();

                if (insuranceID == insurance.getId()) {
                    Map<Integer, Integer> clientBalances = balancePerClientPerInsurance.getOrDefault(insurance.getId(),
                            new HashMap<>());

                    clientBalances.put(clientID, clientBalances.getOrDefault(clientID, 0) + balance);

                    balancePerClientPerInsurance.put(insurance.getId(), clientBalances);
                }
            }
        }

        Map<String, Integer> clienteConMenosFondosPorSeguro = new HashMap<>();

        for (Map<Integer, Integer> balancePerClient : balancePerClientPerInsurance.values()) {
            int clientIdWithLessFunds = 0;
            int balanceClientIdWithLessFunds = Integer.MAX_VALUE;

            for (Map.Entry<Integer, Integer> entry : balancePerClient.entrySet()) {
                if (entry.getValue() < balanceClientIdWithLessFunds) {
                    clientIdWithLessFunds = entry.getKey();
                    balanceClientIdWithLessFunds = entry.getValue();
                }
            }

            for (Seguro insurance : insurances) {
                if (balancePerClientPerInsurance.get(insurance.getId()).containsKey(clientIdWithLessFunds)) {
                    clienteConMenosFondosPorSeguro.put(insurance.getName(), clientIdWithLessFunds);
                }
            }
        }

        return clienteConMenosFondosPorSeguro;
    }

    // Método para agregar un nuevo cliente con datos ficticios y una cuenta en el
    // "SEGURO COMPLEMENTARIO DE SALUD" con un saldo de 15000 para este nuevo
    // cliente, luego devolver el lugar que ocupa este cliente en el ranking de la
    // pregunta 2
    public static int newClientRanking() {
        int newClientId = clients.size() + 1;
        int newAccountId = accounts.size() + 1;

        Cliente newClient = new Cliente(newClientId, "12345678", "NUEVO CLIENTE");
        Cuenta newAccount = new Cuenta(newAccountId, 3, 15000);

        List<Cliente> newClients = new ArrayList<>(clients);
        List<Cuenta> newAccounts = new ArrayList<>(accounts);

        newClients.add(newClient);
        newAccounts.add(newAccount);

        clients = newClients;
        accounts = newAccounts;

        List<Integer> ranking = listClientsIdsSortedByRUT();

        for (int i = 0; i < ranking.size(); i++) {
            if (ranking.get(i) == newClient.getId()) {
                return i + 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(listClientsIds());
        System.out.println(listClientsIdsSortedByRUT());
        System.out.println(sortClientsTotalBalances());
        System.out.println(insuranceClientsByRUT());
        System.out.println(higherClientsBalances());
        System.out.println(insuranceSortedByHighestBalance());
        System.out.println(uniqueInsurance());
        System.out.println(clientWithLessFunds());
        System.out.println(newClientRanking());
    }
}
