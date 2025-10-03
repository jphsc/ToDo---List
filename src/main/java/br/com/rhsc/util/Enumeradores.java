package br.com.rhsc.util;

public class Enumeradores {

	public enum Status {
		PENDENTE(1, "Pendente"),
		FAZENDO(2, "Fazendo"),
		FEITO(3, "Feito"),
		CANCELADO(4, "Cancelado");
		
		private String descricao;
		private Integer codigo;

		public String getDescricao() {
			return this.descricao;
		}

		public Integer getCodigo() {
			return this.codigo;
		}
		
		private Status(Integer codigo, String descricao) {
			this.codigo = codigo;
			this.descricao = descricao;
		}
	}
}
