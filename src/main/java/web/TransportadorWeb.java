package web;

import com.google.gson.Gson;

import controle.ATransportador;

public class TransportadorWeb extends ATransportador{
	
	public String enviarObjetoWeb(){
		
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
