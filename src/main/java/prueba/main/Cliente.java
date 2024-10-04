/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.main;

/**
 *
 * @author valentinadiaz
 */
public class Cliente {
	private int id;
	private String rut;
	private String name;

	public Cliente(int id, String rut, String name) {
		this.id = id;
		this.rut = rut;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getRut() {
		return rut;
	}

	public String getName() {
		return name;
	}
}