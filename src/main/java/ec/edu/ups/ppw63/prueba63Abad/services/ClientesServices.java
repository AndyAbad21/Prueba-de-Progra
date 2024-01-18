package ec.edu.ups.ppw63.prueba63Abad.services;

import java.util.List;

import ec.edu.ups.ppw63.prueba63Abad.business.GestionClientes;
import ec.edu.ups.ppw63.prueba63Abad.model.Cliente;
import ec.edu.ups.ppw63.prueba63Abad.model.Deuda;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("clientes")
public class ClientesServices {
	@Inject
	private GestionClientes gClientes;
	
	@GET
    @Path("{id}/deudas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeudasCliente(@PathParam("id") int clienteId) {
        try {
            Cliente cliente = gClientes.getClientePorId(clienteId);

            if (cliente != null) {
                List<Deuda> deudas = cliente.getDeudas();
                return Response.ok(deudas).build();
            } else {
                ErrorMessage error = new ErrorMessage(4, "Cliente no encontrado");
                return Response.status(Response.Status.NOT_FOUND).entity(error).build();
            }
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)//Obtiene los parametros de las consultas y los envia
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Cliente cliente) {
		try{
			gClientes.guardarClientes(cliente);
			ErrorMessage error = new ErrorMessage(1, "ok");
			return Response.status(Response.Status.CREATED).entity(error).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Cliente cliente) {
		try{
			gClientes.actualizarCliente(cliente);
			return Response.ok(cliente).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("id") int codigo) {
		try{
			gClientes.borrarCliente(codigo);
			return "OK, se borro el cliente";
		}catch (Exception e) {
			// TODO: handle exception
			return "Error al eliminar el cliente";
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response leer(@QueryParam("dni") String cedula, @QueryParam("nombre") String nombre) {
		try{
			System.out.println("cliente " +  cedula + " nombre=" + nombre);
			Cliente cli = gClientes.getClienteCedula(cedula);
			return Response.ok(cli).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@GET
	@Path("{dni}/{nombre}")
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response leer2(@PathParam("dni") String cedula, @PathParam("nombre") String nombre) {
		try{
			System.out.println("cedula " +  cedula + " nombre=" + nombre);
			Cliente cli = gClientes.getClienteCedula(cedula);
			return Response.ok(cli).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getClientes(){
		List<Cliente> clientes = gClientes.getClientes();
		if(clientes.size()>0)
			return Response.ok(clientes).build();
		
		ErrorMessage error = new ErrorMessage(6, "No se registran clientes");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(error)
				.build();
		
	}
}
