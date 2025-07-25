program FEBRERO20; 

const 
	nPA=15; 
	nPB=15;  


var llega1,llega2: array[1.15] of channel of integer;
    salen: array[1.30] of channel of integer;


process type TIPOA(id:integer); 
var
 cual:integer;
begin 
	repeat	 
		llegaA[id] ? cual;
		writeln('TIPO A siendo atendido'); 
		salen[id] ! cual;
	forever 
end; 

process type TIPOB(id:integer); 
 cual:integer
begin 
	repeat 
		llegaB[id] ? cual;
		writeln('TIPO A siendo atendido'); 
		salen[id+15] ! cual;
	forever 
end; 


process Controlador;
var libreGERENTE, libreCOMERCIAL,i,j,k,l,m,cual:integer;
		
begin  
	libreGERENTE=1;
	libreCOMERCIAL=2;
	repeat
		priselect
			for i :=1 to 15 REPLICATE
			when libreCOMERCIAL>0
				llegaA[i] ! 1
				libreCOMERCIAL:=libreCOMERCIAL-1;
		or
			for k :=1 to 15 REPLICATE
			when libreGERENTE>0
				llegaB[k] ! 2
				libreGERENTE:=0;
		or
			for j :=1 to 15 REPLICATE
			when libreCOMERCIAL>0
				llegaB[j] ! 1
				libreCOMERCIAL:=libreCOMERCIAL-1;
		or
			for l :=1 to 15 REPLICATE
			when libreGERENTE>0
				llegaA[l] ! 2
				libreGERENTE:=0;
		or
			for m:=1 to 30 REPLICATE
				salen[m] ? cual;
				if cual=1 then 	libreCOMERCIAL:=libreCOMERCIAL+1;
				else libreGERENTE:=1;
		end select;
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
    controlador;
  coend 
end. 




