/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import prueba.main.Main;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author valentinadiaz
 */
public class MainTest {
    public MainTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void listClientIdsTest() {
        int expectedQuantity = 6;
        List<Integer> expectedIDS = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        List<Integer> ids = Main.listClientsIds();

        Assertions.assertEquals(expectedQuantity, ids.size());
        Assertions.assertIterableEquals(expectedIDS, ids);
    }

    @Test
    public void listClientsIdsSortedByRUTTest() {
        int expectedQuantity = 6;

        List<Integer> expectedIds = new ArrayList<>(Arrays.asList(2, 3, 1, 4, 5, 6));
        List<Integer> ids = Main.listClientsIdsSortedByRUT();

        Assertions.assertEquals(expectedQuantity, ids.size());
        Assertions.assertIterableEquals(expectedIds, ids);
    }

    @Test
    public void sortClientsTotalBalancesTest() {

        List<String> expectedNames = new ArrayList<>(Arrays.asList("ALEJANDRO ZELADA", "ERNESTO GRANADO",
                "NICOLAS PEREZ", "JORDAN MARTINEZ", "DENIS ROJAS", "DANIEL BUSTOS"));

        List<String> names = Main.sortClientsTotalBalances();

        Assertions.assertIterableEquals(expectedNames, names);
    }

    @Test
    public void insuranceClientsByRUTTest() {

        Map<String, List<String>> expectedMap = Map.of(
                "SEGURO DE VIDA", List.of("94020190", "86620855", "73826497", "7317855K"),
                "SEGURO APV", List.of("94020190", "99804238", "73826497", "7317855K"),
                "SEGURO COMPLEMENTARIO DE SALUD", List.of("94020190", "86620855", "73826497", "88587715", "7317855K"));

        Map<String, List<String>> clientBySecure = Main.insuranceClientsByRUT();

        Assertions.assertEquals(expectedMap, clientBySecure);
    }

    @Test
    public void higherClientsBalacesTest() {
        List<Integer> expectedBalances = new ArrayList<>(Arrays.asList(89000, 50400, 38200, 32000));

        List<Integer> balances = Main.higherClientsBalances();

        for (int i = 0; i < balances.size() - 1; i++) {
            Assertions.assertTrue(balances.get(i) >= balances.get(i + 1),
                    "Valor " + balances.get(i) + "debe ser mayor que " + balances.get(i + 1));
        }

        for (int saldo : balances) {
            Assertions.assertTrue(saldo > 30000, "Valor debe ser mayor a 30.000");
        }

        Assertions.assertIterableEquals(expectedBalances, balances);
    }

    @Test
    public void insuranceSortedByHighestBalanceTest() {
        List<Integer> expectedIds = new ArrayList<>(Arrays.asList(2, 1, 3));

        List<Integer> ids = Main.insuranceSortedByHighestBalance();

        Assertions.assertIterableEquals(expectedIds, ids);

    }

    @Test
    public void uniqueInsuranceTest() {
        Map<String, Long> expectedResult = Map.of(
                "SEGURO DE VIDA", 0L,
                "SEGURO APV", 1L,
                "SEGURO COMPLEMENTARIO DE SALUD", 1L);

        Map<String, Long> uniqueInsurance = Main.uniqueInsurance();

        Assertions.assertEquals(expectedResult, uniqueInsurance);
    }

    @Test
    public void clientWithLessFundsTest() {
        Map<String, Integer> expectedResult = Map.of(
                "SEGURO DE VIDA", 1,
                "SEGURO APV", 3,
                "SEGURO COMPLEMENTARIO DE SALUD", 1);

        Map<String, Integer> clientWithLessFunds = Main.clientWithLessFunds();

        Assertions.assertEquals(expectedResult, clientWithLessFunds);
    }

    @Test
    public void newClientRankingTest() {
        int expectedPosition = 1;
        int position = Main.newClientRanking();

        Assertions.assertEquals(expectedPosition, position);
    }
}
