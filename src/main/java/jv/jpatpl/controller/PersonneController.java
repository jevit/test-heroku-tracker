package jv.jpatpl.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jv.jpatpl.domain.Personne;
import jv.jpatpl.service.PersonneService;

@Controller
@RequestMapping("/rest")
public class PersonneController {
	@Autowired(required = true)
	private PersonneService personneService;

	@RequestMapping(value = "/personne", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Personne createPersonne(@RequestBody Personne personne) throws ParseException {
		Personne personneRet = personneService.saveOrUpdate(personne);
		return personneRet;
	}

	@RequestMapping(value = "/personnes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Personne> listPersonnes(Personne personne) {
		return personneService.listAll();
	}

	@RequestMapping(value = "/personne/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Personne getPersonne(@PathVariable("id") Long id) {
		Personne personneRet = personneService.get(id);
		return personneRet;
	}

	@RequestMapping(value = "/personne", method = RequestMethod.PUT)
	public @ResponseBody Personne updatePersonne(@RequestBody Personne personne) throws ParseException {
		Personne personneRet = personneService.saveOrUpdate(personne);
		return personneRet;
	}

	@RequestMapping(value = "/personne/{id}", method = RequestMethod.DELETE)
	public void deletePersonne(@PathVariable("id") Long id) {
		personneService.delete(id);
	}

}
