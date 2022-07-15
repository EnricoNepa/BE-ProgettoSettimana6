package it.business;

import it.data.ContoCorrente;
import it.data.ContoCorrenteDAO;
import jakarta.ejb.*;

/**
 * Session Bean implementation class IntestatarioEJB
 */
@Stateless
@LocalBean
public class IntestatarioEJB implements IntestatarioEJBRemote, IntestatarioEJBLocal {

	private ContoCorrenteDAO contocorrente = new ContoCorrenteDAO();

	public IntestatarioEJB() {
		// TODO Auto-generated constructor stub
	}

	public ContoCorrenteDAO getId() {
		return contocorrente;
	}

	public void setId(ContoCorrenteDAO contocorrente) {
		this.contocorrente = contocorrente;
	}

	public ContoCorrente getSaldo(Long id, int PIN) {
		return contocorrente.getSaldo(id, PIN);
	}
	
	public boolean versa(Long id, int PIN, double saldo) {
		return contocorrente.versa(id, PIN, saldo);
	}

	public boolean preleva(Long id, int PIN, double saldo) {
		return contocorrente.preleva(id, PIN, saldo);
	}

	public boolean controllaOperazione(String operazione, Long id, int PIN, double quantita) {
		ContoCorrente saldo = contocorrente.getSaldo(id	, PIN);
		if (!contocorrente.esiste(id, PIN)) {
			System.out.println("esiste");
			return false;
		} else if (quantita < 0) {
			System.out.println("minore zero");
			return false;
		} else if (!(operazione.equals("saldo")) && !(operazione.equals("prelievo"))
				&& !(operazione.equals("versamento"))) 
		{
			System.out.println("operazione");
			return false;
		} else if (quantita > saldo.getSaldo()) {
			System.out.println("maggiore saldo");
			return false;
		}
		return true;
	}
}
