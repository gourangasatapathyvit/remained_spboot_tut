package com.restservice.tutorial.hellowWorld;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserService {

	private static List<UserDto> dtos = new ArrayList<>();
	private static int counter=0;

	static {
		dtos.add(new UserDto(++counter, "polo", LocalDate.now().minusYears(30)));
		dtos.add(new UserDto(++counter, "qwerty", LocalDate.now().minusYears(20)));
		dtos.add(new UserDto(++counter, "loki", LocalDate.now().minusYears(10)));
	}

	public List<UserDto> getAllUser() {
		return dtos;
	}

	public UserDto getUserById(int id) {

		Predicate<? super UserDto> predicate = e -> e.getId() == id;
		return dtos.stream().filter(predicate).findFirst().orElse(null);
	}

	public UserDto save(UserDto dto) {
		int tempCounter = ++counter;
		dtos.add(new UserDto(tempCounter, dto.getUserName(), dto.getBirthDate()));
		return dtos.get(tempCounter-1);
	}

	public UserDto deleteUserById(int id) {
		Predicate<? super UserDto> predicate = e->e.getId()==id;
		UserDto dto = dtos.stream().filter(predicate).findFirst().orElse(null);
		dtos.removeIf(predicate);
		return dto;
		
	}
}
