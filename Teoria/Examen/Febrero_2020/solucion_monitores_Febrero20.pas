program FEBRERO20; 

const 
	nPA=15; 
	nPB=15;  

{Definicion del monitor}
monitor m;

	export
		entraA, salesA, salen;
	var

		libreGERENTE, libreCOMERCIAL:integer;
		colaVER, colaRECOGER: condition;
		
	procedure entraA(var cual:integer);
	begin
		if (libreGERENTE=0 and libreCOMERCIAL=0) then delay(colaVER);
		if libreCOMERCIAL>0 then begin
			libreCOMERCIAL=libreCOMERCIAL-1;
			cual=1;
		end
		else begin
			libreGERENTE=0;
			cual=2;
		end
	end

	procedure entraB(var cual:integer);
	begin
		if (libreGERENTE=0 and libreCOMERCIAL=0) then delay(colaRECOGER);
		if libreGERENTE>0 then begin
			libreGERENTE=0;
			cual=2;
		end
		else begin
			libreCOMERCIAL=libreCOMERCIAL-1;
			cual=1;
		end
	end

	procedure salen(cual:integer);
	begin
		if cual=1 then begin  //Estoy con comercial
			libreCOMERCIAL=libreCOMERCIAL+1;
			if not empty(colaVER) resume(colaVER);
			else resume(colaRECOGER);
		end
		else begin //Estoy con gerente
			libreGERENTE=1;
			if not empty(colaRECOGER) resume(colaRECOGER);
			else resume(colaVER);
		end 
	end

	begin 
		libreGERENTE=1;
		libreCOMERCIAL=2;
	end; 
(* Fin del monitor *)


process type TIPOA(id:integer); 
var
 cual:integer;
begin 
	repeat	 
		m.entraA(cual);
		writeln('TIPO A EN ITV'+cual); 
		m.salen(cual);
	forever 
end; 

process type TIPOB(id:integer); 
 cual:integer
begin 
	repeat 
		m.entraB(cual);
		writeln('TIPOB EN ITV'); 
		m.salen(cual);
	forever 
end; 

var 
   i,j: integer; 
   clienteA: array[1..nPA] of TIPOA;
   clienteB: array[1..nPB] of TIPOb; 
   
begin 
  cobegin 
    for i := 1 to nPA do clienteA[i](i); 
    for j := 1 to nPB do clienteB[j](j); 
  coend 
end. 

