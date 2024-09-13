package com.octro.lobby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LobbyApplication {
	public static void main(String[] args) {
		SpringApplication.run(LobbyApplication.class, args);
	}
}

//.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
//.\bin\windows\kafka-server-start.bat .\config\server.properties
