package br.ucs.easydent.app.util;

import br.ucs.easydent.model.enums.EasyDentEnum;

public class Util2 {
	
	public static <T extends EasyDentEnum> T getEnumById (T[] enums, Integer id){
		
		if (id == null) {
			return null;
		}
		
		for (T _enum: enums) {
			if (id.equals(_enum.getId())) {
				return _enum;
			}
		}
		
		return null;
	}

}
