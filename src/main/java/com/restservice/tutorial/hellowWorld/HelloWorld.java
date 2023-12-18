package com.restservice.tutorial.hellowWorld;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class HelloWorld {

	@Autowired
	private UserService service;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(path = "/hello", method = RequestMethod.GET)
	public String getHello() throws IOException {
	
      
		return messageSource.getMessage("good.morning.message", null, "default text", LocaleContextHolder.getLocale());

	}

	@RequestMapping(path = "/helloWorldBean/{name}", method = RequestMethod.GET)
	public HelloWorldBean getHelloWorldBean(@PathVariable String name) {
		return new HelloWorldBean("loki" + " = " + name);
	}

	@RequestMapping(path = "/getAllUser", method = RequestMethod.GET)
	public List<UserDto> getAllUsers() {
		return service.getAllUser();
	}

	@GetMapping(path = "/getAllUser/{id}")
	public EntityModel<UserDto> getUserById(@PathVariable int id) {
		UserDto userDto = service.getUserById(id);
		
		EntityModel<UserDto> entityModel = EntityModel.of(userDto);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
		entityModel.add(link.withRel("all-users"));
		if (userDto == null) {
			throw new UserNotFoundException("id is: " + id);
		}
		return entityModel;
	}

	@PostMapping(path = "/addUser")
	public ResponseEntity<Object> addUser(@RequestBody UserDto dto) {
		UserDto userDto = service.save(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDto.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/deleteUser/{id}")
	public UserDto deleteUserById(@PathVariable int id) {
		UserDto userDto = service.deleteUserById(id);
		if (userDto == null) {
			throw new UserNotFoundException("id: " + id);
		}
		return userDto;

	}
}
