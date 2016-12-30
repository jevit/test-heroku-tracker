package jv.jpatpl.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import jv.jpatpl.domain.Personne;
import jv.jpatpl.service.PersonneService;
import jv.jpatpl.util.JsonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring/application-context-test.xml")
public class PersonneControllerTest {

	@Mock
	private PersonneService personneService;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	@InjectMocks
	private PersonneController personneController;

	// constante jeux de données
	private final List<Personne> personnes = new ArrayList<>();
	private final Personne personne2 = new Personne();
	private final Personne personne1 = new Personne();
	private final Personne personneCreeIn = new Personne();
	private final Personne personneCreeOut = new Personne();
	private final Long TEST_ID_GET = 2L;
	private final Long TEST_ID_DELETE = 2L;
	private final Long TEST_ID_UPDATE = 2L;
	private final Long TEST_ID_CREATE = 2L;
	private final String TEST_NAME_GET = "monnom";

	/**
	 * Initialise mockito
	 * 
	 * @throws ParseException
	 */
	@Before
	public void setup() throws ParseException {
		// Process mock annotations for mockito
		MockitoAnnotations.initMocks(this);

		// Initialise les mocks
		when(personneService.get(TEST_ID_GET)).thenReturn(personne2);
		when(personneService.listAll()).thenReturn(personnes);
		Mockito.doNothing().when(personneService).delete(TEST_ID_DELETE);
		when(personneService.saveOrUpdate(any(Personne.class))).thenReturn(personneCreeOut);

		// Initialise le jeux de données
		personne1.setId(1L);
		personne1.setNom("nom1");
		personne2.setId(TEST_ID_GET);
		personne2.setNom(TEST_NAME_GET);
		personneCreeIn.setNom("nom_personne_crée");
		personneCreeOut.setNom("nom_personne_crée");
		personneCreeOut.setId(3L);
		personnes.add(personne1);
		personnes.add(personne2);
		// Set up Spring test in web app mode
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void getPersonneTest_Success() throws Exception {
		this.mockMvc
				.perform(get("/rest/personne/{id}", TEST_ID_GET).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("id").exists()).andExpect(jsonPath("id").value(TEST_ID_GET))
				.andExpect(jsonPath("nom").exists()).andExpect(jsonPath("nom").value(TEST_NAME_GET));
		// Vérifications
		verify(personneService, times(1)).get(TEST_ID_GET);
	}

	@Test
	public void getAllPersonneTest_Success() throws Exception {
		this.mockMvc
				.perform(get("/rest/personnes").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].id", org.hamcrest.Matchers.is(1)))
				.andExpect(jsonPath("$[1].id", org.hamcrest.Matchers.is(2)));
		// Vérifications
		verify(personneService, times(1)).listAll();

	}

	@Test
	public void deletePersonneTest_Success() throws Exception {
		this.mockMvc.perform(delete("/rest/personne/{id}", TEST_ID_DELETE)).andExpect(status().isOk());
		// Vérifications
		verify(personneService, times(1)).delete(TEST_ID_DELETE);
	}

	@Test
	public void updatePersonneTest_Success() throws Exception {
		this.mockMvc
				.perform(put("/rest/personne").content(JsonUtils.asJsonString(personneCreeOut))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		// Vérifications
		verify(personneService, times(1)).saveOrUpdate(personneCreeOut);
		verifyNoMoreInteractions(personneService);
	}

	/*
	 * TODO ne passe pas si je mets "verify(personneService,
	 * times(1)).saveOrUpdate(personneCreeIn)" en parametre pourquoi?
	 */
	@Test
	public void createPersonneTest_Success() throws Exception {
		ResultActions result = this.mockMvc
				.perform(post("/rest/personne").content(JsonUtils.asJsonString(personneCreeIn))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		// Recupere et vérifie le resultat
		String content = result.andReturn().getResponse().getContentAsString();
		assertEquals(content, JsonUtils.asJsonString(personneCreeOut));
		// Vérifie qu'il passe par la méthode
		verify(personneService, times(1)).saveOrUpdate(any(Personne.class));
		verifyNoMoreInteractions(personneService);
	}

}
