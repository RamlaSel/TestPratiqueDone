package com.connexence.testPratique;

import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ChattonController
{
	@Autowired
	private ChattonRepository chattonRepository;

	@GetMapping("/chattons")
	public List<Chatton> getChattons()
	{
		List<Chatton> chattons = chattonRepository.findAll();
		if (chattons.isEmpty())
		{
			Chatton premierChatton = new Chatton();
			premierChatton.setNom("Scott");
			premierChatton.setAge(4);
			chattonRepository.save(premierChatton);

			Chatton deuxiemeChatton = new Chatton();
			deuxiemeChatton.setNom("Marie-Antoinette");
			deuxiemeChatton.setAge(2);
			chattonRepository.save(deuxiemeChatton);

			chattons = chattonRepository.findAll();
		}

		// Liste des chattons age inferieur ou egale a 15 
		chattons = chattons.stream().filter(c->c.getAge()<=15).collect(Collectors.toList());
		
		Collections.reverse(chattons);
		
		return chattons;
	}
	//Ajout chatton
	@PostMapping("/chattons")
	public ResponseEntity setChattons( @RequestBody Chatton chatton)
	{
		// Retourner ERROR si nom est null ou nom est vide ou age est null ou age est superieur a 30 
		if(chatton.getNom() == null || chatton.getNom().isEmpty() || chatton.getAge()==null || chatton.getAge()>30){
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		try {
			chattonRepository.save(chatton);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity(HttpStatus.OK);
	}

}
