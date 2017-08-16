package jv.jpatpl.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jv.jpatpl.domain.Personne;
import jv.jpatpl.service.PersonneService;
import shared.CfgRapport;

@Controller
@RequestMapping("/rest/test")
public class TestController {

	@Autowired(required = true)
	private PersonneService personneService;

	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public @ResponseBody String test1(@PathVariable String name, HttpServletResponse response) {
		System.out.println("test1");
		Personne personne = new Personne(null, name, "");
		Personne personneRet = new Personne();
		try {
			personneRet = personneService.saveOrUpdate(personne);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}

		return personneRet.getNom();
	}

	@Autowired
	private AmqpAdmin admin;
	@Autowired
	private AmqpTemplate template;

	public String simpleProducerConsumerTest() {
		// Objet à envoyer
		String title = "Catch the rabbit! ";
		CfgRapport cfgRapport = new CfgRapport(new Date(), title);

		// Declare la file
		admin.declareQueue(new Queue("test"));

		// write message
		byte[] data = SerializationUtils.serialize(cfgRapport);
		template.convertAndSend(data);

		// read message
		/*
		 * CfgRapport received = (CfgRapport) template.receiveAndConvert();
		 * CfgRapport cfgRapport =
		 * (CfgRapport)SerializationUtils.deserialize(body);
		 */

		return "Envoyé: |" + cfgRapport.toString() + "|";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody String test() {
		System.out.println("test");
		// Test rabbit
		return simpleProducerConsumerTest();
	}

	@RequestMapping(value = "/personne", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Pojo getPojo() {
		System.out.println("getPojo");
		return new Pojo(1L, "vit", "je");
	}

	@RequestMapping(value = "/personnes", method = RequestMethod.GET)
	public @ResponseBody List<Pojo> getPersonnes() {
		System.out.println("getPojolist");
		ArrayList list = new ArrayList();
		list.add(new Pojo(1L, "vit", "je"));
		list.add(new Pojo(2L, "vit2", "je2"));
		return list;
	}

	private class Pojo {
		private Long id;
		private String nom;
		private String prenom;

		public Pojo(Long id, String nom, String prenom) {
			super();
			this.nom = nom;
			this.prenom = prenom;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

	}
}
