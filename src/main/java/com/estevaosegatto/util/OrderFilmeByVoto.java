package com.estevaosegatto.util;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.estevaosegatto.models.Filme;

@Component
public class OrderFilmeByVoto implements Comparator<Filme> {
	@Override
    public int compare(Filme f1, Filme f2) {
		 return Double.compare(f2.getQuantidadeVotos(),f1.getQuantidadeVotos());
    }
}
