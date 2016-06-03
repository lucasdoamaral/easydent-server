package br.ucs.easydental.util;

import br.ucs.easydental.enums.EasyDentEnum;

public class Util {
	
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
