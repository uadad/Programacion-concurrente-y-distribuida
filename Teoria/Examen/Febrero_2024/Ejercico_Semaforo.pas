


Program ududt
var
  s1,s2,s3: of Semaphore;

    process P1                  process P2                    process P3
    begin                       begin                         begin
	 repeat                      repeat                        repeat
	    acquire(s1);               acquire(s2);                  acquire(s3);
		                                                         release(s1);
																 acquire(s3);
																 
		 <<S.c>>					 <<S.c>>					   <<S.c>>
		 
		 release(s2);              release(s3);                  release(s1);
	      Resto1                    Resto2                         Resto3
	 forever                     forever        	            forever
	end                         end                            end