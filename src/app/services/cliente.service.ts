import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';// importar a mano
import { Cliente } from '../models/cliente';
import { Observable } from 'rxjs';
import { environment } from '../enviroments/enviroment';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private http: HttpClient) { }//Cuarta parte
  getClientes(){
    let url = environment.WA_PATH+ '/clientes/list';
    console.log(url);
    return this.http.get<any>(url);
  }

  saveClientes(cliente: Cliente){
    let url = environment.WA_PATH + '/clientes';
    return this.http.post<any>(url, cliente);
  }

  updateClientes(cliente: Cliente){
    let url = environment.WA_PATH + '/clientes'
    return this.http.put<any>(url, cliente);
  }

  deleteClientes(cliente: number): Observable<any>{
    let url = environment.WA_PATH + '/clientes?id=' + cliente;
    console.log(url);
    return this.http.delete<any>(url);
  }
}
