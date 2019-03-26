package com.tav.coupons.api;
import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.tav.coupons.beans.UserDetails;
import com.tav.coupons.logic.SendMailController;

@Path("/sendmail")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SendMailApi {

	SendMailController sendMailController;

	public SendMailApi () {
		this.sendMailController = new SendMailController();
	}

	@POST
	public void sendMail (UserDetails userDetails) {

		sendMailController.sendMail(userDetails);
	}
}