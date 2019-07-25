package com.loanknit.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.clivern.racter.BotPlatform;
import com.clivern.racter.receivers.webhook.MessageReceivedWebhook;
import com.clivern.racter.senders.templates.MessageTemplate;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
public class WebhookHandler {

	BotPlatform platform;

	public void messageHandler(String payload) throws UnirestException, IOException {

		configureBotPlatform();
		MessageTemplate messageTemplate = platform.getBaseSender().getMessageTemplate();
		platform.getBaseReceiver().set(payload).parse();
		HashMap<String, MessageReceivedWebhook> messages = (HashMap<String, MessageReceivedWebhook>) platform
				.getBaseReceiver().getMessages();

		for (MessageReceivedWebhook message : messages.values()) {
			messageTemplate.setRecipientId(message.getUserId());
			messageTemplate.setMessageText(message.getMessageText());
			messageTemplate.setNotificationType("REGULAR");
			platform.getBaseSender().send(messageTemplate);
		}
	}

	private void configureBotPlatform() throws IOException {

		Map<String, String> config = new HashMap<>();
		config.put("app_id", "2538045316213796");
		config.put("page_access_token",
				"EAAkEVrlZCLCQBAMd3S50JfHR7uc5sYV3okKqiQwvaP9jaPhV4J2Bczmq6g9evmUn2NgCA2adergP99ZCdYEjSThFyZB8s8OW2bw0MqcBZB8YkwBETOUNy5FmnysXAfVCZCg6TAWOc6mUz30wR2tTpcLfzoC4wwZBLtS4FhX0lSbwZDZD");
		platform = new BotPlatform(config);

	}
}
