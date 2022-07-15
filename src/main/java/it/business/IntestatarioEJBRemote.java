package it.business;

import it.data.ContoCorrente;
import jakarta.ejb.*;

@Remote
public interface IntestatarioEJBRemote {

	public ContoCorrente getSaldo(Long id, int PIN);
	public boolean versa(Long id, int PIN, double saldo);
	public boolean preleva(Long id, int PIN, double saldo);
	public boolean controllaOperazione(String operazione, Long id, int PIN, double quantita);
}
