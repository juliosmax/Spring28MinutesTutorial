package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	//VERSIONING WITH URI
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Evelin Paredes");
	}

	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Evelin","Paredes"));
	}


	//VERSIONING WITH PARAMS
	@GetMapping(value="person/param", params="version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Evelin Paredes");
	}

	@GetMapping(value="person/param", params="version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Evelin","Paredes"));
	}
	
	
	
   //VERSIONING WITH HEADERS
	@GetMapping(value="person/header", headers="X-API-VERSION=1")
	public PersonV1 headersV1() {
		return new PersonV1("Evelin Paredes");
	}

	@GetMapping(value="person/header", headers="X-API-VERSION=2")
	public PersonV2 headersV2() {
		return new PersonV2(new Name("Evelin","Paredes"));
	}
	
	
	//VERSIONING WITH PRODUCES ALSO AS CONTENT NEGOTIATION
	@GetMapping(value="person/produces", produces="application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Evelin Paredes");
	}

	@GetMapping(value="person/produces", produces="application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Evelin","Paredes"));
	}	

	

}
