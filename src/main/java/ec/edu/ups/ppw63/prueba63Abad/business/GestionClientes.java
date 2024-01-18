package ec.edu.ups.ppw63.prueba63Abad.business;

import java.util.List;

import ec.edu.ups.ppw63.prueba63Abad.dao.ClientesDAO;
import ec.edu.ups.ppw63.prueba63Abad.model.Cliente;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionClientes {
	
	@Inject
	private ClientesDAO daoCliente;
	
	public void guardarClientes(Cliente cliente) {
		Cliente cli = daoCliente.read(cliente.getCodigo());
		if (cli != null) {
			daoCliente.update(cliente);
		} else {
			daoCliente.insert(cliente);
		}
	}

	public void actualizarCliente(Cliente cliente) throws Exception {
		Cliente cli = daoCliente.read(cliente.getCodigo());
		if (cli != null) {
			daoCliente.update(cliente);
		} else {
			throw new Exception("Cliente no existe");
		}
	}

	public Cliente getClienteCedula(String cedula) throws Exception {

		if (cedula.length() != 10)
			throw new Exception("Cedula incorrecta");

		return daoCliente.getClientePorCedula(cedula);
	}

	public void borrarCliente(int codigo) {

		daoCliente.remove(codigo);
	}

	public List<Cliente> getClientes() {
		return daoCliente.getAll();
	}
	
	public Cliente getClientePorId(int clienteId) {
	    return daoCliente.read(clienteId);
	}
}
