package jv.jpatpl.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jv.jpatpl.domain.Utilisateur;
import jv.jpatpl.service.UtilisateurService;

@Controller
@RequestMapping("/rest/")
public class UtilisateurController {

	@Autowired(required=true)
	private UtilisateurService utilisateurService;
	
	@RequestMapping(value= "/utilisateur", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long createUtilisateur(Utilisateur utilisateur) throws ParseException{
		Long idUtilisateur = utilisateurService.saveOrUpdate(utilisateur);
		return idUtilisateur;
	}
	
	@RequestMapping(value = "/utilisateurs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Utilisateur> listUtilisateurs(Utilisateur utilisateur) {
		return utilisateurService.listAll();
	}

    @RequestMapping(value="/utilisateur/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody Utilisateur getUtilisateur(@PathVariable("id") Long id){
    	Utilisateur utilisateurRet = utilisateurService.get(id);
		return utilisateurRet;
    }
    
    @RequestMapping(value="/utilisateur/edit/{id}",method = RequestMethod.POST)
    public Long updateUtilisateur(Utilisateur utilisateur) throws ParseException{
		Long idUtilisateur = utilisateurService.saveOrUpdate(utilisateur);
		return idUtilisateur;
    }
    
	@RequestMapping(value="/utilisateur/delete/{id}",method = RequestMethod.GET)
    public void deleteUtilisateur(@PathVariable("id") Long id){
		utilisateurService.delete(id);
    }
 
}
