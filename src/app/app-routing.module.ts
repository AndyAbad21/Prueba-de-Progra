import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GestionComponent } from './pages/gestion/gestion.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'pages/gestion'},
  {path: 'pages/gestion', component:GestionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
