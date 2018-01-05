package adaptergson;

import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import dominio.Correntista;

public class EliminarRecursividade implements ExclusionStrategy{

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		System.out.println(f.getDeclaredClass() + " - " + f.getName());
		if(f.getDeclaredClass() == Correntista.class && f.getName().equals("correntista"))
			return true;
		else if(f.getDeclaredClass() == List.class && f.getName().equals("transacoes"))
			return true;
		else
			return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

}
