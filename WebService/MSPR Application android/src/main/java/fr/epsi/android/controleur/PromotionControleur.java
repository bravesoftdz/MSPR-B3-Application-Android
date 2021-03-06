package fr.epsi.android.controleur;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import fr.epsi.android.dto.ClientDto;
import fr.epsi.android.dto.PromotionDto;
import fr.epsi.android.modele.Client;
import fr.epsi.android.modele.Promotion;
import fr.epsi.android.service.ClientService;
import fr.epsi.android.service.PromotionIntrouvableException;
import fr.epsi.android.service.PromotionService;

@RestController
@RequestMapping("/gostyle")
public class PromotionControleur {
	
	@Autowired
	private PromotionService promotionService;
//	@Autowired
//	private ClientService clientService;
	
	/**
	 * Fonction test pour verifier la connexion entre le webservice et l'application
	 * avec deux Promotion entrées en dure
	 * @return promotion objet JSON
	 */
	@GetMapping(path="/test", produces = "application/json")
	public List<Promotion>getTest() {
		Promotion promotion1 = new Promotion();
		promotion1.setId(1);
		promotion1.setCode("IOBI7488");
		promotion1.setUrl("polo");
		promotion1.setNom("Promotion test");
		promotion1.setDescription("Produit test");
		promotion1.setDate("2019-03-01");
		
		Promotion promotion2 = new Promotion();
		promotion2.setId(2);
		promotion2.setCode("IOFAIO213");
		promotion2.setUrl("tshirt");
		promotion2.setNom("Promotion test 2");
		promotion2.setDescription("2ème produit test");
		promotion2.setDate("2020-06-01");
		
		List<Promotion> listPromotion = new ArrayList<Promotion>();
		listPromotion.add(promotion1);
		listPromotion.add(promotion2);
		
		return listPromotion;
	}
	
	/**
	 * Reçois l'identifiant et le mot de passe passé dans la requête et créé l'objet client correspondant
	 * @param clientDto Identifiant et mot de passe passé par l'application
	 * @param uriBuilder 
	 * @return Un objet client correspondant au information du login passé par l'application
	 */
//	@PostMapping(path = "/login", produces = "applicaiton/json")
//	public ResponseEntity<Client> getLogin(@RequestBody ClientDto clientDto, UriComponentsBuilder uriBuilder){
//		Client client = promotionService.;
//		URI uri = uriBuilder.path("/gostyle/" + clientService.getId());
//		return ResponseEntity.created(uri);
//	}
	
	/**
	 * Recupère la liste de promotion depuis la couche service pour le transmettre a la vue
	 * @return La liste de toutes les promotions depuis la couche service
	 */
	@GetMapping(path = "/liste-promotion", produces = "application/json")
	public List<Promotion> getAllPromotion() {
		List<Promotion> promotions = promotionService.getAllPromo();
		return promotions;
	}
	
	/**
	 * Recupère les détails d'une promotion grâce au code passé dans le corp de la requête
	 * @param codePromotion Code de la promotion
	 * @return La promotion correspondant au code envoyé dans la requête
	 */
	@PostMapping(path = "/liste-promotion/details", produces = "application/json")
	public ResponseEntity<Promotion> getListePromotion(@RequestBody PromotionDto promotionDto, UriComponentsBuilder uriBuilder) {
		Promotion promotion = null;
		try {
			promotion = promotionService.getPromoByCode(promotionDto.getCode());
			URI uri = uriBuilder.path("/gostyle/liste_promotion/details/" + promotion.getCode()).build().toUri();
			return ResponseEntity.created(uri).body(promotion);
		} catch (PromotionIntrouvableException e) {
			e.printStackTrace();
			return (ResponseEntity<Promotion>) ResponseEntity.notFound();
		}
	}
}