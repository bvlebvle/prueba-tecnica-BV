/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.main;

/**
 *
 * @author valentinadiaz
 */
public class Cuenta {
	private int clientId;
	private int insuranceId;
	private int balance;

	public Cuenta(int clientId, int insuranceId, int balance) {
		this.clientId = clientId;
		this.insuranceId = insuranceId;
		this.balance = balance;
	}

	public int getClientId() {
		return clientId;
	}

	public int getBalance() {
		return balance;
	}

	public int getInsuranceId() {
		return insuranceId;
	}
}