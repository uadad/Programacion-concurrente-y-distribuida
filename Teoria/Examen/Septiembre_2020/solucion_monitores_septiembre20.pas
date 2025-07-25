program FEBRERO20; 

const 
	nPA=15; 


{Definicion del monitor}
monitor m;

	export
		entra, sale,;
	var

		libres, COVID:integer;
		cola: condition;
		
	procedure entra(var escovid:boolean);
	begin
		if (libres=0 or COVID=1) then delay(colaVER);
		libres--:
		if random(1)>0 then 	begin
			escovid=true;
			COVID++;
		else	escovid=false;
	end

	
	procedure sale(escovid:boolean);
	begin
		libres++;
		if escovid then begin 
			COVID--
			resume(cola);
			resume(cola);
			resume(cola);
		end
		else
			else resume(cola);
		end 
	end

	begin 
		libres=3;
		COVID=0;
	end; 
(* Fin del monitor *)


process type TPACIENTE(id:integer); 
var
 escovid:boolean;
begin 
	repeat	 
		m.entra(escovid);
		writeln('TIPO '+escovid+ ' dentro'); 
		m.salen(escovid);
	forever 
end; 


var 
   i: integer; 
   paciente: array[1..nPA] of TPACIENTE;
   
begin 
  cobegin 
    for i := 1 to nPA do paciente[i](i); 
  coend 
end.


