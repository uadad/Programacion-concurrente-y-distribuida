program Febrero22; 
 const 
	nC=20; 
	nF=20;
	
{Definicion del monitor}
monitor m;

	export
		entraCoche, entraFurgo, saleCoche, saleFurgo;
	var

	lateraleslibres,furgolateral, esperacoche, esperafurgo:integer;
	central:char;
	colacoche, colafurgo :condition	;

	procedure entraCoche(var donde:char);
	begin
		esperacoche++;
		if lateraleslibres = 0 && central != 'n' then delay(colacoche);            
		esperacoche--;
		if central = 'n' then begin
			central = 'c';                
			donde= 'c';
		end;
		else begin
			lateraleslibres--;
			donde='l';
		end
	end

	procedure entraFurgo(var donde:char);
	begin
		esperafurgo++;
		if  (lateraleslibres = 0 && central != 'n') || central = 'f' || (lateraleslibres = 0 && furgolateral > 0)  then delay(colafurgo);
		//if not ((lateraleslibres > 0 && central != 'f') || (central = 'n' && furgolateral = 0)) then delay(colafurgo);
		esperafurgo--;
		if lateraleslibres > 0 then begin
			lateraleslibres--;
			furgolateral++;
			donde = 'l';
		end 
		else begin
			central = 'f';
			donde = 'c';
		end
	end

	procedure saleCoche(donde:char) 
   begin      
		if donde = 'l' then begin
			lateraleslibres++;
			if esperafurgo > 0 && central != 'f' then colafurgo.signal();			 
			else colacoche.signal();
		end
		else begin
			central = 'n';
			if (esperafurgo > 0 && furgolateral == 0) then	colafurgo.signal();
			else colacoche.signal();
		end

	procedure saleFurgo(donde:char);
	begin
		if donde = 'l' then begin
			lateraleslibres++;
			furgolateral--;
		end 
		else central = 'n';
		if (esperafurgo > 0) colafurgo.signal();
		else colacoche.signal();
	end

	begin 
		lateraleslibres = 2;
		furgolateral = 0;
		esperacoche = 0;
		esperafurgo = 0;
		central = 'n';
	end; 
(* Fin del monitor *)


 process type TCoche(id:integer); 
	var donde:char;
 begin 
	m.entraCoche(donde);
	{Lavando coche}
	m.saleCoche(donde);
	
 end; 

 process type TFurgo(id:integer); 
	var donde:char;
 begin 
	m.entraFurgo(donde);
	{Lavando coche}
	m.saleFurgo(donde);
	
 end; 

 var 
	i,j: integer; 
	Coche: array[1..nC] of TCoche; 
	Furgo: array[1..nF] of TFurgo;

 begin 
	cobegin 
		for i := 1 to nC do Coche[i](i); 
		for j := 1 to nF do Furgo[i](i); 
	coend 
 end. 

