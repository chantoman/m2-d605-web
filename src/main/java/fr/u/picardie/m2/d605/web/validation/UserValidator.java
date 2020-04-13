package fr.u.picardie.m2.d605.web.validation;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;

import fr.u.picardie.m2.d605.web.api.client.bean.User;

@Component
public class UserValidator {

//	public void validate(User user, Errors errors) {
//
//		if (!user.getPassword().equals(user.getConfirmedPassword())) {
//			errors.rejectValue("confirmedPassword", "password.different");
//		}
//
//	}

	public void validate(User user, ValidationContext context) {
		MessageContext messages = context.getMessageContext();
		if ("save".equals(context.getUserEvent())) {
			if (!user.getPassword().equals(user.getConfirmedPassword())) {
				messages.addMessage(
						new MessageBuilder().error().source("confirmedPassword").code("password.different").build());
			}
		}

	}

}
