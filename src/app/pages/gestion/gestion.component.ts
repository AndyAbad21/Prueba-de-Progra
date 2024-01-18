import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/models/cliente';
import { ClienteService } from 'src/app/services/cliente.service';
@Component({
  selector: 'app-gestion',
  templateUrl: './gestion.component.html',
  styleUrls: ['./gestion.component.scss']
})
export class GestionComponent implements OnInit{
  clientes: any;

  cliente: Cliente = new Cliente();
  mostrarBotonGuardar: boolean = true;
  mostrarBotonActualizar: boolean = false;
  mostrarBotonCancelar: boolean = false;

  constructor(private ClienteService: ClienteService) {
    window.scrollTo({
      top: 0
    })
  }

  ngOnInit(): void {
    this.clientes = this.ClienteService.getClientes();
    alert(this.clientes);
  }

  cancelar() {
    this.mostrarBotonGuardar = true;
    this.mostrarBotonCancelar = false;
    this.mostrarBotonActualizar = false;
    this.clientes = {};
  }

  guardar() {
    if (this.cliente.codigo == null || this.cliente.dni == null || this.cliente.nombre == null) {
      alert("Debe llenar todos los parametros")
    } else {
      alert(this.cliente.nombre);
      this.ClienteService.saveClientes(this.cliente).subscribe(data => {
        console.log(data);
        if (data.codigo == 1) {
          this.cliente = new Cliente();
          this.ngOnInit();
        } else {
          alert("Error al insertar" + data.message)
        }
      });
    }
  }
  actualizar() {
    this.ClienteService.updateClientes(this.cliente).subscribe(data => {
      console.log(data);
    });
    this.cliente = {};
    this.ngOnInit();
    this.mostrarBotonCancelar = false;
    this.mostrarBotonActualizar = false;
    this.mostrarBotonGuardar = true;
  }

  editar(cliente: Cliente) {
    this.cliente = { ...cliente };
    this.mostrarBotonGuardar = false;
    this.mostrarBotonCancelar = true;
    this.mostrarBotonActualizar = true;
  }

  eliminar(auto: number) {
    this.ClienteService.deleteClientes(auto).subscribe(data => {
      console.log(data);
    });
    console.log(auto);
    this.ngOnInit();
  }
}
