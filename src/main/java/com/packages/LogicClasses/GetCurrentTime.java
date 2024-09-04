package com.packages.LogicClasses;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class GetCurrentTime {

	  public String currentTime()
	  {
		     LocalDateTime currentDateTime = LocalDateTime.now();

		     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		     String dateTimeString = currentDateTime.format(formatter);
		     
		     return dateTimeString;
	  }
	 
}
