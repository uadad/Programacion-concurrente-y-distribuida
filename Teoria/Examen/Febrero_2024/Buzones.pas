program Febrero24;
const
    nC=20;
	nM=20;
	entraCoche,entraMoto,saleCoche,saleMoto,cargar,descargar:of mailbox of integer;
	permisoCoche,permisoMoto:array[1...20] of mailbox of integer;

process type Controlador;
var 
   Tinta,ComercialesLibres,id: of integer;

begin
   ComercialesLibres:=3;
   Tinta:=5;
   id:=0;
   repeat
      PriSelect
	     when Tinta==0
		 receive(descargar,any);
		 Tinta+=5;
	  or
	    when Tinta>0 && ComercialesLibres>1
		  receive(entraCoche,id);
		  ComercialesLibres-=2;
                  Tinta--;
		  if(Tinta==0) send(cargar,any);
		  send(permisoCoche[id],any);
	  or
	    when Tinta>0 && ComercialesLibres>0
		  receive(entraMoto,id);
		  ComercialesLibres--;
                  Tinta--;
		  if(Tinta==0) send(cargar,any);
		  send(permisoMoto[id],any); 
         or
		  receive(saleCoche,id);
		  ComercialesLibres+=2;
	  or
		  receive(saleMoto,id);
		  ComercialesLibres++;	   
	  endSelect;
   forever
end;


process type TCoche(id:integer);
begin 
    send(entraCoche,id);
	receive(permisoCoche[id],any);
	sleep(2000);
	send(saleCoche,id);
end;

process type TMoto(id:integer);
begin 
    send(entraMoto,id);
	receive(permisoMoto[id],any);
	sleep(2000);
	send(saleMoto,id);
end;

process type Asistenete;
begin 
    receive(cargar,any);
	sleep(4000);
	send(descargar,any);
end;

var 
     i,j:of integer;
	 Coche:array[1...nC] of TCoche;
	 Moto:array[1...nC] of TMoto;
	 
begin
    cobegin
	   Asistenete;
	   for i:=1 to nC do Coche[i](i);
	   for j:=1 to nM do Moto[j](j);
	coend;
end;
	 