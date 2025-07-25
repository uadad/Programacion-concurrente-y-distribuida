program Febrero24;
const
    nC=20;
	nM=20;

Monitor m;
var
    Tinta,ComercialesLibres,esperaCoche: of integer;
	colaCoche,colaMoto,colaAsistente: of Condition;

export entraCoche,entraMoto,entraAsistente,saleCoche,saleMoto,saleAsistente;

procedure entraAsistente
begin

	while(Tinta>0) await(colaAsistente);

end

procedure saleAsistente
begin
    Tinta:=5;
     if(ComercialesLibres>1 && esperaCoche>0)
	 begin
	    if(ComercialesLibres==3)
		begin
		    signal(colaCoche);
			signal(colaMoto);
		end
		else then signal(colaCoche);
	 end
     else
          signal(colaMoto);
          signal(colaMoto);
          signal(colaMoto);
end

procedure entraCoche
begin

    esperaCoche++;
	while(Tinta==0 || ComercialesLibres<2) await(colaCoche);
	esperaCoche--;
	ComercialesLibres-=2;
         Tinta--;
        if(Tinta==0) then signal(colaAsistente);

end

procedure saleCoche
begin
   
	ComercialesLibres+=2;

	      if(esperaCoche>0) then signal(colaCoche);
	      else
		   begin
                        signal(colaMoto);
			signal(colaMoto);
		   end

end

procedure entraMoto
begin

	while(Tinta==0 || ComercialesLibres<1 || (esperaCoche>0 && ComercialesLibres>1))
         	await(colaMoto);

	ComercialesLibres--;
         Tinta--;
        if(Tinta==0) then signal(colaAsistente);

end;

procedure saleMoto
begin
    
	ComercialesLibres++;
	      if(esperaCoche>0 && ComercialesLibres>1) then signal(colaCoche);
	      else then signal(colaMoto);
end
begin
   ComercialesLibres:=3;
   Tinta:=5;
   esperaCoche:=0;
end

process type TCoche(id:integer);
begin
    m.entraCoche();
	sleep(2000);
	m.saleCoche();
end;

process type TMoto(id:integer);
begin
    m.entraMoto();
	sleep(2000);
	m.saleMoto();
end

process type Asistenete;
begin
    m.entraAsistente();
	sleep(4000);
	m.saleAsistente();
end

var
     i,j:integer;
	 Coche:array[1...nC] of TCoche;
	 Moto:array[1...nC] of TMoto;

begin
    cobegin
	   Asistenete;
	   for i:=1 to nC do Coche[i](i);
	   for j:=1 to nM do Moto[j](j);
	coend;
end

