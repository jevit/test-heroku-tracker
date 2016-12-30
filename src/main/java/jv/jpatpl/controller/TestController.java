package jv.jpatpl.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/test")
public class TestController {

	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public @ResponseBody String test1(@PathVariable String name, HttpServletResponse response) {
		System.out.println("test1");
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return name;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody String test() {
		System.out.println("test");
		return "test";
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
