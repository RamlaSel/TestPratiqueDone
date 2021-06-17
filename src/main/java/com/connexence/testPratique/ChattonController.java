package com.connexence.testPratique;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
		
		// TODO: Filtrer et inverser la liste des chattons ici
		
		return chattons;
	}

}
